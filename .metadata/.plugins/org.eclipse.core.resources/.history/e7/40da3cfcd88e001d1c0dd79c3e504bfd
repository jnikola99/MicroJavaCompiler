package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;

public class RuleVisitor extends VisitorAdaptor{

	int varDeclCount = 0;
	int printCount = 0;
	Logger log = Logger.getLogger(getClass());

	public void visit(VarDeclOneTime VarDeclOneTime) {
		varDeclCount++;
	}
	
	public void visit(PrintCondition PrintCondition) {
		printCount++;
	}
	
	public void visit(OneVar OneVar) {
		varDeclCount++;
	}
	
	public void visit(MultipleVars MultipleVars) {
		varDeclCount++;
	}
	
	public void visit(MoreVarDecl MoreVarDecl) {
		varDeclCount++;
	}

}
