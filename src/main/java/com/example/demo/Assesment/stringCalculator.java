package com.example.demo.Assesment;

import java.util.ArrayList;
import java.util.List;

public class stringCalculator {

	
	// updated ADD Method to Handle 1 OR 2  
	public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        // ArrayList for collecting negative nums if more than 1 or 1
        List<Integer> negativeNumbers = new ArrayList<>();
        
        // added logic for handling input with delimiters in the beginning
        if (numbers.startsWith("//")) {
            String delimiter = numbers.substring(2, numbers.indexOf("\n"));
            String nums = numbers.substring(numbers.indexOf("\n") + 1);
            
           

            // Replacing custom delimiter with commas
            nums = nums.replace(delimiter, ",");
            String[] numArray = nums.split(",");
            int sum = 0;
            for (String num : numArray) {
            	 int numValue = Integer.parseInt(num);
                 if (numValue < 0) {
                     negativeNumbers.add(numValue); // Collecting negative numbers
                 }
            	
                sum += Integer.parseInt(num);
                
            }
            
            if (!negativeNumbers.isEmpty()) {
                String negatives = negativeNumbers.toString().replace("[", "").replace("]", "");
                throw new IllegalArgumentException("Negatives not allowed: " + negatives);
            }
            return sum;
        }
        
     //modified the split for handling new line separators
        String[] numArray = numbers.replace("\n", ",").split(",");
        int sum = 0;

        for (String num : numArray) {
        	// handling negative numbers from the base case as well
        	int numValue = Integer.parseInt(num);
        	 if (numValue < 0) {
                 negativeNumbers.add(numValue); // Collecting negative numbers
             }
            sum += Integer.parseInt(num);
        }
        
        if (!negativeNumbers.isEmpty()) {
            String negatives = negativeNumbers.toString().replace("[", "").replace("]", "");
            throw new IllegalArgumentException("Negatives not allowed: " + negatives);
        }

        return sum;
    }
	
}
