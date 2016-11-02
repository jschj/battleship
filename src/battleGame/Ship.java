/*
	Diese Klasse enthält grundlegende Informationen über jeden Schifftypen.
*/

package battleGame;



public class Ship 
{
	//die festgelegten ID's der Schiffe, die Konform mit den Zellen auf dem Schlachtfeld 
	//und dem ship-Array in der Klasse Battlefield sind
	public static final int ID_CRUISER_1 = 0,
		ID_DESTROYER_1 = 1,
		ID_DESTROYER_2 = 2,
		ID_SUBMARINE_1 = 3,
		ID_SUBMARINE_2 = 4,
		ID_SUBMARINE_3 = 5,
		ID_WATER = 6;
	
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
		
	public static int getShipLengthById(int id)
	{
		return shipLengths[id];
	}
		
	//hier beginnt die eigentliche Klasse, die Informationen über das jeweilige Schiff speichert (nicht-statisch)	
	
	public int length;
	public int health;
	public boolean placed;
	
	public Ship(int id)
	{
		length = shipLengths[id];
		health = length;
		placed = false;
	}	
}
