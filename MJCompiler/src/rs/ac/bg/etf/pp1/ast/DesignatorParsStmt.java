// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class DesignatorParsStmt extends DesignatorStatement {

    private Designator Designator;
    private DesiActPars DesiActPars;

    public DesignatorParsStmt (Designator Designator, DesiActPars DesiActPars) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesiActPars=DesiActPars;
        if(DesiActPars!=null) DesiActPars.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesiActPars getDesiActPars() {
        return DesiActPars;
    }

    public void setDesiActPars(DesiActPars DesiActPars) {
        this.DesiActPars=DesiActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesiActPars!=null) DesiActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesiActPars!=null) DesiActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesiActPars!=null) DesiActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorParsStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesiActPars!=null)
            buffer.append(DesiActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorParsStmt]");
        return buffer.toString();
    }
}
