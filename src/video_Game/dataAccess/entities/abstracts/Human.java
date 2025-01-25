package video_Game.dataAccess.entities.abstracts;

import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Attributes.Name;

import video_Game.businessLayer.Turn;
import video_Game.dataAccess.entities.weapons.Bow;
import video_Game.dataAccess.entities.weapons.Spear;
import video_Game.dataAccess.entities.weapons.Sword;

public abstract class Human<W extends Weapon> implements Character<Weapon> {
	private String name;
	private int point;
	private int stamina;
	private int attack;
	private int speed;
	private boolean specialUsed = false;
	private boolean guardUsed;
	private Weapon weapon;
	

	public Human(String name) {
		super();
		this.name = name;
		stamina = 10;
		Random random = new Random();
        this.point = random.nextInt(100)+50; // Random point value 50 to 150
        this.attack = random.nextInt(20)+20; // Random attack value 20 to 40
        this.speed = random.nextInt(89)+10; // Random speed value 10 to 99

        switch(random.nextInt(3)) {
        case 0 -> weapon = new Bow(attack);
        case 1 -> weapon = new Spear(attack);
        case 2 -> weapon = new Sword(attack);
        }
	}
	
	public String getDetails() {
		return name+", Job: "+getClass().getSimpleName()+", Points: "+point+", Stamina: "+stamina+", Attack: "+attack+", Speed: "+speed+", Weapon: "+weapon.getDetails();
	}
	public String getStats() {
		return name+", Job: "+getClass().getSimpleName()+", Points: "+point+", Stamina: "+stamina;
		
	}
	// 5 ACTION of character:
	
	@Override
	public int punch(Opponent opponent,Turn turn) {	
		int attackAmount = turn.modifyAttack((int) (attack*0.8));
		turn.resetAttackModifier();
		int damageAmount = opponent.decreasePoints(attackAmount);
		guardUsed = false;
		return damageAmount;
	}
	
	@Override
	public int attackWithWeapon(Opponent opponent, Turn turn, int attackType) {
			int attackAmount = turn.modifyAttack(weapon.attack(attackType));
			turn.resetAttackModifier();
			int damageAmount = opponent.decreasePoints(attackAmount);
			guardUsed = false;
			return damageAmount;
	}
	@Override
	public boolean guard() { //void yapılabilir.
		stamina -= 1;
		return guardUsed;
	}
	@Override
	public void run() {
		System.out.println("Your character(s) started running away. The battle ends! \nThanks for playing!");
		System.exit(0);
	}
	@Override
	public abstract void specialAction();
	@Override
	public Weapon getWeapon() {
		return weapon;
	}
	
	
	//point can't be lesser than 0
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		if (point<0)
			this.point=0;
		else 
			this.point = point;
	}
	@Override
	public int decreasePoints(int damageAmount) {
		if (guardUsed) {
			damageAmount = (int) damageAmount / 4;
			point = point - damageAmount;
			return damageAmount;
		} else {
			point = point - damageAmount;
			return damageAmount;
		}
	}
		
	//name ve special used one time:
	public boolean isSpecialUsed() {
		return specialUsed;
	}

	public void setSpecialUsed(boolean specialUsed) {
		this.specialUsed = specialUsed;
	}
	
	//getter setter
	
	public String getName() {
		return name;
	}

	public int getSpeed() {
		return speed;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	public boolean isGuardUsed() {
		return guardUsed;
	}

	public void setGuardUsed(boolean guardUsed) {
		this.guardUsed = guardUsed;
	}
}
