package battleUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import battleGame.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Button;

public class BattleUI extends JFrame 
{
	private JPanel contentPane;
	private JTextField tfCoordinateX;
	private JTextField tfCoordinateY;
	private JTextPane tpEnemyField, tpMyField;
	private JLabel lbStatus1, lbStatus2, lbHealth;
	private JButton btnAction;
	private JCheckBox cbHorizontal;
	private GameState gameState;
	private CoreGame coreGame;

	
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BattleUI frame = new BattleUI();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public BattleUI() 
	{
		setTitle("Battleship");
		setBackground(new Color(128, 128, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tpEnemyField = new JTextPane();
		tpEnemyField.setForeground(new Color(255, 0, 0));
		tpEnemyField.setBackground(new Color(128, 0, 0));
		tpEnemyField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
		tpEnemyField.setText("# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #");
		tpEnemyField.setBounds(12, 32, 256, 256);
		contentPane.add(tpEnemyField);
		
		tpMyField = new JTextPane();
		tpMyField.setForeground(new Color(72, 209, 204));
		tpMyField.setBackground(new Color(0, 0, 128));
		tpMyField.setFont(new Font("DejaVu Sans Mono", Font.PLAIN, 20));
		tpMyField.setText("# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #\n# # # # # # # # # #");
		tpMyField.setBounds(280, 32, 256, 256);
		contentPane.add(tpMyField);
		
		lbStatus1 = new JLabel("Player 1 may place his/her ships");
		lbStatus1.setFont(new Font("Dialog", Font.BOLD, 16));
		lbStatus1.setBounds(12, 8, 320, 15);
		contentPane.add(lbStatus1);
		
		tfCoordinateX = new JTextField();
		tfCoordinateX.setBounds(12, 336, 70, 19);
		contentPane.add(tfCoordinateX);
		tfCoordinateX.setColumns(10);
		
		tfCoordinateY = new JTextField();
		tfCoordinateY.setColumns(10);
		tfCoordinateY.setBounds(96, 336, 70, 19);
		contentPane.add(tfCoordinateY);
		
		btnAction = new JButton("Action");
		btnAction.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAction.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				buttonAction();
			}
		});
		btnAction.setBounds(280, 300, 256, 55);		
		contentPane.add(btnAction);
		
		cbHorizontal = new JCheckBox("Horizontal");
		cbHorizontal.setFont(new Font("Dialog", Font.BOLD, 16));
		cbHorizontal.setBackground(new Color(204, 204, 204));
		cbHorizontal.setBounds(12, 296, 154, 28);
		contentPane.add(cbHorizontal);
		
		lbStatus2 = new JLabel("...");
		lbStatus2.setFont(new Font("Dialog", Font.BOLD, 16));
		lbStatus2.setBounds(12, 367, 524, 15);
		contentPane.add(lbStatus2);
		
		lbHealth = new JLabel("Health: 16");
		lbHealth.setFont(new Font("Dialog", Font.BOLD, 16));
		lbHealth.setBounds(12, 390, 524, 15);
		contentPane.add(lbHealth);
		
