// generated with ast extension for cup
// version 0.8
// 7/0/2023 23:31:20


package rs.ac.bg.etf.pp1.ast;

public class MoreVarDecl extends VarDecl {

    private Type Type;
    private String varDecName;
    private VarSquares VarSquares;
    private VarMultiple VarMultiple;

    public MoreVarDecl (Type Type, String varDecName, VarSquares VarSquares, VarMultiple VarMultiple) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varDecName=varDecName;
        this.VarSquares=VarSquares;
        if(VarSquares!=null) VarSquares.setParent(this);
        this.VarMultiple=VarMultiple;
        if(VarMultiple!=null) VarMultiple.setParent(this);
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

    public VarMultiple getVarMultiple() {
        return VarMultiple;
    }

    public void setVarMultiple(VarMultiple VarMultiple) {
        this.VarMultiple=VarMultiple;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarSquares!=null) VarSquares.accept(visitor);
        if(VarMultiple!=null) VarMultiple.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarSquares!=null) VarSquares.traverseTopDown(visitor);
        if(VarMultiple!=null) VarMultiple.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarSquares!=null) VarSquares.traverseBottomUp(visitor);
        if(VarMultiple!=null) VarMultiple.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MoreVarDecl(\n");

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

        if(VarMultiple!=null)
            buffer.append(VarMultiple.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreVarDecl]");
        return buffer.toString();
    }
}
