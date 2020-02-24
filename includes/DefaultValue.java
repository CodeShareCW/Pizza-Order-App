package includes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class DefaultValue{
	//get the dimension of screen resolution
	public static Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	//set the window size
	public static int WIN_SIZE_X=SCREENSIZE.width;
	public static int WIN_SIZE_Y=SCREENSIZE.height;

//border
	public static Border BORDER=BorderFactory.createLineBorder(Color.WHITE, 5);
	public static Border BUTTON_BORDER=BorderFactory.createLineBorder(Color.BLACK, 2);
//pizza house name
	public static String PIZZA_HOUSE_NAME="CLG PIZZA HOUSE";

//welcomepage image
	public static String WELCOME_IMAGEICON_NAME="img/PizzaImage.jpg";

	public static String USER_ACCOUNT_DIR="User Account";
	public static String USER_ACCOUNT_FN=USER_ACCOUNT_DIR+"/User Account.txt";
	public static String PIZZA_MENU_DIR="Pizza Menu";
	public static String PIZZA_MENU_FN=PIZZA_MENU_DIR+"/Pizza Menu.txt";
	public static String DRINK_MENU_DIR="Drink Menu";
	public static String DRINK_MENU_FN=DRINK_MENU_DIR+"/Drink Menu.txt";
	public static String USER_ORDER_DIR="User Order";
	public static String USER_CONFIRMED_ORDER_DIR="User Confirmed Order";
	public static String USER_CONFIRMED_ORDER_FN=USER_CONFIRMED_ORDER_DIR+"/User Confirmed Order.txt";

//color

	public static Color PAGE_LABEL_BGCOLOR=Color.BLACK;

	public static Color CONTENT_PANEL_BGCOLOR=new Color(162, 212, 241);

	public static Color PANEL_BGCOLOR=new Color(204,229, 255);
	public static Color PANEL_CON_BGCOLOR=new Color(0,51, 102);

	public static Color LEFT_PANEL_BGCOLOR=new Color(204,229,255);
	public static Color RIGHT_PANEL_BGCOLOR=new Color(0,76, 153);

	public static Color BUTTON_BGCOLOR_1=new Color(0,76,153);
	public static Color BUTTON_BGCOLOR_2=new Color(51,153,255);
	public static Color BACK_BUTTON_BGCOLOR=Color.RED;

	public static Color ERROR_BGCOLOR=new Color(255, 204, 204);

//standard dimension of panel
	public static Dimension STD_PANEL_DIMENSION=new Dimension(1300,650);
	public static Dimension STD_PANEL_CON_DIMENSION=new Dimension(1350,700);



//font
	public static Font bTahoma20 = new Font("Tahoma", Font.BOLD, 20);
	public static Font bTahoma30 = new Font("Tahoma", Font.BOLD, 30);
	public static Font bTahoma40 = new Font("Tahoma", Font.BOLD, 40);
	public static Font bTahoma60 = new Font("Tahoma", Font.BOLD, 60);
	public static Font bTahoma80 = new Font("Tahoma", Font.BOLD, 80);

	public static Font bCourier20= new Font("Courier", Font.BOLD, 20);
	public static Font bCourier30= new Font("Courier", Font.BOLD, 30);
	public static Font bCourier40= new Font("Courier", Font.BOLD, 40);
	public static Font bCourier60= new Font("Courier", Font.BOLD, 60);
	public static Font bCourier80= new Font("Courier", Font.BOLD, 80);

//standard font of page label
	public static Font PAGE_LABEL_STD_FONT=bCourier60;

//setdelimiter
	public static char DELIMITER_STYLE=',';

//pattern
	public static String CHAR_NUM_WITH_SPACE_ALLOWED_PATTERN="[a-zA-Z0-9 ]*";
	public static String CHAR_NUM_PATTERN="[a-zA-Z0-9]*";
	public static String CHAR_WITH_SPACE_ALLOWED_PATTERN="[a-zA-Z ]*";
	public static String CHAR_PATTERN="[a-zA-Z]*";
	public static String NUM_PATTERN="[0-9]*";



}//end DefaultValue class