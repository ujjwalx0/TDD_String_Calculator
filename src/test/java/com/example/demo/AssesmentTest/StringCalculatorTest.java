package com.example.demo.AssesmentTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
