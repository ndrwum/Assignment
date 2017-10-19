/*Andrew Um
Oct 19 2017

CheckWriter*/

import java.util.Scanner;

public class CheckWriter {

	public static void main(String[] args) throws MyException {
		System.out.println("Enter check amount: ");
		getInput();

	}

	static void getInput() throws MyException {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		Print(input);
	}

	static void Print(String input) throws MyException {
		//split dollar and cent amount
		String[] split = input.split("\\.");
		if (split[0].isEmpty()) {
			split[0] = "0";
		}
		int dollar = Integer.parseInt(split[0]);
		int cent = 0;
		if (split.length > 1) {
			cent = Integer.parseInt(split[1]);
			if (split[1].length() > 2) {
				//Check if cent amount is 2 decimal places
				throw new MyException("Round the cent amount to 2 decimal places. Please try again.");
			}
		}
		String result = DollarToWords(dollar);
		//Uppercase first letter
		result = result.substring(0, 1).toUpperCase() + result.substring(1) + " dollars";
		String cents = CentToString(split.length, cent);
		result += cents;
		System.out.print(result);
	}

	static String unitsWordList[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };
	static String tensWordList[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety" };

	static String DollarToWords(int d) {
		if (d == 0) {
			return "zero";
		}
		
		//Assumes billions is max amount
		String words = "";
		if ((d / 1000000000) > 0) {
			words += DollarToWords(d / 1000000000) + " billion ";
			d %= 1000000000;
		}
		if ((d / 1000000) > 0) {
			words += DollarToWords(d / 1000000) + " million ";
			d %= 1000000;
		}
		if ((d / 1000) > 0) {
			words += DollarToWords(d / 1000) + " thousand ";
			d %= 1000;
		}
		if ((d / 100) > 0) {
			words += DollarToWords(d / 100) + " hundred ";
			d %= 100;
		}
		if (d > 0) {
			if (d < 20) {
				words += unitsWordList[d];
			} else {
				words += tensWordList[(d / 10)];
				if ((d % 10) > 0) {
					words += " " + unitsWordList[(d % 10)];
				}
			}
		}
		return words;

	}

	static String CentToString(int length, int cent) {
		if (length > 1 && cent != 0) {
			return " and " + cent + "/100";

		} else {
			return " only";
		}
	}
}
