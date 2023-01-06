// generated with ast extension for cup
// version 0.8
// 6/0/2023 0:11:42


package rs.ac.bg.etf.pp1.ast;

public class MoreConditionMore extends ConditionMore {

    private ConditionMore ConditionMore;
    private CondTerm CondTerm;

    public MoreConditionMore (ConditionMore ConditionMore, CondTerm CondTerm) {
        this.ConditionMore=ConditionMore;
        if(ConditionMore!=null) ConditionMore.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public ConditionMore getConditionMore() {
        return ConditionMore;
    }

    public void setConditionMore(ConditionMore ConditionMore) {
        this.ConditionMore=ConditionMore;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionMore!=null) ConditionMore.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionMore!=null) ConditionMore.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionMore!=null) ConditionMore.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreConditionMore(\n");

        if(ConditionMore!=null)
            buffer.append(ConditionMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreConditionMore]");
        return buffer.toString();
    }
}
