package Admin;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class AdminServer implements Runnable 
{
	public Thread t;
	Menu menu;
	Order order;
	OrderGenerator orderGen;
	volatile boolean exit;
	public AdminServer(Menu ref1, OrderGenerator ref2) 
	{
		exit = false;
		menu = ref1;
		orderGen = ref2;
		t = new Thread(this);
		t.start();
	}
	
	public Thread getThread()
	{
		return t;
	}
	
	/*public void stop()
	{
		System.out.println("in stop");
		//t = null;
		//System.gc();
		exit = true;
	}*/
	
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
		System.out.print("Enter reply: ");
		Scanner sc = new Scanner(System.in);
		String msg = sc.nextLine();
		try
		{
			Socket dataSocket = new Socket("localhost", 4050);
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
		menu = menu.getMenu();
		System.out.println("Sending menu");
		try
		{
			Socket clientSocket = new Socket("localhost", 6666);
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream( ));
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
			ServerSocket connectionSocket = new ServerSocket(6668);
			Socket dataSocket = connectionSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(dataSocket.getInputStream( ));
			order = (Order) ois.readObject();
			dataSocket.close();
			connectionSocket.close();
		}
		catch(Exception e){e.printStackTrace();}
		if(order != null)
		{
			System.out.println("Order recieved");
			orderGen.placeOrder(order);
			//kitchenHandeler.addNewOrder(order);
		}
	}
	
	int listener()
	{
		int choice = 0;
		try
		{
			ServerSocket connectionSocket = new ServerSocket(6662);
			Socket dataSocket = connectionSocket.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			choice = Integer.parseInt(br.readLine());
			dataSocket.close();
			connectionSocket.close();			
		}
		catch(Exception e){e.printStackTrace();}
		return choice;
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		System.out.println("Server activated");
		while(true)
		{
			int choice = listener();
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
			else
			{
				System.out.println("New message: ");
				receiveMessage();
			}
		}
		//System.out.println("Server deactivated");
	}

}
