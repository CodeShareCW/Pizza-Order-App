package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
public class ProfilePage extends Page{
	public JLabel name_label;
	public JLabel telephoneno_label;
	public JLabel address_label;
	public JButton editbtn, backbtn;

	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Profile", SwingConstants.CENTER);
		name_label=new JLabel("Strangerdfsf");
		telephoneno_label=new JLabel("tpnsdffffffffffo");
		address_label=new JLabel("addrsdd");
		editbtn=new JButton("Edit Profile");
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
		GridBagConstraints panel_gbc = new GridBagConstraints();
		panel_gbc.gridwidth=GridBagConstraints.REMAINDER;
		panel_gbc.anchor = GridBagConstraints.WEST;

		panel_gbc.insets=new Insets(0,0,20,0);
		panel_gbc.ipadx=20;
		panel_gbc.ipady=80;

		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(name_label, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=1;
		panel.add(telephoneno_label, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=2;
		panel.add(address_label, panel_gbc);

		panel_gbc.anchor = GridBagConstraints.CENTER;
		panel_gbc.ipadx=50;
		panel_gbc.ipady=20;


		panel_gbc.gridx=0;
		panel_gbc.gridy=3;
		panel.add(editbtn, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=4;
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
		panel.setPreferredSize(new Dimension(1300,700));
		panel.setBackground(DefaultValue.PANEL_BGCOLOR);

		panel_container.setPreferredSize(new Dimension(1350,750));
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);
		panel_container.setBorder(DefaultValue.BORDER);

		Font page_label_font=DefaultValue.bCourier80;
		page_label.setFont(page_label_font);
		page_label.setOpaque(true);
		page_label.setBackground(DefaultValue.PAGE_LABEL_BGCOLOR);
		page_label.setForeground(Color.WHITE);


		Font label_font=DefaultValue.bCourier40;
		name_label.setFont(label_font);
		telephoneno_label.setFont(label_font);
		address_label.setFont(label_font);

		Color btn_color=DefaultValue.BACK_BUTTON_BGCOLOR;
		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bTahoma40;

		editbtn.setBackground(DefaultValue.BUTTON_BGCOLOR_1);
		editbtn.setForeground(btn_color_text);
		editbtn.setFont(btn_font_style);

		backbtn.setBackground(btn_color);
		backbtn.setForeground(btn_color_text);
		backbtn.setFont(btn_font_style);



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

				frame.add(new ProfilePage().ContentPanel());
				frame.setVisible(true);

			}
		});
	}



}//end ProfilePage class