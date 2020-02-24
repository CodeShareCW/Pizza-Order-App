package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class LoginPage extends Page{
	public JLabel username_label, password_label;
	public JTextField username_textfield;
	public JPasswordField password_passwordfield;
	public JButton submitbtn, cancelbtn;

	public LoginPage(){}
	public LoginPage(JLabel label){super(label);}

	public void Reset()
	{
		username_textfield.setText("");
		password_passwordfield.setText("");
	}
	public void ResetTextFieldBGColorWhite()
	{
		username_textfield.setBackground(Color.WHITE);
		password_passwordfield.setBackground(Color.WHITE);
	}
	public void VerifyTextField(String username, String password)throws Exception
	{
		if(username_textfield.getText().isEmpty())
		{
			username_textfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			username_textfield.requestFocus(true);
			throw new Exception("Please enter username!");
		}
		else if (password_passwordfield.getText().isEmpty())
		{
			password_passwordfield.setBackground(DefaultValue.ERROR_BGCOLOR);
			password_passwordfield.requestFocus(true);
			throw new Exception("Please enter password!");
		}
	}

	public boolean isLoginSuccess(Vector<User> user)
	{
			try{
				VerifyTextField(username_textfield.getText(), password_passwordfield.getText());
				for (User u: user)
				if (username_textfield.getText().equals(u.getUsername()))
				{
					if (password_passwordfield.getText().equals(u.getPassword()))
						return true;

					else throw new Exception("Wrong Password!");
				}

				throw new Exception("Username not exist!");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Login Failed", JOptionPane.INFORMATION_MESSAGE);
			}
			return false;
	}

	public User getCurrentUser(Vector<User> user, String username)
	{
		if (user!=null)
			for (User u: user)
				if (u.getUsername().equals(username))
					return u;

		return new User();
	}


//GUI part
	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Login", SwingConstants.CENTER);

		username_label=new JLabel("Username: ");
		password_label=new JLabel("Password: ");
		username_textfield=new JTextField(30);
		password_passwordfield=new JPasswordField(30);
		submitbtn=new JButton("Login");
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

		GridBagConstraints panel_gbc = new GridBagConstraints();

		//gap between button
		panel_gbc.insets = new Insets(20, 10, 10, 10);
		//padding y of button
		panel_gbc.ipady=30;
		//padding x of button
		panel_gbc.ipadx=50;

		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(username_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=0;
		//panel_gbc.weightx=2;
		panel_gbc.ipadx=400;
		panel.add(username_textfield, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=1;
		panel_gbc.ipadx=50;
		panel.add(password_label, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=1;
		panel_gbc.ipadx=400;
		panel.add(password_passwordfield, panel_gbc);

		panel_gbc.ipadx=50;
		panel_gbc.gridx=0;
		panel_gbc.gridy=2;
		panel.add(submitbtn, panel_gbc);

		panel_gbc.gridx=1;
		panel_gbc.gridy=2;

		panel.add(cancelbtn, panel_gbc);

	//add panel into container
		panel_container.add(panel);

		GridBagConstraints content_gbc = new GridBagConstraints();

		content_gbc.insets=new Insets(20, 10, 10, 10);
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
		//style the content panel
		content_panel.setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);

		//style the panel container
		panel_container.setPreferredSize(new Dimension(800,500));
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);
		panel_container.setBorder(DefaultValue.BORDER);

		//style panel
		panel.setPreferredSize(new Dimension(750,450));
		panel.setBackground(DefaultValue.PANEL_BGCOLOR);

		//style page label
		page_label.setFont(DefaultValue.PAGE_LABEL_STD_FONT);
		page_label.setOpaque(true);
		page_label.setBackground(DefaultValue.PAGE_LABEL_BGCOLOR);
		page_label.setForeground(Color.WHITE);

		//style labels
		Font label_font_style=DefaultValue.bCourier30;
		username_label.setFont(label_font_style);
		password_label.setFont(label_font_style);

		//style textfield
		Font text_font_style=DefaultValue.bCourier30;
		username_textfield.setFont(text_font_style);
		password_passwordfield.setFont(text_font_style);

		//style buttons
		Color btn_color=new Color(0,0,153);
		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bTahoma20;

		submitbtn.setBackground(btn_color);
		submitbtn.setForeground(btn_color_text);
		submitbtn.setFont(btn_font_style);

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




}//end LoginPage class