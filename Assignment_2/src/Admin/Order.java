package Admin;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class Order implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String customerName;
	int tableNo;
	HashMap<String,Integer> orderDetails = new HashMap<String,Integer>();
	/*transient Scanner sc = new Scanner(System.in);
	public Order(int type)
	{
		System.out.print("Enter customer name: ");
        customerName = sc.nextLine();
        if(type == 1)
        {
        	System.out.print("Enter table number: ");
        	tableNo = sc.nextInt();
            sc.nextLine();
        }
        else
        {
        	tableNo = -1;
        }
        orderDetails = new HashMap<String,Integer>();
        while(true)
        {
            System.out.print("Item name: ");
            String itemName = sc.nextLine();
            if(itemName.equals("stop"))
            {
                break;
            }
            System.out.print("Item quantity: ");
            int itemQty = sc.nextInt();
            sc.nextLine();
            orderDetails.put(itemName,itemQty);
        }
	}*/
}
