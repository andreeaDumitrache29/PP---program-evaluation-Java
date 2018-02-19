import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class ReturnNode extends Node{
	/**
	 * seteaza campul name ca ReturnNode
	 */
	public ReturnNode() {
		this.setName("ReturnNode");
		this.children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
