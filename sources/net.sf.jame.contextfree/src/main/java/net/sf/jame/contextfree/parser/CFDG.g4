grammar CFDG;

options
{
} 

@lexer::header { 
}

@parser::header {
}

@members {
	Builder driver = new Builder();
}

choose
		:
		CFDG2 cfdg2 
		|
		CFDG3 cfdg3
		;
		 
cfdg2
        :
        cfdg2 r=statement_v2 {
	        if ($r.result != null) {
	          	driver.pushRep($r.result, true);
	        }
        } 
        |
        ;
        
cfdg3
        :
        cfdg3 r=statement_v3 {
	        if ($r.result != null) {
	          	driver.pushRep($r.result, true);
	        }
        }
        |
        ;
        
statement_v2 returns [ASTReplacement result]
        : 
        initialization_v2
        | directive_v2  
        | inclusion_v2 { 
        	$result = null;
        }
        | eof {
        	$result = null;        	
        }
        | rule_v2
        | path_v2
        | v3clues { 
        	if (driver.getMaybeVersion().equals("CFDG2")) {
        		driver.error("Illegal mixture of old and new elements");	        		
        	} else {
        		driver.setMaybeVersion("CFDG3");
        	}
        	$result = null;
        }
        ;
        
statement_v3 returns [ASTReplacement result]
        : 
        initialization_v3
        | import_v3 { 
        	$result = null;
        }
        | eof {
        	$result = null;        	
        }
        | rule_v3
        | path
        | r=shape { 
        	$result = null;
        }
        | shape_singleton
        | r=global_definition { 
        	$result = $r.result;
        }
        | v2stuff {
        	driver.error("Illegal mixture of old and new elements");	
        	$result = null;
        }
        ;
        
v3clues
		:
        USER_STRING BECOMES
        | MODTYPE BECOMES
        | PARAM BECOMES
        | USER_STRING '('
        | USER_STRING USER_STRING '('
        | IMPORT
        | SHAPE
        | PATH USER_STRING '('
        | STARTSHAPE USER_STRING '('
        | STARTSHAPE USER_STRING '['
        | STARTSHAPE USER_ARRAYNAME '['
        ;
        
v2stuff:
        BACKGROUND modification_v2
        | TILE modification_v2
        | MODTYPE modification_v2
        | INCLUDE fileString
        | rule_header_v2
        ;

inclusion_v2 
        : 
        INCLUDE f=USER_QSTRING {
        	driver.setShape(null);
        	driver.includeFile($f.getText());
        }
        |
        INCLUDE f=USER_FILENAME {
        	driver.setShape(null);
        	driver.includeFile($f.getText());
        }
        ;

import_v3
        : 
        IMPORT nm=fileNameSpace f=fileString {
            driver.setShape(null);
            driver.includeFile($f.result);
            if ($nm.result != null) {
                driver.pushNameSpace(nm, $nm.result);
            }
        }
        ;

eof 
		:
		EOF {
			if (driver.endInclude()) {
			}
		}
		;
		
fileString returns [String result]
		:
        f=USER_FILENAME {
        	$result = $f.getText();
        } 
        | 
        f=USER_QSTRING {
        	$result = $f.getText();
        } 
       	;
		
fileNameSpace returns [String result]
		:
        '@' r=USER_STRING { 
        	$result = $r.getText();
        }
        | { 
        	$result = null;
        }
        ;

initialization_v3 returns [ASTDefine result]
        : 
        STARTSHAPE s=USER_STRING p=parameter_spec m=modification {
        	String name = $s.getText();
        	ASTExpression p = $p.result;
        	ASTExpression mod = $m.result;
        	driver.setShape(null);
        	ASTDefine cfg = driver.makeDefinition(CFGParam.StartShape.getName(), false);
        	if (cfg != null) {
        		cfg.setExp(driver.makeRuleSpec(name, p, mod, true));
        	}
        	$result = cfg;
        }
        |
        STARTSHAPE s=USER_ARRAYNAME m=modification {
        	String name = $s.getText();
        	ASTExpression mod = $m.result;
        	driver.setShape(null);
        	ASTDefine cfg = driver.makeDefinition(CFGParam.StartShape.getName(), false);
        	if (cfg != null) {
        		cfg.setExp(driver.makeRuleSpec(name, null, mod, true));
        	}
        	$result = cfg;
        }
        |
        STARTSHAPE s=USER_STRING p=parameter_spec {
        	String name = $s.getText();
        	ASTExpression parameter = $p.result;
        	driver.setShape(null);
        	ASTDefine cfg = driver.makeDefinition(CFGParam.StartShape.getName(), false);
        	if (cfg != null) {
        		cfg.setExp(driver.makeRuleSpec(name, p, null, true));
        	}
        	$result = cfg;
        }
        ;

