// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g 2012-08-22 00:03:31

	package net.sf.jame.contextfree.parser; 


import java.util.HashMap;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

public class CFDGParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INCLUDE", "QSTRING", "FILENAME", "STARTSHAPE", "STRING", "BACKGROUND", "TILE", "SIZE", "SHAPE", "RULE", "RATIONAL", "PATH", "PATHOP", "FINALLY", "ELSE", "LOOP", "BECOMES", "IF", "TRANSFORM", "SWITCH", "CASE", "TIME", "TIMESCALE", "X", "Y", "Z", "ROTATE", "SKEW", "FLIP", "HUE", "SATURATION", "BRIGHTNESS", "ALPHA", "TARGETHUE", "TARGETSATURATION", "TARGETBRIGHTNESS", "TARGETALPHA", "X1", "X2", "Y1", "Y2", "RX", "RY", "WIDTH", "PARAM", "PLUSMINUS", "RANGE", "NOT", "LT", "GT", "LE", "GE", "AND", "OR", "XOR", "EQ", "NEQ", "RATIONAL2", "COMMENT", "WHITESPACE", "'{'", "'}'", "','", "'('", "')'", "'*'", "':'", "'['", "']'", "'~'", "'|'", "'-'", "'+'", "'/'", "'^'"
    };
    public static final int EOF=-1;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int INCLUDE=4;
    public static final int QSTRING=5;
    public static final int FILENAME=6;
    public static final int STARTSHAPE=7;
    public static final int STRING=8;
    public static final int BACKGROUND=9;
    public static final int TILE=10;
    public static final int SIZE=11;
    public static final int SHAPE=12;
    public static final int RULE=13;
    public static final int RATIONAL=14;
    public static final int PATH=15;
    public static final int PATHOP=16;
    public static final int FINALLY=17;
    public static final int ELSE=18;
    public static final int LOOP=19;
    public static final int BECOMES=20;
    public static final int IF=21;
    public static final int TRANSFORM=22;
    public static final int SWITCH=23;
    public static final int CASE=24;
    public static final int TIME=25;
    public static final int TIMESCALE=26;
    public static final int X=27;
    public static final int Y=28;
    public static final int Z=29;
    public static final int ROTATE=30;
    public static final int SKEW=31;
    public static final int FLIP=32;
    public static final int HUE=33;
    public static final int SATURATION=34;
    public static final int BRIGHTNESS=35;
    public static final int ALPHA=36;
    public static final int TARGETHUE=37;
    public static final int TARGETSATURATION=38;
    public static final int TARGETBRIGHTNESS=39;
    public static final int TARGETALPHA=40;
    public static final int X1=41;
    public static final int X2=42;
    public static final int Y1=43;
    public static final int Y2=44;
    public static final int RX=45;
    public static final int RY=46;
    public static final int WIDTH=47;
    public static final int PARAM=48;
    public static final int PLUSMINUS=49;
    public static final int RANGE=50;
    public static final int NOT=51;
    public static final int LT=52;
    public static final int GT=53;
    public static final int LE=54;
    public static final int GE=55;
    public static final int AND=56;
    public static final int OR=57;
    public static final int XOR=58;
    public static final int EQ=59;
    public static final int NEQ=60;
    public static final int RATIONAL2=61;
    public static final int COMMENT=62;
    public static final int WHITESPACE=63;

    // delegates
    // delegators


        public CFDGParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CFDGParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[168+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return CFDGParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g"; }


    	Driver driver = new Driver();


    public static class cfdg_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "cfdg"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:22:1: cfdg : (r= statement )* EOF ;
    public final CFDGParser.cfdg_return cfdg() throws RecognitionException {
        CFDGParser.cfdg_return retval = new CFDGParser.cfdg_return();
        retval.start = input.LT(1);
        int cfdg_StartIndex = input.index();
        Object root_0 = null;

        Token EOF1=null;
        CFDGParser.statement_return r = null;


        Object EOF1_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:23:9: ( (r= statement )* EOF )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:24:9: (r= statement )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:24:10: (r= statement )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==INCLUDE||(LA1_0>=STARTSHAPE && LA1_0<=RULE)||LA1_0==PATH) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:0:0: r= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_cfdg77);
            	    r=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            if ( state.backtracking==0 ) {

                      if ((r!=null?r.result:null) != null) {
                        driver.pushRep((r!=null?r.result:null), true);
                      }
                      
            }
            EOF1=(Token)match(input,EOF,FOLLOW_EOF_in_cfdg82); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            EOF1_tree = (Object)adaptor.create(EOF1);
            adaptor.addChild(root_0, EOF1_tree);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, cfdg_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "cfdg"

    public static class statement_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:31:1: statement returns [ASTReplacement result] : ( initialization | background | inclusion | tile | size | rule | path | r= shape | shape_singleton | shape_element | global_definition );
    public final CFDGParser.statement_return statement() throws RecognitionException {
        CFDGParser.statement_return retval = new CFDGParser.statement_return();
        retval.start = input.LT(1);
        int statement_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.shape_return r = null;

        CFDGParser.initialization_return initialization2 = null;

        CFDGParser.background_return background3 = null;

        CFDGParser.inclusion_return inclusion4 = null;

        CFDGParser.tile_return tile5 = null;

        CFDGParser.size_return size6 = null;

        CFDGParser.rule_return rule7 = null;

        CFDGParser.path_return path8 = null;

        CFDGParser.shape_singleton_return shape_singleton9 = null;

        CFDGParser.shape_element_return shape_element10 = null;

        CFDGParser.global_definition_return global_definition11 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:32:9: ( initialization | background | inclusion | tile | size | rule | path | r= shape | shape_singleton | shape_element | global_definition )
            int alt2=11;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:33:9: initialization
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_initialization_in_statement128);
                    initialization2=initialization();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, initialization2.getTree());

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:34:11: background
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_background_in_statement140);
                    background3=background();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, background3.getTree());

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:35:11: inclusion
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_inclusion_in_statement154);
                    inclusion4=inclusion();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, inclusion4.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = null;
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:38:11: tile
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_tile_in_statement168);
                    tile5=tile();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, tile5.getTree());

                    }
                    break;
                case 5 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:39:11: size
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_size_in_statement180);
                    size6=size();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, size6.getTree());

                    }
                    break;
                case 6 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:40:11: rule
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_rule_in_statement192);
                    rule7=rule();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rule7.getTree());

                    }
                    break;
                case 7 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:41:11: path
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_path_in_statement204);
                    path8=path();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, path8.getTree());

                    }
                    break;
                case 8 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:42:11: r= shape
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_shape_in_statement218);
                    r=shape();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (r!=null?r.result:null);
                              
                    }

                    }
                    break;
                case 9 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:45:11: shape_singleton
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_shape_singleton_in_statement232);
                    shape_singleton9=shape_singleton();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, shape_singleton9.getTree());

                    }
                    break;
                case 10 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:46:11: shape_element
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_shape_element_in_statement244);
                    shape_element10=shape_element();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, shape_element10.getTree());

                    }
                    break;
                case 11 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:47:11: global_definition
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_global_definition_in_statement256);
                    global_definition11=global_definition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, global_definition11.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = null;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, statement_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class inclusion_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "inclusion"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:52:1: inclusion : ( INCLUDE f= QSTRING | INCLUDE f= FILENAME );
    public final CFDGParser.inclusion_return inclusion() throws RecognitionException {
        CFDGParser.inclusion_return retval = new CFDGParser.inclusion_return();
        retval.start = input.LT(1);
        int inclusion_StartIndex = input.index();
        Object root_0 = null;

        Token f=null;
        Token INCLUDE12=null;
        Token INCLUDE13=null;

        Object f_tree=null;
        Object INCLUDE12_tree=null;
        Object INCLUDE13_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:53:9: ( INCLUDE f= QSTRING | INCLUDE f= FILENAME )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==INCLUDE) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==QSTRING) ) {
                    alt3=1;
                }
                else if ( (LA3_1==FILENAME) ) {
                    alt3=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:54:9: INCLUDE f= QSTRING
                    {
                    root_0 = (Object)adaptor.nil();

                    INCLUDE12=(Token)match(input,INCLUDE,FOLLOW_INCLUDE_in_inclusion301); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INCLUDE12_tree = (Object)adaptor.create(INCLUDE12);
                    adaptor.addChild(root_0, INCLUDE12_tree);
                    }
                    f=(Token)match(input,QSTRING,FOLLOW_QSTRING_in_inclusion305); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = (Object)adaptor.create(f);
                    adaptor.addChild(root_0, f_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.setShape(null);
                              	driver.includeFile(f.getText());
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:59:9: INCLUDE f= FILENAME
                    {
                    root_0 = (Object)adaptor.nil();

                    INCLUDE13=(Token)match(input,INCLUDE,FOLLOW_INCLUDE_in_inclusion327); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INCLUDE13_tree = (Object)adaptor.create(INCLUDE13);
                    adaptor.addChild(root_0, INCLUDE13_tree);
                    }
                    f=(Token)match(input,FILENAME,FOLLOW_FILENAME_in_inclusion331); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = (Object)adaptor.create(f);
                    adaptor.addChild(root_0, f_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.setShape(null);
                              	driver.includeFile(f.getText());
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, inclusion_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "inclusion"

    public static class initialization_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "initialization"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:65:1: initialization : ( STARTSHAPE s= STRING p= parameter_spec m= modification | STARTSHAPE s= STRING p= parameter_spec );
    public final CFDGParser.initialization_return initialization() throws RecognitionException {
        CFDGParser.initialization_return retval = new CFDGParser.initialization_return();
        retval.start = input.LT(1);
        int initialization_StartIndex = input.index();
        Object root_0 = null;

        Token s=null;
        Token STARTSHAPE14=null;
        Token STARTSHAPE15=null;
        CFDGParser.parameter_spec_return p = null;

        CFDGParser.modification_return m = null;


        Object s_tree=null;
        Object STARTSHAPE14_tree=null;
        Object STARTSHAPE15_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:66:9: ( STARTSHAPE s= STRING p= parameter_spec m= modification | STARTSHAPE s= STRING p= parameter_spec )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==STARTSHAPE) ) {
                int LA4_1 = input.LA(2);

                if ( (synpred13_CFDG()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:67:9: STARTSHAPE s= STRING p= parameter_spec m= modification
                    {
                    root_0 = (Object)adaptor.nil();

                    STARTSHAPE14=(Token)match(input,STARTSHAPE,FOLLOW_STARTSHAPE_in_initialization367); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STARTSHAPE14_tree = (Object)adaptor.create(STARTSHAPE14);
                    adaptor.addChild(root_0, STARTSHAPE14_tree);
                    }
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_initialization371); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    s_tree = (Object)adaptor.create(s);
                    adaptor.addChild(root_0, s_tree);
                    }
                    pushFollow(FOLLOW_parameter_spec_in_initialization375);
                    p=parameter_spec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());
                    pushFollow(FOLLOW_modification_in_initialization379);
                    m=modification();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	String name = s.getText();
                              	ASTExpression parameter = (p!=null?p.result:null);
                              	ASTExpression modification = (m!=null?m.result:null);
                              	driver.setShape(null);
                              	ASTRuleSpecifier ruleSpecifier = driver.makeRuleSpecifier(name, parameter);
                              	ASTReplacement start = new ASTReplacement(ruleSpecifier, name, modification);
                              	driver.initialize(start);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:77:9: STARTSHAPE s= STRING p= parameter_spec
                    {
                    root_0 = (Object)adaptor.nil();

                    STARTSHAPE15=(Token)match(input,STARTSHAPE,FOLLOW_STARTSHAPE_in_initialization401); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    STARTSHAPE15_tree = (Object)adaptor.create(STARTSHAPE15);
                    adaptor.addChild(root_0, STARTSHAPE15_tree);
                    }
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_initialization405); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    s_tree = (Object)adaptor.create(s);
                    adaptor.addChild(root_0, s_tree);
                    }
                    pushFollow(FOLLOW_parameter_spec_in_initialization409);
                    p=parameter_spec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());
                    if ( state.backtracking==0 ) {

                              	String name = s.getText();
                              	ASTExpression parameter = (p!=null?p.result:null);
                              	driver.setShape(null);
                              	ASTRuleSpecifier ruleSpecifier = driver.makeRuleSpecifier(name, parameter);
                              	ASTReplacement start = new ASTReplacement(ruleSpecifier, name, new ASTExpression());
                              	driver.initialize(start);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, initialization_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "initialization"

    public static class background_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "background"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:87:1: background : BACKGROUND m= global_modification ;
    public final CFDGParser.background_return background() throws RecognitionException {
        CFDGParser.background_return retval = new CFDGParser.background_return();
        retval.start = input.LT(1);
        int background_StartIndex = input.index();
        Object root_0 = null;

        Token BACKGROUND16=null;
        CFDGParser.global_modification_return m = null;


        Object BACKGROUND16_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:88:9: ( BACKGROUND m= global_modification )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:89:9: BACKGROUND m= global_modification
            {
            root_0 = (Object)adaptor.nil();

            BACKGROUND16=(Token)match(input,BACKGROUND,FOLLOW_BACKGROUND_in_background445); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BACKGROUND16_tree = (Object)adaptor.create(BACKGROUND16);
            adaptor.addChild(root_0, BACKGROUND16_tree);
            }
            pushFollow(FOLLOW_global_modification_in_background449);
            m=global_modification();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
            if ( state.backtracking==0 ) {

                      	driver.setShape(null);
                      	ASTExpression modification = (m!=null?m.result:null);
                      	ASTReplacement background = new ASTReplacement(ASTRuleSpecifier.ZERO, "", modification);
                      	driver.background(background);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, background_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "background"

    public static class tile_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "tile"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:97:1: tile : TILE m= global_modification ;
    public final CFDGParser.tile_return tile() throws RecognitionException {
        CFDGParser.tile_return retval = new CFDGParser.tile_return();
        retval.start = input.LT(1);
        int tile_StartIndex = input.index();
        Object root_0 = null;

        Token TILE17=null;
        CFDGParser.global_modification_return m = null;


        Object TILE17_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:98:9: ( TILE m= global_modification )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:99:9: TILE m= global_modification
            {
            root_0 = (Object)adaptor.nil();

            TILE17=(Token)match(input,TILE,FOLLOW_TILE_in_tile485); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TILE17_tree = (Object)adaptor.create(TILE17);
            adaptor.addChild(root_0, TILE17_tree);
            }
            pushFollow(FOLLOW_global_modification_in_tile489);
            m=global_modification();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
            if ( state.backtracking==0 ) {

                      	driver.setShape(null);
                      	ASTExpression modification = (m!=null?m.result:null);
                      	ASTReplacement tile = new ASTReplacement(ASTRuleSpecifier.ZERO, "", modification);
                      	driver.buildTileTransform(tile);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, tile_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "tile"

    public static class size_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "size"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:107:1: size : SIZE m= global_modification ;
    public final CFDGParser.size_return size() throws RecognitionException {
        CFDGParser.size_return retval = new CFDGParser.size_return();
        retval.start = input.LT(1);
        int size_StartIndex = input.index();
        Object root_0 = null;

        Token SIZE18=null;
        CFDGParser.global_modification_return m = null;


        Object SIZE18_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:108:9: ( SIZE m= global_modification )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:109:9: SIZE m= global_modification
            {
            root_0 = (Object)adaptor.nil();

            SIZE18=(Token)match(input,SIZE,FOLLOW_SIZE_in_size525); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SIZE18_tree = (Object)adaptor.create(SIZE18);
            adaptor.addChild(root_0, SIZE18_tree);
            }
            pushFollow(FOLLOW_global_modification_in_size529);
            m=global_modification();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
            if ( state.backtracking==0 ) {

                      	driver.setShape(null);
                      	ASTExpression modification = (m!=null?m.result:null);
                      	ASTReplacement size = new ASTReplacement(ASTRuleSpecifier.ZERO, "", modification);
                      	driver.buildSizeTransform(size);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, size_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "size"

    public static class shape_return extends ParserRuleReturnScope {
        public ASTShape result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shape"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:117:1: shape returns [ASTShape result] : SHAPE s= STRING parameter_list ;
    public final CFDGParser.shape_return shape() throws RecognitionException {
        CFDGParser.shape_return retval = new CFDGParser.shape_return();
        retval.start = input.LT(1);
        int shape_StartIndex = input.index();
        Object root_0 = null;

        Token s=null;
        Token SHAPE19=null;
        CFDGParser.parameter_list_return parameter_list20 = null;


        Object s_tree=null;
        Object SHAPE19_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:118:9: ( SHAPE s= STRING parameter_list )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:119:9: SHAPE s= STRING parameter_list
            {
            root_0 = (Object)adaptor.nil();

            SHAPE19=(Token)match(input,SHAPE,FOLLOW_SHAPE_in_shape570); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SHAPE19_tree = (Object)adaptor.create(SHAPE19);
            adaptor.addChild(root_0, SHAPE19_tree);
            }
            s=(Token)match(input,STRING,FOLLOW_STRING_in_shape574); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            s_tree = (Object)adaptor.create(s);
            adaptor.addChild(root_0, s_tree);
            }
            pushFollow(FOLLOW_parameter_list_in_shape576);
            parameter_list20=parameter_list();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, parameter_list20.getTree());
            if ( state.backtracking==0 ) {

                      	String name = s.getText(); 
                          	ASTRuleSpecifier ruleSpecifier = new ASTRuleSpecifier(driver.stringToShape(name), name, driver.paramDecls.getParameters(), driver.paramDecls.getParameters());
                          	ASTShape shape = new ASTShape(ruleSpecifier, false);
                          	shape.getRules().getParameters().clear();
                          	shape.getRules().getParameters().addAll(driver.paramDecls.getParameters());
              	driver.setShape(shape);
                          	shape.getRuleSpecifier().setTypeSignature(shape.getRules().getParameters().isEmpty() ? null : shape.getRules().getParameters());
                          	retval.result = shape;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, shape_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shape"

    public static class shape_singleton_return extends ParserRuleReturnScope {
        public ASTShape result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shape_singleton"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:131:1: shape_singleton returns [ASTShape result] : s= shape '{' buncha_elements '}' ;
    public final CFDGParser.shape_singleton_return shape_singleton() throws RecognitionException {
        CFDGParser.shape_singleton_return retval = new CFDGParser.shape_singleton_return();
        retval.start = input.LT(1);
        int shape_singleton_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal21=null;
        Token char_literal23=null;
        CFDGParser.shape_return s = null;

        CFDGParser.buncha_elements_return buncha_elements22 = null;


        Object char_literal21_tree=null;
        Object char_literal23_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:132:9: (s= shape '{' buncha_elements '}' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:133:9: s= shape '{' buncha_elements '}'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_shape_in_shape_singleton618);
            s=shape();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());
            if ( state.backtracking==0 ) {

                      	driver.inPathContainer = false;
                      	ASTRule rule = new ASTRule(-1);
                      	driver.addRule(rule);
                      	driver.pushRepContainer(rule.getRuleBody());
                      
            }
            char_literal21=(Token)match(input,64,FOLLOW_64_in_shape_singleton622); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal21_tree = (Object)adaptor.create(char_literal21);
            adaptor.addChild(root_0, char_literal21_tree);
            }
            pushFollow(FOLLOW_buncha_elements_in_shape_singleton625);
            buncha_elements22=buncha_elements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_elements22.getTree());
            char_literal23=(Token)match(input,65,FOLLOW_65_in_shape_singleton627); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal23_tree = (Object)adaptor.create(char_literal23);
            adaptor.addChild(root_0, char_literal23_tree);
            }
            if ( state.backtracking==0 ) {

                      	driver.inPathContainer = false;
                      	ASTRule rule = new ASTRule(-1);
                      	driver.popRepContainer(rule);
                      	if (rule.getRepType() == RepElemListEnum.empty) {//TODO da cambiare
                      		rule.setPath(true);
                      		driver.retroPath(rule);
                      	}
              	ASTShape shape = (s!=null?s.result:null);
                      	shape.getRules().getBody().add(0, rule);
                      	retval.result = shape;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, shape_singleton_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shape_singleton"

    public static class rule_header_return extends ParserRuleReturnScope {
        public ASTRule result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule_header"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:152:1: rule_header returns [ASTRule result] : ( RULE s= STRING | RULE s= STRING w= RATIONAL );
    public final CFDGParser.rule_header_return rule_header() throws RecognitionException {
        CFDGParser.rule_header_return retval = new CFDGParser.rule_header_return();
        retval.start = input.LT(1);
        int rule_header_StartIndex = input.index();
        Object root_0 = null;

        Token s=null;
        Token w=null;
        Token RULE24=null;
        Token RULE25=null;

        Object s_tree=null;
        Object w_tree=null;
        Object RULE24_tree=null;
        Object RULE25_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:153:9: ( RULE s= STRING | RULE s= STRING w= RATIONAL )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==STRING) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==RATIONAL) ) {
                        alt5=2;
                    }
                    else if ( (LA5_2==64) ) {
                        alt5=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:154:9: RULE s= STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    RULE24=(Token)match(input,RULE,FOLLOW_RULE_in_rule_header668); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE24_tree = (Object)adaptor.create(RULE24);
                    adaptor.addChild(root_0, RULE24_tree);
                    }
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_rule_header672); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    s_tree = (Object)adaptor.create(s);
                    adaptor.addChild(root_0, s_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	String name = s.getText();
                              	driver.setShape(null);
                              	ASTRule rule = new ASTRule(driver.stringToShape(name));
                              	driver.addRule(rule);
                              	driver.pushRepContainer(rule.getRuleBody());
                              	retval.result = rule;
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:163:9: RULE s= STRING w= RATIONAL
                    {
                    root_0 = (Object)adaptor.nil();

                    RULE25=(Token)match(input,RULE,FOLLOW_RULE_in_rule_header694); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE25_tree = (Object)adaptor.create(RULE25);
                    adaptor.addChild(root_0, RULE25_tree);
                    }
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_rule_header698); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    s_tree = (Object)adaptor.create(s);
                    adaptor.addChild(root_0, s_tree);
                    }
                    w=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_rule_header702); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    w_tree = (Object)adaptor.create(w);
                    adaptor.addChild(root_0, w_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	String name = s.getText();
                              	String weight = w.getText();
                              	driver.setShape(null);
                              	ASTRule rule = new ASTRule(driver.stringToShape(name), Float.parseFloat(weight), weight.indexOf("\u0025") != -1);
                              	driver.addRule(rule);
                              	driver.pushRepContainer(rule.getRuleBody());
                              	retval.result = rule;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, rule_header_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "rule_header"

    public static class rule_return extends ParserRuleReturnScope {
        public ASTRule result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "rule"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:174:1: rule returns [ASTRule result] : h= rule_header '{' buncha_replacements_v2 '}' ;
    public final CFDGParser.rule_return rule() throws RecognitionException {
        CFDGParser.rule_return retval = new CFDGParser.rule_return();
        retval.start = input.LT(1);
        int rule_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal26=null;
        Token char_literal28=null;
        CFDGParser.rule_header_return h = null;

        CFDGParser.buncha_replacements_v2_return buncha_replacements_v227 = null;


        Object char_literal26_tree=null;
        Object char_literal28_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:175:9: (h= rule_header '{' buncha_replacements_v2 '}' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:176:9: h= rule_header '{' buncha_replacements_v2 '}'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_rule_header_in_rule744);
            h=rule_header();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, h.getTree());
            char_literal26=(Token)match(input,64,FOLLOW_64_in_rule746); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal26_tree = (Object)adaptor.create(char_literal26);
            adaptor.addChild(root_0, char_literal26_tree);
            }
            pushFollow(FOLLOW_buncha_replacements_v2_in_rule748);
            buncha_replacements_v227=buncha_replacements_v2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_replacements_v227.getTree());
            char_literal28=(Token)match(input,65,FOLLOW_65_in_rule750); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal28_tree = (Object)adaptor.create(char_literal28);
            adaptor.addChild(root_0, char_literal28_tree);
            }
            if ( state.backtracking==0 ) {

                      	driver.popRepContainer((h!=null?h.result:null));
                      	retval.result = (h!=null?h.result:null);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, rule_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "rule"

    public static class shape_element_header_return extends ParserRuleReturnScope {
        public ASTRule result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shape_element_header"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:182:1: shape_element_header returns [ASTRule result] : ( RULE | RULE w= RATIONAL | PATH );
    public final CFDGParser.shape_element_header_return shape_element_header() throws RecognitionException {
        CFDGParser.shape_element_header_return retval = new CFDGParser.shape_element_header_return();
        retval.start = input.LT(1);
        int shape_element_header_StartIndex = input.index();
        Object root_0 = null;

        Token w=null;
        Token RULE29=null;
        Token RULE30=null;
        Token PATH31=null;

        Object w_tree=null;
        Object RULE29_tree=null;
        Object RULE30_tree=null;
        Object PATH31_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:183:9: ( RULE | RULE w= RATIONAL | PATH )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==RATIONAL) ) {
                    alt6=2;
                }
                else if ( (LA6_1==64) ) {
                    alt6=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA6_0==PATH) ) {
                alt6=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:184:9: RULE
                    {
                    root_0 = (Object)adaptor.nil();

                    RULE29=(Token)match(input,RULE,FOLLOW_RULE_in_shape_element_header790); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE29_tree = (Object)adaptor.create(RULE29);
                    adaptor.addChild(root_0, RULE29_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.inPathContainer = false;
                              	ASTRule rule = new ASTRule(-1);
                              	driver.addRule(rule);
                              	driver.pushRepContainer(rule.getRuleBody());
                              	retval.result = rule;
                             
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:192:9: RULE w= RATIONAL
                    {
                    root_0 = (Object)adaptor.nil();

                    RULE30=(Token)match(input,RULE,FOLLOW_RULE_in_shape_element_header811); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RULE30_tree = (Object)adaptor.create(RULE30);
                    adaptor.addChild(root_0, RULE30_tree);
                    }
                    w=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_shape_element_header815); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    w_tree = (Object)adaptor.create(w);
                    adaptor.addChild(root_0, w_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	String weight = w.getText();
                              	driver.inPathContainer = false;
                              	ASTRule rule = new ASTRule(-1, Float.parseFloat(weight), weight.indexOf("\u0025") != -1);
                              	driver.addRule(rule);
                              	driver.pushRepContainer(rule.getRuleBody());
                              	retval.result = rule;
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:201:9: PATH
                    {
                    root_0 = (Object)adaptor.nil();

                    PATH31=(Token)match(input,PATH,FOLLOW_PATH_in_shape_element_header837); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PATH31_tree = (Object)adaptor.create(PATH31);
                    adaptor.addChild(root_0, PATH31_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.inPathContainer = true;
                              	ASTRule rule = new ASTRule(-1);
                              	rule.setPath(true);
                              	driver.addRule(rule);
                              	driver.pushRepContainer(rule.getRuleBody());
                              	retval.result = rule;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, shape_element_header_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shape_element_header"

    public static class shape_element_return extends ParserRuleReturnScope {
        public ASTRule result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "shape_element"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:211:1: shape_element returns [ASTRule result] : h= shape_element_header '{' buncha_elements '}' ;
    public final CFDGParser.shape_element_return shape_element() throws RecognitionException {
        CFDGParser.shape_element_return retval = new CFDGParser.shape_element_return();
        retval.start = input.LT(1);
        int shape_element_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal32=null;
        Token char_literal34=null;
        CFDGParser.shape_element_header_return h = null;

        CFDGParser.buncha_elements_return buncha_elements33 = null;


        Object char_literal32_tree=null;
        Object char_literal34_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:212:9: (h= shape_element_header '{' buncha_elements '}' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:213:9: h= shape_element_header '{' buncha_elements '}'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_shape_element_header_in_shape_element879);
            h=shape_element_header();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, h.getTree());
            char_literal32=(Token)match(input,64,FOLLOW_64_in_shape_element881); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal32_tree = (Object)adaptor.create(char_literal32);
            adaptor.addChild(root_0, char_literal32_tree);
            }
            pushFollow(FOLLOW_buncha_elements_in_shape_element883);
            buncha_elements33=buncha_elements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_elements33.getTree());
            char_literal34=(Token)match(input,65,FOLLOW_65_in_shape_element885); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal34_tree = (Object)adaptor.create(char_literal34);
            adaptor.addChild(root_0, char_literal34_tree);
            }
            if ( state.backtracking==0 ) {

                      	driver.inPathContainer = false;
                      	ASTRule rule = (h!=null?h.result:null);
                      	driver.popRepContainer(rule);
                      	retval.result = rule;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, shape_element_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "shape_element"

    public static class path_header_return extends ParserRuleReturnScope {
        public ASTRule result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "path_header"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:221:1: path_header returns [ASTRule result] : PATH s= STRING ;
    public final CFDGParser.path_header_return path_header() throws RecognitionException {
        CFDGParser.path_header_return retval = new CFDGParser.path_header_return();
        retval.start = input.LT(1);
        int path_header_StartIndex = input.index();
        Object root_0 = null;

        Token s=null;
        Token PATH35=null;

        Object s_tree=null;
        Object PATH35_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:222:9: ( PATH s= STRING )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:223:9: PATH s= STRING
            {
            root_0 = (Object)adaptor.nil();

            PATH35=(Token)match(input,PATH,FOLLOW_PATH_in_path_header925); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PATH35_tree = (Object)adaptor.create(PATH35);
            adaptor.addChild(root_0, PATH35_tree);
            }
            s=(Token)match(input,STRING,FOLLOW_STRING_in_path_header929); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            s_tree = (Object)adaptor.create(s);
            adaptor.addChild(root_0, s_tree);
            }
            if ( state.backtracking==0 ) {

                      	String name = s.getText();
                      	driver.setShape(null);
                      	driver.inPathContainer = true;
                      	ASTRule rule = new ASTRule(driver.stringToShape(name));
                      	rule.setPath(true);
                      	driver.addRule(rule);
                      	driver.pushRepContainer(rule.getRuleBody());
                      	retval.result = rule;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, path_header_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "path_header"

    public static class path_return extends ParserRuleReturnScope {
        public ASTRule result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "path"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:235:1: path returns [ASTRule result] : h= path_header '{' buncha_pathOps_v2 '}' ;
    public final CFDGParser.path_return path() throws RecognitionException {
        CFDGParser.path_return retval = new CFDGParser.path_return();
        retval.start = input.LT(1);
        int path_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal36=null;
        Token char_literal38=null;
        CFDGParser.path_header_return h = null;

        CFDGParser.buncha_pathOps_v2_return buncha_pathOps_v237 = null;


        Object char_literal36_tree=null;
        Object char_literal38_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:236:9: (h= path_header '{' buncha_pathOps_v2 '}' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:237:9: h= path_header '{' buncha_pathOps_v2 '}'
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_path_header_in_path971);
            h=path_header();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, h.getTree());
            char_literal36=(Token)match(input,64,FOLLOW_64_in_path973); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal36_tree = (Object)adaptor.create(char_literal36);
            adaptor.addChild(root_0, char_literal36_tree);
            }
            pushFollow(FOLLOW_buncha_pathOps_v2_in_path975);
            buncha_pathOps_v237=buncha_pathOps_v2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_pathOps_v237.getTree());
            char_literal38=(Token)match(input,65,FOLLOW_65_in_path977); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal38_tree = (Object)adaptor.create(char_literal38);
            adaptor.addChild(root_0, char_literal38_tree);
            }
            if ( state.backtracking==0 ) {

                      	ASTRule rule = (h!=null?h.result:null);
                      	driver.popRepContainer(rule);
                      	retval.result = rule;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, path_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "path"

    public static class parameter_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:244:1: parameter : (t= STRING v= STRING | SHAPE v= STRING );
    public final CFDGParser.parameter_return parameter() throws RecognitionException {
        CFDGParser.parameter_return retval = new CFDGParser.parameter_return();
        retval.start = input.LT(1);
        int parameter_StartIndex = input.index();
        Object root_0 = null;

        Token t=null;
        Token v=null;
        Token SHAPE39=null;

        Object t_tree=null;
        Object v_tree=null;
        Object SHAPE39_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:245:8: (t= STRING v= STRING | SHAPE v= STRING )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==STRING) ) {
                alt7=1;
            }
            else if ( (LA7_0==SHAPE) ) {
                alt7=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:246:8: t= STRING v= STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    t=(Token)match(input,STRING,FOLLOW_STRING_in_parameter1013); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    t_tree = (Object)adaptor.create(t);
                    adaptor.addChild(root_0, t_tree);
                    }
                    v=(Token)match(input,STRING,FOLLOW_STRING_in_parameter1017); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    v_tree = (Object)adaptor.create(v);
                    adaptor.addChild(root_0, v_tree);
                    }
                    if ( state.backtracking==0 ) {

                      	String type = t.getText();
                      	String var = v.getText();
                      	driver.nextParameterDecl(type, var);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:252:9: SHAPE v= STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    SHAPE39=(Token)match(input,SHAPE,FOLLOW_SHAPE_in_parameter1039); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SHAPE39_tree = (Object)adaptor.create(SHAPE39);
                    adaptor.addChild(root_0, SHAPE39_tree);
                    }
                    v=(Token)match(input,STRING,FOLLOW_STRING_in_parameter1043); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    v_tree = (Object)adaptor.create(v);
                    adaptor.addChild(root_0, v_tree);
                    }
                    if ( state.backtracking==0 ) {

                      	String var = v.getText();
                      	driver.nextParameterDecl("shape", var);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, parameter_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parameter"

    public static class buncha_parameters_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_parameters"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:258:1: buncha_parameters : ( parameter ',' buncha_parameters | parameter );
    public final CFDGParser.buncha_parameters_return buncha_parameters() throws RecognitionException {
        CFDGParser.buncha_parameters_return retval = new CFDGParser.buncha_parameters_return();
        retval.start = input.LT(1);
        int buncha_parameters_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal41=null;
        CFDGParser.parameter_return parameter40 = null;

        CFDGParser.buncha_parameters_return buncha_parameters42 = null;

        CFDGParser.parameter_return parameter43 = null;


        Object char_literal41_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:259:9: ( parameter ',' buncha_parameters | parameter )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==STRING) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==STRING) ) {
                    int LA8_3 = input.LA(3);

                    if ( (LA8_3==66) ) {
                        alt8=1;
                    }
                    else if ( (LA8_3==EOF||LA8_3==68) ) {
                        alt8=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA8_0==SHAPE) ) {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==STRING) ) {
                    int LA8_4 = input.LA(3);

                    if ( (LA8_4==66) ) {
                        alt8=1;
                    }
                    else if ( (LA8_4==EOF||LA8_4==68) ) {
                        alt8=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 4, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:260:9: parameter ',' buncha_parameters
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_parameter_in_buncha_parameters1080);
                    parameter40=parameter();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameter40.getTree());
                    char_literal41=(Token)match(input,66,FOLLOW_66_in_buncha_parameters1082); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal41_tree = (Object)adaptor.create(char_literal41);
                    adaptor.addChild(root_0, char_literal41_tree);
                    }
                    pushFollow(FOLLOW_buncha_parameters_in_buncha_parameters1084);
                    buncha_parameters42=buncha_parameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_parameters42.getTree());

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:262:9: parameter
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_parameter_in_buncha_parameters1106);
                    parameter43=parameter();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parameter43.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 17, buncha_parameters_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_parameters"

    public static class parameter_list_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter_list"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:265:1: parameter_list : ( '(' buncha_parameters ')' | );
    public final CFDGParser.parameter_list_return parameter_list() throws RecognitionException {
        CFDGParser.parameter_list_return retval = new CFDGParser.parameter_list_return();
        retval.start = input.LT(1);
        int parameter_list_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal44=null;
        Token char_literal46=null;
        CFDGParser.buncha_parameters_return buncha_parameters45 = null;


        Object char_literal44_tree=null;
        Object char_literal46_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:266:9: ( '(' buncha_parameters ')' | )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==67) ) {
                alt9=1;
            }
            else if ( (LA9_0==EOF||LA9_0==INCLUDE||(LA9_0>=STARTSHAPE && LA9_0<=RULE)||LA9_0==PATH||LA9_0==64) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:267:9: '(' buncha_parameters ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal44=(Token)match(input,67,FOLLOW_67_in_parameter_list1140); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal44_tree = (Object)adaptor.create(char_literal44);
                    adaptor.addChild(root_0, char_literal44_tree);
                    }
                    pushFollow(FOLLOW_buncha_parameters_in_parameter_list1142);
                    buncha_parameters45=buncha_parameters();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_parameters45.getTree());
                    char_literal46=(Token)match(input,68,FOLLOW_68_in_parameter_list1144); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal46_tree = (Object)adaptor.create(char_literal46);
                    adaptor.addChild(root_0, char_literal46_tree);
                    }
                    if ( state.backtracking==0 ) {

                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:270:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 18, parameter_list_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parameter_list"

    public static class parameter_spec_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "parameter_spec"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:272:1: parameter_spec returns [ASTExpression result] : ( '(' e= exp2 ')' | '(' ')' | );
    public final CFDGParser.parameter_spec_return parameter_spec() throws RecognitionException {
        CFDGParser.parameter_spec_return retval = new CFDGParser.parameter_spec_return();
        retval.start = input.LT(1);
        int parameter_spec_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal47=null;
        Token char_literal48=null;
        Token char_literal49=null;
        Token char_literal50=null;
        CFDGParser.exp2_return e = null;


        Object char_literal47_tree=null;
        Object char_literal48_tree=null;
        Object char_literal49_tree=null;
        Object char_literal50_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:273:9: ( '(' e= exp2 ')' | '(' ')' | )
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==67) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==68) ) {
                    alt10=2;
                }
                else if ( (LA10_1==STRING||LA10_1==RATIONAL||LA10_1==NOT||LA10_1==64||LA10_1==67||LA10_1==73||(LA10_1>=75 && LA10_1<=76)) ) {
                    alt10=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA10_0==EOF||LA10_0==INCLUDE||(LA10_0>=STARTSHAPE && LA10_0<=RULE)||LA10_0==PATH||LA10_0==64||LA10_0==73) ) {
                alt10=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:274:9: '(' e= exp2 ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal47=(Token)match(input,67,FOLLOW_67_in_parameter_spec1194); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal47_tree = (Object)adaptor.create(char_literal47);
                    adaptor.addChild(root_0, char_literal47_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_parameter_spec1198);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal48=(Token)match(input,68,FOLLOW_68_in_parameter_spec1200); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal48_tree = (Object)adaptor.create(char_literal48);
                    adaptor.addChild(root_0, char_literal48_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (e!=null?e.result:null);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:277:11: '(' ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal49=(Token)match(input,67,FOLLOW_67_in_parameter_spec1214); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal49_tree = (Object)adaptor.create(char_literal49);
                    adaptor.addChild(root_0, char_literal49_tree);
                    }
                    char_literal50=(Token)match(input,68,FOLLOW_68_in_parameter_spec1216); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal50_tree = (Object)adaptor.create(char_literal50);
                    adaptor.addChild(root_0, char_literal50_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = new ASTExpression(); 
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:280:11: 
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( state.backtracking==0 ) {

                              	retval.result = null;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 19, parameter_spec_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "parameter_spec"

    public static class buncha_elements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_elements"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:285:1: buncha_elements : (r= element buncha_elements | );
    public final CFDGParser.buncha_elements_return buncha_elements() throws RecognitionException {
        CFDGParser.buncha_elements_return retval = new CFDGParser.buncha_elements_return();
        retval.start = input.LT(1);
        int buncha_elements_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.element_return r = null;

        CFDGParser.buncha_elements_return buncha_elements51 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:286:9: (r= element buncha_elements | )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==STRING||(LA11_0>=PATH && LA11_0<=PATHOP)||LA11_0==LOOP||(LA11_0>=IF && LA11_0<=SWITCH)) ) {
                alt11=1;
            }
            else if ( (LA11_0==EOF||LA11_0==65) ) {
                alt11=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:287:9: r= element buncha_elements
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_element_in_buncha_elements1267);
                    r=element();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    pushFollow(FOLLOW_buncha_elements_in_buncha_elements1269);
                    buncha_elements51=buncha_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_elements51.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.pushRep((r!=null?r.result:null), false);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:291:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( state.backtracking==0 ) {

                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 20, buncha_elements_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_elements"

    public static class buncha_pathOps_v2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_pathOps_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:295:1: buncha_pathOps_v2 : (r= pathOp_v2 buncha_pathOps_v2 | );
    public final CFDGParser.buncha_pathOps_v2_return buncha_pathOps_v2() throws RecognitionException {
        CFDGParser.buncha_pathOps_v2_return retval = new CFDGParser.buncha_pathOps_v2_return();
        retval.start = input.LT(1);
        int buncha_pathOps_v2_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.pathOp_v2_return r = null;

        CFDGParser.buncha_pathOps_v2_return buncha_pathOps_v252 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:296:9: (r= pathOp_v2 buncha_pathOps_v2 | )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==STRING||LA12_0==RATIONAL||LA12_0==PATHOP||LA12_0==67) ) {
                alt12=1;
            }
            else if ( (LA12_0==EOF||LA12_0==65) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:297:9: r= pathOp_v2 buncha_pathOps_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pathOp_v2_in_buncha_pathOps_v21329);
                    r=pathOp_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    pushFollow(FOLLOW_buncha_pathOps_v2_in_buncha_pathOps_v21331);
                    buncha_pathOps_v252=buncha_pathOps_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_pathOps_v252.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.pushRep((r!=null?r.result:null), false);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:301:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( state.backtracking==0 ) {

                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 21, buncha_pathOps_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_pathOps_v2"

    public static class pathOp_simple_v2_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pathOp_simple_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:305:1: pathOp_simple_v2 returns [ASTReplacement result] : (o= PATHOP '{' a= buncha_pathop_adjustments '}' | c= STRING m= modification_v2 );
    public final CFDGParser.pathOp_simple_v2_return pathOp_simple_v2() throws RecognitionException {
        CFDGParser.pathOp_simple_v2_return retval = new CFDGParser.pathOp_simple_v2_return();
        retval.start = input.LT(1);
        int pathOp_simple_v2_StartIndex = input.index();
        Object root_0 = null;

        Token o=null;
        Token c=null;
        Token char_literal53=null;
        Token char_literal54=null;
        CFDGParser.buncha_pathop_adjustments_return a = null;

        CFDGParser.modification_v2_return m = null;


        Object o_tree=null;
        Object c_tree=null;
        Object char_literal53_tree=null;
        Object char_literal54_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:306:9: (o= PATHOP '{' a= buncha_pathop_adjustments '}' | c= STRING m= modification_v2 )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==PATHOP) ) {
                alt13=1;
            }
            else if ( (LA13_0==STRING) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:307:9: o= PATHOP '{' a= buncha_pathop_adjustments '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    o=(Token)match(input,PATHOP,FOLLOW_PATHOP_in_pathOp_simple_v21394); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    o_tree = (Object)adaptor.create(o);
                    adaptor.addChild(root_0, o_tree);
                    }
                    char_literal53=(Token)match(input,64,FOLLOW_64_in_pathOp_simple_v21396); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal53_tree = (Object)adaptor.create(char_literal53);
                    adaptor.addChild(root_0, char_literal53_tree);
                    }
                    pushFollow(FOLLOW_buncha_pathop_adjustments_in_pathOp_simple_v21400);
                    a=buncha_pathop_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a.getTree());
                    char_literal54=(Token)match(input,65,FOLLOW_65_in_pathOp_simple_v21402); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal54_tree = (Object)adaptor.create(char_literal54);
                    adaptor.addChild(root_0, char_literal54_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	String operator = o.getText();
                              	ASTExpression modification = (a!=null?a.result:null);
                              	retval.result = new ASTPathOp(operator, modification, false);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:313:9: c= STRING m= modification_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    c=(Token)match(input,STRING,FOLLOW_STRING_in_pathOp_simple_v21426); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    c_tree = (Object)adaptor.create(c);
                    adaptor.addChild(root_0, c_tree);
                    }
                    pushFollow(FOLLOW_modification_v2_in_pathOp_simple_v21430);
                    m=modification_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	String command = c.getText();
                              	ASTExpression modification = (m!=null?m.result:null);
                              	retval.result = new ASTPathCommand(command, modification);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 22, pathOp_simple_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pathOp_simple_v2"

    public static class element_simple_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element_simple"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:320:1: element_simple returns [ASTReplacement result] : (o= PATHOP '(' e= exp2 ')' | o= PATHOP '(' ')' | s= STRING p= parameter_spec m= modification | PATH s= STRING p= parameter_spec m= modification );
    public final CFDGParser.element_simple_return element_simple() throws RecognitionException {
        CFDGParser.element_simple_return retval = new CFDGParser.element_simple_return();
        retval.start = input.LT(1);
        int element_simple_StartIndex = input.index();
        Object root_0 = null;

        Token o=null;
        Token s=null;
        Token char_literal55=null;
        Token char_literal56=null;
        Token char_literal57=null;
        Token char_literal58=null;
        Token PATH59=null;
        CFDGParser.exp2_return e = null;

        CFDGParser.parameter_spec_return p = null;

        CFDGParser.modification_return m = null;


        Object o_tree=null;
        Object s_tree=null;
        Object char_literal55_tree=null;
        Object char_literal56_tree=null;
        Object char_literal57_tree=null;
        Object char_literal58_tree=null;
        Object PATH59_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:321:9: (o= PATHOP '(' e= exp2 ')' | o= PATHOP '(' ')' | s= STRING p= parameter_spec m= modification | PATH s= STRING p= parameter_spec m= modification )
            int alt14=4;
            switch ( input.LA(1) ) {
            case PATHOP:
                {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==67) ) {
                    int LA14_4 = input.LA(3);

                    if ( (LA14_4==68) ) {
                        alt14=2;
                    }
                    else if ( (LA14_4==STRING||LA14_4==RATIONAL||LA14_4==NOT||LA14_4==64||LA14_4==67||LA14_4==73||(LA14_4>=75 && LA14_4<=76)) ) {
                        alt14=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 4, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
                }
                break;
            case STRING:
                {
                alt14=3;
                }
                break;
            case PATH:
                {
                alt14=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:322:9: o= PATHOP '(' e= exp2 ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    o=(Token)match(input,PATHOP,FOLLOW_PATHOP_in_element_simple1472); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    o_tree = (Object)adaptor.create(o);
                    adaptor.addChild(root_0, o_tree);
                    }
                    char_literal55=(Token)match(input,67,FOLLOW_67_in_element_simple1474); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal55_tree = (Object)adaptor.create(char_literal55);
                    adaptor.addChild(root_0, char_literal55_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_element_simple1478);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal56=(Token)match(input,68,FOLLOW_68_in_element_simple1480); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal56_tree = (Object)adaptor.create(char_literal56);
                    adaptor.addChild(root_0, char_literal56_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.inPathContainer = true;
                              	String operator = o.getText();
                              	ASTExpression modification = (e!=null?e.result:null);
                              	retval.result = new ASTPathOp(operator, modification, true);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:329:9: o= PATHOP '(' ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    o=(Token)match(input,PATHOP,FOLLOW_PATHOP_in_element_simple1504); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    o_tree = (Object)adaptor.create(o);
                    adaptor.addChild(root_0, o_tree);
                    }
                    char_literal57=(Token)match(input,67,FOLLOW_67_in_element_simple1506); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal57_tree = (Object)adaptor.create(char_literal57);
                    adaptor.addChild(root_0, char_literal57_tree);
                    }
                    char_literal58=(Token)match(input,68,FOLLOW_68_in_element_simple1508); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal58_tree = (Object)adaptor.create(char_literal58);
                    adaptor.addChild(root_0, char_literal58_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.inPathContainer = true;
                              	String operator = o.getText();
                              	retval.result = new ASTPathOp(operator, null, true);
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:335:9: s= STRING p= parameter_spec m= modification
                    {
                    root_0 = (Object)adaptor.nil();

                    s=(Token)match(input,STRING,FOLLOW_STRING_in_element_simple1532); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    s_tree = (Object)adaptor.create(s);
                    adaptor.addChild(root_0, s_tree);
                    }
                    pushFollow(FOLLOW_parameter_spec_in_element_simple1536);
                    p=parameter_spec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());
                    pushFollow(FOLLOW_modification_in_element_simple1540);
                    m=modification();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	String command = s.getText();
                              	ASTExpression parameter = (p!=null?p.result:null);
                              	ASTExpression modification = (m!=null?m.result:null);
                              	retval.result = driver.makeElement(command, modification, parameter, false);
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:342:9: PATH s= STRING p= parameter_spec m= modification
                    {
                    root_0 = (Object)adaptor.nil();

                    PATH59=(Token)match(input,PATH,FOLLOW_PATH_in_element_simple1562); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PATH59_tree = (Object)adaptor.create(PATH59);
                    adaptor.addChild(root_0, PATH59_tree);
                    }
                    s=(Token)match(input,STRING,FOLLOW_STRING_in_element_simple1566); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    s_tree = (Object)adaptor.create(s);
                    adaptor.addChild(root_0, s_tree);
                    }
                    pushFollow(FOLLOW_parameter_spec_in_element_simple1570);
                    p=parameter_spec();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());
                    pushFollow(FOLLOW_modification_in_element_simple1574);
                    m=modification();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.inPathContainer = true;
                              	String command = s.getText();
                              	ASTExpression parameter = (p!=null?p.result:null);
                              	ASTExpression modification = (m!=null?m.result:null);
                              	retval.result = driver.makeElement(command, modification, parameter, true);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 23, element_simple_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "element_simple"

    public static class one_or_more_elements_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "one_or_more_elements"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:351:1: one_or_more_elements : ( '{' buncha_elements '}' | r= element_simple );
    public final CFDGParser.one_or_more_elements_return one_or_more_elements() throws RecognitionException {
        CFDGParser.one_or_more_elements_return retval = new CFDGParser.one_or_more_elements_return();
        retval.start = input.LT(1);
        int one_or_more_elements_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal60=null;
        Token char_literal62=null;
        CFDGParser.element_simple_return r = null;

        CFDGParser.buncha_elements_return buncha_elements61 = null;


        Object char_literal60_tree=null;
        Object char_literal62_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:352:9: ( '{' buncha_elements '}' | r= element_simple )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==64) ) {
                alt15=1;
            }
            else if ( (LA15_0==STRING||(LA15_0>=PATH && LA15_0<=PATHOP)) ) {
                alt15=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:353:9: '{' buncha_elements '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal60=(Token)match(input,64,FOLLOW_64_in_one_or_more_elements1610); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal60_tree = (Object)adaptor.create(char_literal60);
                    adaptor.addChild(root_0, char_literal60_tree);
                    }
                    pushFollow(FOLLOW_buncha_elements_in_one_or_more_elements1612);
                    buncha_elements61=buncha_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_elements61.getTree());
                    char_literal62=(Token)match(input,65,FOLLOW_65_in_one_or_more_elements1614); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal62_tree = (Object)adaptor.create(char_literal62);
                    adaptor.addChild(root_0, char_literal62_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:355:9: r= element_simple
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_element_simple_in_one_or_more_elements1638);
                    r=element_simple();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.pushRep((r!=null?r.result:null), false);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 24, one_or_more_elements_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "one_or_more_elements"

    public static class one_or_more_pathOp_v2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "one_or_more_pathOp_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:360:1: one_or_more_pathOp_v2 : ( '{' buncha_pathOps_v2 '}' | r= pathOp_simple_v2 );
    public final CFDGParser.one_or_more_pathOp_v2_return one_or_more_pathOp_v2() throws RecognitionException {
        CFDGParser.one_or_more_pathOp_v2_return retval = new CFDGParser.one_or_more_pathOp_v2_return();
        retval.start = input.LT(1);
        int one_or_more_pathOp_v2_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal63=null;
        Token char_literal65=null;
        CFDGParser.pathOp_simple_v2_return r = null;

        CFDGParser.buncha_pathOps_v2_return buncha_pathOps_v264 = null;


        Object char_literal63_tree=null;
        Object char_literal65_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:361:9: ( '{' buncha_pathOps_v2 '}' | r= pathOp_simple_v2 )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==64) ) {
                alt16=1;
            }
            else if ( (LA16_0==STRING||LA16_0==PATHOP) ) {
                alt16=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:362:9: '{' buncha_pathOps_v2 '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal63=(Token)match(input,64,FOLLOW_64_in_one_or_more_pathOp_v21674); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal63_tree = (Object)adaptor.create(char_literal63);
                    adaptor.addChild(root_0, char_literal63_tree);
                    }
                    pushFollow(FOLLOW_buncha_pathOps_v2_in_one_or_more_pathOp_v21676);
                    buncha_pathOps_v264=buncha_pathOps_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_pathOps_v264.getTree());
                    char_literal65=(Token)match(input,65,FOLLOW_65_in_one_or_more_pathOp_v21678); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal65_tree = (Object)adaptor.create(char_literal65);
                    adaptor.addChild(root_0, char_literal65_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:364:9: r= pathOp_simple_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pathOp_simple_v2_in_one_or_more_pathOp_v21702);
                    r=pathOp_simple_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.pushRep((r!=null?r.result:null), false);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 25, one_or_more_pathOp_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "one_or_more_pathOp_v2"

    public static class caseBody_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "caseBody"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:369:1: caseBody : ( caseBody_element caseBody | );
    public final CFDGParser.caseBody_return caseBody() throws RecognitionException {
        CFDGParser.caseBody_return retval = new CFDGParser.caseBody_return();
        retval.start = input.LT(1);
        int caseBody_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.caseBody_element_return caseBody_element66 = null;

        CFDGParser.caseBody_return caseBody67 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:370:9: ( caseBody_element caseBody | )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ELSE||LA17_0==CASE) ) {
                alt17=1;
            }
            else if ( (LA17_0==EOF||LA17_0==65) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:371:9: caseBody_element caseBody
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_caseBody_element_in_caseBody1738);
                    caseBody_element66=caseBody_element();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, caseBody_element66.getTree());
                    pushFollow(FOLLOW_caseBody_in_caseBody1740);
                    caseBody67=caseBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, caseBody67.getTree());

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:373:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 26, caseBody_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "caseBody"

    public static class caseBody_element_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "caseBody_element"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:375:1: caseBody_element : caseHeader one_or_more_elements ;
    public final CFDGParser.caseBody_element_return caseBody_element() throws RecognitionException {
        CFDGParser.caseBody_element_return retval = new CFDGParser.caseBody_element_return();
        retval.start = input.LT(1);
        int caseBody_element_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.caseHeader_return caseHeader68 = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements69 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:376:9: ( caseHeader one_or_more_elements )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:377:9: caseHeader one_or_more_elements
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_caseHeader_in_caseBody_element1785);
            caseHeader68=caseHeader();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, caseHeader68.getTree());
            pushFollow(FOLLOW_one_or_more_elements_in_caseBody_element1787);
            one_or_more_elements69=one_or_more_elements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements69.getTree());

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 27, caseBody_element_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "caseBody_element"

    public static class element_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:380:1: element returns [ASTReplacement result] : (r= element_simple | definition | rl= element_loop | rl= element_loop FINALLY one_or_more_elements | ri= ifHeader one_or_more_elements | ri= ifHeader one_or_more_elements ELSE one_or_more_elements | rt= transHeader one_or_more_elements | rs= switchHeader '{' caseBody '}' );
    public final CFDGParser.element_return element() throws RecognitionException {
        CFDGParser.element_return retval = new CFDGParser.element_return();
        retval.start = input.LT(1);
        int element_StartIndex = input.index();
        Object root_0 = null;

        Token FINALLY71=null;
        Token ELSE75=null;
        Token char_literal78=null;
        Token char_literal80=null;
        CFDGParser.element_simple_return r = null;

        CFDGParser.element_loop_return rl = null;

        CFDGParser.ifHeader_return ri = null;

        CFDGParser.transHeader_return rt = null;

        CFDGParser.switchHeader_return rs = null;

        CFDGParser.definition_return definition70 = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements72 = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements73 = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements74 = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements76 = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements77 = null;

        CFDGParser.caseBody_return caseBody79 = null;


        Object FINALLY71_tree=null;
        Object ELSE75_tree=null;
        Object char_literal78_tree=null;
        Object char_literal80_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:381:9: (r= element_simple | definition | rl= element_loop | rl= element_loop FINALLY one_or_more_elements | ri= ifHeader one_or_more_elements | ri= ifHeader one_or_more_elements ELSE one_or_more_elements | rt= transHeader one_or_more_elements | rs= switchHeader '{' caseBody '}' )
            int alt18=8;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:382:9: r= element_simple
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_element_simple_in_element1827);
                    r=element_simple();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (r!=null?r.result:null); 
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:386:9: definition
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_definition_in_element1849);
                    definition70=definition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, definition70.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = null;
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:390:9: rl= element_loop
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_element_loop_in_element1873);
                    rl=element_loop();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rl.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (rl!=null?rl.result:null); 
                              	driver.popRepContainer((rl!=null?rl.result:null));
                              	if ((rl!=null?rl.result:null).getRepType().getType() == 0) {
                      	        	retval.result = null; 
                              	}
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:398:9: rl= element_loop FINALLY one_or_more_elements
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_element_loop_in_element1897);
                    rl=element_loop();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rl.getTree());
                    FINALLY71=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_element1899); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FINALLY71_tree = (Object)adaptor.create(FINALLY71);
                    adaptor.addChild(root_0, FINALLY71_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.popRepContainer((rl!=null?rl.result:null));
                              	driver.pushRepContainer(((ASTLoop) (rl!=null?rl.result:null)).getFinallyBody());
                              
                    }
                    pushFollow(FOLLOW_one_or_more_elements_in_element1903);
                    one_or_more_elements72=one_or_more_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements72.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = (rl!=null?rl.result:null); 
                              	driver.popRepContainer((rl!=null?rl.result:null));
                              	if ((rl!=null?rl.result:null).getRepType().getType() == 0) {
                      	        	retval.result = null; 
                              	}
                              
                    }

                    }
                    break;
                case 5 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:409:9: ri= ifHeader one_or_more_elements
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifHeader_in_element1927);
                    ri=ifHeader();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ri.getTree());
                    pushFollow(FOLLOW_one_or_more_elements_in_element1929);
                    one_or_more_elements73=one_or_more_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements73.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = (ri!=null?ri.result:null); 
                              	driver.popRepContainer((ri!=null?ri.result:null));
                              	if ((ri!=null?ri.result:null).getRepType().getType() == 0) {
                      	        	retval.result = null; 
                              	}
                              
                    }

                    }
                    break;
                case 6 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:417:9: ri= ifHeader one_or_more_elements ELSE one_or_more_elements
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ifHeader_in_element1953);
                    ri=ifHeader();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ri.getTree());
                    pushFollow(FOLLOW_one_or_more_elements_in_element1955);
                    one_or_more_elements74=one_or_more_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements74.getTree());
                    ELSE75=(Token)match(input,ELSE,FOLLOW_ELSE_in_element1957); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ELSE75_tree = (Object)adaptor.create(ELSE75);
                    adaptor.addChild(root_0, ELSE75_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	driver.popRepContainer((ri!=null?ri.result:null));
                              	driver.pushRepContainer(((ASTIf)(ri!=null?ri.result:null)).getElseBody());
                              
                    }
                    pushFollow(FOLLOW_one_or_more_elements_in_element1961);
                    one_or_more_elements76=one_or_more_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements76.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = (ri!=null?ri.result:null); 
                              	driver.popRepContainer((ri!=null?ri.result:null));
                              	if ((ri!=null?ri.result:null).getRepType().getType() == 0) {
                      	        	retval.result = null; 
                              	}
                              
                    }

                    }
                    break;
                case 7 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:428:9: rt= transHeader one_or_more_elements
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_transHeader_in_element1985);
                    rt=transHeader();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rt.getTree());
                    pushFollow(FOLLOW_one_or_more_elements_in_element1987);
                    one_or_more_elements77=one_or_more_elements();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements77.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = (rt!=null?rt.result:null); 
                              	driver.popRepContainer((rt!=null?rt.result:null));
                              	if ((rt!=null?rt.result:null).getRepType().getType() == 0) {
                      	        	retval.result = null; 
                              	}
                              
                    }

                    }
                    break;
                case 8 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:436:9: rs= switchHeader '{' caseBody '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_switchHeader_in_element2011);
                    rs=switchHeader();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rs.getTree());
                    char_literal78=(Token)match(input,64,FOLLOW_64_in_element2013); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal78_tree = (Object)adaptor.create(char_literal78);
                    adaptor.addChild(root_0, char_literal78_tree);
                    }
                    pushFollow(FOLLOW_caseBody_in_element2015);
                    caseBody79=caseBody();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, caseBody79.getTree());
                    char_literal80=(Token)match(input,65,FOLLOW_65_in_element2017); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal80_tree = (Object)adaptor.create(char_literal80);
                    adaptor.addChild(root_0, char_literal80_tree);
                    }
                    if ( state.backtracking==0 ) {

                      	(rs!=null?rs.result:null).unify();
                              	retval.result = (rs!=null?rs.result:null); 
                              	driver.popRepContainer((rs!=null?rs.result:null));
                              	driver.switchStack().pop();
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 28, element_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "element"

    public static class pathOp_v2_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "pathOp_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:444:1: pathOp_v2 returns [ASTReplacement result] : (rp= pathOp_simple_v2 | rl= loopHeader_v2 one_or_more_pathOp_v2 );
    public final CFDGParser.pathOp_v2_return pathOp_v2() throws RecognitionException {
        CFDGParser.pathOp_v2_return retval = new CFDGParser.pathOp_v2_return();
        retval.start = input.LT(1);
        int pathOp_v2_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.pathOp_simple_v2_return rp = null;

        CFDGParser.loopHeader_v2_return rl = null;

        CFDGParser.one_or_more_pathOp_v2_return one_or_more_pathOp_v281 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:445:9: (rp= pathOp_simple_v2 | rl= loopHeader_v2 one_or_more_pathOp_v2 )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==STRING||LA19_0==PATHOP) ) {
                alt19=1;
            }
            else if ( (LA19_0==RATIONAL||LA19_0==67) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:446:9: rp= pathOp_simple_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_pathOp_simple_v2_in_pathOp_v22059);
                    rp=pathOp_simple_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rp.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (rp!=null?rp.result:null);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:450:9: rl= loopHeader_v2 one_or_more_pathOp_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_loopHeader_v2_in_pathOp_v22083);
                    rl=loopHeader_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rl.getTree());
                    pushFollow(FOLLOW_one_or_more_pathOp_v2_in_pathOp_v22085);
                    one_or_more_pathOp_v281=one_or_more_pathOp_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_pathOp_v281.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (rl!=null?rl.result:null);
                      	driver.popRepContainer((rl!=null?rl.result:null));
                      	if ((rl!=null?rl.result:null).getRepType().getType() == 0) {
                      		retval.result = null;			
                      	}
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 29, pathOp_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "pathOp_v2"

    public static class element_loop_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "element_loop"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:459:1: element_loop returns [ASTReplacement result] : r= loopHeader one_or_more_elements ;
    public final CFDGParser.element_loop_return element_loop() throws RecognitionException {
        CFDGParser.element_loop_return retval = new CFDGParser.element_loop_return();
        retval.start = input.LT(1);
        int element_loop_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.loopHeader_return r = null;

        CFDGParser.one_or_more_elements_return one_or_more_elements82 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:460:9: (r= loopHeader one_or_more_elements )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:461:9: r= loopHeader one_or_more_elements
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_loopHeader_in_element_loop2127);
            r=loopHeader();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
            pushFollow(FOLLOW_one_or_more_elements_in_element_loop2129);
            one_or_more_elements82=one_or_more_elements();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_elements82.getTree());
            if ( state.backtracking==0 ) {

                      	retval.result = (r!=null?r.result:null);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 30, element_loop_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "element_loop"

    public static class buncha_replacements_v2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_replacements_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:466:1: buncha_replacements_v2 : (r= replacement_v2 buncha_replacements_v2 | );
    public final CFDGParser.buncha_replacements_v2_return buncha_replacements_v2() throws RecognitionException {
        CFDGParser.buncha_replacements_v2_return retval = new CFDGParser.buncha_replacements_v2_return();
        retval.start = input.LT(1);
        int buncha_replacements_v2_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.replacement_v2_return r = null;

        CFDGParser.buncha_replacements_v2_return buncha_replacements_v283 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:467:9: (r= replacement_v2 buncha_replacements_v2 | )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==STRING||LA20_0==RATIONAL||LA20_0==67) ) {
                alt20=1;
            }
            else if ( (LA20_0==EOF||LA20_0==65) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:468:9: r= replacement_v2 buncha_replacements_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_replacement_v2_in_buncha_replacements_v22168);
                    r=replacement_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    pushFollow(FOLLOW_buncha_replacements_v2_in_buncha_replacements_v22170);
                    buncha_replacements_v283=buncha_replacements_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_replacements_v283.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.pushRep((r!=null?r.result:null), false);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:472:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 31, buncha_replacements_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_replacements_v2"

    public static class one_or_more_replacements_v2_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "one_or_more_replacements_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:474:1: one_or_more_replacements_v2 : ( '{' buncha_replacements_v2 '}' | r= replacement_simple_v2 );
    public final CFDGParser.one_or_more_replacements_v2_return one_or_more_replacements_v2() throws RecognitionException {
        CFDGParser.one_or_more_replacements_v2_return retval = new CFDGParser.one_or_more_replacements_v2_return();
        retval.start = input.LT(1);
        int one_or_more_replacements_v2_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal84=null;
        Token char_literal86=null;
        CFDGParser.replacement_simple_v2_return r = null;

        CFDGParser.buncha_replacements_v2_return buncha_replacements_v285 = null;


        Object char_literal84_tree=null;
        Object char_literal86_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:475:9: ( '{' buncha_replacements_v2 '}' | r= replacement_simple_v2 )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==64) ) {
                alt21=1;
            }
            else if ( (LA21_0==STRING) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:476:9: '{' buncha_replacements_v2 '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal84=(Token)match(input,64,FOLLOW_64_in_one_or_more_replacements_v22216); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal84_tree = (Object)adaptor.create(char_literal84);
                    adaptor.addChild(root_0, char_literal84_tree);
                    }
                    pushFollow(FOLLOW_buncha_replacements_v2_in_one_or_more_replacements_v22218);
                    buncha_replacements_v285=buncha_replacements_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_replacements_v285.getTree());
                    char_literal86=(Token)match(input,65,FOLLOW_65_in_one_or_more_replacements_v22220); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal86_tree = (Object)adaptor.create(char_literal86);
                    adaptor.addChild(root_0, char_literal86_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:479:9: r= replacement_simple_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_replacement_simple_v2_in_one_or_more_replacements_v22244);
                    r=replacement_simple_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    if ( state.backtracking==0 ) {

                              	driver.pushRep((r!=null?r.result:null), false);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 32, one_or_more_replacements_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "one_or_more_replacements_v2"

    public static class replacement_simple_v2_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "replacement_simple_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:484:1: replacement_simple_v2 returns [ASTReplacement result] : s= STRING m= modification_v2 ;
    public final CFDGParser.replacement_simple_v2_return replacement_simple_v2() throws RecognitionException {
        CFDGParser.replacement_simple_v2_return retval = new CFDGParser.replacement_simple_v2_return();
        retval.start = input.LT(1);
        int replacement_simple_v2_StartIndex = input.index();
        Object root_0 = null;

        Token s=null;
        CFDGParser.modification_v2_return m = null;


        Object s_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:485:9: (s= STRING m= modification_v2 )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:486:9: s= STRING m= modification_v2
            {
            root_0 = (Object)adaptor.nil();

            s=(Token)match(input,STRING,FOLLOW_STRING_in_replacement_simple_v22286); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            s_tree = (Object)adaptor.create(s);
            adaptor.addChild(root_0, s_tree);
            }
            pushFollow(FOLLOW_modification_v2_in_replacement_simple_v22290);
            m=modification_v2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
            if ( state.backtracking==0 ) {

                      	String name = s.getText();
                      	ASTExpression modification = (m!=null?m.result:null);
                      	ASTRuleSpecifier ruleSpecifier = driver.makeRuleSpecifier(name, new ASTExpression());
                      	retval.result = new ASTReplacement(ruleSpecifier, ruleSpecifier.getEntropy().toString(), modification);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 33, replacement_simple_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "replacement_simple_v2"

    public static class replacement_v2_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "replacement_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:494:1: replacement_v2 returns [ASTReplacement result] : (r= replacement_simple_v2 | rl= loopHeader_v2 one_or_more_replacements_v2 );
    public final CFDGParser.replacement_v2_return replacement_v2() throws RecognitionException {
        CFDGParser.replacement_v2_return retval = new CFDGParser.replacement_v2_return();
        retval.start = input.LT(1);
        int replacement_v2_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.replacement_simple_v2_return r = null;

        CFDGParser.loopHeader_v2_return rl = null;

        CFDGParser.one_or_more_replacements_v2_return one_or_more_replacements_v287 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:495:9: (r= replacement_simple_v2 | rl= loopHeader_v2 one_or_more_replacements_v2 )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==STRING) ) {
                alt22=1;
            }
            else if ( (LA22_0==RATIONAL||LA22_0==67) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:496:9: r= replacement_simple_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_replacement_simple_v2_in_replacement_v22332);
                    r=replacement_simple_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, r.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (r!=null?r.result:null);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:500:9: rl= loopHeader_v2 one_or_more_replacements_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_loopHeader_v2_in_replacement_v22356);
                    rl=loopHeader_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, rl.getTree());
                    pushFollow(FOLLOW_one_or_more_replacements_v2_in_replacement_v22358);
                    one_or_more_replacements_v287=one_or_more_replacements_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, one_or_more_replacements_v287.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = (rl!=null?rl.result:null);
                      	driver.popRepContainer((rl!=null?rl.result:null));
                      	if ((rl!=null?rl.result:null).getRepType().getType() == 0) {
                      	        	retval.result = null;			
                      	}
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 34, replacement_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "replacement_v2"

    public static class loopHeader_v2_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "loopHeader_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:509:1: loopHeader_v2 returns [ASTReplacement result] : (r= RATIONAL '*' m= modification_v2 | '(' c= exp2 ')' '*' m= modification_v2 );
    public final CFDGParser.loopHeader_v2_return loopHeader_v2() throws RecognitionException {
        CFDGParser.loopHeader_v2_return retval = new CFDGParser.loopHeader_v2_return();
        retval.start = input.LT(1);
        int loopHeader_v2_StartIndex = input.index();
        Object root_0 = null;

        Token r=null;
        Token char_literal88=null;
        Token char_literal89=null;
        Token char_literal90=null;
        Token char_literal91=null;
        CFDGParser.modification_v2_return m = null;

        CFDGParser.exp2_return c = null;


        Object r_tree=null;
        Object char_literal88_tree=null;
        Object char_literal89_tree=null;
        Object char_literal90_tree=null;
        Object char_literal91_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:510:9: (r= RATIONAL '*' m= modification_v2 | '(' c= exp2 ')' '*' m= modification_v2 )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RATIONAL) ) {
                alt23=1;
            }
            else if ( (LA23_0==67) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:511:9: r= RATIONAL '*' m= modification_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    r=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_loopHeader_v22400); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    r_tree = (Object)adaptor.create(r);
                    adaptor.addChild(root_0, r_tree);
                    }
                    char_literal88=(Token)match(input,69,FOLLOW_69_in_loopHeader_v22402); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal88_tree = (Object)adaptor.create(char_literal88);
                    adaptor.addChild(root_0, char_literal88_tree);
                    }
                    pushFollow(FOLLOW_modification_v2_in_loopHeader_v22406);
                    m=modification_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression count = new ASTReal(Float.parseFloat(r.getText()));
                              	ASTExpression modification = (m!=null?m.result:null);
                              	ASTLoop loopHeader = new ASTLoop(count, modification);
                              	driver.pushRepContainer(loopHeader.getLoopBody());
                              	retval.result = loopHeader;
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:519:9: '(' c= exp2 ')' '*' m= modification_v2
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal89=(Token)match(input,67,FOLLOW_67_in_loopHeader_v22428); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal89_tree = (Object)adaptor.create(char_literal89);
                    adaptor.addChild(root_0, char_literal89_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_loopHeader_v22432);
                    c=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, c.getTree());
                    char_literal90=(Token)match(input,68,FOLLOW_68_in_loopHeader_v22434); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal90_tree = (Object)adaptor.create(char_literal90);
                    adaptor.addChild(root_0, char_literal90_tree);
                    }
                    char_literal91=(Token)match(input,69,FOLLOW_69_in_loopHeader_v22436); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal91_tree = (Object)adaptor.create(char_literal91);
                    adaptor.addChild(root_0, char_literal91_tree);
                    }
                    pushFollow(FOLLOW_modification_v2_in_loopHeader_v22440);
                    m=modification_v2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression count = (c!=null?c.result:null);
                              	ASTExpression modification = (m!=null?m.result:null);
                              	ASTLoop loopHeader = new ASTLoop(count, modification);
                              	driver.pushRepContainer(loopHeader.getLoopBody());
                              	retval.result = loopHeader;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 35, loopHeader_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "loopHeader_v2"

    public static class loopHeader_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "loopHeader"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:528:1: loopHeader returns [ASTReplacement result] : ( LOOP v= STRING BECOMES i= exp2 m= modification | LOOP c= exp2 m= modification );
    public final CFDGParser.loopHeader_return loopHeader() throws RecognitionException {
        CFDGParser.loopHeader_return retval = new CFDGParser.loopHeader_return();
        retval.start = input.LT(1);
        int loopHeader_StartIndex = input.index();
        Object root_0 = null;

        Token v=null;
        Token LOOP92=null;
        Token BECOMES93=null;
        Token LOOP94=null;
        CFDGParser.exp2_return i = null;

        CFDGParser.modification_return m = null;

        CFDGParser.exp2_return c = null;


        Object v_tree=null;
        Object LOOP92_tree=null;
        Object BECOMES93_tree=null;
        Object LOOP94_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:529:9: ( LOOP v= STRING BECOMES i= exp2 m= modification | LOOP c= exp2 m= modification )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==LOOP) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==STRING) ) {
                    int LA24_2 = input.LA(3);

                    if ( (LA24_2==BECOMES) ) {
                        alt24=1;
                    }
                    else if ( ((LA24_2>=PLUSMINUS && LA24_2<=RANGE)||(LA24_2>=LT && LA24_2<=NEQ)||LA24_2==64||(LA24_2>=66 && LA24_2<=67)||LA24_2==69||LA24_2==73||(LA24_2>=75 && LA24_2<=78)) ) {
                        alt24=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA24_1==RATIONAL||LA24_1==NOT||LA24_1==64||LA24_1==67||LA24_1==73||(LA24_1>=75 && LA24_1<=76)) ) {
                    alt24=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:530:9: LOOP v= STRING BECOMES i= exp2 m= modification
                    {
                    root_0 = (Object)adaptor.nil();

                    LOOP92=(Token)match(input,LOOP,FOLLOW_LOOP_in_loopHeader2480); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOOP92_tree = (Object)adaptor.create(LOOP92);
                    adaptor.addChild(root_0, LOOP92_tree);
                    }
                    v=(Token)match(input,STRING,FOLLOW_STRING_in_loopHeader2484); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    v_tree = (Object)adaptor.create(v);
                    adaptor.addChild(root_0, v_tree);
                    }
                    BECOMES93=(Token)match(input,BECOMES,FOLLOW_BECOMES_in_loopHeader2486); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    BECOMES93_tree = (Object)adaptor.create(BECOMES93);
                    adaptor.addChild(root_0, BECOMES93_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_loopHeader2490);
                    i=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, i.getTree());
                    pushFollow(FOLLOW_modification_in_loopHeader2494);
                    m=modification();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	String var = v.getText();
                              	ASTExpression index = (i!=null?i.result:null);
                              	ASTExpression modification = (m!=null?m.result:null);
                              	ASTLoop loopHeader = new ASTLoop(driver.stringToShape(var), var, index, modification);
                              	driver.pushRepContainer(loopHeader.getLoopBody());
                              	retval.result = loopHeader;
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:539:9: LOOP c= exp2 m= modification
                    {
                    root_0 = (Object)adaptor.nil();

                    LOOP94=(Token)match(input,LOOP,FOLLOW_LOOP_in_loopHeader2516); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOOP94_tree = (Object)adaptor.create(LOOP94);
                    adaptor.addChild(root_0, LOOP94_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_loopHeader2520);
                    c=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, c.getTree());
                    pushFollow(FOLLOW_modification_in_loopHeader2524);
                    m=modification();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression count = (c!=null?c.result:null);
                              	ASTExpression modification = (m!=null?m.result:null);
                              	ASTLoop loopHeader = new ASTLoop(count, modification);
                              	driver.pushRepContainer(loopHeader.getLoopBody());
                              	retval.result = loopHeader;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 36, loopHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "loopHeader"

    public static class ifHeader_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifHeader"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:548:1: ifHeader returns [ASTReplacement result] : IF '(' e= exp2 ')' ;
    public final CFDGParser.ifHeader_return ifHeader() throws RecognitionException {
        CFDGParser.ifHeader_return retval = new CFDGParser.ifHeader_return();
        retval.start = input.LT(1);
        int ifHeader_StartIndex = input.index();
        Object root_0 = null;

        Token IF95=null;
        Token char_literal96=null;
        Token char_literal97=null;
        CFDGParser.exp2_return e = null;


        Object IF95_tree=null;
        Object char_literal96_tree=null;
        Object char_literal97_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:549:9: ( IF '(' e= exp2 ')' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:550:9: IF '(' e= exp2 ')'
            {
            root_0 = (Object)adaptor.nil();

            IF95=(Token)match(input,IF,FOLLOW_IF_in_ifHeader2564); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF95_tree = (Object)adaptor.create(IF95);
            adaptor.addChild(root_0, IF95_tree);
            }
            char_literal96=(Token)match(input,67,FOLLOW_67_in_ifHeader2566); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal96_tree = (Object)adaptor.create(char_literal96);
            adaptor.addChild(root_0, char_literal96_tree);
            }
            pushFollow(FOLLOW_exp2_in_ifHeader2570);
            e=exp2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            char_literal97=(Token)match(input,68,FOLLOW_68_in_ifHeader2572); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal97_tree = (Object)adaptor.create(char_literal97);
            adaptor.addChild(root_0, char_literal97_tree);
            }
            if ( state.backtracking==0 ) {

                      	ASTExpression exp = (e!=null?e.result:null);
                      	ASTIf ifHeader = new ASTIf(exp);
                      	driver.pushRepContainer(ifHeader.getThenBody());
                      	retval.result = ifHeader;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 37, ifHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ifHeader"

    public static class transHeader_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "transHeader"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:558:1: transHeader returns [ASTReplacement result] : TRANSFORM m= modification ;
    public final CFDGParser.transHeader_return transHeader() throws RecognitionException {
        CFDGParser.transHeader_return retval = new CFDGParser.transHeader_return();
        retval.start = input.LT(1);
        int transHeader_StartIndex = input.index();
        Object root_0 = null;

        Token TRANSFORM98=null;
        CFDGParser.modification_return m = null;


        Object TRANSFORM98_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:559:9: ( TRANSFORM m= modification )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:560:9: TRANSFORM m= modification
            {
            root_0 = (Object)adaptor.nil();

            TRANSFORM98=(Token)match(input,TRANSFORM,FOLLOW_TRANSFORM_in_transHeader2612); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            TRANSFORM98_tree = (Object)adaptor.create(TRANSFORM98);
            adaptor.addChild(root_0, TRANSFORM98_tree);
            }
            pushFollow(FOLLOW_modification_in_transHeader2616);
            m=modification();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
            if ( state.backtracking==0 ) {

                      	ASTExpression modification = (m!=null?m.result:null);
                      	ASTTransform transHeader = new ASTTransform(modification);
                      	driver.pushRepContainer(transHeader.getBody());
                      	retval.result = transHeader;
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 38, transHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "transHeader"

    public static class switchHeader_return extends ParserRuleReturnScope {
        public ASTReplacement result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "switchHeader"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:568:1: switchHeader returns [ASTReplacement result] : SWITCH '(' e= exp2 ')' ;
    public final CFDGParser.switchHeader_return switchHeader() throws RecognitionException {
        CFDGParser.switchHeader_return retval = new CFDGParser.switchHeader_return();
        retval.start = input.LT(1);
        int switchHeader_StartIndex = input.index();
        Object root_0 = null;

        Token SWITCH99=null;
        Token char_literal100=null;
        Token char_literal101=null;
        CFDGParser.exp2_return e = null;


        Object SWITCH99_tree=null;
        Object char_literal100_tree=null;
        Object char_literal101_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:569:9: ( SWITCH '(' e= exp2 ')' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:570:9: SWITCH '(' e= exp2 ')'
            {
            root_0 = (Object)adaptor.nil();

            SWITCH99=(Token)match(input,SWITCH,FOLLOW_SWITCH_in_switchHeader2656); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SWITCH99_tree = (Object)adaptor.create(SWITCH99);
            adaptor.addChild(root_0, SWITCH99_tree);
            }
            char_literal100=(Token)match(input,67,FOLLOW_67_in_switchHeader2658); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal100_tree = (Object)adaptor.create(char_literal100);
            adaptor.addChild(root_0, char_literal100_tree);
            }
            pushFollow(FOLLOW_exp2_in_switchHeader2662);
            e=exp2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            char_literal101=(Token)match(input,68,FOLLOW_68_in_switchHeader2664); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            char_literal101_tree = (Object)adaptor.create(char_literal101);
            adaptor.addChild(root_0, char_literal101_tree);
            }
            if ( state.backtracking==0 ) {

                          /*exp_ptr caseVal($3);
                          switch_ptr switchHeader(new ASTswitch(caseVal, @3));
                          driver.push_repContainer(switchHeader->mElseBody);
                          driver.switchStack.push(switchHeader.get());
                          $$ = switchHeader.release();*/
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 39, switchHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "switchHeader"

    public static class caseHeader_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "caseHeader"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:579:1: caseHeader : ( CASE e= exp2 ':' | ELSE ':' );
    public final CFDGParser.caseHeader_return caseHeader() throws RecognitionException {
        CFDGParser.caseHeader_return retval = new CFDGParser.caseHeader_return();
        retval.start = input.LT(1);
        int caseHeader_StartIndex = input.index();
        Object root_0 = null;

        Token CASE102=null;
        Token char_literal103=null;
        Token ELSE104=null;
        Token char_literal105=null;
        CFDGParser.exp2_return e = null;


        Object CASE102_tree=null;
        Object char_literal103_tree=null;
        Object ELSE104_tree=null;
        Object char_literal105_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:580:9: ( CASE e= exp2 ':' | ELSE ':' )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==CASE) ) {
                alt25=1;
            }
            else if ( (LA25_0==ELSE) ) {
                alt25=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:581:9: CASE e= exp2 ':'
                    {
                    root_0 = (Object)adaptor.nil();

                    CASE102=(Token)match(input,CASE,FOLLOW_CASE_in_caseHeader2701); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    CASE102_tree = (Object)adaptor.create(CASE102);
                    adaptor.addChild(root_0, CASE102_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_caseHeader2705);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal103=(Token)match(input,70,FOLLOW_70_in_caseHeader2707); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal103_tree = (Object)adaptor.create(char_literal103);
                    adaptor.addChild(root_0, char_literal103_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  /*exp_ptr valExp($2);
                                  
                                  double val = 0.0;
                                  try {
                                      if (valExp->evaluate(&val, 1) != 1) {
                                          driver.error(@2, "Case expression is not a single, numeric expression");
                                      } else {
                                          ASTrepContainer* caseBody = new ASTrepContainer();
                                          std::pair<ASTswitch::switchMap::iterator, bool> ret = 
                                              driver.switchStack.top()->mCaseStatements.
                                              insert(std::pair<int, ASTrepContainer*>((int)val, caseBody));
                                          
                                          if (!ret.second) {
                                              driver.error(@2, "Case value already in use");
                                              delete caseBody;
                                          }
                                          driver.pop_repContainer(driver.switchStack.top());
                                          driver.push_repContainer(*caseBody);
                                      }
                                  }
                                  catch (DeferUntilRuntime) {
                                      driver.error(@2, "Case expression is not constant");
                                  }*/
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:607:9: ELSE ':'
                    {
                    root_0 = (Object)adaptor.nil();

                    ELSE104=(Token)match(input,ELSE,FOLLOW_ELSE_in_caseHeader2729); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ELSE104_tree = (Object)adaptor.create(ELSE104);
                    adaptor.addChild(root_0, ELSE104_tree);
                    }
                    char_literal105=(Token)match(input,70,FOLLOW_70_in_caseHeader2731); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal105_tree = (Object)adaptor.create(char_literal105);
                    adaptor.addChild(root_0, char_literal105_tree);
                    }
                    if ( state.backtracking==0 ) {

                                  /*if (!driver.switchStack.top()->mElseBody.body.empty()) {
                                      driver.error(@$, "There can only be one 'else:' clause");
                                  } else {
                                      driver.pop_repContainer(driver.switchStack.top());
                                      driver.push_repContainer(driver.switchStack.top()->mElseBody);
                                  }*/
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 40, caseHeader_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "caseHeader"

    public static class modification_v2_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "modification_v2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:617:1: modification_v2 returns [ASTExpression result] : ( '{' buncha_canonical_adjustments '}' | '[' a= buncha_adjustments ']' );
    public final CFDGParser.modification_v2_return modification_v2() throws RecognitionException {
        CFDGParser.modification_v2_return retval = new CFDGParser.modification_v2_return();
        retval.start = input.LT(1);
        int modification_v2_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal106=null;
        Token char_literal108=null;
        Token char_literal109=null;
        Token char_literal110=null;
        CFDGParser.buncha_adjustments_return a = null;

        CFDGParser.buncha_canonical_adjustments_return buncha_canonical_adjustments107 = null;


        Object char_literal106_tree=null;
        Object char_literal108_tree=null;
        Object char_literal109_tree=null;
        Object char_literal110_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:618:9: ( '{' buncha_canonical_adjustments '}' | '[' a= buncha_adjustments ']' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==64) ) {
                alt26=1;
            }
            else if ( (LA26_0==71) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:619:9: '{' buncha_canonical_adjustments '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal106=(Token)match(input,64,FOLLOW_64_in_modification_v22771); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal106_tree = (Object)adaptor.create(char_literal106);
                    adaptor.addChild(root_0, char_literal106_tree);
                    }
                    pushFollow(FOLLOW_buncha_canonical_adjustments_in_modification_v22773);
                    buncha_canonical_adjustments107=buncha_canonical_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_canonical_adjustments107.getTree());
                    char_literal108=(Token)match(input,65,FOLLOW_65_in_modification_v22775); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal108_tree = (Object)adaptor.create(char_literal108);
                    adaptor.addChild(root_0, char_literal108_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	ASTExpression modification = new ASTModification(ASTOperator.makeCanonical(driver.canonicalMods));
                              	retval.result = driver.checkModification(modification);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:624:9: '[' a= buncha_adjustments ']'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal109=(Token)match(input,71,FOLLOW_71_in_modification_v22797); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal109_tree = (Object)adaptor.create(char_literal109);
                    adaptor.addChild(root_0, char_literal109_tree);
                    }
                    pushFollow(FOLLOW_buncha_adjustments_in_modification_v22801);
                    a=buncha_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a.getTree());
                    char_literal110=(Token)match(input,72,FOLLOW_72_in_modification_v22803); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal110_tree = (Object)adaptor.create(char_literal110);
                    adaptor.addChild(root_0, char_literal110_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	ASTExpression modification = new ASTModification((a!=null?a.result:null));
                              	retval.result = driver.checkModification(modification);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 41, modification_v2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "modification_v2"

    public static class modification_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "modification"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:630:1: modification returns [ASTExpression result] : ( '~' '{' buncha_canonical_adjustments '}' | '{' a= buncha_adjustments '}' );
    public final CFDGParser.modification_return modification() throws RecognitionException {
        CFDGParser.modification_return retval = new CFDGParser.modification_return();
        retval.start = input.LT(1);
        int modification_StartIndex = input.index();
        Object root_0 = null;

        Token char_literal111=null;
        Token char_literal112=null;
        Token char_literal114=null;
        Token char_literal115=null;
        Token char_literal116=null;
        CFDGParser.buncha_adjustments_return a = null;

        CFDGParser.buncha_canonical_adjustments_return buncha_canonical_adjustments113 = null;


        Object char_literal111_tree=null;
        Object char_literal112_tree=null;
        Object char_literal114_tree=null;
        Object char_literal115_tree=null;
        Object char_literal116_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:631:9: ( '~' '{' buncha_canonical_adjustments '}' | '{' a= buncha_adjustments '}' )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==73) ) {
                alt27=1;
            }
            else if ( (LA27_0==64) ) {
                alt27=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:632:9: '~' '{' buncha_canonical_adjustments '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal111=(Token)match(input,73,FOLLOW_73_in_modification2843); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal111_tree = (Object)adaptor.create(char_literal111);
                    adaptor.addChild(root_0, char_literal111_tree);
                    }
                    char_literal112=(Token)match(input,64,FOLLOW_64_in_modification2845); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal112_tree = (Object)adaptor.create(char_literal112);
                    adaptor.addChild(root_0, char_literal112_tree);
                    }
                    pushFollow(FOLLOW_buncha_canonical_adjustments_in_modification2847);
                    buncha_canonical_adjustments113=buncha_canonical_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_canonical_adjustments113.getTree());
                    char_literal114=(Token)match(input,65,FOLLOW_65_in_modification2849); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal114_tree = (Object)adaptor.create(char_literal114);
                    adaptor.addChild(root_0, char_literal114_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	ASTExpression modification = new ASTModification(ASTOperator.makeCanonical(driver.canonicalMods));
                              	retval.result = driver.checkModification(modification);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:637:9: '{' a= buncha_adjustments '}'
                    {
                    root_0 = (Object)adaptor.nil();

                    char_literal115=(Token)match(input,64,FOLLOW_64_in_modification2871); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal115_tree = (Object)adaptor.create(char_literal115);
                    adaptor.addChild(root_0, char_literal115_tree);
                    }
                    pushFollow(FOLLOW_buncha_adjustments_in_modification2875);
                    a=buncha_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a.getTree());
                    char_literal116=(Token)match(input,65,FOLLOW_65_in_modification2877); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal116_tree = (Object)adaptor.create(char_literal116);
                    adaptor.addChild(root_0, char_literal116_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	ASTExpression modification = new ASTModification((a!=null?a.result:null));
                              	retval.result = driver.checkModification(modification);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 42, modification_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "modification"

    public static class global_modification_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_modification"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:643:1: global_modification returns [ASTExpression result] : m= modification_v2 ;
    public final CFDGParser.global_modification_return global_modification() throws RecognitionException {
        CFDGParser.global_modification_return retval = new CFDGParser.global_modification_return();
        retval.start = input.LT(1);
        int global_modification_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.modification_v2_return m = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:644:9: (m= modification_v2 )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:645:9: m= modification_v2
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_modification_v2_in_global_modification2919);
            m=modification_v2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
            if ( state.backtracking==0 ) {

                      	retval.result = (m!=null?m.result:null);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 43, global_modification_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_modification"

    public static class buncha_pathop_adjustments_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_pathop_adjustments"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:650:1: buncha_pathop_adjustments returns [ASTExpression result] : (a1= adjustment a2= buncha_pathop_adjustments | );
    public final CFDGParser.buncha_pathop_adjustments_return buncha_pathop_adjustments() throws RecognitionException {
        CFDGParser.buncha_pathop_adjustments_return retval = new CFDGParser.buncha_pathop_adjustments_return();
        retval.start = input.LT(1);
        int buncha_pathop_adjustments_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.adjustment_return a1 = null;

        CFDGParser.buncha_pathop_adjustments_return a2 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:651:9: (a1= adjustment a2= buncha_pathop_adjustments | )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==SIZE||(LA28_0>=TIME && LA28_0<=PARAM)) ) {
                alt28=1;
            }
            else if ( (LA28_0==EOF||LA28_0==65) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:652:9: a1= adjustment a2= buncha_pathop_adjustments
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_adjustment_in_buncha_pathop_adjustments2965);
                    a1=adjustment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a1.getTree());
                    pushFollow(FOLLOW_buncha_pathop_adjustments_in_buncha_pathop_adjustments2969);
                    a2=buncha_pathop_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTCons((a2!=null?a2.result:null), (a1!=null?a1.result:null));
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:656:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( state.backtracking==0 ) {

                              	retval.result = null;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 44, buncha_pathop_adjustments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_pathop_adjustments"

    public static class buncha_adjustments_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_adjustments"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:661:1: buncha_adjustments returns [ASTExpression result] : (a1= adjustment a2= buncha_adjustments | );
    public final CFDGParser.buncha_adjustments_return buncha_adjustments() throws RecognitionException {
        CFDGParser.buncha_adjustments_return retval = new CFDGParser.buncha_adjustments_return();
        retval.start = input.LT(1);
        int buncha_adjustments_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.adjustment_return a1 = null;

        CFDGParser.buncha_adjustments_return a2 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 45) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:662:9: (a1= adjustment a2= buncha_adjustments | )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==SIZE||(LA29_0>=TIME && LA29_0<=PARAM)) ) {
                alt29=1;
            }
            else if ( (LA29_0==EOF||LA29_0==65||LA29_0==72) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:663:9: a1= adjustment a2= buncha_adjustments
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_adjustment_in_buncha_adjustments3032);
                    a1=adjustment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a1.getTree());
                    pushFollow(FOLLOW_buncha_adjustments_in_buncha_adjustments3036);
                    a2=buncha_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('+', (a1!=null?a1.result:null), (a2!=null?a2.result:null));
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:667:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( state.backtracking==0 ) {

                      	retval.result = null;
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 45, buncha_adjustments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_adjustments"

    public static class buncha_canonical_adjustments_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "buncha_canonical_adjustments"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:672:1: buncha_canonical_adjustments : (a= adjustment buncha_canonical_adjustments | );
    public final CFDGParser.buncha_canonical_adjustments_return buncha_canonical_adjustments() throws RecognitionException {
        CFDGParser.buncha_canonical_adjustments_return retval = new CFDGParser.buncha_canonical_adjustments_return();
        retval.start = input.LT(1);
        int buncha_canonical_adjustments_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.adjustment_return a = null;

        CFDGParser.buncha_canonical_adjustments_return buncha_canonical_adjustments117 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 46) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:673:9: (a= adjustment buncha_canonical_adjustments | )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==SIZE||(LA30_0>=TIME && LA30_0<=PARAM)) ) {
                alt30=1;
            }
            else if ( (LA30_0==EOF||LA30_0==65) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:674:9: a= adjustment buncha_canonical_adjustments
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_adjustment_in_buncha_canonical_adjustments3095);
                    a=adjustment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, a.getTree());
                    pushFollow(FOLLOW_buncha_canonical_adjustments_in_buncha_canonical_adjustments3097);
                    buncha_canonical_adjustments117=buncha_canonical_adjustments();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, buncha_canonical_adjustments117.getTree());
                    if ( state.backtracking==0 ) {

                              	(a!=null?a.result:null).flatten(driver.canonicalMods);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:678:9: 
                    {
                    root_0 = (Object)adaptor.nil();

                    if ( state.backtracking==0 ) {

                      	driver.canonicalMods.clear();
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 46, buncha_canonical_adjustments_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "buncha_canonical_adjustments"

    public static class adjustment_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "adjustment"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:683:1: adjustment returns [ASTExpression result] : (t= ( TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH ) ml= explist | t= ( HUE | SATURATION | BRIGHTNESS | ALPHA ) m= exp '|' | PARAM p= STRING | PARAM p= QSTRING );
    public final CFDGParser.adjustment_return adjustment() throws RecognitionException {
        CFDGParser.adjustment_return retval = new CFDGParser.adjustment_return();
        retval.start = input.LT(1);
        int adjustment_StartIndex = input.index();
        Object root_0 = null;

        Token t=null;
        Token p=null;
        Token char_literal118=null;
        Token PARAM119=null;
        Token PARAM120=null;
        CFDGParser.explist_return ml = null;

        CFDGParser.exp_return m = null;


        Object t_tree=null;
        Object p_tree=null;
        Object char_literal118_tree=null;
        Object PARAM119_tree=null;
        Object PARAM120_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 47) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:684:9: (t= ( TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH ) ml= explist | t= ( HUE | SATURATION | BRIGHTNESS | ALPHA ) m= exp '|' | PARAM p= STRING | PARAM p= QSTRING )
            int alt31=4;
            switch ( input.LA(1) ) {
            case HUE:
            case SATURATION:
            case BRIGHTNESS:
            case ALPHA:
                {
                int LA31_1 = input.LA(2);

                if ( (synpred73_CFDG()) ) {
                    alt31=1;
                }
                else if ( (synpred77_CFDG()) ) {
                    alt31=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }
                }
                break;
            case SIZE:
            case TIME:
            case TIMESCALE:
            case X:
            case Y:
            case Z:
            case ROTATE:
            case SKEW:
            case FLIP:
            case TARGETHUE:
            case TARGETSATURATION:
            case TARGETBRIGHTNESS:
            case TARGETALPHA:
            case X1:
            case X2:
            case Y1:
            case Y2:
            case RX:
            case RY:
            case WIDTH:
                {
                alt31=1;
                }
                break;
            case PARAM:
                {
                int LA31_3 = input.LA(2);

                if ( (synpred78_CFDG()) ) {
                    alt31=3;
                }
                else if ( (true) ) {
                    alt31=4;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }

            switch (alt31) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:685:9: t= ( TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH ) ml= explist
                    {
                    root_0 = (Object)adaptor.nil();

                    t=(Token)input.LT(1);
                    if ( input.LA(1)==SIZE||(input.LA(1)>=TIME && input.LA(1)<=WIDTH) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(t));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_explist_in_adjustment3212);
                    ml=explist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ml.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression modification = (ml!=null?ml.result:null); 
                              	retval.result = driver.makeModTerm(t.getText(), modification);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:690:9: t= ( HUE | SATURATION | BRIGHTNESS | ALPHA ) m= exp '|'
                    {
                    root_0 = (Object)adaptor.nil();

                    t=(Token)input.LT(1);
                    if ( (input.LA(1)>=HUE && input.LA(1)<=ALPHA) ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, (Object)adaptor.create(t));
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    pushFollow(FOLLOW_exp_in_adjustment3248);
                    m=exp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    char_literal118=(Token)match(input,74,FOLLOW_74_in_adjustment3250); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal118_tree = (Object)adaptor.create(char_literal118);
                    adaptor.addChild(root_0, char_literal118_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	ASTExpression modification = (m!=null?m.result:null); 
                              	retval.result = driver.makeModTerm(t.getText(), modification);
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:695:9: PARAM p= STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    PARAM119=(Token)match(input,PARAM,FOLLOW_PARAM_in_adjustment3272); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PARAM119_tree = (Object)adaptor.create(PARAM119);
                    adaptor.addChild(root_0, PARAM119_tree);
                    }
                    p=(Token)match(input,STRING,FOLLOW_STRING_in_adjustment3276); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    p_tree = (Object)adaptor.create(p);
                    adaptor.addChild(root_0, p_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	retval.result = driver.makeModTerm(ModTypeEnum.param, p.getText());
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:699:9: PARAM p= QSTRING
                    {
                    root_0 = (Object)adaptor.nil();

                    PARAM120=(Token)match(input,PARAM,FOLLOW_PARAM_in_adjustment3298); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PARAM120_tree = (Object)adaptor.create(PARAM120);
                    adaptor.addChild(root_0, PARAM120_tree);
                    }
                    p=(Token)match(input,QSTRING,FOLLOW_QSTRING_in_adjustment3302); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    p_tree = (Object)adaptor.create(p);
                    adaptor.addChild(root_0, p_tree);
                    }
                    if ( state.backtracking==0 ) {

                              	retval.result = driver.makeModTerm(ModTypeEnum.param, p.getText());
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 47, adjustment_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "adjustment"

    public static class explist_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "explist"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:704:1: explist returns [ASTExpression result] : (e1= exp e2= explist | e= exp );
    public final CFDGParser.explist_return explist() throws RecognitionException {
        CFDGParser.explist_return retval = new CFDGParser.explist_return();
        retval.start = input.LT(1);
        int explist_StartIndex = input.index();
        Object root_0 = null;

        CFDGParser.exp_return e1 = null;

        CFDGParser.explist_return e2 = null;

        CFDGParser.exp_return e = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 48) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:705:9: (e1= exp e2= explist | e= exp )
            int alt32=2;
            switch ( input.LA(1) ) {
            case RATIONAL:
                {
                int LA32_1 = input.LA(2);

                if ( (synpred79_CFDG()) ) {
                    alt32=1;
                }
                else if ( (true) ) {
                    alt32=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 1, input);

                    throw nvae;
                }
                }
                break;
            case 75:
                {
                int LA32_2 = input.LA(2);

                if ( (synpred79_CFDG()) ) {
                    alt32=1;
                }
                else if ( (true) ) {
                    alt32=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 2, input);

                    throw nvae;
                }
                }
                break;
            case 76:
                {
                int LA32_3 = input.LA(2);

                if ( (synpred79_CFDG()) ) {
                    alt32=1;
                }
                else if ( (true) ) {
                    alt32=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 3, input);

                    throw nvae;
                }
                }
                break;
            case 67:
                {
                int LA32_4 = input.LA(2);

                if ( (synpred79_CFDG()) ) {
                    alt32=1;
                }
                else if ( (true) ) {
                    alt32=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 4, input);

                    throw nvae;
                }
                }
                break;
            case STRING:
                {
                int LA32_5 = input.LA(2);

                if ( (synpred79_CFDG()) ) {
                    alt32=1;
                }
                else if ( (true) ) {
                    alt32=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 32, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:706:9: e1= exp e2= explist
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_exp_in_explist3352);
                    e1=exp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e1.getTree());
                    pushFollow(FOLLOW_explist_in_explist3356);
                    e2=explist();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTCons((e2!=null?e2.result:null), (e1!=null?e1.result:null));
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:710:9: e= exp
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_exp_in_explist3381);
                    e=exp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (e!=null?e.result:null);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 48, explist_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "explist"

    public static class exp_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exp"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:715:1: exp returns [ASTExpression result] : (r= RATIONAL | '-' r= RATIONAL | '+' r= RATIONAL | '(' e= exp2 ')' | f= expfunc | '-' f= expfunc | '+' f= expfunc ) ( PLUSMINUS e2= exp | RANGE e2= exp )? ;
    public final CFDGParser.exp_return exp() throws RecognitionException {
        CFDGParser.exp_return retval = new CFDGParser.exp_return();
        retval.start = input.LT(1);
        int exp_StartIndex = input.index();
        Object root_0 = null;

        Token r=null;
        Token char_literal121=null;
        Token char_literal122=null;
        Token char_literal123=null;
        Token char_literal124=null;
        Token char_literal125=null;
        Token char_literal126=null;
        Token PLUSMINUS127=null;
        Token RANGE128=null;
        CFDGParser.exp2_return e = null;

        CFDGParser.expfunc_return f = null;

        CFDGParser.exp_return e2 = null;


        Object r_tree=null;
        Object char_literal121_tree=null;
        Object char_literal122_tree=null;
        Object char_literal123_tree=null;
        Object char_literal124_tree=null;
        Object char_literal125_tree=null;
        Object char_literal126_tree=null;
        Object PLUSMINUS127_tree=null;
        Object RANGE128_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 49) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:716:9: ( (r= RATIONAL | '-' r= RATIONAL | '+' r= RATIONAL | '(' e= exp2 ')' | f= expfunc | '-' f= expfunc | '+' f= expfunc ) ( PLUSMINUS e2= exp | RANGE e2= exp )? )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:717:9: (r= RATIONAL | '-' r= RATIONAL | '+' r= RATIONAL | '(' e= exp2 ')' | f= expfunc | '-' f= expfunc | '+' f= expfunc ) ( PLUSMINUS e2= exp | RANGE e2= exp )?
            {
            root_0 = (Object)adaptor.nil();

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:717:9: (r= RATIONAL | '-' r= RATIONAL | '+' r= RATIONAL | '(' e= exp2 ')' | f= expfunc | '-' f= expfunc | '+' f= expfunc )
            int alt33=7;
            alt33 = dfa33.predict(input);
            switch (alt33) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:718:9: r= RATIONAL
                    {
                    r=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_exp3433); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    r_tree = (Object)adaptor.create(r);
                    adaptor.addChild(root_0, r_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTReal(Float.parseFloat(r.getText())); 
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:722:9: '-' r= RATIONAL
                    {
                    char_literal121=(Token)match(input,75,FOLLOW_75_in_exp3455); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal121_tree = (Object)adaptor.create(char_literal121);
                    adaptor.addChild(root_0, char_literal121_tree);
                    }
                    r=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_exp3459); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    r_tree = (Object)adaptor.create(r);
                    adaptor.addChild(root_0, r_tree);
                    }
                    if ( state.backtracking==0 ) {

                      	retval.result = new ASTReal(Float.parseFloat(r.getText()), true); 
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:726:9: '+' r= RATIONAL
                    {
                    char_literal122=(Token)match(input,76,FOLLOW_76_in_exp3481); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal122_tree = (Object)adaptor.create(char_literal122);
                    adaptor.addChild(root_0, char_literal122_tree);
                    }
                    r=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_exp3485); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    r_tree = (Object)adaptor.create(r);
                    adaptor.addChild(root_0, r_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTReal(Float.parseFloat(r.getText())); 
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:730:9: '(' e= exp2 ')'
                    {
                    char_literal123=(Token)match(input,67,FOLLOW_67_in_exp3507); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal123_tree = (Object)adaptor.create(char_literal123);
                    adaptor.addChild(root_0, char_literal123_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp3511);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal124=(Token)match(input,68,FOLLOW_68_in_exp3513); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal124_tree = (Object)adaptor.create(char_literal124);
                    adaptor.addChild(root_0, char_literal124_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTParen((e!=null?e.result:null)); 
                              
                    }

                    }
                    break;
                case 5 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:734:9: f= expfunc
                    {
                    pushFollow(FOLLOW_expfunc_in_exp3538);
                    f=expfunc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = (f!=null?f.result:null); 
                              
                    }

                    }
                    break;
                case 6 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:738:9: '-' f= expfunc
                    {
                    char_literal125=(Token)match(input,75,FOLLOW_75_in_exp3560); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal125_tree = (Object)adaptor.create(char_literal125);
                    adaptor.addChild(root_0, char_literal125_tree);
                    }
                    pushFollow(FOLLOW_expfunc_in_exp3564);
                    f=expfunc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTOperator('N', (f!=null?f.result:null)); 
                              
                    }

                    }
                    break;
                case 7 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:742:9: '+' f= expfunc
                    {
                    char_literal126=(Token)match(input,76,FOLLOW_76_in_exp3586); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal126_tree = (Object)adaptor.create(char_literal126);
                    adaptor.addChild(root_0, char_literal126_tree);
                    }
                    pushFollow(FOLLOW_expfunc_in_exp3590);
                    f=expfunc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTOperator('P', (f!=null?f.result:null)); 
                              
                    }

                    }
                    break;

            }

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:746:9: ( PLUSMINUS e2= exp | RANGE e2= exp )?
            int alt34=3;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==PLUSMINUS) ) {
                alt34=1;
            }
            else if ( (LA34_0==RANGE) ) {
                alt34=2;
            }
            switch (alt34) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:747:9: PLUSMINUS e2= exp
                    {
                    PLUSMINUS127=(Token)match(input,PLUSMINUS,FOLLOW_PLUSMINUS_in_exp3622); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUSMINUS127_tree = (Object)adaptor.create(PLUSMINUS127);
                    adaptor.addChild(root_0, PLUSMINUS127_tree);
                    }
                    pushFollow(FOLLOW_exp_in_exp3626);
                    e2=exp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression pair = new ASTCons(retval.result, (e2!=null?e2.result:null));
                              	retval.result = new ASTFunction("rand+/-", pair, driver.seed);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:752:9: RANGE e2= exp
                    {
                    RANGE128=(Token)match(input,RANGE,FOLLOW_RANGE_in_exp3648); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RANGE128_tree = (Object)adaptor.create(RANGE128);
                    adaptor.addChild(root_0, RANGE128_tree);
                    }
                    pushFollow(FOLLOW_exp_in_exp3652);
                    e2=exp();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression pair = new ASTCons(retval.result, (e2!=null?e2.result:null));
                              	retval.result = new ASTFunction("rand", pair, driver.seed);
                              
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 49, exp_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "exp"

    public static class exp2_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exp2"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:759:1: exp2 returns [ASTExpression result] : (r= RATIONAL | f= expfunc | '(' e= exp2 ')' | '-' f= expfunc | '+' f= expfunc | NOT e= exp2 | m= modification ) ( ',' e2= exp2 | '+' e2= exp2 | '-' e2= exp2 | '*' e2= exp2 | '/' e2= exp2 | '^' e2= exp2 | LT e2= exp2 | GT e2= exp2 | LE e2= exp2 | GE e2= exp2 | AND e2= exp2 | OR e2= exp2 | XOR e2= exp2 | EQ e2= exp2 | NEQ e2= exp2 | PLUSMINUS e2= exp2 | RANGE e2= exp2 )? ;
    public final CFDGParser.exp2_return exp2() throws RecognitionException {
        CFDGParser.exp2_return retval = new CFDGParser.exp2_return();
        retval.start = input.LT(1);
        int exp2_StartIndex = input.index();
        Object root_0 = null;

        Token r=null;
        Token char_literal129=null;
        Token char_literal130=null;
        Token char_literal131=null;
        Token char_literal132=null;
        Token NOT133=null;
        Token char_literal134=null;
        Token char_literal135=null;
        Token char_literal136=null;
        Token char_literal137=null;
        Token char_literal138=null;
        Token char_literal139=null;
        Token LT140=null;
        Token GT141=null;
        Token LE142=null;
        Token GE143=null;
        Token AND144=null;
        Token OR145=null;
        Token XOR146=null;
        Token EQ147=null;
        Token NEQ148=null;
        Token PLUSMINUS149=null;
        Token RANGE150=null;
        CFDGParser.expfunc_return f = null;

        CFDGParser.exp2_return e = null;

        CFDGParser.modification_return m = null;

        CFDGParser.exp2_return e2 = null;


        Object r_tree=null;
        Object char_literal129_tree=null;
        Object char_literal130_tree=null;
        Object char_literal131_tree=null;
        Object char_literal132_tree=null;
        Object NOT133_tree=null;
        Object char_literal134_tree=null;
        Object char_literal135_tree=null;
        Object char_literal136_tree=null;
        Object char_literal137_tree=null;
        Object char_literal138_tree=null;
        Object char_literal139_tree=null;
        Object LT140_tree=null;
        Object GT141_tree=null;
        Object LE142_tree=null;
        Object GE143_tree=null;
        Object AND144_tree=null;
        Object OR145_tree=null;
        Object XOR146_tree=null;
        Object EQ147_tree=null;
        Object NEQ148_tree=null;
        Object PLUSMINUS149_tree=null;
        Object RANGE150_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 50) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:760:9: ( (r= RATIONAL | f= expfunc | '(' e= exp2 ')' | '-' f= expfunc | '+' f= expfunc | NOT e= exp2 | m= modification ) ( ',' e2= exp2 | '+' e2= exp2 | '-' e2= exp2 | '*' e2= exp2 | '/' e2= exp2 | '^' e2= exp2 | LT e2= exp2 | GT e2= exp2 | LE e2= exp2 | GE e2= exp2 | AND e2= exp2 | OR e2= exp2 | XOR e2= exp2 | EQ e2= exp2 | NEQ e2= exp2 | PLUSMINUS e2= exp2 | RANGE e2= exp2 )? )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:761:9: (r= RATIONAL | f= expfunc | '(' e= exp2 ')' | '-' f= expfunc | '+' f= expfunc | NOT e= exp2 | m= modification ) ( ',' e2= exp2 | '+' e2= exp2 | '-' e2= exp2 | '*' e2= exp2 | '/' e2= exp2 | '^' e2= exp2 | LT e2= exp2 | GT e2= exp2 | LE e2= exp2 | GE e2= exp2 | AND e2= exp2 | OR e2= exp2 | XOR e2= exp2 | EQ e2= exp2 | NEQ e2= exp2 | PLUSMINUS e2= exp2 | RANGE e2= exp2 )?
            {
            root_0 = (Object)adaptor.nil();

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:761:9: (r= RATIONAL | f= expfunc | '(' e= exp2 ')' | '-' f= expfunc | '+' f= expfunc | NOT e= exp2 | m= modification )
            int alt35=7;
            switch ( input.LA(1) ) {
            case RATIONAL:
                {
                alt35=1;
                }
                break;
            case STRING:
                {
                alt35=2;
                }
                break;
            case 67:
                {
                alt35=3;
                }
                break;
            case 75:
                {
                alt35=4;
                }
                break;
            case 76:
                {
                alt35=5;
                }
                break;
            case NOT:
                {
                alt35=6;
                }
                break;
            case 64:
            case 73:
                {
                alt35=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:762:9: r= RATIONAL
                    {
                    r=(Token)match(input,RATIONAL,FOLLOW_RATIONAL_in_exp23716); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    r_tree = (Object)adaptor.create(r);
                    adaptor.addChild(root_0, r_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = new ASTReal(Float.parseFloat(r.getText())); 
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:766:9: f= expfunc
                    {
                    pushFollow(FOLLOW_expfunc_in_exp23740);
                    f=expfunc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
                    if ( state.backtracking==0 ) {
                       
                              	retval.result = (f!=null?f.result:null);
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:770:9: '(' e= exp2 ')'
                    {
                    char_literal129=(Token)match(input,67,FOLLOW_67_in_exp23762); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal129_tree = (Object)adaptor.create(char_literal129);
                    adaptor.addChild(root_0, char_literal129_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp23766);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal130=(Token)match(input,68,FOLLOW_68_in_exp23768); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal130_tree = (Object)adaptor.create(char_literal130);
                    adaptor.addChild(root_0, char_literal130_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTParen((e!=null?e.result:null)); 
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:774:9: '-' f= expfunc
                    {
                    char_literal131=(Token)match(input,75,FOLLOW_75_in_exp23791); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal131_tree = (Object)adaptor.create(char_literal131);
                    adaptor.addChild(root_0, char_literal131_tree);
                    }
                    pushFollow(FOLLOW_expfunc_in_exp23795);
                    f=expfunc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTOperator('N', (f!=null?f.result:null)); 
                              
                    }

                    }
                    break;
                case 5 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:778:9: '+' f= expfunc
                    {
                    char_literal132=(Token)match(input,76,FOLLOW_76_in_exp23817); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal132_tree = (Object)adaptor.create(char_literal132);
                    adaptor.addChild(root_0, char_literal132_tree);
                    }
                    pushFollow(FOLLOW_expfunc_in_exp23821);
                    f=expfunc();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, f.getTree());
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTOperator('P', (f!=null?f.result:null)); 
                              
                    }

                    }
                    break;
                case 6 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:782:9: NOT e= exp2
                    {
                    NOT133=(Token)match(input,NOT,FOLLOW_NOT_in_exp23843); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT133_tree = (Object)adaptor.create(NOT133);
                    adaptor.addChild(root_0, NOT133_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp23847);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    if ( state.backtracking==0 ) {
                       
                      	retval.result = new ASTOperator('!', (e!=null?e.result:null)); 
                              
                    }

                    }
                    break;
                case 7 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:786:9: m= modification
                    {
                    pushFollow(FOLLOW_modification_in_exp23871);
                    m=modification();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = (m!=null?m.result:null);
                              
                    }

                    }
                    break;

            }

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:790:9: ( ',' e2= exp2 | '+' e2= exp2 | '-' e2= exp2 | '*' e2= exp2 | '/' e2= exp2 | '^' e2= exp2 | LT e2= exp2 | GT e2= exp2 | LE e2= exp2 | GE e2= exp2 | AND e2= exp2 | OR e2= exp2 | XOR e2= exp2 | EQ e2= exp2 | NEQ e2= exp2 | PLUSMINUS e2= exp2 | RANGE e2= exp2 )?
            int alt36=18;
            alt36 = dfa36.predict(input);
            switch (alt36) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:791:9: ',' e2= exp2
                    {
                    char_literal134=(Token)match(input,66,FOLLOW_66_in_exp23903); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal134_tree = (Object)adaptor.create(char_literal134);
                    adaptor.addChild(root_0, char_literal134_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp23907);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTCons(retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:795:9: '+' e2= exp2
                    {
                    char_literal135=(Token)match(input,76,FOLLOW_76_in_exp23929); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal135_tree = (Object)adaptor.create(char_literal135);
                    adaptor.addChild(root_0, char_literal135_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp23933);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('+', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:799:9: '-' e2= exp2
                    {
                    char_literal136=(Token)match(input,75,FOLLOW_75_in_exp23955); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal136_tree = (Object)adaptor.create(char_literal136);
                    adaptor.addChild(root_0, char_literal136_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp23959);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('-', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:803:9: '*' e2= exp2
                    {
                    char_literal137=(Token)match(input,69,FOLLOW_69_in_exp23981); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal137_tree = (Object)adaptor.create(char_literal137);
                    adaptor.addChild(root_0, char_literal137_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp23985);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('*', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 5 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:807:9: '/' e2= exp2
                    {
                    char_literal138=(Token)match(input,77,FOLLOW_77_in_exp24007); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal138_tree = (Object)adaptor.create(char_literal138);
                    adaptor.addChild(root_0, char_literal138_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24011);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('/', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 6 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:811:9: '^' e2= exp2
                    {
                    char_literal139=(Token)match(input,78,FOLLOW_78_in_exp24033); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal139_tree = (Object)adaptor.create(char_literal139);
                    adaptor.addChild(root_0, char_literal139_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24037);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('^', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 7 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:815:9: LT e2= exp2
                    {
                    LT140=(Token)match(input,LT,FOLLOW_LT_in_exp24059); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LT140_tree = (Object)adaptor.create(LT140);
                    adaptor.addChild(root_0, LT140_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24063);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('<', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 8 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:819:9: GT e2= exp2
                    {
                    GT141=(Token)match(input,GT,FOLLOW_GT_in_exp24085); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GT141_tree = (Object)adaptor.create(GT141);
                    adaptor.addChild(root_0, GT141_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24089);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('>', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 9 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:823:9: LE e2= exp2
                    {
                    LE142=(Token)match(input,LE,FOLLOW_LE_in_exp24111); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LE142_tree = (Object)adaptor.create(LE142);
                    adaptor.addChild(root_0, LE142_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24115);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('L', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 10 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:827:9: GE e2= exp2
                    {
                    GE143=(Token)match(input,GE,FOLLOW_GE_in_exp24137); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GE143_tree = (Object)adaptor.create(GE143);
                    adaptor.addChild(root_0, GE143_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24141);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('G', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 11 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:831:9: AND e2= exp2
                    {
                    AND144=(Token)match(input,AND,FOLLOW_AND_in_exp24163); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    AND144_tree = (Object)adaptor.create(AND144);
                    adaptor.addChild(root_0, AND144_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24167);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('&', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 12 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:835:9: OR e2= exp2
                    {
                    OR145=(Token)match(input,OR,FOLLOW_OR_in_exp24189); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    OR145_tree = (Object)adaptor.create(OR145);
                    adaptor.addChild(root_0, OR145_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24193);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('|', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 13 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:839:9: XOR e2= exp2
                    {
                    XOR146=(Token)match(input,XOR,FOLLOW_XOR_in_exp24215); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    XOR146_tree = (Object)adaptor.create(XOR146);
                    adaptor.addChild(root_0, XOR146_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24219);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('X', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 14 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:843:9: EQ e2= exp2
                    {
                    EQ147=(Token)match(input,EQ,FOLLOW_EQ_in_exp24241); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EQ147_tree = (Object)adaptor.create(EQ147);
                    adaptor.addChild(root_0, EQ147_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24245);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('=', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 15 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:847:9: NEQ e2= exp2
                    {
                    NEQ148=(Token)match(input,NEQ,FOLLOW_NEQ_in_exp24267); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEQ148_tree = (Object)adaptor.create(NEQ148);
                    adaptor.addChild(root_0, NEQ148_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24271);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	retval.result = new ASTOperator('n', retval.result, (e2!=null?e2.result:null));
                              
                    }

                    }
                    break;
                case 16 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:851:9: PLUSMINUS e2= exp2
                    {
                    PLUSMINUS149=(Token)match(input,PLUSMINUS,FOLLOW_PLUSMINUS_in_exp24293); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    PLUSMINUS149_tree = (Object)adaptor.create(PLUSMINUS149);
                    adaptor.addChild(root_0, PLUSMINUS149_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24297);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression pair = new ASTCons(retval.result, (e2!=null?e2.result:null));
                              	retval.result = new ASTFunction("rand+/-", pair, driver.seed);
                              
                    }

                    }
                    break;
                case 17 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:856:9: RANGE e2= exp2
                    {
                    RANGE150=(Token)match(input,RANGE,FOLLOW_RANGE_in_exp24319); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RANGE150_tree = (Object)adaptor.create(RANGE150);
                    adaptor.addChild(root_0, RANGE150_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp24323);
                    e2=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());
                    if ( state.backtracking==0 ) {

                              	ASTExpression pair = new ASTCons(retval.result, (e2!=null?e2.result:null));
                              	retval.result = new ASTFunction("rand", pair, driver.seed);
                              
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 50, exp2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "exp2"

    public static class expfunc_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expfunc"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:863:1: expfunc returns [ASTExpression result] : (f= STRING '(' ')' | f= STRING '(' e= exp2 ')' | v= STRING );
    public final CFDGParser.expfunc_return expfunc() throws RecognitionException {
        CFDGParser.expfunc_return retval = new CFDGParser.expfunc_return();
        retval.start = input.LT(1);
        int expfunc_StartIndex = input.index();
        Object root_0 = null;

        Token f=null;
        Token v=null;
        Token char_literal151=null;
        Token char_literal152=null;
        Token char_literal153=null;
        Token char_literal154=null;
        CFDGParser.exp2_return e = null;


        Object f_tree=null;
        Object v_tree=null;
        Object char_literal151_tree=null;
        Object char_literal152_tree=null;
        Object char_literal153_tree=null;
        Object char_literal154_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 51) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:864:9: (f= STRING '(' ')' | f= STRING '(' e= exp2 ')' | v= STRING )
            int alt37=3;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==STRING) ) {
                int LA37_1 = input.LA(2);

                if ( (synpred111_CFDG()) ) {
                    alt37=1;
                }
                else if ( (synpred112_CFDG()) ) {
                    alt37=2;
                }
                else if ( (true) ) {
                    alt37=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 37, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:865:9: f= STRING '(' ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    f=(Token)match(input,STRING,FOLLOW_STRING_in_expfunc4376); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = (Object)adaptor.create(f);
                    adaptor.addChild(root_0, f_tree);
                    }
                    char_literal151=(Token)match(input,67,FOLLOW_67_in_expfunc4378); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal151_tree = (Object)adaptor.create(char_literal151);
                    adaptor.addChild(root_0, char_literal151_tree);
                    }
                    char_literal152=(Token)match(input,68,FOLLOW_68_in_expfunc4380); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal152_tree = (Object)adaptor.create(char_literal152);
                    adaptor.addChild(root_0, char_literal152_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	String function = f.getText();
                              	retval.result = driver.makeFunction(function, new ASTExpression(), true);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:870:9: f= STRING '(' e= exp2 ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    f=(Token)match(input,STRING,FOLLOW_STRING_in_expfunc4405); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = (Object)adaptor.create(f);
                    adaptor.addChild(root_0, f_tree);
                    }
                    char_literal153=(Token)match(input,67,FOLLOW_67_in_expfunc4407); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal153_tree = (Object)adaptor.create(char_literal153);
                    adaptor.addChild(root_0, char_literal153_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_expfunc4411);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal154=(Token)match(input,68,FOLLOW_68_in_expfunc4413); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal154_tree = (Object)adaptor.create(char_literal154);
                    adaptor.addChild(root_0, char_literal154_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	String function = f.getText();
                              	ASTExpression arguments = (e!=null?e.result:null);
                              	retval.result = driver.makeFunction(function, arguments, true);
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:876:9: v= STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    v=(Token)match(input,STRING,FOLLOW_STRING_in_expfunc4437); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    v_tree = (Object)adaptor.create(v);
                    adaptor.addChild(root_0, v_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	String var = v.getText();
                              	retval.result = driver.makeVariable(var);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 51, expfunc_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "expfunc"

    public static class exp2func_return extends ParserRuleReturnScope {
        public ASTExpression result;
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "exp2func"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:882:1: exp2func returns [ASTExpression result] : (f= STRING '(' ')' | f= STRING '(' e= exp2 ')' | v= STRING );
    public final CFDGParser.exp2func_return exp2func() throws RecognitionException {
        CFDGParser.exp2func_return retval = new CFDGParser.exp2func_return();
        retval.start = input.LT(1);
        int exp2func_StartIndex = input.index();
        Object root_0 = null;

        Token f=null;
        Token v=null;
        Token char_literal155=null;
        Token char_literal156=null;
        Token char_literal157=null;
        Token char_literal158=null;
        CFDGParser.exp2_return e = null;


        Object f_tree=null;
        Object v_tree=null;
        Object char_literal155_tree=null;
        Object char_literal156_tree=null;
        Object char_literal157_tree=null;
        Object char_literal158_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 52) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:883:9: (f= STRING '(' ')' | f= STRING '(' e= exp2 ')' | v= STRING )
            int alt38=3;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==STRING) ) {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==67) ) {
                    int LA38_2 = input.LA(3);

                    if ( (LA38_2==68) ) {
                        alt38=1;
                    }
                    else if ( (LA38_2==STRING||LA38_2==RATIONAL||LA38_2==NOT||LA38_2==64||LA38_2==67||LA38_2==73||(LA38_2>=75 && LA38_2<=76)) ) {
                        alt38=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 2, input);

                        throw nvae;
                    }
                }
                else if ( (LA38_1==EOF) ) {
                    alt38=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:884:9: f= STRING '(' ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    f=(Token)match(input,STRING,FOLLOW_STRING_in_exp2func4479); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = (Object)adaptor.create(f);
                    adaptor.addChild(root_0, f_tree);
                    }
                    char_literal155=(Token)match(input,67,FOLLOW_67_in_exp2func4481); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal155_tree = (Object)adaptor.create(char_literal155);
                    adaptor.addChild(root_0, char_literal155_tree);
                    }
                    char_literal156=(Token)match(input,68,FOLLOW_68_in_exp2func4483); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal156_tree = (Object)adaptor.create(char_literal156);
                    adaptor.addChild(root_0, char_literal156_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	String function = f.getText();
                              	retval.result = driver.makeFunction(function, new ASTExpression(), true);
                              
                    }

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:889:9: f= STRING '(' e= exp2 ')'
                    {
                    root_0 = (Object)adaptor.nil();

                    f=(Token)match(input,STRING,FOLLOW_STRING_in_exp2func4508); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    f_tree = (Object)adaptor.create(f);
                    adaptor.addChild(root_0, f_tree);
                    }
                    char_literal157=(Token)match(input,67,FOLLOW_67_in_exp2func4510); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal157_tree = (Object)adaptor.create(char_literal157);
                    adaptor.addChild(root_0, char_literal157_tree);
                    }
                    pushFollow(FOLLOW_exp2_in_exp2func4514);
                    e=exp2();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
                    char_literal158=(Token)match(input,68,FOLLOW_68_in_exp2func4516); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    char_literal158_tree = (Object)adaptor.create(char_literal158);
                    adaptor.addChild(root_0, char_literal158_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	String function = f.getText();
                              	ASTExpression arguments = (e!=null?e.result:null);
                              	retval.result = driver.makeFunction(function, arguments, true);
                              
                    }

                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:895:9: v= STRING
                    {
                    root_0 = (Object)adaptor.nil();

                    v=(Token)match(input,STRING,FOLLOW_STRING_in_exp2func4540); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    v_tree = (Object)adaptor.create(v);
                    adaptor.addChild(root_0, v_tree);
                    }
                    if ( state.backtracking==0 ) {
                       
                              	String var = v.getText();
                              	retval.result = driver.makeVariable(var);
                              
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 52, exp2func_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "exp2func"

    public static class global_definition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "global_definition"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:901:1: global_definition : v= STRING BECOMES e= exp2 ;
    public final CFDGParser.global_definition_return global_definition() throws RecognitionException {
        CFDGParser.global_definition_return retval = new CFDGParser.global_definition_return();
        retval.start = input.LT(1);
        int global_definition_StartIndex = input.index();
        Object root_0 = null;

        Token v=null;
        Token BECOMES159=null;
        CFDGParser.exp2_return e = null;


        Object v_tree=null;
        Object BECOMES159_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 53) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:902:9: (v= STRING BECOMES e= exp2 )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:903:9: v= STRING BECOMES e= exp2
            {
            root_0 = (Object)adaptor.nil();

            v=(Token)match(input,STRING,FOLLOW_STRING_in_global_definition4586); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            v_tree = (Object)adaptor.create(v);
            adaptor.addChild(root_0, v_tree);
            }
            BECOMES159=(Token)match(input,BECOMES,FOLLOW_BECOMES_in_global_definition4588); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BECOMES159_tree = (Object)adaptor.create(BECOMES159);
            adaptor.addChild(root_0, BECOMES159_tree);
            }
            pushFollow(FOLLOW_exp2_in_global_definition4592);
            e=exp2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            if ( state.backtracking==0 ) {
               
                      	driver.setShape(null);
                      	String var = v.getText();
                      	ASTExpression expression = (e!=null?e.result:null);
               	driver.nextParameter(var, expression);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 53, global_definition_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "global_definition"

    public static class definition_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "definition"
    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:911:1: definition : v= STRING BECOMES e= exp2 ;
    public final CFDGParser.definition_return definition() throws RecognitionException {
        CFDGParser.definition_return retval = new CFDGParser.definition_return();
        retval.start = input.LT(1);
        int definition_StartIndex = input.index();
        Object root_0 = null;

        Token v=null;
        Token BECOMES160=null;
        CFDGParser.exp2_return e = null;


        Object v_tree=null;
        Object BECOMES160_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 54) ) { return retval; }
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:912:9: (v= STRING BECOMES e= exp2 )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:913:9: v= STRING BECOMES e= exp2
            {
            root_0 = (Object)adaptor.nil();

            v=(Token)match(input,STRING,FOLLOW_STRING_in_definition4629); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            v_tree = (Object)adaptor.create(v);
            adaptor.addChild(root_0, v_tree);
            }
            BECOMES160=(Token)match(input,BECOMES,FOLLOW_BECOMES_in_definition4631); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            BECOMES160_tree = (Object)adaptor.create(BECOMES160);
            adaptor.addChild(root_0, BECOMES160_tree);
            }
            pushFollow(FOLLOW_exp2_in_definition4635);
            e=exp2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());
            if ( state.backtracking==0 ) {
               
                      	String var = v.getText();
                      	ASTExpression expression = (e!=null?e.result:null);
               	driver.nextParameter(var, expression);
                      
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (Object)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 54, definition_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "definition"

    // $ANTLR start synpred7_CFDG
    public final void synpred7_CFDG_fragment() throws RecognitionException {   
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:40:11: ( rule )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:40:11: rule
        {
        pushFollow(FOLLOW_rule_in_synpred7_CFDG192);
        rule();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred7_CFDG

    // $ANTLR start synpred8_CFDG
    public final void synpred8_CFDG_fragment() throws RecognitionException {   
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:41:11: ( path )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:41:11: path
        {
        pushFollow(FOLLOW_path_in_synpred8_CFDG204);
        path();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred8_CFDG

    // $ANTLR start synpred9_CFDG
    public final void synpred9_CFDG_fragment() throws RecognitionException {   
        CFDGParser.shape_return r = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:42:11: (r= shape )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:42:11: r= shape
        {
        pushFollow(FOLLOW_shape_in_synpred9_CFDG218);
        r=shape();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_CFDG

    // $ANTLR start synpred10_CFDG
    public final void synpred10_CFDG_fragment() throws RecognitionException {   
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:45:11: ( shape_singleton )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:45:11: shape_singleton
        {
        pushFollow(FOLLOW_shape_singleton_in_synpred10_CFDG232);
        shape_singleton();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_CFDG

    // $ANTLR start synpred11_CFDG
    public final void synpred11_CFDG_fragment() throws RecognitionException {   
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:46:11: ( shape_element )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:46:11: shape_element
        {
        pushFollow(FOLLOW_shape_element_in_synpred11_CFDG244);
        shape_element();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_CFDG

    // $ANTLR start synpred13_CFDG
    public final void synpred13_CFDG_fragment() throws RecognitionException {   
        Token s=null;
        CFDGParser.parameter_spec_return p = null;

        CFDGParser.modification_return m = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:67:9: ( STARTSHAPE s= STRING p= parameter_spec m= modification )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:67:9: STARTSHAPE s= STRING p= parameter_spec m= modification
        {
        match(input,STARTSHAPE,FOLLOW_STARTSHAPE_in_synpred13_CFDG367); if (state.failed) return ;
        s=(Token)match(input,STRING,FOLLOW_STRING_in_synpred13_CFDG371); if (state.failed) return ;
        pushFollow(FOLLOW_parameter_spec_in_synpred13_CFDG375);
        p=parameter_spec();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_modification_in_synpred13_CFDG379);
        m=modification();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_CFDG

    // $ANTLR start synpred31_CFDG
    public final void synpred31_CFDG_fragment() throws RecognitionException {   
        CFDGParser.element_simple_return r = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:382:9: (r= element_simple )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:382:9: r= element_simple
        {
        pushFollow(FOLLOW_element_simple_in_synpred31_CFDG1827);
        r=element_simple();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred31_CFDG

    // $ANTLR start synpred32_CFDG
    public final void synpred32_CFDG_fragment() throws RecognitionException {   
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:386:9: ( definition )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:386:9: definition
        {
        pushFollow(FOLLOW_definition_in_synpred32_CFDG1849);
        definition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred32_CFDG

    // $ANTLR start synpred33_CFDG
    public final void synpred33_CFDG_fragment() throws RecognitionException {   
        CFDGParser.element_loop_return rl = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:390:9: (rl= element_loop )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:390:9: rl= element_loop
        {
        pushFollow(FOLLOW_element_loop_in_synpred33_CFDG1873);
        rl=element_loop();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred33_CFDG

    // $ANTLR start synpred34_CFDG
    public final void synpred34_CFDG_fragment() throws RecognitionException {   
        CFDGParser.element_loop_return rl = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:398:9: (rl= element_loop FINALLY one_or_more_elements )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:398:9: rl= element_loop FINALLY one_or_more_elements
        {
        pushFollow(FOLLOW_element_loop_in_synpred34_CFDG1897);
        rl=element_loop();

        state._fsp--;
        if (state.failed) return ;
        match(input,FINALLY,FOLLOW_FINALLY_in_synpred34_CFDG1899); if (state.failed) return ;
        pushFollow(FOLLOW_one_or_more_elements_in_synpred34_CFDG1903);
        one_or_more_elements();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred34_CFDG

    // $ANTLR start synpred35_CFDG
    public final void synpred35_CFDG_fragment() throws RecognitionException {   
        CFDGParser.ifHeader_return ri = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:409:9: (ri= ifHeader one_or_more_elements )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:409:9: ri= ifHeader one_or_more_elements
        {
        pushFollow(FOLLOW_ifHeader_in_synpred35_CFDG1927);
        ri=ifHeader();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_one_or_more_elements_in_synpred35_CFDG1929);
        one_or_more_elements();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred35_CFDG

    // $ANTLR start synpred36_CFDG
    public final void synpred36_CFDG_fragment() throws RecognitionException {   
        CFDGParser.ifHeader_return ri = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:417:9: (ri= ifHeader one_or_more_elements ELSE one_or_more_elements )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:417:9: ri= ifHeader one_or_more_elements ELSE one_or_more_elements
        {
        pushFollow(FOLLOW_ifHeader_in_synpred36_CFDG1953);
        ri=ifHeader();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_one_or_more_elements_in_synpred36_CFDG1955);
        one_or_more_elements();

        state._fsp--;
        if (state.failed) return ;
        match(input,ELSE,FOLLOW_ELSE_in_synpred36_CFDG1957); if (state.failed) return ;
        pushFollow(FOLLOW_one_or_more_elements_in_synpred36_CFDG1961);
        one_or_more_elements();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred36_CFDG

    // $ANTLR start synpred73_CFDG
    public final void synpred73_CFDG_fragment() throws RecognitionException {   
        Token t=null;
        CFDGParser.explist_return ml = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:685:9: (t= ( TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH ) ml= explist )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:685:9: t= ( TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH ) ml= explist
        {
        t=(Token)input.LT(1);
        if ( input.LA(1)==SIZE||(input.LA(1)>=TIME && input.LA(1)<=WIDTH) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }

        pushFollow(FOLLOW_explist_in_synpred73_CFDG3212);
        ml=explist();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred73_CFDG

    // $ANTLR start synpred77_CFDG
    public final void synpred77_CFDG_fragment() throws RecognitionException {   
        Token t=null;
        CFDGParser.exp_return m = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:690:9: (t= ( HUE | SATURATION | BRIGHTNESS | ALPHA ) m= exp '|' )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:690:9: t= ( HUE | SATURATION | BRIGHTNESS | ALPHA ) m= exp '|'
        {
        t=(Token)input.LT(1);
        if ( (input.LA(1)>=HUE && input.LA(1)<=ALPHA) ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }

        pushFollow(FOLLOW_exp_in_synpred77_CFDG3248);
        m=exp();

        state._fsp--;
        if (state.failed) return ;
        match(input,74,FOLLOW_74_in_synpred77_CFDG3250); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred77_CFDG

    // $ANTLR start synpred78_CFDG
    public final void synpred78_CFDG_fragment() throws RecognitionException {   
        Token p=null;

        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:695:9: ( PARAM p= STRING )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:695:9: PARAM p= STRING
        {
        match(input,PARAM,FOLLOW_PARAM_in_synpred78_CFDG3272); if (state.failed) return ;
        p=(Token)match(input,STRING,FOLLOW_STRING_in_synpred78_CFDG3276); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred78_CFDG

    // $ANTLR start synpred79_CFDG
    public final void synpred79_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp_return e1 = null;

        CFDGParser.explist_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:706:9: (e1= exp e2= explist )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:706:9: e1= exp e2= explist
        {
        pushFollow(FOLLOW_exp_in_synpred79_CFDG3352);
        e1=exp();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_explist_in_synpred79_CFDG3356);
        e2=explist();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred79_CFDG

    // $ANTLR start synpred94_CFDG
    public final void synpred94_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:791:9: ( ',' e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:791:9: ',' e2= exp2
        {
        match(input,66,FOLLOW_66_in_synpred94_CFDG3903); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred94_CFDG3907);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred94_CFDG

    // $ANTLR start synpred95_CFDG
    public final void synpred95_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:795:9: ( '+' e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:795:9: '+' e2= exp2
        {
        match(input,76,FOLLOW_76_in_synpred95_CFDG3929); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred95_CFDG3933);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred95_CFDG

    // $ANTLR start synpred96_CFDG
    public final void synpred96_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:799:9: ( '-' e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:799:9: '-' e2= exp2
        {
        match(input,75,FOLLOW_75_in_synpred96_CFDG3955); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred96_CFDG3959);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred96_CFDG

    // $ANTLR start synpred97_CFDG
    public final void synpred97_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:803:9: ( '*' e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:803:9: '*' e2= exp2
        {
        match(input,69,FOLLOW_69_in_synpred97_CFDG3981); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred97_CFDG3985);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred97_CFDG

    // $ANTLR start synpred98_CFDG
    public final void synpred98_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:807:9: ( '/' e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:807:9: '/' e2= exp2
        {
        match(input,77,FOLLOW_77_in_synpred98_CFDG4007); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred98_CFDG4011);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred98_CFDG

    // $ANTLR start synpred99_CFDG
    public final void synpred99_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:811:9: ( '^' e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:811:9: '^' e2= exp2
        {
        match(input,78,FOLLOW_78_in_synpred99_CFDG4033); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred99_CFDG4037);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred99_CFDG

    // $ANTLR start synpred100_CFDG
    public final void synpred100_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:815:9: ( LT e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:815:9: LT e2= exp2
        {
        match(input,LT,FOLLOW_LT_in_synpred100_CFDG4059); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred100_CFDG4063);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred100_CFDG

    // $ANTLR start synpred101_CFDG
    public final void synpred101_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:819:9: ( GT e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:819:9: GT e2= exp2
        {
        match(input,GT,FOLLOW_GT_in_synpred101_CFDG4085); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred101_CFDG4089);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred101_CFDG

    // $ANTLR start synpred102_CFDG
    public final void synpred102_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:823:9: ( LE e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:823:9: LE e2= exp2
        {
        match(input,LE,FOLLOW_LE_in_synpred102_CFDG4111); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred102_CFDG4115);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred102_CFDG

    // $ANTLR start synpred103_CFDG
    public final void synpred103_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:827:9: ( GE e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:827:9: GE e2= exp2
        {
        match(input,GE,FOLLOW_GE_in_synpred103_CFDG4137); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred103_CFDG4141);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred103_CFDG

    // $ANTLR start synpred104_CFDG
    public final void synpred104_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:831:9: ( AND e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:831:9: AND e2= exp2
        {
        match(input,AND,FOLLOW_AND_in_synpred104_CFDG4163); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred104_CFDG4167);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred104_CFDG

    // $ANTLR start synpred105_CFDG
    public final void synpred105_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:835:9: ( OR e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:835:9: OR e2= exp2
        {
        match(input,OR,FOLLOW_OR_in_synpred105_CFDG4189); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred105_CFDG4193);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred105_CFDG

    // $ANTLR start synpred106_CFDG
    public final void synpred106_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:839:9: ( XOR e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:839:9: XOR e2= exp2
        {
        match(input,XOR,FOLLOW_XOR_in_synpred106_CFDG4215); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred106_CFDG4219);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred106_CFDG

    // $ANTLR start synpred107_CFDG
    public final void synpred107_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:843:9: ( EQ e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:843:9: EQ e2= exp2
        {
        match(input,EQ,FOLLOW_EQ_in_synpred107_CFDG4241); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred107_CFDG4245);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred107_CFDG

    // $ANTLR start synpred108_CFDG
    public final void synpred108_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:847:9: ( NEQ e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:847:9: NEQ e2= exp2
        {
        match(input,NEQ,FOLLOW_NEQ_in_synpred108_CFDG4267); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred108_CFDG4271);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred108_CFDG

    // $ANTLR start synpred109_CFDG
    public final void synpred109_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:851:9: ( PLUSMINUS e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:851:9: PLUSMINUS e2= exp2
        {
        match(input,PLUSMINUS,FOLLOW_PLUSMINUS_in_synpred109_CFDG4293); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred109_CFDG4297);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred109_CFDG

    // $ANTLR start synpred110_CFDG
    public final void synpred110_CFDG_fragment() throws RecognitionException {   
        CFDGParser.exp2_return e2 = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:856:9: ( RANGE e2= exp2 )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:856:9: RANGE e2= exp2
        {
        match(input,RANGE,FOLLOW_RANGE_in_synpred110_CFDG4319); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred110_CFDG4323);
        e2=exp2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred110_CFDG

    // $ANTLR start synpred111_CFDG
    public final void synpred111_CFDG_fragment() throws RecognitionException {   
        Token f=null;

        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:865:9: (f= STRING '(' ')' )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:865:9: f= STRING '(' ')'
        {
        f=(Token)match(input,STRING,FOLLOW_STRING_in_synpred111_CFDG4376); if (state.failed) return ;
        match(input,67,FOLLOW_67_in_synpred111_CFDG4378); if (state.failed) return ;
        match(input,68,FOLLOW_68_in_synpred111_CFDG4380); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred111_CFDG

    // $ANTLR start synpred112_CFDG
    public final void synpred112_CFDG_fragment() throws RecognitionException {   
        Token f=null;
        CFDGParser.exp2_return e = null;


        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:870:9: (f= STRING '(' e= exp2 ')' )
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:870:9: f= STRING '(' e= exp2 ')'
        {
        f=(Token)match(input,STRING,FOLLOW_STRING_in_synpred112_CFDG4405); if (state.failed) return ;
        match(input,67,FOLLOW_67_in_synpred112_CFDG4407); if (state.failed) return ;
        pushFollow(FOLLOW_exp2_in_synpred112_CFDG4411);
        e=exp2();

        state._fsp--;
        if (state.failed) return ;
        match(input,68,FOLLOW_68_in_synpred112_CFDG4413); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred112_CFDG

    // Delegated rules

    public final boolean synpred78_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred78_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred96_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred96_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred100_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred100_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred102_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred102_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred94_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred94_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred77_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred77_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred108_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred108_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred99_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred99_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred101_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred101_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred73_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred73_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred98_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred98_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred112_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred112_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred103_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred103_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred105_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred105_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred110_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred110_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred79_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred79_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred107_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred107_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred33_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred33_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred106_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred106_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred97_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred97_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred109_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred109_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred95_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred95_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred104_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred104_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred111_CFDG() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred111_CFDG_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA18 dfa18 = new DFA18(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA2_eotS =
        "\17\uffff";
    static final String DFA2_eofS =
        "\17\uffff";
    static final String DFA2_minS =
        "\1\4\5\uffff\3\0\6\uffff";
    static final String DFA2_maxS =
        "\1\17\5\uffff\3\0\6\uffff";
    static final String DFA2_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\3\uffff\1\13\1\6\1\12\1\7\1\10\1\11";
    static final String DFA2_specialS =
        "\6\uffff\1\0\1\1\1\2\6\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\3\2\uffff\1\1\1\11\1\2\1\4\1\5\1\10\1\6\1\uffff\1\7",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "31:1: statement returns [ASTReplacement result] : ( initialization | background | inclusion | tile | size | rule | path | r= shape | shape_singleton | shape_element | global_definition );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA2_6 = input.LA(1);

                         
                        int index2_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_CFDG()) ) {s = 10;}

                        else if ( (synpred11_CFDG()) ) {s = 11;}

                         
                        input.seek(index2_6);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA2_7 = input.LA(1);

                         
                        int index2_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred8_CFDG()) ) {s = 12;}

                        else if ( (synpred11_CFDG()) ) {s = 11;}

                         
                        input.seek(index2_7);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA2_8 = input.LA(1);

                         
                        int index2_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred9_CFDG()) ) {s = 13;}

                        else if ( (synpred10_CFDG()) ) {s = 14;}

                         
                        input.seek(index2_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 2, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA18_eotS =
        "\15\uffff";
    static final String DFA18_eofS =
        "\15\uffff";
    static final String DFA18_minS =
        "\1\10\1\uffff\1\0\1\uffff\2\0\7\uffff";
    static final String DFA18_maxS =
        "\1\27\1\uffff\1\0\1\uffff\2\0\7\uffff";
    static final String DFA18_acceptS =
        "\1\uffff\1\1\4\uffff\1\7\1\10\1\2\1\3\1\4\1\5\1\6";
    static final String DFA18_specialS =
        "\2\uffff\1\0\1\uffff\1\1\1\2\7\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\2\6\uffff\2\1\2\uffff\1\4\1\uffff\1\5\1\6\1\7",
            "",
            "\1\uffff",
            "",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "380:1: element returns [ASTReplacement result] : (r= element_simple | definition | rl= element_loop | rl= element_loop FINALLY one_or_more_elements | ri= ifHeader one_or_more_elements | ri= ifHeader one_or_more_elements ELSE one_or_more_elements | rt= transHeader one_or_more_elements | rs= switchHeader '{' caseBody '}' );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_2 = input.LA(1);

                         
                        int index18_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_CFDG()) ) {s = 1;}

                        else if ( (synpred32_CFDG()) ) {s = 8;}

                         
                        input.seek(index18_2);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA18_4 = input.LA(1);

                         
                        int index18_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_CFDG()) ) {s = 9;}

                        else if ( (synpred34_CFDG()) ) {s = 10;}

                         
                        input.seek(index18_4);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA18_5 = input.LA(1);

                         
                        int index18_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred35_CFDG()) ) {s = 11;}

                        else if ( (synpred36_CFDG()) ) {s = 12;}

                         
                        input.seek(index18_5);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA33_eotS =
        "\12\uffff";
    static final String DFA33_eofS =
        "\12\uffff";
    static final String DFA33_minS =
        "\1\10\1\uffff\2\10\6\uffff";
    static final String DFA33_maxS =
        "\1\114\1\uffff\2\16\6\uffff";
    static final String DFA33_acceptS =
        "\1\uffff\1\1\2\uffff\1\4\1\5\1\2\1\6\1\3\1\7";
    static final String DFA33_specialS =
        "\12\uffff}>";
    static final String[] DFA33_transitionS = {
            "\1\5\5\uffff\1\1\64\uffff\1\4\7\uffff\1\2\1\3",
            "",
            "\1\7\5\uffff\1\6",
            "\1\11\5\uffff\1\10",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA33_eot = DFA.unpackEncodedString(DFA33_eotS);
    static final short[] DFA33_eof = DFA.unpackEncodedString(DFA33_eofS);
    static final char[] DFA33_min = DFA.unpackEncodedStringToUnsignedChars(DFA33_minS);
    static final char[] DFA33_max = DFA.unpackEncodedStringToUnsignedChars(DFA33_maxS);
    static final short[] DFA33_accept = DFA.unpackEncodedString(DFA33_acceptS);
    static final short[] DFA33_special = DFA.unpackEncodedString(DFA33_specialS);
    static final short[][] DFA33_transition;

    static {
        int numStates = DFA33_transitionS.length;
        DFA33_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA33_transition[i] = DFA.unpackEncodedString(DFA33_transitionS[i]);
        }
    }

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = DFA33_eot;
            this.eof = DFA33_eof;
            this.min = DFA33_min;
            this.max = DFA33_max;
            this.accept = DFA33_accept;
            this.special = DFA33_special;
            this.transition = DFA33_transition;
        }
        public String getDescription() {
            return "717:9: (r= RATIONAL | '-' r= RATIONAL | '+' r= RATIONAL | '(' e= exp2 ')' | f= expfunc | '-' f= expfunc | '+' f= expfunc )";
        }
    }
    static final String DFA36_eotS =
        "\44\uffff";
    static final String DFA36_eofS =
        "\1\22\43\uffff";
    static final String DFA36_minS =
        "\1\4\21\0\22\uffff";
    static final String DFA36_maxS =
        "\1\116\21\0\22\uffff";
    static final String DFA36_acceptS =
        "\22\uffff\1\22\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"+
        "\14\1\15\1\16\1\17\1\20\1\21";
    static final String DFA36_specialS =
        "\1\uffff\1\4\1\5\1\6\1\14\1\12\1\0\1\3\1\17\1\16\1\15\1\20\1\2\1"+
        "\1\1\10\1\7\1\11\1\13\22\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\22\2\uffff\7\22\1\uffff\2\22\2\uffff\1\22\1\uffff\3\22\31"+
            "\uffff\1\20\1\21\1\uffff\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1"+
            "\16\1\17\3\uffff\2\22\1\1\1\uffff\1\22\1\4\1\22\2\uffff\1\22"+
            "\1\uffff\1\3\1\2\1\5\1\6",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "790:9: ( ',' e2= exp2 | '+' e2= exp2 | '-' e2= exp2 | '*' e2= exp2 | '/' e2= exp2 | '^' e2= exp2 | LT e2= exp2 | GT e2= exp2 | LE e2= exp2 | GE e2= exp2 | AND e2= exp2 | OR e2= exp2 | XOR e2= exp2 | EQ e2= exp2 | NEQ e2= exp2 | PLUSMINUS e2= exp2 | RANGE e2= exp2 )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_6 = input.LA(1);

                         
                        int index36_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred99_CFDG()) ) {s = 24;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_6);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA36_13 = input.LA(1);

                         
                        int index36_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred106_CFDG()) ) {s = 31;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_13);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA36_12 = input.LA(1);

                         
                        int index36_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred105_CFDG()) ) {s = 30;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_12);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA36_7 = input.LA(1);

                         
                        int index36_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred100_CFDG()) ) {s = 25;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_7);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA36_1 = input.LA(1);

                         
                        int index36_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred94_CFDG()) ) {s = 19;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_1);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA36_2 = input.LA(1);

                         
                        int index36_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred95_CFDG()) ) {s = 20;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_2);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA36_3 = input.LA(1);

                         
                        int index36_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred96_CFDG()) ) {s = 21;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_3);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA36_15 = input.LA(1);

                         
                        int index36_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred108_CFDG()) ) {s = 33;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_15);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA36_14 = input.LA(1);

                         
                        int index36_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred107_CFDG()) ) {s = 32;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_14);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA36_16 = input.LA(1);

                         
                        int index36_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred109_CFDG()) ) {s = 34;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_16);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA36_5 = input.LA(1);

                         
                        int index36_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred98_CFDG()) ) {s = 23;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_5);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA36_17 = input.LA(1);

                         
                        int index36_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred110_CFDG()) ) {s = 35;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_17);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA36_4 = input.LA(1);

                         
                        int index36_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred97_CFDG()) ) {s = 22;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_4);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA36_10 = input.LA(1);

                         
                        int index36_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred103_CFDG()) ) {s = 28;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_10);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA36_9 = input.LA(1);

                         
                        int index36_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred102_CFDG()) ) {s = 27;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_9);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA36_8 = input.LA(1);

                         
                        int index36_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred101_CFDG()) ) {s = 26;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_8);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA36_11 = input.LA(1);

                         
                        int index36_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred104_CFDG()) ) {s = 29;}

                        else if ( (true) ) {s = 18;}

                         
                        input.seek(index36_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_statement_in_cfdg77 = new BitSet(new long[]{0x000000000000BF90L});
    public static final BitSet FOLLOW_EOF_in_cfdg82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_initialization_in_statement128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_background_in_statement140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_inclusion_in_statement154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tile_in_statement168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_size_in_statement180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_statement192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_statement204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_in_statement218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_singleton_in_statement232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_element_in_statement244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_definition_in_statement256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDE_in_inclusion301 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_QSTRING_in_inclusion305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INCLUDE_in_inclusion327 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_FILENAME_in_inclusion331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSHAPE_in_initialization367 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_initialization371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_parameter_spec_in_initialization375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_initialization379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSHAPE_in_initialization401 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_initialization405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_parameter_spec_in_initialization409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BACKGROUND_in_background445 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_global_modification_in_background449 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TILE_in_tile485 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_global_modification_in_tile489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SIZE_in_size525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_global_modification_in_size529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHAPE_in_shape570 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_shape574 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_parameter_list_in_shape576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_in_shape_singleton618 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_shape_singleton622 = new BitSet(new long[]{0x0000000000E98100L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_elements_in_shape_singleton625 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_shape_singleton627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_rule_header668 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_rule_header672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_rule_header694 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_rule_header698 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RATIONAL_in_rule_header702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_header_in_rule744 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_rule746 = new BitSet(new long[]{0x0000000000004100L,0x000000000000000AL});
    public static final BitSet FOLLOW_buncha_replacements_v2_in_rule748 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_rule750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_shape_element_header790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_in_shape_element_header811 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RATIONAL_in_shape_element_header815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PATH_in_shape_element_header837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_element_header_in_shape_element879 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_shape_element881 = new BitSet(new long[]{0x0000000000E98100L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_elements_in_shape_element883 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_shape_element885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PATH_in_path_header925 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_path_header929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_header_in_path971 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_path973 = new BitSet(new long[]{0x0000000000014100L,0x000000000000000AL});
    public static final BitSet FOLLOW_buncha_pathOps_v2_in_path975 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_path977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_parameter1013 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_parameter1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHAPE_in_parameter1039 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_parameter1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_buncha_parameters1080 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_buncha_parameters1082 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_buncha_parameters_in_buncha_parameters1084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parameter_in_buncha_parameters1106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_parameter_list1140 = new BitSet(new long[]{0x0000000000001100L});
    public static final BitSet FOLLOW_buncha_parameters_in_parameter_list1142 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_parameter_list1144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_parameter_spec1194 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_parameter_spec1198 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_parameter_spec1200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_parameter_spec1214 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_parameter_spec1216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_in_buncha_elements1267 = new BitSet(new long[]{0x0000000000E98100L});
    public static final BitSet FOLLOW_buncha_elements_in_buncha_elements1269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathOp_v2_in_buncha_pathOps_v21329 = new BitSet(new long[]{0x0000000000014100L,0x0000000000000008L});
    public static final BitSet FOLLOW_buncha_pathOps_v2_in_buncha_pathOps_v21331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PATHOP_in_pathOp_simple_v21394 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_pathOp_simple_v21396 = new BitSet(new long[]{0x0001FFFFFE000800L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_pathop_adjustments_in_pathOp_simple_v21400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_pathOp_simple_v21402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_pathOp_simple_v21426 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_modification_v2_in_pathOp_simple_v21430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PATHOP_in_element_simple1472 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_element_simple1474 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_element_simple1478 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_element_simple1480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PATHOP_in_element_simple1504 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_element_simple1506 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_element_simple1508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_element_simple1532 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_parameter_spec_in_element_simple1536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_element_simple1540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PATH_in_element_simple1562 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_element_simple1566 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_parameter_spec_in_element_simple1570 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_element_simple1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_one_or_more_elements1610 = new BitSet(new long[]{0x0000000000E98100L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_elements_in_one_or_more_elements1612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_one_or_more_elements1614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_simple_in_one_or_more_elements1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_one_or_more_pathOp_v21674 = new BitSet(new long[]{0x0000000000014100L,0x000000000000000AL});
    public static final BitSet FOLLOW_buncha_pathOps_v2_in_one_or_more_pathOp_v21676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_one_or_more_pathOp_v21678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathOp_simple_v2_in_one_or_more_pathOp_v21702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_caseBody_element_in_caseBody1738 = new BitSet(new long[]{0x0000000001040000L});
    public static final BitSet FOLLOW_caseBody_in_caseBody1740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_caseHeader_in_caseBody_element1785 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_caseBody_element1787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_simple_in_element1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_definition_in_element1849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_loop_in_element1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_loop_in_element1897 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_FINALLY_in_element1899 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_element1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifHeader_in_element1927 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_element1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifHeader_in_element1953 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_element1955 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_element1957 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_element1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_transHeader_in_element1985 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_element1987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_switchHeader_in_element2011 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_element2013 = new BitSet(new long[]{0x0000000001040000L,0x0000000000000002L});
    public static final BitSet FOLLOW_caseBody_in_element2015 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_element2017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathOp_simple_v2_in_pathOp_v22059 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loopHeader_v2_in_pathOp_v22083 = new BitSet(new long[]{0x0000000000010100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_pathOp_v2_in_pathOp_v22085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loopHeader_in_element_loop2127 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_element_loop2129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_replacement_v2_in_buncha_replacements_v22168 = new BitSet(new long[]{0x0000000000004100L,0x0000000000000008L});
    public static final BitSet FOLLOW_buncha_replacements_v2_in_buncha_replacements_v22170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_one_or_more_replacements_v22216 = new BitSet(new long[]{0x0000000000004100L,0x000000000000000AL});
    public static final BitSet FOLLOW_buncha_replacements_v2_in_one_or_more_replacements_v22218 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_one_or_more_replacements_v22220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_replacement_simple_v2_in_one_or_more_replacements_v22244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_replacement_simple_v22286 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_modification_v2_in_replacement_simple_v22290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_replacement_simple_v2_in_replacement_v22332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_loopHeader_v2_in_replacement_v22356 = new BitSet(new long[]{0x0000000000000100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_replacements_v2_in_replacement_v22358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RATIONAL_in_loopHeader_v22400 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_loopHeader_v22402 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_modification_v2_in_loopHeader_v22406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_loopHeader_v22428 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_loopHeader_v22432 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_loopHeader_v22434 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_loopHeader_v22436 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000081L});
    public static final BitSet FOLLOW_modification_v2_in_loopHeader_v22440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOOP_in_loopHeader2480 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_loopHeader2484 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_BECOMES_in_loopHeader2486 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_loopHeader2490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_loopHeader2494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOOP_in_loopHeader2516 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_loopHeader2520 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_loopHeader2524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_ifHeader2564 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ifHeader2566 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_ifHeader2570 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ifHeader2572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRANSFORM_in_transHeader2612 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_transHeader2616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SWITCH_in_switchHeader2656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_switchHeader2658 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_switchHeader2662 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_switchHeader2664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_caseHeader2701 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_caseHeader2705 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_caseHeader2707 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_caseHeader2729 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_caseHeader2731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_modification_v22771 = new BitSet(new long[]{0x0001FFFFFE000800L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_canonical_adjustments_in_modification_v22773 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_modification_v22775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_modification_v22797 = new BitSet(new long[]{0x0001FFFFFE000800L,0x0000000000000100L});
    public static final BitSet FOLLOW_buncha_adjustments_in_modification_v22801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_modification_v22803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_modification2843 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_modification2845 = new BitSet(new long[]{0x0001FFFFFE000800L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_canonical_adjustments_in_modification2847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_modification2849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_modification2871 = new BitSet(new long[]{0x0001FFFFFE000800L,0x0000000000000002L});
    public static final BitSet FOLLOW_buncha_adjustments_in_modification2875 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_modification2877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_modification_v2_in_global_modification2919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adjustment_in_buncha_pathop_adjustments2965 = new BitSet(new long[]{0x0001FFFFFE000800L});
    public static final BitSet FOLLOW_buncha_pathop_adjustments_in_buncha_pathop_adjustments2969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adjustment_in_buncha_adjustments3032 = new BitSet(new long[]{0x0001FFFFFE000800L});
    public static final BitSet FOLLOW_buncha_adjustments_in_buncha_adjustments3036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_adjustment_in_buncha_canonical_adjustments3095 = new BitSet(new long[]{0x0001FFFFFE000800L});
    public static final BitSet FOLLOW_buncha_canonical_adjustments_in_buncha_canonical_adjustments3097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_adjustment3160 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_explist_in_adjustment3212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_adjustment3236 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_exp_in_adjustment3248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_adjustment3250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARAM_in_adjustment3272 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_adjustment3276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARAM_in_adjustment3298 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_QSTRING_in_adjustment3302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_in_explist3352 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_explist_in_explist3356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_in_explist3381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RATIONAL_in_exp3433 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_75_in_exp3455 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RATIONAL_in_exp3459 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_76_in_exp3481 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RATIONAL_in_exp3485 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_67_in_exp3507 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp3511 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_exp3513 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_expfunc_in_exp3538 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_75_in_exp3560 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_expfunc_in_exp3564 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_76_in_exp3586 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_expfunc_in_exp3590 = new BitSet(new long[]{0x0006000000000002L});
    public static final BitSet FOLLOW_PLUSMINUS_in_exp3622 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_exp_in_exp3626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGE_in_exp3648 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_exp_in_exp3652 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RATIONAL_in_exp23716 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_expfunc_in_exp23740 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_67_in_exp23762 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp23766 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_exp23768 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_75_in_exp23791 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_expfunc_in_exp23795 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_76_in_exp23817 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_expfunc_in_exp23821 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_NOT_in_exp23843 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp23847 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_modification_in_exp23871 = new BitSet(new long[]{0x1FF6000000000002L,0x0000000000007824L});
    public static final BitSet FOLLOW_66_in_exp23903 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp23907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_exp23929 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp23933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_exp23955 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp23959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_exp23981 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp23985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_exp24007 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_exp24033 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_exp24059 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_exp24085 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_exp24111 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_exp24137 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_exp24163 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_exp24189 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_exp24215 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_exp24241 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQ_in_exp24267 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSMINUS_in_exp24293 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGE_in_exp24319 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp24323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expfunc4376 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_expfunc4378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_expfunc4380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expfunc4405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_expfunc4407 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_expfunc4411 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_expfunc4413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_expfunc4437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp2func4479 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_exp2func4481 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_exp2func4483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp2func4508 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_exp2func4510 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_exp2func4514 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_exp2func4516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_exp2func4540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_global_definition4586 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_BECOMES_in_global_definition4588 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_global_definition4592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_definition4629 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_BECOMES_in_definition4631 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_definition4635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule_in_synpred7_CFDG192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_in_synpred8_CFDG204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_in_synpred9_CFDG218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_singleton_in_synpred10_CFDG232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shape_element_in_synpred11_CFDG244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STARTSHAPE_in_synpred13_CFDG367 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_synpred13_CFDG371 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_parameter_spec_in_synpred13_CFDG375 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000209L});
    public static final BitSet FOLLOW_modification_in_synpred13_CFDG379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_simple_in_synpred31_CFDG1827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_definition_in_synpred32_CFDG1849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_loop_in_synpred33_CFDG1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_element_loop_in_synpred34_CFDG1897 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_FINALLY_in_synpred34_CFDG1899 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_synpred34_CFDG1903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifHeader_in_synpred35_CFDG1927 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_synpred35_CFDG1929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifHeader_in_synpred36_CFDG1953 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_synpred36_CFDG1955 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ELSE_in_synpred36_CFDG1957 = new BitSet(new long[]{0x0000000000018100L,0x0000000000000001L});
    public static final BitSet FOLLOW_one_or_more_elements_in_synpred36_CFDG1961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred73_CFDG3160 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_explist_in_synpred73_CFDG3212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_synpred77_CFDG3236 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_exp_in_synpred77_CFDG3248 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_synpred77_CFDG3250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PARAM_in_synpred78_CFDG3272 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_STRING_in_synpred78_CFDG3276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exp_in_synpred79_CFDG3352 = new BitSet(new long[]{0x0000000000004100L,0x0000000000001808L});
    public static final BitSet FOLLOW_explist_in_synpred79_CFDG3356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_synpred94_CFDG3903 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred94_CFDG3907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_synpred95_CFDG3929 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred95_CFDG3933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_synpred96_CFDG3955 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred96_CFDG3959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_synpred97_CFDG3981 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred97_CFDG3985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_synpred98_CFDG4007 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred98_CFDG4011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_synpred99_CFDG4033 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred99_CFDG4037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_synpred100_CFDG4059 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred100_CFDG4063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_synpred101_CFDG4085 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred101_CFDG4089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_synpred102_CFDG4111 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred102_CFDG4115 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_synpred103_CFDG4137 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred103_CFDG4141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_synpred104_CFDG4163 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred104_CFDG4167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_synpred105_CFDG4189 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred105_CFDG4193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_XOR_in_synpred106_CFDG4215 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred106_CFDG4219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_synpred107_CFDG4241 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred107_CFDG4245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQ_in_synpred108_CFDG4267 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred108_CFDG4271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSMINUS_in_synpred109_CFDG4293 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred109_CFDG4297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RANGE_in_synpred110_CFDG4319 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred110_CFDG4323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_synpred111_CFDG4376 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_synpred111_CFDG4378 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred111_CFDG4380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_synpred112_CFDG4405 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_synpred112_CFDG4407 = new BitSet(new long[]{0x0008000000004100L,0x0000000000001A09L});
    public static final BitSet FOLLOW_exp2_in_synpred112_CFDG4411 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_synpred112_CFDG4413 = new BitSet(new long[]{0x0000000000000002L});

}
