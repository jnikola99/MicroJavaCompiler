// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:40:29


package rs.ac.bg.etf.pp1.ast;

public class ActParsDes extends FirstDesignatorPart {

    private DesiActPars DesiActPars;

    public ActParsDes (DesiActPars DesiActPars) {
        this.DesiActPars=DesiActPars;
        if(DesiActPars!=null) DesiActPars.setParent(this);
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
        if(DesiActPars!=null) DesiActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesiActPars!=null) DesiActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesiActPars!=null) DesiActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActParsDes(\n");

        if(DesiActPars!=null)
            buffer.append(DesiActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActParsDes]");
        return buffer.toString();
    }
}
