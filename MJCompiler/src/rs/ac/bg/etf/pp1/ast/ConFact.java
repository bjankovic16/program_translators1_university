// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class ConFact extends CondFact {

    private Expr Expr;
    private CondFa CondFa;

    public ConFact (Expr Expr, CondFa CondFa) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.CondFa=CondFa;
        if(CondFa!=null) CondFa.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public CondFa getCondFa() {
        return CondFa;
    }

    public void setCondFa(CondFa CondFa) {
        this.CondFa=CondFa;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(CondFa!=null) CondFa.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(CondFa!=null) CondFa.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(CondFa!=null) CondFa.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConFact(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFa!=null)
            buffer.append(CondFa.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConFact]");
        return buffer.toString();
    }
}
