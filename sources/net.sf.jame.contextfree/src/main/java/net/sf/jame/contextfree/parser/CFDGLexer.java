// $ANTLR 3.3 Nov 30, 2010 12:45:30 /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g 2012-08-22 00:03:34
 
	package net.sf.jame.contextfree.parser; 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CFDGLexer extends Lexer {
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

    public CFDGLexer() {;} 
    public CFDGLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CFDGLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g"; }

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:7:7: ( '{' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:7:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:8:7: ( '}' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:8:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:9:7: ( ',' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:9:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:10:7: ( '(' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:10:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:11:7: ( ')' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:11:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:12:7: ( '*' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:12:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:13:7: ( ':' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:13:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:14:7: ( '[' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:14:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:15:7: ( ']' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:15:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:16:7: ( '~' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:16:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:17:7: ( '|' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:17:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:18:7: ( '-' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:18:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:19:7: ( '+' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:19:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:20:7: ( '/' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:20:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:21:7: ( '^' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:21:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "RATIONAL"
    public final void mRATIONAL() throws RecognitionException {
        try {
            int _type = RATIONAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:921:2: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( '%' )? | '.' ( '0' .. '9' )+ ( '%' )? | ( '0' .. '9' )+ ( '%' )? )
            int alt8=3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:2: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( '%' )?
                    {
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:2: ( '0' .. '9' )+
                    int cnt1=0;
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:3: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt1 >= 1 ) break loop1;
                                EarlyExitException eee =
                                    new EarlyExitException(1, input);
                                throw eee;
                        }
                        cnt1++;
                    } while (true);

                    match('.'); 
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:18: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:19: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:30: ( '%' )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='%') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:30: '%'
                            {
                            match('%'); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:37: '.' ( '0' .. '9' )+ ( '%' )?
                    {
                    match('.'); 
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:41: ( '0' .. '9' )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:42: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);

                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:53: ( '%' )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='%') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:53: '%'
                            {
                            match('%'); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:60: ( '0' .. '9' )+ ( '%' )?
                    {
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:60: ( '0' .. '9' )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:60: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);

                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:70: ( '%' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='%') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:922:70: '%'
                            {
                            match('%'); 

                            }
                            break;

                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RATIONAL"

    // $ANTLR start "RATIONAL2"
    public final void mRATIONAL2() throws RecognitionException {
        try {
            int _type = RATIONAL2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:926:2: ( ( '0' .. '9' )+ ( '%' )? '..' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:927:2: ( '0' .. '9' )+ ( '%' )? '..'
            {
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:927:2: ( '0' .. '9' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:927:3: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:927:14: ( '%' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='%') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:927:14: '%'
                    {
                    match('%'); 

                    }
                    break;

            }

            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RATIONAL2"

    // $ANTLR start "STARTSHAPE"
    public final void mSTARTSHAPE() throws RecognitionException {
        try {
            int _type = STARTSHAPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:931:2: ( 'startshape' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:932:2: 'startshape'
            {
            match("startshape"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STARTSHAPE"

    // $ANTLR start "BACKGROUND"
    public final void mBACKGROUND() throws RecognitionException {
        try {
            int _type = BACKGROUND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:936:2: ( 'background' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:937:2: 'background'
            {
            match("background"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BACKGROUND"

    // $ANTLR start "INCLUDE"
    public final void mINCLUDE() throws RecognitionException {
        try {
            int _type = INCLUDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:941:2: ( 'include' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:942:2: 'include'
            {
            match("include"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INCLUDE"

    // $ANTLR start "TILE"
    public final void mTILE() throws RecognitionException {
        try {
            int _type = TILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:946:2: ( 'tile' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:947:2: 'tile'
            {
            match("tile"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TILE"

    // $ANTLR start "RULE"
    public final void mRULE() throws RecognitionException {
        try {
            int _type = RULE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:951:2: ( 'rule' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:952:2: 'rule'
            {
            match("rule"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE"

    // $ANTLR start "PATH"
    public final void mPATH() throws RecognitionException {
        try {
            int _type = PATH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:956:2: ( 'path' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:957:2: 'path'
            {
            match("path"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PATH"

    // $ANTLR start "SHAPE"
    public final void mSHAPE() throws RecognitionException {
        try {
            int _type = SHAPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:961:2: ( 'shape' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:962:2: 'shape'
            {
            match("shape"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHAPE"

    // $ANTLR start "LOOP"
    public final void mLOOP() throws RecognitionException {
        try {
            int _type = LOOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:966:2: ( 'loop' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:967:2: 'loop'
            {
            match("loop"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOOP"

    // $ANTLR start "FINALLY"
    public final void mFINALLY() throws RecognitionException {
        try {
            int _type = FINALLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:971:2: ( 'finally' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:972:2: 'finally'
            {
            match("finally"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FINALLY"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:976:2: ( 'if' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:977:2: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:981:2: ( 'else' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:982:2: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "SWITCH"
    public final void mSWITCH() throws RecognitionException {
        try {
            int _type = SWITCH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:986:2: ( 'switch' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:987:2: 'switch'
            {
            match("switch"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SWITCH"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:991:2: ( 'case' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:992:2: 'case'
            {
            match("case"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "RANGE"
    public final void mRANGE() throws RecognitionException {
        try {
            int _type = RANGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:996:2: ( '..' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:997:2: '..'
            {
            match(".."); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RANGE"

    // $ANTLR start "PLUSMINUS"
    public final void mPLUSMINUS() throws RecognitionException {
        try {
            int _type = PLUSMINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1001:2: ( '+/-' | '\\u00b1' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='+') ) {
                alt11=1;
            }
            else if ( (LA11_0=='\u00B1') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1002:2: '+/-'
                    {
                    match("+/-"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1002:10: '\\u00b1'
                    {
                    match('\u00B1'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUSMINUS"

    // $ANTLR start "TIME"
    public final void mTIME() throws RecognitionException {
        try {
            int _type = TIME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1006:2: ( 'time' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1007:2: 'time'
            {
            match("time"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIME"

    // $ANTLR start "TIMESCALE"
    public final void mTIMESCALE() throws RecognitionException {
        try {
            int _type = TIMESCALE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1011:2: ( 'timescale' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1012:2: 'timescale'
            {
            match("timescale"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TIMESCALE"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            int _type = X;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1016:2: ( 'x' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1017:2: 'x'
            {
            match('x'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            int _type = Y;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1021:2: ( 'y' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1022:2: 'y'
            {
            match('y'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            int _type = Z;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1026:2: ( 'z' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1027:2: 'z'
            {
            match('z'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Z"

    // $ANTLR start "ROTATE"
    public final void mROTATE() throws RecognitionException {
        try {
            int _type = ROTATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1031:2: ( 'rotate' | 'r' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='r') ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1=='o') ) {
                    alt12=1;
                }
                else {
                    alt12=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1032:2: 'rotate'
                    {
                    match("rotate"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1032:13: 'r'
                    {
                    match('r'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ROTATE"

    // $ANTLR start "SIZE"
    public final void mSIZE() throws RecognitionException {
        try {
            int _type = SIZE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1036:2: ( 'size' | 's' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='s') ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1=='i') ) {
                    alt13=1;
                }
                else {
                    alt13=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1037:2: 'size'
                    {
                    match("size"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1037:11: 's'
                    {
                    match('s'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SIZE"

    // $ANTLR start "SKEW"
    public final void mSKEW() throws RecognitionException {
        try {
            int _type = SKEW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1041:2: ( 'skew' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1042:2: 'skew'
            {
            match("skew"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SKEW"

    // $ANTLR start "FLIP"
    public final void mFLIP() throws RecognitionException {
        try {
            int _type = FLIP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1046:2: ( 'flip' | 'f' )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='f') ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1=='l') ) {
                    alt14=1;
                }
                else {
                    alt14=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1047:2: 'flip'
                    {
                    match("flip"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1047:11: 'f'
                    {
                    match('f'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLIP"

    // $ANTLR start "HUE"
    public final void mHUE() throws RecognitionException {
        try {
            int _type = HUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1051:2: ( 'hue' | 'h' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='h') ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1=='u') ) {
                    alt15=1;
                }
                else {
                    alt15=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1052:2: 'hue'
                    {
                    match("hue"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1052:10: 'h'
                    {
                    match('h'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HUE"

    // $ANTLR start "SATURATION"
    public final void mSATURATION() throws RecognitionException {
        try {
            int _type = SATURATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1056:2: ( 'saturation' | 'sat' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='s') ) {
                int LA16_1 = input.LA(2);

                if ( (LA16_1=='a') ) {
                    int LA16_2 = input.LA(3);

                    if ( (LA16_2=='t') ) {
                        int LA16_3 = input.LA(4);

                        if ( (LA16_3=='u') ) {
                            alt16=1;
                        }
                        else {
                            alt16=2;}
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1057:2: 'saturation'
                    {
                    match("saturation"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1057:17: 'sat'
                    {
                    match("sat"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SATURATION"

    // $ANTLR start "BRIGHTNESS"
    public final void mBRIGHTNESS() throws RecognitionException {
        try {
            int _type = BRIGHTNESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1061:2: ( 'brightness' | 'b' )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='b') ) {
                int LA17_1 = input.LA(2);

                if ( (LA17_1=='r') ) {
                    alt17=1;
                }
                else {
                    alt17=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1062:2: 'brightness'
                    {
                    match("brightness"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1062:17: 'b'
                    {
                    match('b'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BRIGHTNESS"

    // $ANTLR start "ALPHA"
    public final void mALPHA() throws RecognitionException {
        try {
            int _type = ALPHA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1066:2: ( 'alpha' | 'a' )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0=='a') ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1=='l') ) {
                    alt18=1;
                }
                else {
                    alt18=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1067:2: 'alpha'
                    {
                    match("alpha"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1067:12: 'a'
                    {
                    match('a'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALPHA"

    // $ANTLR start "TARGETHUE"
    public final void mTARGETHUE() throws RecognitionException {
        try {
            int _type = TARGETHUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1071:2: ( '|hue' | '|h' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='|') ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1=='h') ) {
                    int LA19_2 = input.LA(3);

                    if ( (LA19_2=='u') ) {
                        alt19=1;
                    }
                    else {
                        alt19=2;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1072:2: '|hue'
                    {
                    match("|hue"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1072:11: '|h'
                    {
                    match("|h"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TARGETHUE"

    // $ANTLR start "TARGETSATURATION"
    public final void mTARGETSATURATION() throws RecognitionException {
        try {
            int _type = TARGETSATURATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1076:2: ( '|saturation' | '|sat' )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='|') ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1=='s') ) {
                    int LA20_2 = input.LA(3);

                    if ( (LA20_2=='a') ) {
                        int LA20_3 = input.LA(4);

                        if ( (LA20_3=='t') ) {
                            int LA20_4 = input.LA(5);

                            if ( (LA20_4=='u') ) {
                                alt20=1;
                            }
                            else {
                                alt20=2;}
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 20, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 20, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1077:2: '|saturation'
                    {
                    match("|saturation"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1077:18: '|sat'
                    {
                    match("|sat"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TARGETSATURATION"

    // $ANTLR start "TARGETBRIGHTNESS"
    public final void mTARGETBRIGHTNESS() throws RecognitionException {
        try {
            int _type = TARGETBRIGHTNESS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1081:2: ( '|brightness' | '|b' )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='|') ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1=='b') ) {
                    int LA21_2 = input.LA(3);

                    if ( (LA21_2=='r') ) {
                        alt21=1;
                    }
                    else {
                        alt21=2;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1082:2: '|brightness'
                    {
                    match("|brightness"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1082:18: '|b'
                    {
                    match("|b"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TARGETBRIGHTNESS"

    // $ANTLR start "TARGETALPHA"
    public final void mTARGETALPHA() throws RecognitionException {
        try {
            int _type = TARGETALPHA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1086:2: ( '|alpha' | '|a' )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='|') ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1=='a') ) {
                    int LA22_2 = input.LA(3);

                    if ( (LA22_2=='l') ) {
                        alt22=1;
                    }
                    else {
                        alt22=2;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1087:2: '|alpha'
                    {
                    match("|alpha"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1087:13: '|a'
                    {
                    match("|a"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TARGETALPHA"

    // $ANTLR start "X1"
    public final void mX1() throws RecognitionException {
        try {
            int _type = X1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1091:2: ( 'x1' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1092:2: 'x1'
            {
            match("x1"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "X1"

    // $ANTLR start "X2"
    public final void mX2() throws RecognitionException {
        try {
            int _type = X2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1096:2: ( 'x2' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1097:2: 'x2'
            {
            match("x2"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "X2"

    // $ANTLR start "Y1"
    public final void mY1() throws RecognitionException {
        try {
            int _type = Y1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1101:2: ( 'y1' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1102:2: 'y1'
            {
            match("y1"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Y1"

    // $ANTLR start "Y2"
    public final void mY2() throws RecognitionException {
        try {
            int _type = Y2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1106:2: ( 'y2' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1107:2: 'y2'
            {
            match("y2"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Y2"

    // $ANTLR start "RX"
    public final void mRX() throws RecognitionException {
        try {
            int _type = RX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1111:2: ( 'rx' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1112:2: 'rx'
            {
            match("rx"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RX"

    // $ANTLR start "RY"
    public final void mRY() throws RecognitionException {
        try {
            int _type = RY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1116:2: ( 'ry' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1117:2: 'ry'
            {
            match("ry"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RY"

    // $ANTLR start "WIDTH"
    public final void mWIDTH() throws RecognitionException {
        try {
            int _type = WIDTH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1121:2: ( 'width' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1122:2: 'width'
            {
            match("width"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WIDTH"

    // $ANTLR start "TRANSFORM"
    public final void mTRANSFORM() throws RecognitionException {
        try {
            int _type = TRANSFORM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1126:2: ( 'transform' | 'trans' )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='t') ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1=='r') ) {
                    int LA23_2 = input.LA(3);

                    if ( (LA23_2=='a') ) {
                        int LA23_3 = input.LA(4);

                        if ( (LA23_3=='n') ) {
                            int LA23_4 = input.LA(5);

                            if ( (LA23_4=='s') ) {
                                int LA23_5 = input.LA(6);

                                if ( (LA23_5=='f') ) {
                                    alt23=1;
                                }
                                else {
                                    alt23=2;}
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 23, 4, input);

                                throw nvae;
                            }
                        }
                        else {
                            NoViableAltException nvae =
                                new NoViableAltException("", 23, 3, input);

                            throw nvae;
                        }
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 23, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1127:2: 'transform'
                    {
                    match("transform"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1127:16: 'trans'
                    {
                    match("trans"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRANSFORM"

    // $ANTLR start "PARAM"
    public final void mPARAM() throws RecognitionException {
        try {
            int _type = PARAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1131:2: ( 'param' | 'p' )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='p') ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1=='a') ) {
                    alt24=1;
                }
                else {
                    alt24=2;}
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1132:2: 'param'
                    {
                    match("param"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1132:12: 'p'
                    {
                    match('p'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PARAM"

    // $ANTLR start "BECOMES"
    public final void mBECOMES() throws RecognitionException {
        try {
            int _type = BECOMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1136:2: ( '=' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1137:2: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BECOMES"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1141:2: ( '<' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1142:2: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1146:2: ( '>' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1147:2: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1151:2: ( '<=' | '\\u2264' )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='<') ) {
                alt25=1;
            }
            else if ( (LA25_0=='\u2264') ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1152:2: '<='
                    {
                    match("<="); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1152:9: '\\u2264'
                    {
                    match('\u2264'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1156:2: ( '>=' | '\\u2265' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='>') ) {
                alt26=1;
            }
            else if ( (LA26_0=='\u2265') ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1157:2: '>='
                    {
                    match(">="); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1157:9: '\\u2265'
                    {
                    match('\u2265'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1161:2: ( '==' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1162:2: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NEQ"
    public final void mNEQ() throws RecognitionException {
        try {
            int _type = NEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1166:2: ( '<>' | '\\u2276' )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0=='<') ) {
                alt27=1;
            }
            else if ( (LA27_0=='\u2276') ) {
                alt27=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1167:2: '<>'
                    {
                    match("<>"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1167:9: '\\u2276'
                    {
                    match('\u2276'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEQ"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1171:2: ( '!' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1172:2: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1176:2: ( '&&' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1177:2: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1181:2: ( '||' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1182:2: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "XOR"
    public final void mXOR() throws RecognitionException {
        try {
            int _type = XOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1186:2: ( '^^' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1187:2: '^^'
            {
            match("^^"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "XOR"

    // $ANTLR start "PATHOP"
    public final void mPATHOP() throws RecognitionException {
        try {
            int _type = PATHOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1191:2: ( 'MOVETO' | 'LINETO' | 'ARCTO' | 'CURVETO' | 'MOVEREL' | 'LINEREL' | 'ARCREL' | 'CURVEREL' | 'CLOSEPOLY' )
            int alt28=9;
            alt28 = dfa28.predict(input);
            switch (alt28) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1192:2: 'MOVETO'
                    {
                    match("MOVETO"); 


                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1194:2: 'LINETO'
                    {
                    match("LINETO"); 


                    }
                    break;
                case 3 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1196:2: 'ARCTO'
                    {
                    match("ARCTO"); 


                    }
                    break;
                case 4 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1198:2: 'CURVETO'
                    {
                    match("CURVETO"); 


                    }
                    break;
                case 5 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1200:2: 'MOVEREL'
                    {
                    match("MOVEREL"); 


                    }
                    break;
                case 6 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1202:2: 'LINEREL'
                    {
                    match("LINEREL"); 


                    }
                    break;
                case 7 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1204:2: 'ARCREL'
                    {
                    match("ARCREL"); 


                    }
                    break;
                case 8 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1206:2: 'CURVEREL'
                    {
                    match("CURVEREL"); 


                    }
                    break;
                case 9 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1208:2: 'CLOSEPOLY'
                    {
                    match("CLOSEPOLY"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PATHOP"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1212:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '\\u0200' .. '\\u0301' | '\\u0303' .. '\\u0377' ) ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0301' | '\\u0303' .. '\\u0377' ) | ( '\\u0302' ( '\\u0200' .. '\\u0260' | '\\u0262' .. '\\u0377' ) ) )* )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1213:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '\\u0200' .. '\\u0301' | '\\u0303' .. '\\u0377' ) ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0301' | '\\u0303' .. '\\u0377' ) | ( '\\u0302' ( '\\u0200' .. '\\u0260' | '\\u0262' .. '\\u0377' ) ) )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0200' && input.LA(1)<='\u0301')||(input.LA(1)>='\u0303' && input.LA(1)<='\u0377') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1213:68: ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0301' | '\\u0303' .. '\\u0377' ) | ( '\\u0302' ( '\\u0200' .. '\\u0260' | '\\u0262' .. '\\u0377' ) ) )*
            loop29:
            do {
                int alt29=3;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>='0' && LA29_0<='9')||(LA29_0>='A' && LA29_0<='Z')||LA29_0=='_'||(LA29_0>='a' && LA29_0<='z')||(LA29_0>='\u0200' && LA29_0<='\u0301')||(LA29_0>='\u0303' && LA29_0<='\u0377')) ) {
                    alt29=1;
                }
                else if ( (LA29_0=='\u0302') ) {
                    alt29=2;
                }


                switch (alt29) {
            	case 1 :
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1213:69: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0301' | '\\u0303' .. '\\u0377' )
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0200' && input.LA(1)<='\u0301')||(input.LA(1)>='\u0303' && input.LA(1)<='\u0377') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;
            	case 2 :
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1213:140: ( '\\u0302' ( '\\u0200' .. '\\u0260' | '\\u0262' .. '\\u0377' ) )
            	    {
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1213:140: ( '\\u0302' ( '\\u0200' .. '\\u0260' | '\\u0262' .. '\\u0377' ) )
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1213:141: '\\u0302' ( '\\u0200' .. '\\u0260' | '\\u0262' .. '\\u0377' )
            	    {
            	    match('\u0302'); 
            	    if ( (input.LA(1)>='\u0200' && input.LA(1)<='\u0260')||(input.LA(1)>='\u0262' && input.LA(1)<='\u0377') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "QSTRING"
    public final void mQSTRING() throws RecognitionException {
        try {
            int _type = QSTRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1217:2: ( '\"' STRING '\"' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1218:2: '\"' STRING '\"'
            {
            match('\"'); 
            mSTRING(); 
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QSTRING"

    // $ANTLR start "FILENAME"
    public final void mFILENAME() throws RecognitionException {
        try {
            int _type = FILENAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1222:6: ( ( 'a' .. 'z' | 'A' .. 'Z' | '\\u0200' .. '\\u0377' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0377' | '.' )* '.cfdg' )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1223:6: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u0200' .. '\\u0377' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0377' | '.' )* '.cfdg'
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0200' && input.LA(1)<='\u0377') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1223:45: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '\\u0200' .. '\\u0377' | '.' )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0=='.') ) {
                    int LA30_1 = input.LA(2);

                    if ( (LA30_1=='c') ) {
                        int LA30_3 = input.LA(3);

                        if ( (LA30_3=='f') ) {
                            int LA30_4 = input.LA(4);

                            if ( (LA30_4=='d') ) {
                                int LA30_5 = input.LA(5);

                                if ( (LA30_5=='g') ) {
                                    int LA30_6 = input.LA(6);

                                    if ( (LA30_6=='.'||(LA30_6>='0' && LA30_6<='9')||(LA30_6>='A' && LA30_6<='Z')||LA30_6=='_'||(LA30_6>='a' && LA30_6<='z')||(LA30_6>='\u0200' && LA30_6<='\u0377')) ) {
                                        alt30=1;
                                    }


                                }
                                else if ( (LA30_5=='.'||(LA30_5>='0' && LA30_5<='9')||(LA30_5>='A' && LA30_5<='Z')||LA30_5=='_'||(LA30_5>='a' && LA30_5<='f')||(LA30_5>='h' && LA30_5<='z')||(LA30_5>='\u0200' && LA30_5<='\u0377')) ) {
                                    alt30=1;
                                }


                            }
                            else if ( (LA30_4=='.'||(LA30_4>='0' && LA30_4<='9')||(LA30_4>='A' && LA30_4<='Z')||LA30_4=='_'||(LA30_4>='a' && LA30_4<='c')||(LA30_4>='e' && LA30_4<='z')||(LA30_4>='\u0200' && LA30_4<='\u0377')) ) {
                                alt30=1;
                            }


                        }
                        else if ( (LA30_3=='.'||(LA30_3>='0' && LA30_3<='9')||(LA30_3>='A' && LA30_3<='Z')||LA30_3=='_'||(LA30_3>='a' && LA30_3<='e')||(LA30_3>='g' && LA30_3<='z')||(LA30_3>='\u0200' && LA30_3<='\u0377')) ) {
                            alt30=1;
                        }


                    }
                    else if ( (LA30_1=='.'||(LA30_1>='0' && LA30_1<='9')||(LA30_1>='A' && LA30_1<='Z')||LA30_1=='_'||(LA30_1>='a' && LA30_1<='b')||(LA30_1>='d' && LA30_1<='z')||(LA30_1>='\u0200' && LA30_1<='\u0377')) ) {
                        alt30=1;
                    }


                }
                else if ( ((LA30_0>='0' && LA30_0<='9')||(LA30_0>='A' && LA30_0<='Z')||LA30_0=='_'||(LA30_0>='a' && LA30_0<='z')||(LA30_0>='\u0200' && LA30_0<='\u0377')) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0200' && input.LA(1)<='\u0377') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            match(".cfdg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FILENAME"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1227:6: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0=='/') ) {
                int LA34_1 = input.LA(2);

                if ( (LA34_1=='/') ) {
                    alt34=1;
                }
                else if ( (LA34_1=='*') ) {
                    alt34=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 34, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:6: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("//"); 

                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:11: (~ ( '\\n' | '\\r' ) )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( ((LA31_0>='\u0000' && LA31_0<='\t')||(LA31_0>='\u000B' && LA31_0<='\f')||(LA31_0>='\u000E' && LA31_0<='\uFFFF')) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:11: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:25: ( '\\r' )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0=='\r') ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:25: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 
                    _channel=HIDDEN;

                    }
                    break;
                case 2 :
                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:57: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 

                    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:62: ( options {greedy=false; } : . )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0=='*') ) {
                            int LA33_1 = input.LA(2);

                            if ( (LA33_1=='/') ) {
                                alt33=2;
                            }
                            else if ( ((LA33_1>='\u0000' && LA33_1<='.')||(LA33_1>='0' && LA33_1<='\uFFFF')) ) {
                                alt33=1;
                            }


                        }
                        else if ( ((LA33_0>='\u0000' && LA33_0<=')')||(LA33_0>='+' && LA33_0<='\uFFFF')) ) {
                            alt33=1;
                        }


                        switch (alt33) {
                    	case 1 :
                    	    // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1228:90: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    match("*/"); 

                    _channel=HIDDEN;

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1232:2: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
            // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1233:2: ( ' ' | '\\t' | '\\r' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:8: ( T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | RATIONAL | RATIONAL2 | STARTSHAPE | BACKGROUND | INCLUDE | TILE | RULE | PATH | SHAPE | LOOP | FINALLY | IF | ELSE | SWITCH | CASE | RANGE | PLUSMINUS | TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH | TRANSFORM | PARAM | BECOMES | LT | GT | LE | GE | EQ | NEQ | NOT | AND | OR | XOR | PATHOP | STRING | QSTRING | FILENAME | COMMENT | WHITESPACE )
        int alt35=75;
        alt35 = dfa35.predict(input);
        switch (alt35) {
            case 1 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:10: T__64
                {
                mT__64(); 

                }
                break;
            case 2 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:16: T__65
                {
                mT__65(); 

                }
                break;
            case 3 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:22: T__66
                {
                mT__66(); 

                }
                break;
            case 4 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:28: T__67
                {
                mT__67(); 

                }
                break;
            case 5 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:34: T__68
                {
                mT__68(); 

                }
                break;
            case 6 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:40: T__69
                {
                mT__69(); 

                }
                break;
            case 7 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:46: T__70
                {
                mT__70(); 

                }
                break;
            case 8 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:52: T__71
                {
                mT__71(); 

                }
                break;
            case 9 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:58: T__72
                {
                mT__72(); 

                }
                break;
            case 10 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:64: T__73
                {
                mT__73(); 

                }
                break;
            case 11 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:70: T__74
                {
                mT__74(); 

                }
                break;
            case 12 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:76: T__75
                {
                mT__75(); 

                }
                break;
            case 13 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:82: T__76
                {
                mT__76(); 

                }
                break;
            case 14 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:88: T__77
                {
                mT__77(); 

                }
                break;
            case 15 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:94: T__78
                {
                mT__78(); 

                }
                break;
            case 16 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:100: RATIONAL
                {
                mRATIONAL(); 

                }
                break;
            case 17 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:109: RATIONAL2
                {
                mRATIONAL2(); 

                }
                break;
            case 18 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:119: STARTSHAPE
                {
                mSTARTSHAPE(); 

                }
                break;
            case 19 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:130: BACKGROUND
                {
                mBACKGROUND(); 

                }
                break;
            case 20 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:141: INCLUDE
                {
                mINCLUDE(); 

                }
                break;
            case 21 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:149: TILE
                {
                mTILE(); 

                }
                break;
            case 22 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:154: RULE
                {
                mRULE(); 

                }
                break;
            case 23 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:159: PATH
                {
                mPATH(); 

                }
                break;
            case 24 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:164: SHAPE
                {
                mSHAPE(); 

                }
                break;
            case 25 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:170: LOOP
                {
                mLOOP(); 

                }
                break;
            case 26 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:175: FINALLY
                {
                mFINALLY(); 

                }
                break;
            case 27 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:183: IF
                {
                mIF(); 

                }
                break;
            case 28 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:186: ELSE
                {
                mELSE(); 

                }
                break;
            case 29 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:191: SWITCH
                {
                mSWITCH(); 

                }
                break;
            case 30 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:198: CASE
                {
                mCASE(); 

                }
                break;
            case 31 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:203: RANGE
                {
                mRANGE(); 

                }
                break;
            case 32 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:209: PLUSMINUS
                {
                mPLUSMINUS(); 

                }
                break;
            case 33 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:219: TIME
                {
                mTIME(); 

                }
                break;
            case 34 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:224: TIMESCALE
                {
                mTIMESCALE(); 

                }
                break;
            case 35 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:234: X
                {
                mX(); 

                }
                break;
            case 36 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:236: Y
                {
                mY(); 

                }
                break;
            case 37 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:238: Z
                {
                mZ(); 

                }
                break;
            case 38 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:240: ROTATE
                {
                mROTATE(); 

                }
                break;
            case 39 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:247: SIZE
                {
                mSIZE(); 

                }
                break;
            case 40 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:252: SKEW
                {
                mSKEW(); 

                }
                break;
            case 41 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:257: FLIP
                {
                mFLIP(); 

                }
                break;
            case 42 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:262: HUE
                {
                mHUE(); 

                }
                break;
            case 43 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:266: SATURATION
                {
                mSATURATION(); 

                }
                break;
            case 44 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:277: BRIGHTNESS
                {
                mBRIGHTNESS(); 

                }
                break;
            case 45 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:288: ALPHA
                {
                mALPHA(); 

                }
                break;
            case 46 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:294: TARGETHUE
                {
                mTARGETHUE(); 

                }
                break;
            case 47 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:304: TARGETSATURATION
                {
                mTARGETSATURATION(); 

                }
                break;
            case 48 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:321: TARGETBRIGHTNESS
                {
                mTARGETBRIGHTNESS(); 

                }
                break;
            case 49 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:338: TARGETALPHA
                {
                mTARGETALPHA(); 

                }
                break;
            case 50 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:350: X1
                {
                mX1(); 

                }
                break;
            case 51 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:353: X2
                {
                mX2(); 

                }
                break;
            case 52 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:356: Y1
                {
                mY1(); 

                }
                break;
            case 53 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:359: Y2
                {
                mY2(); 

                }
                break;
            case 54 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:362: RX
                {
                mRX(); 

                }
                break;
            case 55 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:365: RY
                {
                mRY(); 

                }
                break;
            case 56 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:368: WIDTH
                {
                mWIDTH(); 

                }
                break;
            case 57 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:374: TRANSFORM
                {
                mTRANSFORM(); 

                }
                break;
            case 58 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:384: PARAM
                {
                mPARAM(); 

                }
                break;
            case 59 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:390: BECOMES
                {
                mBECOMES(); 

                }
                break;
            case 60 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:398: LT
                {
                mLT(); 

                }
                break;
            case 61 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:401: GT
                {
                mGT(); 

                }
                break;
            case 62 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:404: LE
                {
                mLE(); 

                }
                break;
            case 63 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:407: GE
                {
                mGE(); 

                }
                break;
            case 64 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:410: EQ
                {
                mEQ(); 

                }
                break;
            case 65 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:413: NEQ
                {
                mNEQ(); 

                }
                break;
            case 66 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:417: NOT
                {
                mNOT(); 

                }
                break;
            case 67 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:421: AND
                {
                mAND(); 

                }
                break;
            case 68 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:425: OR
                {
                mOR(); 

                }
                break;
            case 69 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:428: XOR
                {
                mXOR(); 

                }
                break;
            case 70 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:432: PATHOP
                {
                mPATHOP(); 

                }
                break;
            case 71 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:439: STRING
                {
                mSTRING(); 

                }
                break;
            case 72 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:446: QSTRING
                {
                mQSTRING(); 

                }
                break;
            case 73 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:454: FILENAME
                {
                mFILENAME(); 

                }
                break;
            case 74 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:463: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 75 :
                // /Users/andrea/Documents/progetti/jame/bundles/main/net.sf.jame.contextfree/src/net/sf/jame/contextfree/parser/CFDG.g:1:471: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA28 dfa28 = new DFA28(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA8_eotS =
        "\1\uffff\1\4\3\uffff";
    static final String DFA8_eofS =
        "\5\uffff";
    static final String DFA8_minS =
        "\2\56\3\uffff";
    static final String DFA8_maxS =
        "\2\71\3\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA8_specialS =
        "\5\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "920:1: RATIONAL : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( '%' )? | '.' ( '0' .. '9' )+ ( '%' )? | ( '0' .. '9' )+ ( '%' )? );";
        }
    }
    static final String DFA28_eotS =
        "\32\uffff";
    static final String DFA28_eofS =
        "\32\uffff";
    static final String DFA28_minS =
        "\1\101\1\117\1\111\1\122\1\114\1\126\1\116\1\103\1\122\1\uffff\2"+
        "\105\1\122\1\126\2\122\2\uffff\1\105\4\uffff\1\122\2\uffff";
    static final String DFA28_maxS =
        "\1\115\1\117\1\111\1\122\1\125\1\126\1\116\1\103\1\122\1\uffff\2"+
        "\105\1\124\1\126\2\124\2\uffff\1\105\4\uffff\1\124\2\uffff";
    static final String DFA28_acceptS =
        "\11\uffff\1\11\6\uffff\1\3\1\7\1\uffff\1\1\1\5\1\2\1\6\1\uffff\1"+
        "\4\1\10";
    static final String DFA28_specialS =
        "\32\uffff}>";
    static final String[] DFA28_transitionS = {
            "\1\3\1\uffff\1\4\10\uffff\1\2\1\1",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\11\10\uffff\1\10",
            "\1\12",
            "\1\13",
            "\1\14",
            "\1\15",
            "",
            "\1\16",
            "\1\17",
            "\1\21\1\uffff\1\20",
            "\1\22",
            "\1\24\1\uffff\1\23",
            "\1\26\1\uffff\1\25",
            "",
            "",
            "\1\27",
            "",
            "",
            "",
            "",
            "\1\31\1\uffff\1\30",
            "",
            ""
    };

    static final short[] DFA28_eot = DFA.unpackEncodedString(DFA28_eotS);
    static final short[] DFA28_eof = DFA.unpackEncodedString(DFA28_eofS);
    static final char[] DFA28_min = DFA.unpackEncodedStringToUnsignedChars(DFA28_minS);
    static final char[] DFA28_max = DFA.unpackEncodedStringToUnsignedChars(DFA28_maxS);
    static final short[] DFA28_accept = DFA.unpackEncodedString(DFA28_acceptS);
    static final short[] DFA28_special = DFA.unpackEncodedString(DFA28_specialS);
    static final short[][] DFA28_transition;

    static {
        int numStates = DFA28_transitionS.length;
        DFA28_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA28_transition[i] = DFA.unpackEncodedString(DFA28_transitionS[i]);
        }
    }

    class DFA28 extends DFA {

        public DFA28(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 28;
            this.eot = DFA28_eot;
            this.eof = DFA28_eof;
            this.min = DFA28_min;
            this.max = DFA28_max;
            this.accept = DFA28_accept;
            this.special = DFA28_special;
            this.transition = DFA28_transition;
        }
        public String getDescription() {
            return "1190:1: PATHOP : ( 'MOVETO' | 'LINETO' | 'ARCTO' | 'CURVETO' | 'MOVEREL' | 'LINEREL' | 'ARCREL' | 'CURVEREL' | 'CLOSEPOLY' );";
        }
    }
    static final String DFA35_eotS =
        "\13\uffff\1\71\1\uffff\1\72\1\74\1\76\1\101\1\uffff\1\111\1\116"+
        "\2\61\1\127\1\131\1\61\1\135\2\61\1\uffff\1\142\1\145\1\146\1\150"+
        "\1\152\1\61\1\155\1\156\1\157\5\uffff\5\61\17\uffff\2\101\2\uffff"+
        "\6\61\1\uffff\1\61\1\uffff\2\61\1\uffff\1\61\1\u0080\4\61\1\u0086"+
        "\1\u0087\1\uffff\1\61\1\uffff\3\61\1\uffff\2\61\1\u008f\1\u0090"+
        "\1\uffff\1\u0091\1\u0092\2\uffff\1\61\1\uffff\1\61\1\uffff\1\61"+
        "\4\uffff\5\61\1\uffff\5\61\1\u00a1\4\61\1\uffff\5\61\2\uffff\7\61"+
        "\4\uffff\1\150\12\61\1\111\1\u00bc\1\61\1\uffff\3\61\1\u00c1\1\u00c3"+
        "\1\61\1\u00c5\1\61\1\u00c7\1\61\1\u00c9\1\61\1\135\1\u00cb\1\u00cc"+
        "\11\61\1\u00d8\1\61\1\uffff\4\61\1\uffff\1\61\1\uffff\1\u00e0\1"+
        "\uffff\1\61\1\uffff\1\131\1\uffff\1\61\2\uffff\1\152\1\u00e3\4\61"+
        "\1\u00e8\4\61\1\uffff\1\u00ee\6\61\1\uffff\1\127\1\61\1\uffff\1"+
        "\u00e8\1\61\1\u00e8\1\61\1\uffff\1\u00e8\4\61\1\uffff\3\61\1\u00ff"+
        "\2\61\1\u0102\3\u00e8\6\61\1\uffff\2\61\1\uffff\1\u00e8\5\61\1\u0110"+
        "\1\u00e0\1\u00e8\1\u0111\1\u00a1\1\u0112\1\116\3\uffff";
    static final String DFA35_eofS =
        "\u0113\uffff";
    static final String DFA35_minS =
        "\1\11\12\uffff\1\141\1\uffff\1\57\1\52\1\136\1\45\13\56\1\uffff"+
        "\6\56\3\75\5\uffff\5\56\17\uffff\2\56\2\uffff\6\56\1\uffff\4\56"+
        "\1\uffff\10\56\1\uffff\1\56\1\uffff\3\56\1\uffff\4\56\1\uffff\2"+
        "\56\2\uffff\1\56\1\uffff\1\56\1\uffff\1\56\4\uffff\5\56\1\uffff"+
        "\12\56\1\uffff\5\56\2\uffff\7\56\4\uffff\16\56\1\uffff\32\56\1\uffff"+
        "\4\56\1\uffff\1\56\1\uffff\1\56\1\uffff\1\56\1\uffff\1\56\1\uffff"+
        "\1\56\2\uffff\13\56\1\uffff\7\56\1\uffff\2\56\1\uffff\4\56\1\uffff"+
        "\5\56\1\uffff\20\56\1\uffff\2\56\1\uffff\15\56\3\uffff";
    static final String DFA35_maxS =
        "\1\u2276\12\uffff\1\174\1\uffff\2\57\1\136\2\71\12\u0377\1\uffff"+
        "\6\u0377\1\75\1\76\1\75\5\uffff\5\u0377\17\uffff\2\56\2\uffff\6"+
        "\u0377\1\uffff\4\u0377\1\uffff\10\u0377\1\uffff\1\u0377\1\uffff"+
        "\3\u0377\1\uffff\4\u0377\1\uffff\2\u0377\2\uffff\1\u0377\1\uffff"+
        "\1\u0377\1\uffff\1\u0377\4\uffff\5\u0377\1\uffff\12\u0377\1\uffff"+
        "\5\u0377\2\uffff\7\u0377\4\uffff\16\u0377\1\uffff\32\u0377\1\uffff"+
        "\4\u0377\1\uffff\1\u0377\1\uffff\1\u0377\1\uffff\1\u0377\1\uffff"+
        "\1\u0377\1\uffff\1\u0377\2\uffff\13\u0377\1\uffff\7\u0377\1\uffff"+
        "\2\u0377\1\uffff\4\u0377\1\uffff\5\u0377\1\uffff\20\u0377\1\uffff"+
        "\2\u0377\1\uffff\15\u0377\3\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\14"+
        "\17\uffff\1\40\11\uffff\1\76\1\77\1\101\1\102\1\103\5\uffff\1\110"+
        "\1\107\1\111\1\113\1\56\1\57\1\60\1\61\1\104\1\13\1\15\1\112\1\16"+
        "\1\105\1\17\2\uffff\1\20\1\37\6\uffff\1\47\4\uffff\1\54\10\uffff"+
        "\1\46\1\uffff\1\72\3\uffff\1\51\4\uffff\1\43\2\uffff\1\44\1\45\1"+
        "\uffff\1\52\1\uffff\1\55\1\uffff\1\100\1\73\1\74\1\75\5\uffff\1"+
        "\21\12\uffff\1\33\5\uffff\1\66\1\67\7\uffff\1\62\1\63\1\64\1\65"+
        "\16\uffff\1\53\32\uffff\1\50\4\uffff\1\25\1\uffff\1\41\1\uffff\1"+
        "\26\1\uffff\1\27\1\uffff\1\31\1\uffff\1\34\1\36\13\uffff\1\30\7"+
        "\uffff\1\71\2\uffff\1\70\4\uffff\1\106\5\uffff\1\35\20\uffff\1\24"+
        "\2\uffff\1\32\15\uffff\1\42\1\22\1\23";
    static final String DFA35_specialS =
        "\u0113\uffff}>";
    static final String[] DFA35_transitionS = {
            "\2\63\2\uffff\1\63\22\uffff\1\63\1\51\1\60\3\uffff\1\52\1\uffff"+
            "\1\4\1\5\1\6\1\15\1\3\1\14\1\21\1\16\12\20\1\7\1\uffff\1\44"+
            "\1\43\1\45\2\uffff\1\55\1\57\1\56\10\57\1\54\1\53\15\57\1\10"+
            "\1\uffff\1\11\1\17\1\61\1\uffff\1\41\1\23\1\33\1\57\1\32\1\31"+
            "\1\57\1\40\1\24\2\57\1\30\3\57\1\27\1\57\1\26\1\22\1\25\2\57"+
            "\1\42\1\35\1\36\1\37\1\1\1\13\1\2\1\12\62\uffff\1\34\u014e\uffff"+
            "\u0102\57\1\62\165\57\u1eec\uffff\1\46\1\47\20\uffff\1\50",
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
            "\1\67\1\66\5\uffff\1\64\12\uffff\1\65\10\uffff\1\70",
            "",
            "\1\34",
            "\1\73\4\uffff\1\73",
            "\1\75",
            "\1\100\10\uffff\1\77\1\uffff\12\20",
            "\1\102\1\uffff\12\101",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\110\6\112\1\104\1\106\1\112\1\107\10\112\1\103\2\112\1\105"+
            "\3\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\114\20\112\1\115\10\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\5"+
            "\112\1\120\7\112\1\117\14\112\u0185\uffff\u0102\112\1\113\165"+
            "\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\121\10\112\1\122\10\112\u0185\uffff\u0102\112\1\113\165"+
            "\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\16"+
            "\112\1\124\5\112\1\123\2\112\1\125\1\126\1\112\u0185\uffff\u0102"+
            "\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\130\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\16"+
            "\112\1\132\13\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\133\2\112\1\134\16\112\u0185\uffff\u0102\112\1\113\165"+
            "\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\136\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\137\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\1\112\1\140\1\141\7\112\7\uffff\32\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\1\112\1\143\1\144\7\112\7\uffff\32\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\24"+
            "\112\1\147\5\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\151\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\153\21\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\154",
            "\1\46\1\50",
            "\1\47",
            "",
            "",
            "",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\160\13\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\10\112\1\161\21\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\21\112\1\162\10\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\13\112\1\164\10\112\1\163\5\112"+
            "\4\uffff\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165"+
            "\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
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
            "\1\165",
            "\1\165",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\166\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\167\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\170\21\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\31"+
            "\112\1\171\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\172\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\173\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\62\7\uffff\32\62\4\uffff\1\62\1\uffff\32\62"+
            "\u0185\uffff\141\174\1\62\u0116\174",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\2"+
            "\112\1\175\27\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\176\21\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\2"+
            "\112\1\177\27\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\u0081\1\u0082\15\112\u0185\uffff\u0102\112\1\113\165"+
            "\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u0083\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\u0084\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u0085\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\21"+
            "\112\1\u0089\1\112\1\u0088\6\112\u0185\uffff\u0102\112\1\113"+
            "\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\16"+
            "\112\1\u008a\13\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\15"+
            "\112\1\u008b\14\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\u008c\21\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u008d\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u008e\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u0093\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\17"+
            "\112\1\u0094\12\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\3"+
            "\112\1\u0095\26\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\25\112\1\u0096\4\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\15\112\1\u0097\14\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\2\112\1\u0098\27\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\21\112\1\u0099\10\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\u009a\13\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\21"+
            "\112\1\u009b\10\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\17"+
            "\112\1\u009c\12\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u009d\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u009e\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\26"+
            "\112\1\u009f\3\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\24"+
            "\112\1\u00a0\5\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\12"+
            "\112\1\u00a2\17\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\6"+
            "\112\1\u00a3\23\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\u00a4\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00a5\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00a6\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\15"+
            "\112\1\u00a7\14\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00a8\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00a9\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\7"+
            "\112\1\u00aa\22\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00ab\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\17"+
            "\112\1\u00ac\12\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00ad\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\17"+
            "\112\1\u00ae\12\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00af\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00b0\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\7"+
            "\112\1\u00b1\22\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u00b2\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00b3\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00b4\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\21\112\1\u00b6\1\112\1\u00b5\6"+
            "\112\4\uffff\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113"+
            "\165\112",
            "\1\62\1\uffff\12\112\7\uffff\25\112\1\u00b7\4\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\22\112\1\u00b8\7\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u00b9\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00ba\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\2"+
            "\112\1\u00bb\27\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\21"+
            "\112\1\u00bd\10\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\6"+
            "\112\1\u00be\23\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\7"+
            "\112\1\u00bf\22\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\24"+
            "\112\1\u00c0\5\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u00c2\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u00c4\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u00c6\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\14"+
            "\112\1\u00c8\15\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\u00ca\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00cd\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\7"+
            "\112\1\u00ce\22\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\21\112\1\u00d0\1\112\1\u00cf\6"+
            "\112\4\uffff\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113"+
            "\165\112",
            "\1\62\1\uffff\12\112\7\uffff\21\112\1\u00d2\1\112\1\u00d1\6"+
            "\112\4\uffff\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113"+
            "\165\112",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\u00d3\13\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00d4\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00d5\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00d6\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u00d7\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\7"+
            "\112\1\u00d9\22\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00da\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\21"+
            "\112\1\u00db\10\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u00dc\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\3"+
            "\112\1\u00dd\26\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\2"+
            "\112\1\u00de\27\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\5"+
            "\112\1\u00df\24\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00e1\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\u00e2\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\u00e4\13\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00e5\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\u00e6\13\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00e7\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\13\112\1\u00e9\16\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\21\112\1\u00eb\1\112\1\u00ea\6"+
            "\112\4\uffff\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113"+
            "\165\112",
            "\1\62\1\uffff\12\112\7\uffff\17\112\1\u00ec\12\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\7"+
            "\112\1\u00ed\22\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\23"+
            "\112\1\u00ef\6\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\16"+
            "\112\1\u00f0\13\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\15"+
            "\112\1\u00f1\14\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00f2\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00f3\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\16"+
            "\112\1\u00f4\13\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\30"+
            "\112\1\u00f5\1\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\13\112\1\u00f6\16\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\13\112\1\u00f7\16\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\u00f8\13\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\4\112\1\u00f9\25\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\16\112\1\u00fa\13\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\1"+
            "\u00fb\31\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\10"+
            "\112\1\u00fc\21\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\24"+
            "\112\1\u00fd\5\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u00fe\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\13"+
            "\112\1\u0100\16\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\21"+
            "\112\1\u0101\10\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\13\112\1\u0103\16\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\13\112\1\u0104\16\112\4\uffff"+
            "\1\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\17"+
            "\112\1\u0105\12\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\16"+
            "\112\1\u0106\13\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\15"+
            "\112\1\u0107\14\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u0108\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u0109\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\14"+
            "\112\1\u010a\15\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\30\112\1\u010b\1\112\4\uffff\1"+
            "\112\1\uffff\32\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\4"+
            "\112\1\u010c\25\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\15"+
            "\112\1\u010d\14\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\3"+
            "\112\1\u010e\26\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\22"+
            "\112\1\u010f\7\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "\1\62\1\uffff\12\112\7\uffff\32\112\4\uffff\1\112\1\uffff\32"+
            "\112\u0185\uffff\u0102\112\1\113\165\112",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | RATIONAL | RATIONAL2 | STARTSHAPE | BACKGROUND | INCLUDE | TILE | RULE | PATH | SHAPE | LOOP | FINALLY | IF | ELSE | SWITCH | CASE | RANGE | PLUSMINUS | TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH | TRANSFORM | PARAM | BECOMES | LT | GT | LE | GE | EQ | NEQ | NOT | AND | OR | XOR | PATHOP | STRING | QSTRING | FILENAME | COMMENT | WHITESPACE );";
        }
    }
 

}