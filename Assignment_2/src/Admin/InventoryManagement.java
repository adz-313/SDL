package Admin;
import java.util.*;

public class InventoryManagement
{
	ArrayList<Inventory> list = new ArrayList<Inventory>();
	Scanner sc = new Scanner(System.in);
	int currentList;
	void createNewList()
	{
		System.out.println("Enter inventory name: ");
		String name = sc.nextLine();
		list.add(new Inventory(name));
		list.get(list.size()-1).createList();
	}
	void displayList()
	{
		if(list.size() == 0)
		{
			System.out.println("No list present. Please add a list.");
			return;
		}
		System.out.println("");
		System.out.println("****Current Lists****");
		for(int i=0; i<list.size(); i++)
		{
			System.out.println((i+1) + ". " + list.get(i).inventoryName);
		}
		System.out.println("Select list: ");
		currentList = sc.nextInt()-1;
		System.out.println("Selected " + list.get(currentList).inventoryName + ". Please select an operation.");
	}
	void updateList()
	{
		list.get(currentList).displayMenu();
	}
	void deleteList()	
	{
		list.remove(currentList);
	}
	public void inventoryManagementMenu()
	{
		System.out.println("");
		System.out.println("****Inventory Management****");
		System.out.println("1. Create new list");
		System.out.println("2. Display lists");
		System.out.println("3. Update list");
		System.out.println("4. Delete list");
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
						createNewList();
						break;
					case 2:
						displayList();
						break;
					case 3:
						updateList();
						break;
					case 4:
						deleteList();
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
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("Error 404: List not found.");
			}
		}
	}
}
