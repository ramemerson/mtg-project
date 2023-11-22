import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taschenrechner {

	public static void main(String[] args) {

		System.out.println(
				"CALCULATOR  --\tinput a number followed by an operator ( + - / * )\n\t\tgo on as long as desired and enter an = sign when done");

		boolean equalsChecker = true;
		String operators = "+ - / * =";
		List<String> toBeCalculated = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
			
		while (equalsChecker) {

			System.out.println("input a number:");
			String number = scanner.next();
			toBeCalculated.add(number);

			System.out.println("input an operator:");
			String operator = scanner.next();
			toBeCalculated.add(operator);

			if (!operators.contains(operator)) {
				System.out.println("only input operators: + - / *");
			} else if (operator.equals("/")) {
				if (number.equals("0")) {
					System.out.println("you cant divide by zero!");
				}			
			}
			
			
			switch (operator) {
			case "=":
				// finding the position of a / or a * operator and then dividing the number on either side of 
				// that operator and then replace the old three index of (number - operator - number) with just the result
				for (int i = 0; i < toBeCalculated.size(); i++) {
						if (toBeCalculated.get(i).contains("/")) {
							int operandString1 = Integer.parseInt(toBeCalculated.get(i -1)); 
							int operandString2 = Integer.parseInt(toBeCalculated.get(i + 1));
							String savedValue = Integer.toString((operandString1 / operandString2));
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.add(i - 1, savedValue);
							i = i - 1;
						} else if (toBeCalculated.get(i).contains("*")) {
							int operandString1 = Integer.parseInt(toBeCalculated.get(i -1));
							int operandString2 = Integer.parseInt(toBeCalculated.get(i + 1));
							String savedValue = Integer.toString((operandString1 * operandString2));
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.add(i - 1, savedValue);	
							i = i - 1;
						} else if (toBeCalculated.get(i).contains("+")) {
							int operandString1 = Integer.parseInt(toBeCalculated.get(i -1));
							int operandString2 = Integer.parseInt(toBeCalculated.get(i + 1));
							String savedValue = Integer.toString((operandString1 + operandString2));
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.add(i - 1, savedValue);	
							i = i - 1;
						} else if (toBeCalculated.get(i).contains("-")) {
							int operandString1 = Integer.parseInt(toBeCalculated.get(i -1));
							int operandString2 = Integer.parseInt(toBeCalculated.get(i + 1));
							String savedValue = Integer.toString((operandString1 - operandString2));
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.remove(i -1);
							toBeCalculated.add(i - 1, savedValue);	
							i = i - 1;
						}
				}
				System.out.println(toBeCalculated.get(0));
				equalsChecker = false;
				break;
			default:
				break;
			}

		}
		


	}

}
