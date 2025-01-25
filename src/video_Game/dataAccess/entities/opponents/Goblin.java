package video_Game.dataAccess.entities.opponents;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;

public class Goblin extends Opponent {
		
	public Goblin(int id) {
		super(id);
	}

	public void special(Human human) {
		System.out.println(getName()+" uses rushing attack on "+human.getName());
	}
	

}
