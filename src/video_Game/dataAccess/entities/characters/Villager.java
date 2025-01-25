package video_Game.dataAccess.entities.characters;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;

public class Villager extends Human {

	public Villager(String name) {
		super(name);
	}
	public void specialAction() {
		System.out.println("Villager has no special.");
	}

}
