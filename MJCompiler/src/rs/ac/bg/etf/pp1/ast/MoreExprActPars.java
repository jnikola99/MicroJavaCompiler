// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class MoreExprActPars extends ActPars {

    private Expr Expr;
    private ActParsMore ActParsMore;

    public MoreExprActPars (Expr Expr, ActParsMore ActParsMore) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ActParsMore=ActParsMore;
        if(ActParsMore!=null) ActParsMore.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ActParsMore getActParsMore() {
        return ActParsMore;
    }

    public void setActParsMore(ActParsMore ActParsMore) {
        this.ActParsMore=ActParsMore;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(ActParsMore!=null) ActParsMore.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ActParsMore!=null) ActParsMore.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ActParsMore!=null) ActParsMore.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreExprActPars(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsMore!=null)
            buffer.append(ActParsMore.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreExprActPars]");
        return buffer.toString();
    }
}
