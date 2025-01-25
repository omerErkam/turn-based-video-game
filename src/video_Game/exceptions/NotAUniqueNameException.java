package video_Game.exceptions;

public class NotAUniqueNameException extends Exception{
	public NotAUniqueNameException() {
		super("Not a unique name!");
	}
	public NotAUniqueNameException(String message) {
		super(message);
	}
	
}
