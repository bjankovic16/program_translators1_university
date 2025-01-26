// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class DesignatorCallFuMul extends DesignatorArrayCallFu {

    private DesignatorArrayCallFu DesignatorArrayCallFu;
    private DesignatorWithComma DesignatorWithComma;

    public DesignatorCallFuMul (DesignatorArrayCallFu DesignatorArrayCallFu, DesignatorWithComma DesignatorWithComma) {
        this.DesignatorArrayCallFu=DesignatorArrayCallFu;
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.setParent(this);
        this.DesignatorWithComma=DesignatorWithComma;
        if(DesignatorWithComma!=null) DesignatorWithComma.setParent(this);
    }

    public DesignatorArrayCallFu getDesignatorArrayCallFu() {
        return DesignatorArrayCallFu;
    }

    public void setDesignatorArrayCallFu(DesignatorArrayCallFu DesignatorArrayCallFu) {
        this.DesignatorArrayCallFu=DesignatorArrayCallFu;
    }

    public DesignatorWithComma getDesignatorWithComma() {
        return DesignatorWithComma;
    }

    public void setDesignatorWithComma(DesignatorWithComma DesignatorWithComma) {
        this.DesignatorWithComma=DesignatorWithComma;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.accept(visitor);
        if(DesignatorWithComma!=null) DesignatorWithComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.traverseTopDown(visitor);
        if(DesignatorWithComma!=null) DesignatorWithComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.traverseBottomUp(visitor);
        if(DesignatorWithComma!=null) DesignatorWithComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorCallFuMul(\n");

        if(DesignatorArrayCallFu!=null)
            buffer.append(DesignatorArrayCallFu.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorWithComma!=null)
            buffer.append(DesignatorWithComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorCallFuMul]");
        return buffer.toString();
    }
}
