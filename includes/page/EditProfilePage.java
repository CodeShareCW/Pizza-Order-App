package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditProfilePage extends Page{
	public JLabel firstname_label, lastname_label, telephoneno_label,
				houseno_label, street_label, state_label;

	public JTextField firstname_textfield, lastname_textfield, telephoneno_textfield,
						houseno_textfield, street_textfield, state_textfield;

	public JButton submitbtn, resetbtn, backbtn;


	public EditProfilePage(){}
	public EditProfilePage(JLabel label){super(label);}
	public void Reset()
	{
		firstname_textfield.setText("");
		lastname_textfield.setText("");
		telephoneno_textfield.setText("");
		houseno_textfield.setText("");
		street_textfield.setText("");
		state_textfield.setText("");
	}
	public void PresetCurrentUserProfile(String first, String last, String tpno, String hno, String street, String state)
	{
		firstname_textfield.setText(first);
		lastname_textfield.setText(last);
		telephoneno_textfield.setText(tpno);
		houseno_textfield.setText(hno);
		street_textfield.setText(street);
		state_textfield.setText(state);
	}
	public void ResetTextFieldBGColorWhite()
	{
		firstname_textfield.setBackground(Color.WHITE);
		lastname_textfield.setBackground(Color.WHITE);
		telephoneno_textfield.setBackground(Color.WHITE);
		houseno_textfield.setBackground(Color.WHITE);
		street_textfield.setBackground(Color.WHITE);
		state_textfield.setBackground(Color.WHITE);
	}

	//same code with register's verify function
	public boolean VerifyTextField(String first, String last, String tpno,
								String hno, String street, String state) throws ErrorException
	{

		String character_numeric_space_str=DefaultValue.CHAR_NUM_WITH_SPACE_ALLOWED_PATTERN;
		String character_space_str=DefaultValue.CHAR_WITH_SPACE_ALLOWED_PATTERN;
		String character_numeric_str=DefaultValue.CHAR_NUM_PATTERN;
		String character_str=DefaultValue.NUM_PATTERN;
		String numeric_str=DefaultValue.NUM_PATTERN;

		Pattern character_numeric_space_pattern=Pattern.compile(character_numeric_space_str);
		Pattern character_space_pattern = Pattern.compile(character_space_str);
		Pattern character_numeric_pattern=Pattern.compile(character_numeric_str);
		Pattern character_pattern=Pattern.compile(character_str);
		Pattern numeric_pattern=Pattern.compile(numeric_str);

		Matcher firstname_matcher=character_space_pattern.matcher(first);
		Matcher lastname_matcher=character_space_pattern.matcher(last);
		Matcher telephoneno_matcher=numeric_pattern.matcher(tpno);
		Matcher houseno_matcher=numeric_pattern.matcher(hno);
		Matcher street_matcher=character_numeric_space_pattern.matcher(street);
		Matcher state_matcher=character_numeric_space_pattern.matcher(state);

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

		return true;
	}
	public boolean isEditCurrentUserProfileSuccess(File UserAccount, String delimiter, Vector<User> user_list, User current_user)
	{
		String first=firstname_textfield.getText();
		String last=lastname_textfield.getText();
		String tpno=telephoneno_textfield.getText();
		String hno=houseno_textfield.getText();
		String street=street_textfield.getText();
		String state=state_textfield.getText();

		//we need to check if user do not edit their profile
		//but they press edit submit button
		//return true at that situation to reduce workload
		if (current_user.getFirstName().equals(first)
			&&current_user.getLastName().equals(last)
			&&current_user.getTelephoneNo().equals(tpno)
			&&Integer.toString(current_user.getAddress().getHouseNo()).equals(hno)
			&&current_user.getAddress().getStreet().equals(street)
			&&current_user.getAddress().getState().equals(state)
			) return true;

		//if some changes made, do the following, first we will check the textfield
		try{
			if(VerifyTextField(first, last, tpno, hno, street, state))
			{
			//capitalize firstname
				String firstname_first_letter=first.substring(0, 1);
				String firstname_after_first_letter=first.substring(1);
				first=firstname_first_letter.toUpperCase()+firstname_after_first_letter;

			//capitalize lastname
				String lastname_first_letter=last.substring(0, 1);
				String lastname_after_first_letter=last.substring(1);
				last=lastname_first_letter.toUpperCase()+lastname_after_first_letter;

			//capitalize address street
				String street_first_letter=street.substring(0, 1);
				String street_after_first_letter=street.substring(1);
				street=street_first_letter.toUpperCase()+street_after_first_letter;

			//capitalize address state
				String state_first_letter=state.substring(0, 1);
				String state_after_first_letter=state.substring(1);
				state=state_first_letter.toUpperCase()+state_after_first_letter;

			//now, we will update the current login user info
				current_user.setFirstName(first);
				current_user.setLastName(last);
				current_user.setTelephoneNo(tpno);
				current_user.setAddress(new Address(Integer.parseInt(hno), street, state));

					for (User u: user_list)
						/*we will check the database of user account
						  and find the id in user list that is matched
						  with current user's id*/
						if (current_user.getUserID()==u.getUserID())
						{
							//if found, we insert this current user into the database
							user_list.insertElementAt(current_user, user_list.indexOf(u));
							//afterthat, remove the outdated one out of the list
							user_list.remove(u);
							break;
						}
				//we then move to user account database(file)
				//rewrite the edited version into the database
				try{
					//rewrite the user account database
					BufferedWriter writer=new BufferedWriter(new FileWriter(UserAccount));
					for (User u: user_list)
					{
						writer.write(Integer.toString(u.getUserID())+delimiter);
						writer.write(u.getFirstName()+delimiter);
						writer.write(u.getLastName()+delimiter);
						writer.write(u.getTelephoneNo()+delimiter);
						writer.write(Integer.toString(u.getAddress().getHouseNo())+delimiter);
						writer.write(u.getAddress().getStreet()+delimiter);
						writer.write(u.getAddress().getState()+delimiter);
						writer.write(u.getUsername()+delimiter);
						writer.write(u.getPassword()+delimiter);
						writer.newLine();
					}
					writer.close();
				}
				catch(Exception e)
				{
					System.out.println("File Error: "+ e.getMessage());
				}
				return true;
			}
		}
		catch(ErrorException e)
		{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Edit Profile Failed", JOptionPane.INFORMATION_MESSAGE);
		}
		return false;
	}



	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Edit Profile", SwingConstants.CENTER);

		firstname_label=new JLabel("First Name: ");
		lastname_label=new JLabel("Last Name: ");
		telephoneno_label=new JLabel("Telephone No: ");
		houseno_label=new JLabel("House No: ");
		street_label=new JLabel("Street: ");
		state_label=new JLabel("State: ");

		firstname_textfield=new JTextField(20);
		lastname_textfield=new JTextField(20);
		telephoneno_textfield=new JTextField(30);
		houseno_textfield=new JTextField(10);
		street_textfield=new JTextField(10);
		state_textfield=new JTextField(10);

		submitbtn=new JButton("Edit");
		resetbtn=new JButton("Reset");
		backbtn=new JButton("Back");
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

		GridBagConstraints panel_gbc = new GridBagConstraints();

 		panel_gbc.anchor = GridBagConstraints.WEST;

		panel_gbc.insets = new Insets(80, 30, 0, 30);
		panel_gbc.ipady=20;

		panel_gbc.gridwidth=5;
		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(firstname_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=0;
		panel.add(firstname_textfield, panel_gbc);

		panel_gbc.gridx=3;
		panel_gbc.gridy=0;
		panel.add(lastname_label, panel_gbc);

		panel_gbc.gridx=4;
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

		panel_gbc.gridx=3;
		panel_gbc.gridy=2;
		panel.add(street_textfield, panel_gbc);

		panel_gbc.gridx=4;
		panel_gbc.gridy=2;
		panel.add(state_label, panel_gbc);

		panel_gbc.gridx=5;
		panel_gbc.gridy=2;
		panel.add(state_textfield, panel_gbc);

		panel_gbc.ipadx=50;
		panel_gbc.ipady=20;
		panel_gbc.gridx=1;
		panel_gbc.gridy=3;
		panel_gbc.gridwidth=1;
		panel.add(submitbtn, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=3;
		panel_gbc.gridwidth=1;
		panel.add(resetbtn, panel_gbc);

		panel_gbc.gridx=3;
		panel_gbc.gridy=3;
		panel.add(backbtn, panel_gbc);

		panel_container.add(panel);
		GridBagConstraints content_panel_gbc = new GridBagConstraints();
		content_panel_gbc.ipadx=100;
		content_panel_gbc.ipady=50;
		content_panel.add(page_label, content_panel_gbc);
		content_panel_gbc.insets=new Insets(20,0,0,0);
		content_panel_gbc.gridx=0;
		content_panel_gbc.gridy=1;
		content_panel_gbc.ipadx=0;
		content_panel_gbc.ipady=0;
		content_panel.add(panel_container, content_panel_gbc);
	}
	public void StyleComponent()
	{
		//style the content panel
		content_panel.setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);

		//style the panel container
		panel_container.setPreferredSize(new Dimension(1550,750));
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);
		panel_container.setBorder(DefaultValue.BORDER);

		//style the panel
		panel.setPreferredSize(new Dimension(1500,700));
		panel.setBackground(DefaultValue.PANEL_BGCOLOR);

		//style the page label
		page_label.setFont(DefaultValue.PAGE_LABEL_STD_FONT);
		page_label.setOpaque(true);
		page_label.setBackground(DefaultValue.PAGE_LABEL_BGCOLOR);
		page_label.setForeground(Color.WHITE);

		//style the labels
		Font label_font_style=DefaultValue.bCourier30;
		firstname_label.setFont(label_font_style);
		lastname_label.setFont(label_font_style);
		telephoneno_label.setFont(label_font_style);
		houseno_label.setFont(label_font_style);
		street_label.setFont(label_font_style);
		state_label.setFont(label_font_style);

		//style the textfields
		Font text_font_style=DefaultValue.bCourier30;
		firstname_textfield.setFont(text_font_style);
		lastname_textfield.setFont(text_font_style);
		telephoneno_textfield.setFont(text_font_style);
		houseno_textfield.setFont(text_font_style);
		street_textfield.setFont(text_font_style);
		state_textfield.setFont(text_font_style);

		//style the buttons
		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bTahoma40;

		submitbtn.setBackground(DefaultValue.BUTTON_BGCOLOR_1);
		submitbtn.setForeground(btn_color_text);
		submitbtn.setFont(btn_font_style);

		resetbtn.setBackground(DefaultValue.BUTTON_BGCOLOR_1);
		resetbtn.setForeground(btn_color_text);
		resetbtn.setFont(btn_font_style);

		backbtn.setBackground(DefaultValue.BACK_BUTTON_BGCOLOR);
		backbtn.setForeground(btn_color_text);
		backbtn.setFont(btn_font_style);

	}
	public JPanel ContentPanel()
	{
		InitializeComponent();
		SetComponentLayout();
		AddComponent();
		StyleComponent();
		return content_panel;
	}
}