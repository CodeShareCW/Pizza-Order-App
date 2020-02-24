package includes.page;
import includes.*;

public enum PageTitle{
	WelcomePage("Welcome Page"),
	LoginPage("Login Page"),
	RegisterPage("Register Page"),
	LobbyPage("Lobby Page"),
	ProfilePage("Profile Page"),
	EditProfilePage("Edit Profile Page"),
	MenuPage("Menu Page"),
	PizzaMenuPage("Pizza Page"),
	DrinkMenuPage("Drink Page"),
	AddOrderPage("Add Order Page"),
	MyOrderPage("My Order Page");

	String title;
	PageTitle(){}
	PageTitle(String t){title=t;}
	public String getTitle(){return title;}

}//end PageTitle class