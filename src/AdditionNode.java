import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class AdditionNode extends Node {
	/**
	 * seteaza numele nodului drept AdiitionNode si initializeaza lista de copii
	 */
	public AdditionNode() {
		this.setName("AdditionNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
