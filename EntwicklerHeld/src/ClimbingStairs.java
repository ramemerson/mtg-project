import java.util.List;

public class ClimbingStairs {

	public static void main(String[] args) {
		
		System.out.println(climbingStairs(2));

	}

	public static int climbingStairs(int numberOfStairs) {

		if (numberOfStairs < 1) {
			return 1;
		}

		int[] possibilities = new int[numberOfStairs + 1];
		
		possibilities[0] = 1;
		possibilities[1] = 1;

		for (int i = 2; i <= numberOfStairs; i++) {
			possibilities[i] = possibilities[i - 1] + possibilities[i - 2];
		}

		return possibilities[numberOfStairs];
	}

}
