package com.ecc.exercise8;

import java.util.Optional;
import java.util.Scanner;

public class InputUtility {

	public static String nextStringPersistent(String message, String... invalidSubstrings) {
		Optional<String> input;

		do {
			input = nextString(message, invalidSubstrings);
		} while (!input.isPresent());

		return input.get();
	}

	public static Optional<String> nextString(String message, String... invalidSubstrings) {
		Scanner scanner = new Scanner(System.in);
		boolean isInputValidated = false;

		System.out.print(message + " ");
		String input = scanner.nextLine();

		if (invalidSubstrings != null) {
			for (int i = 0, s = invalidSubstrings.length; i < s; i++) {
				if (input.contains(invalidSubstrings[i])) {
					System.out.printf("Input should not contain %s\n", invalidSubstrings[i]);
					return Optional.empty();
				};
			}
		}

		return Optional.of(input);
	}

	public static int nextIntPersistent(String message) {
		Optional<Integer> input;

		do {
			input = nextInt(message);
		} while (!input.isPresent());

		return input.get().intValue();
	}

	public static int nextIntPersistent(String message, int min, int max) {
		Optional<Integer> input;

		do {
			input = nextInt(message, min, max);
		} while (!input.isPresent());

		return input.get().intValue();
	}

	public static Optional<Integer> nextInt(String message) {
		return nextInt(message, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static Optional<Integer> nextInt(String message, int min, int max) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(message + " ");
		String input = scanner.nextLine();
		
		if (input.matches("-?\\d+")) {
			int intInput = Integer.parseInt(input);

			if (intInput >= min && intInput <= max) {				
				return Optional.of(intInput);
			}
			else {
				System.out.printf("Input should be an integer between %d (inclusive) and %d " + 
					"(inclusive)\n", min, max);
			}

		} else {
			System.out.println("Please enter valid integer");
		}	

		return Optional.empty();
	}	

	public static long nextLongPersistent(String message) {
		Optional<Long> input;

		do {
			input = nextLong(message);
		} while (!input.isPresent());

		return input.get().longValue();
	}

	public static long nextLongPersistent(String message, long min, long max) {
		Optional<Long> input;

		do {
			input = nextLong(message, min, max);
		} while (!input.isPresent());

		return input.get().longValue();
	}

	public static Optional<Long> nextLong(String message) {
		return nextLong(message, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public static Optional<Long> nextLong(String message, long min, long max) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(message + " ");
		String input = scanner.nextLine();
		
		if (input.matches("-?\\d+")) {
			long longInput = Long.parseLong(input);

			if (longInput >= min && longInput <= max) {				
				return Optional.of(longInput);
			}
			else {
				System.out.printf("Input should be a long value between %d (inclusive) and %d " + 
					"(inclusive)\n", min, max);
			}

		} else {
			System.out.println("Please enter valid long value");
		}	

		return Optional.empty();
	}

	public static boolean nextBoolPersistent(String message, String trueSymbol, String falseSymbol) {
		Optional<Boolean> input;

		do {
			input = nextBool(message, trueSymbol, falseSymbol);
		} while (!input.isPresent());

		return input.get().booleanValue();
	}

	public static Optional<Boolean> nextBool(String message, String trueSymbol, String falseSymbol) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(message + " ");
		String input = scanner.nextLine();

		if (input.equals(trueSymbol)) {
			return Optional.of(true);
		}		
		else if (input.equals(falseSymbol)) {
			return Optional.of(false);
		}
		else {
			System.out.printf("Valid inputs are %s (true) and %s (false)\n", trueSymbol, falseSymbol);
			return Optional.empty();
		}
	}
}