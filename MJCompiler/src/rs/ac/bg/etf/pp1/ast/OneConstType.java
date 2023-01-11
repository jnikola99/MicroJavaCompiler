// generated with ast extension for cup
// version 0.8
// 11/0/2023 4:28:10


package rs.ac.bg.etf.pp1.ast;

public class OneConstType extends ConstTypeList {

    private String someConst;
    private ConstType ConstType;

    public OneConstType (String someConst, ConstType ConstType) {
        this.someConst=someConst;
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
    }

    public String getSomeConst() {
        return someConst;
    }

    public void setSomeConst(String someConst) {
        this.someConst=someConst;
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

        buffer.append(" "+tab+someConst);
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
