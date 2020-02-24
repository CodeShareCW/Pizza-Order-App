package includes.page;
import includes.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class MyOrderPage extends Page{
	public JScrollPane ordered_item_scrollpane;
	public JLabel header_label;
	public ArrayList<JLabel> ordered_item_label_list;
	public JLabel overall_pay_label, total_item_label;
	public JPanel info_panel;
	public JButton confirm_order_btn, clear_all_order_btn, backbtn;

	public MyOrderPage(){}
	public MyOrderPage(JLabel label){super(label);}

	public void Reset()
	{
		ordered_item_label_list=new ArrayList<JLabel>();
		total_item_label.setText("Total item: 0");
		overall_pay_label.setText("Amount Pay: RM0.00");
	}
	public void SetupOrderedItemLabelList(Order ordered_item_list)
	{
		for (int i=0; i<ordered_item_list.getOrderedItemList().size(); i++)
		{
			String desc=ordered_item_list.getOrderedItemDescription(i);
			//push the label into the label list
			ordered_item_label_list.add(new JLabel(desc));
		}
	}



	//GUI part
	public void InitializeComponent()
	{
		content_panel=new JPanel();
		panel=new JPanel();
		panel_container=new JPanel();
		page_label=new JLabel("My Order", SwingConstants.CENTER);
		ordered_item_scrollpane=new JScrollPane();

		String header_str=String.format("%-6s%-30s%-10s%-20s%s", "Bil", "Item Name", "Qty", "Topping", "SubTotal");
		header_label=new JLabel(header_str);

		ordered_item_label_list=new ArrayList<JLabel>();
		overall_pay_label=new JLabel("Amount Pay: RM 0.00");
		total_item_label=new JLabel("Total item: 0");
		info_panel=new JPanel();
		confirm_order_btn=new JButton("Confirm");
		clear_all_order_btn=new JButton("Clear All");
		backbtn=new JButton("Back");
	}
	public void SetComponentLayout()
	{
		content_panel.setLayout(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		panel_container.setLayout(new GridBagLayout());
		ordered_item_scrollpane.setLayout(new ScrollPaneLayout());
		info_panel.setLayout(new GridBagLayout());
	}
	public void SetupViewportScrollPane()
	{
		/*We actually want to separate this part because it will be dynamicly
			updated over time when user add order......
		*/
		JPanel ordered_item_viewport_panel=new JPanel();

		SpringLayout ordered_item_viewport_layout=new SpringLayout();
		ordered_item_viewport_panel.setLayout(ordered_item_viewport_layout);
		ordered_item_viewport_panel.setBackground(new Color(102,178,255));

		JPanel ordered_item_viewport_content_panel=new JPanel();
		ordered_item_viewport_content_panel.setLayout(new BorderLayout());



		//declare the initial gap_height of the contentview to be 100px
		int gap_height=100;
		ordered_item_viewport_panel.add(header_label);
		for(JLabel ordered_item_label: ordered_item_label_list)
		{
			ordered_item_viewport_panel.add(ordered_item_label);

			//west edge of the item label is of 10px from the east of content view
			ordered_item_viewport_layout.putConstraint(SpringLayout.WEST, ordered_item_label, 10, SpringLayout.EAST, ordered_item_viewport_content_panel);
			//north edge of the item label is (gap_height)px from the north of content view
			ordered_item_viewport_layout.putConstraint(SpringLayout.NORTH, ordered_item_label, gap_height, SpringLayout.NORTH,	ordered_item_viewport_content_panel);

			//update the height of viewport panel
			ordered_item_viewport_panel.setPreferredSize(new Dimension(1500, gap_height));
			//increment the gap height
			gap_height+=100;
		}
		//set viewport of item panel
		ordered_item_scrollpane.setViewportView(ordered_item_viewport_panel);

		//styling the added labels and buttons
		for (JLabel label: ordered_item_label_list)
		{
			label.setFont(DefaultValue.bTahoma40);
			label.setOpaque(true);
			label.setBackground(Color.WHITE);
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
		panel_gbc.gridwidth=4;
		panel_gbc.gridx=0;
		panel_gbc.gridy=0;
		panel.add(ordered_item_scrollpane, panel_gbc);

		GridBagConstraints info_panel_gbc = new GridBagConstraints();

		info_panel_gbc.gridx=0;
		info_panel_gbc.gridy=0;
		info_panel.add(total_item_label, info_panel_gbc);

		info_panel_gbc.insets=new Insets(0,200,0,0);
		info_panel_gbc.gridx=1;
		info_panel_gbc.gridy=0;
		info_panel.add(overall_pay_label, info_panel_gbc);

		//panel_gbc.gridwidth=1;
		panel_gbc.gridx=0;
		panel_gbc.gridy=1;
		panel.add(info_panel, panel_gbc);


		//panel container add component
		panel_container.add(panel);

		//content panel add component
		GridBagConstraints content_gbc = new GridBagConstraints();
		content_gbc.gridwidth=3;
		content_gbc.ipadx=100;
		content_gbc.ipady=50;
		content_panel.add(page_label, content_gbc);

		content_gbc.insets=new Insets(20,0,0,0);
		content_gbc.gridx=0;
		content_gbc.gridy=1;
		content_gbc.ipadx=0;
		content_gbc.ipady=0;
		content_panel.add(panel_container, content_gbc);


		content_gbc.gridwidth=1;
		content_gbc.insets=new Insets(20,100,0,0);
		content_gbc.gridx=0;
		content_gbc.gridy=2;
		content_panel.add(confirm_order_btn, content_gbc);

		content_gbc.gridx=1;
		content_gbc.gridy=2;
		content_panel.add(clear_all_order_btn, content_gbc);

		content_gbc.gridx=2;
		content_gbc.gridy=2;
		content_panel.add(backbtn, content_gbc);





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

		//style item scrollpane, size should less than size of parent
		ordered_item_scrollpane.setPreferredSize(new Dimension(1200,500));
		ordered_item_scrollpane.setBackground(new Color(125,0, 255));


		//style header_label
		header_label.setFont(DefaultValue.bTahoma40);

		//style info panel
		info_panel.setPreferredSize(new Dimension(1200,100));
		info_panel.setBackground(DefaultValue.PANEL_BGCOLOR);
		//style other labels
		total_item_label.setFont(DefaultValue.bTahoma40);
		overall_pay_label.setFont(DefaultValue.bTahoma40);

		//style buttons
		confirm_order_btn.setBackground(Color.RED);
		confirm_order_btn.setForeground(Color.WHITE);
		confirm_order_btn.setFont(DefaultValue.bTahoma40);

		clear_all_order_btn.setBackground(Color.RED);
		clear_all_order_btn.setForeground(Color.WHITE);
		clear_all_order_btn.setFont(DefaultValue.bTahoma40);

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

}//end MyOrderPage class