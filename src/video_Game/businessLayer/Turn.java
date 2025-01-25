package video_Game.businessLayer;

import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.opponents.Wolf;

public class Turn {
	private String idOrName;
	private double attackModifier;
	private boolean skippedTurn;
	private boolean isTemporary;
	
	
	public Turn(String idOrName) {
		this.idOrName = idOrName;
		this.attackModifier = 1;
	}
	public String getIdOrName() {
		return idOrName;
	}
	public int modifyAttack(int attackValue) {
		int modifiedAttack = (int) attackModifier * attackValue;
		return modifiedAttack;
	}
	public void setAttackModifier(double attackModifier) {
		this.attackModifier = attackModifier;
	}
	public void resetAttackModifier() {
		this.attackModifier = 1;
	}
	public boolean isTemporaryTurn() {
		return isTemporary;
	}
	public void setTurnAsTemporary() {
		isTemporary = true;
	}
	public boolean isSkippedTurn() {
		return skippedTurn;
	}
	public void skipNextTurn() {
		skippedTurn = true;
	}
}
