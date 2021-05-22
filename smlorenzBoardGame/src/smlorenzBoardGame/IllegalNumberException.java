package smlorenzBoardGame;

public class IllegalNumberException extends RuntimeException {
	public IllegalNumberException(String mesg) {
		super(mesg);
		System.out.println(mesg);
	}
}
