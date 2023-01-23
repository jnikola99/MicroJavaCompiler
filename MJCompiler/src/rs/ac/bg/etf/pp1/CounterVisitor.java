package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class CounterVisitor extends VisitorAdaptor{

protected int count;
	
	public int getCount(){
		return count;
	}

	public static class VarCounter extends CounterVisitor{
		public void visit(VarDeclOneTime VarDeclOneTime) {
			count++;
		}
		
		
		public void visit(OneVar OneVar) {
			count++;
		}
		
		public void visit(MultipleVars MultipleVars) {
			count++;
		}
		
		public void visit(MoreVarDecl MoreVarDecl) {
			count++;
		}
		
	}

}
