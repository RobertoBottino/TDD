package com.lastminute.salestaxes;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.policy.AssertionSet;

public class SalesTaxesTest {

	SalesTaxes classUnderTest; 
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new SalesTaxes();
	}

	@Test
	public void testMain() {
		assertNotNull(classUnderTest);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testLoadExempt() {
		try{
			classUnderTest.loadExempt();
			assertTrue(!classUnderTest.exempt.equals(""));
		}catch(Exception e){
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testLoadInput() {
		try{
			Path path = classUnderTest.loadInput();
			assertNotNull(path);
		}catch(Exception e){
			assertTrue(false);
		}
		assertTrue(true);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCalculateTaxexForProduct() throws Exception{
		classUnderTest.isExempt = true;
		classUnderTest.calculateTaxesForProduct("12.13");
		assertTrue(classUnderTest.salesTaxes>0);
		System.out.println(classUnderTest.salesTaxes);
		
		classUnderTest.isExempt = false;
		classUnderTest.isImported = true;
		classUnderTest.calculateTaxesForProduct("12.13");
		assertTrue(classUnderTest.salesTaxes>0);
		System.out.println(classUnderTest.salesTaxes);
		
		classUnderTest.isExempt = true;
		classUnderTest.calculateTaxesForProduct("12.13");
		assertTrue(classUnderTest.salesTaxes>0);
		System.out.println(classUnderTest.salesTaxes);
		
	}
}
