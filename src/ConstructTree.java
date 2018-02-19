import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class ConstructTree {
	/**
	 * radacina arborelui
	 */
	private Node root;
	/**
	 * lista cu toti operatorii care pot aparea in expresie: +, *, =, <, ==,
	 * assert if, for, return
	 */
	private ArrayList<String> operators;

	public ConstructTree(ArrayList<String> operators) {
		this.operators = new ArrayList<>();
		this.operators = operators;
		this.root = new OperatorNodeFactory().createNode(";");
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	/**
	 * 
	 * @param n
	 *            elementul de start
	 * Reseteaza toate nodurile ca fiind nevizitate
	 */
	public void resetVisited(Element n) {
		n.setVisited(false);
		for (int i = 0; i < n.getChildrenNumber(); i++) {
			if (n.getChild(i).isVisited() == true) {
				resetVisited(n.getChild(i));
			}
		}
	}
	/**
	 * 
	 * @param stack stiva de operanzi
	 * @param op numele unui operator
	 */
	public void addNode(Stack<Element> stack, String op) {
		/**
		 * creeaza un nou nod, in functie de operatorul primit
		 */
		OperatorNodeFactory factory = new OperatorNodeFactory();
		Node node = factory.createNode(op);
		ArrayList<Element> list = new ArrayList<>();
		Element n;
		/**
		 * cauta in stiva de operanzi si extrage toate elementele pana la prima paranteza.
		 * acestea vor fii copii nodului creat
		 */
		while (!stack.isEmpty()) {
			n = stack.pop();
			if (n.getName().contentEquals("[") == false) {
				list.add(n);
			} else {
				break;
			}
		}
		/**
		 * extrage elementele in ordinea inversa scoaterii lor de pe stiva, pentru a le
		 * adauga in ordinea corecta in lista de copii
		 */
		int nr = list.size() - 1;
		while (!list.isEmpty()) {
			n = list.remove(nr);
			node.addChild(n);
			nr = list.size() - 1;
		}

		stack.push(node);
	}
	/**
	 * 
	 * @param s string de verificat
	 * @return true daca s reprezinta un numar, false altfel
	 */
	public boolean isNumber(String s) {
		for (Integer i = 0; i <= 9; i++) {
			if (s.startsWith(i.toString()))
				return true;
		}
		return false;
	}
	/**
	 * 
	 * @param expression  expresia programului care trebuie sa fie evaluat
	 */
	public void createTree(String expression) {
		/**
		 * vom folosi 2 sitve: una pentru operatori, cealalta pentru operanzi
		 */
		Stack<Element> operands = new Stack<>();
		Stack<String> operator = new Stack<>();
		/**
		 * separam expresia dupa spatiu
		 */
		String[] tokens = expression.split(" ");
		Element o, op;
		String s;
		boolean ok;

		for (int i = 0; i < tokens.length; i++) {
			ok = false;
			/**
			 * cat timp elementul curent incepe cu paranteza: elimina
			 * prima paranteza si adaug-o in stiva de operanzi
			 */
			while (tokens[i].startsWith("[")) {
				op = new Parantheses("[");
				operands.push(op);
				tokens[i] = tokens[i].substring(1, tokens[i].length());
			}

			int contor = 0;
			/**
			 * daca am ajuns la o ], atunci trebuie sa creem nodul corespunzator
			 * expresiei ce se afla in interiorul ei.
			 * Pentru aceasta: extragem ultimul operator adaugat pe stiva de operatori
			 * si apelam functia addNode, care creeaza nodul corespunzator operatorului respectiv.
			 */
			if (tokens[i].contentEquals("]")) {
				if (!operator.isEmpty()) {
					s = operator.pop();
					addNode(operands, s);
					ok = true;
				}
			} else if (tokens[i].endsWith("]")) {
				while (tokens[i].endsWith("]")) {
					/**
					 * extragem si evetualul element atasat de parantezele inchise
					 */
					contor++;
					tokens[i] = tokens[i].substring(0, tokens[i].length() - 1);
				}
			}
			if (ok == false) {
				/**
				 * verificam acum daca ce a ramas din elementul curent dupa eliminarea parantezelor
				 * de la inceput si de la sfarsit este un operator, o variabila sau o valoare
				 */
				if (operators.contains(tokens[i])) {
					operator.push(tokens[i]);
				} else if (tokens[i].contentEquals("") == false) {
					if (isNumber(tokens[i]) == true)
						o = new Value(tokens[i]);
					else
						o = new Variable(tokens[i]);
					operands.push(o);
				}
			}
			/**
			 * daca am avut mai multe paranteze ], atunci extragem numarul corespunzator
			 * de operatori de pe stivas i construim nodurile pentru ei			 
			 **/
			while (contor > 0) {
				contor--;
				if (!operator.isEmpty()) {
					s = operator.pop();
					addNode(operands, s);
				}
			}
		}
		/**
		 * ultimul element ramas pe stiva reprezinta radacina arborelui
		 */
		Element E = operands.peek();
		if (E instanceof SemicolonNode) {
			root = (SemicolonNode) operands.pop();
		}
	}
}
