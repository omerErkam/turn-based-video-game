package video_Game.dataAccess.entities.characters;


import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;

public class Hunter extends Human {

	public Hunter(String name) {
		super(name);
	}
	@Override
	public void specialAction() {
		int hunterAttack = (int)(super.getAttack() * 0.5);
		System.out.println("Hunter attack for 0.5 × attack in the current turn and have two turns back-to- back.");
		
	}
	
}
