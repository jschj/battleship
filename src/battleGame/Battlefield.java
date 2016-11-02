/*
	Diese Klasse übernimmt die meisten Aufgaben der Interaktion mit dem Spielfeld.
*/

package battleGame;



public class Battlefield 
{
	//Die Bit-Masken, um aus einer einzelnen Zelle (int) die Informationen über ID und Treffer zu extrahieren
	private static final int CELL_ID_MASK = 0x000000FF,
		CELL_HIT_MASK = 0x0000FF00;
	
	//Das eigentliche Feld ist ein int-Array, die Bits 0-8 enthalten die ID
	//Bit 8 ist 1, wenn das Feld schon einmal angegriffen wurde
	private int[][] field;
	
	//Das ships-Array wird benötigt, um zuammenhängende Schiffe über mehrere Zellen einfacher zu beschreiben
	//und eine Zerstörung eines einzelnen Schiffes leichter nachzuvollziehen
	private Ship[] ships;
	
	//Der Konstruktor initilisiert das Feld und die Schiffe
	public Battlefield()
	{
		ships = new Ship[7];		
		field = new int[10][10];

		resetField();
		resetShips();
		
		placeShip(0, 0, 0, false);
		placeShip(1, 1, 0, false);
		placeShip(2, 2, 0, false);
		placeShip(3, 3, 0, false);
		placeShip(4, 4, 0, false);
		placeShip(5, 5, 0, false);
	}
	
	//setzt jede Zelle auf "Wasser"
	public void resetField()
	{
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 10; y++)
			{
				field[x][y] = Ship.ID_WATER;
			}
		}
	}
	
	//(Re)Initilisiert alle Schiffe, weist ihnen die standardmäßigen Grundeigenschaften zu
	public void resetShips()
	{
		ships[0] = new Ship(0);		//Kreuzer
		ships[1] = new Ship(1);		//Zerstörer 1
		ships[2] = new Ship(2);		//Zerstörer 2
		ships[3] = new Ship(3);		//U-Boot 1
		ships[4] = new Ship(4);		//U-Boot 2
		ships[5] = new Ship(5);		//U-Boot 3
		ships[6] = new Ship(6);		//Wasser
	}
	
	//Die folgenden Methoden arbeiten mit Bit-Logik, sind aber selbsterklärend.
	public boolean isCoordinateInbound(int x, int y)
	{
		return x >= 0 && x < 10 && y >= 0 && y < 10;
	}
	
	public boolean isCellHit(int x, int y)
	{
		return (field[x][y] & CELL_HIT_MASK) == 0x00000100;
	}
	
	public int getCellId(int x, int y)
	{
		return field[x][y] & CELL_ID_MASK;
	}
	
	public void setCellHit(int x, int y, boolean hit)
	{
		if (hit)
		{
			field[x][y] |= 0x00000100;
			return;
		}
		
		field[x][y] &= 0xFFFFFEFF;
	}
	
	public void setCellId(int x, int y, int id)
	{
		field[x][y] = (field[x][y] & 0xFFFFFF00) | id;
	}
	
	public int getFullHealth()
	{
		int hp = 0;
		
		for (int i = 0; i < 6; i++)
		{
			hp += ships[i].health;
		}
		
		return hp;
	}
	
	public int getCell(int x, int y)
	{
		return field[x][y];
	}
	
	public int getShipLength(int id)
	{
		return ships[id].length;
	}
	
	public int getShipHealth(int id)
	{
		return ships[id].health;
	}
	
	public void doDamageToShip(int id)
	{
		ships[id].health--;
	}
	
	//Überprüft, ob ein Schiff in der über die Parameter angegebenen Konfiguration plazierbar ist.
	//Wurde das Schiff bereits gesetzt?
	//Sind Anfangs- und Endkoordinate innerhalb des Feldes?
	//Kollidiert es mit anderen Schiffen?
	//
	public boolean isShipPlaceable(int shipId, int x, int y, boolean horizontal)
	{
		if (ships[shipId].placed) 
		{
			return false;
		}
		
		if (horizontal)
		{
			if (!isCoordinateInbound(x, y) || !isCoordinateInbound(x + ships[shipId].length, y)) return false;
		
			for (int px = x; px < x + ships[shipId].length; px++)
			{
				if (getCellId(px, y) != Ship.ID_WATER) 
				{
					return false;
				}
			}
			
			return true;
		}
		
		if (!isCoordinateInbound(x, y) || !isCoordinateInbound(x, y + ships[shipId].length)) return false;
		
		for (int py = y; py < y + ships[shipId].length; py++)
		{
			if (getCellId(x, py) != Ship.ID_WATER) 
			{
				return false;
			}
		}
		
		return true;
	}
	
	//Versucht das angegebene Schiff zu plazieren
	public boolean placeShip(int shipId, int x, int y, boolean horizontal)
	{
		if (!isShipPlaceable(shipId, x, y, horizontal)) 
		{
			return false;
		}
		
		if (horizontal)
		{
			for (int px = x; px < x + ships[shipId].length; px++)
			{
				setCellId(px, y, shipId);
			}
			
			return true;
		}
		
		for (int py = y; py < y + ships[shipId].length; py++)
		{
			setCellId(x, py, shipId);
		}
		
		ships[shipId].placed = true;
		
		return true;
	}

	//exportiert die Methoden aus getCellId(x, y) und isCellHit(x, y), um sie ggf. unabhängig von dem eigentlichen
	//Spielfeld zu benutzen
	
	public static int getId(int cell)
	{
		return cell & CELL_ID_MASK;
	}
	
	public static boolean isHit(int cell)
	{
		return (cell & CELL_HIT_MASK) == 0x00000100;
	}
}
