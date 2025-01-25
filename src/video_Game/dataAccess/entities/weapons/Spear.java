package video_Game.dataAccess.entities.weapons;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Weapon;
import video_Game.dataAccess.entities.abstracts.WeaponType;

public class Spear extends Weapon{
	public Spear(int humanAttack) {
		super(WeaponType.SPEAR,humanAttack);
	}
	
	public int stab() {
		//combined attack:
		return (int)(super.getCombinedAttack() * 1.1);
	}
	public int thrown() {
		//combined attack:
		return (int)(super.getCombinedAttack() * 2);
	}
	@Override
	public int attack(int attackType) {
		if (attackType == 1)
			return stab();
		else if (attackType == 2)
			return thrown();
		else 
			throw new IllegalArgumentException();
	}
	@Override
	public String getWeaponAttackTypes() {
		return "([1] Stab [2] Thrown)";
	}

}
