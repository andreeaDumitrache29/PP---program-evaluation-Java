import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class AssignNode extends Node{
	/**
	 * Seteazanumele nodului ca fiind AssignNode
	 */
	public AssignNode() {
		this.setName("AssignNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
