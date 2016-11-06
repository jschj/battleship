package battleGame;

import javax.swing.*;



public class GameInterface 
{
	protected JButton btnAction;
	protected JTextPane tpEnemyField;
	protected JTextPane tpMyField;
	protected JCheckBox cbHorizontal;
	protected JTextField tfCoordinateX;
	protected JTextField tfCoordinateY;
	protected JLabel lbStatus1;
	protected JLabel lbStatus2;
	protected JLabel lbHealth;
	
	
	
	public GameInterface(JButton action,
		JTextPane enemyField,
		JTextPane myField,
		JCheckBox horizontal,
		JTextField coordinateX,
		JTextField coordinateY,
		JLabel status1,
		JLabel status2,
		JLabel health)
	{
		btnAction = action;
		tpEnemyField = enemyField;
		tpMyField = myField;
		cbHorizontal = horizontal;
		tfCoordinateX = coordinateX;
		tfCoordinateY = coordinateY;
		lbStatus1 = status1;
		lbStatus2 = status2;
		lbHealth = health;
	}
}
