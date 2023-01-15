// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class MoreFormPars extends FormPars {

    private Type Type;
    private String formParsIdent;
    private VarSquares VarSquares;

    public MoreFormPars (Type Type, String formParsIdent, VarSquares VarSquares) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formParsIdent=formParsIdent;
        this.VarSquares=VarSquares;
        if(VarSquares!=null) VarSquares.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormParsIdent() {
        return formParsIdent;
    }

    public void setFormParsIdent(String formParsIdent) {
        this.formParsIdent=formParsIdent;
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
        buffer.append("MoreFormPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formParsIdent);
        buffer.append("\n");

        if(VarSquares!=null)
            buffer.append(VarSquares.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MoreFormPars]");
        return buffer.toString();
    }
}
