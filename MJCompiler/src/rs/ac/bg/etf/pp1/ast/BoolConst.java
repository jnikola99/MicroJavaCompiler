// generated with ast extension for cup
// version 0.8
// 8/0/2023 2:33:1


package rs.ac.bg.etf.pp1.ast;

public class BoolConst extends ConstType {

    private String constType;

    public BoolConst (String constType) {
        this.constType=constType;
    }

    public String getConstType() {
        return constType;
    }

    public void setConstType(String constType) {
        this.constType=constType;
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
        buffer.append("BoolConst(\n");

        buffer.append(" "+tab+constType);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConst]");
        return buffer.toString();
    }
}
