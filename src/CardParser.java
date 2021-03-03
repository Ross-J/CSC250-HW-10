import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CardParser 
{
	private String urlString;
	private ArrayList<HearthstoneCardObject> theMinions;

	
	public CardParser(String urlString)
	{
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCardObject>();

		URLReader hearthstoneURLReader = new URLReader(this.urlString);

		Object obj = JSONValue.parse(hearthstoneURLReader.gettheURLContents());

		if(obj instanceof JSONArray)
		{
			JSONArray array = (JSONArray)obj;
			
			for(int i = 0; i < array.size(); i++)
			{
				JSONObject cardData = (JSONObject)array.get(i);
				
		    	if(cardData.containsKey("cost") && cardData.containsKey("name"))
		    	{
		    		if(cardData.containsKey("type") && cardData.get("type").equals("MINION"))
		    		{
			    		String name = (String)cardData.get("name");
			    		int cost = Integer.parseInt(cardData.get("cost").toString());
			    		int attack = Integer.parseInt(cardData.get("attack").toString());
			    		int health = Integer.parseInt(cardData.get("health").toString());
			    		HearthstoneCardObject temp = new HearthstoneCardObject(name, cost, attack, health);
			    		theMinions.add(temp);
		    		}
		    	}
			}		
		}
	}
	
	public void showMinions()
	{
		for(int i = 0; i < this.theMinions.size(); i++)
		{
			this.theMinions.get(i).display();
		}
	}
	

	
	
	public void selectionSortHighestAttackToLowestAttack()
	{
		for(int max = 0; max < theMinions.size(); max++)
		{
			int maxIndex = this.findIndexOfCardWithLargestAttackFromPosition(max);
			HearthstoneCardObject temp = this.theMinions.get(maxIndex);
			this.theMinions.set(maxIndex,  this.theMinions.get(max));
			this.theMinions.set(max, temp);
		}
	}
	
	private int findIndexOfCardWithLargestAttackFromPosition(int pos)
	{
		HearthstoneCardObject currWinner = this.theMinions.get(pos);
		int indexOfWinner = pos;
		for(int i = pos; i < this.theMinions.size(); i++)
		{
			if(this.theMinions.get(i).getAttack() > currWinner.getAttack())
			{
				currWinner = this.theMinions.get(i);
				indexOfWinner = i;
			}
		}
		return indexOfWinner;
	}
	
	
	//Binary Search Algorithm
	public void binarySearchAttackValue()
	{
		this.selectionSortHighestAttackToLowestAttack();
		
		Scanner input = new java.util.Scanner(System.in);
		System.out.print("Enter an attack value to search for: ");
		int searchKey = Integer.parseInt((input).nextLine());
		
		int indexOfWinner = -1;
		int lowerIndex = 0;
		int upperIndex = theMinions.size();
		while(indexOfWinner == -1)
		{
			int middleIndex = (upperIndex / 2) + (lowerIndex / 2);
			if(searchKey < this.theMinions.get(middleIndex).getAttack())
			{
				upperIndex = middleIndex - 1;
			}
			else if(searchKey > this.theMinions.get(middleIndex).getAttack())
			{
				lowerIndex = middleIndex + 1;
			}
			else if(searchKey == this.theMinions.get(middleIndex).getAttack())
			{
				indexOfWinner = middleIndex;
			}
		}
		this.theMinions.get(indexOfWinner).display();
	}
}

