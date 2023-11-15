
public class TypeValidation {

	public static boolean validateNumber(Object number) {
		if (number instanceof Number) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateLetter(String word) {
		if (word.matches("[a-zA-Z]+")) {
			return true;
		} else {
			return false;
		}
	}
}


