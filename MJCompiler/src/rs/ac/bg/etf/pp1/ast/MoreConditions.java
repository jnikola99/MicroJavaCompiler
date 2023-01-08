// generated with ast extension for cup
// version 0.8
// 8/0/2023 2:33:1


package rs.ac.bg.etf.pp1.ast;

public class MoreConditions extends Condition {

    private CondTerm CondTerm;
    private ConditionMore ConditionMore;

    public MoreConditions (CondTerm CondTerm, ConditionMore ConditionMore) {
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
        this.ConditionMore=ConditionMore;
        if(ConditionMore!=null) ConditionMore.setParent(this);
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public ConditionMore getConditionMore() {
        return ConditionMore;
    }

    public void setConditionMore(ConditionMore ConditionMore) {
        this.ConditionMore=ConditionMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondTerm!=null) CondTerm.accept(visitor);
        if(ConditionMore!=null) ConditionMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
        if(ConditionMore!=null) ConditionMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        if(ConditionMore!=null) ConditionMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreConditions(\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionMore!=null)
            buffer.append(ConditionMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreConditions]");
        return buffer.toString();
    }
}
