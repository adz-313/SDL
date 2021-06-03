package Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Admin.CustomerDetails;
import Admin.Menu;
import Admin.MenuHandler;
import Admin.MenuItem;
import Admin.Order;
import Admin.OrderGenerator;
import Admin.OrderGenerator.TestPanel;

public class HomeDelivery 
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
	
	CustomerDetails custInfo = new CustomerDetails();
	Order order;
	CustomerServer custServer = new CustomerServer();
	Menu menu = custServer.getMenu();
	MenuHandler menuHandler = new MenuHandler(menu);
	OrderGenerator orderGen = new OrderGenerator(menuHandler);
	Scanner sc = new Scanner(System.in);
	
	JTable tbl_list;
	DefaultTableModel model;
	
	HomeDelivery()
	{
		tbl_list = new JTable();
		model = new DefaultTableModel(
				new Object [][] {

	            },
	            new String [] {
	                "Item Name", "Item Quantity"
	            }
	            
		) {

			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		tbl_list.setModel(model);
		order = new Order();
		order.total = 0;
		order.orderDetails = new HashMap<String,Integer>();
	}
	
	/*public void homeDeliveryFrame()
	{
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JLabel lbl_name = new JLabel();
		lbl_name.setBounds(450, 50, 100, 30);
		lbl_name.setText("Customer Name:");
		
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
		for(int i=0; i<(menu.menu.size())/2; i++)
		{
			menuModel.addRow(new Object[] {menu.menu.get(i).name, String.valueOf(menu.menu.get(i).price)});
		}
		tbl_menu.setBounds(50, 50, 350, 450);
		
		JButton btn_add = new JButton();
		btn_add.setText("Add");
		btn_add.setBounds(450, 200, 100, 30);
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
	            }
				model.addRow(new Object[]{name,sum});
			}
		});
		
		JButton btn_placeOrder = new JButton();
		btn_placeOrder.setText("Order");
		btn_placeOrder.setBounds(600, 200, 100, 30);
		btn_placeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				order.customerName = txt_name.getText();
				order.tableNo = -1;
				custServer.sendOrder(order);
			}
		});
		
		tbl_list.setBounds(450, 270, 300, 230);
		
		JPanel pnl_tbl = new JPanel();
		pnl_tbl.setBounds(40, 30, 370, 480);
		pnl_tbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Menu", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl.add(tbl_menu);
		
		JPanel pnl_tbl2 = new JPanel();
		pnl_tbl2.setBounds(440, 250, 320, 260);
		pnl_tbl2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cart", TitledBorder.LEFT, TitledBorder.TOP));
		pnl_tbl2.add(tbl_list);
		
		frame.add(lbl_name);
		frame.add(txt_name);
		frame.add(lbl_itemQty);
		frame.add(txt_itemQty);
		frame.add(lbl_itemName);
		frame.add(txt_itemName);
		frame.add(tbl_menu);
		frame.add(tbl_list);
		frame.add(btn_add);
		frame.add(btn_placeOrder);
		frame.add(pnl_tbl);
		frame.add(pnl_tbl2);
		frame.setVisible(true);
	}*/
	
	public void homeDeliveryFrame(JFrame prevFrame)
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
		
		JLabel lbl_itemName = new JLabel();
		lbl_itemName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_itemName.setForeground(Color.white);
		lbl_itemName.setBounds(450, 100, 100, 30);
		lbl_itemName.setText("Item Name:");
		
		JTextField txt_itemName = new JTextField();
		txt_itemName.setBounds(600, 100, 150, 30);
		
		JLabel lbl_itemQty = new JLabel();
		lbl_itemQty.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_itemQty.setForeground(Color.white);
		lbl_itemQty.setBounds(450, 150, 100, 30);
		lbl_itemQty.setText("Item Quantity:");
		
		JTextField txt_itemQty = new JTextField();
		txt_itemQty.setBounds(600, 150, 150, 30);
		
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
		for(int i=(menu.menu.size())/2; i<(menu.menu.size()); i++)
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
		lbl_total.setBounds(450, 350, 100, 30);
		lbl_total.setText("Total: ");
		
		JLabel lbl_totalValue = new JLabel();
		lbl_totalValue.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_totalValue.setForeground(Color.white);
		lbl_totalValue.setBounds(600, 350, 150, 30);
		lbl_totalValue.setText(String.valueOf(order.total));
		
		JButton btn_add = new JButton();
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_add.setBackground(new Color(77, 30, 17));
		btn_add.setForeground(Color.white);
		btn_add.setText("Add");
		btn_add.setBounds(475, 200, 100, 30);
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
		btn_update.setBounds(625, 200, 100, 30);
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
		btn_delete.setBounds(475, 250, 100, 30);
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
		btn_placeOrder.setBounds(625, 250, 100, 30);
		btn_placeOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				order.customerName = txt_name.getText();
				order.tableNo = -1;
				custServer.sendOrder(order);
				JOptionPane.showMessageDialog (null, "Order placed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				txt_name.setText("");
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setText("Back");
		btn_back.setBounds(550, 300, 100, 30);
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				custServer.t.interrupt();
				frame.dispose();
				prevFrame.setVisible(true);
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
	public Order getOrder()
	{
		return order;
	}
	public void homeDeliveryMenu()
	{
		try
		{
			while(true)
			{
				System.out.println("");
				System.out.println("****Home Delivery****");
				System.out.println("1. View menu");
				System.out.println("2. Create order");
				System.out.println("3. Back");
				System.out.println("Enter choice: ");
				int choice = sc.nextInt();
				switch(choice)
				{
					case 1: 
						menu.display();
						break;
					case 2:
						order = orderGen.createOrder(0);
						custServer.sendOrder(order);
						break;
					case 3:
						return;
					default:
						System.out.println("Invalid choice");
				}
			}
		}
		catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
            sc.nextLine();
        }
	}
}
