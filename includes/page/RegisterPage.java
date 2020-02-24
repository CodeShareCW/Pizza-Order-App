package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPage extends Page{
	public JLabel firstname_label, lastname_label, telephoneno_label,
				houseno_label, street_label, state_label,
				username_label, password_label, confirm_password_label;

	public JTextField firstname_textfield, lastname_textfield, telephoneno_textfield,
						houseno_textfield, street_textfield, state_textfield,
						username_textfield;

	public JPasswordField password_passwordfield, confirm_password_passwordfield;

	public JButton submitbtn, resetbtn, cancelbtn;

	public RegisterPage(){}
	public RegisterPage(JLabel label){super(label);}

	//reset the textfield
	public void Reset()
	{
		firstname_textfield.setText("");
		lastname_textfield.setText("");
		telephoneno_textfield.setText("");
		houseno_textfield.setText("");
		street_textfield.setText("");
		state_textfield.setText("");
		username_textfield.setText("");
		password_passwordfield.setText("");
		confirm_password_passwordfield.setText("");
	}

	/*	reset the background color to be white
		as we will have change to background to error background color
		to red to indicate which field is invalid*/
	public void ResetTextFieldBGColorWhite()
	{
		firstname_textfield.setBackground(Color.WHITE);
		lastname_textfield.setBackground(Color.WHITE);
		telephoneno_textfield.setBackground(Color.WHITE);
		houseno_textfield.setBackground(Color.WHITE);
		street_textfield.setBackground(Color.WHITE);
		state_textfield.setBackground(Color.WHITE);
		username_textfield.setBackground(Color.WHITE);
		password_passwordfield.setBackground(Color.WHITE);
		confirm_password_passwordfield.setBackground(Color.WHITE);
	}

	public boolean VerifyTextField(String first, String last, String tpno,
								String hno, String street, String state, String username,
								String password, String confirm_password, Vector<User> user_list) throws ErrorException
	{

		String character_numeric_space_str=DefaultValue.CHAR_NUM_WITH_SPACE_ALLOWED_PATTERN;
		String character_space_str=DefaultValue.CHAR_WITH_SPACE_ALLOWED_PATTERN;
		String character_numeric_str=DefaultValue.CHAR_NUM_PATTERN;
		String character_str=DefaultValue.NUM_PATTERN;
		String numeric_str=DefaultValue.NUM_PATTERN;

		//5 patterns of input constraint pattern
		Pattern character_numeric_space_pattern=Pattern.compile(character_numeric_space_str);
		Pattern character_space_pattern = Pattern.compile(character_space_str);
		Pattern character_numeric_pattern=Pattern.compile(character_numeric_str);
		Pattern character_pattern=Pattern.compile(character_str);
		Pattern numeric_pattern=Pattern.compile(numeric_str);

		//matcher of all fields
		Matcher firstname_matcher=character_space_pattern.matcher(first);
		Matcher lastname_matcher=character_space_pattern.matcher(last);
		Matcher telephoneno_matcher=numeric_pattern.matcher(tpno);
		Matcher houseno_matcher=numeric_pattern.matcher(hno);
		Matcher street_matcher=character_numeric_space_pattern.matcher(street);
		Matcher state_matcher=character_numeric_space_pattern.matcher(state);
		Matcher username_matcher=character_numeric_pattern.matcher(username);
		Matcher password_matcher=character_numeric_pattern.matcher(password);

		//set the background to red when specific field is found error
		//also add focus to that invalid field
		if (first.isEmpty())
		{
			firstname_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			firstname_textfield.requestFocus(true);
			throw new ErrorException("First name cannot be left empty!");
		}
		else if (last.isEmpty())
		{
			lastname_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			lastname_textfield.requestFocus(true);
			throw new ErrorException("Last name cannot be left empty!");
		}
		else if (tpno.isEmpty())
		{
			telephoneno_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			telephoneno_textfield.requestFocus(true);
			throw new ErrorException("Telephone No cannot be left empty!");
		}
		else if (hno.isEmpty())
		{
			houseno_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			houseno_textfield.requestFocus(true);
			throw new ErrorException("House No cannot be left empty!");
		}
		else if (street.isEmpty())
		{
			street_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			street_textfield.requestFocus(true);
			throw new ErrorException("Street cannot be left empty!");
		}
		else if (state.isEmpty())
		{
			state_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			state_textfield.requestFocus(true);
			throw new ErrorException("State cannot be left empty!");
		}
		else if (username.isEmpty())
		{
			username_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			username_textfield.requestFocus(true);
			throw new ErrorException("Username cannot be left empty!");
		}
		else if (password.isEmpty())
		{
			password_passwordfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			password_passwordfield.requestFocus(true);
			throw new ErrorException("Password cannot be left empty!");
		}

		else if (confirm_password.isEmpty())
		{
			confirm_password_passwordfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			confirm_password_passwordfield.requestFocus(true);
			throw new ErrorException("Confirm Password cannot be left empty!");
		}

		else if(first.charAt(0)==' ')
		{
			firstname_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			firstname_textfield.requestFocus(true);
			throw new ErrorException("First name should not be started with space!");
		}
		else if(!firstname_matcher.matches())
		{
			firstname_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			firstname_textfield.requestFocus(true);
			throw new ErrorException("First name should be only alphabet!");
		}
		else if(last.charAt(0)==' ')
		{
			lastname_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			lastname_textfield.requestFocus(true);
			throw new ErrorException("Last name should not be started with space!");
		}
		else if(!lastname_matcher.matches())
		{
			lastname_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			lastname_textfield.requestFocus(true);
			throw new ErrorException("Last name should be only alphabet!");
		}
		else if (tpno.length()!=10)
		{
			telephoneno_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			telephoneno_textfield.requestFocus(true);
			throw new ErrorException("Telephone No should be 10 digit numbers!\nE.g: 0123456789");
		}
		else if (!telephoneno_matcher.matches())
		{
			telephoneno_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			telephoneno_textfield.requestFocus(true);
			throw new ErrorException("Telephone No should be only digit numbers!\nE.g: 0123456789");
		}
		else if (!houseno_matcher.matches())
		{
			houseno_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			houseno_textfield.requestFocus(true);
			throw new ErrorException("House No should only be digit numbers!");
		}
		else if (street.charAt(0)==' ')
		{
			street_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			street_textfield.requestFocus(true);
			throw new ErrorException("Street should not be started with space!");
		}
		else if (!street_matcher.matches())
		{
			street_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			street_textfield.requestFocus(true);
			throw new ErrorException("Street should only be alphabets!");
		}
		else if (state.charAt(0)==' ')
		{
			state_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			state_textfield.requestFocus(true);
			throw new ErrorException("State should not be started with space!");
		}
		else if (!state_matcher.matches())
		{
			state_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			state_textfield.requestFocus(true);
			throw new ErrorException("Street should only be alphabets!");
		}
		else if (username.length()<6)
		{
			username_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			username_textfield.requestFocus(true);
			throw new ErrorException("Username should at least 6 digit number or alphabets!");
		}
		else if (!username_matcher.matches())
		{
			username_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			username_textfield.requestFocus(true);
			throw new ErrorException("Username should only be only digit number and alphabets!");
		}
		else if (password.length()<6)
		{
			password_passwordfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			password_passwordfield.requestFocus(true);
			throw new ErrorException("Password should at least 6 digit number or alphabets!");
		}
		else if (!password_matcher.matches())
		{
			password_passwordfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			password_passwordfield.requestFocus(true);
			throw new ErrorException("Password should only be only digit number and alphabets!");
		}
		else if (!confirm_password.equals(password))
		{
			confirm_password_passwordfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			confirm_password_passwordfield.requestFocus(true);
			throw new ErrorException("Confirm Password is not matched with password!");
		}
//check database if same username is found throw
		for (User u: user_list)
			if(username.equals(u.getUsername()))
			{
				username_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
				username_textfield.requestFocus(true);
				throw new ErrorException("Username exists! Please choose another one!");
			}
//no other error return true
		return true;
	}

	public boolean isRegisterSuccess(File UserAccount, Vector<User> user_list)
	{
		String first=firstname_textfield.getText();
		String last=lastname_textfield.getText();
		String tpno=telephoneno_textfield.getText();
		String hno=houseno_textfield.getText();
		String street=street_textfield.getText();
		String state=state_textfield.getText();
		String username=username_textfield.getText();
		String password=password_passwordfield.getText();
		String confirm_password=confirm_password_passwordfield.getText();
		try{
				BufferedWriter writer=new BufferedWriter(new FileWriter(UserAccount.getAbsoluteFile(),true));

			if (VerifyTextField(first,last,tpno,hno,street,state,username,password,confirm_password, user_list))
			{
			//capitalize name
				String firstname_first_letter=first.substring(0, 1);
				String firstname_after_first_letter=first.substring(1);
				first=firstname_first_letter.toUpperCase()+firstname_after_first_letter;

				String lastname_first_letter=last.substring(0, 1);
				String lastname_after_first_letter=last.substring(1);
				last=lastname_first_letter.toUpperCase()+lastname_after_first_letter;
			//capitalize address
				String street_first_letter=street.substring(0, 1);
				String street_after_first_letter=street.substring(1);
				street=street_first_letter.toUpperCase()+street_after_first_letter;

				String state_first_letter=state.substring(0, 1);
				String state_after_first_letter=state.substring(1);
				state=state_first_letter.toUpperCase()+state_after_first_letter;


				String delimiter=Character.toString(DefaultValue.DELIMITER_STYLE);
				//write into file
				writer.write(user_list.size()+delimiter);
				writer.write(first+delimiter);
				writer.write(last+delimiter);
				writer.write(tpno+delimiter);
				writer.write(Integer.parseInt(hno)+delimiter);
				writer.write(street+delimiter);
				writer.write(state+delimiter);
				writer.write(username+delimiter);
				writer.write(password+delimiter);
				writer.newLine();
				writer.close();


				User u=new User(user_list.size(), first, last, tpno, new Address(Integer.parseInt(hno), street, state), username, password);

				boolean makeDirectorystatus=new File(DefaultValue.USER_ORDER_DIR).mkdirs();
				if (makeDirectorystatus) System.out.println("Create Directory \"" + DefaultValue.USER_ORDER_DIR + "\" Success.");

				File file=new File(DefaultValue.USER_ORDER_DIR+"/"+u.getUserID()+".txt");
				if (!file.exists()) {
					file.createNewFile();
					System.out.println("Create Order File For User ID: " + u.getUserID() + " in \"" + DefaultValue.USER_ORDER_DIR + "\" Success.");
				}
				user_list.addElement(u);
				return true;

			}
		}
			catch(ErrorException e)
			{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Register Failed", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(IOException e)
			{
					JOptionPane.showMessageDialog(null, "File Error", "Register Failed", JOptionPane.INFORMATION_MESSAGE);
			}
			return false;
	}





	//components part, for GUI
	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Register", SwingConstants.CENTER);

		firstname_label=new JLabel("FirstName: ");
		lastname_label=new JLabel("LastName: ");
		telephoneno_label=new JLabel("Telephone: ");
		houseno_label=new JLabel("House No: ");
		street_label=new JLabel("Street: ");
		state_label=new JLabel("State: ");
		username_label=new JLabel("Username: ");
		password_label=new JLabel("Password: ");
		confirm_password_label=new JLabel("Confirm Password: ");

		firstname_textfield=new JTextField(20);
		lastname_textfield=new JTextField(20);
		telephoneno_textfield=new JTextField(30);
		houseno_textfield=new JTextField(10);
		street_textfield=new JTextField(10);
		state_textfield=new JTextField(10);
		username_textfield=new JTextField(30);
		password_passwordfield=new JPasswordField(30);
		confirm_password_passwordfield=new JPasswordField(30);

		submitbtn=new JButton("Ok");
		resetbtn=new JButton("Reset");
		cancelbtn=new JButton("Cancel");

	}
	public void SetComponentLayout()
	{
		content_panel.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		panel_container.setLayout(new GridBagLayout());
	}
	public void AddComponent()
	{
		/*IMPORTANT*/
		/*Please add the components into smaller panel
			before adding it to larger panel,
			because the there will be empty components
			if not do otherwise*/

		//set the constraint for the panel
		GridBagConstraints panel_gbc = new GridBagConstraints();

 		panel_gbc.anchor = GridBagConstraints.WEST;

		panel_gbc.insets = new Insets(0, 0, 20, 0);
		panel_gbc.ipady=20;

		panel_gbc.gridwidth=6;
		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(firstname_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=0;
		panel.add(firstname_textfield, panel_gbc);

		panel_gbc.gridx=4;
		panel_gbc.gridy=0;
		panel.add(lastname_label, panel_gbc);

		panel_gbc.gridx=5;
		panel_gbc.gridy=0;
		panel.add(lastname_textfield, panel_gbc);

//give 1st row 2 column
		panel_gbc.gridx=0;
		panel_gbc.gridy=1;
		panel.add(telephoneno_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=1;
		panel.add(telephoneno_textfield, panel_gbc);


		panel_gbc.gridwidth=1;
		panel_gbc.gridx=0;
		panel_gbc.gridy=2;
		panel.add(houseno_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=2;
		panel.add(houseno_textfield, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=2;
		panel.add(street_label, panel_gbc);

		panel_gbc.gridx=4;
		panel_gbc.gridy=2;
		panel.add(street_textfield, panel_gbc);

		panel_gbc.gridx=5;
		panel_gbc.gridy=2;
		panel.add(state_label, panel_gbc);

		panel_gbc.gridx=6;
		panel_gbc.gridy=2;
		panel.add(state_textfield, panel_gbc);

		panel_gbc.gridwidth=6;
		panel_gbc.gridx=0;
		panel_gbc.gridy=3;
		panel.add(username_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=3;
		panel.add(username_textfield, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=4;
		panel.add(password_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=4;
		panel.add(password_passwordfield, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=5;
		panel.add(confirm_password_label, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=5;
		panel.add(confirm_password_passwordfield, panel_gbc);

//button
		panel_gbc.insets = new Insets(30, 50, 0, 0);
		panel_gbc.ipadx=100;
		panel_gbc.ipady=50;
		panel_gbc.gridx=1;
		panel_gbc.gridy=6;
		panel_gbc.gridwidth=1;
		panel.add(submitbtn, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=6;
		panel.add(resetbtn, panel_gbc);

		panel_gbc.gridx=4;
		panel_gbc.gridy=6;
		panel.add(cancelbtn, panel_gbc);


		//add panel into container
		panel_container.add(panel);


		GridBagConstraints content_gbc = new GridBagConstraints();
		//set margin-bottom to 10px
		content_gbc.insets=new Insets(0, 0, 10, 0);
		content_gbc.ipadx=100;
		content_gbc.ipady=50;
		content_gbc.gridx=0;
		content_gbc.gridy=0;
		content_panel.add(page_label, content_gbc);

		content_gbc.ipadx=0;
		content_gbc.ipady=0;
		content_gbc.gridx=0;
		content_gbc.gridy=1;

		content_panel.add(panel_container, content_gbc);
	}
	public void StyleComponent()
	{
		//style content panel
		content_panel.setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);

		//style panel container
		panel_container.setPreferredSize(DefaultValue.STD_PANEL_CON_DIMENSION);
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);
		panel_container.setBorder(DefaultValue.BORDER);

		//style panel
		panel.setPreferredSize(DefaultValue.STD_PANEL_DIMENSION);
		panel.setBackground(DefaultValue.PANEL_BGCOLOR);

		//style page title label
		page_label.setFont(DefaultValue.PAGE_LABEL_STD_FONT);
		//set page label's background to be opaque(visible)
		page_label.setOpaque(true);
		page_label.setBackground(DefaultValue.PAGE_LABEL_BGCOLOR);
		//set text to be white
		page_label.setForeground(Color.WHITE);

		//style label
		Font label_font_style=DefaultValue.bCourier30;
		firstname_label.setFont(label_font_style);
		lastname_label.setFont(label_font_style);
		telephoneno_label.setFont(label_font_style);
		houseno_label.setFont(label_font_style);
		street_label.setFont(label_font_style);
		state_label.setFont(label_font_style);
		username_label.setFont(label_font_style);
		password_label.setFont(label_font_style);
		confirm_password_label.setFont(label_font_style);

		//style textfield
		Font text_font_style=DefaultValue.bCourier30;
		firstname_textfield.setFont(text_font_style);
		lastname_textfield.setFont(text_font_style);
		telephoneno_textfield.setFont(text_font_style);
		houseno_textfield.setFont(text_font_style);
		street_textfield.setFont(text_font_style);
		state_textfield.setFont(text_font_style);
		username_textfield.setFont(text_font_style);
		password_passwordfield.setFont(text_font_style);
		confirm_password_passwordfield.setFont(text_font_style);

		//style buttons
		Color btn_color=new Color(0,0,153);
		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bTahoma20;

		submitbtn.setBackground(btn_color);
		submitbtn.setForeground(btn_color_text);
		submitbtn.setFont(btn_font_style);

		resetbtn.setBackground(btn_color);
		resetbtn.setForeground(btn_color_text);
		resetbtn.setFont(btn_font_style);

		cancelbtn.setBackground(btn_color);
		cancelbtn.setForeground(btn_color_text);
		cancelbtn.setFont(btn_font_style);
	}
	public JPanel ContentPanel()
	{
		InitializeComponent();
		SetComponentLayout();
		AddComponent();
		StyleComponent();
		return content_panel;
	}
	public static void main(String[]a)
	{
		SwingUtilities.invokeLater(new Runnable(){

			public void run()
			{
				JFrame frame=new JFrame();
				frame.setTitle(PageTitle.RegisterPage.getTitle());
				frame.getContentPane().setBackground(new Color(255, 255, 255));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(new Dimension(DefaultValue.WIN_SIZE_X, DefaultValue.WIN_SIZE_Y));
				frame.setLocationRelativeTo(null);

				frame.add(new MyOrderPage().ContentPanel());
				frame.setVisible(true);

			}
		});
	}


}//end RegisterPage class