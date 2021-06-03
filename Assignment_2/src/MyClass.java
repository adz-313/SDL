import java.util.*;
import java.net.*;
import java.io.*;

import Admin.AdminServer;
import Admin.CustomerDetails;
import Admin.CustomerHandler;
import Admin.EmployeeManagement;
import Admin.InventoryManagement;
import Admin.OrderGenerator;
import Admin.KitchenHandler;
import Admin.Menu;
import Admin.MenuHandler;
import Client.ContactlessOrder;
import Client.CustomerAuthentication;
import Client.CustomerCredentials;
import Client.CustomerServer;
import Client.HomeDelivery;

public class MyClass 
{
	InventoryManagement invMgmt = new InventoryManagement();
	EmployeeManagement empMgmt = new EmployeeManagement();
	Menu menuGen = new Menu();
	CustomerHandler custHandler = new CustomerHandler();
	KitchenHandler kh = new KitchenHandler();
	OrderGenerator orderGen = new OrderGenerator(kh);
	CustomerAuthentication custAuth = new CustomerAuthentication(custHandler);
	ContactlessOrder contactlessOrder = new ContactlessOrder(menuGen);
	MenuHandler menuHandler = new MenuHandler(menuGen);
	
	void adminMenu() 
	{
		AdminServer adminServer = new AdminServer(menuGen, orderGen);
		//AdminChatServer ch = new AdminChatServer();
		while(true)
	    {
	        try
	        {
	            System.out.println("");
	            System.out.println("****Restaurant Management****");
	            System.out.println("1. Inventory Management");
	            System.out.println("2. Employee Management");
	            System.out.println("3. Menu Generator");
	            System.out.println("4. Order Generator");
	            System.out.println("5. Customer Management");
	            System.out.println("6. Kitchen Handler");
	            System.out.println("7. Chat");
	            System.out.println("8. Exit");
	            System.out.print("Enter choice: ");
	            Scanner sc = new Scanner(System.in);
	            int choice = sc.nextInt();
	            switch(choice)
	            {
	                case 1:
	                    invMgmt.inventoryManagementMenu();
	                    break;
	                case 2:
	                    empMgmt.employeeManagementMenu();
	                    break;
	                case 3:
	                	menuHandler.menuGenerator();
	                    break;
	                case 4:                        
	                    orderGen.orderGeneratorMenu();
	                    break;
	                case 5:
	                	custHandler.customerHandlerMenu();
	                	break;
	                case 6:
	                    kh.kitchenHandlerMenu();
	                    break;
	                case 7:
	                    adminServer.sendMessage();
	                    break;
	                case 8:
	                	//Thread t = adminServer.getThread();
	                	//t.interrupt();
	                    return;
	                default:
	                    System.out.println("Invalid input.");
	            }
	        }
	        catch(InputMismatchException e)
	        {
	            System.out.println("Error: Wrong input type. Please try again.");
	        }
	    }
	}
	void clientMenu()
	{
		Scanner sc = new Scanner(System.in);
		CustomerDetails custDetails = custAuth.customerAuthMenu();
		CustomerServer custServer = new CustomerServer();
		try
		{
			 while(true)
			 {
				 System.out.println("");
				 System.out.println("****Welcome " + custDetails.getCustomerName() + "****");
				 System.out.println("1. Home Delivery");
				 System.out.println("2. Chat Server");
				 System.out.println("3. Back");
				 int choice = sc.nextInt();
				 switch(choice)
				 {
				 	case 1:
				 		HomeDelivery homeDelivery = new HomeDelivery();
				 		homeDelivery.homeDeliveryMenu();
				 		break;
				 	case 2:
				 		custServer.startThread();
				 		custServer.sendMessage(custDetails.getCustomerName());
				 		break;
				 	case 3:
				 		//custServer = null;
				 		//System.gc();
				 		return;
				 }
			 }
		}
		catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
        }
	}
	public static void main(String[] args)
	{
		MyClass obj = new MyClass();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("");
			System.out.println("****Client or Admin****");
			System.out.println("1. Client");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    obj.clientMenu();
                    break;
                case 2:
                    obj.adminMenu();
                    break;
                case 3:
                    return;
            }
		}
	}
}
