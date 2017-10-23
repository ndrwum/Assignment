
/*Andrew Um
Oct 19 2017

CheckWriter*/
 
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;

public class CheckWriter {

	public static void main(String[] args) throws MyException, ParseException {
		getInput();

	}

	static void getInput() throws MyException, ParseException {
		System.out.println("Do you use comma as decimal point? 'y' or 'n'");
		Scanner scanner = new Scanner(System.in);
		String dec = scanner.nextLine();
		while (!dec.equalsIgnoreCase("y") && !dec.equalsIgnoreCase("n")) {
			System.out.println("Please type 'y' or 'n'.");
			dec = scanner.nextLine();
		}
		System.out.println("Enter check amount: ");
		scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		Print(input, dec);
	}

	static void Print(String input, String dec) throws MyException, ParseException {
		String[] split;
		int dollar = 0;
		try {
			if (dec.toLowerCase().startsWith("y")) {
				split = input.split("\\,");
				split[0] = split[0].replaceAll(" ", "");
				if (!split[0].isEmpty()) {
					dollar = Integer.parseInt(split[0].replaceAll("\\.", ""));
				}
			} else {
				split = input.split("\\.");
				split[0] = split[0].replaceAll(" ", "");
				if (!split[0].isEmpty()) {
					dollar = Integer.parseInt(split[0].replaceAll("\\,", ""));
				}
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Input valid amount.");
		}
		if (split[0].isEmpty()) {
			split[0] = "0";
		}
		int cent = 0;
		if (split.length > 1) {
			if (split[1].length() > 2) {
				// Check if cent amount is 2 decimal places
				throw new MyException("Round the cent amount to 2 decimal places. Please try again.");
			}
			if (split[1].length() < 2) {
				split[1] += "0";

			}
			try {
				cent = Integer.parseInt(split[1]);
			} catch (NumberFormatException e) {
				throw new NumberFormatException("Input valid amount.");
			}
		}
		String result = DollarToWords(dollar);
		// Uppercase first letter
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

		// Assumes billions is max amount
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
