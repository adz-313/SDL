package Admin;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class EmployeeManagement 
{
	
	public class TestPanel extends JPanel {

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        int w = getWidth();
	        int h = getHeight();
	        Color color1 = new Color(242, 166, 90);
	        Color color2 = new Color(119, 47, 26);
	        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	    }
	}
	
	Scanner sc = new Scanner(System.in);
	Connection con;
	Statement st;
	ResultSet rs;
	JTable tbl_emp = new JTable();
	DefaultTableModel model;
	JTextField txt_name;
	JTextField txt_age;
	JTextField txt_phone;
	JTextField txt_designation;
	JTextField txt_address;
	
	public EmployeeManagement()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/restaurentusers", "root", "root");
		    st = con.createStatement();
		    tbl_emp.setBounds(50, 50, 400, 450);
			//model = (DefaultTableModel) tbl_emp.getModel();
			model = new DefaultTableModel(
					new Object [][] {

		            },
		            new String [] {
		                "Name", "Age", "Phone No", "Designation", "Address"
		            }
		            
			) {

				public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
			};
			tbl_emp.setModel(model);
			model.addRow(new Object[] {"Name", "Age", "Phone No", "Designation", "Address"});
		    displayRecords();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void addEmployee(EmployeeDetails empDetails) 
	{
		String query = "insert into employee values('" + empDetails.name + "'," + empDetails.age + "," + empDetails.phoneNo + ",'" + empDetails.designation + "','" + empDetails.address + "');";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String query = "select * from employee";
		try {
			rs = st.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] {rs.getString(1), String.valueOf(rs.getString(2)), String.valueOf(rs.getString(3)) ,rs.getString(4), rs.getString(5)});
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
	
	void deleteRecord(String name) 
	{
		String query = "delete from employee where emp_name = '" + name + "';";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	/*public void employeeManagementMenu() throws SQLException
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
	}*/
	
	public void employeeManagementFrame(JFrame prevFrame)
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Employee Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JLabel lbl_name = new JLabel();
		lbl_name.setBounds(475, 50, 100, 30);
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_name.setForeground(Color.white);
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_name.setText("Name:");
		
		txt_name = new JTextField();
		txt_name.setBounds(600, 50, 150, 30);
		
		JLabel lbl_age = new JLabel();
		lbl_age.setBounds(475, 100, 100, 30);
		lbl_age.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_age.setForeground(Color.white);
		lbl_age.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_age.setText("Age:");
		
		txt_age = new JTextField();
		txt_age.setBounds(600, 100, 150, 30);
		
		JLabel lbl_phone = new JLabel();
		lbl_phone.setBounds(475, 150, 100, 30);
		lbl_phone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_phone.setForeground(Color.white);
		lbl_phone.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_phone.setText("Phone:");
		
		txt_phone = new JTextField();
		txt_phone.setBounds(600, 150, 150, 30);
		
		JLabel lbl_designation = new JLabel();
		lbl_designation.setBounds(475, 200, 100, 30);
		lbl_designation.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_designation.setForeground(Color.white);
		lbl_designation.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_designation.setText("Designation:");
		
		txt_designation = new JTextField();
		txt_designation.setBounds(600, 200, 150, 30);
		
		JLabel lbl_address = new JLabel();
		lbl_address.setBounds(475, 250, 100, 30);
		lbl_address.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_address.setForeground(Color.white);
		lbl_address.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_address.setText("Address:");
		
		txt_address = new JTextField();
		txt_address.setBounds(600, 250, 150, 30);
		
		JButton btn_add = new JButton();
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_add.setBackground(new Color(77, 30, 17));
		btn_add.setForeground(Color.white);
		btn_add.setBounds(500, 330, 225, 30);
		btn_add.setText("Add");
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EmployeeDetails emp = new EmployeeDetails();
				emp.name = txt_name.getText();
				emp.age = Integer.parseInt(txt_age.getText());
				emp.phoneNo = Integer.parseInt(txt_phone.getText());
				emp.designation = txt_designation.getText();
				emp.address = txt_address.getText();
				addEmployee(emp);
				model.setRowCount(1);
				displayRecords();
				clearInput();
			}
		});
		
		JButton btn_update = new JButton();
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_update.setBackground(new Color(77, 30, 17));
		btn_update.setForeground(Color.white);
		btn_update.setBounds(500, 380, 225, 30);
		btn_update.setText("Update");
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EmployeeDetails emp = new EmployeeDetails();
				emp.name = txt_name.getText();
				emp.age = Integer.parseInt(txt_age.getText());
				emp.phoneNo = Integer.parseInt(txt_phone.getText());
				emp.designation = txt_designation.getText();
				emp.address = txt_address.getText();
				String query = "update employee set emp_name = '" + emp.name + "', emp_age = " + emp.age + ", emp_phone = " + emp.phoneNo + ", emp_designation = '" + emp.designation + "', emp_address = '" + emp.address + "' where emp_name = '" + emp.name + "';";
				try {
					st.executeUpdate(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				model.setRowCount(1);
				displayRecords();
				clearInput();
			}
		});
		
		JButton btn_delete = new JButton();
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_delete.setBackground(new Color(77, 30, 17));
		btn_delete.setForeground(Color.white);
		btn_delete.setBounds(500, 430, 225, 30);
		btn_delete.setText("Delete");
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				deleteRecord(name);
				model.setRowCount(1);
				displayRecords();
				clearInput();
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setBounds(500, 480, 225, 30);
		btn_back.setText("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		
		JPanel pnl_tbl = new TestPanel();
		pnl_tbl.setBounds(40, 30, 420, 480);
		pnl_tbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Employees", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl.add(tbl_emp);
		
		pnlMain.add(tbl_emp);
		pnlMain.add(lbl_name);
		pnlMain.add(txt_name);
		pnlMain.add(lbl_age);
		pnlMain.add(txt_age);
		pnlMain.add(lbl_phone);
		pnlMain.add(txt_phone);
		pnlMain.add(lbl_designation);
		pnlMain.add(txt_designation);
		pnlMain.add(lbl_address);
		pnlMain.add(txt_address);
		pnlMain.add(btn_add);
		pnlMain.add(btn_update);
		pnlMain.add(btn_delete);
		pnlMain.add(btn_back);
		pnlMain.add(pnl_tbl);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	void clearInput()
	{
		txt_name.setText("");
		txt_age.setText("");
		txt_phone.setText("");
		txt_designation.setText("");
		txt_address.setText("");
	}
}
