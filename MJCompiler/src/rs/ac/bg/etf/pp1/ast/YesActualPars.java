// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class YesActualPars extends ActualPars {

    private ActualParsList ActualParsList;

    public YesActualPars (ActualParsList ActualParsList) {
        this.ActualParsList=ActualParsList;
        if(ActualParsList!=null) ActualParsList.setParent(this);
    }

    public ActualParsList getActualParsList() {
        return ActualParsList;
    }

    public void setActualParsList(ActualParsList ActualParsList) {
        this.ActualParsList=ActualParsList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParsList!=null) ActualParsList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParsList!=null) ActualParsList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParsList!=null) ActualParsList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesActualPars(\n");

        if(ActualParsList!=null)
            buffer.append(ActualParsList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesActualPars]");
        return buffer.toString();
    }
}
