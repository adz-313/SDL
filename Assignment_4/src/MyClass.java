import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Admin.AdminServer;
import Admin.CustomerDetails;
import Admin.CustomerHandler;
import Admin.EmployeeManagement;
import Admin.InventoryManagement;
import Admin.OrderGenerator;
import Admin.KitchenHandler;
import Admin.Menu;
import Admin.MenuHandler;
import Client.CustomerAuthentication;
import Client.CustomerServer;
import Client.HomeDelivery;
import Client.CustomerAuthentication.ImagePanel2;

public class MyClass extends JFrame
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
	
	InventoryManagement invMgmt = new InventoryManagement();
	EmployeeManagement empMgmt = new EmployeeManagement();
	Menu menuGen = new Menu();
	CustomerHandler custHandler = new CustomerHandler();
	KitchenHandler kh = new KitchenHandler();
	CustomerAuthentication custAuth = new CustomerAuthentication(custHandler);
	MenuHandler menuHandler = new MenuHandler(menuGen);
	JTextArea txt_kitchenView = new JTextArea();
	OrderGenerator orderGen = new OrderGenerator(kh,menuHandler,txt_kitchenView);
	JTextArea txt_chat = new JTextArea();
	AdminServer adminServer = new AdminServer(menuGen, orderGen, custHandler, txt_chat, txt_kitchenView);
	
	void mainFrame()
	{
		JFrame frame = new JFrame();
		frame.setSize(400, 350);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Select User");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 400, 350);
		pnlMain.setLayout(null);
		
		JButton btn_client = new JButton();
		btn_client.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_client.setBackground(new Color(77, 30, 17));
		btn_client.setForeground(Color.white);
		btn_client.setBounds(10, 10, 360, 40);
		btn_client.setText("Client");
		btn_client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				custAuth.customerAuthMenuFrame();	
			}
		});
		
		JButton btn_admin = new JButton();
		btn_admin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_admin.setBackground(new Color(77, 30, 17));
		btn_admin.setForeground(Color.white);
		btn_admin.setBounds(10, 110, 360, 40);
		btn_admin.setText("Admin");
		btn_admin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				adminMenuFrame();
			}
		});
		
		JButton btn_exit = new JButton();
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_exit.setBackground(new Color(77, 30, 17));
		btn_exit.setForeground(Color.white);
		btn_exit.setBounds(10, 210, 360, 40);
		btn_exit.setText("Exit");
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
		pnlMain.add(btn_client);
		pnlMain.add(btn_admin);
		pnlMain.add(btn_exit);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	/*void adminMenu() 
	{
		AdminServer adminServer = new AdminServer(menuGen, orderGen, custHandler);
		while(true)
	    {
	        try
	        {
	            System.out.println("");
	            System.out.println("****Restaurant Management****");
	            System.out.println("1. Inventory Management");
	            System.out.println("2. Employee Management");
	            System.out.println("3. Menu Generator");
	            System.out.println("4. Order Generator");
	            System.out.println("5. Kitchen Handler");
	            System.out.println("6. Chat");
	            System.out.println("7. Exit");
	            System.out.print("Enter choice: ");
	            Scanner sc = new Scanner(System.in);
	            int choice = sc.nextInt();
	            switch(choice)
	            {
	                case 1:
	                    invMgmt.inventoryManagementFrame();
	                    break;
	                case 2:
	                    empMgmt.employeeManagementMenu();
	                    break;
	                case 3:
	                	menuHandler.menuGenerator();
	                    break;
	                case 4:                        
	                    orderGen.orderGeneratorMenu();
	                    break;	            
	                case 5:
	                    kh.kitchenHandlerMenu();
	                    break;
	                case 6:
	                    adminServer.sendMessage();
	                    break;
	                case 7:	   
	                	menuHandler.saveMenu();
	                	adminServer.t.interrupt();
	                    return;
	                default:
	                    System.out.println("Invalid input.");
	            }
	        }
	        catch(InputMismatchException e)
	        {
	            System.out.println("Error: Wrong input type. Please try again.");
	        } 
	        catch (SQLException e) 
	        {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}*/
	
	void adminMenuFrame()
	{
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Admin Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 400, 400);
		pnlMain.setLayout(null);
		
		JButton btn_invMgmt = new JButton();
		btn_invMgmt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_invMgmt.setBackground(new Color(77, 30, 17));
		btn_invMgmt.setForeground(Color.white);
		btn_invMgmt.setBounds(10, 10, 360, 40);
		btn_invMgmt.setText("Inventory Management");
		btn_invMgmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				invMgmt.inventoryManagementFrame(frame);
			}
		});
		
		JButton btn_empMgmt = new JButton();
		btn_empMgmt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_empMgmt.setBackground(new Color(77, 30, 17));
		btn_empMgmt.setForeground(Color.white);
		btn_empMgmt.setBounds(10, 60, 360, 40);
		btn_empMgmt.setText("Employee Management");
		btn_empMgmt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				empMgmt.employeeManagementFrame(frame);
			}
		});
		
		JButton btn_menuHandler = new JButton();
		btn_menuHandler.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_menuHandler.setBackground(new Color(77, 30, 17));
		btn_menuHandler.setForeground(Color.white);
		btn_menuHandler.setBounds(10, 110, 360, 40);
		btn_menuHandler.setText("Menu Handler");
		btn_menuHandler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				menuHandler.menuGeneratorFrame(frame);
			}
		});
		
		JButton btn_orderGen = new JButton();
		btn_orderGen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_orderGen.setBackground(new Color(77, 30, 17));
		btn_orderGen.setForeground(Color.white);
		btn_orderGen.setBounds(10, 160, 360, 40);
		btn_orderGen.setText("Order Generator");
		btn_orderGen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				orderGen.orderGeneratorFrame(frame, menuGen);
			}
		});
		
		JButton btn_chat = new JButton();
		btn_chat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_chat.setBackground(new Color(77, 30, 17));
		btn_chat.setForeground(Color.white);
		btn_chat.setBounds(10, 210, 360, 40);
		btn_chat.setText("Chat Box");
		btn_chat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				chatWindow(frame);
			}
		});
		
		JButton btn_kitchenView = new JButton();
		btn_kitchenView.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_kitchenView.setBackground(new Color(77, 30, 17));
		btn_kitchenView.setForeground(Color.white);
		btn_kitchenView.setBounds(10, 260, 360, 40);
		btn_kitchenView.setText("Kitchen View");
		btn_kitchenView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				kitchenViewFrame(frame);
			}
		});
		
		JButton btn_exit = new JButton();
		btn_exit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_exit.setBackground(new Color(77, 30, 17));
		btn_exit.setForeground(Color.white);
		btn_exit.setBounds(10, 310, 360, 40);
		btn_exit.setText("Exit");
		btn_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				System.exit(0);
			}
		});
		
		pnlMain.add(btn_invMgmt);
		pnlMain.add(btn_empMgmt);
		pnlMain.add(btn_menuHandler);
		pnlMain.add(btn_orderGen);
		pnlMain.add(btn_chat);
		pnlMain.add(btn_kitchenView);
		pnlMain.add(btn_exit);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	/*int clientMenu()
	{
		Scanner sc = new Scanner(System.in);
		CustomerDetails custDetails = custAuth.customerAuthMenuFrame();
		if(custDetails == null)
		{
			System.out.println("Invalid details. Please try again.");
			return -1;
		}
		CustomerServer custServer = new CustomerServer(custDetails);
		//clientFrame();
		try
		{
			 while(true)
			 {
				 System.out.println("");
				 System.out.println("****Welcome " + custDetails.custName + "****");
				 System.out.println("1. Home Delivery");
				 System.out.println("2. Chat Server");
				 System.out.println("3. Back");
				 int choice = sc.nextInt();
				 switch(choice)
				 {
				 	case 1:
				 		HomeDelivery homeDelivery = new HomeDelivery();
				 		homeDelivery.homeDeliveryMenu();
				 		break;
				 	case 2:
				 		custServer.startThread();
				 		custServer.sendMessage(custDetails.custName);
				 		break;
				 	case 3:
				 		custServer.t.interrupt();
				 		return 0;
				 }
			 }
		}
		catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
        }
		return -1;
	}*/
	
	public static void main(String[] args)
	{
		MyClass obj = new MyClass();
		obj.mainFrame();
		/*Scanner sc = new Scanner(System.in);
		while(true)
		{
			System.out.println("");
			System.out.println("****Client or Admin****");
			System.out.println("1. Client");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    int status = obj.clientMenu();
                    if(status == -1)
                    {
                    	return;
                    }
                    break;
                case 2:
                    obj.adminMenuFrame();
                    break;
                case 3:
                    return;
            }
		}*/
		//
		//invMgmt.inventoryManagementFrame();
	}
	
	void chatWindow(JFrame prevFrame)
	{
		JFrame frame = new JFrame();
		frame.setSize(700, 450);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Chat Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 700, 450);
		pnlMain.setLayout(null);
		
		txt_chat.setBounds(10, 10, 663, 300);
		
		JLabel lbl_name = new JLabel();
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_name.setForeground(Color.white);
		lbl_name.setBounds(10, 320, 75, 30);
		lbl_name.setText("Name: ");
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(80, 320, 433, 30);
		
		JLabel lbl_msg = new JLabel();
		lbl_msg.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_msg.setForeground(Color.white);
		lbl_msg.setBounds(10, 370, 125, 30);
		lbl_msg.setText("Message: ");
		
		JTextField txt_msg = new JTextField();
		txt_msg.setBounds(110, 370, 563, 30);
		
		JButton btn_send = new JButton();
		btn_send.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_send.setBackground(new Color(77, 30, 17));
		btn_send.setForeground(Color.white);
		btn_send.setBounds(523, 320, 70, 30);
		btn_send.setText("Send");
		btn_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_name.getText();
				String msg = txt_msg.getText();
				adminServer.sendMessage(name, msg);
				txt_chat.append("[you] : " + msg + "\n");
			}
		});
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setBounds(603, 320, 70, 30);
		btn_back.setText("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		
		pnlMain.add(txt_chat);
		pnlMain.add(lbl_name);
		pnlMain.add(txt_name);
		pnlMain.add(lbl_msg);
		pnlMain.add(txt_msg);
		pnlMain.add(btn_send);
		pnlMain.add(btn_back);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	void kitchenViewFrame(JFrame prevFrame)
	{
		JFrame frame = new JFrame();
		frame.setSize(700, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Kitchen View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 700, 400);
		pnlMain.setLayout(null);
		
		txt_kitchenView.setBounds(10, 10, 663, 300);
		txt_kitchenView.setEditable(false);
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setBounds(603, 320, 70, 30);
		btn_back.setText("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		
		pnlMain.add(txt_kitchenView);
		pnlMain.add(btn_back);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
}
