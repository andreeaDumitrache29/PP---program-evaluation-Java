/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class Visitor implements VisitorInterface {
	/**
	 * evalueaza fiul stagsi fiul drept si combina rezultatul
	 */
	@Override
	public void visit(AdditionNode node) {
		/**
		 * verificam intai sintaxa: in cazde eroare nu mergem mai departe in
		 * evaluare
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}

		/**
		 * verificare pentru assert failed
		 */
		if (Evaluator.checkAssert(node) == false) {
			return;
		}

		/**
		 * evaluam fiii nodului
		 */
		Evaluator.evaluate(node.getChild(0));
		Evaluator.evaluate(node.getChild(1));
		Integer i, j;
		/**
		 * Salvam valorile calculate in urma evaluarii copiilor in 2 variabile
		 * Integer. Daca unul dintre fii este de tip Variable ii luam valoarea
		 * din HashMap-ul din Evaluator. Altfel, valoarea e reprezentata de
		 * numele nodului.
		 */
		if (node.getChild(1) instanceof Variable) {
			i = Integer.parseInt(Evaluator.variables.get(node.getChild(1).getName()));

		} else {
			i = Integer.parseInt(node.getChild(1).getName());
		}

		if (node.getChild(0) instanceof Variable) {
			j = Integer.parseInt(Evaluator.variables.get(node.getChild(0).getName()));

		} else {
			j = Integer.parseInt(node.getChild(0).getName());
		}

		/**
		 * realizam suna celor doua valori si updatam numele nodului curent cu
		 * valoarea calculata
		 */
		Integer n = i + j;

		node.setName(n.toString());

	}

	@Override
	public void visit(AssertNode node) {
		/**
		 * evaluam intai fiul nodului, pentru a respecta oridnea prioritatilor
		 * erorilor
		 */
		Evaluator.evaluate(node.getChild(0));

		/**
		 * daca nodul contine variabile nedeclarate pana acum nu mergem mai
		 * departe si propagam in sus check failed
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}
		/**
		 * daca valoarea din copil se evalueaza la false setam indicatorul de
		 * assert failed
		 */
		if (node.getChild(0).getName().contentEquals("false")) {
			Evaluator.assertFailed = true;
			return;
		} else {
			node.setName("true");
		}

	}

	@Override
	public void visit(AssignNode node) {
		/**
		 * Evaluam copiii din stanga si dreapta. Nu evaluam sintaxa in nodurile
		 * de tip Assign deoarece aici se asigneaza valori variailelor
		 */
		Evaluator.evaluate(node.getChild(0));
		Evaluator.evaluate(node.getChild(1));

		/**
		 * Verificam fiii si construim perechea Variabila, Valoare pentru a fi
		 * inserata in HahMap-ul din Evaluator
		 */
		Variable x = null;
		Value i = null;
		if (node.getChild(1) instanceof Variable) {
			x = new Variable(node.getChild(1).getName());
		} else {
			i = new Value(node.getChild(1).getName());
		}

		if (node.getChild(0) instanceof Variable) {
			x = new Variable(node.getChild(0).getName());
		} else {
			i = new Value(node.getChild(0).getName());
		}
		Evaluator.variables.put(x.getName(), i.getName());
		node.setName(i.getName());
	}

	@Override
	public void visit(IfNode node) {
		/**
		 * Efectuam verificarea pentru check failed intai si propagam in sus
		 * rezultatul in caz de esec
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}
		/**
		 * Verificare pentru assert failed
		 */
		if (Evaluator.checkAssert(node) == false) {
			return;
		}
		/**
		 * evaluam conditia si preluam rezultatul evaluarii fiului corespunzator
		 * branch-ului de then sau de else
		 */
		Evaluator.evaluate(node.getChild(0));

		/***
		 * Daca conditia este adevarata, continuam cu evaluarea celui de-al
		 * doiela fiu, iar daca este false evaluam al treilea fiu
		 */
		if (node.getChild(0).getName().contentEquals("true")) {
			Evaluator.evaluate(node.getChild(1));
			node.getChild(2).setVisited(true);

			if (node.getChild(1) instanceof Variable) {
				node.setName(Evaluator.variables.get(node.getChild(1).getName()));
			} else {
				node.setName(node.getChild(1).getName());
			}
		} else {
			Evaluator.evaluate(node.getChild(2));
			node.getChild(1).setVisited(true);

			if (node.getChild(2) instanceof Variable) {
				node.setName(Evaluator.variables.get(node.getChild(2).getName()));
			} else {
				node.setName(node.getChild(2).getName());
			}
		}

	}

	@Override
	public void visit(ForNode node) {
		/**
		 * verificare pentru check failed si assert failed
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}

		if (Evaluator.checkAssert(node) == false) {
			return;
		}
		/**
		 * initializare
		 */
		Evaluator.evaluate(node.getChild(0));

		/**
		 * evaluam conditia pentru a intra in for
		 */
		Evaluator.evaluate(node.getChild(1));
		while (node.getChild(1).getName().contentEquals("true")) {
			/**
			 * Daca conditia e adevarata, atunci executam programul din for
			 */
			Evaluator.evaluate(node.getChild(3));
			/**
			 * executam pasul
			 */
			Evaluator.evaluate(node.getChild(2));
			/**
			 * reevaluam conditia
			 */
			Evaluator.evaluate(node.getChild(1));
		}
		/**
		 * preluam valoarea din fiul al patrulea, corespunzator rezultatului
		 * evaluarii programului din for
		 */
		if (node.getChild(3) instanceof Variable) {
			node.setName(Evaluator.variables.get(node.getChild(3).getName()));
		} else {
			node.setName(node.getChild(3).getName());
		}

	}

	@Override
	public void visit(EqualityNode node) {
		/**
		 * verificare check failed si assert failed
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}

		if (Evaluator.checkAssert(node) == false) {
			return;
		}
		/**
		 * evaluam fiul stang si fiul drept
		 */
		Evaluator.evaluate(node.getChild(0));
		Evaluator.evaluate(node.getChild(1));

		String s2 = null;
		String s1 = null;
		/**
		 * preluam rezultatele evaluarii celor doi fii
		 */
		if (node.getChild(0) instanceof Variable) {
			s1 = Evaluator.variables.get(node.getChild(0).getName());
		} else {
			s1 = node.getChild(0).getName();
		}
		if (node.getChild(1) instanceof Variable) {
			s2 = Evaluator.variables.get(node.getChild(1).getName());
		} else {
			s2 = node.getChild(1).getName();
		}

		/**
		 * verificam daca cei doi fii s- au evaluat la aceeasi expresie
		 */
		if (s1.contentEquals(s2)) {
			node.setName("true");
		} else {
			node.setName("false");
		}

	}

	@Override
	public void visit(MultiplyNode node) {
		/**
		 * verificare check failed si assert failed
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}

		if (Evaluator.checkAssert(node) == false) {
			return;
		}

		/**
		 * evaluam cei doi fii
		 */
		Evaluator.evaluate(node.getChild(0));
		Evaluator.evaluate(node.getChild(1));

		Integer i, j;
		/**
		 * salvam si inmultim valorile rezulate in urma evaluarii celor 2 fii si
		 * setam valoarea corespunzatoare in nodul curent
		 */
		if (node.getChild(1) instanceof Variable) {
			i = Integer.parseInt(Evaluator.variables.get(node.getChild(1).getName()));

		} else {
			i = Integer.parseInt(node.getChild(1).getName());
		}

		if (node.getChild(0) instanceof Variable) {
			j = Integer.parseInt(Evaluator.variables.get(node.getChild(0).getName()));

		} else {
			j = Integer.parseInt(node.getChild(0).getName());
		}

		Integer n = i * j;

		node.setName(n.toString());
	}

	@Override
	public void visit(LessNode node) {
		/**
		 * verificare check failed si assert failed
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}

		if (Evaluator.checkAssert(node) == false) {
			return;
		}
		/**
		 * evaluam expresiile din cei 2 fii
		 */
		Evaluator.evaluate(node.getChild(0));
		Evaluator.evaluate(node.getChild(1));

		Integer s2 = 0;
		Integer s1 = 0;
		/**
		 * preluam rezultatele evaluarii si verificam daca primul este mai mic
		 * decat la doilea ,setand corespunzator valoarea din nodul curent in
		 * functie de rezultatul comparatiei
		 */
		if (node.getChild(0) instanceof Variable) {
			s1 = Integer.parseInt(Evaluator.variables.get(node.getChild(0).getName()));
		} else {
			s1 = Integer.parseInt(node.getChild(0).getName());
		}
		if (node.getChild(1) instanceof Variable) {
			s2 = Integer.parseInt(Evaluator.variables.get(node.getChild(1).getName()));
		} else {
			s2 = Integer.parseInt(node.getChild(1).getName());
		}

		if (s1 < s2) {
			node.setName("true");
		} else {
			node.setName("false");
		}
	}

	@Override
	public void visit(ReturnNode node) {
		/**
		 * indicam faptul ca exista return in program
		 */
		Evaluator.missingRet = false;
		/**
		 * verificam check failed, nu si assert failed, deoarece eroarea de
		 * missing return are prioritate mai mare decat cea de assert failed
		 */
		if (Evaluator.checkScope(node) == false) {
			return;
		}
		/**
		 * evaluam copilul nodului
		 */
		Evaluator.evaluate(node.getChild(0));
		/**
		 * preluam rezultatul evaluarii
		 */
		if (node.getChild(0) instanceof Variable) {
			node.setName(Evaluator.variables.get(node.getChild(0).getName()));
		} else {
			node.setName(node.getChild(0).getName());
		}
	}

	@Override
	public void visit(Variable v) {
	}

	@Override
	public void visit(Value v) {
	}

	@Override
	public void visit(Parantheses p) {

	}

	@Override
	public void visit(SemicolonNode semicolonNode) {
		/**
		 * verificare de assert failed
		 */
		if (Evaluator.checkAssert(semicolonNode) == false) {
			return;
		}

		/**
		 * evaluarea copiilor
		 */
		Evaluator.evaluate(semicolonNode.getChild(0));
		Evaluator.evaluate(semicolonNode.getChild(1));

		/**
		 * preluam rezultatul evaluarii celui de-al doilea copil
		 */
		if (semicolonNode.getChild(1) instanceof Variable) {
			semicolonNode.setName(Evaluator.variables.get(semicolonNode.getChild(1).getName()));
		} else {
			semicolonNode.setName(semicolonNode.getChild(1).getName());
		}

	}

}
