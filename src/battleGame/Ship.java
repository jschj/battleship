package battleGame;



public class Ship 
{
	public int length;
	public int health;
	public boolean placed;
	
	public Ship(String name)
	{
		int id;
		
		id = getShipIdByName(name);
		
		if (id == INVALID_ID) id = 6;
		
		length = getShipLengthById(id);
		health = length;
		placed = false;
	}
	
	//
	
	public static final int INVALID_ID = -1;
	
	private static final String[] shipNames = 
	{
		"Cruiser 1",
		"Destroyer 1",
		"Destroyer 2",
		"Submarine 1",
		"Submarine 2",
		"Submarine 3",
		"Water"
	};
	
	private static final int[] shipLengths = 
	{
		4,
		3,
		3,
		2,
		2,
		2,
		0
	};
	
	public static String getShipNameById(int id)
	{
		return shipNames[id];
	}
	
	public static int getShipLengthById(int id)
	{
		return shipLengths[id];
	}
	
	public static int getShipIdByName(String name)
	{
		for (int i = 0; i < shipNames.length; i++)
		{
			if (name.equals(shipNames[i])) return i;
		}
		
		return -1;
	}
}
