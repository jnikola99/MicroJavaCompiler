// generated with ast extension for cup
// version 0.8
// 15/0/2023 0:32:55


package rs.ac.bg.etf.pp1.ast;

public class MoreCondFactMore extends CondFactMore {

    private CondFactMore CondFactMore;
    private CondFact CondFact;

    public MoreCondFactMore (CondFactMore CondFactMore, CondFact CondFact) {
        this.CondFactMore=CondFactMore;
        if(CondFactMore!=null) CondFactMore.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public CondFactMore getCondFactMore() {
        return CondFactMore;
    }

    public void setCondFactMore(CondFactMore CondFactMore) {
        this.CondFactMore=CondFactMore;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFactMore!=null) CondFactMore.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFactMore!=null) CondFactMore.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFactMore!=null) CondFactMore.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreCondFactMore(\n");

        if(CondFactMore!=null)
            buffer.append(CondFactMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreCondFactMore]");
        return buffer.toString();
    }
}
