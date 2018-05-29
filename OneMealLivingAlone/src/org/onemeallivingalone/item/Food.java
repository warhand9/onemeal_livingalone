package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Comparator;

import org.phonesajo.item.Item;

public class Food {
	int foodId;
	String name;
	int cookingTime;
	int cookingCost;
	ArrayList<Integer> ingredients;
	String receipe;
	int averageGrade;
	ArrayList<Integer> reviews;
	
	int getEstimatedPrice()
	{}

	int getAverageGrade()
	{}
	
	String getSummaryDescription()
	{
		// foodID / name / cookingTime / cookingCost
	}
	String getDetailedDescription()
	{
		// print all information
		// foodID / name / cookingTime / cookingCost / ingredients / receipe / 평균평점 / allReviews
	}
	
	void showReviews()
	{
		
	}
	
	public int getItemId() {
		return foodId;
	}
	
	
	boolean equals(Object another) {
		
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
	
	/** 상품 조회수 기준 내림차순 비교기
	 */
	public static class AverageGradeComparator implements Comparator<Food> {

		@Override
		public int compare(Food a, Food b) {
			int comp = b.averageGrade - a.averageGrade;
			if (comp < 0)
				return -1;
			if (comp > 0)
				return 1;
			return 0;
		}
		
	}
}
