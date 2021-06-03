import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import Admin.AdminServer;
import Admin.CustomerDetails;
import Admin.CustomerHandler;
import Admin.EmployeeManagement;
import Admin.InventoryManagement;
import Admin.OrderGenerator;
import Admin.KitchenHandler;
import Admin.Menu;
import Admin.MenuHandler;
import Client.CustomerAuthentication;
import Client.CustomerServer;
import Client.HomeDelivery;

public class MyClass extends JFrame
{
	InventoryManagement invMgmt = new InventoryManagement();
	EmployeeManagement empMgmt = new EmployeeManagement();
	Menu menuGen = new Menu();
	CustomerHandler custHandler = new CustomerHandler();
	KitchenHandler kh = new KitchenHandler();
	CustomerAuthentication custAuth = new CustomerAuthentication(custHandler);
	MenuHandler menuHandler = new MenuHandler(menuGen);
	OrderGenerator orderGen = new OrderGenerator(kh,menuHandler);
		
	void adminMenu() 
	{
		AdminServer adminServer = new AdminServer(menuGen, orderGen, custHandler);
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
	            System.out.println("5. Kitchen Handler");
	            System.out.println("6. Chat");
	            System.out.println("7. Exit");
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
	                    kh.kitchenHandlerMenu();
	                    break;
	                case 6:
	                    adminServer.sendMessage();
	                    break;
	                case 7:	   
	                	menuHandler.saveMenu();
	                	adminServer.t.interrupt();
	                    return;
	                default:
	                    System.out.println("Invalid input.");
	            }
	        }
	        catch(InputMismatchException e)
	        {
	            System.out.println("Error: Wrong input type. Please try again.");
	        } 
	        catch (SQLException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        /*catch (InterruptedException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/ 
	    }
	}
	
	int clientMenu()
	{
		Scanner sc = new Scanner(System.in);
		CustomerDetails custDetails = custAuth.customerAuthMenu();
		if(custDetails == null)
		{
			System.out.println("Invalid details. Please try again.");
			return -1;
		}
		CustomerServer custServer = new CustomerServer(custDetails);
		//clientFrame();
		try
		{
			 while(true)
			 {
				 System.out.println("");
				 System.out.println("****Welcome " + custDetails.custName + "****");
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
				 		custServer.sendMessage(custDetails.custName);
				 		break;
				 	case 3:
				 		custServer.t.interrupt();
				 		return 0;
				 }
			 }
		}
		catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
        }
		return -1;
	}
	
	public static void main(String[] args)
	{
		MyClass obj = new MyClass();
		//obj.mainFrame();
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
                    int status = obj.clientMenu();
                    if(status == -1)
                    {
                    	return;
                    }
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
