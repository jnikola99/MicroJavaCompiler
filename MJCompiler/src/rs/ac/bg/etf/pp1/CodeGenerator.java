package rs.ac.bg.etf.pp1;
import rs.etf.pp1.mj.runtime.Code;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{
	private int mainPc;
	List<String> designatori = new ArrayList<String>();
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(DesignatorYes des) {
		designatori.add(des.getDesignator().obj.getName());
	}
	
	public void visit(DesignatorNo des) {
		designatori.add(null);
	}
	
	public void visit(DesignatorSquareStmt stmt) {
		
	}
	
	public void visit(MoreDesignatorSquare stmt) {
		
	}
	
	public void visit(ReadCondition read) {
		Struct s = read.getDesignator().obj.getType();
		if(s != Tab.charType) {
			Code.put(Code.read);
			Code.store(read.getDesignator().obj);
		}
		else {
			Code.put(Code.bread);
			Code.store(read.getDesignator().obj);
		}
	}
	
	public void visit(PrintCondition print) {
		Struct s = print.getExpr().struct;
		if(s.getKind()==Struct.Array) {
			if(s.getElemType()!=Tab.charType) {
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else {
				Code.loadConst(1);
				Code.put(Code.bprint);
			}
		}
		else {
		if(s != Tab.charType) {
			Code.loadConst(5);
			Code.put(Code.print);
			}
		else {
			Code.loadConst(1);
			Code.put(Code.bprint);
			}
		}
	}
	
	public void visit(MorePrintCondition print) {
		Struct s = print.getExpr().struct;
		if(s.getKind()==Struct.Array) {
			if(s.getElemType()!=Tab.charType) {
				Code.loadConst(print.getN1());
				Code.put(Code.print);
			}
			else {
				Code.loadConst(print.getN1());
				Code.put(Code.bprint);
			}
		}
		else {
		if(s != Tab.charType) {
			Code.loadConst(print.getN1());
			Code.put(Code.print);
			}
		else {
			Code.loadConst(print.getN1());
			Code.put(Code.bprint);
			}
		}
	}
	
	public void visit(VarDeclOneTime VarDeclOneTime) {
		Code.put(Code.new_);
		Code.put2(4);
	}
	
	
	public void visit(OneVar OneVar) {
		Code.put(Code.new_);
		Code.put2(4);
	}
	
	public void visit(MultipleVars MultipleVars) {
		Code.put(Code.new_);
		Code.put2(4);
	}
	
	public void visit(MoreVarDecl MoreVarDecl) {
		Code.put(Code.new_);
		Code.put2(4);
	}
	
	public void visit(NegativeExpr expr) {
		Code.put(Code.neg);
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
	
	public void visit(FactorNewTypeExpr factor) {
		Code.put(Code.newarray);
		if(factor.getType().struct==Tab.charType) {
			Code.loadConst(0);
		}
		else {
			Code.loadConst(1);
		}
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
			if(des.getDesignator().getClass()==DesignatorWithExpr.class) {
				Code.put(Code.astore);
			}else
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
	
	public void visit(TermMore term) {
		if(term.getMulop().getClass() == MulopItem.class) {
			Code.put(Code.mul);
		}
		else if(term.getMulop().getClass() == Divop.class) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
	}
	
	public void visit(DesignatorInc des) {
		Code.put(Code.const_1);
		Code.put(Code.add);
		Code.store(des.getDesignator().obj);
	}
	
	public void visit(DesignatorDec des) {
		Code.put(Code.const_1);
		Code.put(Code.sub);
		Code.store(des.getDesignator().obj);
	}
	
	public void visit(DesignatorWithExpr des) {
		SyntaxNode parent = des.getParent();
		
		if(DesignatorAssign.class != parent.getClass()) {
			Code.put(Code.aload);
		}
	}
}