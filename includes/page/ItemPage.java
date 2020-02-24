package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

public class ItemPage extends Page{
	public JScrollPane item_scrollpane;
	public JLabel header_label;
	public ArrayList<JLabel> item_label_list;
	public ArrayList<JButton> order_button_list;
	public JButton backbtn;

	public ItemPage(){}
	public ItemPage(JLabel label){super(label);}

	public void SetupItemLabelList(ArrayList<Item> item_list)
	{
		for (Item i: item_list)
		{
			//push the label into the label list
			item_label_list.add(new JLabel(i.getItemDescription()));
		}
	}


//GUI part
	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("Item", SwingConstants.CENTER);

		item_scrollpane=new JScrollPane();
		String header_str=String.format("%-4s%-30s%s", "ID", "Item Name", "Price");
		header_label=new JLabel(header_str);
		item_label_list=new ArrayList<JLabel>();
		order_button_list=new ArrayList<JButton>();
		backbtn=new JButton("Back");
	}
	public void SetComponentLayout()
	{
		content_panel.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		panel_container.setLayout(new GridBagLayout());
		item_scrollpane.setLayout(new ScrollPaneLayout());
	}
	public void SetupViewportScrollPane()
	{
		//item panel add component


		//style item viewport panel
		/*to use scroll pane, we have create a viewport panel, where it
			should be larger than the size of item_scrollpane(its parent)
			so that we can have thing to scroll,
			*/
		JPanel item_viewport_panel=new JPanel();

		SpringLayout item_viewport_layout=new SpringLayout();
		item_viewport_panel.setLayout(item_viewport_layout);
		item_viewport_panel.setBackground(new Color(102,178,255));

		/*after setting up the viewport, we then add items into the viewport
			so we need a panel for the content to be seen in this viewport.
			 */
		JPanel item_viewport_content_panel=new JPanel();
		item_viewport_content_panel.setLayout(new BorderLayout());

		//declare the initial gap_height of the contentview to be 100px
		int gap_height=100;
		item_viewport_panel.add(header_label);
		for(JLabel item_label: item_label_list)
		{
			//declare a button and arrange together with each label
			JButton btn=new JButton("Order");
			item_viewport_panel.add(item_label);
			item_viewport_panel.add(btn);

			//west edge of the item label is of 10px from the east of content view
			item_viewport_layout.putConstraint(SpringLayout.WEST, item_label, 10, SpringLayout.EAST, item_viewport_content_panel);
			//north edge of the item label is (gap_height)px from the north of content view
			item_viewport_layout.putConstraint(SpringLayout.NORTH, item_label, gap_height, SpringLayout.NORTH,	item_viewport_content_panel);
			//west edge of the button is of 10 px from the east of item label label
			item_viewport_layout.putConstraint(SpringLayout.WEST, btn, 10, SpringLayout.EAST, item_label);
			//north edge of the button is of (gap_height)px from the north of contentview
			item_viewport_layout.putConstraint(SpringLayout.NORTH, btn, gap_height, SpringLayout.NORTH, item_viewport_content_panel);

			order_button_list.add(btn);
			//update the height of viewport panel
			item_viewport_panel.setPreferredSize(new Dimension(1000, gap_height));
			//increment the gap height
			gap_height+=100;
		}
		//set viewport of item panel
		item_scrollpane.setViewportView(item_viewport_panel);

		//style all the item label list
		if (item_label_list!=null)
		{
			for(JLabel item_label: item_label_list)
			{
				item_label.setFont(DefaultValue.bCourier40);
				item_label.setOpaque(true);
				item_label.setBackground(Color.WHITE);
				item_label.setForeground(Color.BLACK);
			}
		}

		//style all the order buttons
		if (order_button_list!=null)
		for (JButton b: order_button_list)
		{
			b.setBackground(Color.GREEN);
			b.setForeground(Color.WHITE);
			b.setFont(DefaultValue.bTahoma40);
		}

	}
	public void AddComponent()
	{
		/*IMPORTANT*/
		/*Please add the components into smaller panel
			before adding it to larger panel,
			because the there will be empty components
			if not do otherwise*/


		//panel add component
		GridBagConstraints panel_gbc = new GridBagConstraints();

		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(item_scrollpane, panel_gbc);


		//panel container add component
		panel_container.add(panel);

		//content panel add component
		GridBagConstraints content_gbc = new GridBagConstraints();
		content_gbc.ipadx=100;
		content_gbc.ipady=50;
		content_panel.add(page_label, content_gbc);

		content_gbc.insets=new Insets(20,0,0,0);
		content_gbc.gridx=0;
		content_gbc.gridy=1;
		content_gbc.ipadx=0;
		content_gbc.ipady=0;
		content_panel.add(panel_container, content_gbc);

		content_gbc.insets=new Insets(30,1000,0,0);
		content_gbc.gridx=0;
		content_gbc.gridy=2;
		content_panel.add(backbtn,  content_gbc);

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

		//style page label
		page_label.setFont(DefaultValue.PAGE_LABEL_STD_FONT);
		page_label.setOpaque(true);
		page_label.setBackground(Color.BLACK);
		page_label.setForeground(Color.WHITE);


		//style item scrollpane, size should less than size of parent
		item_scrollpane.setPreferredSize(DefaultValue.STD_PANEL_DIMENSION);
		item_scrollpane.setBackground(new Color(125,0, 255));

		header_label.setFont(DefaultValue.bCourier40);


		//style back button
		backbtn.setBackground(Color.RED);
		backbtn.setForeground(Color.WHITE);
		backbtn.setFont(DefaultValue.bTahoma40);






	}
	public JPanel ContentPanel()
	{
		InitializeComponent();
		SetComponentLayout();
		AddComponent();
		StyleComponent();
		return content_panel;
	}

}//end ItemPage class
