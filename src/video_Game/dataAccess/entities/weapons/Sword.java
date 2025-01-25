package video_Game.dataAccess.entities.weapons;

import java.util.Random;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;
import video_Game.dataAccess.entities.abstracts.WeaponType;

public class Sword extends Weapon {
	
	//constructor:
	public Sword(int humanAttack) {
		super(WeaponType.SWORD,humanAttack);
	}
	
	//methods:
	
	public int slash() {
	//combined attack damage:
		return (int)(super.getCombinedAttack());
	}
	
	public int stab() {
		Random random = new Random();
	// attack with %75 possible:
        if (random.nextInt(100) < 75) {
        }
        return (int)(super.getCombinedAttack() * 2);
		
	}

	@Override
	public int attack(int attackType) {
		if (attackType == 1)
			return slash();
		else if (attackType == 2)
			return stab();
		else 
			throw new IllegalArgumentException();
	}

	@Override
	public String getWeaponAttackTypes() {
		return "([1] Slash [2] Stab)";
	}
}
