package Admin;
import java.util.*;

import Kitchen.KitchenView;

public class OrderGenerator 
{
	Scanner sc = new Scanner(System.in);
    LinkedList<Order> customerList = new LinkedList<Order>();
    KitchenHandler kh;
    MenuHandler menuHandler;
    public OrderGenerator(){}
    public OrderGenerator(MenuHandler ref)
    {
    	menuHandler = ref;
    }
    public OrderGenerator(KitchenHandler obj,MenuHandler ref)
    {
    	kh = obj;
    	menuHandler = ref;
    }
    public Order createOrder(int type)
    {
    	Order obj = new Order();
    	int sum = 0;
    	System.out.print("Enter customer name: ");
        obj.customerName = sc.nextLine();
        if(type == 1)
        {
        	System.out.print("Enter table number: ");
        	obj.tableNo = sc.nextInt();
            sc.nextLine();
        }
        else
        {
        	obj.tableNo = -1;
        }
        obj.orderDetails = new HashMap<String,Integer>();
        while(true)
        {
            System.out.print("Item name: ");
            String itemName = sc.nextLine();
            if(itemName.equals("stop"))
            {
                break;
            }
            int price = menuHandler.searchItem(itemName);
            if(price != -1)
            {
            	System.out.print("Item quantity: ");
                int itemQty = sc.nextInt();
                sc.nextLine();
                obj.orderDetails.put(itemName,itemQty);
                sum += (price * itemQty);
            }
            else
            {
            	System.out.println("Error 404: Item not found.");
            }
            obj.total = sum;
        }
        return obj;
    }
    void placeOrder()
    {
    	Order order = createOrder(1);
    	customerList.add(order);
        kh.addNewOrder(order);
        System.out.println("Order placed successfully.");
    }
    public void placeOrder(Order order)
    {
    	customerList.add(order);
        kh.addNewOrder(order);
        System.out.println("Order placed successfully.");
    }
    void deleteOrder()
    {
        System.out.println("Enter table no: ");
        int tblNo = sc.nextInt();
        int i;
        for(i=0; i<customerList.size(); i++)
        {
            if(tblNo == customerList.get(i).tableNo)
            {
                customerList.remove(i);
            }
        }
        if(i == customerList.size())
        {
            System.out.println("Error 404: Customer not found.");
        }
    }
    void displayCustomerQueue()
    {
        if(customerList.size() == 0)
        {
            System.out.println("No records present.");
            return;
        }
        for(Order obj : customerList)
        {
            System.out.println("Name: " + obj.customerName);
            System.out.println("Table No: " + obj.tableNo);
            for(Map.Entry m : obj.orderDetails.entrySet()){
                System.out.println(m.getKey()+" "+m.getValue());
            }
            System.out.println("Total: " + obj.total);
        }
    }
    public void orderGeneratorMenu()
    {
        while(true)
        {
            try
            {
                System.out.println("");
                System.out.println("****Order Generator****");
                System.out.println("1. New order");
                System.out.println("2. Display order");
                System.out.println("3. Delete order");
                System.out.println("4. Back");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        placeOrder();
                        break;
                    case 2:
                        displayCustomerQueue();
                        break;
                    case 3:
                        deleteOrder();
                        break;
                    case 4:
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
    }
}
