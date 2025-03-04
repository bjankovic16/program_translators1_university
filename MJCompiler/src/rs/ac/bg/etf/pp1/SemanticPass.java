package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticPass extends VisitorAdaptor {

	public class Met {
		public Obj method;
		public List<Struct> args = new LinkedList<>();
	}

	Struct currentType = Tab.noType;
	Struct currentClass = null;
	Struct extClass = null;
	boolean thisIn = false;
	boolean flagFun = false;
	List<Struct> listParams = null;
	List<Struct> listArrayParams = null;
	List<Obj> listObjinClass = null;
	List<Obj> currentMetParams = null;
	Obj currentMet = null;
	Obj objClass = null;

	boolean returnFound = false;
	boolean mainFound = false;
	int globalVarsNum = 0;
	int varsNumber = 0;
	int paramsNumber = 0;
	boolean errorDetected = false;

	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode method) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (method == null) ? 0 : method.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}

	public void visit(NamePro namePro) {//
		namePro.obj = Tab.insert(Obj.Prog, namePro.getNamePro(), Tab.noType);
		Tab.openScope();
	}

	public void visit(Program program) { //
		Tab.chainLocalSymbols(program.getNamePro().obj);
		Tab.closeScope();
	}

	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getTypeName());
		if (typeObj == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! Semanticka greska", type);
			type.struct = Tab.noType;// odnosi se na ceo struct cvor
		} else {
			if (Obj.Type == typeObj.getKind()) {
				currentType = typeObj.getType();
				type.struct = typeObj.getType();
				report_info("Obradjuje se tip " + type.getTypeName(), type);
			} else {
				report_error("Ime " + type.getTypeName() + " ne predstavlja tip! Semanticka greska", type);
				type.struct = Tab.noType;
			}
		}
	}

	public void visit(ClassFirst classDeclaration) {
		ClassExtends ce = null;
		Obj className = Tab.find(classDeclaration.getClassName());
		if (className != Tab.noObj) {
			report_error("Ime " + classDeclaration.getClassName() + " je vec deklarisano! Semanticka greska",
					classDeclaration);
			classDeclaration.obj = Tab.noObj;
		} else {
			currentClass = new Struct(Struct.Class);
			if (classDeclaration.getClassExt() instanceof ClassExtends) {
				ce = (ClassExtends) classDeclaration.getClassExt();
				if (!Table.containsClass(ce.getType().struct)) {
					report_error("Ime " + classDeclaration.getClassName()
							+ " ne moze da se izvede iz zadatog tipa! Semanticka greska", classDeclaration);
					classDeclaration.obj = Tab.noObj;
				} else {
					extClass = ce.getType().struct;
					currentClass.setElementType(extClass);
				}
			}
			objClass = classDeclaration.obj = Tab.insert(Obj.Type, classDeclaration.getClassName(), currentClass);
			listObjinClass = new ArrayList<>();
			report_info("Kreirana je klasa " + classDeclaration.getClassName(), classDeclaration);
			Tab.openScope();
			if (ce != null)
				for (Obj classMember : ce.getType().struct.getMembers()) {
					if (classMember.getKind() == Obj.Fld) {
						Obj o = Tab.insert(Obj.Fld, classMember.getName(), classMember.getType());
						listObjinClass.add(o);
						report_info("Ubacen je simbol " + classMember.getName(), classDeclaration);
						varsNumber++;
					}
				}
		}
	}

	public void visit(ClassDeclaration cd) {
		copyClassMethods();
		objClass.setAdr(varsNumber);
		Table.addListClass(currentClass);
		Tab.chainLocalSymbols(currentClass);
		Tab.closeScope();
		listObjinClass = null;
		objClass = null;
		extClass = null;
		varsNumber = 0;
		currentClass = null;
	}

	public void visit(ConstDeclarationItem constDeclarationItem) {
		Obj constant = Tab.find(constDeclarationItem.getConstName());
		if (!constDeclarationItem.getConstItemValue().struct.equals(currentType)) { // ne radi se o trenutnom tipu
			report_error("Tip konstante se ne poklapa s vrednoscu koja je dodeljena! Semanticka greska",
					constDeclarationItem);
		} else {
			if (constant != Tab.noObj) { // postoji objekat sa ovim imenom
				report_error("Ime " + constDeclarationItem.getConstName() + " je vec deklarisano! Semanticka greska",
						constDeclarationItem);
			} else {
				Obj node = Tab.insert(Obj.Con, constDeclarationItem.getConstName(), currentType); // provera trenutnog
				if (currentType.equals(Tab.intType)
						&& constDeclarationItem.getConstItemValue() instanceof ConstValueNumber) {
					ConstValueNumber c = (ConstValueNumber) constDeclarationItem.getConstItemValue();
					node.setAdr(c.getNumber());
				}
				if (currentType.equals(Tab.charType)
						&& constDeclarationItem.getConstItemValue() instanceof ConstValueChar) {
					ConstValueChar c = (ConstValueChar) constDeclarationItem.getConstItemValue();
					node.setAdr(c.getCharacter());
				}
				if (currentType.equals(Table.boolType)
						&& constDeclarationItem.getConstItemValue() instanceof ConstValueBool) {
					ConstValueBool c = (ConstValueBool) constDeclarationItem.getConstItemValue();
					node.setAdr(c.getBool().equalsIgnoreCase("true") ? 1 : 0);//
				}
			}
		}
	}

	public void visit(VarDeclarations varDeclarations) {
		currentType = Tab.noType;
	}

	public void visit(ConstValueNumber c) {
		c.struct = Tab.intType;
	}

	public void visit(ConstValueChar c) {
		c.struct = Tab.charType;
	}

	public void visit(ConstValueBool c) {
		c.struct = Table.boolType;
	}

	public void visit(ConstDeclarations c) {
		currentType = Tab.noType;
	}

	public void visit(VarDeclarationItem varDeclarationItem) {
		Obj varCheck = Table.existInCurrentScope(varDeclarationItem.getVarName());
		if (varCheck == Tab.noObj) {
			boolean cond = false;
			SyntaxNode father = varDeclarationItem.getParent();
			while (father != null) {
				if (father instanceof ClassVarDeclaration) {
					cond = true;
					break;
				}
				father = father.getParent();
			}
			if (cond) {
				varsNumber++;
				varDeclarationItem.obj = Tab.insert(Obj.Fld, varDeclarationItem.getVarName(), currentType);
				listObjinClass.add(varDeclarationItem.obj);
			} else {
				if(!inFun)
				globalVarsNum++;
				varDeclarationItem.obj = Tab.insert(Obj.Var, varDeclarationItem.getVarName(), currentType);
			}
			report_info("Deklarisana promenljiva " + varDeclarationItem.getVarName(), varDeclarationItem);
		} else {
			report_error("Ime " + varDeclarationItem.getVarName() + " je vec deklarisano! Semanticka greska",
					varDeclarationItem);
		}
	}

	public void visit(ArrayDeclarationItem arrayDeclarationItem) {
		Obj arrCheck = Table.existInCurrentScope(arrayDeclarationItem.getArrName());
		if (arrCheck == Tab.noObj) {
			Struct str = new Struct(Struct.Array, currentType);
			if (!Table.getListArr().contains(str)) {
				Table.getListArr().add(str);
			} else {
				List<Struct> lista = Table.getListArr();
				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).equals(str)) {
						str = lista.get(i);
						break;
					}
				}
			}
			boolean cond = false;
			SyntaxNode father = arrayDeclarationItem.getParent();
			while (father != null) {
				if (father instanceof ClassVarDeclaration) {
					cond = true;
					break;
				}
				father = father.getParent();
			}
			if (cond) {
				varsNumber++;
				arrayDeclarationItem.obj = Tab.insert(Obj.Fld, arrayDeclarationItem.getArrName(), str);
				listObjinClass.add(arrayDeclarationItem.obj);
			} else {
				if(!inFun)
				globalVarsNum++;
				arrayDeclarationItem.obj = Tab.insert(Obj.Var, arrayDeclarationItem.getArrName(), str);
			}
			report_info("Deklarisan niz " + arrayDeclarationItem.getArrName(), arrayDeclarationItem);
		} else {
			report_error("Ime " + arrayDeclarationItem.getArrName() + " je vec deklarisano! Semanticka greska",
					arrayDeclarationItem);
		}
	}

	public void copyClassMethods() {
		if (extClass != null) {// kopiranje metode u izvedenu klasu
			for (Obj o : extClass.getMembers()) {
				if (o.getKind() == Obj.Meth) {
					Obj method = Table.existInCurrentScope(o.getName());
					if (method != Table.noObj) {
						if (method.getType().equals(o.getType())) {
							if (!Table.equalsCompleteHashClassMethods(o.getLevel(), method.getLevel(),
									o.getLocalSymbols(), method.getLocalSymbols())) {
								report_error(
										"Nemoguce redefinisati metodu, potpis metode nije isti! Semanticka greska!",
										null);
								return;
							}
						} else {
							report_error("Nemoguce redefinisati metodu, potpis metode nije isti! Semanticka greska!",
									null);
							return;
						}
					}
					Obj newObj = Tab.insert(Obj.Meth, o.getName(), o.getType());
					Tab.openScope();
					for (Obj params : o.getLocalSymbols()) {
						if (!params.getName().equalsIgnoreCase("this"))
							Tab.insert(params.getKind(), params.getName(), params.getType());
						else
							Tab.insert(params.getKind(), "this", currentClass);
					}
					newObj.setLevel(o.getLevel());
					Tab.chainLocalSymbols(newObj);
					Tab.closeScope();
				}
			}
		}
	}
	boolean inFun=false;

	public void visit(MethodReturnType returnType) {
		inFun=true;
		if (Tab.find(returnType.getNameMeth()) == Tab.noObj) {
			if (!(currentType.equals(Table.boolType) || currentType.equals(Table.charType)
					|| currentType.equals(Table.intType))) {
				report_error("metoda mora vracati tipove bool char ili int", returnType);
				flagFun = true;
			}
			returnType.obj = currentMet = Tab.insert(Obj.Meth, returnType.getNameMeth(), currentType);
			currentMetParams = new ArrayList<>();
			report_info("obradjuje se metoda " + returnType.getNameMeth(), returnType);
			Tab.openScope();
		} else {
			returnType.obj = Tab.noObj;
			report_error("Ime " + returnType.getNameMeth() + " je vec deklarisano! Semanticka greska", returnType);
		}
	}

	public void visit(MethodReturnVoid returnType) {
		inFun=true;
		if (Tab.find(returnType.getNameMeth()) == Tab.noObj) {
			returnType.obj = currentMet = Tab.insert(Obj.Meth, returnType.getNameMeth(), Tab.noType);
			currentMetParams = new ArrayList<>();
			report_info("obradjuje se metoda " + returnType.getNameMeth(), returnType);
			Tab.openScope();
		} else {
			returnType.obj = Tab.noObj;
			report_error("Ime " + returnType.getNameMeth() + " je vec deklarisano! Semanticka greska", returnType);
		}
	}

	public void visit(ExprStmtMl hasReturn) {
		returnFound = true;
		if (currentMet == Tab.noObj) {
			flagFun = true;
			report_error("Naredba return mora biti unutar tela metode! Semanticka greska", hasReturn);
		} else {
			if (currentMet.getType().equals(Tab.noType)) {
				flagFun = true;
				report_info("Metoda tipa void ne vraca nikakvu vrednost! Semanticka greska", hasReturn);
			} else {
				if (!currentMet.getType().equals(hasReturn.getExpr().struct)) {
					flagFun = true;
					report_info("Izraz uz return nije ekvivalentan tipu tekuce metode! Semanticka greska", hasReturn);
				}
			}
		}
	}

	public void visit(ExprStmtLE noReturn) {
		returnFound = true;
		if (currentMet == Tab.noObj) {
			flagFun = true;
			report_info("Naredba return mora biti unutar tela metode! Semanticka greska", noReturn);
		} else {
			if (!currentMet.getType().equals(Tab.noType)) {
				flagFun = true;
				report_info("Return ne vraca vrednost iako bi trebalo da vraca! Semanticka greska", noReturn);
			}
		}
	}

	public void visit(ScalarParam scalarParam) {
		if (currentClass != null && !thisIn) {
			paramsNumber++;
			currentMetParams.add(Tab.insert(Obj.Var, "this", currentClass));
			thisIn = true;
		}
		if (Table.existInCurrentScope(scalarParam.getParamName()) == Tab.noObj) {
			scalarParam.obj = Tab.insert(Obj.Var, scalarParam.getParamName(), currentType);
			currentMetParams.add(scalarParam.obj);
			paramsNumber++;
			currentType = Tab.noType;
		} else {
			scalarParam.obj = Tab.noObj;
			report_error("Ime " + scalarParam.getParamName() + " je vec deklarisano! Semanticka greska", scalarParam);
		}
	}

	public void visit(ArrayParam arrayParam) {
		if (currentClass != null && !thisIn) {
			paramsNumber++;
			currentMetParams.add(Tab.insert(Obj.Var, "this", currentClass));
			thisIn = true;
		}
		if (Table.existInCurrentScope(arrayParam.getParamName()) == Tab.noObj) {
			Struct str = new Struct(Struct.Array, currentType);
			if (!Table.getListArr().contains(str)) {
				Table.getListArr().add(str);
			} else {
				List<Struct> lista = Table.getListArr();
				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).equals(str)) {
						str = lista.get(i);
						break;
					}
				}
			}
			arrayParam.obj = Tab.insert(Obj.Var, arrayParam.getParamName(), str);
			currentMetParams.add(arrayParam.obj);
			paramsNumber++;
			currentType = Tab.noType;
		} else {
			arrayParam.obj = Tab.noObj;
			report_error("Ime " + arrayParam.getParamName() + " je vec deklarisano! Semanticka greska", arrayParam);
		}
	}

	public void visit(MethodDeclaration methodDeclaration) {
		if (!returnFound && !currentMet.getType().equals(Tab.noType)) {
			report_error("Funkcija nema return naredbu! Semanticka greska", methodDeclaration);
		} else {
			if (currentMet.getName().equalsIgnoreCase("main")) {
				if (paramsNumber > 0) {
					report_error("main metoda ne sme da ima parametre", null);
					return;
				}
				if (!currentMet.getType().equals(Tab.noType)) {
					report_error("main metoda mora biti tipa void", null);
					return;
				}
			}
			inFun=false;
			thisIn = false;
			currentMetParams = null;
			currentMet.setLevel(paramsNumber);
			Tab.chainLocalSymbols(currentMet);
			Tab.closeScope();
			returnFound = false;
			currentMet = null;
			paramsNumber = 0;
		}
	}

	public void visit(FactorNumber factorNumber) {
		factorNumber.struct = Tab.intType;
	}

	public void visit(FactorChar factorChar) {
		factorChar.struct = Tab.charType;
	}

	public void visit(FactorBool factorBool) {
		factorBool.struct = Table.boolType;
	}

	public void visit(FactorArray factorArray) {
		if (factorArray.getExpr().struct != Tab.intType) {
			report_error("Izraz unutar [ ] mora biti tipa int! Semanticka greska!", factorArray);
			factorArray.struct = Tab.noType;
		} else {
			Struct str = new Struct(Struct.Array, factorArray.getType().struct);
			if (!Table.getListArr().contains(str)) {
				Table.getListArr().add(str);
			} else {
				List<Struct> lista = Table.getListArr();
				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).equals(str)) {
						str = lista.get(i);
						break;
					}
				}
			}
			factorArray.struct = str;
		}
	}

	public void visit(DesignatorAssignValue das) {
		if (das.getExpr().struct.getKind() == Struct.Class) {
			Struct s = das.getExpr().struct;
			while (s != null && !s.equals(das.getDesignator().obj.getType())) {
				s = s.getElemType();
			}
			if (s != null) {
				report_info("uspesno dodeljeno", das);
				return;
			}
		}
		if (das.getExpr().struct.assignableTo(das.getDesignator().obj.getType())){
			report_info("uspesna dodela",das);
		} else {
			report_error("Nisu kompatibilni tipovi u naredbi dodele",das);
		}
	}

	public void visit(TermMul termMul) {
		Struct termM = termMul.getTerm().struct;
		Struct factorM = termMul.getFactor().struct;
		if (termM.equals(factorM) && termM == Tab.intType) {
			termMul.struct = termM;
		} else {
			report_error("Nisu kompatibilni tipovi u izrazu za mnozenje! Semanticka greska", termMul);
			termMul.struct = Tab.noType;
		}
	}

	public void visit(DesignatorCallFuOne dcfo) {
		if (listArrayParams == null) {
			listArrayParams = new ArrayList<>();
		}
		listArrayParams.add(dcfo.getDesignator().obj.getType());
	}

	public void visit(DesignatorWCommaE dwce) {
		if (listArrayParams == null) {
			listArrayParams = new ArrayList<>();
		}
		listArrayParams.add(Tab.noType);
	}

	public void visit(DesignatorWComma dwc) {
		if (listArrayParams == null) {
			listArrayParams = new ArrayList<>();
		}
		listArrayParams.add(dwc.getDesignator().obj.getType());
	}

	public void visit(DesignatorArrCall dac) {
		if (dac.getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error("objekat sa desne strane jednakosti mora biti tipa array! Semanticka greska", dac);
		} else {
			for (Struct s : listArrayParams) {
				if (s.getKind() != Struct.None) {
					if (s.getKind() == Struct.Array) {
						if (!s.getElemType().equals(dac.getDesignator().obj.getType().getElemType()))
							report_error("promenljiva sa leve strane nije istog tipa kao niz! Semanticka greska", dac);
					} else {
						if (s.getKind() != dac.getDesignator().obj.getType().getElemType().getKind())
							report_error("promenljiva sa leve strane nije istog tipa kao niz! Semanticka greska", dac);
					}
				}
			}
		}
		listArrayParams=null;
	}

	public void visit(FactorFuncCall designatorProcCall) {
		if (designatorProcCall.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Mora se pozivati metoda! Semanticka greska", designatorProcCall);
		} else {
			Collection<Obj> lista = null;
			int lvl = 0;
			if (designatorProcCall.getDesignator().obj.getName().equalsIgnoreCase(currentMet.getName())) {
				lista = currentMetParams;
				lvl = currentMetParams.size();
			} else {
				lista = designatorProcCall.getDesignator().obj.getLocalSymbols();
				lvl = designatorProcCall.getDesignator().obj.getLevel();
			}
			int cnt = 0;
			boolean Fthis = false;
			boolean flag = true;
			for (Obj o : lista) {
				if (o.getName().equalsIgnoreCase("this")) {
					Fthis = true;
					continue;
				}
				if (listParams == null || cnt > listParams.size() || !listParams.get(cnt++).assignableTo(o.getType())) {
					report_error("Broj argumenata ili tip argumenata nije dobar! Semanticka greska", designatorProcCall);
					flag = false;
					break;
				}
				if (cnt == listParams.size())
					break;
			}
			if (Fthis)
				cnt++;
			if (!flag || cnt != lvl || (listParams!=null && lvl == 0 && listParams.size()>0)) {
				report_error("Broj argumenata ili tip argumenata nije dobar! Semanticka greska", designatorProcCall);
			} else {
				report_info("Poziva se metoda fun "+ designatorProcCall.getDesignator().obj.getName(), designatorProcCall);
			}
			listParams = null;
		}
		designatorProcCall.struct = designatorProcCall.getDesignator().obj.getType();
	}

	public void visit(TermFactor tf) {
		tf.struct = tf.getFactor().struct;
	}

	public void visit(FactorDesignator fd) {
		fd.struct = fd.getDesignator().obj.getType();
	}

	public void visit(ExprMTerm exprMTerm) {
		exprMTerm.struct = exprMTerm.getTerm().struct;
	}

	public void visit(ExprTerm exprTerm) {
		exprTerm.struct = exprTerm.getTerm().struct;
	}

	public void visit(ExprAdd exprAdd) {
		Struct termA = exprAdd.getTerm().struct;
		Struct exprA = exprAdd.getExpr().struct;
		if (termA.equals(exprA) && termA == Tab.intType) {
			exprAdd.struct = termA;
		} else {
			report_error("Nisu kompatibilni tipovi u izrazu za mnozenje! Semanticka greska", exprAdd);
			exprAdd.struct = Tab.noType;
		}
	}

	public void visit(ScalarDesignator scalarDesignator) {
		Obj d = Tab.find(scalarDesignator.getName());
		if (d == Tab.noObj) {
			if (extClass != null) {
				for (Obj o : extClass.getMembers()) {
					if (o.getName().equalsIgnoreCase(scalarDesignator.getName())) {
						d = o;
						break;
					}
				}
			}
			if (d == Tab.noObj) {
				if (scalarDesignator.getName().equalsIgnoreCase("super") && extClass != null) {
					d = new Obj(extClass.getNumberOfFields(), "super", extClass);
				} else
					report_error("Ime " + scalarDesignator.getName() + " nije deklarisano! Semanticka greska",
							scalarDesignator);
			} else
				scalarDesignator.obj = d;
		}
		scalarDesignator.obj = d;
	}

	public void visit(ArrayDesignator ad) {
		if (ad.getExpr().struct == Tab.intType) {
			if (ad.getDesignator().obj.getType().getKind() == Struct.Array) {
				ad.obj = new Obj(Obj.Elem, ad.getDesignator().obj.getName(),
						ad.getDesignator().obj.getType().getElemType());
			} else {
				report_error("Tip mora biti tipa Array! Semanticka greska", ad);
				ad.obj = Tab.noObj;
			}
		} else {
			report_error("Izraz unutar zagrada mora biti tipa int! Semanticka greska", ad);
			ad.obj = Tab.noObj;
		}
	}

	public void visit(ClassDesignator cd) {
		if (cd.getDesignator().obj.getType().getKind() == Struct.Class) {
			Obj o = Table.getSymbolFromClass(cd.getDesignator().obj.getType(), cd.getName());
			if (o == Tab.noObj) {
				for (Obj ob : listObjinClass) {
					if (ob.getName().equalsIgnoreCase(cd.getName())) {
						o = ob;
						break;
					}
				}
			}
			if (o == Tab.noObj) {
				report_error("Dati tip ne pripada klasi! Semanticka greska", cd);
				cd.obj = Tab.noObj;
			} else {
				cd.obj = o;
				report_info("poziva se " + cd.getName(), cd);
			}
		} else {
			report_error("Izraz mora biti tipa Class! Semanticka greska", cd);
			cd.obj = Tab.noObj;
		}
	}

	public void visit(WhileStmt whileStmt) {
		if (!whileStmt.getCondition().struct.equals(Table.boolType)) {
			report_error("Condition u do while petlji mora biti tipa bool! Semanticka greska", whileStmt);
		}
	}

	public void visit(ConditionIFStat conditionIfStat) {
		conditionIfStat.struct = conditionIfStat.getCondition().struct;
	}

	public void visit(IfStmt ifStmt) {
		if (!ifStmt.getConditionIF().struct.equals(Table.boolType)) {
			report_error("Condition u do if petlji mora biti tipa bool! Semanticka greska", ifStmt);
		}
	}

	public void visit(IfStmtElse ifStmtElse) {
		if (!ifStmtElse.getConditionIF().struct.equals(Table.boolType)) {
			report_error("Condition u do if else petlji mora biti tipa bool! Semanticka greska", ifStmtElse);
		}
	}

	public void visit(ConditionTerm conditionTerm) {
		conditionTerm.struct = conditionTerm.getCondTerm().struct;
	}

	public void visit(ConditionOr conditionOr) {
		conditionOr.struct = conditionOr.getCondTerm().struct;
	}

	public void visit(ConditionFact conditionFact) {
		conditionFact.struct = conditionFact.getCondFact().struct;
	}

	public void visit(ConditionAnd conditionAnd) {
		conditionAnd.struct = conditionAnd.getCondFact().struct;
	}

	public void visit(FactorExpr fe) {
		fe.struct = fe.getExpr().struct;
	}

	public void visit(CondFactorPart cfp) {
		cfp.struct = cfp.getExpr().struct;
	}

	public void visit(FactorParams fp) {
		fp.struct = fp.getType().struct;
	}

	public void visit(NoCondFactorPart ncfp) {
		ncfp.struct = Tab.noType;
	}

	public void visit(ConFact conFact) {
		Struct s1 = conFact.getCondFa().struct;
		Struct s2 = conFact.getExpr().struct;
		if(s1.equals(Tab.nullType) && s2.isRefType()) {
			conFact.struct=Table.boolType;
			return;
		}
		if (s1.equals(Tab.noType)) {
			if (!s2.equals(Table.boolType)) {
				report_error("Tip nije bool! Semanticka greska", conFact);
				conFact.struct = Tab.noType;
			} else {
				conFact.struct = Table.boolType;
			}
		} else {
			if (!s1.equals(s2)) {
				report_error("Tipovi nisu kompatibilni! Semanticka greska", conFact);
				conFact.struct = Tab.noType;
			} else {
				if ((s1.isRefType() || s2.isRefType()) && conFact.getCondFa() instanceof CondFactorPart) {
					CondFactorPart cfp = (CondFactorPart) conFact.getCondFa();
					if (!(cfp.getRelop() instanceof EqualOperation || cfp.getRelop() instanceof NotEqualOperation)) {
						report_error("Uz tipove klase ili niza mogu se koristiti operatori == i != ! Semanticka greska",
								conFact);
						conFact.struct = Tab.noType;
					} else {
						conFact.struct = Table.boolType;
					}
				} else {
					conFact.struct = Table.boolType;
				}
			}
		}
	}

	public void visit(BreakStmt stmt) {
		boolean cond = false;
		SyntaxNode father = stmt.getParent();
		while (father != null) {
			if (father instanceof WhileStmt || father instanceof ImplicationStmt) {
				cond = true;
				break;
			}
			father = father.getParent();
		}
		if (!cond) {
			report_error("Naredba break mora biti unutar while ili foreach petlje! Semanticka greska", stmt);
		}
	}

	public void visit(ContinueStmt stmt) {
		boolean cond = false;
		SyntaxNode father = stmt.getParent();
		while (father != null) {
			if (father instanceof WhileStmt || father instanceof ImplicationStmt) {
				cond = true;
				break;
			}
			father = father.getParent();
		}
		if (!cond) {
			report_error("Naredba continue mora biti unutar while ili foreach petlje! Semanticka greska", stmt);
		}
	}

	public void visit(DesignatorIncValue designatorIncValue) {
		if (designatorIncValue.getDesignator().obj.getType().getKind() != Struct.Int
				&& (designatorIncValue.getDesignator().obj.getType().getKind() == Struct.Array
						&& designatorIncValue.getDesignator().obj.getType().getElemType().getKind() != Struct.Int)) {
			report_error("Izraz uz ++ mora biti tipa int! Semanticka greska", designatorIncValue);
		}
	}

	public void visit(DesignatorProcCall designatorProcCall) {
		if (designatorProcCall.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Mora se pozivati metoda! Semanticka greska", designatorProcCall);
		} else {
			Collection<Obj> lista = null;
			int lvl = 0;
			if (designatorProcCall.getDesignator().obj.getName().equalsIgnoreCase(currentMet.getName())) {
				lista = currentMetParams;
				lvl = currentMetParams.size();
			} else {
				lista = designatorProcCall.getDesignator().obj.getLocalSymbols();
				lvl = designatorProcCall.getDesignator().obj.getLevel();
			}
			int cnt = 0;
			boolean Fthis = false;
			boolean flag = true;
			for (Obj o : lista) {
				if (o.getName().equalsIgnoreCase("this")) {
					Fthis = true;
					continue;
				}
				if (listParams == null || cnt > listParams.size() || !listParams.get(cnt++).assignableTo(o.getType())) {
					report_error("Broj argumenata ili tip argumenata nije dobar! Semanticka greskaaaa", designatorProcCall);
					flag = false;
					break;
				}
				if (cnt == listParams.size())
					break;
			}
			if (Fthis)
				cnt++;
			if (!flag || cnt != lvl || (listParams!=null && lvl == 0 && listParams.size()>0)) {
				report_error("Broj argumenata ili tip argumenata nije dobar! Semanticka greska", designatorProcCall);
			} else {
				report_info("Poziva se metoda " + designatorProcCall.getDesignator().obj.getName(), designatorProcCall);
			}
			listParams = null;
		}
	}

	public void visit(ActualParameter ap) {
		if (listParams == null) {
			listParams = new ArrayList<Struct>();
		}
		listParams.add(ap.getExpr().struct);
	}

	public void visit(DesignatorDecValue designatorDecValue) {
		if (designatorDecValue.getDesignator().obj.getType().getKind() != Struct.Int
				&& (designatorDecValue.getDesignator().obj.getType().getKind() == Struct.Array
						&& designatorDecValue.getDesignator().obj.getType().getElemType().getKind() != Struct.Int)) {
			report_error("Izraz uz -- mora biti tipa int! Semanticka greska", designatorDecValue);
		}
	}

	public void visit(ReadStmt readStmt) {// promenljiva elem niza ili polje objekta
		int kind = readStmt.getDesignator().obj.getKind();
		int type = readStmt.getDesignator().obj.getType().getKind();
		if (!(kind == Obj.Elem || (kind == Obj.Var && type != Struct.Array && type != Struct.Class)
				|| kind == Obj.Fld)) {
			report_error("Objekat mora biti tipa elem var ili fld! Semanticka greska", readStmt);
		} else {

			if (!(type == 1 || type == 2 || type == 5)) {
				report_error("Unutar naredbe print mora biti tip bool, char ili int! Semanticka greska" + kind,
						readStmt);
			}

		}
	}

	public void visit(ImplicationStmt implication) {
		if (implication.getDesignator().obj.getType().getKind() != Struct.Array) {
			report_error("U izrazu pre tacke mora biti tip Array! Semanticka greska", implication);
		} else {
			Obj o = Tab.find(implication.getCondImpl());
			if (o == Tab.noObj) {
				report_error("U izrazu objekat ne postoji! Semanticka greska", implication);
			} else {
				if (o.getType().getKind() != implication.getDesignator().obj.getType().getElemType().getKind()) {
					report_error("Nisu istog tip element niza i varijabla", implication);
				}
			}
		}
	}

	public void visit(PrintStmt print) {
		int kind = print.getExpr().struct.getKind();
		if (kind == Struct.Array) {
			kind = print.getExpr().struct.getElemType().getKind();
		}
		if (!(kind == 1 || kind == 2 || kind == 5)) {
			report_error("Unutar naredbe print mora biti tip bool, char ili int! Semanticka greska" + kind, print);
		}
	}
}