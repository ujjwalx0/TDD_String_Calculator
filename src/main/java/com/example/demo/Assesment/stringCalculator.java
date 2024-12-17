package com.example.demo.Assesment;

import java.util.ArrayList;
import java.util.List;

public class stringCalculator {

	private int calledCount = 0;

	// MAIN ADD METHOD: HANDLES BOTH CUSTOM DELIMITERS AND DEFAULT CASE
	public int add(String numbers) {
		calledCount++;

		// IF INPUT IS EMPTY, RETURN 0
		if (numbers.isEmpty()) {
			return 0;
		}

		// IF THE INPUT STARTS WITH "//", CUSTOM DELIMITERS ARE USED
		if (numbers.startsWith("//")) {
			return handleCustomDelimiter(numbers);
		}

		// OTHERWISE, USE DEFAULT DELIMITERS (COMMA AND NEWLINE)
		return calculateSum(numbers.replace("\n", ","));
	}

	// HELPER METHOD: HANDLES CASE WHEN CUSTOM DELIMITERS ARE PRESENT
	private int handleCustomDelimiter(String numbers) {
		// EXTRACT THE DELIMITER SECTION (BETWEEN "//" AND NEWLINE)
		String delimiterSection = numbers.substring(2, numbers.indexOf("\n"));

		// SPLIT DELIMITERS (CAN BE MULTIPLE) USING REGEX "]["
		String[] delimiters = delimiterSection.split("\\]\\[");

		// REMOVE BRACKETS AND CLEAN UP DELIMITERS
		for (int i = 0; i < delimiters.length; i++) {
			delimiters[i] = delimiters[i].replace("[", "").replace("]", "");
		}

		// EXTRACT THE NUMBERS AFTER THE NEWLINE
		String nums = numbers.substring(numbers.indexOf("\n") + 1);

		// REPLACE EACH CUSTOM DELIMITER WITH A COMMA
		for (String delimiter : delimiters) {
			nums = nums.replace(delimiter, ",");
		}

		// CALCULATE AND RETURN THE SUM AFTER HANDLING NUMBERS
		return calculateSum(nums);
	}

	// HELPER METHOD: CALCULATES THE SUM AND HANDLES NEGATIVES AND NUMBERS OVER 1000
	private int calculateSum(String numbers) {
		List<Integer> negativeNumbers = new ArrayList<>();
		String[] numArray = numbers.split(",");
		int sum = 0;

		// LOOP THROUGH EACH NUMBER AND PROCESS IT
		for (String num : numArray) {
			int number = Integer.parseInt(num);

			// IF THE NUMBER IS NEGATIVE, ADD TO THE NEGATIVE LIST
			if (number < 0) {
				negativeNumbers.add(number);
			}

			// ONLY ADD THE NUMBER TO THE SUM IF IT IS NON-NEGATIVE AND <= 1000
			if (number >= 0 && number <= 1000) {
				sum += number;
			}
		}

		// IF THERE ARE NEGATIVE NUMBERS, THROW AN EXCEPTION WITH A MESSAGE
		if (!negativeNumbers.isEmpty()) {
			String negatives = String.join(", ", negativeNumbers.stream().map(String::valueOf).toArray(String[]::new));
			throw new IllegalArgumentException("Negatives not allowed: " + negatives);
		}

		// RETURN THE FINAL SUM
		return sum;
	}

	// METHOD TO GET THE COUNT OF TIMES THE ADD METHOD HAS BEEN CALLED
	public int getCalledCount() {
		return calledCount;
	}
}
