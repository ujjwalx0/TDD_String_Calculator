package com.example.demo.Assesment;

import java.util.ArrayList;
import java.util.List;

public class stringCalculator {

    private int calledCount = 0;

    // updated ADD Method to Handle single delimiter (simple version)
    public int add(String numbers) {
        calledCount++; 
        if (numbers.isEmpty()) {
            return 0;
        }

        // ArrayList for collecting negative nums if more than 1 or 1
        List<Integer> negativeNumbers = new ArrayList<>();

        // If numbers string starts with "//"
        if (numbers.startsWith("//")) {
        	 String delimiterSection = numbers.substring(2, numbers.indexOf("\n"));
            
             String[] delimiters = delimiterSection.split("\\]\\["); // Split at each occurrence of ][
             for (int i = 0; i < delimiters.length; i++) {
                 delimiters[i] = delimiters[i].replace("[", "").replace("]", ""); // Clean up the delimiters
             }

             String nums = numbers.substring(numbers.indexOf("\n") + 1);

             // Replacing each delimiter with a comma
             for (String delimiter : delimiters) {
                 nums = nums.replace(delimiter, ",");
             }

            // Split numbers by comma
            String[] numArray = nums.split(",");
            int sum = 0;
            for (String num : numArray) {
                int numValue = Integer.parseInt(num);

                // CHECKing IF THE NUMBER IS NEGATIVE AND ADD TO THE NEGATIVE NUMBERS LIST IF SO
                if (numValue < 0) {
                    negativeNumbers.add(numValue); // Collecting negative numbers
                }

                // ADDING THE NUMBER TO THE SUM ONLY IF IT IS NON-NEGATIVE AND <= 1000
                if (numValue >= 0 && numValue <= 1000) {
                    sum += numValue;
                }
            }

            // IF THERE ARE NEGATIVE NUMBERS, THROWING AN EXCEPTION
            if (!negativeNumbers.isEmpty()) {
                String negatives = negativeNumbers.toString().replace("[", "").replace("]", "");
                throw new IllegalArgumentException("Negatives not allowed: " + negatives);
            }
            return sum;
        }

        //  split by commas and newlines
        String[] numArray = numbers.replace("\n", ",").split(",");
        int sum = 0;

        // Process each number and sum them up
        for (String num : numArray) {
            int numValue = Integer.parseInt(num);

            // CHECKING IF THE NUMBER IS NEGATIVE AND ADD TO THE NEGATIVE NUMBERS LIST IF SO
            if (numValue < 0) {
                negativeNumbers.add(numValue); // Collecting negative numbers
            }

            // ADDING THE NUMBER TO THE SUM ONLY IF IT IS NON-NEGATIVE AND <= 1000
            if (numValue >= 0 && numValue <= 1000) {
                sum += numValue;
            }
        }

        // IF THERE ARE NEGATIVE NUMBERS, THROWING AN EXCEPTION
        if (!negativeNumbers.isEmpty()) {
            String negatives = negativeNumbers.toString().replace("[", "").replace("]", "");
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }

    public int GetCalledCount() {
        return calledCount;
    }
}
