// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:40:29


package rs.ac.bg.etf.pp1.ast;

public class AssignopItem extends Assignop {

    public AssignopItem () {
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
        buffer.append("AssignopItem(\n");

        buffer.append(tab);
        buffer.append(") [AssignopItem]");
        return buffer.toString();
    }
}
