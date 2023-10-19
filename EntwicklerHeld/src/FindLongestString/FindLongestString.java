package FindLongestString;

import java.util.ArrayList;
import java.util.List;

public class FindLongestString {

	public static void main(String[] args) {

		System.out.println(LongestSubstring.findLongestSubstring("24WECODEONLINEON88ENTWICKLERHELDDECHECKITOUT", "CHECKITOUTWECODEONLINEON24ENTWICKLERHELDOUT8"));
		
	}


	public class LongestSubstring {
		public static String findLongestSubstring(String string1, String string2) {

			List<String> result = new ArrayList<String>();

			int maxLength = 0;
			int endIndex = 0;

			// iterate through string1 left to right
			for (int i = 0; i < string1.length(); i++) {
				// iterate through string2 left to right
				for (int j = 0; j < string2.length(); j++) {
					int length = 0;
					int x = i;
					int y = j;
					// if 1st matching character is found in string2
					// we loop from this position in both strings as long as all subsequent
					// characters match
					while (x < string1.length() && y < string2.length() && string1.charAt(x) == string2.charAt(y)) {
						length++;
						x++;
						y++;
					}
					// matching parts ended
					// we store the calculated length if it is longer than the previously stored
					// length
					if (length > maxLength) {
						result.clear();
					}
					if (length > 0 && length >= maxLength) {
						maxLength = length;
						endIndex = x;
						result.add(string1.substring(i, i + maxLength));
					
						
					}
				}
			}
			String resultString = "";
			for (String resultItemString : result) {
				if (resultString.length() > 0) {
					resultString += " ";
				}
				resultString += resultItemString;
			}

			return resultString;

		}

	}
}