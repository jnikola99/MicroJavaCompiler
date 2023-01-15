// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class Greaterequal extends Relop {

    public Greaterequal () {
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
        buffer.append("Greaterequal(\n");

        buffer.append(tab);
        buffer.append(") [Greaterequal]");
        return buffer.toString();
    }
}
