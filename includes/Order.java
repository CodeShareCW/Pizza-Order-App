package includes;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.*;

public class Order{
	private int total_item;
	private double overall_pay;
	private ArrayList<Item> order_item_list;

	public Order()
	{
		total_item=0;
		overall_pay=0;
		order_item_list=new ArrayList<Item>();
	}


	public void LoadCurrentUserOrder(File user_order_file, char delimiter, ArrayList<Item> pizza_item_list, ArrayList<Item> drink_item_list)
	{
		try{
			BufferedReader reader=new BufferedReader(new FileReader(user_order_file));
			String line;
			while ((line=reader.readLine())!=null)
			{
				Item item=new Item();
				int count=0;
				int pos=0;
				for (int i=0; i<line.length(); i++)
				{
					if (count==0&&line.charAt(i)==delimiter)
					{
						item.setItemID(line.substring(pos, i));
							for (Item p: pizza_item_list)
								if (p.getItemID().equals(item.getItemID()))
								{
									item=p;
									break;
								}

							for (Item d: drink_item_list)
								if (d.getItemID().equals(item.getItemID()))
								{
									item=d;
									break;
								}

						pos=i;
						count++;
					}
					else if (count==1&&line.charAt(i)==delimiter)
					{
						int qty=Integer.parseInt(line.substring(pos+1, i));
						item.setQuantity(qty);
						pos=i;
						count++;
					}
					else if (count==2&&line.charAt(i)==delimiter)
					{
						if (line.substring(pos+1, i).equals("Cheese")||line.substring(pos+1, i).equals("Ice Cream"))
							item.setAddTopping(true);
						else item.setAddTopping(false);
						pos=i;
						count++;
					}
				}
				if (line!=null)
					order_item_list.add(item);
			}
			int index=user_order_file.getName().indexOf('.');
			String ID=user_order_file.getName().substring(0, index);
			System.out.println("Finished Loading UserID: "+ID+" 's Order. Retrived order file done...");



			reader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "File Error: "+e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}



	public void ProcessRequestedOrder(File user_order_file, char delimiter, Item item)
	{

			order_item_list.add(item);

			try{
				BufferedWriter writer=new BufferedWriter(new FileWriter(user_order_file.getAbsoluteFile(), true));
				writer.write(item.getItemID()+delimiter);
				writer.write(Integer.toString(item.getQuantity())+delimiter);
				writer.write(item.getToppingName()+delimiter);
				writer.newLine();
				writer.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

	}

	public void ClearAllOrder(File file)
	{
		try{
			BufferedWriter writer=new BufferedWriter(new FileWriter(file));
			writer.close();
			int index=file.getName().indexOf('.');
			String ID=file.getName().substring(0, index);
			System.out.println("Finished clearing up UserID: "+ID+" 's Order...");
		}
		catch(Exception e)
		{
			System.out.println("File error: "+e.getMessage());
		}
	}


	//rewrite order file
	public void RemoveOrderedItem(File user_order_file, char delimiter, int index)
	{
		order_item_list.remove(index);
		try{
			BufferedWriter writer=new BufferedWriter(new FileWriter(user_order_file));
			for (Item i: order_item_list)
			{
				writer.write(i.getItemID()+Character.toString(delimiter));
				writer.write(i.getQuantity()+Character.toString(delimiter));
				writer.write(i.getToppingName()+Character.toString(delimiter));
			}
			writer.close();
		}
		catch(Exception e)
		{
			System.out.println("File error: "+e.getMessage());
		}
	}



	public void setOrderItemList(ArrayList<Item> list){order_item_list=list;}
	public ArrayList<Item> getOrderedItemList(){return order_item_list;}

	public String getOrderedItemDescription(int index)
	{
		return String.format("%-6s%-30s%-10s%-20sRM %.2f", (index+1),order_item_list.get(index).getItemName(), order_item_list.get(index).getQuantity(), order_item_list.get(index).getToppingName(), order_item_list.get(index).getSubTotal());
	}

	public int getTotalItem()
	{
		for (Item i: order_item_list)
			total_item+=i.getQuantity();
		return total_item;
	}
	public double getOverallPay()
	{
		for (Item i: order_item_list)
			overall_pay+=i.getSubTotal();
		return overall_pay;
	}




}