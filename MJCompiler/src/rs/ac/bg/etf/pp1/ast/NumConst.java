// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class NumConst extends ConstType {

    private Integer constType;

    public NumConst (Integer constType) {
        this.constType=constType;
    }

    public Integer getConstType() {
        return constType;
    }

    public void setConstType(Integer constType) {
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
        buffer.append("NumConst(\n");

        buffer.append(" "+tab+constType);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConst]");
        return buffer.toString();
    }
}
