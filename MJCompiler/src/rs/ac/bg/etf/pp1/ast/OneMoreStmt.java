// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class OneMoreStmt extends Statement {

    private StatementHN StatementHN;

    public OneMoreStmt (StatementHN StatementHN) {
        this.StatementHN=StatementHN;
        if(StatementHN!=null) StatementHN.setParent(this);
    }

    public StatementHN getStatementHN() {
        return StatementHN;
    }

    public void setStatementHN(StatementHN StatementHN) {
        this.StatementHN=StatementHN;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementHN!=null) StatementHN.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementHN!=null) StatementHN.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementHN!=null) StatementHN.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneMoreStmt(\n");

        if(StatementHN!=null)
            buffer.append(StatementHN.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneMoreStmt]");
        return buffer.toString();
    }
}
