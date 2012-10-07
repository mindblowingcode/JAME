grammar CFDG;

options
{
	output=AST;
	backtrack=true;
	memoize=true;
} 

@lexer::header { 
	package net.sf.jame.contextfree.parser; 
}

@parser::header {
	package net.sf.jame.contextfree.parser; 
}

@members {
	Driver driver = new Driver();
}
 
cfdg
        :
        r=statement* {
        if ($r.result != null) {
          driver.pushRep($r.result, true);
        }
        } EOF
        ;
        
statement returns [ASTReplacement result]
        : 
        initialization
        | background  
        | inclusion { 
        	$result = null;
        }
        | tile
        | size
        | rule
        | path
        | r=shape { 
        	$result = $r.result;
        }
        | shape_singleton
        | shape_element
        | global_definition { 
        	$result = null;
        }
        ;
        
inclusion 
        : 
        INCLUDE f=QSTRING {
        	driver.setShape(null);
        	driver.includeFile($f.getText());
        }
        |
        INCLUDE f=FILENAME {
        	driver.setShape(null);
        	driver.includeFile($f.getText());
        }
        ;

initialization
        : 
        STARTSHAPE s=STRING p=parameter_spec m=modification {
        	String name = $s.getText();
        	ASTExpression parameter = $p.result;
        	ASTExpression modification = $m.result;
        	driver.setShape(null);
        	ASTRuleSpecifier ruleSpecifier = driver.makeRuleSpecifier(name, parameter);
        	ASTReplacement start = new ASTReplacement(ruleSpecifier, name, modification);
        	driver.initialize(start);
        }
        |
        STARTSHAPE s=STRING p=parameter_spec {
        	String name = $s.getText();
        	ASTExpression parameter = $p.result;
        	driver.setShape(null);
        	ASTRuleSpecifier ruleSpecifier = driver.makeRuleSpecifier(name, parameter);
        	ASTReplacement start = new ASTReplacement(ruleSpecifier, name, new ASTExpression());
        	driver.initialize(start);
        }
        ;

background
        : 
        BACKGROUND m=global_modification {
        	driver.setShape(null);
        	ASTExpression modification = $m.result;
        	ASTReplacement background = new ASTReplacement(ASTRuleSpecifier.ZERO, "", modification);
        	driver.background(background);
        }
        ;

tile
        : 
        TILE m=global_modification {
        	driver.setShape(null);
        	ASTExpression modification = $m.result;
        	ASTReplacement tile = new ASTReplacement(ASTRuleSpecifier.ZERO, "", modification);
        	driver.buildTileTransform(tile);
        }
        ;

size
        : 
        SIZE m=global_modification {
        	driver.setShape(null);
        	ASTExpression modification = $m.result;
        	ASTReplacement size = new ASTReplacement(ASTRuleSpecifier.ZERO, "", modification);
        	driver.buildSizeTransform(size);
        }
        ; 

shape returns [ASTShape result]
        : 
        SHAPE s=STRING parameter_list {
        	String name = $s.getText(); 
            	ASTRuleSpecifier ruleSpecifier = new ASTRuleSpecifier(driver.stringToShape(name), name, driver.paramDecls.getParameters(), driver.paramDecls.getParameters());
            	ASTShape shape = new ASTShape(ruleSpecifier, false);
            	shape.getRules().getParameters().clear();
            	shape.getRules().getParameters().addAll(driver.paramDecls.getParameters());
	driver.setShape(shape);
            	shape.getRuleSpecifier().setTypeSignature(shape.getRules().getParameters().isEmpty() ? null : shape.getRules().getParameters());
            	$result = shape;
        }
        ;

shape_singleton returns [ASTShape result]
        : 
        s=shape {
        	driver.inPathContainer = false;
        	ASTRule rule = new ASTRule(-1);
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        } '{'  buncha_elements '}' {
        	driver.inPathContainer = false;
        	ASTRule rule = new ASTRule(-1);
        	driver.popRepContainer(rule);
        	if (rule.getRepType() == RepElemListEnum.empty) {//TODO da cambiare
        		rule.setPath(true);
        		driver.retroPath(rule);
        	}
	ASTShape shape = $s.result;
        	shape.getRules().getBody().add(0, rule);
        	$result = shape;
        }
        ; 

rule_header returns [ASTRule result]
        : 
        RULE s=STRING {
        	String name = $s.getText();
        	driver.setShape(null);
        	ASTRule rule = new ASTRule(driver.stringToShape(name));
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        	$result = rule;
        }
        |
        RULE s=STRING w=RATIONAL {
        	String name = $s.getText();
        	String weight = $w.getText();
        	driver.setShape(null);
        	ASTRule rule = new ASTRule(driver.stringToShape(name), Float.parseFloat(weight), weight.indexOf("\u0025") != -1);
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        	$result = rule;
        }
        ;

