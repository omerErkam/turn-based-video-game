package video_Game.dataAccess.entities.opponents;

import java.util.ArrayList;
import java.util.Random;

import video_Game.businessLayer.Turn;
import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;

public class Wolf extends Opponent{
	public Wolf(int id) {
		super(id);
	}
	
	@Override
	public void special(Human human) {
		Random random = new Random();
		//New wolf created:
		switch (random.nextInt(5)){
		case 0-> System.out.println("Wolf is added.");
		default -> System.out.println("Wolf is not added.");
		}
	}
        
}
