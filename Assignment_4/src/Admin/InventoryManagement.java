package Admin;
import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Admin.EmployeeManagement.TestPanel;

public class InventoryManagement
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
	}
	/*public void inventoryManagementFrame(JFrame prevFrame)
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  		
		
		JLabel lbl_name = new JLabel();
		lbl_name.setBounds(450, 50, 100, 30);
		lbl_name.setText("Inventory Name:");
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(600, 50, 150, 30);
		
		JLabel lbl_itemName = new JLabel();
		lbl_itemName.setBounds(450, 100, 100, 30);
		lbl_itemName.setText("Item Name:");
		
		JTextField txt_itemName = new JTextField();
		txt_itemName.setBounds(600, 100, 150, 30);
		
		JLabel lbl_itemQty = new JLabel();
		lbl_itemQty.setBounds(450, 150, 100, 30);
		lbl_itemQty.setText("Item Quantity:");
		
		JTextField txt_itemQty = new JTextField();
		txt_itemQty.setBounds(600, 150, 150, 30);
		
		JTable tbl_inv = new JTable();
		tbl_inv.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Item Quantity"
            }
        ));
		tbl_inv.setBounds(50, 50, 350, 450);
		DefaultTableModel model = (DefaultTableModel) tbl_inv.getModel();
		
		JButton btn_add = new JButton();
		btn_add.setText("Add");
		btn_add.setBounds(450, 200, 100, 30);
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				String itemName = txt_itemName.getText();
				String itemQty = txt_itemQty.getText();
				String query = "insert into inventory values('" + itemName + "'," + itemQty + ",'" + name + "');";
				try {
					st.executeUpdate(query);
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
			}
		});
		
		JButton btn_display = new JButton();
		btn_display.setText("Display");
		btn_display.setBounds(600, 200, 100, 30);
		btn_display.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try
				{
					String name = txt_name.getText();
					String query = "select itemName, itemQty from inventory where itemCategory = '" + name + "';";
					rs = st.executeQuery(query);
					while(rs.next())
					{
						model.addRow(new Object[]{rs.getString(1), String.valueOf(rs.getInt(2))});
					}
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
			}
		});
		
		JTable tbl_list = new JTable();
		tbl_list.setModel(new DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "Table Name"
	            }
	        ));
		tbl_list.setBounds(450, 250, 300, 250);
		DefaultTableModel invModel = (DefaultTableModel) tbl_list.getModel();
		try
		{
			String query = "select distinct itemCategory from inventory;";
			rs = st.executeQuery(query);
			while(rs.next())
			{
				invModel.addRow(new Object[]{rs.getString(1)});
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		frame.add(lbl_name);
		frame.add(txt_name);
		frame.add(lbl_itemQty);
		frame.add(txt_itemQty);
		frame.add(lbl_itemName);
		frame.add(txt_itemName);
		frame.add(tbl_inv);
		frame.add(tbl_list);
		frame.add(btn_add);
		frame.add(btn_display);
		frame.setVisible(true);
	}*/
	void createNewList()
	{
		System.out.println("Enter inventory name: ");
		String invName = sc.nextLine();
		String itemName;
		int itemQty;
		//list.add(new Inventory(name));
		//list.get(list.size()-1).createList();
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
		System.out.print("****Inventory List****");
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
				System.out.print(rs.getString(1) + "	" + rs.getInt(2));
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
		System.out.println("Enter item name of item to be updated: ");
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
	public void inventoryManagementFrame(JFrame prevFrame)
    {	
    	JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Inventory Management");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JLabel lbl_name = new JLabel();
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_name.setForeground(Color.white);
		lbl_name.setBounds(450, 50, 100, 30);
		lbl_name.setText("Inventory Name:");
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(600, 50, 150, 30);
		
		JLabel lbl_itemName = new JLabel();
		lbl_itemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_itemName.setForeground(Color.white);
		lbl_itemName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itemName.setBounds(450, 100, 100, 30);
		lbl_itemName.setText("Item Name:");
		
		JTextField txt_itemName = new JTextField();
		txt_itemName.setBounds(600, 100, 150, 30);
		
		JLabel lbl_itemQty = new JLabel();
		lbl_itemQty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_itemQty.setForeground(Color.white);
		lbl_itemQty.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itemQty.setBounds(450, 150, 100, 30);
		lbl_itemQty.setText("Item Quantity:");
		
		JTextField txt_itemQty = new JTextField();
		txt_itemQty.setBounds(600, 150, 150, 30);
		
		JTable tbl_inv = new JTable();
		DefaultTableModel invModel = new DefaultTableModel(
				new Object [][] {

	            },
	            new String [] {
	                "Item Name", "Item Quantity"
	            }
	            
		) {

			public boolean isCellEditable(int row, int column) 
			{
		       //all cells false
		       return false;
		    }
		};
		tbl_inv.setModel(invModel);
		invModel.addRow(new Object[] {"Item Name", "Item Quantity"});
		tbl_inv.setBounds(50, 50, 350, 210);
		
		JTable tbl_list = new JTable();
		DefaultTableModel listModel = new DefaultTableModel(
				new Object [][] {

	            },
	            new String [] {
	            		"Inventory Name"
	            }
	            
		) {

			public boolean isCellEditable(int row, int column) 
			{
		       //all cells false
		       return false;
		    }
		};
		tbl_list.setModel(listModel);
		listModel.addRow(new Object[] {"Inventory Name"});
		try
		{
			String query = "select distinct itemCategory from inventory;";
			rs = st.executeQuery(query);
			while(rs.next())
			{
				listModel.addRow(new Object[]{rs.getString(1)});
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		tbl_list.setBounds(50, 300, 350, 210);
		
		JButton btn_add = new JButton();
		btn_add.setText("Add");
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_add.setBackground(new Color(77, 30, 17));
		btn_add.setForeground(Color.white);
		btn_add.setBounds(475, 200, 100, 30);
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				String itemName = txt_itemName.getText();
				String itemQty = txt_itemQty.getText();
				String query = "insert into inventory values('" + itemName + "'," + itemQty + ",'" + name + "');";
				try {
					st.executeUpdate(query);
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				txt_itemQty.setText("");
				txt_itemName.setText("");
			}
		});
		
		JButton btn_update = new JButton();
		btn_update.setText("Update");
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_update.setBackground(new Color(77, 30, 17));
		btn_update.setForeground(Color.white);
		btn_update.setBounds(625, 200, 100, 30);
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				String itemName = txt_itemName.getText();
				String itemQty = txt_itemQty.getText();
				String query = "update inventory set itemQty = " + itemQty + " where itemName = '" + itemName + "' and itemCategory = '" + name + "';";
				try {
					st.executeUpdate(query);
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				txt_name.setText("");
				txt_itemQty.setText("");
				txt_itemName.setText("");
			}
		});
		
		JButton btn_delete = new JButton();
		btn_delete.setText("Delete");
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_delete.setBackground(new Color(77, 30, 17));
		btn_delete.setForeground(Color.white);
		btn_delete.setBounds(475, 250, 100, 30);
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				String itemName = txt_itemName.getText();
				String query = "delete from inventory where itemName = '" + itemName + "'and itemCategory = '" + name + "';";
				try {
					st.executeUpdate(query);
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				txt_name.setText("");
				txt_itemQty.setText("");
				txt_itemName.setText("");
				JOptionPane.showMessageDialog(frame,"Item Deleted.");  
			}
		});
		
		JButton btn_display = new JButton();
		btn_display.setText("Display");
		btn_display.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_display.setBackground(new Color(77, 30, 17));
		btn_display.setForeground(Color.white);
		btn_display.setBounds(625, 250, 100, 30);
		btn_display.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				invModel.setRowCount(1);
				try
				{
					String name = txt_name.getText();
					String query = "select itemName, itemQty from inventory where itemCategory = '" + name + "';";
					rs = st.executeQuery(query);
					while(rs.next())
					{
						invModel.addRow(new Object[]{rs.getString(1), String.valueOf(rs.getInt(2))});
					}
				} catch (SQLException er) {
					// TODO Auto-generated catch block
					er.printStackTrace();
				}
				txt_name.setText("");
				txt_itemQty.setText("");
				txt_itemName.setText("");
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setText("Back");
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setBounds(550, 300, 100, 30);
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		
		JPanel pnl_tbl = new TestPanel();
		pnl_tbl.setBounds(40, 30, 370, 240);
		pnl_tbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Inventory", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl.add(tbl_inv);
		
		JPanel pnl_tbl2 = new TestPanel();
		pnl_tbl2.setBounds(40, 280, 370, 240);
		pnl_tbl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl2.add(tbl_list);
		
		pnlMain.add(lbl_name);
		pnlMain.add(txt_name);
		pnlMain.add(lbl_itemQty);
		pnlMain.add(txt_itemQty);
		pnlMain.add(lbl_itemName);
		pnlMain.add(txt_itemName);
		pnlMain.add(tbl_inv);
		pnlMain.add(tbl_list);
		pnlMain.add(btn_add);
		pnlMain.add(btn_update);
		pnlMain.add(btn_delete);
		pnlMain.add(btn_display);
		pnlMain.add(btn_back);
		pnlMain.add(pnl_tbl);
		pnlMain.add(pnl_tbl2);
		frame.add(pnlMain);
		frame.setVisible(true);
    }
}
