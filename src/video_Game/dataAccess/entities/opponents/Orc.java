package video_Game.dataAccess.entities.opponents;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;

public class Orc extends Opponent{

	public Orc(int id) {
		super(id);
	}

	@Override
	public void special(Human human) {
		System.out.println(getName()+" uses heavy hit on "+human.getName());
		
	}
}
