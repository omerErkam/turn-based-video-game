package video_Game.dataAccess.entities.opponents;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;

public class Slime extends Opponent{

	public Slime(int id) {
		super(id);
	}

	@Override
	public void special(Human human) {
	int slimeAttack = human.decreasePoints(super.getAttack());
	int slimePoint = getPoint();
	slimePoint += slimeAttack;
	if (slimePoint > 150) {
		slimePoint = 150;
	}
	super.setPoint(super.getPoint());
	System.out.println(getName()+" uses absorb on "+human.getName());
	}
}