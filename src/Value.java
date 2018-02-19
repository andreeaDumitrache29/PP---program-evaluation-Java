/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class Value implements Element {
	
	private String name;
	private boolean visited;
	
	
	public Value (String name){
		this.name = name;
	}

	public Value() {
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isVisited() {
		return visited;
	}

	@Override
	public void setVisited(boolean v) {
		this.visited = v;
	}

	@Override
	public int getChildrenNumber() {
		return 0;
	}

	@Override
	public Element getChild(int i) {
		return null;
	}

	@Override
	public void accept(VisitorInterface visitor) {
		visitor.visit(this);
	}
}
