package Admin;

import java.io.Serializable;

public class MenuItem implements Serializable
{
	private static final long serialVersionUID = 1L;
	public String name;	
	public int price;
	MenuItem(String n,int p)
	{
		name = n;
		price = p;
	}
}
