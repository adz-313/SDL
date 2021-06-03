package Admin;
import java.util.*;

public class KitchenHandler 
{
	Queue<Order> orderQueue = new LinkedList<Order>();
    Scanner sc = new Scanner(System.in);
    public void addNewOrder(Order obj)
    {
    	orderQueue.add(obj);
    }
    void displayOrderQueue()
    {
        System.out.println("");
        System.out.println("****Order Queue****");
        for(Order obj : orderQueue)
        {
            System.out.println("Name: " + obj.customerName);
            System.out.println("Table No: " + obj.tableNo);
            for (Map.Entry m : obj.orderDetails.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
        }
    }
    public void kitchenHandlerMenu()
    {
    	while(true)
    	{
    		System.out.println("");
	    	System.out.println("****Kitchen Handler****");
	    	System.out.println("1. Display order queue");
	    	System.out.println("2. Request service");
	    	System.out.println("3. Back");
	    	System.out.print("Enter choice: ");
	    	int choice = sc.nextInt();
	    	switch(choice)
	    	{
	    		case 1:
	    			displayOrderQueue();
	    			break;
	    		case 2:
	    			orderQueue.remove();
	    			break;
	    		case 3:
	    			return;
	    		default:
	    			System.out.println("Invalid choice.");
	    	}
    	}
    }
}
