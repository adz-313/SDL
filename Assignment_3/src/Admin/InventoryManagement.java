package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class InventoryManagement
{
	//ArrayList<Inventory> list = new ArrayList<Inventory>();
	Scanner sc = new Scanner(System.in);
	int currentList;
	Connection con;
	Statement st;
	ResultSet rs;
	public InventoryManagement() 
	{
		// TODO Auto-generated constructor stub
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
		//loadInventory();
	}
	void createNewList()
	{
		System.out.println("Enter inventory name: ");
		String invName = sc.nextLine();
		String itemName;
		int itemQty;
		while(true)
		{
			System.out.println("Enter item name: ");
			itemName = sc.nextLine();
			if(itemName.equals("stop"))
			{
				return;
			}
			System.out.println("Enter item quantity: ");
			itemQty = sc.nextInt();
			sc.nextLine();
			String query = "insert into inventory values('" + itemName + "'," + itemQty + ",'" + invName + "');";
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*void loadInventory()
	{
		String query = "select distinct(itemCategory) from inventory;";
		try
		{
			rs = st.executeQuery(query);
			while(rs.next())
			{
				list.add(new Inventory(rs.getString(1)));
			}
			for(int i = 0; i < list.size(); i++)
			{
				query ="select * from inventory where itemCategory = '" + list.get(i).inventoryName +"';";
				rs = st.executeQuery(query);
				while(rs.next())
				{
					list.get(i).addItem(rs.getString(1),rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveInventory()
	{
		String query = "delete from inventory;";
		try {
			st.executeUpdate(query);
			for(int i = 0; i < list.size(); i++)
			{
				String invName = list.get(i).inventoryName;
				for(InventoryItem item: list.get(i).inventory)
				{
					query = "insert into inventory values('" + item.itemName +"'," + item.itemQuantity + ",'" + invName + "');";
					st.executeUpdate(query);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	void displayList()
	{
		System.out.println("****Inventory List****");
		String query = "select distinct itemCategory from inventory;";
		try {
			rs = st.executeQuery(query);
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
			System.out.print("Enter inventory name: ");
			String invName = sc.nextLine();
			query = "select itemName, itemQty from inventory where itemCategory = '" + invName + "';";
			rs = st.executeQuery(query);
			System.out.println("Item Name    Item Quantity");
			while(rs.next())
			{
				System.out.println(rs.getString(1) + "	" + rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void updateList()
	{
		//list.get(currentList).displayMenu();
		System.out.println("Enter inventory name: ");
		String invName = sc.nextLine();
		System.out.println("Enter item name of item to be updated: ");
		String itemName = sc.nextLine();
		System.out.println("Enter item quantity: ");
		int itemQty = sc.nextInt();
		String query = "update inventory set itemQty = " + itemQty + " where itemName = '" + itemName + "' and itemCategory = '" + invName + "';";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void deleteList()	
	{
		//list.remove(currentList);
		System.out.println("Enter inventory name: ");
		String invName = sc.nextLine();
		System.out.println("Enter item name of item to be deleted: ");
		String itemName = sc.nextLine();
		String query = "delete from inventory where itemName = '" + itemName + "' and itemCategory = '" + invName + "';";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void inventoryManagementMenu()
	{
		System.out.println("");
		System.out.println("****Inventory Management****");
		System.out.println("1. Create new list");
		System.out.println("2. Display lists");
		System.out.println("3. Update item");
		System.out.println("4. Delete item");
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
