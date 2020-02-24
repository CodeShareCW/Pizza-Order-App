package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
public class MenuPage extends Page{
	public JButton pizzabtn, drinkbtn, backbtn;

	public MenuPage(){}
	public MenuPage(JLabel label){super(label);}
//GUI part
	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Menu", SwingConstants.CENTER);
		pizzabtn=new JButton("Pizza");
		drinkbtn=new JButton("Drink");
		backbtn=new JButton("Back");
	}
	public void SetComponentLayout()
	{
		content_panel.setLayout(new GridBagLayout());
		panel_container.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
	}
	public void AddComponent()
	{
		/*IMPORTANT*/
		/*Please add the components into smaller panel
			before adding it to larger panel,
			because the there will be empty components
			if not do otherwise*/

		GridBagConstraints panel_gbc = new GridBagConstraints();
	//	panel_gbc.gridwidth = GridBagConstraints.REMAINDER;
	//	panel_gbc.fill = GridBagConstraints.HORIZONTAL;

		//set panel's cell margin(top, right, bottom, left)
		panel_gbc.insets=new Insets(0,0,50,0);

		panel_gbc.ipadx=150;
		panel_gbc.ipady=100;
		panel_gbc.gridwidth=3;

		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(pizzabtn, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=1;
		panel.add(drinkbtn, panel_gbc);

		panel_gbc.ipadx=10;
		panel_gbc.ipady=10;

		panel_gbc.ipadx=50;
		panel_gbc.ipady=20;
		panel_gbc.gridx=2;
		panel_gbc.gridy=2;
		panel.add(backbtn, panel_gbc);

		//add panel into container
		panel_container.add(panel);

		//add panel container into content panel
		GridBagConstraints content_gbc = new GridBagConstraints();

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

	//style whole content panel
		content_panel.setBackground(DefaultValue.CONTENT_PANEL_BGCOLOR);

	//style the inner second larger panel
		panel_container.setPreferredSize(DefaultValue.STD_PANEL_CON_DIMENSION);
		panel_container.setBackground(DefaultValue.PANEL_CON_BGCOLOR);
		panel_container.setBorder(DefaultValue.BORDER);
	//style the inner third larger panel, should not larger than its parent
		panel.setPreferredSize(DefaultValue.STD_PANEL_DIMENSION);
		panel.setBackground(DefaultValue.PANEL_BGCOLOR);

	//style page label
		page_label.setFont(DefaultValue.PAGE_LABEL_STD_FONT);
		page_label.setOpaque(true);
		page_label.setBackground(Color.BLACK);
		page_label.setForeground(Color.WHITE);

	//style buttons
		Color btn_color=new Color(0,102,204);
		Color btn_color_text=Color.WHITE;
		Font btn_font_style=DefaultValue.bTahoma40;

		pizzabtn.setBackground(btn_color);
		pizzabtn.setForeground(btn_color_text);
		pizzabtn.setFont(btn_font_style);

		drinkbtn.setBackground(btn_color);
		drinkbtn.setForeground(btn_color_text);
		drinkbtn.setFont(btn_font_style);

		backbtn.setBackground(Color.RED);
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

				frame.add(new MenuPage().ContentPanel());
				frame.setVisible(true);

			}
		});
	}


}//end MenuPage class