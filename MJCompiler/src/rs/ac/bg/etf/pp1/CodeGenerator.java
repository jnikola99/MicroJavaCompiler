package rs.ac.bg.etf.pp1;
import rs.etf.pp1.mj.runtime.Code;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{
	private int mainPc;
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(PrintCondition print) {
		Struct s = print.getExpr().struct;
		if(s != Tab.charType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}
	
	public void visit(MorePrintCondition print) {
		Struct s = print.getExpr().struct;
		
		Code.loadConst(print.getN1());
		if(s != Tab.charType) {
			Code.put(Code.print);
		}
		else {
			Code.put(Code.bprint);
		}
	}
	
	public void visit(FactorNumber cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getN1());
		Code.load(con);
	}
	
	public void visit(FactorChar cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		con.setAdr(cnst.getC1().charAt(0));
		Code.load(con);
	}
	
	public void visit(FactorBool cnst) {
		Obj con = Tab.insert(Obj.Con, "$", cnst.struct);
		con.setLevel(0);
		if(cnst.getB1().equals("true")) {
			con.setAdr(1);
		}
		else {
			con.setAdr(0);
		}
		Code.load(con);
	}
	
	public void visit(MethTypeVoid meth) {
		if("main".equalsIgnoreCase(meth.getMethName())){
			mainPc = Code.pc;
		}
		meth.obj.setAdr(Code.pc);
		
		SyntaxNode methodNode = meth.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		Code.put(Code.enter);
		Code.put(0);
		Code.put(varCnt.getCount());
	}
	
	public void visit(MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorAssign des) {
		Code.store(des.getDesignator().obj);
	}
	
	public void visit(OneDesignatorIdent des) {
		SyntaxNode parent = des.getParent();
		
		if(DesignatorAssign.class != parent.getClass()) {
			Code.load(des.obj);
		}
	}
	
	public void visit(NextExpr expr) {
		if(expr.getAddop().getClass() == AddopItem.class) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
}
