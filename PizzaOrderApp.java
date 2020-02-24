import includes.*;			catch(Exception e)
			{
				e.printStackTrace();
			}
import javax.swing.*;


public class PizzaOrderApp{
	public PizzaOrderApp(){}
	public void Run(){ new Activity().setVisible(true);}
	public static void main(String[]a)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				/*all you need to do is just run, go to register and make you order!*/
				/*be sure you add class path for your class at the right place!*/
				new PizzaOrderApp().Run();
			}
		});
	}


}//end PizzaOrderApp classw