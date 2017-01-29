package ch.alten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

import ch.alten.beans.Product;

/*
 * 
 *	Author: Roberto Bottino 
 * 
 */

public class Run {
	
	public HashSet<String> exempt = new HashSet<String>();
	public float salesTaxes;
	public float totalPrice;
	public DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
	
	public static void main(String[] args) throws IOException {
		
		Run rr = new Run();
		
		// load exempt products
		rr.loadExempt();
		
		try{
			if(args.length > 0) {
				FileInputStream fstream = new FileInputStream(args[0]);
				Scanner fileScanner = new Scanner(fstream);
				fileScanner.useDelimiter(System.getProperty("line.separator"));
				while (fileScanner.hasNextLine()) {
					while (fileScanner.hasNext()) {
						
						//bean creation
						Product product = new Product(fileScanner.next(), rr.exempt, rr.df);
						
						if(!product.isWrongProduct()){
							// calculate taxes
							float tax = rr.calculateTaxes(product);
							
							rr.totalPrice+=product.getPrice();
							rr.salesTaxes+=tax;
							
							// print receipt
							System.out.println(product.toString());
						}
					}
				}
				
				fileScanner.close();
				
				// print totals
				System.out.println("Sales Taxes: "+rr.df.format(rr.salesTaxes));
				System.out.println("Total: "+rr.df.format(rr.totalPrice));
			}
		} catch (FileNotFoundException e) {
		  System.out.println("Error at opening input file");
		  e.printStackTrace();
		}
	}
	
	public void loadExempt() throws IOException{
		URL exemptUrl = this.getClass().getClassLoader().getResource("Exempt.txt");
		Scanner exemptScanner = new Scanner(exemptUrl.openStream());
		while(exemptScanner.hasNextLine()){
			while(exemptScanner.hasNext()){
				exempt.add(exemptScanner.next());
			}
		}
		exemptScanner.close();
	}
	
	public float calculateTaxes(Product product){
		float tax = 0;
		if(product.isImported()){
			tax += Math.round((product.getPrice() * 5 / 100) * 20) / 20.0;   
		}
		if(!product.isExempt()){
			tax += Math.round((product.getPrice() * 10 / 100) * 20) / 20.0;  
		}
		product.setPrice(product.getPrice()+tax);
		return tax;
	}
	
}
