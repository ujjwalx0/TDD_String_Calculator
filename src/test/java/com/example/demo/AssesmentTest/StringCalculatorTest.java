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
	public void testTwoNumbers() {
	    assertEquals(3, stc.add("1,2"), "Sum of two numbers should return their sum");
	}

}
