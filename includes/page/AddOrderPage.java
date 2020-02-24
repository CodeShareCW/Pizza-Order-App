package includes.page;
import includes.*;


import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AddOrderPage extends Page{
	public JLabel item_description_label, quantity_label, addon_label, sub_total_label;
	public JTextField quantity_textfield;
	public JRadioButton yesbtn, nobtn;
	public JButton incrementbtn, decrementbtn, addbtn, backbtn;

	public AddOrderPage(){}
	public AddOrderPage(JLabel label){super(label);}

	public void Reset()
	{
		quantity_textfield.setText("0");
		yesbtn.setSelected(false);
		nobtn.setSelected(true);
		sub_total_label.setText("SubTotal:        RM 0.00");
	}


//GUI part
	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Add Order", SwingConstants.CENTER);

		item_description_label=new JLabel("P2   Spicy Chicken  ddddddddddddddddd                RM12.00");
		quantity_label=new JLabel("Quantity:");
		addon_label=new JLabel("Add Cheese:");
		sub_total_label=new JLabel("SubTotal:        RM 0.00");

		quantity_textfield=new JTextField("0");
		quantity_textfield.setEditable(false);
		quantity_textfield.setHorizontalAlignment(JTextField.CENTER);


		yesbtn=new JRadioButton("Yes", false);
		nobtn=new JRadioButton("No", true);
		//we want to allow one selection, so we put the two buttons into a buttongroup
		ButtonGroup btngroup=new ButtonGroup();
		btngroup.add(yesbtn);
		btngroup.add(nobtn);


		incrementbtn=new JButton("+");
		decrementbtn=new JButton("-");
		addbtn=new JButton("Add");
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

		//add panel into container
		GridBagConstraints panel_gbc = new GridBagConstraints();
		panel_gbc.gridwidth=7;
		panel_gbc.anchor=GridBagConstraints.WEST;

		panel_gbc.insets=new Insets(0,0,100,30);
		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(item_description_label, panel_gbc);

		panel_gbc.insets=new Insets(0,0,30,30);
		panel_gbc.gridwidth=1;
		panel_gbc.gridx=0;
		panel_gbc.gridy=1;
		panel.add(quantity_label, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=1;
		panel.add(quantity_textfield, panel_gbc);

		panel_gbc.gridx=3;
		panel_gbc.gridy=1;
		panel.add(incrementbtn, panel_gbc);

		panel_gbc.gridx=4;
		panel_gbc.gridy=1;
		panel.add(decrementbtn, panel_gbc);

		panel_gbc.gridx=0;
		panel_gbc.gridy=2;
		panel.add(addon_label, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=2;
		panel.add(yesbtn, panel_gbc);

		panel_gbc.gridx=3;
		panel_gbc.gridy=2;
		panel.add(nobtn, panel_gbc);


		panel_gbc.insets=new Insets(50,0,0,0);
		panel_gbc.gridwidth=6;
		panel_gbc.gridx=0;
		panel_gbc.gridy=4;
		panel.add(sub_total_label, panel_gbc);

		panel_gbc.anchor=GridBagConstraints.CENTER;

		panel_gbc.gridx=0;
		panel_gbc.gridy=5;
		panel_gbc.ipady=50;
		panel_gbc.ipady=20;
		panel.add(addbtn, panel_gbc);

		panel_gbc.gridx=2;
		panel_gbc.gridy=5;
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

		//style page title label
		page_label.setFont(DefaultValue.PAGE_LABEL_STD_FONT);
		//set page label's background to be opaque(visible)
		page_label.setOpaque(true);
		page_label.setBackground(DefaultValue.PAGE_LABEL_BGCOLOR);
		//set text to be white
		page_label.setForeground(Color.WHITE);

		item_description_label.setFont(DefaultValue.bTahoma40);
		item_description_label.setOpaque(true);
		item_description_label.setBackground(new Color(51, 253, 151));

		Font font=DefaultValue.bTahoma40;
		quantity_label.setFont(font);
		quantity_textfield.setFont(font);
		quantity_textfield.setPreferredSize(new Dimension(70,70));

		incrementbtn.setFont(font);
		incrementbtn.setForeground(Color.WHITE);
		incrementbtn.setBackground(Color.GREEN);
		incrementbtn.setPreferredSize(new Dimension(70,70));
		incrementbtn.setBorder(DefaultValue.BUTTON_BORDER);

		decrementbtn.setFont(font);
		decrementbtn.setForeground(Color.WHITE);
		decrementbtn.setBackground(Color.GREEN);
		decrementbtn.setPreferredSize(new Dimension(70,70));
		decrementbtn.setBorder(DefaultValue.BUTTON_BORDER);

		addon_label.setFont(font);

		yesbtn.setFont(font);
		yesbtn.setBackground(DefaultValue.PANEL_BGCOLOR);

		nobtn.setFont(font);
		nobtn.setBackground(DefaultValue.PANEL_BGCOLOR);

		sub_total_label.setFont(font);

		addbtn.setFont(font);
		addbtn.setForeground(Color.WHITE);
		addbtn.setBackground(new Color(255,128,0));
		addbtn.setPreferredSize(new Dimension(150,70));

		backbtn.setFont(font);
		backbtn.setForeground(Color.WHITE);
		backbtn.setBackground(DefaultValue.BACK_BUTTON_BGCOLOR);
		backbtn.setPreferredSize(new Dimension(150,70));

	}
	public JPanel ContentPanel()
	{
		InitializeComponent();
		SetComponentLayout();
		AddComponent();
		StyleComponent();
		return content_panel;
	}

}//end AddOrderPage class