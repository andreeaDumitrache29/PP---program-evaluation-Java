import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class AssertNode extends Node{
	/**
	 * Seteaza numele nodului ca fiind AssertNode
	 */
	public AssertNode() {
		this.setName("AssertNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
		
	}

}
