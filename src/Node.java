import java.util.ArrayList;

/**
 * 
 * @author Dumitrache Daniela Andreea
 */

/**
 * * Node reprezinta un element din arbore, care, spre deosebire de elementele
 * atomice(Variable, Value si Paranthesis) are si o lista de copii, fiind un nod
 * mai complex, ce va trebui sa fie evaluat
 *
 */
public abstract class Node implements Element {
	protected ArrayList<Element> children;
	private String name;
	private boolean visited;

	public Node() {
		children = new ArrayList<Element>();
	}

	public void addChild(Element node) {
		children.add(node);
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getChildrenNumber() {
		return children.size();
	}

	public Element getChild(int i) {
		return children.get(i);
	}

	public abstract void accept(VisitorInterface visitor);
}
