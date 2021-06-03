package Kitchen;

import java.util.concurrent.*;

import Admin.Order;

public class KitchenView implements Runnable
{
	Order order;
	Thread t;
	public KitchenView(Order ref)
	{
		order = ref;
		t = new Thread(this);
		//t.start();
		executeOrder();
	}
	
	void executeOrder()
	{
		Stage1 t1 = new Stage1("Stage1"); 
		Stage1 t2 = new Stage1("Stage2");
		Stage1 t3 = new Stage1("Stage3");
		/*ExecutorService pool = Executors.newFixedThreadPool(3);
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.shutdown();*/
		System.out.println("Order of " + order.customerName + " is complete.");
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}
}
