// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class WhileStmt extends Statement {

    private WhileBeg WhileBeg;
    private Condition Condition;
    private StatWhileBeg StatWhileBeg;
    private Statement Statement;

    public WhileStmt (WhileBeg WhileBeg, Condition Condition, StatWhileBeg StatWhileBeg, Statement Statement) {
        this.WhileBeg=WhileBeg;
        if(WhileBeg!=null) WhileBeg.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.StatWhileBeg=StatWhileBeg;
        if(StatWhileBeg!=null) StatWhileBeg.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public WhileBeg getWhileBeg() {
        return WhileBeg;
    }

    public void setWhileBeg(WhileBeg WhileBeg) {
        this.WhileBeg=WhileBeg;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public StatWhileBeg getStatWhileBeg() {
        return StatWhileBeg;
    }

    public void setStatWhileBeg(StatWhileBeg StatWhileBeg) {
        this.StatWhileBeg=StatWhileBeg;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WhileBeg!=null) WhileBeg.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(StatWhileBeg!=null) StatWhileBeg.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileBeg!=null) WhileBeg.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(StatWhileBeg!=null) StatWhileBeg.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileBeg!=null) WhileBeg.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(StatWhileBeg!=null) StatWhileBeg.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileStmt(\n");

        if(WhileBeg!=null)
            buffer.append(WhileBeg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatWhileBeg!=null)
            buffer.append(StatWhileBeg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileStmt]");
        return buffer.toString();
    }
}
