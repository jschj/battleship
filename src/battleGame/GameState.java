/*
	Mit dieser Klasse wird der eindeutige Spielzustand an das GUI weitergegeben.
	Zudem kann der logische folgende Spielzustand ermittelt werden (falls dieser existiert).
	public enum State = aktueller Spielzug
	public enum Status = Status der letzten ausgeführten Aktion
*/

package battleGame;



public class GameState 
{
	//die einzelnen Spielzustände (unabhängig vom eigentlichen Feld), die passieren können
	public enum State
	{
		P1_PLACE_CRUISER_1,
		P1_PLACE_DESTROYER_1,
		P1_PLACE_DESTROYER_2,
		P1_PLACE_SUBMARINE_1,
		P1_PLACE_SUBMARINE_2,
		P1_PLACE_SUBMARINE_3,
		P1_SHIPS_PLACED,
		P2_PLACE_CRUISER_1,
		P2_PLACE_DESTROYER_1,
		P2_PLACE_DESTROYER_2,
		P2_PLACE_SUBMARINE_1,
		P2_PLACE_SUBMARINE_2,
		P2_PLACE_SUBMARINE_3,
		P2_SHIPS_PLACED,
		P1_ATTACK,
		P1_ATTACK_TRY_AGAIN,
		P1_ATTACK_SWITCH,
		P2_ATTACK,
		P2_ATTACK_TRY_AGAIN,
		P2_ATTACK_SWITCH,
		P1_WON,
		P2_WON
	};
	
	public enum Status
	{
		NO_ERROR,							//alles ok, der nächste Spielzug kann eingeleitet werden
		COORDINATE_OUT_OF_BOUNDS,			//die Koordinate ist außerhalb des Feldes, der Spieler ist noch einmal dran
		CELL_OCCUPIED,						//die Zelle ist bereits belegt, der Spieler ist noch einmal dran
		ATTACK_CELL_ALREADY_ATTACKED,		//die Zelle wurde bereits angegriffen, der Spieler ist noch einmal dran
		ATTACK_CELL_HIT_WATER,				//der Spieler traf Wasser, der nächste Spieler ist dran
		ATTACK_CELL_HIT_SHIP,				//der Spieler traf ein Schiff, er darf nochmal schießen
		ATTACK_CELL_SHIP_DESTROYED			//der Spieler zerstörte ein Schiff, der andere Spieler ist dran
	};
	
	public State gameState;
	public Status status;
	
	//ermitellt den nächsten logischen Spielzustand (falls es ihn gibt)
	public void nextState()
	{
		switch (gameState)
		{
		case P1_PLACE_CRUISER_1:
			gameState = State.P1_PLACE_DESTROYER_1;
			break;
		case P1_PLACE_DESTROYER_1:
			gameState = State.P1_PLACE_DESTROYER_2;
			break;
		case P1_PLACE_DESTROYER_2:
			gameState = State.P1_PLACE_SUBMARINE_1;
			break;
		case P1_PLACE_SUBMARINE_1:
			gameState = State.P1_PLACE_SUBMARINE_2;
			break;
		case P1_PLACE_SUBMARINE_2:
			gameState = State.P1_PLACE_SUBMARINE_3;
			break;
		case P1_PLACE_SUBMARINE_3:
			gameState = State.P1_SHIPS_PLACED;
			break;
		case P1_SHIPS_PLACED:
			gameState = State.P2_PLACE_CRUISER_1;
			break;
		case P2_PLACE_CRUISER_1:
			gameState = State.P2_PLACE_DESTROYER_1;
			break;
		case P2_PLACE_DESTROYER_1:
			gameState = State.P2_PLACE_DESTROYER_2;
			break;
		case P2_PLACE_DESTROYER_2:
			gameState = State.P2_PLACE_SUBMARINE_1;
			break;
		case P2_PLACE_SUBMARINE_1:
			gameState = State.P2_PLACE_SUBMARINE_2;
			break;
		case P2_PLACE_SUBMARINE_2:
			gameState = State.P2_PLACE_SUBMARINE_3;
			break;
		case P2_PLACE_SUBMARINE_3:
			gameState = State.P2_SHIPS_PLACED;
			break;
		case P2_SHIPS_PLACED:
			gameState = State.P1_ATTACK;
			break;
		case P1_ATTACK_TRY_AGAIN:
			gameState = State.P1_ATTACK;
			break;
		case P1_ATTACK_SWITCH:
			gameState = State.P2_ATTACK;
			break;
		case P2_ATTACK_TRY_AGAIN:
			gameState = State.P2_ATTACK;
			break;
		case P2_ATTACK_SWITCH:
			gameState = State.P1_ATTACK;
			break;
		}
	}
}
