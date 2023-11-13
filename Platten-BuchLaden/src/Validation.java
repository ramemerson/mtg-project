
public class Validation {

	public static void validateNumber(Object number) {
		if (number instanceof Number) {
//			return true;
			System.out.println("True");
		} else {
//			return false;
			System.out.println("False");
		}
	}
	
	public static void validateLetter(letter) {
		if (Character.isLetterOrDigit(letter)) {
//			return true;
			System.out.println("True");
		} else {
//			return false;
			System.out.println("False");
		}		
	}
}