initialization_v2 returns [ASTDefine result]
        : 
        STARTSHAPE s=USER_STRING {
        	String name = $s.getText();
        	driver.setShape(null);
        	ASTDefine cfg = driver.makeDefinition(CFGParam.StartShape.getName(), false);
        	if (cfg != null) {
        		cfg.setExp(driver.makeRuleSpec(name, null, null, true));
        	}
        	$result = cfg;
        }
        ;

directive_v2 returns [ASTDefine result]
		:
        s=directive_string m=modification_v2 {
        	ASTModification mod = $m.result; 
            ASTDefine cfg = driver.makeDefinition($s.result, false);
            if (cfg != null) {
                cfg.setExp(mod);
            }
            driver.setMaybeVersion("CFDG2");
        	$result = cfg;
        }
        ;

directive_string returns [String result]
		:
        BACKGROUND { 
        	$result = CFGParam.Background.getName();
        }
        |
        TILE { 
        	$result = CFGParam.Tile.getName();
        }
        |
        t=MODTYPE {
            switch ($t.getText()) {
	            case ModTypeEnum.size.name():
	                $result = CFGParam.Tile.getName();
	                break;
	            case ModTypeEnum.time.name():
	                $result = CFGParam.Time.getName();
	                break;
	            default:
	                $result = CFGParam.Size.getName();
	                driver.error("Syntax error");
	                break;
            }
        }
        ;
        
shape
        : 
        SHAPE s=USER_STRING parameter_list {
        	String name = $s.getText(); 
			driver.setShape(name);
        }
        ;

shape_singleton_header returns [ASTRule result]
        : 
        shape '{' {
        	driver.inPathContainer = false;
        	$result = new ASTRule(-1);
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        }
        ; 

shape_singleton returns [ASTRule result]
        :
        s=shape_singleton_header buncha_elements '}' {
        	$result = $s.result;
        	driver.popRepContainer($result.getRuleBody());
        }
        ; 

rule_header_v2 returns [ASTRule result]
        : 
        RULE s=USER_STRING {
        	String name = $s.getText();
        	driver.setShape(null);
        	$result = new ASTRule(driver.stringToShape(name, false));
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        }
        |
        RULE s=USER_STRING w=USER_RATIONAL {
        	String name = $s.getText();
        	String weight = $w.getText();
        	driver.setShape(null);
        	$result = new ASTRule(driver.stringToShape(name), Float.parseFloat(weight), weight.indexOf("\u0025") != -1);
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        }
        ;

rule_v2 returns [ASTRule result]
        : 
        h=rule_header_v2 '{' buncha_replacements_v2 '}' {
            driver.setMaybeVersion("CFDG2");
        	$result = $h.result;
        	driver.popRepContainer($h.result);
        }
        ;

rule_header returns [ASTRule result]
        : 
        RULE {
        	driver.inPathContainer = false;
        	$result = new ASTRule(-1);
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        }
        |
        RULE w=USER_RATIONAL {
        	driver.inPathContainer = false;
        	$result = new ASTRule(-1, Float.parseFloat(weight), weight.indexOf("\u0025") != -1);
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        }
        ;

path_header returns [ASTRule result]
        : 
        PATH s=USER_STRING parameter_list {
        	String name = $s.getText();
        	driver.setShape(null);
        	driver.inPathContainer = true;
        	$result = new ASTRule(-1);
        	$result.setPath(true);
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        }
        ;

rule_v3 returns [ASTRule result]
        : 
        h=rule_header '{' buncha_elements '}' {
        	$result = $h.result;
        	driver.popRepContainer($result);
        	driver.inPathContainer = false;
        }
        ;

path returns [ASTRule result]
        : 
        h=path_header '{' buncha_elements '}' {
        	$result = $h.result;
        	driver.popRepContainer($result);
        	driver.inPathContainer = false;
        	driver.setShape(null);
        }
        ;
        
path_header_v2 returns [ASTRule result]
        : 
        PATH s=USER_STRING {
        	String name = $s.getText();
        	driver.setShape(null);
        	$result = new ASTRule(driver.stringToShape(name), false);
        	$result.setPath(true);
        	driver.addRule($result);
        	driver.pushRepContainer($result.getRuleBody());
        	driver.inPathContainer = true;
        }
        ;

