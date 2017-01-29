package com.lastminute.salestaxes;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

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
	public void testCalculateTaxesForProduct() throws Exception{		
		classUnderTest.isImported = false;
		classUnderTest.isExempt = true;
		classUnderTest.salesTaxes = 0;
		classUnderTest.calculateTaxesForProduct("12.55");
		assertTrue(classUnderTest.salesTaxes == 0);
		
		classUnderTest.isExempt = false;
		classUnderTest.isImported = true;
		classUnderTest.salesTaxes = 0;
		classUnderTest.calculateTaxesForProduct("14.33");
		assertTrue(classUnderTest.salesTaxes== 2.15f);
		
		classUnderTest.isImported = true;
		classUnderTest.isExempt = true;
		classUnderTest.salesTaxes = 0;
		classUnderTest.calculateTaxesForProduct("19.99");
		assertTrue(classUnderTest.salesTaxes==1);

	}
	
	@Test
	public void testCheckForImportedAndExemption() {
		try{
			classUnderTest.loadExempt();
			
			classUnderTest.isImported = false;
			classUnderTest.isExempt = false;
			classUnderTest.checkForImportedAndExemption("imported");
			assertTrue(classUnderTest.isImported);
			assertTrue(!classUnderTest.isExempt);
			
			classUnderTest.isImported = false;
			classUnderTest.isExempt = false;
			classUnderTest.checkForImportedAndExemption("chocolates");
			assertTrue(!classUnderTest.isImported);
			assertTrue(classUnderTest.isExempt);
			
			
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
			assertTrue(true);
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
}
