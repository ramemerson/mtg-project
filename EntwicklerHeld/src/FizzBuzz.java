
public class FizzBuzz {

	public static void main(String[] args) {
		class Fizzbuzz1 {


			static String fizzbuzz(int number) {

				String returnString = "";

				if (number % 3 == 0) {
					returnString = "fizz";
				} else if (number % 5 == 0) {
					returnString = "buzz";
				} else if (number % 3 == 0 && number % 5 == 0) {
					returnString = "fizzbuzz";
				}

				return returnString;
			}

		}
	}

}
