package includes;
import includes.page.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Activity extends JFrame implements ActionListener, KeyListener{
	char delimiter=DefaultValue.DELIMITER_STYLE;

	private File user_account_file;
	private Vector<User> user_list;

	private File pizza_menu_file;
	private ArrayList<Item> pizza_item_list;

	private File drink_menu_file;
	private ArrayList<Item> drink_item_list;

	private WelcomePage welcome;
	private LoginPage login;
	private RegisterPage register;
	private LobbyPage lobby;
	private ProfilePage profile;
	private EditProfilePage editprofile;
	private MenuPage menu;
	private ItemPage pizza;
	private ItemPage drink;
	private AddOrderPage addorder;
	private MyOrderPage myorder;

	//to indicate which page user enter in the menu(pizza/drink)
	private int menu_page_indicator;

	private User current_user;
	private Item current_selected_item;

	private Order current_user_order;

	private CardLayout pagelayout;
	private JPanel pagecontent;

	public Activity()
	{
		try{
			user_list=new Vector<User>();
			LoadUserAccountDatabase();

			pizza_item_list=new ArrayList<Item>();
			LoadPizzaMenu();

			drink_item_list=new ArrayList<Item>();
			LoadDrinkMenu();
		}catch(Exception e)
		{
			System.out.print("File error: "+e.getMessage());
			dispose();
		}

		setTitle(PageTitle.WelcomePage.getTitle());
		setSize(DefaultValue.WIN_SIZE_X, DefaultValue.WIN_SIZE_Y);
		getContentPane().setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setResizable(false);

		pagelayout=new CardLayout();
		pagecontent=new JPanel();
		pagecontent.setLayout(pagelayout);

		welcome=new WelcomePage();
		login=new LoginPage();
		register=new RegisterPage();
		lobby=new LobbyPage();
		profile=new ProfilePage();
		editprofile=new EditProfilePage();
		menu=new MenuPage();
		pizza=new ItemPage();
		drink=new ItemPage();
		addorder=new AddOrderPage();
		myorder=new MyOrderPage();

		pagecontent.add(welcome.ContentPanel(), PageTitle.WelcomePage.getTitle());
		pagecontent.add(login.ContentPanel(), PageTitle.LoginPage.getTitle());
	    pagecontent.add(register.ContentPanel(), PageTitle.RegisterPage.getTitle());
		pagecontent.add(lobby.ContentPanel(), PageTitle.LobbyPage.getTitle());
		pagecontent.add(profile.ContentPanel(), PageTitle.ProfilePage.getTitle());
		pagecontent.add(editprofile.ContentPanel(), PageTitle.EditProfilePage.getTitle());
		pagecontent.add(menu.ContentPanel(), PageTitle.MenuPage.getTitle());
		pagecontent.add(pizza.ContentPanel(), PageTitle.PizzaMenuPage.getTitle());
		pagecontent.add(drink.ContentPanel(), PageTitle.DrinkMenuPage.getTitle());
		pagecontent.add(addorder.ContentPanel(), PageTitle.AddOrderPage.getTitle());
		pagecontent.add(myorder.ContentPanel(), PageTitle.MyOrderPage.getTitle());

		//renew the label in the pizza menu page first
		pizza.SetupItemLabelList(pizza_item_list);
		//reset viewport of scrollpane
		pizza.SetupViewportScrollPane();

		//renew the label in the drink menu page first
		drink.SetupItemLabelList(drink_item_list);
		//reset viewport of scrollpane
		drink.SetupViewportScrollPane();

		AddListener();
		getContentPane().add(pagecontent);

		if (user_list.size()>0)
		{
			System.out.println("\n-----------User Account Database----------\n");
			int count=0;
			for (User u: user_list)
			{
				count++;
				System.out.println("User#"+count);
				System.out.println("UserID: "+u.getUserID());
				System.out.println("Username: "+u.getUsername());
				System.out.println("Password: "+u.getPassword()+"\n");
			}
		}
	}
	public void AddListener()
	{
		welcome.loginbtn.addActionListener(this);
		welcome.registerbtn.addActionListener(this);
		welcome.exitbtn.addActionListener(this);

		login.submitbtn.addActionListener(this);
		login.cancelbtn.addActionListener(this);

		register.submitbtn.addActionListener(this);
		register.resetbtn.addActionListener(this);
		register.cancelbtn.addActionListener(this);

		lobby.profilebtn.addActionListener(this);
		lobby.menubtn.addActionListener(this);
		lobby.myorderbtn.addActionListener(this);
		lobby.logoutbtn.addActionListener(this);

		profile.editbtn.addActionListener(this);
		profile.backbtn.addActionListener(this);

		editprofile.submitbtn.addActionListener(this);
		editprofile.resetbtn.addActionListener(this);
		editprofile.backbtn.addActionListener(this);

		menu.pizzabtn.addActionListener(this);
		menu.drinkbtn.addActionListener(this);
		menu.backbtn.addActionListener(this);

		for (JButton b: pizza.order_button_list)
			b.addActionListener(this);

		for (JButton b: drink.order_button_list)
			b.addActionListener(this);

		pizza.backbtn.addActionListener(this);
		drink.backbtn.addActionListener(this);

		addorder.incrementbtn.addActionListener(this);
		addorder.decrementbtn.addActionListener(this);
		addorder.yesbtn.addActionListener(this);
		addorder.nobtn.addActionListener(this);
		addorder.addbtn.addActionListener(this);
		addorder.backbtn.addActionListener(this);

		myorder.confirm_order_btn.addActionListener(this);
		myorder.clear_all_order_btn.addActionListener(this);
		myorder.backbtn.addActionListener(this);

	}
//Update and flip page
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==welcome.loginbtn)
		{
			//enable default key(ENTER) in login
			getRootPane().setDefaultButton(login.submitbtn);
			pagelayout.show(pagecontent, PageTitle.LoginPage.getTitle());
			setTitle(PageTitle.LoginPage.getTitle());

			//set focus to username textfield
			login.username_textfield.requestFocus(true);
		}
		if (e.getSource()==welcome.registerbtn)
		{
			//enable default key(ENTER) in register
			getRootPane().setDefaultButton(register.submitbtn);
			pagelayout.show(pagecontent, PageTitle.RegisterPage.getTitle());
			setTitle(PageTitle.RegisterPage.getTitle());
			register.firstname_textfield.requestFocus(true);
		}

		if (e.getSource()==welcome.exitbtn)
		{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to exit?","Confirm Exit", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null, "See you next time!", "Bye bye", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				System.exit(0);
			}
		}
		if (e.getSource()==register.submitbtn)
		{
			register.ResetTextFieldBGColorWhite();
			if (register.isRegisterSuccess(user_account_file,user_list))
			{
				//remove default key when user success register
				getRootPane().setDefaultButton(null);

				JOptionPane.showMessageDialog(null, "Successfully register!\nPlease proceed to login!", "Register Success", JOptionPane.INFORMATION_MESSAGE);
				pagelayout.show(pagecontent, PageTitle.WelcomePage.getTitle());
				setTitle(PageTitle.WelcomePage.getTitle());
				register.Reset();
			}

		}

		if (e.getSource()==register.resetbtn)
		{
			register.Reset();
			register.ResetTextFieldBGColorWhite();
		}
		if (e.getSource()==register.cancelbtn)
		{
			//remove effect of 'enter' button once user press cancel
			getRootPane().setDefaultButton(null);

			pagelayout.show(pagecontent, PageTitle.WelcomePage.getTitle());
			setTitle(PageTitle.WelcomePage.getTitle());
			register.Reset();
			register.ResetTextFieldBGColorWhite();
		}

		if (e.getSource()==login.submitbtn)
		{
			login.ResetTextFieldBGColorWhite();
			if (login.isLoginSuccess(user_list))
			{
				getRootPane().setDefaultButton(null);
				JOptionPane.showMessageDialog(null, "Successfully Login!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
				current_user=login.getCurrentUser(user_list, login.username_textfield.getText());
				lobby.greeting_label.setText("Good Day, "+current_user.getFirstName());
				pagelayout.show(pagecontent, PageTitle.LobbyPage.getTitle());
				setTitle(PageTitle.LobbyPage.getTitle());
				login.Reset();
				login.ResetTextFieldBGColorWhite();

				//loading current user order
				current_user_order=new Order();
				File current_user_order_file=new File(DefaultValue.USER_ORDER_DIR+"/"+current_user.getUserID()+".txt");
				current_user_order.LoadCurrentUserOrder(current_user_order_file, delimiter, pizza_item_list, drink_item_list);


				//reset first myorder page first
				myorder.Reset();
				myorder.SetupOrderedItemLabelList(current_user_order);
				myorder.SetupViewportScrollPane();
				//setting total item and overall pay label
				myorder.total_item_label.setText("Total Item: "+current_user_order.getTotalItem());
				String overall_pay_str=String.format("%.2f", current_user_order.getOverallPay());
				myorder.overall_pay_label.setText("Amount Pay: RM "+overall_pay_str);
			}
		}
		if (e.getSource()==login.cancelbtn)
		{
			//remove effect of 'enter' button once user press cancel
			getRootPane().setDefaultButton(null);

			pagelayout.show(pagecontent, PageTitle.WelcomePage.getTitle());
			setTitle(PageTitle.WelcomePage.getTitle());
			login.Reset();
			login.ResetTextFieldBGColorWhite();
		}

		if (e.getSource()==lobby.profilebtn)
		{
			//update label
			profile.name_label.setText("Name: "+current_user.getFirstName()+" "+current_user.getLastName());
			profile.telephoneno_label.setText("Telephone No: "+current_user.getTelephoneNo());
			profile.address_label.setText("Address: "+current_user.getAddress().getFullAddress());

			pagelayout.show(pagecontent, PageTitle.ProfilePage.getTitle());
			setTitle(PageTitle.ProfilePage.getTitle());
		}

		if (e.getSource()==lobby.menubtn)
		{
			pagelayout.show(pagecontent, PageTitle.MenuPage.getTitle());
			setTitle(PageTitle.MenuPage.getTitle());
		}

		if (e.getSource()==lobby.myorderbtn)
		{
			//order part.............
			//reset first myorder page first
			pagelayout.show(pagecontent, PageTitle.MyOrderPage.getTitle());
			setTitle(PageTitle.MyOrderPage.getTitle());
		}

		if (e.getSource()==lobby.logoutbtn)
		{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Do you want to log out?","Confirm Log Out", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION)
			{
				current_user=new User();
				current_user_order=new Order();
				myorder.Reset();
				pagelayout.show(pagecontent, PageTitle.WelcomePage.getTitle());
				setTitle(PageTitle.WelcomePage.getTitle());
			}
		}

		if (e.getSource()==profile.editbtn)
		{
			pagelayout.show(pagecontent, PageTitle.EditProfilePage.getTitle());
			setTitle(PageTitle.EditProfilePage.getTitle());

			String first=current_user.getFirstName();
			String last=current_user.getLastName();
			String tpno=current_user.getTelephoneNo();
			String hno=Integer.toString(current_user.getAddress().getHouseNo());
			String street=current_user.getAddress().getStreet();
			String state=current_user.getAddress().getState();

			editprofile.PresetCurrentUserProfile(first, last, tpno, hno, street, state);

			//set focus to username textfield
			editprofile.firstname_textfield.requestFocus(true);
			//enable default key of enter in edit profile
			getRootPane().setDefaultButton(editprofile.submitbtn);
		}

		if (e.getSource()==profile.backbtn)
		{
			pagelayout.show(pagecontent, PageTitle.LobbyPage.getTitle());
			setTitle(PageTitle.LobbyPage.getTitle());
		}

		if (e.getSource()==editprofile.submitbtn)
		{
			editprofile.ResetTextFieldBGColorWhite();
			if(editprofile.isEditCurrentUserProfileSuccess(user_account_file, Character.toString(delimiter), user_list, current_user))
			{
				//remove effect of 'enter' button once user success edit profile
				getRootPane().setDefaultButton(null);
				//update label
				lobby.greeting_label.setText("Good Day, "+current_user.getFirstName());
				profile.name_label.setText("Name: "+current_user.getFirstName()+" "+current_user.getLastName());
				profile.telephoneno_label.setText("Telephone No: "+current_user.getTelephoneNo());
				profile.address_label.setText("Address: "+current_user.getAddress().getFullAddress());

				JOptionPane.showMessageDialog(null, "Successfully Edit Profile!", "Edit Profile Success", JOptionPane.INFORMATION_MESSAGE);
				editprofile.Reset();
				pagelayout.show(pagecontent, PageTitle.ProfilePage.getTitle());
				setTitle(PageTitle.ProfilePage.getTitle());
			}
		}
		if (e.getSource()==editprofile.resetbtn)
		{
			editprofile.Reset();
			editprofile.ResetTextFieldBGColorWhite();
		}
		if (e.getSource()==editprofile.backbtn)
		{
			//remove effect of 'enter' button once user press back
			getRootPane().setDefaultButton(null);
			pagelayout.show(pagecontent, PageTitle.ProfilePage.getTitle());
			setTitle(PageTitle.ProfilePage.getTitle());
			editprofile.Reset();
			editprofile.ResetTextFieldBGColorWhite();
		}


		if (e.getSource()==menu.pizzabtn)
		{
			pizza.page_label.setText("Pizza");
			addorder.addon_label.setText("Add Cheese: ");
			pagelayout.show(pagecontent, PageTitle.PizzaMenuPage.getTitle());
			setTitle(PageTitle.PizzaMenuPage.getTitle());
			menu_page_indicator=1;
		}
		if (e.getSource()==menu.drinkbtn)
		{
			drink.page_label.setText("Drink");
			addorder.addon_label.setText("Add Ice Cream: ");
			pagelayout.show(pagecontent, PageTitle.DrinkMenuPage.getTitle());
			setTitle(PageTitle.DrinkMenuPage.getTitle());
			menu_page_indicator=2;
		}
		if (e.getSource()==menu.backbtn)
		{
			pagelayout.show(pagecontent, PageTitle.LobbyPage.getTitle());
			setTitle(PageTitle.LobbyPage.getTitle());
		}

		for(int i=0; i<pizza.order_button_list.size();i++)
		{
			if (e.getSource()==pizza.order_button_list.get(i))
				{
					current_selected_item=pizza_item_list.get(i);
					addorder.item_description_label.setText(current_selected_item.getItemDescription());
					pagelayout.show(pagecontent, PageTitle.AddOrderPage.getTitle());
					setTitle(PageTitle.AddOrderPage.getTitle());
					break;
				}

		}


		for(int i=0; i<drink.order_button_list.size();i++)
		{
			if (e.getSource()==drink.order_button_list.get(i))
			{
				current_selected_item=drink_item_list.get(i);
				addorder.item_description_label.setText(current_selected_item.getItemDescription());
				pagelayout.show(pagecontent, PageTitle.AddOrderPage.getTitle());
				setTitle(PageTitle.AddOrderPage.getTitle());
				break;
			}
		}

		if (e.getSource()==pizza.backbtn||e.getSource()==drink.backbtn)
		{
			pagelayout.show(pagecontent, PageTitle.MenuPage.getTitle());
			setTitle(PageTitle.MenuPage.getTitle());
		}

		if (e.getSource()==addorder.incrementbtn)
		{
			int qty=Integer.parseInt(addorder.quantity_textfield.getText());
			qty++;
			addorder.quantity_textfield.setText(Integer.toString(qty));
			current_selected_item.setQuantity(qty);
			addorder.sub_total_label.setText("SubTotal:        RM "+current_selected_item.getSubTotalString());
		}
		if (e.getSource()==addorder.decrementbtn)
		{
			//give not change when user keep press this decrement button
			if (!addorder.quantity_textfield.getText().equals("0"))
			{
				int qty=Integer.parseInt(addorder.quantity_textfield.getText());
				qty--;
				addorder.quantity_textfield.setText(Integer.toString(qty));
				current_selected_item.setQuantity(qty);
				addorder.sub_total_label.setText("SubTotal:        RM "+current_selected_item.getSubTotalString());
			}
		}
		if (e.getSource()==addorder.yesbtn)
		{
			current_selected_item.setAddTopping(true);
		}
		if (e.getSource()==addorder.nobtn)
		{
			current_selected_item.setAddTopping(false);
		}
		if (e.getSource()==addorder.addbtn)
		{
			//add order into current user order

				//if quantity is 0, ignore this process
				if (!addorder.quantity_textfield.getText().equals("0"))
				{
					File current_user_order_file=new File(DefaultValue.USER_ORDER_DIR+"/"+current_user.getUserID()+".txt");
					//reload current user order,
					current_user_order.ProcessRequestedOrder(current_user_order_file, delimiter, current_selected_item);
					current_user_order=new Order();
					current_user_order.LoadCurrentUserOrder(current_user_order_file, delimiter, pizza_item_list, drink_item_list);
					/*we will rewrite again the label*/
					//first reset my order page
					myorder.Reset();
					//then renew the item label
					myorder.SetupOrderedItemLabelList(current_user_order);
					//then re-setup the content of viewport
					myorder.SetupViewportScrollPane();
					myorder.total_item_label.setText("Total Item: "+current_user_order.getTotalItem());
					String overall_pay_str=String.format("%.2f", current_user_order.getOverallPay());

					myorder.overall_pay_label.setText("Amount Pay: RM "+overall_pay_str);


					JOptionPane.showMessageDialog(null, "Successfully Add Order!", "Add Order Success", JOptionPane.INFORMATION_MESSAGE);

					addorder.Reset();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No item added into order!", "Add Order Info", JOptionPane.INFORMATION_MESSAGE);
				}


		}
		if (e.getSource()==addorder.backbtn)
		{
			//if user enter pizza page, we need to back to this too
			if (menu_page_indicator==1)
			{
				pagelayout.show(pagecontent, PageTitle.PizzaMenuPage.getTitle());
				setTitle(PageTitle.PizzaMenuPage.getTitle());
			}
			//if user enter drink page, we need to back to this too
			else if (menu_page_indicator==2)
			{
				pagelayout.show(pagecontent, PageTitle.DrinkMenuPage.getTitle());
				setTitle(PageTitle.DrinkMenuPage.getTitle());
			}
			current_selected_item=null;
			//reset the addorderpage
			addorder.Reset();
		}

		if (e.getSource()==myorder.confirm_order_btn)
		{
			if (current_user_order.getOrderedItemList().size()>0)
			{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure submit the order?","Confirm Submit Order", dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION)
				{
					//record the confirm order into file for management
					RecordConfirmedOrder();
					File current_user_order_file=new File(DefaultValue.USER_ORDER_DIR+"/"+current_user.getUserID()+".txt");
					current_user_order.ClearAllOrder(current_user_order_file);
					current_user_order=new Order();
					//reset myorder page and its viewport content
					myorder.Reset();
					myorder.SetupViewportScrollPane();
					JOptionPane.showMessageDialog(null, "Sucessfully submit the order\nThank you very much.", "Submit Order Success", JOptionPane.INFORMATION_MESSAGE);

				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Currently no order!", "Clear Order Failed", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		if (e.getSource()==myorder.clear_all_order_btn)
		{
			if (current_user_order.getOrderedItemList().size()>0)
			{
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure clear the order?","Confirm Clear Order", dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION)
				{
					File current_user_order_file=new File(DefaultValue.USER_ORDER_DIR+"/"+current_user.getUserID()+".txt");
					current_user_order.ClearAllOrder(current_user_order_file);
					current_user_order=new Order();
					//reset myorder page and its viewport content
					myorder.Reset();
					myorder.SetupViewportScrollPane();
					JOptionPane.showMessageDialog(null, "Sucessfully clear the order.", "Clear Order Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Currently no order!", "Clear Order Failed", JOptionPane.INFORMATION_MESSAGE);
			}
		}



		if (e.getSource()==myorder.backbtn)
		{
			pagelayout.show(pagecontent, PageTitle.LobbyPage.getTitle());
			setTitle(PageTitle.LobbyPage.getTitle());
		}


	}

	//obsolete
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

	public void LoadUserAccountDatabase() throws Exception
	{
		//create directory
		boolean makeDirectorystatus=new File(DefaultValue.USER_ACCOUNT_DIR).mkdirs();
		if (makeDirectorystatus) System.out.println("Create Directory \""+ DefaultValue.USER_ACCOUNT_DIR+ "\" Success.");

		user_account_file=new File(DefaultValue.USER_ACCOUNT_FN);
		if (!user_account_file.exists())
		{
			user_account_file.createNewFile();
			System.out.println("File \""+DefaultValue.USER_ACCOUNT_FN+"\" is created.");
		}

		BufferedReader reader=new BufferedReader(new FileReader(user_account_file));
		String line;

		while((line=reader.readLine())!=null){
			//TotalNumberCustomer++;
			User u=new User();
			Address addr=new Address();
			char ch;
			int pos=0;
			int count=0;
			for (int i=0; i<line.length(); i++)
			{
				if (count==0&&line.charAt(i)==delimiter){
					u.setUserID(Integer.parseInt(line.substring(pos, i)));
					pos=i;
					count++;
				}
				else if (count==1&&line.charAt(i)==delimiter){
					u.setFirstName(line.substring(pos+1,i));
					pos=i;
					count++;
				}
				else if (count==2&&line.charAt(i)==delimiter){
					u.setLastName(line.substring(pos+1,i));
					pos=i;
					count++;
				}
				else if (count==3&&line.charAt(i)==delimiter){
					u.setTelephoneNo(line.substring(pos+1,i));
					pos=i;
					count++;
				}
				else if (count==4&&line.charAt(i)==delimiter){
					addr.setHouseNo(Integer.parseInt(line.substring(pos+1,i)));
					pos=i;
					count++;
				}
				else if (count==5&&line.charAt(i)==delimiter){
					addr.setStreet(line.substring(pos+1,i));
					pos=i;
					count++;
				}
				else if (count==6&&line.charAt(i)==delimiter){
					addr.setState(line.substring(pos+1,i));
					pos=i;
					count++;
				}
				else if (count==7&&line.charAt(i)==delimiter){
					u.setUsername(line.substring(pos+1,i));
					pos=i;
					count++;
				}
				else if (count==8&&line.charAt(i)==delimiter){
					u.setPassword(line.substring(pos+1,i));
					pos=i;
					count++;
				}
			}
			if(line!=null)
			{
				u.setAddress(addr);
				user_list.addElement(u);
			}
		}
		reader.close();
		System.out.println("Finished Loading User Account Database...");
	}

	public void LoadPizzaMenu() throws Exception
	{
		boolean makeDirectorystatus=new File(DefaultValue.PIZZA_MENU_DIR).mkdirs();
		if (makeDirectorystatus) System.out.println("Create Directory \""+ DefaultValue.PIZZA_MENU_DIR+ "\" Success.");
		pizza_menu_file=new File(DefaultValue.PIZZA_MENU_FN);

		if (!pizza_menu_file.exists())
		{
			pizza_menu_file.createNewFile();
			System.out.println("File \""+DefaultValue.PIZZA_MENU_FN+"\" is created.");

			//initialize 10 item for new menu
			BufferedWriter writer=new BufferedWriter(new FileWriter(pizza_menu_file));
			String d=Character.toString(delimiter);
			writer.write("P1"+d+"Chicken Supreme Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P2"+d+"Blazing Seafood Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P3"+d+"Hawaiian Supreme Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P4"+d+"Island Supreme Pizza"+d+"12.30"+d);
			writer.newLine();
			writer.write("P5"+d+"Super Supreme Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P6"+d+"Aloha Supreme Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P7"+d+"Beef Pepperoni Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P8"+d+"Veggie Lover Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P9"+d+"Triple Seafood Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P10"+d+"Island Tuna Pizza"+d+"20.00"+d);
			writer.newLine();
			writer.write("P11"+d+"None"+d+"0.00"+d);
			writer.newLine();
			writer.close();

			System.out.println("Finished writing 10 new items into pizza menu...");
		}

		BufferedReader reader=new BufferedReader(new FileReader(pizza_menu_file));
		String line;
		while ((line=reader.readLine())!=null)
		{
			Pizza temp=new Pizza();
			int pos=0;
			int count=0;
			for (int i=0; i<line.length();i++)
			{
				if (count==0&&line.charAt(i)==delimiter)
				{
					temp.setItemID(line.substring(pos, i));
					pos=i;
					count++;
				}
				else if (count==1&&line.charAt(i)==delimiter)
				{
					temp.setItemName(line.substring(pos+1, i));
					pos=i;
					count++;
				}
				else if (count==2&&line.charAt(i)==delimiter)
				{
					temp.setPrice(Double.parseDouble(line.substring(pos+1, i)));
					pos=i;
					count++;
				}
			}
			if (line!=null)
				pizza_item_list.add(temp);
		}
		reader.close();
		System.out.println("Finished Loading Pizza Menu...");
	}

	public void LoadDrinkMenu()throws Exception
	{
		boolean makeDirectorystatus=new File(DefaultValue.DRINK_MENU_DIR).mkdirs();
		if (makeDirectorystatus) System.out.println("Create Directory \""+ DefaultValue.DRINK_MENU_DIR+ "\" Success.");
		drink_menu_file=new File(DefaultValue.DRINK_MENU_FN);

		if (!drink_menu_file.exists())
		{
			drink_menu_file.createNewFile();
			System.out.println("File \""+DefaultValue.DRINK_MENU_FN+"\" is created.");

			//initialize 10 item for new menu
			BufferedWriter writer=new BufferedWriter(new FileWriter(drink_menu_file));
			String d=Character.toString(delimiter);
			writer.write("D1"+d+"Sprite"+d+"5.00"+d);
			writer.newLine();
			writer.write("D2"+d+"Cola"+d+"5.00"+d);
			writer.newLine();
			writer.write("D3"+d+"Orange Juice"+d+"5.00"+d);
			writer.newLine();
			writer.write("D4"+d+"Green Tea"+d+"5.00"+d);
			writer.newLine();
			writer.write("D5"+d+"Cappuccino"+d+"5.00"+d);
			writer.newLine();
			writer.write("D6"+d+"Chai Latte"+d+"5.00"+d);
			writer.newLine();
			writer.write("D7"+d+"Hot Chocolate"+d+"5.00"+d);
			writer.newLine();
			writer.write("D8"+d+"100 PLUS"+d+"5.00"+d);
			writer.newLine();
			writer.write("D9"+d+"Ice Milo"+d+"5.00"+d);
			writer.newLine();
			writer.write("D10"+d+"Ice Coffee"+d+"5.00"+d);
			writer.newLine();
			writer.write("D11"+d+"None"+d+"0.00"+d);
			writer.newLine();
			writer.close();

			System.out.println("Finished writing 10 new items into drink menu...");
		}

		BufferedReader reader=new BufferedReader(new FileReader(drink_menu_file));
		String line;
		while ((line=reader.readLine())!=null)
		{
			Drink temp=new Drink();
			int pos=0;
			int count=0;
			for (int i=0; i<line.length();i++)
			{
				if (count==0&&line.charAt(i)==delimiter)
				{
					temp.setItemID(line.substring(pos, i));
					pos=i;
					count++;
				}
				else if (count==1&&line.charAt(i)==delimiter)
				{
					temp.setItemName(line.substring(pos+1, i));
					pos=i;
					count++;
				}
				else if (count==2&&line.charAt(i)==delimiter)
				{
					temp.setPrice(Double.parseDouble(line.substring(pos+1, i)));
					pos=i;
					count++;
				}
			}
			if(line!=null)
				drink_item_list.add(temp);
		}
		reader.close();
		System.out.println("Finished Loading Drink Menu...");
	}

	public void RecordConfirmedOrder()
	{
		//create directory
		boolean makeDirectorystatus=new File(DefaultValue.USER_CONFIRMED_ORDER_DIR).mkdirs();
		if (makeDirectorystatus) System.out.println("Create Directory \""+ DefaultValue.USER_CONFIRMED_ORDER_DIR+ "\" Success.");

		File user_confirmed_order_file=new File(DefaultValue.USER_CONFIRMED_ORDER_FN);
		try{
			BufferedWriter writer=new BufferedWriter(new FileWriter(user_confirmed_order_file.getAbsoluteFile(), true));
			if (!user_confirmed_order_file.exists())
			{
				user_confirmed_order_file.createNewFile();
				System.out.println("File \""+user_confirmed_order_file+"\" is created.");
				writer.write("--------------Confirmed Order--------------\n\n");
			}

			writer.write("User ID: "+Integer.toString(current_user.getUserID()));
			writer.newLine();
			writer.write("User's Name: "+current_user.getFirstName()+" "+current_user.getLastName());
			writer.newLine();
			writer.write("User's Telephone No: "+current_user.getTelephoneNo());
			writer.newLine();
			writer.write("User's Address: "+current_user.getAddress().getFullAddress());
			writer.newLine();
			writer.write("************Order*************");
			writer.newLine();
			int count=1;
			for (Item j: current_user_order.getOrderedItemList())
			{
				writer.write("Item #"+(count++)+":");
				writer.newLine();
				writer.write("Item ID: "+j.getItemID());
				writer.newLine();
				writer.write("Item Name: "+j.getItemName());
				writer.newLine();
				writer.write("Quantity: "+j.getQuantity());
				writer.newLine();
				writer.write("Topping: "+j.getToppingName());
				writer.newLine();
				writer.write(String.format("SubTotal: RM%.2f", j.getSubTotal()));
				writer.newLine();
				writer.write("-------------------------------------------------------");
				writer.newLine();
			}
			writer.write("Total item purchased: "+current_user_order.getTotalItem()/2);
			writer.newLine();
			writer.write(String.format("Overall pay: RM%.2f",current_user_order.getOverallPay()/2));
			writer.newLine();
			writer.write("=========================Order End=========================");
			writer.newLine();
			writer.newLine();
			writer.close();
			System.out.println("UserID: "+current_user.getUserID()+" has confirm his/her order. Finished recorded into confirm order file and wait for delivery...");
		}

		catch(Exception e)
		{
			System.out.println("File Error: "+e.getMessage());
		}
	}

}//end Activity class
//still a messy code....need for further improvement