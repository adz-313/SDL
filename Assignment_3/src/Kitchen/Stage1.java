package Kitchen;

public class Stage1 implements Runnable 
{
	String name;
	Thread t;
	Stage1(String name)
	{
		this.name= name;
		t = new Thread(this);
		t.start();
	}
	
	@Override
	synchronized public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("In " + name);
			Thread.sleep(5000);
			System.out.println("Finished " + name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public void startThread() {
		// TODO Auto-generated method stub
		t.start();
	}*/

}
