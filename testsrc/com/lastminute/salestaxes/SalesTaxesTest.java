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
	
	@Test
	public void testLoadExempt() {
		try{
			classUnderTest.loadExempt();
			assertTrue(!classUnderTest.exempt.equals(""));
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
	@Test
	public void testLoadInput() {
		try{
			Path path = classUnderTest.loadInput();
			assertNotNull(path);
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
	@Test
	public void testCalculateTaxexForProduct() throws Exception{
		classUnderTest.isImported = false;
		classUnderTest.isExempt = true;
		classUnderTest.calculateTaxesForProduct("10.00");
		assertTrue(classUnderTest.salesTaxes>0);
		
		classUnderTest.isExempt = false;
		classUnderTest.isImported = true;
		classUnderTest.calculateTaxesForProduct("10.00");
		assertTrue(classUnderTest.salesTaxes>0);
		
		classUnderTest.isExempt = true;
		classUnderTest.calculateTaxesForProduct("10.00");
		assertTrue(classUnderTest.salesTaxes>0);

	}
	
	@Test
	public void testCheckForImportedAndExemption() {
		try{
			classUnderTest.isImported = false;
			classUnderTest.isExempt = false;
			classUnderTest.checkForImportedAndExemption("imported");
			assertTrue(classUnderTest.isImported);
			assertTrue(!classUnderTest.isExempt);

			classUnderTest.isImported = false;
			classUnderTest.isExempt = false;
			classUnderTest.checkForImportedAndExemption("chocolates");
			assertTrue(!classUnderTest.isExempt);
			assertTrue(classUnderTest.isImported);
			
			classUnderTest.isImported = false;
			classUnderTest.isExempt = false;
			classUnderTest.checkForImportedAndExemption("paper");
			assertTrue(!classUnderTest.isImported);
			assertTrue(!classUnderTest.isExempt);

		}catch(Exception e){
			assertTrue(false);
		}
	}
	
	@Test
	public void testPrintTotalAndSalesTaxes() {
		try{
			classUnderTest.printTotalAndSalesTaxes();
			assertTrue();
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
}
