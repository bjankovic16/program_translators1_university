// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class ClassFirst implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String className;
    private ClassExt ClassExt;

    public ClassFirst (String className, ClassExt ClassExt) {
        this.className=className;
        this.ClassExt=ClassExt;
        if(ClassExt!=null) ClassExt.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public ClassExt getClassExt() {
        return ClassExt;
    }

    public void setClassExt(ClassExt ClassExt) {
        this.ClassExt=ClassExt;
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
        if(ClassExt!=null) ClassExt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassExt!=null) ClassExt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassExt!=null) ClassExt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassFirst(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(ClassExt!=null)
            buffer.append(ClassExt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassFirst]");
        return buffer.toString();
    }
}
