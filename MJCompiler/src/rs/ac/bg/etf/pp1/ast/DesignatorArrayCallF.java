// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArrayCallF extends DesignatorACall {

    private DesignatorArrayCallFu DesignatorArrayCallFu;

    public DesignatorArrayCallF (DesignatorArrayCallFu DesignatorArrayCallFu) {
        this.DesignatorArrayCallFu=DesignatorArrayCallFu;
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.setParent(this);
    }

    public DesignatorArrayCallFu getDesignatorArrayCallFu() {
        return DesignatorArrayCallFu;
    }

    public void setDesignatorArrayCallFu(DesignatorArrayCallFu DesignatorArrayCallFu) {
        this.DesignatorArrayCallFu=DesignatorArrayCallFu;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrayCallFu!=null) DesignatorArrayCallFu.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArrayCallF(\n");

        if(DesignatorArrayCallFu!=null)
            buffer.append(DesignatorArrayCallFu.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArrayCallF]");
        return buffer.toString();
    }
}
