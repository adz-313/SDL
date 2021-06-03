package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Client.CustomerCredentials;

public class CustomerHandler 
{
	private TreeSet<CustomerDetails> customerList = new TreeSet<CustomerDetails>();
	Scanner sc = new Scanner(System.in);
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	public CustomerHandler() 
	{
		// TODO Auto-generated constructor stub
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/restaurentusers", "root", "root");
		    st = con.createStatement();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void addCustomer(CustomerDetails custDetails) throws SQLException
	{
		String query = "insert into customer values('" + custDetails.custName + "'," + custDetails.custPhoneNo + ",'" + custDetails.custAddress + "','" + custDetails.getUsername() + "','" + custDetails.getPassword() + "');";
		st.executeUpdate(query);
	}
	
	/* newCustomer()
	{
		System.out.print("Name: ");
		String custName = sc.nextLine();
		System.out.print("Phone No: ");
		int custPhoneNo = sc.nextInt();
		sc.nextLine();
		System.out.print("Address: ");
		String custAddress = sc.nextLine();
		String query = "insert into customer values('" + custName + "'," + custPhoneNo + ",'" + custAddress + "','null','null');";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	void newCustomer(CustomerDetails obj)
	{
		customerList.add(obj);
	}
	
	/*void updateCustomer()
	{
		System.out.print("Name: ");
		String name = sc.nextLine();
		CustomerDetails temp = new CustomerDetails();
		for(CustomerDetails obj : customerList)
		{
			if(obj.custName.equals(name))
			{
				temp = obj;
				//customerList.remove(obj);
			}
		}
		System.out.print("1. Name");
		System.out.print("2. Phone No");
		System.out.print("3. Address");
		System.out.print("Enter choice: ");
		int choice = sc.nextInt();
		sc.nextLine();
		switch(choice)
		{
			case 1:
				System.out.print("Enter new name: ");
				temp.custName = sc.nextLine();
				break;
			case 2:
				System.out.print("Enter new phone number: ");
				temp.custPhoneNo = sc.nextInt();
				break;
			case 3:
				System.out.print("Enter new address: ");
				temp.custAddress = sc.nextLine();
				break;
			default:
				System.out.println("Invalid choice.");
		}
		customerList.add(temp);
	}*/
	
	/*void deleteCustomer()
	{
		System.out.print("Name: ");
		String name = sc.nextLine();
		for(CustomerDetails obj : customerList)
		{
			if(obj.custName.equals(name))
			{
				customerList.remove(obj);
			}
		}
	}*/
	
	/*void displayCustomers()
	{
		for(CustomerDetails obj : customerList)
		{
			System.out.println(obj.custName + " " + obj.custPhoneNo + " " + obj.custAddress);
		}
	}*/
	
	CustomerDetails authenticateCustomer(CustomerDetails cd)
	{
		/*for(CustomerDetails obj : customerList)
		{
			if(obj.custUsername.equals(cd.custUsername) && obj.custPassword.equals(cd.custPassword))
			{
				return obj;
			}
		}
		return null;*/
		CustomerDetails obj = new CustomerDetails();
		String query = "select * from customer where username = '" + cd.custUsername + "';";
		try {
			rs = st.executeQuery(query);
			while(rs.next())
			{
				obj.custName = rs.getString(1);
				obj.custPhoneNo = rs.getInt(2);
				obj.custAddress = rs.getString(3);
				obj.custUsername = rs.getString(4);
				obj.custPassword = rs.getString(5);
			}
			if(obj.custPassword.equals(cd.custPassword))
			{
				return obj;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*public void customerHandlerMenu()
	{
		while(true)
        {
            try
            {
                System.out.println("");
                System.out.println("****Customer Management****");
                System.out.println("1. New customer");
                System.out.println("2. Display customers");
                System.out.println("3. Update customer");
                System.out.println("4. Delete customer");
                System.out.println("5. Back");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        newCustomer();
                        break;
                    case 2:
                        displayCustomers();
                        break;
                    case 3:
                        updateCustomer();
                        break;
                    case 4:
                        deleteCustomer();
                        break;
                    case 5:
                    	return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Error: Wrong input type. Please try again.");
                sc.nextLine();
            }
        }
	}*/
}
