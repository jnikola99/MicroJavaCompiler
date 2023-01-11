// generated with ast extension for cup
// version 0.8
// 11/0/2023 5:45:28


package rs.ac.bg.etf.pp1.ast;

public class MoreDesignatorSquare extends DesignatorStatement {

    private DesignatorNot DesignatorNot;
    private DesignatorStmtMore DesignatorStmtMore;
    private Designator Designator;

    public MoreDesignatorSquare (DesignatorNot DesignatorNot, DesignatorStmtMore DesignatorStmtMore, Designator Designator) {
        this.DesignatorNot=DesignatorNot;
        if(DesignatorNot!=null) DesignatorNot.setParent(this);
        this.DesignatorStmtMore=DesignatorStmtMore;
        if(DesignatorStmtMore!=null) DesignatorStmtMore.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorNot getDesignatorNot() {
        return DesignatorNot;
    }

    public void setDesignatorNot(DesignatorNot DesignatorNot) {
        this.DesignatorNot=DesignatorNot;
    }

    public DesignatorStmtMore getDesignatorStmtMore() {
        return DesignatorStmtMore;
    }

    public void setDesignatorStmtMore(DesignatorStmtMore DesignatorStmtMore) {
        this.DesignatorStmtMore=DesignatorStmtMore;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorNot!=null) DesignatorNot.accept(visitor);
        if(DesignatorStmtMore!=null) DesignatorStmtMore.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorNot!=null) DesignatorNot.traverseTopDown(visitor);
        if(DesignatorStmtMore!=null) DesignatorStmtMore.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorNot!=null) DesignatorNot.traverseBottomUp(visitor);
        if(DesignatorStmtMore!=null) DesignatorStmtMore.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreDesignatorSquare(\n");

        if(DesignatorNot!=null)
            buffer.append(DesignatorNot.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStmtMore!=null)
            buffer.append(DesignatorStmtMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreDesignatorSquare]");
        return buffer.toString();
    }
}
