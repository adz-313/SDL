package Admin;
import java.util.*;

public class Inventory 
{
	ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
	String inventoryName;
	Scanner sc = new Scanner(System.in);
	Inventory(String name)
	{
		inventoryName = name;
	}
	Inventory(){}
	void createList()
	{
		while(true)
		{
			System.out.println("Enter item name: ");
			String itemName = sc.nextLine();
			if(itemName.equals("stop"))
			{
				return;
			}
			System.out.println("Enter item quantity: ");
			int itemQuantity = sc.nextInt();
			sc.nextLine();
			inventory.add(new InventoryItem(itemName, itemQuantity));
		}	
	}
	void addItem()
	{
		InventoryItem obj = new InventoryItem();
		System.out.println("Enter item name: ");
		obj.itemName = sc.nextLine();
		System.out.println("Enter item quantity: ");
		obj.itemQuantity = sc.nextInt();
		inventory.add(obj);	
	}
	void deleteItem()
	{
		System.out.println("Enter item to be deleted: ");
		String name = sc.nextLine();
  		for(int i=0; i<inventory.size(); i++)
		{
			if(name.equals(inventory.get(i).itemName))
			{
				inventory.remove(i);
			}
		} 
	}
	void updateInventory()
	{
		System.out.println("Enter item to be updated: ");
		String name = sc.nextLine();
		System.out.println("Enter new quantity: ");
		int qty = sc.nextInt();
  		for(int i=0; i<inventory.size(); i++)
		{
			if(name.equals(inventory.get(i).itemName))
			{
				inventory.remove(i);
				inventory.add(i, new InventoryItem(name, qty));
				return;
			}
		}	
	}
	void displayInventory()
	{
		System.out.println("------------------");
		System.out.println("|Name\t|Quantity|");
		System.out.println("------------------");
		for(InventoryItem item: inventory)
		{
			System.out.println("|" + item.itemName + "\t|" + item.itemQuantity + "       |");
			System.out.println("------------------");
		}
	}
	void displayMenu() 
	{
		System.out.println("");
		System.out.println("****Inventory Options****");
		System.out.println("Current inventory: " + inventoryName);
		System.out.println("1. Add new item");
		System.out.println("2. Delete an item");
		System.out.println("3. Update inventory");
		System.out.println("4. Display inventory");
		System.out.println("5. Back");
		int choice = 0;
		while(true)
		{
			try
			{
				System.out.println("Enter choice: ");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice)
				{
					case 1:
						addItem();
						break;
					case 2:
						deleteItem();
						break;
					case 3:
						updateInventory();
						break;	
					case 4:
						displayInventory();
						break;
					case 5:
						return;
					default: 
						System.out.println("Invalid choice.");	
				}
			}		
			catch(InputMismatchException e)
			{
				System.out.println("Invalid input. Please try again.");
				sc.nextLine();
			}
		}	
	}
}
