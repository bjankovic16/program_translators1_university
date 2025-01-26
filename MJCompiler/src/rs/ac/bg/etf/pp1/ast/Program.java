// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class Program implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private NamePro NamePro;
    private GlobalDeclList GlobalDeclList;
    private MethodDeclList MethodDeclList;

    public Program (NamePro NamePro, GlobalDeclList GlobalDeclList, MethodDeclList MethodDeclList) {
        this.NamePro=NamePro;
        if(NamePro!=null) NamePro.setParent(this);
        this.GlobalDeclList=GlobalDeclList;
        if(GlobalDeclList!=null) GlobalDeclList.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public NamePro getNamePro() {
        return NamePro;
    }

    public void setNamePro(NamePro NamePro) {
        this.NamePro=NamePro;
    }

    public GlobalDeclList getGlobalDeclList() {
        return GlobalDeclList;
    }

    public void setGlobalDeclList(GlobalDeclList GlobalDeclList) {
        this.GlobalDeclList=GlobalDeclList;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NamePro!=null) NamePro.accept(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NamePro!=null) NamePro.traverseTopDown(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NamePro!=null) NamePro.traverseBottomUp(visitor);
        if(GlobalDeclList!=null) GlobalDeclList.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program(\n");

        if(NamePro!=null)
            buffer.append(NamePro.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalDeclList!=null)
            buffer.append(GlobalDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program]");
        return buffer.toString();
    }
}
