package Admin;

import java.net.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Client.CustomerCredentials;

import java.io.*;

public class AdminServer implements Runnable 
{
	public Thread t;
	Menu menu;
	Order order;
	OrderGenerator orderGen;
	CustomerHandler custHandler;
	CustomerDetails custDetails;
	boolean exit;
	HashMap<String, Integer> portList = new HashMap<String, Integer>();
	int initPort;
	
	public AdminServer(Menu ref1, OrderGenerator ref2, CustomerHandler ref3) 
	{
		initPort = 6000;
		menu = ref1;
		orderGen = ref2;
		custHandler = ref3;
		exit = false;
		t = new Thread(this);
		t.start();
	}
	
	public void stopThread()
	{
		exit = true;
	}
	
	public Thread getThread()
	{
		return t;
	}
	
	public void receiveMessage()
	{
		try
		{
			ServerSocket connectionSocket = new ServerSocket(5000);
			Socket dataSocket = connectionSocket.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			System.out.println(br.readLine());
			dataSocket.close();
			connectionSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void sendMessage()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter customer name: ");
		String name = sc.nextLine();
		int port = portList.get(name);
		System.out.print("Enter reply: ");
		String msg = sc.nextLine();
		try
		{
			Socket dataSocket = new Socket("localhost", port);
			PrintStream socketOutput = new PrintStream(dataSocket.getOutputStream());
			socketOutput.println(msg);
		 	socketOutput.flush();
			dataSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	void sendMenu()
	{
		//menu = menu.getMenu();
		System.out.println("Sending menu");
		try
		{
			Socket clientSocket = new Socket("localhost", 5002);
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(menu);
			oos.flush();
			clientSocket.close( ); 
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	
	void getOrder()
	{
		Order order = null;
		try
		{
			ServerSocket connectionSocket = new ServerSocket(5003);
			Socket dataSocket = connectionSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(dataSocket.getInputStream());
			order = (Order) ois.readObject();
			dataSocket.close();
			connectionSocket.close();
		}
		catch(Exception e){e.printStackTrace();}
		if(order != null)
		{
			System.out.println("Order recieved");
			orderGen.placeOrder(order);
		}
	}
	
	void sendAuthStatus()
	{
		CustomerDetails ref = custHandler.authenticateCustomer(custDetails);
		if(ref != null)
		{
			System.out.println("boom");
			initPort++;
			portList.put(ref.custName, initPort);
			ref.portNo = initPort;
		}
		try
		{
			Socket dataSocket = new Socket("localhost", 5004);
			ObjectOutputStream oos = new ObjectOutputStream(dataSocket.getOutputStream());
			oos.writeObject(ref);
			oos.flush();
			dataSocket.close();
			System.out.println("Sent auth status");
		}
		catch(Exception e)
		{
			System.out.println("Could not send acknowledgement");
			return;
		}
	}
	
	int listener()
	{
		ServerSocket connectionSocket;
		int choice = 0;
		try
		{
			connectionSocket = new ServerSocket(5005);
			connectionSocket.setSoTimeout(10000);
			Socket dataSocket = connectionSocket.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			choice = Integer.parseInt(br.readLine());
			dataSocket.close();
			connectionSocket.close();			
		}
		catch(Exception e)
		{
			connectionSocket = null;
			System.gc();
			return -1;
		}
		return choice;
	}
	
	void getCustomer()
	{
		CustomerDetails cust = null;
		try
		{
			ServerSocket connectionSocket = new ServerSocket(5006);
			Socket dataSocket = connectionSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(dataSocket.getInputStream());
			cust = (CustomerDetails) ois.readObject();
			dataSocket.close();
			connectionSocket.close();
		}
		catch(Exception e){e.printStackTrace();}
		custDetails = cust;
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		System.out.println("Server activated");
		while(!Thread.interrupted())
		{
			int choice = listener();
			if(choice == -1)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
			}
			if(choice == 1)
			{
				System.out.println("Menu acknowledgement recieved.");
				sendMenu();
			}
			else if(choice == 2)
			{
				System.out.println("Order acknowledgement recieved.");
				getOrder();
			}
			else if(choice == 3)
			{
				System.out.println("New message: ");
				receiveMessage();
			}
			else if(choice == 4)
			{
				System.out.println("New client online.");
				getCustomer();
				try {
					custHandler.addCustomer(custDetails);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(choice == 5)
			{
				System.out.println("New client online.");
				getCustomer();
				sendAuthStatus();
			}
		}
		System.out.println("Server deactivated");
	}

}
