// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class MethodReturnVoid extends MethodReturnTypeIdent {

    private String nameMeth;

    public MethodReturnVoid (String nameMeth) {
        this.nameMeth=nameMeth;
    }

    public String getNameMeth() {
        return nameMeth;
    }

    public void setNameMeth(String nameMeth) {
        this.nameMeth=nameMeth;
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
        buffer.append("MethodReturnVoid(\n");

        buffer.append(" "+tab+nameMeth);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodReturnVoid]");
        return buffer.toString();
    }
}
