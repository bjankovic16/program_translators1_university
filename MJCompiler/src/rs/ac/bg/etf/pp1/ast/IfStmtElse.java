// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class IfStmtElse extends Statement {

    private IfBeg IfBeg;
    private ConditionIF ConditionIF;
    private Statement Statement;
    private EndIf EndIf;
    private Statement Statement1;
    private ElseBeg ElseBeg;

    public IfStmtElse (IfBeg IfBeg, ConditionIF ConditionIF, Statement Statement, EndIf EndIf, Statement Statement1, ElseBeg ElseBeg) {
        this.IfBeg=IfBeg;
        if(IfBeg!=null) IfBeg.setParent(this);
        this.ConditionIF=ConditionIF;
        if(ConditionIF!=null) ConditionIF.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.EndIf=EndIf;
        if(EndIf!=null) EndIf.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
        this.ElseBeg=ElseBeg;
        if(ElseBeg!=null) ElseBeg.setParent(this);
    }

    public IfBeg getIfBeg() {
        return IfBeg;
    }

    public void setIfBeg(IfBeg IfBeg) {
        this.IfBeg=IfBeg;
    }

    public ConditionIF getConditionIF() {
        return ConditionIF;
    }

    public void setConditionIF(ConditionIF ConditionIF) {
        this.ConditionIF=ConditionIF;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public EndIf getEndIf() {
        return EndIf;
    }

    public void setEndIf(EndIf EndIf) {
        this.EndIf=EndIf;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public ElseBeg getElseBeg() {
        return ElseBeg;
    }

    public void setElseBeg(ElseBeg ElseBeg) {
        this.ElseBeg=ElseBeg;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfBeg!=null) IfBeg.accept(visitor);
        if(ConditionIF!=null) ConditionIF.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(EndIf!=null) EndIf.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
        if(ElseBeg!=null) ElseBeg.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfBeg!=null) IfBeg.traverseTopDown(visitor);
        if(ConditionIF!=null) ConditionIF.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(EndIf!=null) EndIf.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
        if(ElseBeg!=null) ElseBeg.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfBeg!=null) IfBeg.traverseBottomUp(visitor);
        if(ConditionIF!=null) ConditionIF.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(EndIf!=null) EndIf.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        if(ElseBeg!=null) ElseBeg.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfStmtElse(\n");

        if(IfBeg!=null)
            buffer.append(IfBeg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionIF!=null)
            buffer.append(ConditionIF.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EndIf!=null)
            buffer.append(EndIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseBeg!=null)
            buffer.append(ElseBeg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfStmtElse]");
        return buffer.toString();
    }
}
