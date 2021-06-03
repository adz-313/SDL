package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuHandler 
{
	Menu menuGen;
	Connection con;
	Statement st;
	ResultSet rs;
	Scanner sc = new Scanner(System.in); 
	public MenuHandler(Menu ref) {
		// TODO Auto-generated constructor stub
		menuGen = ref;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/restaurentusers", "root", "root");
		    st = con.createStatement();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadMenu();
	}
	void loadMenu()
	{
		String query = "select * from menu;";
		try {
			rs = st.executeQuery(query);
			while(rs.next())
			{
				addMenuItem(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	public void saveMenu()
	{
		String query = "delete from menu;";
		try {
			st.executeUpdate(query);
			for(MenuItem m : menuGen.menu)
			{
				query = "insert into menu values('" + m.name +"'," + m.price + ");";
				st.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int searchItem(String name)
	{
		for(MenuItem m : menuGen.menu)
		{
			if(m.name.equals(name))
			{
				return m.price;
			}
		}
		return -1;
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
	void addMenuItem(String name,int price)
	{
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
