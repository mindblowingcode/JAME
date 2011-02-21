// renderimpl.cpp
// this file is part of Context Free
// ---------------------
// Copyright (C) 2006-2008 Mark Lentczner - markl@glyphic.com
// Copyright (C) 2006-2008 John Horigan - john@glyphic.com
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
// 
// John Horigan can be contacted at john@glyphic.com or at
// John Horigan, 1209 Villa St., Mountain View, CA 94041-1123, USA
//
// Mark Lentczner can be contacted at markl@glyphic.com or at
// Mark Lentczner, 1209 Villa St., Mountain View, CA 94041-1123, USA
//
//


#include "renderimpl.h"

#include <iterator>
#include <string>
#include <algorithm>
#include <stack>

#ifdef _WIN32
#include <float.h>
#define isfinite _finite
#else
#include <math.h>
#endif

#include "shapeSTL.h"
#include "primShape.h"
#include "builder.h"

using namespace std;


//#define DEBUG_SIZES
#ifndef DEBUG_SIZES
const unsigned int MOVE_FINISHED_AT     = 2000000; // when this many, move to file
const unsigned int MOVE_UNFINISHED_AT   =  500000; // when this many, move to files
const unsigned int MAX_MERGE_FILES      =     200; // maximum number of files to merge at once
#else
const unsigned int MOVE_FINISHED_AT     =    1000; // when this many, move to file
const unsigned int MOVE_UNFINISHED_AT   =     200; // when this many, move to files
const unsigned int MAX_MERGE_FILES      =       2; // maximum number of files to merge at once
#endif
const double SHAPE_BORDER = 2.0; // multiplier of shape size when calculating bounding box
const double FIXED_BORDER = 8.0; // fixed extra border, in pixels



RendererImpl::RendererImpl(CFDGImpl* cfdg,
                            int width, int height, float minSize,
                            int variation, double border)
    : m_cfdg(cfdg), m_canvas(0),
    m_maxShapes(500000000),
    mNextFramePoint(-1.0),

    mFinishedFileCount(0),
    mUnfinishedFileCount(0),

    mVariation(variation),

    mScaleArea(0.0), mScale(0.0),
    mFixedBorder(1.0), mShapeBorder(1.0),
    mTotalArea(0.0),
    
    m_currScale(0.0), m_minArea(0.3), 
    m_outputSoFar(0)
{
    m_stats.shapeCount = m_stats.toDoCount = 0;
    minSize = (minSize == 0.0F) ? 0.3F : minSize;
    m_minArea = minSize * minSize;

    mFixedBorder = FIXED_BORDER * ((border <= 1) ? border : 1);
    mShapeBorder = SHAPE_BORDER * ((border <= 1) ? 0.5 : (border / 2));
    Bounds::setScale(mShapeBorder);

    double tile_x, tile_y;
    m_tiled = m_cfdg->isTiled(0, &tile_x, &tile_y);
    m_sized = m_cfdg->isSized(&tile_x, &tile_y);
    
    m_width = width;
    m_height = height;
    if (m_tiled || m_sized) {
        mFixedBorder = mShapeBorder = 0.0;
        mBounds.mMin_X = -(mBounds.mMax_X = tile_x / 2.0);
        mBounds.mMin_Y = -(mBounds.mMax_Y = tile_y / 2.0);
        mBounds.mValid = true;
        rescaleOutput(m_width, m_height, true);
        mScaleArea = m_currScale * m_currScale;
    }
}

RendererImpl::~RendererImpl()
{
}

void
RendererImpl::setMaxShapes(int n)
{
    m_maxShapes = n ? n : 400000000;
}

void
RendererImpl::resetBounds()
{
    mBounds = Bounds();
}


void
RendererImpl::outputPrep(Canvas* canvas)
{
    m_canvas = canvas;
	
	if (canvas) {
		m_width = canvas->mWidth;
		m_height = canvas->mHeight;
	}
    
    requestStop = false;
    requestFinishUp = false;
    requestUpdate = false;
    
    m_stats.inOutput = false;
	m_stats.animating = false;
	
    mNextFramePoint = -1.0;
}


