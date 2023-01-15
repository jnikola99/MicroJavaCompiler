// generated with ast extension for cup
// version 0.8
// 15/0/2023 1:16:8


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormPars extends FormParsMultiple {

    private FormParsMultiple FormParsMultiple;
    private Type Type;
    private String formIdent;
    private VarSquares VarSquares;

    public MultipleFormPars (FormParsMultiple FormParsMultiple, Type Type, String formIdent, VarSquares VarSquares) {
        this.FormParsMultiple=FormParsMultiple;
        if(FormParsMultiple!=null) FormParsMultiple.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.formIdent=formIdent;
        this.VarSquares=VarSquares;
        if(VarSquares!=null) VarSquares.setParent(this);
    }

    public FormParsMultiple getFormParsMultiple() {
        return FormParsMultiple;
    }

    public void setFormParsMultiple(FormParsMultiple FormParsMultiple) {
        this.FormParsMultiple=FormParsMultiple;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFormIdent() {
        return formIdent;
    }

    public void setFormIdent(String formIdent) {
        this.formIdent=formIdent;
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
        if(FormParsMultiple!=null) FormParsMultiple.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(VarSquares!=null) VarSquares.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsMultiple!=null) FormParsMultiple.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarSquares!=null) VarSquares.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsMultiple!=null) FormParsMultiple.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarSquares!=null) VarSquares.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormPars(\n");

        if(FormParsMultiple!=null)
            buffer.append(FormParsMultiple.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+formIdent);
        buffer.append("\n");

        if(VarSquares!=null)
            buffer.append(VarSquares.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormPars]");
        return buffer.toString();
    }
}
