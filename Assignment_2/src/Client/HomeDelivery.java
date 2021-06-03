package Client;

import java.util.InputMismatchException;
import java.util.Scanner;

import Admin.CustomerDetails;
import Admin.Menu;
import Admin.Order;
import Admin.OrderGenerator;

public class HomeDelivery 
{
	CustomerDetails custInfo = new CustomerDetails();
	Order order;
	CustomerServer custServer = new CustomerServer();
	Menu menu = custServer.getMenu();
	OrderGenerator orderGen = new OrderGenerator();
	Scanner sc = new Scanner(System.in);
	/*public HomeDelivery(MenuGenerator ref1, OrderGenerator ref2, CustomerDetails ref3)
	{
		menu = ref1;
		order = ref2;
		custInfo = ref3;
	}*/
	public Order getOrder()
	{
		return order;
	}
	public void homeDeliveryMenu()
	{
		try
		{
			while(true)
			{
				System.out.println("");
				System.out.println("****Home Delivery****");
				System.out.println("1. View menu");
				System.out.println("2. Create order");
				System.out.println("3. Back");
				System.out.println("Enter choice: ");
				int choice = sc.nextInt();
				switch(choice)
				{
					case 1: 
						menu.display();
						break;
					case 2:
						order = orderGen.createOrder(0);
						custServer.sendOrder(order);
						break;
					case 3:
						return;
					default:
						System.out.println("Invalid choice");
				}
			}
		}
		catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
            sc.nextLine();
        }
	}
}
