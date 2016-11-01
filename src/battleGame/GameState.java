package battleGame;



public class GameState 
{
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
		NO_ERROR,
		COORDINATE_OUT_OF_BOUNDS,
		CELL_OCCUPIED,
		ATTACK_CELL_ALREADY_ATTACKED,
		ATTACK_CELL_HIT_WATER,
		ATTACK_CELL_HIT_SHIP,
		ATTACK_CELL_SHIP_DESTROYED
	};
	
	public State gameState;
	public Status status;
	
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
