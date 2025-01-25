package video_Game.dataAccess.entities.abstracts;

import java.util.Random;

public abstract class Weapon {
	private int additionalAttack;
	private int combinedAttack;
	private WeaponType weaponType;
	
	//constructor:
	public Weapon(WeaponType weaponType, int humanAttack) {
		this.weaponType = weaponType;
		
		//random attack power:
		Random random = new Random();
        this.additionalAttack = random.nextInt(10)+10; // 10 ile 20 arasında rastgele
        
        //combined attack:
        combinedAttack = additionalAttack + humanAttack;
	}
	
	
	
	public abstract int attack(int attackType);
	public abstract String getWeaponAttackTypes();
	
	//getter-setter
	public int getCombinedAttack() {
		
		return combinedAttack;
	}

	public void setCombinedAttack(int combinedAttack) {
		this.combinedAttack = combinedAttack;
	}

	public int getAdditionalAttack() {
		return additionalAttack;
	}
	public void setAdditionalAttack(int additionalAttack) {
		this.additionalAttack = additionalAttack;
	}
	
	public String getDetails() {
		return getClass().getSimpleName()+ " with +"+additionalAttack+" attack";
	}

}
