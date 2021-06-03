package Admin;
import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String customerName;
	public int tableNo;
	public HashMap<String,Integer> orderDetails = new HashMap<String,Integer>();
	public int total;
}
