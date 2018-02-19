/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class OperatorNodeFactory {

	/**
	 * 
	 * @param op
	 *            string-ul care determina tipul de nod dorit
	 * @return Nodul corespunzator operatorului dat
	 */
	public Node createNode(String op) {
		switch (op) {
		case "=":
			return new AssignNode();
		case "+":
			return new AdditionNode();
		case "==":
			return new EqualityNode();
		case "assert":
			return new AssertNode();
		case "for":
			return new ForNode();
		case "if":
			return new IfNode();
		case "*":
			return new MultiplyNode();
		case "<":
			return new LessNode();
		case "return":
			return new ReturnNode();
		case ";":
			return new SemicolonNode();
		default:
			return new Node() {
				/**
				 * pentru radacina
				 */
				@Override
				public void accept(VisitorInterface visitor) {
				}
			};
		}
	}
}
