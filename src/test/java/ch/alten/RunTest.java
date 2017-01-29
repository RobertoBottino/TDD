package ch.alten;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.alten.beans.Product;

/*
 * 
 *	Author: Roberto Bottino 
 * 
 */


public class RunTest {    
    
	Run classUnderTest; 
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new Run();
	}

	@Test
	public void testMain() {
		assertNotNull(classUnderTest);
	}
	
	@Test
	public void testLoadExempt() {
		try{
			classUnderTest.loadExempt();
			assertTrue(!classUnderTest.exempt.isEmpty());
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
	@Test
	public void testLoadProduct() {
		try{
			classUnderTest.loadExempt();
			assertTrue(!classUnderTest.exempt.isEmpty());
			
			Product p = new Product("1 book at 12.49", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 12.49f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" book: "));
			assertTrue(p.isExempt() == true);
			assertTrue(p.isImported() == false);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 music CD at 14.99", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 14.99f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" music CD: "));
			assertTrue(p.isExempt() == false);
			assertTrue(p.isImported() == false);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 chocolate bar at 0.85", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 0.85f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" chocolate bar: "));
			assertTrue(p.isExempt() == true);
			assertTrue(p.isImported() == false);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 imported box of chocolates at 10.00", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 10.00f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" box of chocolates: "));
			assertTrue(p.isExempt() == true);
			assertTrue(p.isImported() == true);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 imported bottle of perfume at 47.50", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 47.50f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" bottle of perfume: "));
			assertTrue(p.isExempt() == false);
			assertTrue(p.isImported() == true);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 imported bottle of perfume at 27.99", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 27.99f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" bottle of perfume: "));
			assertTrue(p.isExempt() == false);
			assertTrue(p.isImported() == true);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 bottle of perfume at 18.99", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 18.99f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" bottle of perfume: "));
			assertTrue(p.isExempt() == false);
			assertTrue(p.isImported() == false);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 packet of headache pills at 9.75", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 9.75f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" packet of headache pills: "));
			assertTrue(p.isExempt() == true);
			assertTrue(p.isImported() == false);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 box of imported chocolates at 11.25", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.getPrice() == 11.25f);
			assertTrue(p.getQuantity() == 1);
			assertTrue(p.getName().equals(" box of chocolates: "));
			assertTrue(p.isExempt() == true);
			assertTrue(p.isImported() == true);
			assertTrue(p.isWrongProduct() == false);
			p = new Product("1 box of 5.25 chocolates at 11.25", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.isWrongProduct() == true);
			p = new Product("box of 5 chocolates at 11.25", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.isWrongProduct() == true);
			p = new Product("1 box of 5.25 chocolates at", classUnderTest.exempt, classUnderTest.df);
			assertNotNull(p);
			assertTrue(p.isWrongProduct() == true);
		}catch(Exception e){
			assertTrue(false);
		}
	}
	
	@Test
	public void testCalculateTaxes() throws Exception{
		try{
			classUnderTest.loadExempt();
			assertTrue(!classUnderTest.exempt.isEmpty());
			Product p = new Product("1 book at 12.49", classUnderTest.exempt, classUnderTest.df);
			float tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 12.49f);
			assertTrue(tax == 0.00f);
			p = new Product("1 music CD at 14.99", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 16.49f);
			assertTrue(tax == 1.50f);
			p = new Product("1 chocolate bar at 0.85", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 0.85f);
			assertTrue(tax == 0.00f);
			p = new Product("1 imported box of chocolates at 10.00", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 10.50f);
			assertTrue(tax == 0.50f);
			p = new Product("1 imported bottle of perfume at 47.50", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 54.65f);
			assertTrue(tax == 7.15f);
			p = new Product("1 imported bottle of perfume at 27.99", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 32.19f);
			assertTrue(tax == 4.2f);
			p = new Product("1 bottle of perfume at 18.99", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 20.89f);
			assertTrue(tax == 1.90f);
			p = new Product("1 packet of headache pills at 9.75", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 9.75f);
			assertTrue(tax == 0.0f);
			p = new Product("1 box of imported chocolates at 11.25", classUnderTest.exempt, classUnderTest.df);
			tax = classUnderTest.calculateTaxes(p);
			assertTrue(p.getPrice() == 11.80f);
			assertTrue(tax == 0.55f);	
		}catch(Exception e){
			assertTrue(false);
		}
	}
    
}
