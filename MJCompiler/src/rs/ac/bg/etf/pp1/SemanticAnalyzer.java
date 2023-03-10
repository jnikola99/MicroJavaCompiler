package rs.ac.bg.etf.pp1;
import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean newArray = false;
	String currentDesignatorName = null;
	int adr=0;
	char adrch=0;
	int currentNumber = 0;
	int level=0;
	int nVars;
	int elemCount=1;
	List<Integer> listaTipova = new ArrayList<>();
	HashMap<String, Integer> deklarisaniNizovi = new HashMap<String, Integer>();	
	Struct typevar = null;
	Struct constTypevar = null;
	Struct boolType = new Struct(Struct.Bool);

	Logger log = Logger.getLogger(getClass());
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	private void mainExist() {
		if((Tab.currentScope.findSymbol("main")==null)||(Tab.currentScope.findSymbol("main").getType().getKind()!=Struct.None)){
			report_error("Greska, main metoda nije pronadjena ili nije VOID tipa",null);
		}
	}
	
	
	//VISIT ZA VAR
	public void visit(VarDeclOneTime varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarDecName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarDecName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		if(!(varDeclOne.getVarSquares() instanceof NoSquares)) {
			report_info("Deklarisan niz "+ varDeclOne.getVarDecName(), varDeclOne);
			Obj varNode = Tab.insert(Obj.Var,varDeclOne.getVarDecName(),new Struct(Struct.Array,varDeclOne.getType().struct));
		}
		else {
			report_info("Deklarisana promenljiva "+ varDeclOne.getVarDecName(), varDeclOne);
			Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarDecName(), varDeclOne.getType().struct);
		}
	}
	
	public void visit(MoreVarDecl varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarDecName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarDecName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		if(!(varDeclOne.getVarSquares() instanceof NoSquares)) {
			report_info("Deklarisan niz "+ varDeclOne.getVarDecName(), varDeclOne);
			Obj varNode = Tab.insert(Obj.Var,varDeclOne.getVarDecName(),new Struct(Struct.Array,varDeclOne.getType().struct));
		}
		else {
			report_info("Deklarisana promenljiva "+ varDeclOne.getVarDecName(), varDeclOne);
			Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarDecName(), varDeclOne.getType().struct);
		}
	}
	
	public void visit(OneVar varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		if(!(varDeclOne.getVarSquares() instanceof NoSquares)) {
			report_info("Deklarisan niz "+ varDeclOne.getVarName(), varDeclOne);
			Obj varNode = Tab.insert(Obj.Var,varDeclOne.getVarName(),new Struct(Struct.Array,typevar));
		}
		else {
		report_info("Deklarisana promenljiva "+ varDeclOne.getVarName(), varDeclOne);
		Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarName(),typevar);
		}	
	}
	
	public void visit(MultipleVars varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		if(!(varDeclOne.getVarSquares() instanceof NoSquares)) {
			report_info("Deklarisan niz "+ varDeclOne.getVarName(), varDeclOne);
			Obj varNode = Tab.insert(Obj.Var,varDeclOne.getVarName(),new Struct(Struct.Array,typevar));
		}
		else {
		report_info("Deklarisana promenljiva "+ varDeclOne.getVarName(), varDeclOne);
		Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarName(),typevar);
		}
	}
	
	//KONSTANTNE PROMENLJIVE
	public void visit(ConstDeclOneTime constDecl) {
		if(!(Tab.currentScope().findSymbol(constDecl.getSomeConst()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+constDecl.getSomeConst()+" je deklarisana vec!",constDecl);
			return;
		}
		String typeName=constDecl.getType().getTypeName();
		if(!((typeName.equals("int") && (constDecl.getConstType().struct==Tab.intType))||(typeName.equals("char") && (constDecl.getConstType().struct==Tab.charType))||(typeName.equals("bool") && (constDecl.getConstType().struct==boolType)))) {
			report_error("Greska, nekompatibilni tipovi kod promenljive: "+constDecl.getSomeConst(),constDecl);
			return;
		}
		report_info("Deklarisana konstanta "+ constDecl.getSomeConst(), constDecl);
		Obj constNode = Tab.insert(Obj.Con,constDecl.getSomeConst(),typevar);
		
		if(constDecl.getConstType().struct==Tab.intType) {
			constNode.setAdr(adr);
		}
		else if(constDecl.getConstType().struct==Tab.charType) {
			constNode.setAdr(adrch);
		}
		else {
			constNode.setAdr(adr);
		}
		constNode.setLevel(level);
	}
	
	public void visit(MoreConstDecl constDecl) {
		if(!(Tab.currentScope().findSymbol(constDecl.getSomeConst()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+constDecl.getSomeConst()+" je deklarisana vec!",constDecl);
			return;
		}
		String typeName=constDecl.getType().getTypeName();
		if(!((typeName.equals("int") && (constDecl.getConstType().struct==Tab.intType))||(typeName.equals("char") && (constDecl.getConstType().struct==Tab.charType))||(typeName.equals("bool") && (constDecl.getConstType().struct==boolType)))) {
			report_error("Greska, nekompatibilni tipovi kod promenljive: "+constDecl.getSomeConst(),constDecl);
			return;
		}
		report_info("Deklarisana konstanta "+ constDecl.getSomeConst(), constDecl);
		Obj constNode = Tab.insert(Obj.Con,constDecl.getSomeConst(),typevar);
	}
	
	public void visit(OneConstType constDecl) {
		if(!(Tab.currentScope().findSymbol(constDecl.getSomeConst()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+constDecl.getSomeConst()+" je deklarisana vec!",constDecl);
			return;
		}
		if(!(((constTypevar==Tab.intType)&&(typevar==Tab.intType))||((constTypevar==Tab.charType)&&(typevar==Tab.charType))||((constTypevar==boolType)&&(typevar==boolType)))) {
			report_error("Greska, nekompatibilni tipovi kod promenljive: "+constDecl.getSomeConst(),constDecl);
			return;
		}
		report_info("Deklarisana konstanta "+ constDecl.getSomeConst(), constDecl);
		Obj constNode = Tab.insert(Obj.Con,constDecl.getSomeConst(),typevar);
	}
	
	public void visit(MultipleConstTypes constDecl) {
		if(!(Tab.currentScope().findSymbol(constDecl.getSomeConst()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+constDecl.getSomeConst()+" je deklarisana vec!",constDecl);
			return;
		}
		if(!(((constTypevar==Tab.intType)&&(typevar==Tab.intType))||((constTypevar==Tab.charType)&&(typevar==Tab.charType))||((constTypevar==boolType)&&(typevar==boolType)))) {
			report_error("Greska, nekompatibilni tipovi kod promenljive: "+constDecl.getSomeConst(),constDecl);
			return;
		}
		report_info("Deklarisana konstanta "+ constDecl.getSomeConst(), constDecl);
		Obj constNode = Tab.insert(Obj.Con,constDecl.getSomeConst(),typevar);
	}
	
	
	//TIPOVI KONSTANTI
	public void visit(NumConst cnst) {
		cnst.struct = Tab.intType;
		constTypevar = Tab.intType;
		adr = cnst.getConstType();
	}
	
	public void visit(CharConst cnst) {
		cnst.struct = Tab.charType;
		constTypevar = Tab.charType;
		adrch = cnst.getConstType().charAt(1);
	}
	
	public void visit(BoolConst cnst) {
		cnst.struct = boolType;
		constTypevar = boolType;
		if(cnst.getConstType().equals("true")) {
			adr = 1;
		}
		else {
			adr = 0;
		}
	}
	
	public void visit(TermOne term) {
		term.struct = term.getFactor().struct;
	}
	
	public void visit(TermMore term) {
		Struct t = term.getTerm().struct;
		Struct e = term.getFactor().struct;
		if(t.getKind()==Struct.Array) {
			t=t.getElemType();
		}
		if(e.getKind()==Struct.Array) {
			e=e.getElemType();
		}
		if(t.equals(e) && e==Tab.intType) {
			term.struct=e;
		}
		else {
			report_error("Greska na liniji "+ term.getLine()+" : nekompatibilni tipovi u izrazu za mnozenje.", null);
			term.struct = Tab.noType;
		}
	}
	
	public void visit(NextExpr expr) {
		Struct t = expr.getAddopTermList().struct;
		Struct e = expr.getTerm().struct;
		if(t.getKind()==Struct.Array) {
			t=t.getElemType();
		}
		if(e.getKind()==Struct.Array) {
			e=e.getElemType();
		}
		if(t.equals(e) && e==Tab.intType) {
			expr.struct=e;
		}
		else {
			report_error("Greska na liniji "+ expr.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
			expr.struct = Tab.noType;
		}
	}
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();     	
	}
	
	public void visit(Program program) {
		mainExist();
		nVars = Tab.currentScope().getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if(typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			type.struct = Tab.noType;
		}
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} 
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			}
		}
		typevar=type.struct;
	}
	
	public void visit(MethodDecl methodDecl) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		returnFound = false;
		currentMethod = null;
	}
	
	public void visit(MethTypeOther methodType) {
		currentMethod = Tab.insert(Obj.Meth, methodType.getMethName(), methodType.getType().struct);
		methodType.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodType.getMethName(), methodType);
	}
	
	public void visit(MethTypeVoid methodType) {
		currentMethod = Tab.insert(Obj.Meth, methodType.getMethName(), Tab.noType);
		methodType.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + methodType.getMethName(), methodType);
	}
	
	public void visit(OneDesignatorIdent designator) {
		Obj obj = Tab.find(designator.getDesignatorName());
		if (obj == Tab.noObj) { 
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getDesignatorName()+" nije deklarisano! ", null);
		}
		designator.obj = obj;
		currentDesignatorName = designator.getDesignatorName();
		if(obj.getKind()==Obj.Con) {
			currentNumber = obj.getAdr();
		}
	}
	
	public void visit(ReturnCondition returnExpr) {
		returnFound = true;
		Struct currMethType = currentMethod.getType();
		if(!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}
	}
	
	public void visit(FactorExpr factor) {
		factor.struct=factor.getExpr().struct;
	}
	
	public void visit(FactorDesAct funcCall) {
		Obj func = funcCall.getDesignator().obj;
		if(Obj.Meth == func.getKind()) {
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
		}
		else {
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}
	}
	
	public void visit(FactorDesNoAct factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}
	
	public void visit(FirstExpr expr) {
		expr.struct = expr.getTerm().struct;
	}
	
	public void visit(FactorNumber factor) {
		factor.struct=Tab.intType;
		currentNumber=factor.getN1();
	}
	
	public void visit(FactorChar factor) {
		factor.struct = Tab.charType;
	}
	
	public void visit(FactorBool factor) {
		factor.struct = boolType;
	}
	
	public void visit(FactorNewTypeExpr factor) {
		Struct s = factor.getExpr().struct;
		if(s!=Tab.intType) {
			report_error("Greska na liniji: " + factor.getLine() + ", index nije tipa int",null);
			s=Tab.noType;
		}
		newArray=true;
		factor.struct=factor.getType().struct;
		//System.out.println()
	}
	
	public void visit(NegativeExpr ne) {
		Struct s = ne.getAddopTermList().struct;
		if(s!=Tab.intType) {
			report_error("Greska na liniji "+ ne.getLine()+" : izraz mora biti tipa int.", null);
			ne.struct = Tab.noType;
		}
		else {
			ne.struct=s;
		}
	}
	
	public void visit(PositiveExpr expr) {
		expr.struct = expr.getAddopTermList().struct;
	}
	
	public void visit(DesignatorWithExpr des) {
		Struct e = des.getExpr().struct;
		Struct s = des.getDesignator().obj.getType();
		des.obj = des.getDesignator().obj;
		if(e.getKind()==Struct.Array) {
			e=e.getElemType();
		}
		if(e!=Tab.intType || s.getKind()!=Struct.Array) {
			report_error("Greska na liniji "+ des.getLine()+" : promenljiva nije niz ili indikator nije broj", null);
		}
	}
	
	public void visit(DesignatorInc cond) {
		int desKind=cond.getDesignator().obj.getKind();
		if(cond.getDesignator().obj.getType()!=Tab.intType &&  desKind!=Obj.Var && desKind!=Obj.Elem && desKind!=Obj.Con) {
			report_error("Greska na liniji "+ cond.getLine()+" : designator uz ++ mora biti promenljiva ili element niza tipa int", null);
		}
	}
	
	public void visit(DesignatorDec cond) {
		int desKind=cond.getDesignator().obj.getKind();
		if(cond.getDesignator().obj.getType()!=Tab.intType &&  desKind!=Obj.Var && desKind!=Obj.Elem && desKind!=Obj.Con) {
			report_error("Greska na liniji "+ cond.getLine()+" : designator uz -- mora biti promenljiva ili element niza tipa int", null);
		}
	}
	
	public void visit(DesignatorAssign cond) {
		int desKind=cond.getDesignator().obj.getKind();
		if(desKind!=Obj.Var && desKind!=Obj.Elem && desKind!=Obj.Con) {
			report_error("Greska na liniji "+ cond.getLine()+" : designator mora biti promenljiva ili element niza", null);
		}
		if(newArray) {
			newArray=false;
			deklarisaniNizovi.put(currentDesignatorName,currentNumber);
			currentDesignatorName=null;
			currentNumber=0;
			if(cond.getDesignator().obj.getType().getElemType().getKind()!=cond.getExpr().struct.getKind()) {
				report_error("Greska na liniji "+ cond.getLine() + " : nisu kompatibilni tipovi",null);
			}
			return;
		}
		if(cond.getDesignator().obj.getType().getKind()==Struct.Array) {
			if(cond.getExpr().struct.getKind()!=Struct.Array) {
				if(!cond.getExpr().struct.assignableTo(cond.getDesignator().obj.getType().getElemType())) {
					report_error("Greska na liniji "+ cond.getLine() + " : nisu kompatibilni tipovi",null);
				}
			}else
			if(!cond.getExpr().struct.getElemType().assignableTo(cond.getDesignator().obj.getType().getElemType())) {
				report_error("Greska na liniji "+ cond.getLine() + " : nisu kompatibilni tipovi",null);
			}
		}
		else {
			if(cond.getExpr().struct.getKind()!=Struct.Array) {
				if(!cond.getExpr().struct.assignableTo(cond.getDesignator().obj.getType())) {
					report_error("Greska na liniji "+ cond.getLine() + " : nisu kompatibilni tipovi",null);
				}
				
			}
			else {
				if(!cond.getExpr().struct.getElemType().assignableTo(cond.getDesignator().obj.getType())) {
					report_error("Greska na liniji "+ cond.getLine() + " : nisu kompatibilni tipovi",null);
				}
			}
			
		}
		
	}
	
	public void visit(OneDesStmt stmt) {
		if(!(stmt.getDesignatorNot() instanceof DesignatorNo)) {
			Obj stm=Tab.find(stmt.getDesignatorNot().obj.getName());
			if(stm.getKind()!=Obj.Var && stm.getKind()!=Obj.Elem && stm.getKind()!=Obj.Con) {
				report_error("Greska na liniji "+ stmt.getLine() + " : terminali sa leve strane moraju biti promenljive ili elementi niza",null);
			}
		}
		elemCount++;
		stmt.obj = stmt.getDesignatorNot().obj;
	}
	
	public void visit(DesignatorYes des) {
		des.obj = des.getDesignator().obj;
		Obj curr = Tab.find(des.obj.getName());
		if(curr.getType().getKind()==Struct.Array)
			listaTipova.add(curr.getType().getElemType().getKind());
		else
		listaTipova.add(curr.getType().getKind());
		
		
	}
	
	public void visit(MoreDesStmt stmt) {
		if(!(stmt.getDesignatorNot() instanceof DesignatorNo)) {
			Obj stm=Tab.find(stmt.getDesignatorNot().obj.getName());
			if(stm.getKind()!=Obj.Var && stm.getKind()!=Obj.Elem && stm.getKind()!=Obj.Con) {
				report_error("Greska na liniji "+ stmt.getLine() + " : terminali sa leve strane moraju biti promenljive ili elementi niza",null);
			}
		}
		elemCount++;
		stmt.obj = stmt.getDesignatorNot().obj;
	}
	
	public void visit(DesignatorSquareStmt stmt) {
		
		if(stmt.getDesignatorNot() instanceof DesignatorNo) {
			return;
		}
		
		Obj first = stmt.getDesignatorNot().obj;
		Obj second = stmt.getDesignator().obj;
		Obj firstVar = Tab.find(first.getName());
		Obj secondVar = Tab.find(second.getName());
		
		if(firstVar.getType().getKind()==Struct.Array) {
			if(!firstVar.getType().getElemType().assignableTo(secondVar.getType().getElemType())) {
				report_error("Greska na liniji "+ stmt.getLine() + " : nekompatibilna dodela nizu",null);
			}
		}
		else {
			if(!firstVar.getType().assignableTo(secondVar.getType().getElemType())) {
				report_error("Greska na liniji "+ stmt.getLine() + " : nekompatibilna dodela nizu",null);
			}
		}
		if(firstVar.getKind()!=Obj.Var && firstVar.getKind()!=Obj.Elem) {
			report_error("Greska na liniji "+ stmt.getLine() + " : ono sto se dodeljuje nije promenljiva ili element niza",null);
		}
		if(!secondVar.getType().isRefType()) {
			report_error("Greska na liniji "+ stmt.getLine() + " : promenljiva sa desne strane nije niz",null);
		}
		
	}
	
	public void visit(MoreDesignatorSquare stmt) {
		int number = 0;
		String name = stmt.getDesignator().obj.getName();
		for(String i : deklarisaniNizovi.keySet()) {
			if(i.equals(name)) {
				number=deklarisaniNizovi.get(i);
			}
		}
		//Niz nije deklarisan
		if(number==0) {
			report_error("Greska na liniji "+ stmt.getLine() + " : niz sa desne strane nije deklarisan",null);
		}
		Obj first = stmt.getDesignatorNot().obj;
		Obj second = stmt.getDesignator().obj;
		Obj secondVar = Tab.find(second.getName());
		
		//Ovo sa desne strane nije niz
		if(!secondVar.getType().isRefType()) {
			report_error("Greska na liniji "+ stmt.getLine() + " : promenljiva sa desne strane nije niz",null);
		}
		//Ima vise elemenata u dodeli nego sto prima niz
		Struct secondType = secondVar.getType().getElemType();
		if(elemCount>number) {
			report_error("Greska na liniji "+ stmt.getLine() + " : vise elemenata nego sto je velicina niza",null);
		}
		for(int i=0;i<listaTipova.size();i++) {
			if(listaTipova.get(i)!=secondType.getKind()) {
				report_error("Greska na liniji "+ stmt.getLine() + " : tipovi se ne poklapaju pri dodeli niza",null);
			}
		}
		elemCount=1;
		
	}
	
	public void visit(ReadCondition cond) {
		int desKind=cond.getDesignator().obj.getKind();
		if(desKind==Struct.Array) {
			if(cond.getDesignator().obj.getType().getElemType()!=Tab.intType && cond.getDesignator().obj.getType().getElemType()!=Tab.charType && cond.getDesignator().obj.getType().getElemType()!=boolType) {
				report_error("Greska na liniji "+ cond.getLine()+" : promenljiva u read iskazu nije tipa int, char ili bool", null);
			}
		}
		else if(cond.getDesignator().obj.getType()!=Tab.intType && cond.getDesignator().obj.getType()!=Tab.charType && cond.getDesignator().obj.getType()!=boolType && desKind!=Obj.Var && desKind!=Obj.Elem && desKind!=Obj.Con) {
			report_error("Greska na liniji "+ cond.getLine()+" : promenljiva u read iskazu nije tipa int, char ili bool", null);
		}
	}
	
	public void visit(PrintCondition cond) {
		if(cond.getExpr().struct.getKind()==Struct.Array) {
			if(cond.getExpr().struct.getElemType()!=Tab.intType && cond.getExpr().struct.getElemType()!=Tab.charType && cond.getExpr().struct.getElemType()!=boolType) {
				report_error("Greska na liniji "+ cond.getLine()+" : promenljiva u print iskazu nije tipa int, char ili bool", null);
			}
		}
		else if(cond.getExpr().struct!=Tab.intType && cond.getExpr().struct!=Tab.charType && cond.getExpr().struct!=boolType) {
			report_error("Greska na liniji "+ cond.getLine()+" : promenljiva u print iskazu nije tipa int, char ili bool", null);
		}
	}
	
	public void visit(MorePrintCondition cond) {
		if(cond.getExpr().struct.getKind()==Struct.Array) {
			if(cond.getExpr().struct.getElemType()!=Tab.intType && cond.getExpr().struct.getElemType()!=Tab.charType && cond.getExpr().struct.getElemType()!=boolType) {
				report_error("Greska na liniji "+ cond.getLine()+" : promenljiva u print iskazu nije tipa int, char ili bool", null);
			}
		}
		else if(cond.getExpr().struct!=Tab.intType && cond.getExpr().struct!=Tab.charType && cond.getExpr().struct!=boolType) {
			report_error("Greska na liniji "+ cond.getLine()+" : promenljiva u print iskazu nije tipa int, char ili bool", null);
		}
	}
	
	
	public boolean passed() {
		return !errorDetected;
	}
	
}

