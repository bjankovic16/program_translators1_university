package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class Table extends Tab {

	public static final Struct boolType = new Struct(Struct.Bool);

	private static List<Struct> listaClass = new ArrayList<>();
	private static List<Struct> listaArr = new ArrayList<>();

	public static List<Struct> getListArr() {
		return listaArr;
	}

	public static void addListClass(Struct s) {
		listaClass.add(s);
	}

	public static boolean containsClass(Struct s) {
		for (Struct str : listaClass) {
			if (str.equals(s))
				return true;
		}
		return false;
	}

	public static Obj getSymbolFromClass(Struct s, String name) {
		for (Obj o : s.getMembers()) {
			if (o.getName().equalsIgnoreCase(name)) {
				return o;
			}
		}
		return Tab.noObj;
	}

	public static void initialization() {
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}

	public static Obj existInCurrentScope(String name) {
		if (currentScope.getLocals() != null) {
			Obj resultObj = currentScope.getLocals().searchKey(name);
			if (resultObj != null)
				return resultObj;
		}
		return noObj;
	}

	public static boolean equalsCompleteHashClassMethods(int l1, int l2, Collection<Obj> h1Obj, Collection<Obj> h2Obj) {
		if (h1Obj == h2Obj)
			return true;

		if (h1Obj == null || h2Obj == null || l1 != l2)
			return false;

		ArrayList<Obj> itH1 = new ArrayList<Obj>(h1Obj), itH2 = new ArrayList<>(h2Obj);

		for (int i = 0; i < l1; i++) {
			if (itH1.get(i).getName().equalsIgnoreCase("this"))
				continue;
			if (!itH1.get(i).getType().equals(itH2.get(i).getType())) {
				return false;
			}
		}
		return true;
	}

	public static void dump(SymbolTableVisitor stv) {
		System.out.println("=====================SYMBOL TABLE DUMP=========================");
		if (stv == null)
			stv = new ExtSymbolTableVisitor();
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		System.out.println(stv.getOutput());
	}

	public static void dump() {
		dump(null);
	}
}
