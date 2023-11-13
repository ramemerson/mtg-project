
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
	
	public static void validateLetter(Character letter) {
		if (Character.isLetter(letter)) {
//			return true;
			System.out.println("True");
		} else {
//			return false;
			System.out.println("False");
		}		
	}
}
