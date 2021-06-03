package Admin;

import java.io.Serializable;

public class MenuItem implements Serializable
{
	private static final long serialVersionUID = 1L;
	String name;	
	int price;
	MenuItem(String n,int p)
	{
		name = n;
		price = p;
	}
}