rule returns [ASTRule result]
        : 
        h=rule_header '{' buncha_replacements_v2 '}' {
        	driver.popRepContainer($h.result);
        	$result = $h.result;
        }
        ;

shape_element_header returns [ASTRule result]
        : 
        RULE {
        	driver.inPathContainer = false;
        	ASTRule rule = new ASTRule(-1);
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        	$result = rule;
       }
       |
        RULE w=RATIONAL {
        	String weight = $w.getText();
        	driver.inPathContainer = false;
        	ASTRule rule = new ASTRule(-1, Float.parseFloat(weight), weight.indexOf("\u0025") != -1);
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        	$result = rule;
        }
        |
        PATH {
        	driver.inPathContainer = true;
        	ASTRule rule = new ASTRule(-1);
        	rule.setPath(true);
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        	$result = rule;
        }
        ;

shape_element returns [ASTRule result]
        : 
        h=shape_element_header '{' buncha_elements '}' {
        	driver.inPathContainer = false;
        	ASTRule rule = $h.result;
        	driver.popRepContainer(rule);
        	$result = rule;
        }
        ;

path_header returns [ASTRule result]
        : 
        PATH s=STRING {
        	String name = $s.getText();
        	driver.setShape(null);
        	driver.inPathContainer = true;
        	ASTRule rule = new ASTRule(driver.stringToShape(name));
        	rule.setPath(true);
        	driver.addRule(rule);
        	driver.pushRepContainer(rule.getRuleBody());
        	$result = rule;
        }
        ;

path returns [ASTRule result]
        : 
        h=path_header '{' buncha_pathOps_v2 '}' {
        	ASTRule rule = $h.result;
        	driver.popRepContainer(rule);
        	$result = rule;
        }
        ;

parameter
       : 
       t=STRING v=STRING {
	String type = $t.getText();
	String var = $v.getText();
	driver.nextParameterDecl(type, var);
        }
        |
        SHAPE v=STRING {
	String var = $v.getText();
	driver.nextParameterDecl("shape", var);
        }
        ;

buncha_parameters 
        : 
        parameter ',' buncha_parameters 
        | 
        parameter
        ;

parameter_list
        : 
        '(' buncha_parameters ')' {
        }
        |
        ;

parameter_spec returns [ASTExpression result]
        : 
        '(' e=exp2 ')' { 
        	$result = $e.result;
        }
        | '(' ')' { 
        	$result = new ASTExpression(); 
        }
        | {
        	$result = null;
        }
        ;

buncha_elements 
        : 
        r=element buncha_elements {
        	driver.pushRep($r.result, false);
        }
        | 
        {
        }
        ;

buncha_pathOps_v2 
        : 
        r=pathOp_v2 buncha_pathOps_v2 {
        	driver.pushRep($r.result, false);
        }
        | 
        {
        }
        ;

pathOp_simple_v2 returns [ASTReplacement result]
        : 
        o=PATHOP '{' a=buncha_pathop_adjustments '}' {
        	String operator = $o.getText();
        	ASTExpression modification = $a.result;
        	$result = new ASTPathOp(operator, modification, false);
        }
        |
        c=STRING m=modification_v2 {
        	String command = $c.getText();
        	ASTExpression modification = $m.result;
        	$result = new ASTPathCommand(command, modification);
        }
        ;

