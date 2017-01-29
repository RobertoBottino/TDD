package com.lastminute.salestaxes;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class SalesTaxes {
	
	float salesTaxes = 0;
	float price;
	float total = 0;
	int quantity = 0;
	boolean isImported = false;
	boolean isExempt = false;
	String line="";
	String exempt="";
	DecimalFormat df = new DecimalFormat("0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
	Charset encoding = Charset.forName("UTF-8");
	
	public SalesTaxes(){
			
	}
		
	public static void main(String args[]) throws Exception
	{    
		SalesTaxes taxes= new SalesTaxes();
		
		try{
			
			taxes.loadExempt();
			Scanner fileScanner = new Scanner(taxes.loadInput());

			while (fileScanner.hasNextLine()) {
				while (fileScanner.hasNext()) { 
					if (fileScanner.hasNextInt()) { 
						taxes.quantity = fileScanner.nextInt();
						taxes.line+= taxes.quantity;
					}
					else if(fileScanner.hasNext()){
						String wordScanned = fileScanner.next();
						if(wordScanned.matches("at")){
							taxes.line+=": ";
						}else if(wordScanned.matches("\\d+.\\d+")){
							taxes.calculateTaxesForProduct(wordScanned);
						}
						else{
							taxes.checkForImportedAndExemption(wordScanned);
						}
					}
				}                    
			}
			
			fileScanner.close();
			taxes.printTotalAndSalesTaxes();

		}catch(Exception e){
			System.out.println("Error is: "+e.getMessage());
		}
	}
	
	public void loadExempt() throws Exception{
		Path path = Paths.get(ClassLoader.class.getResource("/Exempt.txt").toURI());
		exempt = new String(Files.readAllBytes(path), encoding);
	}
	
	public Path loadInput() throws Exception{
		Path input = Paths.get(ClassLoader.class.getResource("/Input.txt").toURI());
		return input;
	}
	
	public void calculateTaxesForProduct(String wordScanned) throws Exception{
		price = Float.parseFloat(wordScanned);
		float tax = 0;
		if(isImported){
			line = line.replaceFirst(""+ quantity, quantity + " imported");
			tax += Math.round((price * 5 / 100) * 20) / 20.0;   
		}
		if(!isExempt){
			tax += Math.round((price * 10 / 100) * 20) / 20.0;  
		}
		salesTaxes+=tax;
		total+=price+tax;
		line += df.format(price+tax);
		System.out.println(line);
		isImported=false;
		isExempt = false;
		line = "";
	}
	
	public void checkForImportedAndExemption(String wordScanned) throws Exception{
		if(wordScanned.equalsIgnoreCase("imported")){
			isImported = true;
		}else{
			if(exempt.indexOf(wordScanned) != -1){
				isExempt = true;
			}
			line += " " + wordScanned;
		}
	}
	
	public void printTotalAndSalesTaxes() throws Exception{
		System.out.println("Sales Taxes: "+df.format(salesTaxes));
		System.out.println("Total: "+df.format(total));
	}
}
