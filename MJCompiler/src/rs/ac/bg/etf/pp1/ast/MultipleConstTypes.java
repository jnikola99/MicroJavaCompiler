// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:40:29


package rs.ac.bg.etf.pp1.ast;

public class MultipleConstTypes extends ConstTypeList {

    private ConstTypeList ConstTypeList;
    private String constVar;
    private ConstType ConstType;

    public MultipleConstTypes (ConstTypeList ConstTypeList, String constVar, ConstType ConstType) {
        this.ConstTypeList=ConstTypeList;
        if(ConstTypeList!=null) ConstTypeList.setParent(this);
        this.constVar=constVar;
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
    }

    public ConstTypeList getConstTypeList() {
        return ConstTypeList;
    }

    public void setConstTypeList(ConstTypeList ConstTypeList) {
        this.ConstTypeList=ConstTypeList;
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
        if(ConstTypeList!=null) ConstTypeList.accept(visitor);
        if(ConstType!=null) ConstType.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstTypeList!=null) ConstTypeList.traverseTopDown(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstTypeList!=null) ConstTypeList.traverseBottomUp(visitor);
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConstTypes(\n");

        if(ConstTypeList!=null)
            buffer.append(ConstTypeList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constVar);
        buffer.append("\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConstTypes]");
        return buffer.toString();
    }
}