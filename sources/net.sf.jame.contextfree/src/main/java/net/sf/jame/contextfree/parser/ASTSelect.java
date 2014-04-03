package net.sf.jame.contextfree.parser;

import java.util.List;


class ASTSelect extends ASTExpression {
		private List<ASTExpression> choices;
		private ASTExpression selector;
		private int tupleSize;
		private int indexCache;
		private String entropy;
		private ASTSelect weakPointer;
		private ASTExpression arguments;
		
		public ASTSelect(ASTExpression arguments, boolean asIf) {
			super();
			this.arguments = arguments;
			indexCache = 0;
			tupleSize = -1;
			selector = null;
//	        args->entropy(ent);
//	        ent.append("\xB5\xA2\x4A\x74\xA9\xDF");
//	        
//	        arguments = args.release()->simplify();
//	        ASTexpression::iterator arg = arguments->begin(), arg_end = arguments->end();
//	        
//	        if (arg == arg_end) {
//	            CfdgError::Error(loc, "select() function requires at least two arguments");
//	            return;
//	        }
//	        
//	        if (arg->mType != NumericType || arg->evaluate(0, 0) != 1) {
//	            CfdgError::Error(arg->where, "select() selector must be a numeric 1-tuple");
//	            return;
//	        }
//	        
//	        selector = &*arg;
//	        ++arg;
//	        if (selector->isConstant) {
//	            indexCache = getIndex();
//	            selector = NULL;
//	        }
//	        
//	        bool firstOne = true;
//	        while (arg != arg_end) {
//	            choices.push_back(&*arg);
//	            
//	            if (firstOne) {
//	                mType = arg->mType;
//	                tupleSize = (mType == NumericType) ? arg->evaluate(0, 0) : 1;
//	                firstOne = false;
//	                if (tupleSize == -1)
//	                    CfdgError::Error(arg->where, "Error determining tuple size");
//	            } else {
//	                if (mType != arg->mType)
//	                    CfdgError::Error(arg->where, "select() choices must be of same type");
//	                else if (mType == NumericType && tupleSize != -1 && 
//	                         arg->evaluate(0, 0) != tupleSize)
//	                    CfdgError::Error(arg->where, "select() choices must be of same length");
//	            }
//	            ++arg;
//	        }
//	        
//	        if (firstOne) {
//	            CfdgError::Error(loc, "select() function requires at least two arguments");
//	        }
//	        
//	        if (selector->isConstant)
//	            isConstant = choices[getIndex()]->isConstant;
		}

		@Override
		public void entropy(StringBuilder e) {
	        e.append(entropy);
		}

		@Override
		public ASTStackType evalArgs(ASTStackType parent, RTI rti) {
	        if (type != ExpType.RuleType) {
	            throw new RuntimeException("Evaluation of a non-shape select() in a shape context");
	        }

	        return choices.get(getIndex(rti)).evalArgs(parent, rti);
		}

		@Override
		public int flatten(List<ASTExpression> dest) {
	        if (type !=  ExpType.NumericType || tupleSize < 2) {
	            dest.add(this);
	            return 1;
	        }
	        
	        int tups = tupleSize;
	        {      
	        	// deleted an if, but keep the indent
	            // Flatten a select() of n-tuples into n selects() of 1-tuples
	            // The selector expression for the n selects() must only be evaluated
	            // once, since it may be random. So the first one gets the selector
	            // and the rest get weak pointers to the first one. This assumes that
	            // the first select() is always evaluated first and that if the first
	            // select() is deleted then the rest will not be evaluated. I am
	            // certain that these are both true. simplify() will never delete the
	            // first one because it only deletes when the selector is constant and
	            // we know that it isn't.
//	            std::vector<ASTselect*> tempSel(tupleSize);
//	            for (unsigned i = 0; i < tups; ++i) {
//	                tempSel[i] = new ASTselect(where);
//	                tempSel[i]->selector = i ? NULL : selector;
//	                tempSel[i]->weakPointer = i  && selector ? tempSel[0] : NULL;
//	                tempSel[i]->tupleSize = 1;
//	                tempSel[i]->mType = NumericType;
//	                tempSel[i]->ent = ent;
//	                tempSel[i]->indexCache = indexCache;
//	                tempSel[i]->arguments = i ? NULL : arguments;
//	                tempSel[i]->choices.reserve(choices.size());
//	            }
//	            arguments = NULL;
//	            
//	            ASTexpArray temp;
//	            for (ASTexpArray::iterator it = choices.begin(); it != choices.end(); ++it) {
//	                (*it)->flatten(temp);
//	                for (unsigned i = 0; i < tups; ++i)
//	                    tempSel[i]->choices.push_back(temp[i]);
//	                temp.clear();
//	            }
//	            choices.clear();
//	            
//	            for (unsigned i = 0; i < tups; ++i)
//	                dest.push_back(tempSel[i]);
//	            tempSel.clear();
	        }
	        return tups;
		}

		@Override
		public ASTExpression simplify() {
	        // NB: ASTselect::flatten() depends on ASTselect::simplify() never
	        // deleting the first select() in a flattened list.
	        if (selector == null) {
	            int s = getIndex(null);
	            ASTExpression ret = choices.get(s).simplify();
	            choices.set(s, null);
	            return ret;
	        }
	        return this;
		}
		
		public int getIndex(RTI rti) {
	        if (selector != null) {
	            double[] select = new double[1];
	            selector.evaluate(select, 1, rti);
	            if (select[0] < 0.0)
	                indexCache = 0;
	            else if (select[0] >= choices.size())
	                indexCache = choices.size() - 1;
	            else
	                indexCache = (int)Math.rint(select[0]);
	            
	            return indexCache;
//	        } else if (weakPointer) {
//	            return weakPointer.indexCache;
	        } else {
	            return indexCache;
	        }
		}

		@Override
		public int evaluate(double[] result, int length, RTI rti) {
	        if (type != ExpType.NumericType) {
	            throw new RuntimeException("Evaluation of a non-shape select() in a numeric context");
	        }
	        
	        if (result == null)
	            return tupleSize;
	        
	        return choices.get(getIndex(rti)).evaluate(result, length, rti);
		}

		@Override
		public void evaluate(Modification modification, String s, double[] width, boolean justCheck, int[] seedIndex, RTI rti) {
	        if (type != ExpType.ModificationType) {
	            throw new RuntimeException("Evaluation of a non-shape select() in a adjustement context");
	        }
	        
	        choices.get(getIndex(rti)).evaluate(modification, s, width, justCheck, seedIndex, rti);
		}
	}