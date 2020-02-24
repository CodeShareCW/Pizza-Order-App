package includes;

public class Drink extends Item{
	private boolean addicecream;

	public Drink(){}
	public Drink(String id, String name, double p){super(id, name, p);}
	public Drink(String id, String name, double p, boolean aic){super(id, name, p);addicecream=aic;}
	public void setAddIceCream(boolean aic){addicecream=aic;}

	public boolean getAddIceCream(){return addicecream;}
}