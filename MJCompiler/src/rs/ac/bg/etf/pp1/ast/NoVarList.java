// generated with ast extension for cup
// version 0.8
// 27/0/2023 18:1:23


package rs.ac.bg.etf.pp1.ast;

public class NoVarList extends VarDeclList {

    public NoVarList () {
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
        buffer.append("NoVarList(\n");

        buffer.append(tab);
        buffer.append(") [NoVarList]");
        return buffer.toString();
    }
}
