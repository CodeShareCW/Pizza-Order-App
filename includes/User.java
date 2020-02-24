package includes;
public class User{
	private int userID;
	private String firstname, lastname, telephoneno;
	private String username, password;
	private Address address;
	public User(){}
	public User(int id, String fn, String ln, String tpno, Address addr, String un, String pwd)
	{
		userID=id;
		firstname=fn;
		lastname=ln;
		telephoneno=tpno;
		address=addr;
		username=un;
		password=pwd;
	}
	public void setUserID(int id){userID=id;}
	public void setFirstName(String fn){firstname=fn;}
	public void setLastName(String ln){lastname=ln;}
	public void setTelephoneNo(String tpno){telephoneno=tpno;}
	public void setAddress(Address addr){address=addr;}
	public void setUsername(String un){username=un;}
	public void setPassword(String pwd){password=pwd;}

	public int getUserID(){return userID;}
	public String getFirstName(){return firstname;}
	public String getLastName(){return lastname;}
	public String getTelephoneNo(){return telephoneno;}
	public Address getAddress(){return address;}
	public String getUsername(){return username;}
	public String getPassword(){return password;}

	public String getFullName(){return firstname+" "+lastname;}


}//end User class

