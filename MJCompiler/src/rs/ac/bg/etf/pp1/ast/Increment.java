// generated with ast extension for cup
// version 0.8
// 10/0/2023 3:23:6


package rs.ac.bg.etf.pp1.ast;

public class Increment extends FirstDesignatorPart {

    public Increment () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Increment(\n");

        buffer.append(tab);
        buffer.append(") [Increment]");
        return buffer.toString();
    }
}
