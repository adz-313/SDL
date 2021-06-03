package Admin;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class CustomerHandler 
{
	private TreeSet<CustomerDetails> customerList = new TreeSet<CustomerDetails>();
	Scanner sc = new Scanner(System.in);
	void newCustomer()
	{
		System.out.print("Name: ");
		String custName = sc.nextLine();
		System.out.print("Phone No: ");
		int custPhoneNo = sc.nextInt();
		sc.nextLine();
		System.out.print("Address: ");
		String custAddress = sc.nextLine();
		customerList.add(new CustomerDetails(custName, custPhoneNo, custAddress));
	}
	void newCustomer(CustomerDetails obj)
	{
		customerList.add(obj);
	}
	void updateCustomer()
	{
		System.out.print("Name: ");
		String name = sc.nextLine();
		CustomerDetails temp = new CustomerDetails();
		for(CustomerDetails obj : customerList)
		{
			if(obj.custName.equals(name))
			{
				temp = obj;
				customerList.remove(obj);
			}
		}
		System.out.print("1. Name");
		System.out.print("2. Phone No");
		System.out.print("3. Address");
		System.out.print("Enter choice: ");
		int choice = sc.nextInt();
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
	}
	void deleteCustomer()
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
	}
	void displayCustomers()
	{
		for(CustomerDetails obj : customerList)
		{
			System.out.println(obj.custName + " " + obj.custPhoneNo + " " + obj.custAddress);
		}
	}
	/*public void customerServer() 
	{
        try 
        {
            ServerSocket ss = new ServerSocket(6666);
            while (true) 
            {
                Socket s = ss.accept();//establishes connection
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
                CustomerDetails obj = (CustomerDetails) in.readObject();
                s.close();
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }*/
	public void customerHandlerMenu()
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
	}
}
