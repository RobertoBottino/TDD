package ch.alten.beans;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 
 *	Author: Roberto Bottino 
 * 
 */

public class Product {
	
	int quantity;
	String name;
	float price;
	boolean imported;
	boolean exempt;
	boolean wrongProduct;
	DecimalFormat df;
	
	public Product(String row, HashSet<String> exempt, DecimalFormat df) {
		super();
		this.df = df;
		this.wrongProduct = true;
		Matcher m = Pattern.compile("(\\d+)([a-zA-Z ]+)(\\d+.\\d+)").matcher(row);
		if(m.matches())
		{
			this.name = m.group(2);
			if(this.name.indexOf(" at ") != -1){
				this.wrongProduct = false;
				this.quantity = Integer.parseInt(m.group(1));
				this.price = Float.parseFloat(m.group(3));
				this.name = this.name.replaceFirst(" at ", ": ");
				
				if(this.name.indexOf("imported") != -1){
					this.name = this.name.replaceFirst(" imported ", " ");
					this.imported = true;
				}else{
					this.imported = false;
				}
				
				Scanner nameScanner = new Scanner(this.name);
				this.exempt = false;
				
				while(nameScanner.hasNext()){
					String nameScanned = nameScanner.next();
					if(nameScanned.indexOf(":") != -1){
						nameScanned = nameScanned.substring(0, nameScanned.length()-1);
					}
					if(exempt.contains(nameScanned)){
						this.exempt = true;
					}	
				}
				
				nameScanner.close();
			}
		}
	}
	
	
	public boolean isImported() {
		return imported;
	}
	public void setImported(boolean imported) {
		this.imported = imported;
	}
	public boolean isExempt() {
		return exempt;
	}
	public void setExempt(boolean exempt) {
		this.exempt = exempt;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public boolean isWrongProduct() {
		return wrongProduct;
	}

	public void setWrongProduct(boolean wrongProduct) {
		this.wrongProduct = wrongProduct;
	}

	public DecimalFormat getDf() {
		return df;
	}

	public void setDf(DecimalFormat df) {
		this.df = df;
	}

	
	@Override
	public String toString() {
		if(imported){
			return quantity + " imported"+ name + df.format(price);
		}
		else{
			return quantity + name + df.format(price);
		}	
	}
	
}