double
RendererImpl::run(Canvas * canvas, bool partialDraw)
{
    outputPrep(canvas);
    if (canvas)
        canvas->scale(m_currScale);
    
    m_stats.shapeCount = m_stats.toDoCount = 0;
    m_unfinishedInFilesCount = 0;

    int reportAt = 250;

    Shape initShape = m_cfdg->getInitialShape();
    initShape.mWorldState.mRand48Seed.seed(0x330E, 
        (unsigned short)((mVariation >> 16) & 0xffff), 
        (unsigned short)(mVariation & 0xffff)); 
    // Init color to opaque black
    initShape.mWorldState.m_ColorTarget = HSBColor(0, 0, 0, 1);
    initShape.mWorldState.m_Color = HSBColor(0, 0, 0, 1);   // only place where alpha is set to 1
    processShape(initShape);

    for (;;) {
        if (requestStop) break;
        if (requestFinishUp) break;
        
        fileIfNecessary();
        
        if (mUnfinishedShapes.empty()) break;
        if ((m_stats.shapeCount + m_stats.toDoCount) > m_maxShapes)
            break;

        // Get the largest unfinished shape
        multiset<Shape>::iterator biggest = mUnfinishedShapes.end();
        Shape s = *(--biggest);
        mUnfinishedShapes.erase(biggest);
        m_stats.toDoCount--;
        
        executeShape(s);
        
        if (requestUpdate || (m_stats.shapeCount > reportAt)) {
            if (partialDraw)
              outputPartial();
            outputStats();
            reportAt = 2 * m_stats.shapeCount;
        }
    }
    
    if (!requestStop) {
        stable_sort(mFinishedShapes.begin(), mFinishedShapes.end());
        outputFinal();
    }

    outputStats();
    return m_currScale;
}

void
RendererImpl::executeShape(Shape& s)
{
    static deque<Shape> shapeStack;
    static deque<int> counterStack;
    static deque<unsigned> repStack;
    
    Rule* rule = m_cfdg->findRule(s.mShapeType, s.mWorldState.mRand48Seed.getDouble());
    for (unsigned rep = 0; rep < rule->mReplacements.size(); ++rep) 
    {
        if (requestStop) {
            shapeStack.clear();
            counterStack.clear();
            repStack.clear();
            return;
        }
        
        if (rule->mReplacements[rep].mShapeType == primShape::loopStartType) {
            shapeStack.push_back(s);
            counterStack.push_back(0);
            repStack.push_back(rep);
            continue;
        }
        
        if (rule->mReplacements[rep].mShapeType == primShape::loopEndType) {
            if (repStack.back() == rep - 2) {
                // If this is a single-replacement loop (with no chance of a
                // nested loop) then emulate the v2.1 behavior so that the 
                // variations on older cfdg files still work.
                Shape copy = s;
                s = shapeStack.back();
                copy.mWorldState.mRand48Seed.bump();
                for (int c = 1; c < rule->mReplacements[rep].mLoopCount; ++c) {
                    s.mWorldState.mRand48Seed.bump();
                    copy *= rule->mReplacements[rep];
                    copy.mWorldState.mRand48Seed.bump();
                    Shape child = copy;
                    child *= rule->mReplacements[rep - 1];
                    processShape(child);
                    if (requestStop) break;
                }
                shapeStack.pop_back();
                counterStack.pop_back();
                repStack.pop_back();
            } else  if (++(counterStack.back()) >= rule->mReplacements[rep].mLoopCount) {
                s = shapeStack.back();
                shapeStack.pop_back();
                counterStack.pop_back();
                repStack.pop_back();
            } else {
                s *= rule->mReplacements[rep];
                rep = repStack.back();
            }
            continue;
        }
        
        // bump random seed
        s.mWorldState.mRand48Seed.bump();
        for (unsigned i = 0; i < shapeStack.size(); ++i)
            shapeStack[i].mWorldState.mRand48Seed.bump();
        
        Shape child = s;
        child.mWorldState.mRand48Seed.bump();
        child *= rule->mReplacements[rep];
        processShape(child);
    }
}

void
RendererImpl::draw(Canvas* canvas)
{
    outputPrep(canvas);
    outputFinal();
    outputStats();
}

