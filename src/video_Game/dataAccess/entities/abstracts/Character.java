package video_Game.dataAccess.entities.abstracts;

import video_Game.businessLayer.Turn;

public interface Character<W extends Weapon> {
	
	int punch(Opponent opponent, Turn turn);
	int attackWithWeapon(Opponent opponent,Turn turn, int attackType);
	boolean guard();
	void run();
	void specialAction();
	String getName();
	int getSpeed();
	String getDetails();
	String getStats();
	int decreasePoints(int damageAmount);
	W getWeapon();
}
	
