
public class HearthstoneCardObject 
{
	private String name;
	private int cost;
	private int attack;
	private int defense;
	
	
	public HearthstoneCardObject(String name, int cost, int attack, int defense)
	{
		this.cost = cost;
		this.attack = attack;
		this.defense = defense;
		this.name = name;
	}
	
	
	public int getCost()
	{
		return this.cost;
	}
	
	public int getAttack()
	{
		return this.attack;
	}
	
	
	
	
	public void setName(String name)
	{
		if(name.length() >= 5)
		{
			this.name = name;
		}
	}
	
	
	void display()
	{
		System.out.format("Name: %s \nCost: %d \nAttack: %d \nDefense: %d \n", this.name, this.cost, this.attack, this.defense);
	}
}

