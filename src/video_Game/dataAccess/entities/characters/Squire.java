package video_Game.dataAccess.entities.characters;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;

public class Squire extends Human {

	public Squire(String name) {
		super(name);
	}
	public void specialAction() {
		int squireAttack = (int)(super.getAttack() * 0.5);
		int squireStamina = (int) (super.getStamina());
		squireStamina += squireAttack;
		System.out.println("Squire attacks for 0.5 × attack in the current turn and increase his stamina to 10.");
	}
}
