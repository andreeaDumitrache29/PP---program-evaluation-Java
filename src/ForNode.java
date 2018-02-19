import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class ForNode extends Node {
	/**
	 * seteaza numele nodului ca ForNode
	 */
	public ForNode() {
		this.setName("ForNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
