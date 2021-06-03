package Client;

import java.util.InputMismatchException;
import java.util.Scanner;
import Admin.CustomerDetails;
import Admin.CustomerHandler;

public class CustomerAuthentication 
{
	
	CustomerDetails custCred;	
	CustomerHandler ref;
	Scanner sc = new Scanner(System.in);
	
	public CustomerAuthentication(CustomerHandler ch)
	{
		ref = ch;
	}	
	
	CustomerDetails customerRegistration()
	{
		CustomerDetails cc = new CustomerDetails();
		System.out.println("");
		System.out.println("****Customer Registration****");
		System.out.print("Name: ");
		cc.custName = sc.nextLine();
		System.out.print("Phone No: ");
		cc.custPhoneNo = sc.nextInt();
		sc.nextLine();
		System.out.print("Address: ");
		cc.custAddress = sc.nextLine();
		System.out.print("Username: ");
		cc.custUsername = sc.nextLine();
		System.out.print("Password: ");
		cc.custPassword = sc.nextLine();
		System.out.println("Registration successful.");
		CustomerServer custServer = new CustomerServer();
		custServer.sendCredentials(cc,4);
		return cc;
	}
	
	CustomerDetails loginPage()
	{
		CustomerDetails cc = new CustomerDetails();
		System.out.println("");
		System.out.println("****Customer Login****");
		System.out.print("Username: ");
		cc.custUsername = sc.nextLine();
		System.out.print("Password: ");
		cc.custPassword = sc.nextLine();
		CustomerServer custServer = new CustomerServer();
		custServer.sendCredentials(cc,5);
		CustomerDetails custDetails = custServer.authListener();
		return custDetails;
	}
	
	public CustomerDetails customerAuthMenu()
	{
		CustomerDetails custCred = null;
		try
        {
            System.out.println("");
            System.out.println("****Hello User****");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                	custCred = customerRegistration();
                    break;
                case 2:
                	custCred = loginPage();
                	break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
        }
		return custCred;
	}
}
