package org.onemeallivingalone.item;


public class FoodIndexByIngredient implements Comparable<FoodIndexByIngredient> {

	private final double sameness;
	private final int extraCost;
	private final Food food;
	
	public FoodIndexByIngredient(double sameness, int extraCost, Food food) {
		this.sameness = sameness;
		this.extraCost = extraCost;
		this.food = food;
	}
	
	@Override
	public int compareTo(FoodIndexByIngredient other) {
		int result = -Double.compare(this.sameness, other.sameness);
		if (result == 0) {
			result = Integer.compare(this.extraCost, other.extraCost);
		}
		return result;
	}
	
	public int getExtraCost() {
		return extraCost;
	}

	public Food getFood() {
		return food;
	}
	
	public double getSameness() {
		return sameness;
	}

}
