package Admin;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Admin.MenuHandler.TestPanel;
import Kitchen.KitchenView;

public class OrderGenerator 
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
    LinkedList<Order> customerList = new LinkedList<Order>();
    KitchenView kv;
    KitchenHandler kh;
    MenuHandler menuHandler;
    JTable tbl_cust;
    JTable tbl_order;
	DefaultTableModel custModel;
	DefaultTableModel orderModel;
	Order order;
	JTable tbl_list;
	DefaultTableModel model;
	JTextArea txt_kitchenView;
    public OrderGenerator(){}
    public OrderGenerator(MenuHandler ref)
    {
    	menuHandler = ref;
    }
    public OrderGenerator(KitchenHandler obj,MenuHandler ref, JTextArea ta)
    {
    	kh = obj;
    	menuHandler = ref;
    	txt_kitchenView = ta;
    	tbl_list = new JTable();
		orderModel = new DefaultTableModel(
				new Object [][] {

	            },
	            new String [] {
	                "Name", "Phone No", "Address"
	            }
	            
		) {

			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		tbl_list.setModel(orderModel);
		orderModel.addRow(new Object[] {"Name" , "Table No", "Total"});
    }
    public Order createOrder(int type)
    {
    	Order obj = new Order();
    	int sum = 0;
    	System.out.print("Enter customer name: ");
        obj.customerName = sc.nextLine();
        if(type == 1)
        {
        	System.out.print("Enter table number: ");
        	obj.tableNo = sc.nextInt();
            sc.nextLine();
        }
        else
        {
        	obj.tableNo = -1;
        }
        obj.orderDetails = new HashMap<String,Integer>();
        while(true)
        {
            System.out.print("Item name: ");
            String itemName = sc.nextLine();
            if(itemName.equals("stop"))
            {
                break;
            }
            int price = menuHandler.searchItem(itemName);
            if(price != -1)
            {
            	System.out.print("Item quantity: ");
                int itemQty = sc.nextInt();
                sc.nextLine();
                obj.orderDetails.put(itemName,itemQty);
                sum += (price * itemQty);
            }
            else
            {
            	System.out.println("Error 404: Item not found.");
            }
            obj.total = sum;
        }
        return obj;
    }
    public Order createOrder(Order obj, int type)
    {
    	int sum = 0;
    	System.out.print("Enter customer name: ");
        obj.customerName = sc.nextLine();
        if(type == 1)
        {
        	System.out.print("Enter table number: ");
        	obj.tableNo = sc.nextInt();
            sc.nextLine();
        }
        else
        {
        	obj.tableNo = -1;
        }
        obj.orderDetails = new HashMap<String,Integer>();
        while(true)
        {
            System.out.print("Item name: ");
            String itemName = sc.nextLine();
            if(itemName.equals("stop"))
            {
                break;
            }
            int price = menuHandler.searchItem(itemName);
            if(price != -1)
            {
            	System.out.print("Item quantity: ");
                int itemQty = sc.nextInt();
                sc.nextLine();
                obj.orderDetails.put(itemName,itemQty);
                sum += (price * itemQty);
            }
            else
            {
            	System.out.println("Error 404: Item not found.");
            }
            obj.total = sum;
        }
        return obj;
    }
    void placeOrder()
    {
    	Order order = createOrder(1);
    	customerList.add(order);
        kh.addNewOrder(order);
        //kv = new KitchenView(order);
        System.out.println("Order placed successfully.");
    }
    public void placeOrder(Order order)
    {
    	customerList.add(order);
        kh.addNewOrder(order);
        //kv = new KitchenView(order);
        System.out.println("Order placed successfully.");
    }
    void deleteOrder()
    {
        System.out.println("Enter table no: ");
        int tblNo = sc.nextInt();
        int i;
        for(i=0; i<customerList.size(); i++)
        {
            if(tblNo == customerList.get(i).tableNo)
            {
                customerList.remove(i);
            }
        }
        if(i == customerList.size())
        {
            System.out.println("Error 404: Customer not found.");
        }
    }
    void displayCustomerQueue()
    {
    	orderModel.setRowCount(1);
        for(Order obj : customerList)
        {
        	orderModel.addRow(new Object[] {obj.customerName, String.valueOf(obj.tableNo), String.valueOf(obj.total)});
        }
    }
    public void orderGeneratorMenu()
    {
        while(true)
        {
            try
            {
                System.out.println("");
                System.out.println("****Order Generator****");
                System.out.println("1. New order");
                System.out.println("2. Display order");
                System.out.println("3. Delete order");
                System.out.println("4. Back");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice)
                {
                    case 1:
                        placeOrder();
                        break;
                    case 2:
                        displayCustomerQueue();
                        break;
                    case 3:
                        deleteOrder();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("Error: Wrong input type. Please try again.");
                sc.nextLine();
            }
        }
    }
    public void orderGeneratorFrame(JFrame prevFrame, Menu menu)
    {
    	displayCustomerQueue();
    	JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Order Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JLabel lbl_name = new JLabel();
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_name.setForeground(Color.white);
		lbl_name.setBounds(450, 50, 100, 30);
		lbl_name.setText("Name:");
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(600, 50, 150, 30);
		
		JTable tbl_cart = new JTable();
		DefaultTableModel cartModel = new DefaultTableModel(
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
		tbl_cart.setModel(cartModel);
		cartModel.addRow(new Object[] {"Item Name", "Item Quantity"});
		tbl_cart.setBounds(450, 250, 300, 250);
		
		JButton btn_newOrder = new JButton();
		btn_newOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_newOrder.setBackground(new Color(77, 30, 17));
		btn_newOrder.setForeground(Color.white);
		btn_newOrder.setText("New Order");
		btn_newOrder.setBounds(450, 100, 100, 30);
		btn_newOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				addOrderFrame(frame, menu);
			}
		});
		
		JButton btn_display = new JButton();
		btn_display.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_display.setBackground(new Color(77, 30, 17));
		btn_display.setForeground(Color.white);
		btn_display.setText("Display");
		btn_display.setBounds(600, 100, 100, 30);	
		btn_display.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				for(Order obj : customerList)
		        {
		        	if(obj.customerName.equals(name))
		        	{
		        		for(Map.Entry m : obj.orderDetails.entrySet()){
			            	cartModel.addRow(new Object[] {m.getKey(), String.valueOf(m.getValue())});
			            }
		        	} 
		        }
			}
		});
		
		JButton btn_delete = new JButton();
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_delete.setBackground(new Color(77, 30, 17));
		btn_delete.setForeground(Color.white);
		btn_delete.setText("Delete");
		btn_delete.setBounds(450, 150, 100, 30);	
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
		        int i;
		        for(i=0; i<customerList.size(); i++)
		        {
		            if(name.equals(customerList.get(i).customerName))
		            {
		                customerList.remove(i);
		            }
		        }
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setText("Back");
		btn_back.setBounds(600, 150, 100, 30);	
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				prevFrame.setVisible(true);
				frame.dispose();
			}
		});
		
		tbl_list.setBounds(50, 50, 350, 450);
		
		JPanel pnl_tbl = new TestPanel();
		pnl_tbl.setBounds(40, 30, 370, 480);
		pnl_tbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "List", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl.add(tbl_list);
		
		JPanel pnl_tbl2 = new TestPanel();
		pnl_tbl2.setBounds(440, 230, 320, 280);
		pnl_tbl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cart", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl2.add(tbl_cart);
		
		pnlMain.add(lbl_name);
		pnlMain.add(txt_name);
		pnlMain.add(btn_newOrder);
		pnlMain.add(btn_delete);
		pnlMain.add(btn_back);
		pnlMain.add(btn_display);
		pnlMain.add(tbl_list);
		pnlMain.add(tbl_cart);
		pnlMain.add(pnl_tbl);
		pnlMain.add(pnl_tbl2);
		frame.add(pnlMain);
		frame.setVisible(true);
    }
    public void addOrderFrame(JFrame prevFrame,Menu menu)
    {
    	order = new Order();
		order.total = 0;
		order.orderDetails = new HashMap<String,Integer>();
		
    	JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("New Order");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JLabel lbl_name = new JLabel();
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_name.setForeground(Color.white);
		lbl_name.setBounds(450, 50, 100, 30);
		lbl_name.setText("Customer Name:");
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(600, 50, 150, 30);
		
		JLabel lbl_tblNo = new JLabel();
		lbl_tblNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_tblNo.setForeground(Color.white);
		lbl_tblNo.setBounds(450, 100, 100, 30);
		lbl_tblNo.setText("Table No:");
		
		JTextField txt_tblNo = new JTextField();
		txt_tblNo.setBounds(600, 100, 150, 30);
		
		JLabel lbl_itemName = new JLabel();
		lbl_itemName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_itemName.setForeground(Color.white);
		lbl_itemName.setBounds(450, 150, 100, 30);
		lbl_itemName.setText("Item Name:");
		
		JTextField txt_itemName = new JTextField();
		txt_itemName.setBounds(600, 150, 150, 30);
		
		JLabel lbl_itemQty = new JLabel();
		lbl_itemQty.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_itemQty.setForeground(Color.white);
		lbl_itemQty.setBounds(450, 200, 100, 30);
		lbl_itemQty.setText("Item Quantity:");
		
		JTextField txt_itemQty = new JTextField();
		txt_itemQty.setBounds(600, 200, 150, 30);
		
		JTable tbl_menu = new JTable();
		DefaultTableModel menuModel = new DefaultTableModel(
				new Object [][] {

	            },
	            new String [] {
	                "Item Name", "Item Price"
	            }
	            
		) {

			public boolean isCellEditable(int row, int column) 
			{
		       //all cells false
		       return false;
		    }
		};
		tbl_menu.setModel(menuModel);
		menuModel.addRow(new Object[] {"Item Name", "Item Price"});
		for(int i=0; i<(menu.menu.size()); i++)
		{
			menuModel.addRow(new Object[] {menu.menu.get(i).name, String.valueOf(menu.menu.get(i).price)});
		}
		tbl_menu.setBounds(50, 50, 350, 210);
		
		JTable tbl_cart = new JTable();
		DefaultTableModel cartModel = new DefaultTableModel(
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
		tbl_cart.setModel(cartModel);
		cartModel.addRow(new Object[] {"Item Name", "Item Quantity"});
		tbl_cart.setBounds(50, 300, 350, 210);
		
		JLabel lbl_total = new JLabel();
		lbl_total.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_total.setForeground(Color.white);
		lbl_total.setBounds(450, 400, 100, 30);
		lbl_total.setText("Total: ");
		
		JLabel lbl_totalValue = new JLabel();
		lbl_totalValue.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_totalValue.setForeground(Color.white);
		lbl_totalValue.setBounds(600, 400, 150, 30);
		lbl_totalValue.setText(String.valueOf(order.total));
		
		JButton btn_add = new JButton();
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_add.setBackground(new Color(77, 30, 17));
		btn_add.setForeground(Color.white);
		btn_add.setText("Add");
		btn_add.setBounds(475, 250, 100, 30);
		btn_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_itemName.getText();
				int qty = Integer.parseInt(txt_itemQty.getText());
				int price = menuHandler.searchItem(name);
				int sum = 0;
	            if(price != -1)
	            {
	                order.orderDetails.put(name,qty);
	                sum = (price * qty);
	                order.total += sum;
	                lbl_totalValue.setText(String.valueOf(order.total)); 
		            cartModel.addRow(new Object[]{name,qty});
	            }
	            txt_itemName.setText("");
	            txt_itemQty.setText("");
			}
		});
		
		JButton btn_update = new JButton();
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_update.setBackground(new Color(77, 30, 17));
		btn_update.setForeground(Color.white);
		btn_update.setText("Update");
		btn_update.setBounds(625, 250, 100, 30);
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_itemName.getText();
				int qty = Integer.parseInt(txt_itemQty.getText());
				int i;
				for(i=0; i<cartModel.getRowCount(); i++)
				{
					if(name.equals(cartModel.getValueAt(i, 0)))
					{
						break;
					}
				}
				int oldQty = Integer.parseInt(String.valueOf(cartModel.getValueAt(i, 1)));
				order.orderDetails.replace(name, qty);
				cartModel.setValueAt(qty, i, 1);
				int price = menuHandler.searchItem(name);
				order.total = order.total - (oldQty * price) + (qty * price);
				lbl_totalValue.setText(String.valueOf(order.total));
				txt_itemName.setText("");
	            txt_itemQty.setText("");
			}
		});
		
		JButton btn_delete = new JButton();
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_delete.setBackground(new Color(77, 30, 17));
		btn_delete.setForeground(Color.white);
		btn_delete.setText("Delete");
		btn_delete.setBounds(475, 300, 100, 30);
		btn_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_itemName.getText();
				int price = menuHandler.searchItem(name);
				int i;
				for(i=0; i<cartModel.getRowCount(); i++)
				{
					if(name.equals(cartModel.getValueAt(i, 0)))
					{
						break;
					}
				}
				int qty = Integer.parseInt(String.valueOf(cartModel.getValueAt(i, 1)));
				order.total = order.total - (qty * price);
				lbl_totalValue.setText(String.valueOf(order.total)); 
				cartModel.removeRow(i);
				order.orderDetails.remove(name);
				txt_itemName.setText("");
	            txt_itemQty.setText("");
			}
		});
		
		JButton btn_placeOrder = new JButton();
		btn_placeOrder.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_placeOrder.setBackground(new Color(77, 30, 17));
		btn_placeOrder.setForeground(Color.white);
		btn_placeOrder.setText("Order");
		btn_placeOrder.setBounds(625, 300, 100, 30);
		btn_placeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				order.customerName = txt_name.getText(); 
				order.tableNo = Integer.parseInt(txt_tblNo.getText());
				customerList.add(order);
				kv = new KitchenView(order,txt_kitchenView);
				JOptionPane.showMessageDialog (null, "Order placed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				txt_name.setText("");
				txt_tblNo.setText("");
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setText("Back");
		btn_back.setBounds(550, 350, 100, 30);
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				prevFrame.setVisible(true);
				displayCustomerQueue();
			}
		});
		
		JPanel pnl_tbl = new TestPanel();
		pnl_tbl.setBounds(40, 30, 370, 240);
		pnl_tbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl.add(tbl_menu);
		
		JPanel pnl_tbl2 = new TestPanel();
		pnl_tbl2.setBounds(40, 280, 370, 240);
		pnl_tbl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cart", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl2.add(tbl_cart);
		
		pnlMain.add(lbl_name);
		pnlMain.add(txt_name);
		pnlMain.add(lbl_tblNo);
		pnlMain.add(txt_tblNo);
		pnlMain.add(lbl_itemQty);
		pnlMain.add(txt_itemQty);
		pnlMain.add(lbl_itemName);
		pnlMain.add(txt_itemName);
		pnlMain.add(tbl_menu);
		pnlMain.add(tbl_cart);
		pnlMain.add(btn_add);
		pnlMain.add(btn_update);
		pnlMain.add(btn_delete);
		pnlMain.add(btn_placeOrder);
		pnlMain.add(btn_back);
		pnlMain.add(lbl_total);
		pnlMain.add(lbl_totalValue);
		pnlMain.add(pnl_tbl);
		pnlMain.add(pnl_tbl2);
		frame.add(pnlMain);
		frame.setVisible(true);
    }
}
