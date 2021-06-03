package Admin;
public class CustomerDetails implements Comparable<CustomerDetails> 
{
	String custName;
	long custPhoneNo;
	String custAddress;
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
	public String getCustomerName()
	{
		return this.custName;
	}
}