element_simple returns [ASTReplacement result]
        : 
        o=PATHOP '(' e=exp2 ')' {
        	driver.inPathContainer = true;
        	String operator = $o.getText();
        	ASTExpression modification = $e.result;
        	$result = new ASTPathOp(operator, modification, true);
        }
        |
        o=PATHOP '(' ')' {
        	driver.inPathContainer = true;
        	String operator = $o.getText();
        	$result = new ASTPathOp(operator, null, true);
        }
        |
        s=STRING p=parameter_spec m=modification {
        	String command = $s.getText();
        	ASTExpression parameter = $p.result;
        	ASTExpression modification = $m.result;
        	$result = driver.makeElement(command, modification, parameter, false);
        }
        |
        PATH s=STRING p=parameter_spec m=modification {
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
        	driver.popRepContainer($rl.result);
        	if ($rl.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        rl=element_loop FINALLY {
        	driver.popRepContainer($rl.result);
        	driver.pushRepContainer(((ASTLoop) $rl.result).getFinallyBody());
        } one_or_more_elements {
        	$result = $rl.result; 
        	driver.popRepContainer($rl.result);
        	if ($rl.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        ri=ifHeader one_or_more_elements {
        	$result = $ri.result; 
        	driver.popRepContainer($ri.result);
        	if ($ri.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        ri=ifHeader one_or_more_elements ELSE {
        	driver.popRepContainer($ri.result);
        	driver.pushRepContainer(((ASTIf)(ri!=null?ri.result:null)).getElseBody());
        } one_or_more_elements {
        	$result = $ri.result; 
        	driver.popRepContainer($ri.result);
        	if ($ri.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        rt=transHeader one_or_more_elements {
        	$result = $rt.result; 
        	driver.popRepContainer($rt.result);
        	if ($rt.result.getRepType().getType() == 0) {
	        	$result = null; 
        	}
        }
        |
        rs=switchHeader '{' caseBody '}' {
	$rs.result.unify();
        	$result = $rs.result; 
        	driver.popRepContainer($rs.result);
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
	driver.popRepContainer($rl.result);
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
        s=STRING m=modification_v2 {
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
	driver.popRepContainer($rl.result);
	if ($rl.result.getRepType().getType() == 0) {
	        	$result = null;			
	}
        }
        ;

loopHeader_v2 returns [ASTReplacement result]
        : 
        r=RATIONAL '*' m=modification_v2 {
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
        LOOP v=STRING BECOMES i=exp2 m=modification {
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
        	$result = driver.makeModTerm($t.getText(), modification);
        }
        |
        t=(HUE|SATURATION|BRIGHTNESS|ALPHA) m=exp '|' {
        	ASTExpression modification = $m.result; 
        	$result = driver.makeModTerm($t.getText(), modification);
        }
        |
        PARAM p=STRING {
        	$result = driver.makeModTerm(ModTypeEnum.param, $p.getText());
        }
        |
        PARAM p=QSTRING {
        	$result = driver.makeModTerm(ModTypeEnum.param, $p.getText());
        }
        ;
        
explist returns [ASTExpression result]
        : 
        e1=exp e2=explist {
        	$result = new ASTCons($e2.result, $e1.result);
        }
        | 
        e=exp { 
        	$result = $e.result;
        }
        ;

exp returns [ASTExpression result]
        : 
        (
        r=RATIONAL { 
	$result = new ASTReal(Float.parseFloat($r.getText())); 
        }
        |
        '-' r=RATIONAL {
	$result = new ASTReal(Float.parseFloat($r.getText()), true); 
        }
        |
        '+' r=RATIONAL { 
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
        r=RATIONAL { 
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
        f=STRING '(' ')' { 
        	String function = $f.getText();
        	$result = driver.makeFunction(function, new ASTExpression(), true);
        }
        | 
        f=STRING '(' e=exp2 ')' { 
        	String function = $f.getText();
        	ASTExpression arguments = $e.result;
        	$result = driver.makeFunction(function, arguments, true);
        }
        |
        v=STRING { 
        	String var = $v.getText();
        	$result = driver.makeVariable(var);
        }
        ;

exp2func returns [ASTExpression result]
        : 
        f=STRING '(' ')' { 
        	String function = $f.getText();
        	$result = driver.makeFunction(function, new ASTExpression(), true);
        }
        | 
        f=STRING '(' e=exp2 ')' { 
        	String function = $f.getText();
        	ASTExpression arguments = $e.result;
        	$result = driver.makeFunction(function, arguments, true);
        }
        |
        v=STRING { 
        	String var = $v.getText();
        	$result = driver.makeVariable(var);
        }
        ;
        
global_definition
        : 
        v=STRING BECOMES e=exp2 { 
        	driver.setShape(null);
        	String var = $v.getText();
        	ASTExpression expression = $e.result;
 	driver.nextParameter(var, expression);
        }
        ;

definition
        :
        v=STRING BECOMES e=exp2 { 
        	String var = $v.getText();
        	ASTExpression expression = $e.result;
 	driver.nextParameter(var, expression);
        }
        ;
	
RATIONAL
	: 
	('0'..'9')+ '.' ('0'..'9')* '%'? | '.' ('0'..'9')+ '%'? | '0'..'9'+ '%'?
	;

RATIONAL2		
	: 
	('0'..'9')+ '%'? '..' 
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

PATHOP
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
	
STRING 
    	: 
    	('a'..'z'|'A'..'Z'|'_'|'\u0200'..'\u0301'|'\u0303'..'\u0377') (('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'\u0200'..'\u0301'|'\u0303'..'\u0377')|('\u0302'('\u0200'..'\u0260'|'\u0262'..'\u0377')))* 
    	;

QSTRING	
	:	
	'"' STRING '"' 
	;
	
FILENAME 
    	: 
    	('a'..'z'|'A'..'Z'|'\u0200'..'\u0377') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'\u0200'..'\u0377'|'.')* '.cfdg' 
    	;

COMMENT
    	: 
    	'//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;} | '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;} 
    	;

WHITESPACE  
	: 
	( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;} 
	;