path_v2 returns [ASTRule result]
		:
        r=path_header_v2 '{' buncha_pathOps_v2 '}' {
            $result = $r.result;
            driver.popRepContainer($result);
        }
        ;

parameter
       : 
       t=USER_STRING v=USER_STRING {
			String type = $t.getText();
			String var = $v.getText();
			driver.nextParameterDecl(type, var);
        }
        |
        SHAPE v=USER_STRING {
			String var = $v.getText();
			driver.nextParameterDecl("shape", var);
        }
        |
        v=USER_STRING MODTYPE {
        	error("Reserved keyword: adjustment");
        }
        |
        SHAPE MODTYPE {
        	error("Reserved keyword: adjustment");
        }
        |
        v=USER_STRING {
			String var = $v.getText();
			driver.nextParameterDecl("number", var);
        }
        |
        MODTYPE {
        	error("Reserved keyword: adjustment");
        }
        ;

buncha_parameters 
        : 
        buncha_parameters ',' parameter 
        | 
        parameter
        ;

parameter_list
        : 
        '(' buncha_parameters ')' {
        }
        |
        ;

function_parameter_list
		:
        '(' buncha_parameters ')'
        | 
        '(' ')'
        ;

parameter_spec returns [ASTExpression result]
        : 
        '(' a=arglist ')' { 
        	$result = $a.result;
        }
        |
        '(' BECOMES ')' { 
        	$result = new ASTExpression(false, false, ExpType.ReuseType);
        }
        | '(' ')' { 
        	$result = null; 
        }
        | {
        	$result = null;
        }
        ;

buncha_elements 
        : 
        buncha_elements r=element {
        	driver.pushRep($r.result);
        }
        | 
        ;

buncha_pathOps_v2 
        : 
        buncha_pathOps_v2 r=pathOp_v2 {
        	driver.pushRep($r.result);
        }
        | 
        ;




////////////////////////////////////////
pathOp_simple_v2 returns [ASTReplacement result]
        : 
        o=USER_PATHOP '{' a=buncha_pathop_adjustments '}' {
        	String operator = $o.getText();
        	ASTExpression modification = $a.result;
        	$result = new ASTPathOp(operator, modification, false);
        }
        |
        c=USER_STRING m=modification_v2 {
        	String command = $c.getText();
        	ASTExpression modification = $m.result;
        	$result = new ASTPathCommand(command, modification);
        }
        ;

element_simple returns [ASTReplacement result]
        : 
        o=USER_PATHOP '(' e=exp2 ')' {
        	driver.inPathContainer = true;
        	String operator = $o.getText();
        	ASTExpression modification = $e.result;
        	$result = new ASTPathOp(operator, modification, true);
        }
        |
        o=USER_PATHOP '(' ')' {
        	driver.inPathContainer = true;
        	String operator = $o.getText();
        	$result = new ASTPathOp(operator, null, true);
        }
        |
        s=USER_STRING p=parameter_spec m=modification {
        	String command = $s.getText();
        	ASTExpression parameter = $p.result;
        	ASTExpression modification = $m.result;
        	$result = driver.makeElement(command, modification, parameter, false);
        }
        |
        PATH s=USER_STRING p=parameter_spec m=modification {
        	driver.inPathContainer = true;
        	String command = $s.getText();
        	ASTExpression parameter = $p.result;
        	ASTExpression modification = $m.result;
        	$result = driver.makeElement(command, modification, parameter, true);
        }
        ;

one_or_more_elements
        : 
        '{' buncha_elements '}' { }
        |
        r=element_simple {
        	driver.pushRep($r.result, false);
        }
        ;

one_or_more_pathOp_v2
        : 
        '{' buncha_pathOps_v2 '}' { }
        |
        r=pathOp_simple_v2 {
        	driver.pushRep($r.result, false);
        }
        ;

caseBody
        : 
        caseBody_element caseBody 
        |
        ;

caseBody_element
        : 
        caseHeader one_or_more_elements
        ;