class OutputBounds : public ShapeOp
{
public:
    OutputBounds(int frames, double frameInc, double shapeBorder, 
                 int width, int height, double fixedBorder);
    void apply(const FinishedShape&);
	
	const Bounds& frameBounds(int frame) { return mFrameBounds[frame]; }
	int           frameCount(int frame) { return mFrameCounts[frame]; }
	
	void finalAccumulate();
		// call after all the frames to compute the bounds at each frame
		
	void backwardFilter(double framesToHalf);
	void smooth(int window);
	
private:
	double			mFrameInc;
	double			mShapeBorder;
    vector<Bounds>	mFrameBounds;
	vector<int>		mFrameCounts;
    double          mScale;
    int             mWidth;
    int             mHeight;
    double          mFixedBorder;
};

OutputBounds::OutputBounds(int frames, double frameInc, double shapeBorder, 
                           int width, int height, double fixedBorder)
	: mFrameInc(frameInc), mShapeBorder(shapeBorder), mScale(0.0),
      mWidth(width), mHeight(height), mFixedBorder(fixedBorder)
{
	mFrameBounds.resize(frames);
	mFrameCounts.resize(frames, 0);
}

void
OutputBounds::apply(const FinishedShape& s)
{
	int frame = (int)floor(s.mCumulativeArea / mFrameInc);
	if (frame >= (int)mFrameBounds.size()) return;
	
	agg::trans_affine tr;
	s.GetTransform(tr);
	
    if (mScale == 0.0) {
        // If we don't know the approximate scale yet then just
        // make an educated guess.
        mScale = (mWidth + mHeight) / sqrt(fabs(tr.determinant()));
    }
	mFrameBounds[frame].update(tr, s.mPath, s.mAttributes, mScale);
	mFrameCounts[frame] += 1;
    mScale = mFrameBounds[frame].computeScale(mWidth, mHeight, mFixedBorder, false);
}

void
OutputBounds::finalAccumulate()
{
	vector<Bounds>::iterator prev, curr, end;
	prev = mFrameBounds.begin();
	end = mFrameBounds.end();
	if (prev == end) return;
	
	for (curr = prev + 1; curr != end; prev = curr, ++curr) {
		*curr += *prev;
	}
}

void
OutputBounds::backwardFilter(double framesToHalf)
{
	double alpha = pow(0.5, 1.0 / framesToHalf);
	
	vector<Bounds>::reverse_iterator prev, curr, end;
	prev = mFrameBounds.rbegin();
	end = mFrameBounds.rend();
	if (prev == end) return;

	for (curr = prev + 1; curr != end; prev = curr, ++curr) {
		*curr = curr->interpolate(*prev, alpha);
	}
} 

void
OutputBounds::smooth(int window)
{
	int frames = mFrameBounds.size();
	if (frames == 0) return;
	
	mFrameBounds.resize(frames + window - 1, mFrameBounds.back());
	
	vector<Bounds>::iterator write, read, end;
	read = mFrameBounds.begin();
	
	double factor = 1.0 / window;
	
	Bounds accum;
	for (int i = 0; i < window; ++i)
		accum.gather(*read++, factor);
	
	write = mFrameBounds.begin();
	end = mFrameBounds.end();
	for (;;) {
		Bounds old = *write;
		*write++ = accum;
		accum.gather(old, -factor);
		
		if (read == end) break;
		
		accum.gather(*read++, factor);
	} 
	
	mFrameBounds.resize(frames, Bounds());
}


