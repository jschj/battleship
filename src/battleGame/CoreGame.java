/*
	Die eigentliche Spiellogik wird hier behandelt.
	Nach jeder spielrelevanten Aktion wird der Spielzustand (gameState) neu berechnet.
	P1 fängt an, im Vertrauen, dass die Spieler ihr Alter korrekt ermittelt haben.
*/

package battleGame;



public class CoreGame 
{
	private static final int P1 = 0,
							 P2 = 1;
	
	private Battlefield p1Field, p2Field;
	private int turn;
	private GameState gameState;
	
	public CoreGame()
	{
		p1Field = new Battlefield();
		p2Field = new Battlefield();
		gameState = new GameState();
		
		gameState.gameState = GameState.State.P1_PLACE_CRUISER_1;
	}
	
	public void switchTurns()
	{
		if (gameState.gameState != GameState.State.P1_SHIPS_PLACED &&
			gameState.gameState != GameState.State.P2_SHIPS_PLACED &&
			gameState.gameState != GameState.State.P1_ATTACK_SWITCH &&
			gameState.gameState != GameState.State.P2_ATTACK_SWITCH)
		{
			return;
		}
		
		if (turn == P1)
		{
			turn = P2;
		}
		else
		{
			turn = P1;
		}
		
		gameState.nextState();
		gameState.status = GameState.Status.NO_ERROR;
	}
	
	public void placeShip(int id, int x, int y, boolean horizontal)
	{
		boolean result;
		
		if (turn == P1)
		{
			result = p1Field.placeShip(id, x, y, horizontal);
		}
		else
		{
			result = p2Field.placeShip(id, x, y, horizontal);
		}
		
		//wenn das Schiff plaziert wurde, wird der nächste Spielzustand eingeleitet
		if (result)
		{
			gameState.nextState();
		}
	}
	
	public void attack(int x, int y)
	{
		GameState.Status result;
		
		if (turn == P2)
		{
			if (!p1Field.isCoordinateInbound(x, y)) 
			{
				result = GameState.Status.COORDINATE_OUT_OF_BOUNDS;
			}
			else if (p1Field.isCellHit(x, y)) 
			{
				result = GameState.Status.ATTACK_CELL_ALREADY_ATTACKED;
			}
			else if (p1Field.getCellId(x, y) == Ship.ID_WATER) 
			{
				result = GameState.Status.ATTACK_CELL_HIT_WATER;
				p1Field.setCellHit(x, y, true);
			}
			else
			{
				p1Field.setCellHit(x, y, true);
				p1Field.doDamageToShip(p1Field.getCellId(x, y));
		
				if (p1Field.getShipHealth(p1Field.getCellId(x, y)) == 0) 
				{
					result = GameState.Status.ATTACK_CELL_SHIP_DESTROYED;
				}
				else
				{
					result = GameState.Status.ATTACK_CELL_HIT_SHIP;
				}
			}
		}
		else
		{
			if (!p2Field.isCoordinateInbound(x, y)) 
			{
				result = GameState.Status.COORDINATE_OUT_OF_BOUNDS;
			}
			else if (p2Field.isCellHit(x, y)) 
			{
				result = GameState.Status.ATTACK_CELL_ALREADY_ATTACKED;
			}
			else if (p2Field.getCellId(x, y) == Ship.ID_WATER) 
			{
				result = GameState.Status.ATTACK_CELL_HIT_WATER;
				p2Field.setCellHit(x, y, true);
			}
			else
			{
				p2Field.setCellHit(x, y, true);
				p2Field.doDamageToShip(p2Field.getCellId(x, y));
		
				if (p2Field.getShipHealth(p2Field.getCellId(x, y)) == 0) 
				{
					result = GameState.Status.ATTACK_CELL_SHIP_DESTROYED;
				}
				else
				{
					result = GameState.Status.ATTACK_CELL_HIT_SHIP;
				}
			}
		}
		
		if (result == GameState.Status.COORDINATE_OUT_OF_BOUNDS || 
			result == GameState.Status.ATTACK_CELL_ALREADY_ATTACKED ||
			result == GameState.Status.ATTACK_CELL_HIT_SHIP)
		{
			//nothing, try again
			
			if (turn == P1)
			{
				gameState.gameState = GameState.State.P1_ATTACK_TRY_AGAIN;
			}
			else
			{
				gameState.gameState = GameState.State.P2_ATTACK_TRY_AGAIN;
			}
		}
		else if (result == GameState.Status.ATTACK_CELL_HIT_WATER ||
				 result == GameState.Status.ATTACK_CELL_SHIP_DESTROYED)
		{
			//change state
			
			if (turn == P1)
			{
				gameState.gameState = GameState.State.P1_ATTACK_SWITCH;
			}
			else
			{
				gameState.gameState = GameState.State.P2_ATTACK_SWITCH;
			}
		}
		
		gameState.status = result;
		
		checkWin();
	}
	
	private void checkWin()
	{
		if (p1Field.getFullHealth() == 0)
		{
			gameState.gameState = GameState.State.P2_WON;
			gameState.status = GameState.Status.NO_ERROR;
		}
		else if (p2Field.getFullHealth() == 0)
		{
			gameState.gameState = GameState.State.P1_WON;
			gameState.status = GameState.Status.NO_ERROR;
		}
	}
	
	//adaptiert die ganzen Methoden aus der Klasse Battlefield für den jeweiligen Spieler
	
	public int getFullHealth()
	{
		if (turn == P1)
		{
			return p1Field.getFullHealth();
		}
		
		return p2Field.getFullHealth();
	}
	
	public boolean isEnemyCellHit(int x, int y)
	{
		if (turn == P1)
		{
			return p2Field.isCellHit(x, y);
		}
		
		return p1Field.isCellHit(x, y);
	}
	
	public boolean isMyCellHit(int x, int y)
	{
		if (turn == P1)
		{
			return p1Field.isCellHit(x, y);
		}
		
		return p2Field.isCellHit(x, y);
	}
	
	public int getEnemyCellId(int x, int y)
	{
		if (turn == P1)
		{
			return p2Field.getCellId(x, y);
		}
		
		return p1Field.getCellId(x, y);
	}
	
	public int getMyCellId(int x, int y)
	{
		if (turn == P1)
		{
			return p1Field.getCellId(x, y);
		}
		
		return p2Field.getCellId(x, y);
	}
	
	//gibt den eindeutigen Spielzustand zurück
	
	public GameState queryGameState()
	{
		return gameState;
	}
}
