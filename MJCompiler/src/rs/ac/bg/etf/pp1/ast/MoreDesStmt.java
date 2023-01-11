// generated with ast extension for cup
// version 0.8
// 11/0/2023 4:28:10


package rs.ac.bg.etf.pp1.ast;

public class MoreDesStmt extends DesignatorStmtMore {

    private DesignatorStmtMore DesignatorStmtMore;
    private DesignatorNot DesignatorNot;

    public MoreDesStmt (DesignatorStmtMore DesignatorStmtMore, DesignatorNot DesignatorNot) {
        this.DesignatorStmtMore=DesignatorStmtMore;
        if(DesignatorStmtMore!=null) DesignatorStmtMore.setParent(this);
        this.DesignatorNot=DesignatorNot;
        if(DesignatorNot!=null) DesignatorNot.setParent(this);
    }

    public DesignatorStmtMore getDesignatorStmtMore() {
        return DesignatorStmtMore;
    }

    public void setDesignatorStmtMore(DesignatorStmtMore DesignatorStmtMore) {
        this.DesignatorStmtMore=DesignatorStmtMore;
    }

    public DesignatorNot getDesignatorNot() {
        return DesignatorNot;
    }

    public void setDesignatorNot(DesignatorNot DesignatorNot) {
        this.DesignatorNot=DesignatorNot;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStmtMore!=null) DesignatorStmtMore.accept(visitor);
        if(DesignatorNot!=null) DesignatorNot.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStmtMore!=null) DesignatorStmtMore.traverseTopDown(visitor);
        if(DesignatorNot!=null) DesignatorNot.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStmtMore!=null) DesignatorStmtMore.traverseBottomUp(visitor);
        if(DesignatorNot!=null) DesignatorNot.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreDesStmt(\n");

        if(DesignatorStmtMore!=null)
            buffer.append(DesignatorStmtMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorNot!=null)
            buffer.append(DesignatorNot.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreDesStmt]");
        return buffer.toString();
    }
}
