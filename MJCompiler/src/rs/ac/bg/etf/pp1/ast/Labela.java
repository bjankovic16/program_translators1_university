// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class Labela extends Label {

    private String lab;

    public Labela (String lab) {
        this.lab=lab;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab=lab;
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
        buffer.append("Labela(\n");

        buffer.append(" "+tab+lab);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Labela]");
        return buffer.toString();
    }
}
