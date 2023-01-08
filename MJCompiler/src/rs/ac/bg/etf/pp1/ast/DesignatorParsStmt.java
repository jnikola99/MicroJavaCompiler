// generated with ast extension for cup
// version 0.8
// 8/0/2023 2:33:1


package rs.ac.bg.etf.pp1.ast;

public class DesignatorParsStmt extends DesignatorStatement {

    private Designator Designator;
    private FirstDesignatorPart FirstDesignatorPart;

    public DesignatorParsStmt (Designator Designator, FirstDesignatorPart FirstDesignatorPart) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FirstDesignatorPart=FirstDesignatorPart;
        if(FirstDesignatorPart!=null) FirstDesignatorPart.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FirstDesignatorPart getFirstDesignatorPart() {
        return FirstDesignatorPart;
    }

    public void setFirstDesignatorPart(FirstDesignatorPart FirstDesignatorPart) {
        this.FirstDesignatorPart=FirstDesignatorPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FirstDesignatorPart!=null) FirstDesignatorPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FirstDesignatorPart!=null) FirstDesignatorPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FirstDesignatorPart!=null) FirstDesignatorPart.traverseBottomUp(visitor);
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

        if(FirstDesignatorPart!=null)
            buffer.append(FirstDesignatorPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorParsStmt]");
        return buffer.toString();
    }
}
