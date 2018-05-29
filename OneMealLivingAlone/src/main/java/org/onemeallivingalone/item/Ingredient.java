package org.onemeallivingalone.item;

import java.util.Comparator;

public class Ingredient {
	int ingredientId;
	String name;
	int price;
	

	String getDescription()
	{}

	public int getItemId() {
		return ingredientId;
	}
	
	
	/** 상품 ID 기준 내림차순 비교기.
	 */
	public static class IdDescendingComparator implements Comparator<Food> {

		@Override
		public int compare(Food a, Food b) {
			int comp = b.foodId - a.foodId;
			if (comp < 0)
				return -1;
			if (comp > 0)
				return 1;
			return 0;
		}
		
	}
}
