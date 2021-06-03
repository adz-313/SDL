package Client;
import Admin.CustomerDetails;

public class CustomerCredentials implements Comparable<CustomerCredentials>
{
	String custUsername;
	String custPassword;
	CustomerDetails custDetails;
	void customerDetailHandler(String name,int phoneNo,String address)
	{
		custDetails = new CustomerDetails(name, phoneNo, address);
	}
	public int compareTo(CustomerCredentials obj)
	{
		return this.custUsername.compareTo(obj.custUsername);
	}
	public CustomerDetails getCustomerDetails()
	{
		return this.custDetails;
	}
}