void
RendererImpl::animate(Canvas* canvas, int frames, bool zoom)
{
    outputPrep(canvas);

    if (zoom) resetBounds();
    
    // start with a blank frame
    
    int curr_width = m_width;
    int curr_height = m_height;
    rescaleOutput(curr_width, curr_height, true);
    
    m_canvas->start(true, m_cfdg->backgroundColor,
        curr_width, curr_height);
    m_canvas->end();
	
    double frameInc = mTotalArea / frames;
    
	OutputBounds outputBounds(frames, frameInc, mShapeBorder, 
                              curr_width, curr_height, mFixedBorder);
	{
        system()->message(false, "Computing zoom");

		forEachShape(true, outputBounds);
		outputBounds.finalAccumulate();
		outputBounds.backwardFilter(10.0);
		//outputBounds.smooth(3);
	}
	
	m_stats.shapeCount = 0;
	m_stats.animating = true;
	
    for (int frameCount = 1; frameCount <= frames; ++frameCount)
    {
        system()->message(false, "Generating frame %d of %d", frameCount, frames);
		
		if (zoom) mBounds = outputBounds.frameBounds(frameCount - 1);
		m_stats.shapeCount += outputBounds.frameCount(frameCount - 1);
        mNextFramePoint = frameInc * frameCount;
        outputFinal();
		outputStats();

        if (requestStop || requestFinishUp) break;
    }
	
	m_stats.animating = false;
	outputStats();
	system()->message(false, "Animation of %d frames complete", frames);
}

void
RendererImpl::processShape(const Shape& s)
{
    double area = s.area();
    if (!isfinite(area)) {
        requestStop = true;
        system()->message(true, "A shape got too big.");
        return;
    }
    
    if (m_cfdg->getShapeType(s.mShapeType) == CFDGImpl::ruleType &&
        m_cfdg->shapeHasRules(s.mShapeType)) 
    {
        // only add it if it's big enough (or if there are no finished shapes yet)
        if (!mBounds.valid() || (area * mScaleArea >= m_minArea)) {
            m_stats.toDoCount++;
            mUnfinishedShapes.insert(s);
        }
    } else if (primShape::isPrimShape(s.mShapeType) ||
        m_cfdg->getShapeType(s.mShapeType) == CFDGImpl::pathType) 
    {
        Shape ps = s;
        agg::path_storage* path = 0;
        pathAttr* attr = &Builder::DefaultPathAttributes;
        if (m_cfdg->getShapeType(ps.mShapeType) == CFDGImpl::pathType) {
            Rule* rule = m_cfdg->findRule(ps.mShapeType, 0.0);
            path = rule->mPath;
            attr = rule->mAttributes;
        } else {
            path = primShape::shapeMap[ps.mShapeType];
        }
        static stack<Shape> shapeStack;
        static stack<int> counterStack;
        static stack<pathAttr*> attrStack;
        for (; attr; attr = attr->mNext) {
            if (attr->mCommand == pathAttr::loopStart) {
                shapeStack.push(ps);
                counterStack.push(0);
                attrStack.push(attr);
                continue;
            }
            
            if (attr->mCommand == pathAttr::loopEnd) {
                if (++(counterStack.top()) >= attr->mCount) {
                    ps = shapeStack.top();
                    shapeStack.pop();
                    counterStack.pop();
                    attrStack.pop();
                } else {
                    ps *= attr->mWorldState;
                    attr = attrStack.top();
                }
                continue;
            }
            
            Shape temp = ps;
            temp *= attr->mWorldState;
            m_stats.shapeCount++;
            mTotalArea += temp.area() * attr->mArea;
            FinishedShape fs(temp, mTotalArea, path, attr);
            for (int i = 0; i < 6; i++) {
                if (!isfinite(fs.m_MiniTransform[i])) {
                    requestStop = true;
                    system()->message(true, "A shape got too big.");
                    return;
                }
            }
            mFinishedShapes.push_back(fs);
            if (!m_tiled && !m_sized) {
                if (mScale == 0.0) {
                    // If we don't know the approximate scale yet then just
                    // make an educated guess.
                    mScale = (m_width + m_height) / sqrt(fabs(temp.mWorldState.m_transform.determinant()));
                }
                mBounds.update(temp.mWorldState.m_transform, path, attr, mScale);
                mScale = mBounds.computeScale(m_width, m_height, mFixedBorder, false);
                mScaleArea = mScale * mScale;
            }
        }
    } else {
        requestStop = true;
        system()->message(true, "Shape with no rules encountered: %s.", 
            m_cfdg->decodeShapeName(s.mShapeType).c_str());
    }
}


//-------------------------------------------------------------------------////


