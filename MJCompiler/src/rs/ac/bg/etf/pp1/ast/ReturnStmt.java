// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class ReturnStmt extends Statement {

    private ExprStmtO ExprStmtO;

    public ReturnStmt (ExprStmtO ExprStmtO) {
        this.ExprStmtO=ExprStmtO;
        if(ExprStmtO!=null) ExprStmtO.setParent(this);
    }

    public ExprStmtO getExprStmtO() {
        return ExprStmtO;
    }

    public void setExprStmtO(ExprStmtO ExprStmtO) {
        this.ExprStmtO=ExprStmtO;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExprStmtO!=null) ExprStmtO.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExprStmtO!=null) ExprStmtO.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExprStmtO!=null) ExprStmtO.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStmt(\n");

        if(ExprStmtO!=null)
            buffer.append(ExprStmtO.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStmt]");
        return buffer.toString();
    }
}