		//setup game
		initGame();
		queryGameState();
		updateGUI();
	}
		
	private void initGame()
	{
		coreGame = new CoreGame();
	}
	
	private void queryGameState()
	{
		gameState = coreGame.queryGameState();
	}
	
	private void updateGUI()
	{
		if (gameState.gameState == GameState.State.P1_WON)
		{
			lbStatus1.setText("P1 has won!");
			btnAction.setEnabled(false);
		}
		else if (gameState.gameState == GameState.State.P2_WON)
		{
			lbStatus1.setText("P2 has won!");
			btnAction.setEnabled(false);
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_CRUISER_1)
		{
			lbStatus1.setText("P1 shall place Cruiser_1");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_DESTROYER_1)
		{
			lbStatus1.setText("P1 shall place Destroyer 1");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_DESTROYER_2)
		{
			lbStatus1.setText("P1 shall place Destroyer 3");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_SUBMARINE_1)
		{
			lbStatus1.setText("P1 shall place Submarine 1");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_SUBMARINE_2)
		{
			lbStatus1.setText("P1 shall place Submarine 2");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_SUBMARINE_3)
		{
			lbStatus1.setText("P1 shall place Submarine 3");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P1_SHIPS_PLACED)
		{
			lbStatus1.setText("P1 placed all ship, switch turns");
			btnAction.setText("Switch");
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_CRUISER_1)
		{
			lbStatus1.setText("P2 shall place Cruiser_1");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_DESTROYER_1)
		{
			lbStatus1.setText("P2 shall place Destroyer 1");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_DESTROYER_2)
		{
			lbStatus1.setText("P2 shall place Destroyer 3");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_SUBMARINE_1)
		{
			lbStatus1.setText("P2 shall place Submarine 1");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_SUBMARINE_2)
		{
			lbStatus1.setText("P2 shall place Submarine 2");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_SUBMARINE_3)
		{
			lbStatus1.setText("P2 shall place Submarine 3");
			btnAction.setText("Place");
		}
		else if (gameState.gameState == GameState.State.P2_SHIPS_PLACED)
		{
			cbHorizontal.setEnabled(false);
			lbStatus1.setText("P2 placed all ships, switch turns");
			btnAction.setText("Switch");
		}
		else if (gameState.gameState == GameState.State.P1_ATTACK)
		{
			cbHorizontal.setEnabled(false);
			lbStatus1.setText("P1 shall attack");
			btnAction.setText("Attack");
		}
		else if (gameState.gameState == GameState.State.P1_ATTACK_TRY_AGAIN)
		{
			lbStatus1.setText("P1 shall try to attack again");
			btnAction.setText("Attack");
		}
		else if (gameState.gameState == GameState.State.P1_ATTACK_SWITCH)
		{
			lbStatus1.setText("P1's attack finished, switch turns");
			btnAction.setText("Switch");
		}
		else if (gameState.gameState == GameState.State.P2_ATTACK)
		{
			lbStatus1.setText("P2 shall attack");
			btnAction.setText("Attack");
		}
		else if (gameState.gameState == GameState.State.P2_ATTACK_TRY_AGAIN)
		{
			lbStatus1.setText("P2 shall try to attack again");
			btnAction.setText("Attack");
		}
		else if (gameState.gameState == GameState.State.P2_ATTACK_SWITCH)
		{
			lbStatus1.setText("P2's attack finished, switch turns");
			btnAction.setText("Switch");
		}
		
		if (gameState.status == GameState.Status.NO_ERROR)
		{
			lbStatus2.setText("...");
		}
		else if (gameState.status == GameState.Status.COORDINATE_OUT_OF_BOUNDS)
		{
			lbStatus2.setText("Coordinate is out of bounds, try again!");
		}
		else if (gameState.status == GameState.Status.ATTACK_CELL_ALREADY_ATTACKED)
		{
			lbStatus2.setText("Cell was already attacked, try again!");
		}
		else if (gameState.status == GameState.Status.ATTACK_CELL_HIT_WATER)
		{
			lbStatus2.setText("You hit water, switch turn!");
		}
		else if (gameState.status == GameState.Status.ATTACK_CELL_HIT_SHIP)
		{
			lbStatus2.setText("You hit a ship, shoot again!");
		}
		else if (gameState.status == GameState.Status.ATTACK_CELL_SHIP_DESTROYED)
		{
			lbStatus2.setText("You destroyed a ship, switch turn!");
		}
		else if (gameState.status == GameState.Status.CELL_OCCUPIED)
		{
			lbStatus2.setText("Some cells are occupied, try another configuration!");
		}
		
		String myField = "", enemyField = "";
		
		for (int y = 0; y < 10; y++)
		{
			for (int x = 0; x < 10; x++)
			{
				if (coreGame.isMyCellHit(x, y))
				{
					myField = myField + " X";
				}
				else
				{
					int id = coreGame.getMyCellId(x, y);
					
					if (id == 6)
					{
						myField = myField + " " + "#";
					}
					else
					{
						myField = myField + " " + id;
					}
				}
				
				if (coreGame.isEnemyCellHit(x, y))
				{
					if (coreGame.getEnemyCellId(x, y) == 6)
					{
						enemyField = enemyField + " O";
					}
					else
					{
						enemyField = enemyField + " X";
					}
				}
				else
				{
					enemyField = enemyField + " ?";
				}
			}
			
			myField += "\n";
			enemyField += "\n";
		}
		
		tpMyField.setText(myField);
		tpEnemyField.setText(enemyField);
		lbHealth.setText("Health: " + coreGame.getFullHealth());
	}
	
	private void buttonAction()
	{
		int x, y;
		boolean hor;
		
		x = Integer.parseInt(tfCoordinateX.getText());
		y = Integer.parseInt(tfCoordinateY.getText());
		hor = cbHorizontal.isSelected();
		
		if (gameState.gameState == GameState.State.P1_PLACE_CRUISER_1)
		{
			coreGame.placeShip(0, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_DESTROYER_1)
		{
			coreGame.placeShip(1, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_DESTROYER_2)
		{
			coreGame.placeShip(2, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_SUBMARINE_1)
		{
			coreGame.placeShip(3, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_SUBMARINE_2)
		{
			coreGame.placeShip(4, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P1_PLACE_SUBMARINE_3)
		{
			coreGame.placeShip(5, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P1_SHIPS_PLACED)
		{
			coreGame.switchTurns();
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_CRUISER_1)
		{
			coreGame.placeShip(0, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_DESTROYER_1)
		{
			coreGame.placeShip(1, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_DESTROYER_2)
		{
			coreGame.placeShip(2, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_SUBMARINE_1)
		{
			coreGame.placeShip(3, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_SUBMARINE_2)
		{
			coreGame.placeShip(4, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P2_PLACE_SUBMARINE_3)
		{
			coreGame.placeShip(5, x, y, hor);
		}
		else if (gameState.gameState == GameState.State.P2_SHIPS_PLACED)
		{
			coreGame.switchTurns();
		}
		else if (gameState.gameState == GameState.State.P1_ATTACK)
		{
			coreGame.attack(x, y);
		}
		else if (gameState.gameState == GameState.State.P1_ATTACK_TRY_AGAIN)
		{
			coreGame.attack(x, y);
		}
		else if (gameState.gameState == GameState.State.P1_ATTACK_SWITCH)
		{
			coreGame.switchTurns();
		}
		else if (gameState.gameState == GameState.State.P2_ATTACK)
		{
			coreGame.attack(x, y);
		}
		else if (gameState.gameState == GameState.State.P2_ATTACK_TRY_AGAIN)
		{
			coreGame.attack(x, y);
		}
		else if (gameState.gameState == GameState.State.P2_ATTACK_SWITCH)
		{
			coreGame.switchTurns();
		}
		else if (gameState.gameState == GameState.State.P1_WON)
		{
			
		}
		else if (gameState.gameState == GameState.State.P2_WON)
		{
			
		}
		
		gameState = coreGame.queryGameState();
		updateGUI();
	}
}