element returns [ASTReplacement result]
        : 
        r=element_simple { 
        	$result = $r.result; 
        }
        |
        definition { 
        	$result = null;
        }
        |
        rl=element_loop { 
        	$result = $rl.result; 
        	driver.popRep($rl.result);
        	if ($rl.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        rl=element_loop FINALLY {
        	driver.popRep($rl.result);
        	driver.pushRepContainer(((ASTLoop) $rl.result).getFinallyBody());
        } one_or_more_elements {
        	$result = $rl.result; 
        	driver.popRep($rl.result);
        	if ($rl.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        ri=ifHeader one_or_more_elements {
        	$result = $ri.result; 
        	driver.popRep($ri.result);
        	if ($ri.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        ri=ifHeader one_or_more_elements ELSE {
        	driver.popRep($ri.result);
         	driver.pushRepContainer(((ASTIf)($ri.result)).getElseBody());
        } one_or_more_elements {
        	$result = $ri.result;  
        	driver.popRep($ri.result);
        	if ($ri.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        rt=transHeader one_or_more_elements {
        	$result = $rt.result; 
        	driver.popRep($rt.result);
        	if ($rt.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        rs=switchHeader '{' caseBody '}' {
			$rs.result.unify();
        	$result = $rs.result; 
        	driver.popRep($rs.result);
        	driver.switchStack().pop();
        }
        ;

pathOp_v2 returns [ASTReplacement result]
        : 
        rp=pathOp_simple_v2 { 
        	$result = $rp.result;
        }
        |
        rl=loopHeader_v2 one_or_more_pathOp_v2 { 
        	$result = $rl.result;
			driver.popRep($rl.result);
			if ($rl.result.getRepType().getType() == 0) {
				$result = null;			
			}
        }
        ;

element_loop returns [ASTReplacement result]
        : 
        r=loopHeader one_or_more_elements {
        	$result = $r.result;
        }
        ;

buncha_replacements_v2 
        : 
        r=replacement_v2 buncha_replacements_v2 {
        	driver.pushRep($r.result, false);
        }
        |
        ;

one_or_more_replacements_v2
        : 
        '{' buncha_replacements_v2 '}' { 
        }
        |
        r=replacement_simple_v2 {
        	driver.pushRep($r.result, false);
        }
        ;

replacement_simple_v2 returns [ASTReplacement result]
        : 
        s=USER_STRING m=modification_v2 {
        	String name = $s.getText();
        	ASTExpression modification = $m.result;
        	ASTRuleSpecifier ruleSpecifier = driver.makeRuleSpecifier(name, new ASTExpression());
        	$result = new ASTReplacement(ruleSpecifier, ruleSpecifier.getEntropy().toString(), modification);
        }
        ;

replacement_v2 returns [ASTReplacement result]
        : 
        r=replacement_simple_v2 { 
        	$result = $r.result;
        }
        |
        rl=loopHeader_v2 one_or_more_replacements_v2 {
        	$result = $rl.result;
			driver.popRep($rl.result);
			if ($rl.result.getRepType().getType() == 0) {
	        	$result = null;			
			}
        }
        ;

loopHeader_v2 returns [ASTReplacement result]
        : 
        r=USER_RATIONAL '*' m=modification_v2 {
        	ASTExpression count = new ASTReal(Float.parseFloat($r.getText()));
        	ASTExpression modification = $m.result;
        	ASTLoop loopHeader = new ASTLoop(count, modification);
        	driver.pushRepContainer(loopHeader.getLoopBody());
        	$result = loopHeader;
        }
        |
        '(' c=exp2 ')' '*' m=modification_v2 {
        	ASTExpression count = $c.result;
        	ASTExpression modification = $m.result;
        	ASTLoop loopHeader = new ASTLoop(count, modification);
        	driver.pushRepContainer(loopHeader.getLoopBody());
        	$result = loopHeader;
        }
        ;

loopHeader returns [ASTReplacement result]
        : 
        LOOP v=USER_STRING BECOMES i=exp2 m=modification {
        	String var = $v.getText();
        	ASTExpression index = $i.result;
        	ASTExpression modification = $m.result;
        	ASTLoop loopHeader = new ASTLoop(driver.stringToShape(var), var, index, modification);
        	driver.pushRepContainer(loopHeader.getLoopBody());
        	$result = loopHeader;
        }
        |
        LOOP c=exp2 m=modification {
        	ASTExpression count = $c.result;
        	ASTExpression modification = $m.result;
        	ASTLoop loopHeader = new ASTLoop(count, modification);
        	driver.pushRepContainer(loopHeader.getLoopBody());
        	$result = loopHeader;
        }
        ;

ifHeader returns [ASTReplacement result]
        : 
        IF '(' e=exp2 ')' {
        	ASTExpression exp = $e.result;
        	ASTIf ifHeader = new ASTIf(exp);
        	driver.pushRepContainer(ifHeader.getThenBody());
        	$result = ifHeader;
        }
        ;

transHeader returns [ASTReplacement result]
        : 
        TRANSFORM m=modification {
        	ASTExpression modification = $m.result;
        	ASTTransform transHeader = new ASTTransform(modification);
        	driver.pushRepContainer(transHeader.getBody());
        	$result = transHeader;
        }
        ;

switchHeader returns [ASTReplacement result]
        : 
        SWITCH '(' e=exp2 ')' {
            /*exp_ptr caseVal($3);
            switch_ptr switchHeader(new ASTswitch(caseVal, @3));
            driver.push_repContainer(switchHeader->mElseBody);
            driver.switchStack.push(switchHeader.get());
            $$ = switchHeader.release();*/
        }
        ;

caseHeader
        :  
        CASE e=exp2 ':' {
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
        |
        ELSE ':' {
            /*if (!driver.switchStack.top()->mElseBody.body.empty()) {
                driver.error(@$, "There can only be one 'else:' clause");
            } else {
                driver.pop_repContainer(driver.switchStack.top());
                driver.push_repContainer(driver.switchStack.top()->mElseBody);
            }*/
        }
        ;

modification_v2 returns [ASTExpression result]
        : 
        '{' buncha_canonical_adjustments '}' {
        	ASTExpression modification = new ASTModification(ASTOperator.makeCanonical(driver.canonicalMods));
        	$result = driver.checkModification(modification);
        }
        |
        '[' a=buncha_adjustments ']' {
        	ASTExpression modification = new ASTModification($a.result);
        	$result = driver.checkModification(modification);
        }
        ;

modification returns [ASTExpression result]
        : 
        '~' '{' buncha_canonical_adjustments '}' {
        	ASTExpression modification = new ASTModification(ASTOperator.makeCanonical(driver.canonicalMods));
        	$result = driver.checkModification(modification);
        }
        |
        '{' a=buncha_adjustments '}' {
        	ASTExpression modification = new ASTModification($a.result);
        	$result = driver.checkModification(modification);
        }
        ;

global_modification returns [ASTExpression result]
        : 
        m=modification_v2 {
        	$result = $m.result;
        }
        ;
    
buncha_pathop_adjustments returns [ASTExpression result]
        : 
        a1=adjustment a2=buncha_pathop_adjustments {
        	$result = new ASTCons($a2.result, $a1.result);
        }
        | 
        {
        	$result = null;
        }
        ;

buncha_adjustments returns [ASTExpression result]
        : 
        a1=adjustment a2=buncha_adjustments {
        	$result = new ASTOperator('+', $a1.result, $a2.result);
        }
        | 
        {
			$result = null;
        }
        ;

buncha_canonical_adjustments
        : 
        a=adjustment buncha_canonical_adjustments {
        	$a.result.flatten(driver.canonicalMods);
        }
        | 
        {
			driver.canonicalMods.clear();
        }
        ;

adjustment returns [ASTExpression result]
        : 
        t=(TIME|TIMESCALE|X|Y|Z|ROTATE|SIZE|SKEW|FLIP|HUE|SATURATION|BRIGHTNESS|ALPHA|TARGETHUE|TARGETSATURATION|TARGETBRIGHTNESS|TARGETALPHA|X1|X2|Y1|Y2|RX|RY|WIDTH) ml=explist {
        	ASTExpression modification = $ml.result; 
        	$result = driver.makeModTerm($t.getText(), modification, false);
        }
        |
        t=(HUE|SATURATION|BRIGHTNESS|ALPHA) m=exp '|' {
        	ASTExpression modification = $m.result; 
        	$result = driver.makeModTerm($t.getText(), modification, true);
        }
        |
        PARAM p=USER_STRING {
        	$result = driver.makeModTermParam($p.getText());
        }
        |
        PARAM p=USER_QSTRING {
        	$result = driver.makeModTermParam($p.getText());
        }
        ;
        
explist returns [ASTExpression result]
        : 
        e2=explist e1=exp {
        	$result = $e2.result.append($e1.result);
        }
        | 
        e=exp { 
        	$result = $e.result;
        }
        ;

arglist returns [ASTExpression result]
        : 
        e2=explist e1=exp {
        	$result = $e2.result.append($e1.result);
        }
        | 
        e=exp { 
        	$result = new ASTCons(new ASTParen($e.result));
        }
        ;

exp returns [ASTExpression result]
        : 
        (
        r=USER_RATIONAL { 
			$result = new ASTReal(Float.parseFloat($r.getText())); 
        }
        |
        '-' r=USER_RATIONAL {
			$result = new ASTReal(Float.parseFloat($r.getText()), true); 
        }
        |
        '+' r=USER_RATIONAL { 
			$result = new ASTReal(Float.parseFloat($r.getText())); 
        }
        |
        '(' e=exp2 ')' { 
			$result = new ASTParen($e.result); 
        }
        | 
        f=expfunc { 
			$result = $f.result; 
        }
        |
        '-' f=expfunc { 
			$result = new ASTOperator('N', $f.result); 
        }
        |
        '+' f=expfunc { 
			$result = new ASTOperator('P', $f.result); 
        }
        )
        (
        PLUSMINUS e2=exp {
        	ASTExpression pair = new ASTCons($result, $e2.result);
        	$result = new ASTFunction("rand+/-", pair, driver.seed);
        }
        |
        RANGE e2=exp {
        	ASTExpression pair = new ASTCons($result, $e2.result);
        	$result = new ASTFunction("rand", pair, driver.seed);
        }
        )?
        ;

exp2 returns [ASTExpression result]	
        : 
        (
        r=USER_RATIONAL { 
        	$result = new ASTReal(Float.parseFloat($r.getText())); 
        }
        |
        f=expfunc { 
        	$result = $f.result;
        }
        |
        '(' e=exp2 ')' { 
			$result = new ASTParen($e.result); 
        }
        | 
        '-' f=expfunc { 
			$result = new ASTOperator('N', $f.result); 
        }
        |
        '+' f=expfunc { 
			$result = new ASTOperator('P', $f.result); 
        }
        |
        NOT e=exp2 { 
			$result = new ASTOperator('!', $e.result); 
        }
        |
        m=modification {
        	$result = $m.result;
        }
        )
        (
        ',' e2=exp2 {
        	$result = new ASTCons($result, $e2.result);
        }
        |
        '+' e2=exp2 {
        	$result = new ASTOperator('+', $result, $e2.result);
        }
        |
        '-' e2=exp2 {
        	$result = new ASTOperator('-', $result, $e2.result);
        }
        |
        '*' e2=exp2 {
        	$result = new ASTOperator('*', $result, $e2.result);
        }
        |
        '/' e2=exp2 {
        	$result = new ASTOperator('/', $result, $e2.result);
        }
        |
        '^' e2=exp2 {
        	$result = new ASTOperator('^', $result, $e2.result);
        }
        |
        LT e2=exp2 {
        	$result = new ASTOperator('<', $result, $e2.result);
        }
        |
        GT e2=exp2 {
        	$result = new ASTOperator('>', $result, $e2.result);
        }
        |
        LE e2=exp2 {
        	$result = new ASTOperator('L', $result, $e2.result);
        }
        |
        GE e2=exp2 {
        	$result = new ASTOperator('G', $result, $e2.result);
        }
        |
        AND e2=exp2 {
        	$result = new ASTOperator('&', $result, $e2.result);
        }
        |
        OR e2=exp2 {
        	$result = new ASTOperator('|', $result, $e2.result);
        }
        |
        XOR e2=exp2 {
        	$result = new ASTOperator('X', $result, $e2.result);
        }
        |
        EQ e2=exp2 {
        	$result = new ASTOperator('=', $result, $e2.result);
        }
        |
        NEQ e2=exp2 {
        	$result = new ASTOperator('n', $result, $e2.result);
        }
        |
        PLUSMINUS e2=exp2 {
        	ASTExpression pair = new ASTCons($result, $e2.result);
        	$result = new ASTFunction("rand+/-", pair, driver.seed);
        }
        |
        RANGE e2=exp2 {
        	ASTExpression pair = new ASTCons($result, $e2.result);
        	$result = new ASTFunction("rand", pair, driver.seed);
        }
        )?
        ;

expfunc returns [ASTExpression result]
        : 
        f=USER_STRING '(' ')' { 
        	String function = $f.getText();
        	$result = driver.makeFunction(function, new ASTExpression());
        }
        | 
        f=USER_STRING '(' e=exp2 ')' { 
        	String function = $f.getText();
        	ASTExpression arguments = $e.result;
        	$result = driver.makeFunction(function, arguments);
        }
        |
        v=USER_STRING { 
        	String var = $v.getText();
        	$result = driver.makeVariable(var);
        }
        ;

exp2func returns [ASTExpression result]
        : 
        f=USER_STRING '(' ')' { 
        	String function = $f.getText();
        	$result = driver.makeFunction(function, new ASTExpression());
        }
        | 
        f=USER_STRING '(' e=exp2 ')' { 
        	String function = $f.getText();
        	ASTExpression arguments = $e.result;
        	$result = driver.makeFunction(function, arguments);
        }
        |
        v=USER_STRING { 
        	String var = $v.getText();
        	$result = driver.makeVariable(var);
        }
        ;
        
global_definition returns [ASTDefine result]
		:
        r=global_definition_header e=exp2 {
            ASTDefine var = $r.result;
            ASTExpression exp = $e.result;
            if (var != null) {
                switch (var.getDefineType()) {
                    case DefineType.StackDefine:
                        if (exp instanceOf ASTModification)
                            var-getChildChange().grab(mod); // emptied ASTmod gets deleted
                        else
                            var.setExpression(exp);
                        break;
                    case DefineType.LetDefine:
                        assert(false);
                        break;
                    case DefineType.FunctionDefine:
                        driver.popRepContainer(null);
                        driver.paramDecls.getParameters().clear();
                        driver.paramDecls.setStackCount(0);
                        // fall through
                    default:
                        var.setExpression(exp);
                        break;
                }
                $result = var;
            } else {
                $result = null;
            }
        }
        ;

function_definition_header returns [ASTDefine result]
		:
        SHAPE f=USER_STRING p=function_parameter_list BECOMES {
        	String name = $f.getText();
            $result = driver.makeDefinition(name, true);
            if ($result) {
                $result.setExpType(ExpType.RuleType);
                $result.setTupleSize(1);
            }
        }
        |
        f=USER_STRING p=function_parameter_list BECOMES {
        	String name = $f.getText();
            $result = driver.makeDefinition(name, true);
            if ($result) {
                $result.setExpType(ExpType.NumericType);
                $result.setTupleSize(1);
            }
        }
        |
        t=USER_STRING f=USER_STRING p=function_parameter_list BECOMES {
        	String name = $f.getText();
        	String type = $f.getText();
            $result = driver.makeDefinition(name, true);
            if ($result) {
                $result.setExpType(driver.decodeType(type, $result.getTupleSize(), $result.isNatural()); 
            }
        }
        |
        SHAPE MODTYPE p=function_parameter_list BECOMES {
            driver.error("Reserved keyword: adjustment");
            $result = null;
        }
        |
        MODTYPE p=function_parameter_list BECOMES {
            driver.error("Reserved keyword: adjustment");
            $result = null;
        }
        |
        t=USER_STRING MODTYPE p=function_parameter_list BECOMES {
            driver.error("Reserved keyword: adjustment");
            $result = null;
        }
        ;

global_definition_header returns [ASTDefine result]
		:
        r=function_definition_header {
            if ($r.result != null) {
                assert($r.result.getDefineType() == DefineType.FunctionDefine);
                driver.pushPepContainer(driver.paramDecls);
            } else {
                // An error occurred
                driver.paramDecls.getParameters().clear();
                driver.paramDecls.setStackCount(0);
            }
            $result = $r.result;
        }
        |
       	r=definition_header {
            $result = $r.result;
        }
        ;

definition_header returns [ASTDefine result]
		:
        n=USER_STRING BECOMES {
        	String name = $n.getText();
            $result = driver.makeDefinition(name, false);
        }
        | MODTYPE BECOMES {
            driver.error("Reserved keyword: adjustment");
            $result = null;
        }
        ;

definition returns [ASTDefine result]
        :
        d=definition_header e=exp2 { 
        	ASTDefinition var = $d.result;
        	ASTExpression exp = $e.result;
        	if (var != null) {
        		if (exp instanceOf ASTModification) {
        			mod.getModData().setSeed(0);
        			var.getChildChange().grab(mod);
        		} else {
        			var.setExp(exp);
        		}
        		$result = var;
        	} else {
        		$result = null;        		
        	}
        }
        ;
	
USER_RATIONAL
	: 
	('0'..'9')+ '.' ('0'..'9')* '%'? | '.' ('0'..'9')+ '%'? | '0'..'9'+ '%'?
	;

STARTSHAPE
	: 
	'startshape' 
	;

BACKGROUND
	: 
	'background' 
	;

INCLUDE
	: 
	'include' 
	;

IMPORT
	: 
	'import' 
	;

TILE
	: 
	'tile' 
	;

RULE
	: 
	'rule' 
	;

PATH
	: 
	'path' 
	;

SHAPE
	: 
	'shape' 
	;

LOOP
	: 
	'loop' 
	;

FINALLY
	: 
	'finally' 
	;

IF
	: 
	'if' 
	;

ELSE
	: 
	'else' 
	;

SWITCH
	: 
	'switch' 
	;

CASE
	: 
	'case' 
	;

RANGE
	: 
	'..' 
	;

PLUSMINUS
	: 
	'+/-' | '\u00b1'
	;

TIME
	: 
	'time' 
	;

TIMESCALE
	: 
	'timescale' 
	;

X
	: 
	'x' 
	;

Y
	: 
	'y' 
	;

Z
	: 
	'z' 
	;
	
ROTATE
	: 
	'rotate' | 'r' 
	;

SIZE
	: 
	'size' | 's' 
	;
	
SKEW
	: 
	'skew' 
	;

FLIP
	: 
	'flip' | 'f' 
	;

HUE
	: 
	'hue' | 'h' 
	;

SATURATION
	: 
	'saturation' | 'sat'
	;

BRIGHTNESS
	: 
	'brightness' | 'b' 
	;

ALPHA
	: 
	'alpha' | 'a' 
	;

TARGETHUE
	: 
	'|hue' | '|h' 
	;

TARGETSATURATION
	: 
	'|saturation' | '|sat' 
	;

TARGETBRIGHTNESS
	: 
	'|brightness' | '|b' 
	;

TARGETALPHA
	: 
	'|alpha' | '|a' 
	;

X1
	: 
	'x1' 
	;

X2
	: 
	'x2' 
	;

Y1
	: 
	'y1' 
	;

Y2
	: 
	'y2' 
	;

RX
	: 
	'rx' 
	;

RY
	: 
	'ry' 
	;

WIDTH
	: 
	'width' 
	;

TRANSFORM
	: 
	'transform' | 'trans' 
	;

PARAM
	: 
	'param' | 'p' 
	;
	
BECOMES
	: 
	'=' 
	;

LT
	: 
	'<' 
	;

GT
	: 
	'>' 
	;

LE
	: 
	'<=' | '\u2264'
	;

GE
	: 
	'>=' | '\u2265' 
	;

EQ
	: 
	'==' 
	;

NEQ
	: 
	'<>' | '\u2276'
	;

NOT
	: 
	'!' 
	;

AND
	: 
	'&&' 
	;

OR
	: 
	'||' 
	;

XOR
	: 
	'^^' 
	;

USER_PATHOP
	: 
	'MOVETO'
	| 
	'LINETO'
	| 
	'ARCTO'
	| 
	'CURVETO'
	| 
	'MOVEREL'
	| 
	'LINEREL'
	| 
	'ARCREL'
	| 
	'CURVEREL'
	| 
	'CLOSEPOLY' 
	;

MODTYPE
	:
	TIME | TIMESCALE | X | Y | Z | ROTATE | SIZE | SKEW | FLIP | HUE | SATURATION | BRIGHTNESS | ALPHA | TARGETHUE | TARGETSATURATION | TARGETBRIGHTNESS | TARGETALPHA | X1 | X2 | Y1 | Y2 | RX | RY | WIDTH
	;
		
USER_STRING 
	: 
	('a'..'z'|'A'..'Z'|'_'|'\u0200'..'\u0301'|'\u0303'..'\u0377') (('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'\u0200'..'\u0301'|'\u0303'..'\u0377')|('\u0302'('\u0200'..'\u0260'|'\u0262'..'\u0377')))* 
	;

USER_QSTRING	
	:	
	'"' USER_STRING '"' 
	;
	
USER_FILENAME 
	: 
	('a'..'z'|'A'..'Z'|'\u0200'..'\u0377') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'\u0200'..'\u0377'|'.')* '.cfdg' 
	;

USER_ARRAYNAME 
	: 
	('a'..'z'|'A'..'Z'|'_'|'\u0200'..'\u0301'|'\u0303'..'\u0377') (('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'\u0200'..'\u0301'|'\u0303'..'\u0377')|('\u0302'('\u0200'..'\u0260'|'\u0262'..'\u0377')))* 
	;

COMMENT
	: 
	('//' ~('\n'|'\r')* '\r'? '\n' {} | '/*' (.)*? '*/' {}) -> skip 
	;

WHITESPACE  
	: 
	( ' ' | '\t' | '\r' | '\n' ) -> skip 
	;

CFDG2
	: 
	'CFDG2' 
	;
	
CFDG3
	: 
	'CFDG3' 
	;
	