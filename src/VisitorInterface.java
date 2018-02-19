/**
 * 
 * @author Dumitrache Daniela Andreea 
 * Functiile accept pentru toate tipurile de noduri si elemente atom
 */
public interface VisitorInterface {
	public void visit(AdditionNode node);

	public void visit(AssertNode node);

	public void visit(AssignNode node);

	public void visit(IfNode node);

	public void visit(ForNode node);

	public void visit(EqualityNode node);

	public void visit(MultiplyNode node);

	public void visit(LessNode node);

	public void visit(ReturnNode node);

	public void visit(Variable v);

	public void visit(Value v);

	public void visit(Parantheses p);

	public void visit(SemicolonNode semicolonNode);
}
