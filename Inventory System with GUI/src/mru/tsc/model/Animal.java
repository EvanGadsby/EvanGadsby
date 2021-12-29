package mru.tsc.model;

public class Animal extends Toy {
	/**
	 * This class is used to create Animal objects, this is useful for adding them to the arraylist. It inherits a lot of parameters from its super class: Toy
	 */
	String Material;
	char size;
	
	/**
	 * Constructor creates the animal based the parameters passed into it, most of which are from the Toy Superclass
	 * @param SN
	 * @param name
	 * @param brand
	 * @param price
	 * @param availableCount
	 * @param ageAppropriate
	 * @param mat
	 * @param Size
	 */
	
	public Animal(String SN, String name, String brand, double price, int availableCount, int ageAppropriate, String mat, String Size) {
		super(SN, name, brand, price, availableCount, ageAppropriate);
		 Material = mat;
		 size = Size.charAt(0);
		
	}
	
	// Getters and setters generated by Eclipse 
	public String getMaterial() {
		return Material;
	}

	public void setMaterial(String material) {
		Material = material;
	}

	public char getSize() {
		return size;
	}

	public void setSize(char size) {
		this.size = size;
	}
	
	// ToString method which allows the animal to appear in a readable form, generated by Eclipse
	@Override
	public String toString() {
		return "Animal [SN=" + SN + ", name=" + name + ", brand=" + brand + ", price=" + price + ", availableCount="
				+ availableCount + ", ageAppropraite=" + ageAppropraite + ", Material=" + Material + ", size=" + size
				+ "]";
	}

	// format which will be called and used when the arraylist is saved into the database
	public String format() {
		
		return SN + ";" + name + ";" + brand + ";" + price + ";" + availableCount + ";" + ageAppropraite + ";" + Material + ";" + size;
	
	}
	

}