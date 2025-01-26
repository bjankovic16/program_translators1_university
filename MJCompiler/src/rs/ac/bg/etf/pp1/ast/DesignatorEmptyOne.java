// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class DesignatorEmptyOne extends DesignatorArrayCallFu {

    private EmptyArr EmptyArr;
    private DesignatorWithComma DesignatorWithComma;

    public DesignatorEmptyOne (EmptyArr EmptyArr, DesignatorWithComma DesignatorWithComma) {
        this.EmptyArr=EmptyArr;
        if(EmptyArr!=null) EmptyArr.setParent(this);
        this.DesignatorWithComma=DesignatorWithComma;
        if(DesignatorWithComma!=null) DesignatorWithComma.setParent(this);
    }

    public EmptyArr getEmptyArr() {
        return EmptyArr;
    }

    public void setEmptyArr(EmptyArr EmptyArr) {
        this.EmptyArr=EmptyArr;
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
        if(EmptyArr!=null) EmptyArr.accept(visitor);
        if(DesignatorWithComma!=null) DesignatorWithComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EmptyArr!=null) EmptyArr.traverseTopDown(visitor);
        if(DesignatorWithComma!=null) DesignatorWithComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EmptyArr!=null) EmptyArr.traverseBottomUp(visitor);
        if(DesignatorWithComma!=null) DesignatorWithComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorEmptyOne(\n");

        if(EmptyArr!=null)
            buffer.append(EmptyArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorWithComma!=null)
            buffer.append(DesignatorWithComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorEmptyOne]");
        return buffer.toString();
    }
}
