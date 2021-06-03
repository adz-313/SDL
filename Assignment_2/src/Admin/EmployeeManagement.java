package Admin;
import java.util.*;

public class EmployeeManagement 
{
	LinkedList<EmployeeDetails> employeeList= new LinkedList<EmployeeDetails>();
	Scanner sc = new Scanner(System.in);
	void insertRecord()
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
		employeeList.add(obj);
	}
	void displayRecords()
	{
		System.out.println("");
		System.out.println("****Employee List****");
		System.out.println("Name | Age | Phone No | Designation | Address");
		for(EmployeeDetails obj: employeeList)
		{
			System.out.println(obj.name + " | " + obj.age + " | " + obj.phoneNo + " | " + obj.designation + " | " + obj.address);
		}
	}
	void updateRecord()
	{
		System.out.print("Enter name of employee to be updated: ");
		String name = sc.nextLine();
		int i;
		for(i=0; i<employeeList.size(); i++)
		{
			if(name.equals(employeeList.get(i).name))
			{
				break;
			}
		}
		if(i == employeeList.size())
		{
			System.out.println("Error 404: Record not found.");
			return;
		}
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
					employeeList.get(i).name = data;
					break;
				case 2:
					employeeList.get(i).age = Integer.parseInt(data);
					break;
				case 3:
					employeeList.get(i).phoneNo = Integer.parseInt(data);
					break;
				case 4:
					employeeList.get(i).designation = data;
					break;
				case 5:
					employeeList.get(i).address = data;
					break;
				case 6:
					return;
				default:
					System.out.println("Invalid choice.");
			}
		}
	}
	/*<E> void newValue(int i, E data)
	{
		if(i == 2 || i == 3)
		{

		}
	}*/
	void deleteRecord()
	{
		System.out.print("Enter name of employee to be updated: ");
		String name = sc.nextLine();
		int i;
		for(i=0; i<employeeList.size(); i++)
		{
			if(name.equals(employeeList.get(i).name))
			{
				employeeList.remove(i);
			}
		}
		if(i == employeeList.size())
		{
			System.out.println("Error 404: Record not found.");
			return;
		}
		else
		{
			System.out.println("Record deleted.");
		}
	} 
	public void employeeManagementMenu()
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
