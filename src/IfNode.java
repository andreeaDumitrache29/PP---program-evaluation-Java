import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class IfNode extends Node {
	/**
	 * seteaza numele nodului ca fiind IfNode
	 */
	public IfNode() {
		this.setName("IfNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
