import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		/**
		 * deschide fisierele si apeleaza functia readInput, care construieste
		 * arborele si evalueaza expresia
		 */
		InputReader inp = new InputReader();
		FileReader in = new FileReader(args[0]);
		FileWriter out = new FileWriter(args[1]);
		inp.readInput(in, out);
		in.close();
		out.close();
	}
}
