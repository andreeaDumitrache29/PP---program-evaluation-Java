import java.util.ArrayList;
/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class LessNode extends Node{
/**
 * seteaza numele nodului ca LessNode
 */
	public LessNode() {
		this.setName("LessNode");
		children = new ArrayList<>();
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}

}
