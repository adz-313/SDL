package Admin;

import java.io.Serializable;

public class CustomerDetails implements Comparable<CustomerDetails>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String custName;
	public long custPhoneNo;
	public String custAddress;
	public String custUsername;
	public String custPassword;
	public int portNo;
	public CustomerDetails(){}
	public CustomerDetails(String name, int phoneNo, String address)
	{
		custName = name;
		custPhoneNo = phoneNo;
		custAddress = address; 
	}
	public int compareTo(CustomerDetails cd)
	{
		return this.custName.compareTo(cd.custName);
	}
	public String getUsername()
	{
		return this.custUsername;
	}
	public String getPassword()
	{
		return this.custPassword;
	}
}
