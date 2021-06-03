package Client;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

import Admin.CustomerDetails;
import Admin.CustomerHandler;

public class CustomerAuthentication 
{
	TreeSet<CustomerCredentials> customerList = new TreeSet<CustomerCredentials>();
	CustomerHandler ref;
	CustomerCredentials currentUser;
	Scanner sc = new Scanner(System.in);
	public CustomerAuthentication(CustomerHandler ch)
	{
		ref = ch;
	}
	boolean customerRegistration()
	{
		CustomerCredentials cc = new CustomerCredentials();
		System.out.println("");
		System.out.println("****Customer Registration****");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Phone No: ");
		int phoneNo = sc.nextInt();
		sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();
		cc.customerDetailHandler(name,phoneNo,address);
		//System.out.print("Username: ");
		//cc.custUsername = sc.nextLine();
		//System.out.print("Password: ");
		//cc.custPassword = sc.nextLine();
		if(customerList.add(cc))
		{
			System.out.println("Registration successful.");
			currentUser = cc;
			return true;
		}
		else
		{
			System.out.println("Username taken. Please try again.");
			return false;
		}
	}
	/*boolean loginPage()
	{
		CustomerCredentials cc = new CustomerCredentials();
		System.out.println("");
		System.out.println("****Customer Login****");
		System.out.print("Username: ");
		cc.custUsername = sc.nextLine();
		System.out.print("Password: ");
		cc.custPassword = sc.nextLine();	
		for(CustomerCredentials obj : customerList)
		{
			if(obj.custUsername.equals(cc.custUsername) && obj.custPassword.equals(cc.custPassword))
			{
				currentUser = cc;
				return true;
			}
		}
		System.out.print("Invalid details. Please try again.");
		return false;
	}*/
	
	CustomerDetails getDetails()
	{
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Phone No: ");
		int phoneNo = sc.nextInt();
		sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();
		CustomerDetails obj = new CustomerDetails(name, phoneNo, address);
		return obj;
	}
	
	public CustomerDetails customerAuthMenu()
	{
		CustomerDetails obj = null;
		try
        {
            System.out.println("");
            System.out.println("****Hello User****");
            System.out.println("1. Enter details");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                	obj = getDetails();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
        }
		return obj;
	}
}
