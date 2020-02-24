package includes;

public class Pizza extends Item{
	private boolean addcheese;

	public Pizza(){}
	public Pizza(String id, String name, double p){super(id, name, p);}
	public Pizza(String id, String name, double p, boolean ac){super(id, name, p); addcheese=ac;}
	public void setAddCheese(boolean ac){addcheese=ac;}
	public boolean getAddCheese(){return addcheese;}
}