import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class SemicolonNode extends Node {
	/**
	 * seteazanumele nodului ca SemicolonNode
	 */
	public SemicolonNode() {
		this.setName("SemicolonNode");
		this.children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
