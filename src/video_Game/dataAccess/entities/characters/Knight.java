package video_Game.dataAccess.entities.characters;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;

public class Knight extends Human {

	public Knight(String name) {
		super(name);
	}
	@Override
	public void specialAction() {
		int knightAttack = (int)(super.getAttack() * 3);
		System.out.println("Knight skip the current turn and deals 3 × attack on his next turn.");
	}
}
