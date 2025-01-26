// generated with ast extension for cup
// version 0.8
// 11/0/2023 19:54:26


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArrCall extends DesignatorStatement {

    private DesignatorArrBeg DesignatorArrBeg;
    private DesignatorACall DesignatorACall;
    private Designator Designator;
    private DesignatorArrEnd DesignatorArrEnd;

    public DesignatorArrCall (DesignatorArrBeg DesignatorArrBeg, DesignatorACall DesignatorACall, Designator Designator, DesignatorArrEnd DesignatorArrEnd) {
        this.DesignatorArrBeg=DesignatorArrBeg;
        if(DesignatorArrBeg!=null) DesignatorArrBeg.setParent(this);
        this.DesignatorACall=DesignatorACall;
        if(DesignatorACall!=null) DesignatorACall.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorArrEnd=DesignatorArrEnd;
        if(DesignatorArrEnd!=null) DesignatorArrEnd.setParent(this);
    }

    public DesignatorArrBeg getDesignatorArrBeg() {
        return DesignatorArrBeg;
    }

    public void setDesignatorArrBeg(DesignatorArrBeg DesignatorArrBeg) {
        this.DesignatorArrBeg=DesignatorArrBeg;
    }

    public DesignatorACall getDesignatorACall() {
        return DesignatorACall;
    }

    public void setDesignatorACall(DesignatorACall DesignatorACall) {
        this.DesignatorACall=DesignatorACall;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorArrEnd getDesignatorArrEnd() {
        return DesignatorArrEnd;
    }

    public void setDesignatorArrEnd(DesignatorArrEnd DesignatorArrEnd) {
        this.DesignatorArrEnd=DesignatorArrEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrBeg!=null) DesignatorArrBeg.accept(visitor);
        if(DesignatorACall!=null) DesignatorACall.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorArrEnd!=null) DesignatorArrEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrBeg!=null) DesignatorArrBeg.traverseTopDown(visitor);
        if(DesignatorACall!=null) DesignatorACall.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorArrEnd!=null) DesignatorArrEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrBeg!=null) DesignatorArrBeg.traverseBottomUp(visitor);
        if(DesignatorACall!=null) DesignatorACall.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorArrEnd!=null) DesignatorArrEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArrCall(\n");

        if(DesignatorArrBeg!=null)
            buffer.append(DesignatorArrBeg.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorACall!=null)
            buffer.append(DesignatorACall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorArrEnd!=null)
            buffer.append(DesignatorArrEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArrCall]");
        return buffer.toString();
    }
}
