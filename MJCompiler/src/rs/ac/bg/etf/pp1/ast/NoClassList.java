// generated with ast extension for cup
// version 0.8
// 10/0/2023 0:21:0


package rs.ac.bg.etf.pp1.ast;

public class NoClassList extends ClassDeclList {

    public NoClassList () {
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
        buffer.append("NoClassList(\n");

        buffer.append(tab);
        buffer.append(") [NoClassList]");
        return buffer.toString();
    }
}
