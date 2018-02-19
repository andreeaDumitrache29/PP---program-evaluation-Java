/**
 * 
 * @author Dumitrache Daniela Andreea 
 * 	
 * Interfata care reprezinta un element din
 * arbore. Acesta va fi decorat de catre Node.
 */
public interface Element {
	/**
	 * 
	 * @return numele elementului
	 */
	public String getName();
	
	/**
	 * 
	 * @return daca elementul a fost sau nu vizitat
	 */
	public boolean isVisited();
	
	/**
	 * 
	 * @param v seteaza daca elementul a fost sau nu vizitat
	 */
	public void setVisited(boolean v);
	/**
	 * 
	 * @return numarul de copii ai elementului
	 */
	public int getChildrenNumber();
	/**
	 * 
	 * @param i indice
	 * @return copilul de pe pozitia i din lista  de copii a elementului(daca exista)
	 */
	public Element getChild(int i);
	/**
	 * 
	 * @param visitor pentru evaluare
	 */
	public void accept(VisitorInterface visitor);
}
