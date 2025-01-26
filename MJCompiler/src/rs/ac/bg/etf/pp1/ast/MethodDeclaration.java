// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclaration extends MethodDecl {

    private MethodReturnTypeIdent MethodReturnTypeIdent;
    private FormPars FormPars;
    private LocalVarDeclList LocalVarDeclList;
    private StatementListM StatementListM;

    public MethodDeclaration (MethodReturnTypeIdent MethodReturnTypeIdent, FormPars FormPars, LocalVarDeclList LocalVarDeclList, StatementListM StatementListM) {
        this.MethodReturnTypeIdent=MethodReturnTypeIdent;
        if(MethodReturnTypeIdent!=null) MethodReturnTypeIdent.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.LocalVarDeclList=LocalVarDeclList;
        if(LocalVarDeclList!=null) LocalVarDeclList.setParent(this);
        this.StatementListM=StatementListM;
        if(StatementListM!=null) StatementListM.setParent(this);
    }

    public MethodReturnTypeIdent getMethodReturnTypeIdent() {
        return MethodReturnTypeIdent;
    }

    public void setMethodReturnTypeIdent(MethodReturnTypeIdent MethodReturnTypeIdent) {
        this.MethodReturnTypeIdent=MethodReturnTypeIdent;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public LocalVarDeclList getLocalVarDeclList() {
        return LocalVarDeclList;
    }

    public void setLocalVarDeclList(LocalVarDeclList LocalVarDeclList) {
        this.LocalVarDeclList=LocalVarDeclList;
    }

    public StatementListM getStatementListM() {
        return StatementListM;
    }

    public void setStatementListM(StatementListM StatementListM) {
        this.StatementListM=StatementListM;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodReturnTypeIdent!=null) MethodReturnTypeIdent.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.accept(visitor);
        if(StatementListM!=null) StatementListM.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodReturnTypeIdent!=null) MethodReturnTypeIdent.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseTopDown(visitor);
        if(StatementListM!=null) StatementListM.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodReturnTypeIdent!=null) MethodReturnTypeIdent.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(LocalVarDeclList!=null) LocalVarDeclList.traverseBottomUp(visitor);
        if(StatementListM!=null) StatementListM.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclaration(\n");

        if(MethodReturnTypeIdent!=null)
            buffer.append(MethodReturnTypeIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LocalVarDeclList!=null)
            buffer.append(LocalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementListM!=null)
            buffer.append(StatementListM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclaration]");
        return buffer.toString();
    }
}
