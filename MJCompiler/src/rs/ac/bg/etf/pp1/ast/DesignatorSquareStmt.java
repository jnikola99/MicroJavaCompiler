// generated with ast extension for cup
// version 0.8
// 11/0/2023 4:28:10


package rs.ac.bg.etf.pp1.ast;

public class DesignatorSquareStmt extends DesignatorStatement {

    private DesignatorNot DesignatorNot;
    private Designator Designator;

    public DesignatorSquareStmt (DesignatorNot DesignatorNot, Designator Designator) {
        this.DesignatorNot=DesignatorNot;
        if(DesignatorNot!=null) DesignatorNot.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DesignatorNot getDesignatorNot() {
        return DesignatorNot;
    }

    public void setDesignatorNot(DesignatorNot DesignatorNot) {
        this.DesignatorNot=DesignatorNot;
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
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorNot!=null) DesignatorNot.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorNot!=null) DesignatorNot.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorSquareStmt(\n");

        if(DesignatorNot!=null)
            buffer.append(DesignatorNot.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorSquareStmt]");
        return buffer.toString();
    }
}
