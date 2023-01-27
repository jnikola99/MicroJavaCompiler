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
	List<Obj> designatori = new ArrayList<Obj>();
	List<Integer> indexi = new ArrayList<Integer>();
	private boolean negBool=true;
	private int currIndex=0;
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(DesignatorYes des) {
		designatori.add(des.getDesignator().obj);
	}
	
	public void visit(DesignatorNo des) {
		designatori.add(null);
	}
	
	public void visit(DesignatorSquareStmt stmt) {
		Obj o = designatori.get(0);
		if(o!=null) {
			Code.load(stmt.getDesignator().obj);
			Code.loadConst(0);
			if(stmt.getDesignator().obj.getType().getElemType()==Tab.charType) {
				Code.put(Code.baload);
			}
			else
			Code.put(Code.aload);
			if(stmt.getDesignatorNot().obj.getType().getKind()!=Struct.Array) {
				Code.store(stmt.getDesignatorNot().obj);
			}
			else {
				if(stmt.getDesignatorNot().obj.getType().getElemType()==Tab.charType) {
					Code.put(Code.bastore);
				}
				else
				Code.put(Code.astore);
			}
		}
		designatori.clear();
		//indexi.clear();
		
	}
	
	public void visit(MoreDesignatorSquare stmt) {
		int index = 0;
		/*int count = 0;
		for(int i=0;i<designatori.size();i++) {
			if(designatori.get(i)!=null) {
				if(designatori.get(i).getType().getKind()==Struct.Array) {
						count++;
					}
				}
		}*/
		for(int i=0;i<designatori.size();i++) {
			if(designatori.get(i)!=null) {
				if(designatori.get(i).getType().getKind()!=Struct.Array) {
					Code.load(stmt.getDesignator().obj);
					Code.loadConst(i);
					
					if(stmt.getDesignator().obj.getType().getElemType()==Tab.charType) {
						Code.put(Code.baload);
					}
					else
						Code.put(Code.aload);
					
					Code.store(designatori.get(i));
					indexi.add(i);
				}
				else {
					Code.load(stmt.getDesignator().obj);
					Code.loadConst(designatori.size()-i-1);
					
					if(stmt.getDesignator().obj.getType().getElemType()==Tab.charType) {
						Code.put(Code.baload);
					}
					else
						Code.put(Code.aload);
					
					Code.put(Code.astore);
				}
			}
		}
		designatori.clear();
		indexi.clear();
	}
	
	public void visit(ReadCondition read) {
		Struct s;
		if(read.getDesignator().obj.getType().getKind()==Struct.Array) {
			s = read.getDesignator().obj.getType().getElemType();
		}
		else {
			s = read.getDesignator().obj.getType();
		}
		if(s != Tab.charType) {
			Code.put(Code.read);
			if(read.getDesignator().obj.getType().getKind()==Struct.Array) {
				Code.put(Code.astore);
			}
			else {
				Code.store(read.getDesignator().obj);
			}
		}
		else {
			Code.put(Code.bread);
			if(read.getDesignator().obj.getType().getKind()==Struct.Array) {
				Code.put(Code.bastore);
			}
			else {
				Code.store(read.getDesignator().obj);
			}
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
		negBool=true;
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
				if(des.getDesignator().obj.getType().getElemType()==Tab.charType) {
					Code.put(Code.bastore);
				}
				else
				Code.put(Code.astore);
			}else
			Code.store(des.getDesignator().obj);
	}
	
	public void visit(OneDesignatorIdent des) {
		SyntaxNode parent = des.getParent();
		
		if(DesignatorAssign.class != parent.getClass() && DesignatorYes.class != parent.getClass()  && DesignatorSquareStmt.class != parent.getClass() && MoreDesignatorSquare.class != parent.getClass()) {
			Code.load(des.obj);
		}
	}
	
	public void visit(FirstExpr expr) {
		boolean treba = false;
		SyntaxNode parent = expr.getParent();
		while(parent!=null) {
			if(parent.getClass()==NegativeExpr.class) {
				treba=true;
				break;
			}
			parent=parent.getParent();
		}
			if(negBool && treba) {
				Code.put(Code.neg);
				negBool=false;
			}
	}
	
	public void visit(NextExpr expr) {
		if(expr.getAddop().getClass() == AddopItem.class) {
			Code.put(Code.add);
		}
		else if(expr.getAddop().getClass() == Subop.class){
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
		if(des.getDesignator().obj.getType().getKind()==Struct.Array) {
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.put(Code.astore);
		}else {
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(des.getDesignator().obj);
		}
	}
	
	public void visit(DesignatorDec des) {
		if(des.getDesignator().obj.getType().getKind()==Struct.Array) {
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.put(Code.astore);
		}else {
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(des.getDesignator().obj);
		}
	}
	
	
	public void visit(DesignatorWithExpr des) {
		SyntaxNode parent = des.getParent();
		if(DesignatorAssign.class != parent.getClass() && DesignatorYes.class != parent.getClass() && ReadCondition.class != parent.getClass()) {
			if(des.getDesignator().obj.getType().getElemType()==Tab.charType) {
				if(DesignatorInc.class == parent.getClass()|| DesignatorDec.class == parent.getClass())Code.put(Code.dup2);
				Code.put(Code.baload);
			}
			else {
				if(DesignatorInc.class == parent.getClass()|| DesignatorDec.class == parent.getClass())Code.put(Code.dup2);
				Code.put(Code.aload);
			}
		}
	}
}
