package video_Game.dataAccess.entities.weapons;

import video_Game.dataAccess.entities.abstracts.Human;
import video_Game.dataAccess.entities.abstracts.Weapon;
import video_Game.dataAccess.entities.abstracts.WeaponType;

public class Bow extends Weapon{
	public Bow(int humanAttack) {
		super(WeaponType.BOW, humanAttack);
	}
	
	public int singleArrow() {
		return (int)(super.getCombinedAttack() * 0.8);
		
	}
	public int doubleArrow() {
		return (int)(super.getCombinedAttack() * 2.5);
 	}
	
	@Override
	public int attack(int attackType) {
		if (attackType == 1) {
			return singleArrow();
		}else {
			return doubleArrow();
		}
	}
	@Override
	public String getWeaponAttackTypes() {
		return "([1] SingleArrow [2] DoubleArrow)";
	}
	

}
