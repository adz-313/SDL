package Admin;

import java.util.Scanner;

public class MenuHandler 
{
	Menu menuGen;
	public MenuHandler(Menu ref) {
		// TODO Auto-generated constructor stub
		menuGen = ref;
	}
	Scanner sc = new Scanner(System.in); 
	void createMenuList()
	{
		while(true)
		{
			System.out.print("Item name: ");
			String name = sc.nextLine();
			if(name.equals("stop"))
			{
				return;
			}
			System.out.print("Item price: ");
			int price = sc.nextInt();
			sc.nextLine();
			menuGen.menu.add(new MenuItem(name, price));
		}
	}
	public void displayMenuItems()
	{
		System.out.println("****Food Menu****");
		System.out.println("Item Name    Price");
		for(MenuItem m : menuGen.menu)
		{
			System.out.println(m.name + " " + m.price);
		}
	}
	void addMenuItem()
	{
		System.out.print("Item name: ");
		String name = sc.nextLine();
		System.out.print("Item price: ");
		int price = sc.nextInt();
		sc.nextLine();
		menuGen.menu.add(new MenuItem(name, price));
	}
	void deleteMenuItem()
	{
		String name;
		System.out.print("Enter name of item to be deleted: ");
		name = sc.nextLine();
		for(int i=0; i<menuGen.menu.size(); i++)
		{
			if(name.equals(menuGen.menu.get(i).name))
			{
				menuGen.menu.remove(i);
			}
		}
	}
	public void menuGenerator()
	{
		System.out.println("");
		System.out.println("****Menu Generator****");
		System.out.println("1. Create menu");
		System.out.println("2. Display menu");
		System.out.println("3. Add menu item");
		System.out.println("4. Delete menu item");
		System.out.println("5. Back");
		while(true)
		{
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1:
					createMenuList();
					break;
				case 2:
					displayMenuItems();
					break;
				case 3:
					addMenuItem();
					break;
				case 4:
					deleteMenuItem();
					break;
				case 5:
					return;
				default: 
					System.out.println("Invalid choice.");
			}
		}
	}
}
