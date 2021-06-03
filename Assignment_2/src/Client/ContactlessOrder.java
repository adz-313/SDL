package Client;

import java.util.InputMismatchException;
import java.util.Scanner;

import Admin.Menu;
import Admin.Order;
import Admin.OrderGenerator;

public class ContactlessOrder 
{
	Menu menu;
	Order order;
	Scanner sc = new Scanner(System.in);
	public ContactlessOrder(Menu ref1)
	{
		menu = ref1;
	}
	public void contactlessOrderMenu()
	{
		try
		{
			while(true)
			{
				System.out.println("");
				System.out.println("****Contactless Order****");
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
						//order = new Order(1);
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
