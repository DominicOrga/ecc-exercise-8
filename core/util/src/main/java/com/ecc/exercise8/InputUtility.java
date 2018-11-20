package com.ecc.exercise8;

import java.util.Optional;
import java.util.Scanner;
import java.time.Year;
import java.time.LocalDate;

public class InputUtility {

	public static String nextStringPersistent(String message) {
		return nextStringPersistent(message, true);
	}

	public static String nextStringPersistent(String message, boolean isTrimmed) {
		Optional<String> input;

		do {
			input = nextString(message, isTrimmed);
		} while (!input.isPresent());

		return input.get();
	}

	public static Optional<String> nextString(String message) {
		return nextString(message, true);
	}

	public static Optional<String> nextString(String message, boolean isTrimmed) {
		Scanner scanner = new Scanner(System.in);
		boolean isInputValidated = false;

		System.out.print(message + " ");
		String input = scanner.nextLine();

		if (isTrimmed) {
			input = input.trim();
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

	public static float nextFloatPersistent(String message) {
		return nextFloatPersistent(message, Float.MIN_VALUE, Float.MAX_VALUE);
	}

	public static float nextFloatPersistent(String message, float min, float max) {
		Optional<Float> input;

		do {
			input = nextFloat(message, min, max);
		} while (!input.isPresent());

		return input.get().floatValue();
	}

	public static Optional<Float> nextFloat(String message) {
		return nextFloat(message, Float.MIN_VALUE, Float.MAX_VALUE);
	}

	public static Optional<Float> nextFloat(String message, float min, float max) {
		Scanner scanner = new Scanner(System.in);

		System.out.print(message + " ");
		String input = scanner.nextLine();
		
		if (input.matches("^([+-]?\\d*\\.?\\d*)$")) {
			float floatInput = Float.parseFloat(input);

			if (floatInput >= min && floatInput <= max) {				
				return Optional.of(floatInput);
			}
			else {
				System.out.printf("Input should be a float value between %,.4f (inclusive) and %,.4f " + 
					"(inclusive)\n", min, max);
			}
		} else {
			System.out.println("Please enter valid float value");
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

	public static LocalDate nextDatePersistent(String message, int minYear, int maxYear) {
		Optional<LocalDate> date = Optional.empty();

		do {
			date = nextDate(message, minYear, maxYear);
		} while (!date.isPresent());

		return date.get();
	}

	public static Optional<LocalDate> nextDate(String message, int minYear, int maxYear) {
		if (minYear < Year.MIN_VALUE) {
			throw new IllegalArgumentException("Min Year must be greater than or equal to " + Year.MIN_VALUE);
		}

		if (minYear > Year.MAX_VALUE) {
			throw new IllegalArgumentException("Min Year must be less than or equal to " + Year.MAX_VALUE);
		}

		if (minYear > maxYear) {
			throw new IllegalArgumentException("Min Year must be less than or equal to Max Year");
		}

		if (maxYear < Year.MIN_VALUE) {
			throw new IllegalArgumentException("Max Year must be greater than or equal to " + Year.MIN_VALUE);
		}

		if (maxYear > Year.MAX_VALUE) {
			throw new IllegalArgumentException("Max Year must be less than or equal to " + Year.MAX_VALUE);
		}

		System.out.println(message);

		Optional<Integer> year = 
			nextInt(String.format("Enter Year (%d ~ %d):", minYear, maxYear), minYear, maxYear);

		if (!year.isPresent()) {
			return Optional.empty();
		}

		Optional<Integer> month = nextInt("Enter Month (1 ~ 12):", 1, 12);

		if (!month.isPresent()) {
			return Optional.empty();
		}

		LocalDate date = LocalDate.of(year.get(), month.get(), 1);
		Optional<Integer> day = 
			nextInt(String.format("Enter day (%d ~ %d):", 1, date.lengthOfMonth()), 1, date.lengthOfMonth());

		if (!day.isPresent()) {
			return Optional.empty();
		}

		return Optional.ofNullable(LocalDate.of(year.get(), month.get(), day.get()));
	}
}