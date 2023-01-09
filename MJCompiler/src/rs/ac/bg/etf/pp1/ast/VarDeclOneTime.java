// generated with ast extension for cup
// version 0.8
// 10/0/2023 0:21:0


package rs.ac.bg.etf.pp1.ast;

public class VarDeclOneTime extends VarDecl {

    private Type Type;
    private String varDecName;
    private VarSquares VarSquares;

    public VarDeclOneTime (Type Type, String varDecName, VarSquares VarSquares) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varDecName=varDecName;
        this.VarSquares=VarSquares;
        if(VarSquares!=null) VarSquares.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarDecName() {
        return varDecName;
    }

    public void setVarDecName(String varDecName) {
        this.varDecName=varDecName;
    }

    public VarSquares getVarSquares() {
        return VarSquares;
    }

    public void setVarSquares(VarSquares VarSquares) {
        this.VarSquares=VarSquares;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarSquares!=null) VarSquares.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarSquares!=null) VarSquares.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarSquares!=null) VarSquares.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclOneTime(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varDecName);
        buffer.append("\n");

        if(VarSquares!=null)
            buffer.append(VarSquares.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclOneTime]");
        return buffer.toString();
    }
}
