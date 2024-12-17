package com.example.demo.Assesment;

import java.util.ArrayList;
import java.util.List;

public class stringCalculator {
	
	 private int calledCount = 0;
	
	// updated ADD Method to Handle 1 OR 2  
	public int add(String numbers) {
		 calledCount++; 
        if (numbers.isEmpty()) {
            return 0;
        }
        
        // ArrayList for collecting negative nums if more than 1 or 1
        List<Integer> negativeNumbers = new ArrayList<>();
        
        // added logic for handling input with delimiters in the beginning
        if (numbers.startsWith("//")) {
           // String delimiter = numbers.substring(2, numbers.indexOf("\n"));
        	//updated for handling delimiters of any length
            String delimiter = numbers.substring(2, numbers.indexOf("\n")).replace("[", "").replace("]", "");
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
                 if (numValue <= 1000) { // handling numbers only upto 1000
                     sum += numValue;
                 }
                
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
        	 if (numValue <= 1000) { // handling numbers only upto 1000
                 sum += numValue;
             }
        }
        
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
