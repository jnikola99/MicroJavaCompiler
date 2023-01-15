// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclOneTime extends ConstDecl {

    private Type Type;
    private String someConst;
    private ConstType ConstType;

    public ConstDeclOneTime (Type Type, String someConst, ConstType ConstType) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.someConst=someConst;
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstType!=null) ConstType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclOneTime(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+someConst);
        buffer.append("\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclOneTime]");
        return buffer.toString();
    }
}
