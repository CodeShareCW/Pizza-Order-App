package includes;

public class Item{
//item description
	private String itemID;
	private String item_name;
	private boolean add_topping;
	private double price;

//order item
	protected int quantity;

	public Item(){}
	public Item(String id, String n, double p){itemID=id; item_name=n; price=p;}
	public void setItemID(String id){itemID=id;}
	public void setItemName(String n){item_name=n;}
	public void setPrice(double p){price=p;}
	public String getItemID(){return itemID;}
	public String getItemName(){return item_name;}
	public double getPrice(){return price;}

	public String getItemDescription(){return String.format("%-4s%-30sRM %.2f", itemID, item_name, price);}

//order item function
	public void setQuantity(int qty){quantity=qty;}
	public int getQuantity(){return quantity;}

	public void setAddTopping(boolean addtopping){add_topping=addtopping;}
	public boolean getAddTopping(){return add_topping;}

	public String getToppingName()
	{
		//case where user add topping
		if(add_topping==true)
		{
			if (itemID.charAt(0)=='P')
				return "Cheese";
			else if (itemID.charAt(0)=='D')
				return "Ice Cream";
			else return "Error";
		}
		else return "-";
	}

	public double getSubTotal(){return quantity*price;}
	public String getSubTotalString(){return String.format("%.2f", getSubTotal());}



}//end Item class