void
RendererImpl::fileIfNecessary()
{
    if (mFinishedShapes.size() > MOVE_FINISHED_AT)
        moveFinishedToFile();

    if (mUnfinishedShapes.size() > MOVE_UNFINISHED_AT) 
        moveUnfinishedToTwoFiles();
    else if (mUnfinishedShapes.empty())
        getUnfinishedFromFile();
}

void
RendererImpl::moveUnfinishedToTwoFiles()
{
    ref_ptr<TempFile> t1 = TempFile::build(system(), "cfdg-temp-unfin-",
                                            "expansion", ++mFinishedFileCount);
    ref_ptr<TempFile> t2 = TempFile::build(system(), "cfdg-temp-unfin-",
                                            "expansion", ++mFinishedFileCount);

    m_unfinishedFiles.push_back(t1);
    m_unfinishedFiles.push_back(t2);

    ostream* f1 = t1->forWrite();
    ostream* f2 = t2->forWrite();
    
    system()->message(false, "Writing %s temp files %d & %d",
                        t1->type().c_str(), t1->number(), t2->number());

    int count = mUnfinishedShapes.size() / 3;

    multiset<Shape>::iterator usi = mUnfinishedShapes.begin();

    for (int i = 0; i < count; ++i, ++usi)
        usi->write(*f1);

    for (int j = 0; j < count; ++j, ++usi)
        usi->write(*f2);

    mUnfinishedShapes.erase(mUnfinishedShapes.begin(), usi);

    delete f1;
    delete f2;
    
    m_unfinishedInFilesCount += 2 * count;
}

void
RendererImpl::getUnfinishedFromFile()
{
    if (m_unfinishedFiles.empty()) return;
    
    ref_ptr<TempFile> t = m_unfinishedFiles.front();
    
    istream* f = t->forRead();

    copy(istream_iterator<Shape>(*f), istream_iterator<Shape>(), 
         inserter<multiset<Shape> >(mUnfinishedShapes, mUnfinishedShapes.end()));

    delete f;

    m_unfinishedFiles.pop_front();
}

//-------------------------------------------------------------------------////

void
RendererImpl::moveFinishedToFile()
{
    ref_ptr<TempFile> t = TempFile::build(system(), "cfdg-temp-fin-",
                                            "shapes", ++mFinishedFileCount);
    m_finishedFiles.push_back(t);
    
    ostream* f = t->forWrite();

    stable_sort(mFinishedShapes.begin(), mFinishedShapes.end());

    copy(mFinishedShapes.begin(), mFinishedShapes.end(), 
         ostream_iterator<FinishedShape>(*f));

    mFinishedShapes.clear();
    
    delete f;
}

//-------------------------------------------------------------------------////

void RendererImpl::rescaleOutput(int& curr_width, int& curr_height, bool final)
{
	agg::trans_affine trans;
    double scale;
	
	scale = mBounds.computeScale(curr_width, curr_height,
		mFixedBorder , true, &trans, m_tiled || m_sized);

    if (final                       // if final output
    || m_currScale == 0.0           // if first time, use this scale
    || (m_currScale * 0.90) > scale)// if grew by more than 10%
    {
        m_currScale = scale;
        m_currTrans = trans;
        m_outputSoFar = 0;
        m_stats.fullOutput = true;
    }
}


void
RendererImpl::forEachShape(bool final, ShapeOp& op)
{
	if (!final) {
		for_each(mFinishedShapes.begin() + m_outputSoFar, mFinishedShapes.end(),
			op.outputFunction());
		m_outputSoFar = mFinishedShapes.size();
	}
	else if (m_finishedFiles.empty()) {
		for_each(mFinishedShapes.begin(), mFinishedShapes.end(),
			op.outputFunction());
	}
	else {
		deque< ref_ptr<TempFile> >::iterator begin, last, end;

		while (m_finishedFiles.size() > MAX_MERGE_FILES) {
			OutputMerge merger(*system());
			
			begin = m_finishedFiles.begin();
			last = begin + (MAX_MERGE_FILES - 1);
			end = last + 1;
							
			for_each(begin, end, merger.tempFileAdder());
				
			ref_ptr<TempFile> t = TempFile::build(system(), "cfdg-temp-mrg-",
													"merge", ++mFinishedFileCount);

			ostream* f = t->forWrite();
			system()->message(false, "Merging temp files %d through %d",
								(*begin)->number(), (*last)->number());
								
			merger.merge(ostream_iterator<FinishedShape>(*f));

			delete f;
			
			m_finishedFiles.erase(begin, end);
			m_finishedFiles.push_back(t);
		}

		OutputMerge merger(*system());
		
		begin = m_finishedFiles.begin();
		end = m_finishedFiles.end();
		
		for_each(begin, end, merger.tempFileAdder());
		
		merger.addShapes(mFinishedShapes.begin(), mFinishedShapes.end());
		merger.merge(op.outputIterator());
	}
}


