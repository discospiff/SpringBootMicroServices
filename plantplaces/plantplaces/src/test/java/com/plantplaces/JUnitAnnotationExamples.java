package com.plantplaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class JUnitAnnotationExamples {
	
	@BeforeClass
	public static void setupEverything() {
		int i = 1 + 1;
	}
	
	@Before
	public void setupBeforeEachTest() {
		int i = 1 + 1;
	}
	
	@Test
	public void runTests() {
		double i = 1.01 + 1.01;
		assertEquals(2.0, i, 0.3);
	}
	
	@Test
	public void runMoreTests() {
		int i = 1 + 1;
		assertEquals(2, i);
		Object o = null;
		assertNull(o);
		assertTrue(4 == 2 + 2);
	}

	@Ignore
	@Test
	public void runFailTests() {
		fail();
	}

	@AfterClass
	public static void teardownEverything() {
		int i = 1 + 1;
	}
	
	@After
	public void teardownBeforeEachTest() {
		int i = 1 + 1;
	}
}
