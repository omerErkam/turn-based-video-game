package video_Game.dataAccess.entities.abstracts;

import java.util.Random;

import video_Game.businessLayer.Turn;

public abstract class Opponent {
	private String name;
	public int id;
	private int point;
	private int attack;
	private int speed;
	private int type;
	private Random random;
	private boolean guardUsed;
	
	public Opponent (int id) {

		random = new Random();
    	this.point = random.nextInt(100)+50;
    	this.attack = random.nextInt(20)+5;
    	this.speed = random.nextInt(89)+1;
    	this.id = id;
    	this.name = "Opponent "+id;
	}
	public String getName() {
		return name;
	}

	public int attack(Character<Weapon> character, Turn turn) {
		int attackAmount = turn.modifyAttack(attack);
		turn.resetAttackModifier();
		int damageAmount = character.decreasePoints(attackAmount);
		return damageAmount;
	}
	
	public boolean guard() {
		return guardUsed;
	}
	
	public void special(Human human) {
	
	}
	
	public String getDetails() {
		return "Id: "+id+", Type: "+getClass().getSimpleName()+", Points: "+point+", Attack: "+attack+", Speed: "+speed;
	}
	public int decreasePoints(int damageAmount) {
		if (guardUsed) {
			damageAmount = (int) damageAmount / 2;
			point = point - damageAmount;
			return damageAmount;
		} else {
			point = point - damageAmount;
			return damageAmount;
		}
	}
	//getter-setter
	
	//point can't be lesser than 0:
	public int getId() {
		return id;
	}
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		if (point<0)
			this.point=0;
		else 
			this.point = point;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public boolean isGuardUsed() {
		return guardUsed;
	}

	public void setGuardUsed(boolean guardUsed) {
		this.guardUsed = guardUsed;
	}

}
