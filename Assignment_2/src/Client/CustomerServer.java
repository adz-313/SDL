package Client;

import java.net.*;
import java.util.Scanner;

import Admin.Menu;
import Admin.Order;

import java.io.*;


public class CustomerServer implements Runnable
{
	Thread t;
	
	public CustomerServer() 
	{
		// TODO Auto-generated constructor stub
		t = new Thread(this);
	}
	
	public void startThread()
	{
		t.start();
	}
	
	void sendAcknowledgement(int ack)
	{
		try
		{
			Socket dataSocket = new Socket("localhost", 6662); 
			PrintStream ps = new PrintStream(dataSocket.getOutputStream());
			ps.println(ack);
			ps.flush();
			dataSocket.close();
			System.out.println("Sent acknowledgement");
		}
		catch(Exception e){}
	}
	
	public Menu getMenu()
	{
		Menu menu = null;
		try
		{
			sendAcknowledgement(1);
			ServerSocket connectionSocket = new ServerSocket(6666);
			Socket dataSocket = connectionSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(dataSocket.getInputStream( ));
			menu = (Menu) ois.readObject();
			dataSocket.close();
			connectionSocket.close();
		}
		catch(Exception e){e.printStackTrace();}
		if(menu != null)
		{
			return menu;
		}
		else
		{
			System.out.println("Error");
			return null;
		}
	}
	
	public void sendOrder(Order order)
	{
		try
		{
			sendAcknowledgement(2);
			Socket dataSocket = new Socket("localhost", 6668);
			ObjectOutputStream oos = new ObjectOutputStream(dataSocket.getOutputStream( ));
			oos.writeObject(order);
			oos.flush();
			dataSocket.close(); 			
		}
		catch(Exception e) {}
	}
	
	public void sendMessage(String name)
	{
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String str = sc.nextLine();
			if(str.equals("exit"))
			{
				return;
			}
			String msg = name + ": " + str;
			try
			{
				sendAcknowledgement(3);
				Socket dataSocket = new Socket("localhost", 5000);
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
	}
	
	public void receiveMessage()
	{
		try
		{
			ServerSocket connectionSocket = new ServerSocket(4050);
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
	
	public void run()
	{
		while(true)
		{
			receiveMessage();	
		}
	}
}
