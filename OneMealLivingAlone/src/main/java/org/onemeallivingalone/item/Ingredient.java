package org.onemeallivingalone.item;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	public Ingredient(int ingredientId, String name, int price) {
		this.ingredientId = ingredientId;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Ingredient other = (Ingredient) obj;
		return Integer.compare(this.ingredientId, other.ingredientId) == 0
				&& this.name.equals(other.name)
				&& Integer.compare(this.price, other.price) == 0;
	}

	@JsonIgnore
	public String getDescription() {
		return String.format("ID:%d %s(%d 원)", ingredientId, name, price);
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
