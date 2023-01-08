// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:40:29


package rs.ac.bg.etf.pp1.ast;

public class OneDesignatorIdent extends Designator {

    private String designatorName;

    public OneDesignatorIdent (String designatorName) {
        this.designatorName=designatorName;
    }

    public String getDesignatorName() {
        return designatorName;
    }

    public void setDesignatorName(String designatorName) {
        this.designatorName=designatorName;
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
        buffer.append("OneDesignatorIdent(\n");

        buffer.append(" "+tab+designatorName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneDesignatorIdent]");
        return buffer.toString();
    }
}