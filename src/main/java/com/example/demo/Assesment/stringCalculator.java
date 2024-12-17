package com.example.demo.Assesment;

public class stringCalculator {

	
	// updated ADD Method to Handle 1 OR 2  
	public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numArray = numbers.split(",");
        int sum = 0;

        for (String num : numArray) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }
	
}
