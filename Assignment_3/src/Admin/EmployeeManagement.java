package Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class EmployeeManagement 
{
	Scanner sc = new Scanner(System.in);
	Connection con;
	Statement st;
	ResultSet rs;
	
	public EmployeeManagement()
	{
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
	}
	
	void addEmployee(EmployeeDetails empDetails) throws SQLException
	{
		String query = "insert into employee values('" + empDetails.name + "'," + empDetails.age + "," + empDetails.phoneNo + ",'" + empDetails.designation + "','" + empDetails.address + "');";
		st.executeUpdate(query);
	}
	
	void insertRecord() throws SQLException
	{
		EmployeeDetails obj = new EmployeeDetails();
		System.out.print("Name: ");
		obj.name = sc.nextLine();
		System.out.print("Age: ");
		obj.age = sc.nextInt();
		System.out.print("Phone No: ");
		obj.phoneNo = sc.nextInt();
		sc.nextLine();
		System.out.print("Designation: ");
		obj.designation = sc.nextLine();
		System.out.print("Address: ");
		obj.address = sc.nextLine();
		addEmployee(obj);
	}
	
	void displayRecords()
	{
		System.out.println("");
		System.out.println("****Employee List****");
		System.out.println("Name | Age | Phone No | Designation | Address");
		String query = "select * from employee";
		try {
			rs = st.executeQuery(query);
			while(rs.next())
			{
				System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void updateRecord()
	{
		String query = "";
		System.out.print("Enter name of employee to be updated: ");
		String name = sc.nextLine();
		System.out.println("Choose data to be updated: ");
		System.out.println("1. Name");
		System.out.println("2. Age");
		System.out.println("3. Phone number");
		System.out.println("4. Designation");
		System.out.println("5. Address");
		System.out.println("6. Back");
		while(true)
		{
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();
			String data = null;
  			if(choice != 6)
  			{
  				System.out.print("Enter updated data: ");
  				sc.nextLine();
  				data = sc.nextLine();
  			}
			switch(choice)
			{
				case 1:
					query = "update employee set emp_name = '" + data + "' where emp_name = '" + name + "';";
					break;
				case 2:
					query = "update employee set emp_age = " + Integer.parseInt(data) + " where emp_name = '" + name + "';";
					break;
				case 3:
					query = "update employee set emp_phone = " + Integer.parseInt(data) + " where emp_name = '" + name + "';";
					break;
				case 4:
					query = "update employee set emp_designation = '" + data + "' where emp_name = '" + name + "';";
					break;
				case 5:
					query = "update employee set emp_address = '" + data + "' where emp_name = '" + name + "';";
					break;
				case 6:
					return;
				default:
					System.out.println("Invalid choice.");
			}
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void deleteRecord() throws SQLException
	{
		System.out.print("Enter name of employee to be deleted: ");
		String name = sc.nextLine();
		String query = "delete from employee where emp_name = '" + name + "';";
		st.executeUpdate(query);
	} 
	
	public void employeeManagementMenu() throws SQLException
	{
		System.out.println("");
		System.out.println("****Employee Management****");
		System.out.println("1. Insert record");
		System.out.println("2. Display records");
		System.out.println("3. Update record");
		System.out.println("4. Delete record");
		System.out.println("5. Back");
		while(true)
		{
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
				case 1:
					insertRecord();
					break;
				case 2:
					displayRecords();
					break;
				case 3:
					updateRecord();
					break;
				case 4:
					deleteRecord();
					break;
				case 5:
					return;
				default: 
					System.out.println("Invalid choice.");
			}
		}	
	}
}
