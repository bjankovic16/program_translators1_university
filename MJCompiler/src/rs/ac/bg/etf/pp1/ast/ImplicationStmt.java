// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class ImplicationStmt extends Statement {

    private Designator Designator;
    private String condImpl;
    private BegImpli BegImpli;
    private Statement Statement;

    public ImplicationStmt (Designator Designator, String condImpl, BegImpli BegImpli, Statement Statement) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.condImpl=condImpl;
        this.BegImpli=BegImpli;
        if(BegImpli!=null) BegImpli.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public String getCondImpl() {
        return condImpl;
    }

    public void setCondImpl(String condImpl) {
        this.condImpl=condImpl;
    }

    public BegImpli getBegImpli() {
        return BegImpli;
    }

    public void setBegImpli(BegImpli BegImpli) {
        this.BegImpli=BegImpli;
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
        if(Designator!=null) Designator.accept(visitor);
        if(BegImpli!=null) BegImpli.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(BegImpli!=null) BegImpli.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(BegImpli!=null) BegImpli.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ImplicationStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+condImpl);
        buffer.append("\n");

        if(BegImpli!=null)
            buffer.append(BegImpli.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ImplicationStmt]");
        return buffer.toString();
    }
}
