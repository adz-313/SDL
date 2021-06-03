package Kitchen;

import java.util.concurrent.*;

import javax.swing.JTextArea;

import Admin.Order;

public class KitchenView implements Runnable
{
	Order order;
	String taskName;
	JTextArea txt_kv;
	KitchenView(String tn, JTextArea ta)
	{
		taskName = tn;
		txt_kv = ta;
	}
	public KitchenView(Order ref, JTextArea ta)
	{
		order = ref;
		txt_kv = ta;
		executeOrder();
	}
	public KitchenView(String tn, Order ref, JTextArea ta)
	{
		order = ref;
		txt_kv = ta;
		taskName = tn;
	}
	void executeOrder()
	{
		Runnable t1 = new KitchenView("Stage1",txt_kv); 
		Runnable t2 = new KitchenView("Stage2",txt_kv);
		Runnable t3 = new KitchenView("Stage3",order,txt_kv);
		ExecutorService pool = Executors.newFixedThreadPool(3);
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.shutdown();
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		try
        { 
            for(int i=0; i<5; i++)
            {
            	if(taskName.equals("Stage1"))
            	{
            		Thread.sleep(1000);
            		
            	}
            	else if(taskName.equals("Stage2"))
            	{
            		Thread.sleep(2000);
            	}
            	else
            	{
            		Thread.sleep(3000);
            	}
            }
            txt_kv.append(taskName + " is complete.\n");
            if(taskName.equals("Stage3"))
        	{
            	txt_kv.append("Order of " + order.customerName + " is complete.\n");
        	}
        } 
          
        catch(InterruptedException e) 
        { 
            e.printStackTrace(); 
        } 
	}
}
