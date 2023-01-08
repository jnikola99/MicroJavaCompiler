// generated with ast extension for cup
// version 0.8
// 8/0/2023 2:33:1


package rs.ac.bg.etf.pp1.ast;

public class OneConstType extends ConstTypeList {

    private String constVar;
    private ConstType ConstType;

    public OneConstType (String constVar, ConstType ConstType) {
        this.constVar=constVar;
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
    }

    public String getConstVar() {
        return constVar;
    }

    public void setConstVar(String constVar) {
        this.constVar=constVar;
    }

    public ConstType getConstType() {
        return ConstType;
    }

    public void setConstType(ConstType ConstType) {
        this.ConstType=ConstType;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstType!=null) ConstType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneConstType(\n");

        buffer.append(" "+tab+constVar);
        buffer.append("\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneConstType]");
        return buffer.toString();
    }
}
