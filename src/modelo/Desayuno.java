package modelo;

public class Desayuno {

	private int numCarta;
	private String name;
	private double price;
	private String description;
	private int calories;
	public Desayuno(int numCarta, String name, double price, String description, int calories) {
		this.numCarta = numCarta;
		this.name = name;
		this.price = price;
		this.description = description;
		this.calories = calories;
	}
	public int getNumCarta() {
		return numCarta;
	}
	public void setNumCarta(int numCarta) {
		this.numCarta = numCarta;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	
	public String toString() {
		return "\n\nCarta: " + numCarta + "\nName: " +  name + "\nPrice: " + price + "\nDescription: " + description + "\nCalories: " + calories;
	}
	
}
