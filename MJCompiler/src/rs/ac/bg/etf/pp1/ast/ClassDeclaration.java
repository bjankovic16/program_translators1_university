// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ClassDecl {

    private ClassFirst ClassFirst;
    private ClassVarDecl ClassVarDecl;
    private ClassMethDecl ClassMethDecl;

    public ClassDeclaration (ClassFirst ClassFirst, ClassVarDecl ClassVarDecl, ClassMethDecl ClassMethDecl) {
        this.ClassFirst=ClassFirst;
        if(ClassFirst!=null) ClassFirst.setParent(this);
        this.ClassVarDecl=ClassVarDecl;
        if(ClassVarDecl!=null) ClassVarDecl.setParent(this);
        this.ClassMethDecl=ClassMethDecl;
        if(ClassMethDecl!=null) ClassMethDecl.setParent(this);
    }

    public ClassFirst getClassFirst() {
        return ClassFirst;
    }

    public void setClassFirst(ClassFirst ClassFirst) {
        this.ClassFirst=ClassFirst;
    }

    public ClassVarDecl getClassVarDecl() {
        return ClassVarDecl;
    }

    public void setClassVarDecl(ClassVarDecl ClassVarDecl) {
        this.ClassVarDecl=ClassVarDecl;
    }

    public ClassMethDecl getClassMethDecl() {
        return ClassMethDecl;
    }

    public void setClassMethDecl(ClassMethDecl ClassMethDecl) {
        this.ClassMethDecl=ClassMethDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassFirst!=null) ClassFirst.accept(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.accept(visitor);
        if(ClassMethDecl!=null) ClassMethDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassFirst!=null) ClassFirst.traverseTopDown(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.traverseTopDown(visitor);
        if(ClassMethDecl!=null) ClassMethDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassFirst!=null) ClassFirst.traverseBottomUp(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.traverseBottomUp(visitor);
        if(ClassMethDecl!=null) ClassMethDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        if(ClassFirst!=null)
            buffer.append(ClassFirst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDecl!=null)
            buffer.append(ClassVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethDecl!=null)
            buffer.append(ClassMethDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
