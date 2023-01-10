// generated with ast extension for cup
// version 0.8
// 10/0/2023 3:23:6


package rs.ac.bg.etf.pp1.ast;

public class OneDesStmt extends DesignatorStmtMore {

    private DesignatorNot DesignatorNot;

    public OneDesStmt (DesignatorNot DesignatorNot) {
        this.DesignatorNot=DesignatorNot;
        if(DesignatorNot!=null) DesignatorNot.setParent(this);
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
        if(DesignatorNot!=null) DesignatorNot.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorNot!=null) DesignatorNot.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorNot!=null) DesignatorNot.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneDesStmt(\n");

        if(DesignatorNot!=null)
            buffer.append(DesignatorNot.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneDesStmt]");
        return buffer.toString();
    }
}
