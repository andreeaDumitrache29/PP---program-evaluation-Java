import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class InputReader {
	protected BufferedReader reader;
	protected BufferedWriter writer;

	/**
	 * 
	 * @param in
	 *            fisierul de intrare
	 * @param out
	 *            fisierul desire
	 * @throws IOException
	 */
	public void readInput(FileReader in, FileWriter out) throws IOException {
		reader = new BufferedReader(in);
		writer = new BufferedWriter(out);
		String s = reader.readLine();
		String res = s;
		/**
		 * cititm inputul linie cu linie
		 */
		while (s != null) {
			s = reader.readLine();
			res += s;
		}

		/**
		 * creem array-ul de operatori, care e folosit in constructia arborelui
		 */
		ArrayList<String> operators = createOperators();
		/**
		 * parsam expresia, eliminand spatiile, tab-urile si liniile noi si
		 * inlocuindu-le cu spatii, pentru a avea expresia pe un singur rand
		 */
		res = res.replace('\n', ' ');
		res = res.replace('\t', ' ');
		res = res.replace("null", "");
		res = res.replaceAll("\\s+", " ");
		/**
		 * construim si evaluam arborele, verificand cele 3 tipuri de erori
		 * inainte de a afisa rezultatul final
		 */
		ConstructTree constr = new ConstructTree(operators);
		constr.createTree(res);
		Evaluator.evaluate(constr.getRoot());
		if (constr.getRoot().getName().contentEquals("Check failed")) {
			writer.write("Check failed");
		} else {
			Evaluator.checkReturn(constr.getRoot());
			writer.write(constr.getRoot().getName());
		}
		writer.close();
		reader.close();
	}
	/**
	 * 
	 * @return array-ul cu operatorii ce pot aparea in expresii 
	 */
	public ArrayList<String> createOperators() {
		ArrayList<String> a = new ArrayList<>();
		a.add("+");
		a.add("assert");
		a.add("=");
		a.add("==");
		a.add("for");
		a.add("if");
		a.add("*");
		a.add("<");
		a.add("return");
		a.add(";");

		return a;
	}
}
