package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
public class LobbyPage extends Page{
	public JPanel left_panel, right_panel;
	public JLabel greeting_label;
	public JButton profilebtn, menubtn, myorderbtn, logoutbtn;

	public LobbyPage(){}
	public LobbyPage(JLabel label){super(label);}

	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		left_panel=new JPanel();
		right_panel=new JPanel();
		greeting_label=new JLabel("Good Day, Stranger");
		profilebtn=new JButton("Profile");
		menubtn=new JButton("Menu");
		myorderbtn=new JButton("My Order");
		logoutbtn=new JButton("Log Out");
	}

	public void SetComponentLayout()
	{
		content_panel.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		panel_container.setLayout(new GridBagLayout());
		left_panel.setLayout(new GridBagLayout());
		right_panel.setLayout(new GridBagLayout());
	}
	public void AddComponent()
	{
		GridBagConstraints left_panel_gbc = new GridBagConstraints();
		left_panel_gbc.gridwidth = GridBagConstraints.REMAINDER;
		left_panel_gbc.fill=GridBagConstraints.HORIZONTAL;
		//Inset--margin(top,left, bottom, right)
		left_panel_gbc.insets=new Insets(0,0,200,0);
		left_panel.add(greeting_label, left_panel_gbc);
		left_panel_gbc.ipadx=100;
		left_panel_gbc.ipady=50;
		left_panel_gbc.insets=new Insets(0,0,100,00);

		left_panel.add(menubtn, left_panel_gbc);
		left_panel.add(myorderbtn, left_panel_gbc);

		GridBagConstraints right_panel_gbc = new GridBagConstraints();
		right_panel_gbc.gridwidth = GridBagConstraints.REMAINDER;

		right_panel_gbc.ipadx=200;
		right_panel_gbc.ipady=50;


		right_panel.add(profilebtn,right_panel_gbc );
		right_panel_gbc.insets=new Insets(400,0,0,0);
		right_panel.add(logoutbtn, right_panel_gbc);

		panel.add(left_panel);
		panel.add(right_panel);

		panel_container.add(panel);
		content_panel.add(panel_container);
	}
	public void StyleComponent()
	{
		panel.setPreferredSize(new Dimension(1400,800));
		panel_container.setPreferredSize(new Dimension(1450,850));
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);

		left_panel.setPreferredSize(new Dimension(1000,800));
		left_panel.setBackground(DefaultValue.LEFT_PANEL_BGCOLOR);

		right_panel.setPreferredSize(new Dimension(400,800));
		right_panel.setBackground(DefaultValue.RIGHT_PANEL_BGCOLOR);

		Font label_font=DefaultValue.bCourier30;
		greeting_label.setFont(label_font);

		Color btn_color_1=DefaultValue.BUTTON_BGCOLOR_2;
		Color btn_color_2=DefaultValue.BUTTON_BGCOLOR_1;

		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bCourier30;

		profilebtn.setBackground(btn_color_1);
		profilebtn.setForeground(btn_color_text);
		profilebtn.setFont(btn_font_style);

		logoutbtn.setBackground(btn_color_1);
		logoutbtn.setForeground(btn_color_text);
		logoutbtn.setFont(btn_font_style);

		menubtn.setBackground(btn_color_2);
		menubtn.setForeground(btn_color_text);
		menubtn.setFont(btn_font_style);


		myorderbtn.setBackground(btn_color_2);
		myorderbtn.setForeground(btn_color_text);
		myorderbtn.setFont(btn_font_style);




		panel_container.setBorder(DefaultValue.BORDER);
		content_panel.setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);
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

				frame.add(new LobbyPage().ContentPanel());
				frame.setVisible(true);

			}
		});
	}
}//end LobbyPage class