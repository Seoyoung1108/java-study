package prob04;

@SuppressWarnings("serial")
public class MyStackException extends Exception {
	public String toString() {
		return "Prob04.MyStackException: stack is empty";
	}
}