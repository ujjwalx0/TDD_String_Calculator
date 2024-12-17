package com.example.demo.Assesment;

public class stringCalculator {

	
	// updated ADD Method to Handle 1 OR 2  
	public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        // added logic for handling input with delimiters in the beginning
        if (numbers.startsWith("//")) {
            String delimiter = numbers.substring(2, numbers.indexOf("\n"));
            String nums = numbers.substring(numbers.indexOf("\n") + 1);

            // Replacing custom delimiter with commas
            nums = nums.replace(delimiter, ",");
            String[] numArray = nums.split(",");
            int sum = 0;
            for (String num : numArray) {
                sum += Integer.parseInt(num);
            }
            return sum;
        }
        
     //modified the split for handling new line separators
        String[] numArray = numbers.replace("\n", ",").split(",");
        int sum = 0;

        for (String num : numArray) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }
	
}
