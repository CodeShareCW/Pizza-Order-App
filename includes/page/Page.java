package includes.page;
import includes.*;

import javax.swing.*;

public abstract class Page{
	public JLabel page_label;
	public JPanel panel, panel_container;
	public JPanel content_panel;

	public Page(){}
	public Page(JLabel label){page_label=label;}
	public abstract void InitializeComponent();
	public abstract void AddComponent();
	public abstract void SetComponentLayout();
	public abstract void StyleComponent();
	public abstract JPanel ContentPanel();
}//end class Page