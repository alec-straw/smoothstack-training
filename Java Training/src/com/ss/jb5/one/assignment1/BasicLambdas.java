/**
 * basic lambda functionality
 */
package com.ss.jb5.one.assignment1;

/*import java.util.Arrays;
import java.util.stream.Collectors;*/

/**
 * @author alecs
 *
 */
public class BasicLambdas {

	static int containsE(String str1, String str2) {
		if (str1.toLowerCase().contains("e") && str2.toLowerCase().contains("e"))
			return 0;
		else
			return str1.toLowerCase().contains("e") ? -1 : 1;
	}

	/**
	 * demo code
	 */
	/*public static void main(String[] args) {
		String[] words = { "SmoothStack", "cat", "Bill", "eight" };
		System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
		// sort shortest to longest
		Arrays.sort(words, (str1, str2) -> str1.length() - str2.length());
		System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
		// sort longest to shortest
		Arrays.sort(words, (str1, str2) -> str2.length() - str1.length());
		System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
		// sort by first character
		Arrays.sort(words, (str1, str2) -> str1.toLowerCase().charAt(0) - str2.toLowerCase().charAt(0));
		System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
		// put strings containing an e first
		Arrays.sort(words, (str1, str2) -> {
			if (str1.toLowerCase().contains("e") && str2.toLowerCase().contains("e"))
				return 0;
			else
				return str1.toLowerCase().contains("e") ? -1 : 1;
		});
		System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
		// same with static helper method
		Arrays.sort(words, (s1, s2) -> containsE(s1, s2));
		System.out.println(Arrays.stream(words).collect(Collectors.joining(", ")));
	}*/

}
