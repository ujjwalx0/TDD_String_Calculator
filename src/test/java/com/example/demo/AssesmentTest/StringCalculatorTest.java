package com.example.demo.AssesmentTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.demo.Assesment.stringCalculator;

public class StringCalculatorTest {
	
	
	private final stringCalculator stc = new stringCalculator();
	
	
	@Test
	public void testEmptyString() {
		assertEquals(0, stc.add(""), "Empty string should return 0");
	}
	
	@Test
	public void testOneNumber() {
	    assertEquals(3, stc.add("3"), " one number should return the number");
	}
	@Test
	public void testTwoNumbers() {
	    assertEquals(3, stc.add("1,2"), "Sum of two numbers should return their sum");
	}

	@Test
	public void testOf_N_Numbers() {
	    assertEquals(25, stc.add("1,2,3,5,3,11"), "Sum of N numbers should return their sum");
	}
	
	@Test
	public void testNewLineSeparator() {
	    assertEquals(6, stc.add("1\n2,3"), "Numbers with newlines and commas should be summed correctly");
	}
	
	@Test
	public void testCustomDelimiter() {
	    assertEquals(10, stc.add("//;\n1;9"), "Custom delimiter should be recognized and used for splitting numbers");
	}

	   @Test
	    public void testNegativeNumbers() {
	        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	            stc.add("-1,3,-4,9");
	        });

	        // Assert that the exception message contains all negative numbers
	        assertEquals("Negatives not allowed: -1, -4", exception.getMessage());
	    }
	 

	   @Test
	    public void testGetCalledCount() {
	        stc.add("1,2,3"); 
	        stc.add("4,5,6"); 
	        
	        // Assert that GetCalledCount() returns 2, since we called add() twice
	        assertEquals(2, stc.GetCalledCount(), "GetCalledCount should return the correct number of times Add() was called");
	    }
	   
	   @Test
	    public void testNumbersGreaterThan1000() {
	        
	        assertEquals(2, stc.add("2,1001"), "Numbers greater than 1000 should be ignored");
	        assertEquals(2, stc.add("1001,2"), "Numbers greater than 1000 should be ignored");
	        assertEquals(1005, stc.add("2,1001,1000,3"), "Numbers greater than 1000 should be ignored");
	    }
	   
	   @Test
	    public void testCustomDelimiterOfAnyLength() {
	      
	        assertEquals(6, stc.add("//[***]\n1***2***3"), "Custom delimiter [***] should work for any length");
	     
	        assertEquals(6, stc.add("//[abcd]\n1abcd2abcd3"), "Custom delimiter [abcd] should work for any length");
	    }
	   
	   
	   @Test
	   public void testMultipleDelimiters() {
	     
	       assertEquals(6, stc.add("//[*][%]\n1*2%3"), "Multiple delimiters [*] and [%] should be supported and the sum should be 6");

	      
	       assertEquals(10, stc.add("//[*][#][%]\n1*2#3%4"), "Multiple delimiters [*], [#], and [%] should be supported and the sum should be 6");

	     
	       IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
	           stc.add("//[*][%]\n1*2%3*-4");
	       });
	       assertEquals("Negatives not allowed: -4", exception.getMessage(), "Exception should indicate the negative number");
	   }


	   @Test
	   public void testMultipleDelimitersWithLongerLength() {
	       assertEquals(6, stc.add("//[**][%%]\n1**2%%3"), "Multiple delimiters [**] and [%%] should be supported and the sum should be 6");
	       
	      
	       assertEquals(10, stc.add("//[%%][**]\n2%%3**5"), "Multiple delimiters [%%] and [**] should be supported and the sum should be 10");
	       
	      
	       assertEquals(6, stc.add("//[%%][**]\n1%%2**3"), "Multiple delimiters [%%] and [**] should be supported and the sum should be 6");

	      
	       assertEquals(100, stc.add("//[***][&&]\n10***20&&70"), "Multiple delimiters [***] and [&&] should be supported and the sum should be 100");
	   }


}


