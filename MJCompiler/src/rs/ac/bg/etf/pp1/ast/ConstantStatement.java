// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class ConstantStatement extends ConstStatement {

    private ConstStatement ConstStatement;
    private Integer number;

    public ConstantStatement (ConstStatement ConstStatement, Integer number) {
        this.ConstStatement=ConstStatement;
        if(ConstStatement!=null) ConstStatement.setParent(this);
        this.number=number;
    }

    public ConstStatement getConstStatement() {
        return ConstStatement;
    }

    public void setConstStatement(ConstStatement ConstStatement) {
        this.ConstStatement=ConstStatement;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number=number;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstStatement!=null) ConstStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstStatement!=null) ConstStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstStatement!=null) ConstStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstantStatement(\n");

        if(ConstStatement!=null)
            buffer.append(ConstStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+number);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantStatement]");
        return buffer.toString();
    }
}
