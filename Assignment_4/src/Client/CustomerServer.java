package Client;

import java.net.*;
import java.util.Scanner;

import javax.swing.JTextArea;

import Admin.CustomerDetails;
import Admin.Menu;
import Admin.Order;

import java.io.*;


public class CustomerServer implements Runnable
{
	public Thread t;
	CustomerDetails custDetails;
	JTextArea txt_chat;
	
	public CustomerServer() 
	{
		// TODO Auto-generated constructor stub
		t = new Thread(this);
	}
	
	public CustomerServer(CustomerDetails ref, JTextArea jt) 
	{
		// TODO Auto-generated constructor stub
		custDetails = ref;
		txt_chat = jt;
		t = new Thread(this);
	}
	
	public void startThread()
	{
		t.start();
	}
	
	CustomerDetails authListener()
	{
		ServerSocket connectionSocket;
		CustomerDetails custDetails = null;
		try
		{
			connectionSocket = new ServerSocket(5004);
			//connectionSocket.setSoTimeout(20000);
			Socket dataSocket = connectionSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(dataSocket.getInputStream());
			custDetails = (CustomerDetails) ois.readObject();
			dataSocket.close();
			connectionSocket.close();			
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		return custDetails;
	}
	
	void sendAcknowledgement(int ack)
	{
		try
		{
			Socket dataSocket = new Socket("localhost", 5005);
			PrintStream ps = new PrintStream(dataSocket.getOutputStream());
			ps.println(ack);
			ps.flush();
			dataSocket.close();
			System.out.println("Sent acknowledgement");
		}
		catch(Exception e)
		{
			System.out.println("Could not send acknowledgement");
			return;
		}
	}
	
	public Menu getMenu()
	{
		Menu menu = null;
		try
		{
			sendAcknowledgement(1);
			ServerSocket connectionSocket = new ServerSocket(5002);
			Socket dataSocket = connectionSocket.accept();
			ObjectInputStream ois = new ObjectInputStream(dataSocket.getInputStream());
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
			Socket dataSocket = new Socket("localhost", 5003);
			ObjectOutputStream oos = new ObjectOutputStream(dataSocket.getOutputStream());
			oos.writeObject(order);
			oos.flush();
			dataSocket.close(); 			
		}
		catch(Exception e) {}
	}
	
	public void sendMessage(String name, String str)
	{
		String msg = "[" + name + "]" + ": " + str + "\n";
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
	
	public void receiveMessage()
	{
		ServerSocket connectionSocket;
		try
		{
			//System.out.println("yolo");
			connectionSocket = new ServerSocket(custDetails.portNo);
			connectionSocket.setSoTimeout(5000);
			Socket dataSocket = connectionSocket.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			txt_chat.append("[Admin] : " + br.readLine() + "\n");
			dataSocket.close();
			connectionSocket.close();
		}
		catch(Exception e)
		{
			connectionSocket = null;
			System.gc();
			return;
		}
	}
	
	public void sendCredentials(CustomerDetails custCred,int custAck)
	{
		try
		{
			sendAcknowledgement(custAck);
			Socket dataSocket = new Socket("localhost", 5006);
			dataSocket.setSoTimeout(10000);
			ObjectOutputStream oos = new ObjectOutputStream(dataSocket.getOutputStream());
			oos.writeObject(custCred);
			oos.flush();
			dataSocket.close(); 			
		}
		catch(Exception e) {}
	}
	
	public void run()
	{
		while(!Thread.interrupted())
		{
			receiveMessage();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}
}
