package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Admin.CustomerDetails;
import Admin.CustomerHandler;

public class CustomerAuthentication 
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
	
	public class ImagePanel extends JPanel{

	    private BufferedImage image;

	    public ImagePanel() {
	       try {                
	          image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\TE\\SDL\\img4.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);            
	    }

	}
	
	public class ImagePanel2 extends JPanel{

	    private BufferedImage image;

	    public ImagePanel2() {
	       try {                
	          image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\TE\\SDL\\img6.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);            
	    }

	}
	
	CustomerDetails custCred;
	JFrame frame;
	JTextField txt_username;
	JTextField txt_password;
	JTextArea txt_chat = new JTextArea();
	CustomerHandler ref;
	Scanner sc = new Scanner(System.in);
	
	public CustomerAuthentication(CustomerHandler ch)
	{
		ref = ch;
	}
	
	public void customerAuthMenuFrame()
	{		
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		
		JPanel pnlMain = new ImagePanel2();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JPanel pnlSecond = new JPanel();
		pnlSecond.setBackground(new Color(0, 0, 0, 150));
		pnlSecond.setBounds(190, 50, 400, 420);
		pnlSecond.setLayout(null);
		
		
		JLabel lbl_heading = new JLabel();
		lbl_heading.setText("Hello");
		lbl_heading.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setForeground(Color.white);
		lbl_heading.setBounds(125, 50, 150, 40);
		
		JLabel lbl_username = new JLabel();
		lbl_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_username.setForeground(Color.white);
		lbl_username.setText("Username:");
		lbl_username.setBounds(50, 125, 100, 30);
		
		txt_username = new JTextField();
		txt_username.setBounds(200, 125, 150, 30);
		
		JLabel lbl_password = new JLabel();
		lbl_password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_password.setForeground(Color.white);
		lbl_password.setText("Password:");
		lbl_password.setBounds(50, 200, 100, 30);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(200, 200, 150, 30);
		
		JButton btn_login = new JButton();
		btn_login.setText("Login");
		btn_login.setBackground(new Color(0,0,0));
		btn_login.setForeground(Color.white);
		btn_login.setBounds(125, 275, 150, 30);
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = txt_username.getText();
				String password = txt_password.getText();
				custCred = loginPage(username, password);
				if(custCred != null)
				{
					frame.dispose();
					clientFrame(custCred.custName);
				}
				else
				{
					txt_username.setText("");
					txt_password.setText("");
					JOptionPane.showMessageDialog(null, "Invalid Credentials.","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btn_register = new JButton();
		btn_register.setBackground(new Color(0,0,0));
		btn_register.setForeground(Color.white);
		btn_register.setText("Register");
		btn_register.setBounds(125, 350, 150, 30);
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				registerFrame();
			}
		});
		
		pnlSecond.add(lbl_heading);
		pnlSecond.add(lbl_username);
		pnlSecond.add(txt_username);
		pnlSecond.add(lbl_password);
		pnlSecond.add(txt_password);
		pnlSecond.add(btn_login);
		pnlSecond.add(btn_register);
		pnlMain.add(pnlSecond);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	public void registerFrame()
	{
		JFrame frame = new JFrame();
		frame.setTitle("New Customer");
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		
		JPanel pnlMain = new ImagePanel2();
		pnlMain.setBounds(0, 0, 800, 600);
		pnlMain.setLayout(null);
		
		JPanel pnlSecond = new JPanel();
		pnlSecond.setBackground(new Color(0, 0, 0, 150));
		pnlSecond.setBounds(190, 50, 400, 420);
		pnlSecond.setLayout(null);
		
		
		JLabel lbl_heading = new JLabel();
		lbl_heading.setText("Register");
		lbl_heading.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbl_heading.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_heading.setForeground(Color.white);
		lbl_heading.setBounds(125, 50, 150, 40);
		
		JLabel lbl_name = new JLabel();
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_name.setForeground(Color.white);
		lbl_name.setText("Name:");
		lbl_name.setBounds(50, 100, 100, 30);
		
		JTextField txt_name = new JTextField();
		txt_name.setBounds(200, 100, 150, 30);
		
		JLabel lbl_phoneNo = new JLabel();
		lbl_phoneNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_phoneNo.setForeground(Color.white);
		lbl_phoneNo.setText("Phone No:");
		lbl_phoneNo.setBounds(50, 150, 100, 30);
		
		JTextField txt_phoneNo = new JTextField();
		txt_phoneNo.setBounds(200, 150, 150, 30);
		
		JLabel lbl_address = new JLabel();
		lbl_address.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_address.setForeground(Color.white);
		lbl_address.setText("Address:");
		lbl_address.setBounds(50, 200, 100, 30);
		
		JTextField txt_address = new JTextField();
		txt_address.setBounds(200, 200, 150, 30);
		
		JLabel lbl_username = new JLabel();
		lbl_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_username.setForeground(Color.white);
		lbl_username.setText("Username:");
		lbl_username.setBounds(50, 250, 100, 30);
		
		JTextField txt_username = new JTextField();
		txt_username.setBounds(200, 250, 150, 30);
		
		JLabel lbl_password = new JLabel();
		lbl_password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_password.setForeground(Color.white);
		lbl_password.setText("Password:");
		lbl_password.setBounds(50, 300, 100, 30);
		
		JPasswordField txt_password = new JPasswordField();
		txt_password.setBounds(200, 300, 150, 30);
		
		JButton btn_register = new JButton();
		btn_register.setBackground(new Color(0,0,0));
		btn_register.setForeground(Color.white);
		btn_register.setText("Register");
		btn_register.setBounds(125, 350, 150, 30);
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				custCred = customerRegistration(txt_name.getText(), Integer.parseInt(txt_phoneNo.getText()), txt_address.getText(), txt_username.getText(),String.valueOf(txt_password.getPassword()));
				if(custCred != null)
				{
					frame.dispose();
					clientFrame(custCred.custName);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Could not register.","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		pnlSecond.add(lbl_heading);
		pnlSecond.add(lbl_name);
		pnlSecond.add(txt_name);
		pnlSecond.add(lbl_phoneNo);
		pnlSecond.add(txt_phoneNo);
		pnlSecond.add(lbl_address);
		pnlSecond.add(txt_address);
		pnlSecond.add(lbl_username);
		pnlSecond.add(txt_username);
		pnlSecond.add(lbl_password);
		pnlSecond.add(txt_password);
		pnlSecond.add(btn_register);
		pnlMain.add(pnlSecond);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
	
	void clientFrame(String name)
	{
		JFrame frame;
		CustomerServer custServer = new CustomerServer();;
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 600, 400));
		frame.setTitle("Welcome");
		frame.setBounds(0, 0, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_2 = new TestPanel();
		panel_2.setBounds(176, 11, 209, 339);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_1 = new ImagePanel();
		panel_1.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(panel_1);
			
		JLabel lblNewLabel_1 = new JLabel("Welcome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 189, 25);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 35, 189, 25);
		panel_2.add(lblNewLabel_2);
		
		JButton btn_about = new JButton();
		btn_about.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_about.setBackground(new Color(77, 30, 17));
		btn_about.setForeground(Color.white);
		btn_about.setSelected(false);
		btn_about.setBounds(10,150,189,30);
		btn_about.setText("About");
		btn_about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				aboutFrame(frame);
			}
		});
		panel_2.add(btn_about);
		
		JButton btn_homeDelivery = new JButton();
		btn_homeDelivery.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_homeDelivery.setBackground(new Color(77, 30, 17));
		btn_homeDelivery.setForeground(Color.white);
		btn_homeDelivery.setSelected(false);
		btn_homeDelivery.setBounds(10,200,189,30);
		btn_homeDelivery.setText("Home Delivery");
		btn_homeDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				HomeDelivery homeDelivery = new HomeDelivery();
				homeDelivery.homeDeliveryFrame(frame);
			}
		});
		panel_2.add(btn_homeDelivery);
		
		JButton btn_chat = new JButton();
		btn_chat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_chat.setBackground(new Color(77, 30, 17));
		btn_chat.setForeground(Color.white);
		btn_chat.setBounds(10,250,189,30);
		btn_chat.setText("Chat");
		btn_chat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				custServer.startThread();
				chatWindow(frame);
			}
		});
		panel_2.add(btn_chat);
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setBounds(10,300,189,30);
		btn_back.setText("Exit");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				custServer.t.interrupt();
				frame.dispose();
			}
		});
		panel_2.add(btn_back);
		frame.setVisible(true);
		/*JButton btn_homeDelivery;
		JButton btn_chat;
		JButton btn_back;
		JLabel lbl_heading;
		frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setVisible(true);
		
		lbl_heading = new JLabel();
		lbl_heading.setText("Welcome " + custCred.custName);
		lbl_heading.setBounds(350,100,150,30);
		
		btn_homeDelivery = new JButton();
		btn_homeDelivery.setBounds(350,150,150,30);
		btn_homeDelivery.setText("Home Delivery");
		btn_homeDelivery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				HomeDelivery homeDelivery = new HomeDelivery();
				homeDelivery.homeDeliveryFrame();
			}
		});
		
		btn_chat = new JButton();
		btn_chat.setBounds(350,200,150,30);
		btn_chat.setText("Chat");
		btn_chat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				CustomerServer custServer = new CustomerServer();
				custServer.startThread();
				chatWindow(frame);
			}
		});
		
		btn_back = new JButton();
		btn_back.setBounds(350,250,150,30);
		btn_back.setText("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
		
		frame.add(lbl_heading);
		frame.add(btn_back);
		frame.add(btn_chat);
		frame.add(btn_homeDelivery);*/
	}
	
	void aboutFrame(JFrame prevFrame)
	{
		JFrame frame;
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 600, 400));
		frame.setTitle("About");
		frame.setBounds(0, 0, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_2 = new TestPanel();
		panel_2.setBounds(176, 11, 209, 339);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_1 = new ImagePanel();
		panel_1.setBounds(0, 0, 600, 400);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Guidelines");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 189, 25);
		panel_2.add(lblNewLabel_1);
		
		JTextArea lblNewLabel_2 = new JTextArea("Please press button \nnamed Home Delivery to \nplace an order. Note that \npayment is cash on \ndelivery.\n\nPlease press Chat button \nto chat.\n\nPlease press Exit to quit \nthe application.");
		lblNewLabel_2.setEditable(false);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 40, 189, 250);
		panel_2.add(lblNewLabel_2);
		
		JButton btn_back = new JButton();
		btn_back.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_back.setBackground(new Color(77, 30, 17));
		btn_back.setForeground(Color.white);
		btn_back.setBounds(10,300,189,30);
		btn_back.setText("Back");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		panel_2.add(btn_back);
		
		frame.setVisible(true);
	}
		
	CustomerDetails customerRegistration(String name, long phoneNo, String address, String username, String password)
	{
		CustomerDetails cc = new CustomerDetails();
		cc.custName = name;
		cc.custPhoneNo = phoneNo;
		cc.custAddress = address;
		cc.custUsername = username;
		cc.custPassword = password;
		CustomerServer custServer = new CustomerServer();
		custServer.sendCredentials(cc,4);
		return cc;
	}
	
	CustomerDetails loginPage(String username, String password)
	{
		CustomerDetails cc = new CustomerDetails();
		cc.custUsername = username;
		cc.custPassword = password;	
		CustomerServer custServer = new CustomerServer();
		custServer.sendCredentials(cc,5);
		CustomerDetails custDetails = custServer.authListener();
		return custDetails;
	}
	
	public CustomerDetails customerAuthMenu()
	{
		CustomerDetails custCred = null;
		try
        {
            System.out.println("");
            System.out.println("****Hello User****");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                	//custCred = customerRegistration();
                    break;
                case 2:
                	//custCred = loginPage();
                	break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        }
        catch(InputMismatchException e)
        {
            System.out.println("Error: Wrong input type. Please try again.");
        }
		return custCred;
	}
	
	void chatWindow(JFrame prevFrame)
	{
		CustomerServer custServer = new CustomerServer(custCred, txt_chat);
		custServer.startThread();
		JFrame frame = new JFrame();
		frame.setTitle("Chat Window");
		frame.setSize(700, 450);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlMain = new TestPanel();
		pnlMain.setBounds(0, 0, 700, 450);
		pnlMain.setLayout(null);
		
		txt_chat.setBounds(10, 10, 663, 300);
		
		JLabel lbl_msg = new JLabel();
		lbl_msg.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_msg.setForeground(Color.white);
		lbl_msg.setBounds(10, 320, 75, 30);;
		lbl_msg.setText("Message: ");
		
		JTextField txt_msg = new JTextField();
		txt_msg.setBounds(80, 320, 433, 30);
		
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
				String msg = txt_msg.getText();
				custServer.sendMessage(custCred.custName, msg);
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
				custServer.t.interrupt();
				frame.dispose();
				prevFrame.setVisible(true);
			}
		});
		
		pnlMain.add(txt_chat);
		pnlMain.add(lbl_msg);
		pnlMain.add(txt_msg);
		pnlMain.add(btn_send);
		pnlMain.add(btn_back);
		frame.add(pnlMain);
		frame.setVisible(true);
	}
}
