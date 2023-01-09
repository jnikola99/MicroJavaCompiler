package rs.ac.bg.etf.pp1;
import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	int level=0;
	int nVars;
	Struct typevar = null;

	Logger log = Logger.getLogger(getClass());
	

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
	
	//VISIT ZA VAR
	public void visit(VarDeclOneTime varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarDecName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarDecName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		report_info("Deklarisana promenljiva "+ varDeclOne.getVarDecName(), varDeclOne);
		Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarDecName(), varDeclOne.getType().struct);
	}
	
	public void visit(MoreVarDecl varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarDecName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarDecName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		report_info("Deklarisana promenljiva "+ varDeclOne.getVarDecName(), varDeclOne);
		Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarDecName(), varDeclOne.getType().struct);
	}
	
	public void visit(OneVar varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		report_info("Deklarisana promenljiva "+ varDeclOne.getVarName(), varDeclOne);
		Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarName(),typevar);
	}
	
	public void visit(MultipleVars varDeclOne) {
		if(!(Tab.currentScope().findSymbol(varDeclOne.getVarName()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+varDeclOne.getVarName()+" je deklarisana vec!",varDeclOne);
			return;
		}
		report_info("Deklarisana promenljiva "+ varDeclOne.getVarName(), varDeclOne);
		Obj varNode = Tab.insert(Obj.Var, varDeclOne.getVarName(),typevar);
	}
	
	//KONSTANTNE PROMENLJIVE
	public void visit(ConstDeclOneTime constDecl) {
		if(!(Tab.currentScope().findSymbol(constDecl.getSomeConst()) == null)) {
			report_error("Greska, promenljiva sa imenom: "+constDecl.getSomeConst()+" je deklarisana vec!",constDecl);
			return;
		}
		String typeName=constDecl.getType().getTypeName();
		if(!(typeName.equals("int") && (constDecl.getConstType().struct==Tab.intType))) {
			report_error("Greska, nekompatibilni tipovi kod promenljive: "+constDecl.getSomeConst(),constDecl);
			return;
		}
		report_info("Deklarisana konstanta "+ constDecl.getSomeConst(), constDecl);
		Obj constNode = Tab.insert(Obj.Con,constDecl.getSomeConst(),typevar);
		
	}
	
	//TIPOVI KONSTANTI
	public void visit(NumConst cnst) {
		cnst.struct = Tab.intType;
	}
	
	public void visit(CharConst cnst) {
		cnst.struct = Tab.charType;
	}
	
	public void visit(TermOne term) {
		term.struct = term.getFactor().struct;
	}
	
	public void visit(TermMore term) {
		Struct t = term.getTerm().struct;
		Struct e = term.getFactor().struct;
		if(t.equals(e) && e==Tab.intType) {
			term.struct=e;
		}
		else {
			report_error("Greska na liniji "+ term.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
			term.struct = Tab.noType;
		}
	}
	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();     	
	}
	
	public void visit(Program program) {
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
	}
	
	public void visit(ReturnCondition returnExpr) {
		returnFound = true;
		Struct currMethType = currentMethod.getType();
		if(!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}
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
	
	public boolean passed() {
		return !errorDetected;
	}
	
}

