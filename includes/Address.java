package includes;

public class Address{
	private int houseno;
	private String street, state;

	public Address(){}
	public Address(int hno, String street, String state)
	{
		houseno=hno;
		this.street=street;
		this.state=state;
	}
	public void setHouseNo(int hno){houseno=hno;}
	public void setStreet(String street){this.street=street;}
	public void setState(String state){this.state=state;}

	public int getHouseNo(){return houseno;}
	public String getStreet(){return street;}
	public String getState(){return state;}

	public String getFullAddress(){return houseno+", "+street+", "+state;}


}//end Address class