import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class EqualityNode extends Node{
	/**
	 * Seteaza numele nodului ca EqualityNode si initializeaza lista de copii
	 */
	public EqualityNode() {
		this.setName("EqualityNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
