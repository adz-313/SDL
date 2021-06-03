package Admin;
import java.io.Serializable;
import java.util.*;

public class Menu implements Serializable
{
	private static final long serialVersionUID = 1L;
	public ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
	public Menu getMenu() {
		// TODO Auto-generated method stub
		return this;
	}
	public void display()
	{
		System.out.println("****Food Menu****");
		System.out.println("Item Name    Price");
		for(MenuItem m : menu)
		{
			System.out.println(m.name + " " + m.price);
		}
	}
}
