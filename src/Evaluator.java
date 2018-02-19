import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class Evaluator {
	/**
	 * folosim un HashMap pentru a retine perechi de tipul <nume variabila,
	 * valoare>
	 */
	public static Map<String, String> variables = new HashMap<>();
	public static boolean missingRet = true;
	public static boolean checkFailed = false;
	public static boolean assertFailed = false;

	/**
	 * 
	 * @param node
	 *            nodul de evaluat
	 * @return true daca nodul nu are drept copii variabile nedeclarate
	 *         anterior, false daca da
	 */
	public static boolean checkScope(Node node) {
		for (int i = 0; i < node.getChildrenNumber(); i++) {
			if (node.getChild(i).getName().contentEquals("Check failed") || (node.getChild(i) instanceof Variable
					&& variables.containsKey(node.getChild(i).getName()) == false)) {
				checkFailed = true;
				break;
			}
		}
		if (checkFailed == true) {
			node.setName("Check failed");
			return false;

		}
		return true;
	}

	/**
	 * 
	 * @param node
	 *            nodul de evaluat
	 * @return false daca s-a ajuns pana acum in executie la assert failed, true
	 *         altfel
	 */
	public static boolean checkAssert(Node node) {
		if (assertFailed == true) {
			node.setName("Assert failed");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param node
	 *            nodul de evaluat Se apeleaza visitor pe nodul curent pentru a
	 *            fi evaluat
	 */
	public static void evaluate(Element node) {
		Visitor visitor = new Visitor();
		node.setVisited(true);
		node.accept(visitor);
	}

	/**
	 * 
	 * @param n
	 *            nodul de evluat
	 * @return true daca nu s-a ajuns nici la Missing return nici la Assert
	 *         failed in timpul executiei, false altfel
	 */
	public static boolean checkReturn(Node n) {
		if (missingRet == true) {
			n.setName("Missing return");
			return false;
		}
		if (checkAssert(n) == false)
			return false;
		return true;
	}

}
