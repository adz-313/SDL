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
import java.util.Scanner;

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

import Admin.EmployeeManagement.TestPanel;

public class MenuHandler 
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

	
	Menu menuGen;
	Connection con;
	Statement st;
	ResultSet rs;
	Scanner sc = new Scanner(System.in);
	JTable tbl_menu;
	DefaultTableModel model;
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
		tbl_menu = new JTable();
		tbl_menu.setBounds(50, 50, 350, 450);
		model = new DefaultTableModel(
				new Object [][] {

	            },
	            new String [] {
	                "Item Name", "Item Price"
	            }
	            
		) {

			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		tbl_menu.setModel(model);
		displayTable();
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
	public int searchItem(String name)
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
	
	void deleteMenuItem(String name)
	{
		for(int i=0; i<menuGen.menu.size(); i++)
		{
			if(name.equals(menuGen.menu.get(i).name))
			{
				menuGen.menu.remove(i);
			}
		}
	}
	
	void updateMenuItem(String name,int price)
	{
		deleteMenuItem(name);
		addMenuItem(name,price);
	}
	
	/*public void menuGenerator()
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
	}*/
	
	public void menuGeneratorFrame(JFrame prevFrame)
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Menu Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JLabel lbl_itemName = new JLabel();
		lbl_itemName.setBounds(450, 50, 100, 30);
		lbl_itemName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_itemName.setForeground(Color.white);
		lbl_itemName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itemName.setText("Item Name:");
		
		JTextField txt_itemName = new JTextField();
		txt_itemName.setBounds(600, 50, 150, 30);
		
		JLabel lbl_itemPrice = new JLabel();
		lbl_itemPrice.setBounds(450, 100, 100, 30);
		lbl_itemPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_itemPrice.setForeground(Color.white);
		lbl_itemPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itemPrice.setText("Item Quantity:");
		
		JTextField txt_itemPrice = new JTextField();
		txt_itemPrice.setBounds(600, 100, 150, 30);
				
		JPanel pnl_tbl = new TestPanel();
		pnl_tbl.setBounds(40, 30, 370, 480);
		pnl_tbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl.add(tbl_menu);
		
		JButton btn_add = new JButton();
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_add.setBackground(new Color(77, 30, 17));
		btn_add.setForeground(Color.white);
		btn_add.setText("Add");
		btn_add.setBounds(450, 200, 100, 30);
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addMenuItem(txt_itemName.getText(),Integer.parseInt(txt_itemPrice.getText()));
				model.setRowCount(0);
				displayTable();
			}
		});
		
		JButton btn_update = new JButton();
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_update.setBackground(new Color(77, 30, 17));
		btn_update.setForeground(Color.white);
		btn_update.setText("Update");
		btn_update.setBounds(600, 200, 100, 30);
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateMenuItem(txt_itemName.getText(),Integer.parseInt(txt_itemPrice.getText()));
				model.setRowCount(0);
				displayTable();
			}
		});
		
		JButton btn_delete = new JButton();
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_delete.setBackground(new Color(77, 30, 17));
		btn_delete.setForeground(Color.white);
		btn_delete.setText("Delete");
		btn_delete.setBounds(450, 250, 100, 30);
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteMenuItem(txt_itemName.getText());
				model.setRowCount(0);
				displayTable();
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setText("Back");
		btn_back.setBounds(600, 250, 100, 30);
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveMenu();
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		
		pnlMain.add(lbl_itemPrice);
		pnlMain.add(txt_itemPrice);
		pnlMain.add(lbl_itemName);
		pnlMain.add(txt_itemName);
		pnlMain.add(tbl_menu);
		pnlMain.add(btn_add);
		pnlMain.add(btn_update);
		pnlMain.add(btn_delete);
		pnlMain.add(btn_back);
		pnlMain.add(pnl_tbl);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	void displayTable()
	{
		for(int i=0; i<(menuGen.menu.size()); i++)
		{
			model.addRow(new Object[] {menuGen.menu.get(i).name, String.valueOf(menuGen.menu.get(i).price)});
		}
	}
}
