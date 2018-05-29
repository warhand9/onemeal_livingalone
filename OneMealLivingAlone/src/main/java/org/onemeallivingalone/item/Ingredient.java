package org.onemeallivingalone.item;

import java.util.Comparator;

public class Ingredient {

	/**
	 * 식재료 ID 기준 내림차순 비교기.
	 */
	public static class IdDescendingComparator implements Comparator<Ingredient> {

		@Override
		public int compare(Ingredient a, Ingredient b) {
			return Integer.compare(b.ingredientId, a.ingredientId);
		}

	}

	private final int ingredientId;
	private String name;
	private int price;

	public Ingredient(int id, String name, int price) {
		this.ingredientId = id;
		this.name = name;
		this.price = price;
	}

	public String getDescription() {
		return String.format("%s(%d 원)", name, price);
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%d %s %d", ingredientId, name, price);
	}

}
