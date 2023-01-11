// generated with ast extension for cup
// version 0.8
// 11/0/2023 5:45:28


package rs.ac.bg.etf.pp1.ast;

public class MoreCondFact extends CondTerm {

    private CondFact CondFact;
    private CondFactMore CondFactMore;

    public MoreCondFact (CondFact CondFact, CondFactMore CondFactMore) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.CondFactMore=CondFactMore;
        if(CondFactMore!=null) CondFactMore.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public CondFactMore getCondFactMore() {
        return CondFactMore;
    }

    public void setCondFactMore(CondFactMore CondFactMore) {
        this.CondFactMore=CondFactMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(CondFactMore!=null) CondFactMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(CondFactMore!=null) CondFactMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(CondFactMore!=null) CondFactMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreCondFact(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactMore!=null)
            buffer.append(CondFactMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreCondFact]");
        return buffer.toString();
    }
}
