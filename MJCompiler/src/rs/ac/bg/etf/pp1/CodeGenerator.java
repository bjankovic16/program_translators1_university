package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	boolean flagFun = false;
	int adr;

	public int getMainPc() {
		return mainPc;
	}

	public void visit(MethodReturnType m) {
		if (flagFun) {
			Code.put(Code.trap);
			Code.put(1);
			return;
		}
		m.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(m.obj.getLevel());
		Code.put(m.obj.getLocalSymbols().size());
		adr=m.obj.getLocalSymbols().size()+1;
	}

	public void visit(MethodReturnVoid m) {
		m.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(m.obj.getName())) {
			mainPc = Code.pc;
		}
		Code.put(Code.enter);
		Code.put(m.obj.getLevel());
		Code.put(m.obj.getLocalSymbols().size());
		adr=m.obj.getLocalSymbols().size()+1;
	}

	public void visit(ExprStmtLE e) {
		if (flagFun) {
			Code.put(Code.trap);
			Code.put(1);
		}
	}

	public void visit(ExprStmtMl e) {
		if (flagFun) {
			Code.put(Code.trap);
			Code.put(1);
		}
	}

	public void visit(MethodDeclaration r) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	public void visit(FactorNumber fact) {
		Code.loadConst(fact.getNumber());
	}

	public void visit(FactorChar fact) {
		Code.loadConst(fact.getCharacter());
	}

	public void visit(FactorBool fact) {
		String res = fact.getBool();
		boolean b = Boolean.valueOf(res);
		Code.loadConst((b) ? 1 : 0);
	}

	public void visit(TermMul op) {
		if (op.getMulop() instanceof MulOperation) {
			Code.put(Code.mul);
		} else if (op.getMulop() instanceof DivOperation) {
			Code.put(Code.div);
		} else if (op.getMulop() instanceof RemOperation) {
			Code.put(Code.rem);
		}
	}

	public void visit(ExprAdd op) {
		if (op.getAddop() instanceof PlusOperation) {
			Code.put(Code.add);
		} else if (op.getAddop() instanceof MinusOperation) {
			Code.put(Code.sub);
		}
	}

	public void visit(ExprMTerm op) {
		Code.put(Code.neg);
	}

	public void visit(FactorArray arr) {
		Code.put(Code.newarray);
		if (arr.struct.getElemType().equals(Table.intType)) {
			Code.put(1);
		} else {
			Code.put(0);
		}
	}

	public void visit(ScalarDesignator s) {
		if (s.getParent() instanceof ArrayDesignator) {
			Code.load(s.obj);
		}
	}

	public void visit(FactorDesignator f) {
		if (flagImplication > 0) {
			boolean ass=false;
			for (int i = lvarsImplication.size() - 1; i >= 0; i--) {
				if (f.getDesignator().obj.getName().equalsIgnoreCase(lvarsImplication.get(i))) {
					Obj aIm = larrImplication.get(i);
					Code.load(aIm);
					Code.load(counterImplication);
					ass=true;
					if (aIm.getType().getElemType().equals(Tab.intType)) {
						Code.put(Code.aload);
					} else {
						Code.put(Code.baload);
					}
					break;
				}
		}
			if(!ass) {
				Code.load(f.getDesignator().obj);
			}
		} else
			Code.load(f.getDesignator().obj);
	}

	public void visit(DesignatorAssignValue d) {//ne sme dodela vrednosti u foreach
		if (flagImplication > 0) {
			boolean ass=false;
			for (int i = lvarsImplication.size() - 1; i >= 0; i--) {
				if (d.getDesignator().obj.getName().equalsIgnoreCase(lvarsImplication.get(i))) {
					ass=true;
					Code.put(Code.trap);
					Code.loadConst(1);
				}
			}
			if(!ass) {
				Code.store(d.getDesignator().obj);
			}
		} else 
			Code.store(d.getDesignator().obj);
	}

	public void visit(DesignatorDecValue d) {
		if (flagImplication > 0) {
			for (int i = lvarsImplication.size() - 1; i >= 0; i--) {
				if (d.getDesignator().obj.getName().equalsIgnoreCase(lvarsImplication.get(i))) {
					Code.put(Code.trap);
					Code.loadConst(1);
				}
			}
		}
		Obj o = d.getDesignator().obj;
		if (d.getDesignator() instanceof ScalarDesignator) {
			Code.load(o);
			Code.put(Code.const_1);
			Code.put(Code.sub);
			Code.store(o);
		} else {
			Code.put(Code.dup2);
			if (o.getType().equals(Table.intType)) {
				Code.put(Code.aload);
			} else {
				Code.put(Code.baload);
			}
			Code.put(Code.const_1);
			Code.put(Code.sub);
			if (o.getType().equals(Table.intType)) {
				Code.put(Code.astore);
			} else {
				Code.put(Code.bastore);
			}
		}
	}

	public void visit(DesignatorIncValue d) {
		if (flagImplication > 0) {
			for (int i = lvarsImplication.size() - 1; i >= 0; i--) {
				if (d.getDesignator().obj.getName().equalsIgnoreCase(lvarsImplication.get(i))) {
					Code.put(Code.trap);
					Code.loadConst(1);
				}
			}
		}
		Obj o = d.getDesignator().obj;
		if (d.getDesignator() instanceof ScalarDesignator) {
			Code.load(o);
			Code.put(Code.const_1);
			Code.put(Code.add);
			Code.store(o);
		} else {
			Code.put(Code.dup2);
			if (o.getType().getKind() == Struct.Int) {
				Code.put(Code.aload);
			} else {
				Code.put(Code.baload);
			}
			Code.put(Code.const_1);
			Code.put(Code.add);
			if (o.getType().getKind() == Struct.Int) {
				Code.put(Code.astore);
			} else {
				Code.put(Code.bastore);
			}
		}
	}

	public void visit(PrintStmt p) {
		List<Integer> l = new ArrayList<>();
		if (p.getExpr().struct.equals(Table.intType) || p.getExpr().struct.equals(Table.boolType) ) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		ConstStatement c = p.getConstStatement();
		while (c instanceof ConstantStatement) {
			ConstantStatement cs = (ConstantStatement) c;
			l.add(cs.getNumber());
			c = cs.getConstStatement();
		}
		for (int i = l.size() - 1; i >= 0; i--) {
			Code.loadConst(l.get(i));
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}

	public void visit(ReadStmt r) {
		if (r.getDesignator().obj.getType().equals(Table.charType)) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read);
		}
		Code.store(r.getDesignator().obj);
	}

	int counterArr = 0;
	Obj niz = null;
	int jumpArr=0;
	int instrArr=0;

	public void visit(DesignatorArrBegin d) {
		DesignatorArrCall des = (DesignatorArrCall) d.getParent();
		niz = des.getDesignator().obj;
		Code.putJump(0);
		jumpArr=Code.pc-2;
		instrArr=Code.pc;
	}
	
	public void visit(DesignatorArrEnd d) {
		Code.putJump(0);
		int adr=Code.pc-2;
		Code.fixup(jumpArr);
		DesignatorArrCall des = (DesignatorArrCall) d.getParent();
		niz = des.getDesignator().obj;
		DesignatorArrCall dac = (DesignatorArrCall) d.getParent();
		Obj arrObj = dac.getDesignator().obj;
		Code.loadConst(counterArr);
		Code.load(arrObj);
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.gt, instrArr);
		Code.put(Code.trap);
		Code.put(1);
		counterArr = 0;
		Code.fixup(adr);
	}
	

	public void visit(DesignatorCallFuOne d) {
		Code.load(niz);
		Code.loadConst(counterArr++);
		if (niz.getType().getElemType().equals(Tab.intType)) {
			Code.put(Code.aload);
			if (d.getDesignator().obj.getKind() == Obj.Elem)
				Code.put(Code.astore);
			else
				Code.store(d.getDesignator().obj);
		} else {
			Code.put(Code.baload);
			if (d.getDesignator().obj.getKind() == Obj.Elem)
				Code.put(Code.bastore);
			else
				Code.store(d.getDesignator().obj);
		}
	}

	public void visit(DesignatorWComma d) {
		Code.load(niz);
		Code.loadConst(counterArr++);
		if (niz.getType().getElemType().equals(Tab.intType)) {
			Code.put(Code.aload);
			if (d.getDesignator().obj.getKind() == Obj.Elem)
				Code.put(Code.astore);
			else
				Code.store(d.getDesignator().obj);
		} else {
			Code.put(Code.baload);
			if (d.getDesignator().obj.getKind() == Obj.Elem)
				Code.put(Code.bastore);
			else
				Code.store(d.getDesignator().obj);
		}
	}
	
	public void visit(EmptyArrayDes d) {
		counterArr++;
	}

	public void visit(DesignatorWCommaE d) {
		counterArr++;
	}

	public void visit(DesignatorArrayCallE d) {
		counterArr++;
	}

	boolean inWhile = false;
	int flagImplication = 0;
	Obj counterImplication = null;
	Obj arrImplication = null;
	Stack<Integer> stackImpli = new Stack<>();
	Stack<Integer> stackTrapImpli = new Stack<>();
	List<String> lvarsImplication = new ArrayList<>();
	List<Obj> larrImplication = new ArrayList<>();
	Stack<Obj> counters = new Stack<>();
	String nameOfVar;

	public void visit(BeginImplication b) {
		inWhile = false;
		flagImplication++;
		counters.push(counterImplication);
		counterImplication = new Obj(Obj.Var, "$" + flagImplication, Tab.intType);
		counterImplication.setAdr(adr++);
		Code.loadConst(0);
		Code.store(counterImplication);
		stackImpli.push(Code.pc);
		ImplicationStmt imp = (ImplicationStmt) b.getParent();
		nameOfVar = imp.getCondImpl();
		lvarsImplication.add(nameOfVar);
		Code.load(counterImplication);
		arrImplication = imp.getDesignator().obj;
		larrImplication.add(arrImplication);
		Code.load(arrImplication);// niz po kome se radi
		Code.put(Code.arraylength);
		Code.putFalseJump(Code.lt, 0);
		stackTrapImpli.push(Code.pc - 2);
	}

	public void visit(ImplicationStmt im) {
		flagImplication--;
		larrImplication.remove(flagImplication);
		lvarsImplication.remove(flagImplication);
		Code.load(counterImplication);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(counterImplication);
		Code.putJump(stackImpli.pop());
		Code.fixup(stackTrapImpli.pop());
		counterImplication = counters.pop();
		adr--;
		if (whereInBreak.size() > 0 && whereInBreak.peek() == false && flagImplication == lvlInBreak.peek() - 1) {
			whereInBreak.pop();
			lvlInBreak.pop();
			Code.fixup(lvlInBreak.pop());
		}
	}

	Stack<List<Integer>> patchingList = new Stack<>();

	public void visit(CondFactorPart c) {
		if (c.getRelop() instanceof EqualOperation) {
			Code.putFalseJump(Code.eq, 0);
		} else if (c.getRelop() instanceof NotEqualOperation) {
			Code.putFalseJump(Code.ne, 0);
		} else if (c.getRelop() instanceof LessOperation) {
			Code.putFalseJump(Code.lt, 0);
		} else if (c.getRelop() instanceof GreatOperation) {
			Code.putFalseJump(Code.gt, 0);
		} else if (c.getRelop() instanceof GreatEqualOperation) {
			Code.putFalseJump(Code.ge, 0);
		} else if (c.getRelop() instanceof LessEqualOperation) {
			Code.putFalseJump(Code.le, 0);
		}
		patchingList.peek().add(Code.pc - 2);
	}

	public void visit(NoCondFactorPart n) {
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		patchingList.peek().add(Code.pc - 2);
	}

	Stack<Integer> whileBeg = new Stack<>();
	Stack<Stack<Integer>> codeBeg = new Stack<>();
	Stack<Integer> elsePart=new Stack<>();

	public void visit(IfBegin b) {
		codeBeg.push(new Stack<>());
		patchingList.push(new ArrayList<>());
	}

	public void visit(EndIfEmpty e) {
		if(e.getParent() instanceof IfStmtElse) {
			Code.putJump(0);
			elsePart.push(Code.pc-2);
		}
		for (int i = 0; i < patchingList.peek().size(); i++) {
			Code.fixup(patchingList.peek().get(i));
		}
		patchingList.pop();
	}
	
	public void visit(ElseBegin e) {
		Code.fixup(elsePart.pop());
	}

	public void visit(WhileEmpty w) { // pocetak while petlje
		inWhile = true;
		patchingList.push(new ArrayList<>());
		codeBeg.push(new Stack<>());
		whileBeg.push(Code.pc);
	}

	public void visit(StatWhileBeg s) { // pocetak izvrsavanja while petlje i sredjivanje laznih skokova
		while (codeBeg.peek().size() > 0) {
			Code.fixup(codeBeg.peek().pop());
		}
		codeBeg.pop();
	}

	public void visit(ConditionTerm c) {//ili skokovi
		if (c.getParent() instanceof ConditionOr) {
			Code.putJump(0);
			codeBeg.peek().add(Code.pc - 2);
		}
	}
	
	public void visit(ConditionIFStat c) {
		while (codeBeg.peek().size() > 0) {
			Code.fixup(codeBeg.peek().pop());
		}
		codeBeg.pop();
	}

	public void visit(ConditionPatching c) {// sredjivanje skoka na sledeci uslov ||
		for (int i = 0; i < patchingList.peek().size(); i++) {
			Code.fixup(patchingList.peek().get(i));
		}
		patchingList.peek().clear();
	}

	public void visit(WhileStmt w) { // kraj while petlje
		inWhile=false;
		Code.putJump(whileBeg.pop());
		for (int i = 0; i < patchingList.peek().size(); i++) {
			Code.fixup(patchingList.peek().get(i));
		}
		patchingList.pop();
		if (whereInBreak.size() > 0 && whereInBreak.peek() == true && whileBeg.size() == lvlInBreak.peek() - 1) {
			whereInBreak.pop();
			lvlInBreak.pop();
			Code.fixup(lvlInBreak.pop());
		}
	}

	Stack<Boolean> whereInBreak = new Stack<>();
	Stack<Integer> lvlInBreak = new Stack<>();

	public void visit(BreakStmt b) {// mora da se zna da li je while ili foreach i koji je level mora fix jmp
		Code.putJump(0);
		whereInBreak.push(inWhile);
		lvlInBreak.push(Code.pc - 2);
		if (inWhile) {
			lvlInBreak.push(whileBeg.size());
		} else {
			lvlInBreak.push(flagImplication);
		}
	}

	public void visit(ContinueStmt c) {// break + rast indeksa za jedan ako je foreach radi se cim se naidje jmp
		if (inWhile) {
			Code.putJump(whileBeg.peek());
		} else {
			Code.load(counterImplication);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(counterImplication);
			Code.putJump(stackImpli.peek());
		}
	}

	public void visit(DesignatorProcCall stmt) {
		int offset = stmt.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if (!stmt.getDesignator().obj.getType().equals(Tab.noType)) {
			Code.put(Code.pop);
		}
	}

	public void visit(FactorFuncCall stmt) {
		if("len".equals(stmt.getDesignator().obj.getName())) {
			Code.put(Code.arraylength);
			return;
		}
		if("ord".equals(stmt.getDesignator().obj.getName())) {
			return;
		}

		if("chr".equals(stmt.getDesignator().obj.getName())) {
			return;
		}
		int offset = stmt.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
	}

}
