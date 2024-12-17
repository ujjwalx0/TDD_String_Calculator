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
	public void testSingleAndMultipleNumbers() {
		// Test for 0
		assertEquals(0, stc.add("0"), "Sum of 0 should return 0");
		assertEquals(0, stc.add("0,0,0"), "Sum of multiple 0's should return 0");

		// Test for single numbers
		assertEquals(1, stc.add("1"), "Sum of 1 should return 1");
		assertEquals(3, stc.add("1,2"), "Sum of 1 and 2 should be 3");
		assertEquals(6, stc.add("1,2,3"), "Sum of 1, 2, and 3 should be 6");

		// Test for multiple numbers
		assertEquals(25, stc.add("1,2,3,5,3,11"), "Sum of 1, 2, 3, 5, 3, and 11 should be 25");
		assertEquals(45, stc.add("10,15,20"), "Sum of 10, 15, and 20 should be 45");
	}

	@Test
	public void testNewLineSeparator() {
		assertEquals(6, stc.add("1\n2,3"), "Numbers with newlines and commas should be summed correctly");
		assertEquals(10, stc.add("1\n2\n3\n4"), "Numbers with newlines should be summed correctly");
		assertEquals(15, stc.add("5\n5,5"), "Numbers with mixed newlines and commas should be summed correctly");
	}

	@Test
	public void testCustomDelimiter() {
		assertEquals(10, stc.add("//;\n1;9"),
				"Custom delimiter ';' should be recognized and used for splitting numbers");
		assertEquals(20, stc.add("//|\n5|5|10"),
				"Custom delimiter '|' should be recognized and used for splitting numbers");
		assertEquals(15, stc.add("//#\n7#8"),
				"Custom delimiter '#' should be recognized and used for splitting numbers");
	}

	@Test
	public void testNegativeNumbers() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			stc.add("-1,3,-4,9");
		});
		assertEquals("Negatives not allowed: -1, -4", exception.getMessage());

		exception = assertThrows(IllegalArgumentException.class, () -> {
			stc.add("//;\n-5;10;-2");
		});
		assertEquals("Negatives not allowed: -5, -2", exception.getMessage());
	}

	@Test
	public void testGetCalledCount() {
		stc.add("1,2,3");
		stc.add("4,5,6");
		assertEquals(2, stc.getCalledCount(), "GetCalledCount should return correct count of method calls");

		stc.add("7,8,9");
		stc.add("10,11,12");
		assertEquals(4, stc.getCalledCount(), "GetCalledCount should return correct count after multiple calls");
	}

	@Test
	public void testNumbersGreaterThan1000() {
		assertEquals(2, stc.add("2,1001"), "Numbers greater than 1000 should be ignored");
		assertEquals(1005, stc.add("2,1001,1000,3"), "Numbers greater than 1000 should be ignored");

		assertEquals(45, stc.add("10,15,20,1001"), "Numbers greater than 1000 should be ignored");
		assertEquals(10, stc.add("1001,5,5"), "Numbers greater than 1000 should be ignored");
	}

	@Test
	public void testCustomDelimiterOfAnyLength() {
		assertEquals(6, stc.add("//[***]\n1***2***3"), "Custom delimiter [***] should work for any length");
		assertEquals(6, stc.add("//[abcd]\n1abcd2abcd3"), "Custom delimiter [abcd] should work for any length");
		assertEquals(15, stc.add("//[longDelim]\n5longDelim5longDelim5"),
				"Custom delimiter [longDelim] should work for any length");
	}

	@Test
	public void testMultipleDelimiters() {
		assertEquals(6, stc.add("//[*][%]\n1*2%3"), "Multiple delimiters [*] and [%] should be supported");
		assertEquals(10, stc.add("//[*][#][%]\n1*2#3%4"), "Multiple delimiters [*], [#], and [%] should be supported");
		assertEquals(12, stc.add("//[##][%%]\n5##3%%4"), "Multiple delimiters [##] and [%%] should be supported");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			stc.add("//[*][%]\n1*2%3*-4");
		});
		assertEquals("Negatives not allowed: -4", exception.getMessage());
	}

	@Test
	public void testMultipleDelimitersWithLongerLength() {
		assertEquals(6, stc.add("//[**][%%]\n1**2%%3"), "Multiple delimiters [**] and [%%] should be supported");
		assertEquals(10, stc.add("//[%%][**]\n2%%3**5"), "Multiple delimiters [%%] and [**] should be supported");
		assertEquals(6, stc.add("//[%%][**]\n1%%2**3"), "Multiple delimiters [%%] and [**] should be supported");
		assertEquals(100, stc.add("//[***][&&]\n10***20&&70"),
				"Multiple delimiters [***] and [&&] should be supported");

		// More cases with long delimiters
		assertEquals(7, stc.add("//[long][delimiter]\n2long3delimiter2"), "Multiple long delimiters should work");
		assertEquals(9, stc.add("//[ab][cd]\n1ab3cd5"), "Multiple mixed long delimiters should work");
	}
}
