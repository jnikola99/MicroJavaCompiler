// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:40:29


package rs.ac.bg.etf.pp1.ast;

public class NoFormParsList extends FormParsList {

    public NoFormParsList () {
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
        buffer.append("NoFormParsList(\n");

        buffer.append(tab);
        buffer.append(") [NoFormParsList]");
        return buffer.toString();
    }
}