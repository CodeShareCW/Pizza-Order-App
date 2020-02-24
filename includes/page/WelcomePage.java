package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;

public class WelcomePage extends Page{
	public ImageIcon image_icon;
	public JLabel image_label;
	public JButton loginbtn;
	public JButton registerbtn;
	public JButton exitbtn;

	public WelcomePage(){}
	public WelcomePage(JLabel label){super(label);}

	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel(DefaultValue.PIZZA_HOUSE_NAME, SwingConstants.CENTER);
		image_icon=new ImageIcon();
		image_icon=new ImageIcon(new ImageIcon(DefaultValue.WELCOME_IMAGEICON_NAME).getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT));
		image_label=new JLabel();
		image_label.setIcon(image_icon);
		loginbtn=new JButton("Login");
		registerbtn=new JButton("Register");
		exitbtn=new JButton("Exit");
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

		//layout is almost all gridbaglayout, we can set constraints to
		//position our components in a grid manner
		panel.add(loginbtn);
		GridBagConstraints panel_gbc = new GridBagConstraints();
		panel_gbc.gridwidth = GridBagConstraints.REMAINDER;
		panel_gbc.fill = GridBagConstraints.HORIZONTAL;


		panel_gbc.insets=new Insets(10,10,10,10);
		panel_gbc.ipadx=40;
		panel_gbc.ipady=40;
		panel.add(loginbtn, panel_gbc);
		panel.add(registerbtn, panel_gbc);
		panel.add(exitbtn, panel_gbc);

		panel_container.add(panel);

		GridBagConstraints content_gbc = new GridBagConstraints();

		content_gbc.gridheight = 2;
		content_gbc.gridx=0;
		content_gbc.gridy=0;
		content_panel.add(image_label, content_gbc);

		content_gbc.insets=new Insets(10,100,0,0);
		content_gbc.gridheight = 1;
		content_gbc.gridx=1;
		content_gbc.gridy=0;
		content_panel.add(page_label, content_gbc);

		content_gbc.gridx=1;
		content_gbc.gridy=1;
		content_panel.add(panel_container, content_gbc);

	}
	public void StyleComponent()
	{
		//style content panel
		content_panel.setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);

		//style panel
		panel.setPreferredSize(new Dimension(450,550));
		panel.setBackground(DefaultValue.PANEL_BGCOLOR);

		//style panel container
		panel_container.setPreferredSize(new Dimension(500,600));
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);
		panel_container.setBorder(DefaultValue.BORDER);

		//style page title label, but this is the shop name of welcome page
		page_label.setOpaque(true);
		page_label.setBackground(new Color(204,0,0));
		page_label.setFont(DefaultValue.bCourier30);
		page_label.setForeground(Color.WHITE);
		page_label.setPreferredSize(new Dimension(400,80));

		//style buttons
		Color btn_color=DefaultValue.BUTTON_BGCOLOR_1;
		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bTahoma20;

		loginbtn.setBackground(btn_color);
		loginbtn.setForeground(btn_color_text);
		loginbtn.setFont(btn_font_style);

		registerbtn.setBackground(btn_color);
		registerbtn.setForeground(btn_color_text);
		registerbtn.setFont(btn_font_style);

		exitbtn.setBackground(btn_color);
		exitbtn.setForeground(btn_color_text);
		exitbtn.setFont(btn_font_style);
	}
	public JPanel ContentPanel()
	{
		InitializeComponent();
		SetComponentLayout();
		AddComponent();
		StyleComponent();
		return content_panel;
	}

}//end WelcomePage class