class OutputDraw : public ShapeOp
{
public:
    OutputDraw(RendererImpl& renderer, bool final);
    
    void apply(const FinishedShape&);

    class Stopped { }; 

private:
    RendererImpl& mRenderer;
    bool mFinal;
        
    int mCircleType;
    int mSquareType;
    int mTriangleType;
    int mLoopStartType;
    int mLoopEndType;
};

OutputDraw::OutputDraw(RendererImpl& renderer, bool final)
     : mRenderer(renderer), mFinal(final)
{
    static string circle_name = "CIRCLE";
    static string square_name = "SQUARE";
    static string triangle_name = "TRIANGLE";
    
    mCircleType = mRenderer.m_cfdg->encodeShapeName(circle_name);
    mSquareType = mRenderer.m_cfdg->encodeShapeName(square_name);
    mTriangleType = mRenderer.m_cfdg->encodeShapeName(triangle_name);
}

void
OutputDraw::apply(const FinishedShape& s)
{
    if (mRenderer.requestStop) throw Stopped();
    if (!mFinal  &&  mRenderer.requestFinishUp) throw Stopped();
    
    if (mRenderer.requestUpdate)
        mRenderer.outputStats();


    if (mRenderer.mNextFramePoint >= 0.0
    &&  mRenderer.mNextFramePoint < s.mCumulativeArea)
        return;


    mRenderer.m_stats.outputDone += 1;

    agg::trans_affine tr;
    s.GetTransform(tr);

    tr *= mRenderer.m_currTrans;

    double a = fabs(tr.determinant()); 
    if (!isfinite(a) || a < mRenderer.m_minArea) return;

    if (mRenderer.m_cfdg->getShapeType(s.mPrimitiveShapeType) == CFDGImpl::pathType) {
        mRenderer.m_canvas->path(s.mColor, tr, s.mPath, s.mAttributes);
    } 
    else if (s.mPrimitiveShapeType == mCircleType) {
        mRenderer.m_canvas->circle(s.mColor, tr);
    }
    else if (s.mPrimitiveShapeType == mSquareType) {
        mRenderer.m_canvas->square(s.mColor, tr);
    }
    else if (s.mPrimitiveShapeType == mTriangleType) {
        mRenderer.m_canvas->triangle(s.mColor, tr);
    } else {
        mRenderer.system()->message(true,
            "Non drawable shape with no rules: %s",
            mRenderer.m_cfdg->decodeShapeName(s.mPrimitiveShapeType).c_str());
    }
}


void RendererImpl::output(bool final)
{
    if (!m_canvas)
        return;
        
    if (!final &&  !m_finishedFiles.empty())
        return; // don't do updates once we have temp files
        
    m_stats.inOutput = true;
    m_stats.fullOutput = final;
    m_stats.finalOutput = final;
    m_stats.outputCount = m_stats.shapeCount;

    int curr_width = m_width;
    int curr_height = m_height;
    rescaleOutput(curr_width, curr_height, final);
    
    m_stats.outputDone = m_outputSoFar;
    
    m_canvas->start(m_outputSoFar == 0, m_cfdg->backgroundColor,
        curr_width, curr_height);

    OutputDraw draw(*this, final);
    try {
		forEachShape(final, draw);
    }
    catch (OutputDraw::Stopped) { }

    m_canvas->end();
    m_stats.inOutput = false;
    m_stats.outputTime = m_canvas->mTime;
}


void
RendererImpl::outputStats()
{
    system()->stats(m_stats);
    requestUpdate = false;
}

