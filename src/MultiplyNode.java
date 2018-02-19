import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class MultiplyNode extends Node{
	/**
	 * seteaza numele nodului ca fiind MultiplyNode
	 */
	public MultiplyNode() {
		this.setName("MultiplyNode");
		this.children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
