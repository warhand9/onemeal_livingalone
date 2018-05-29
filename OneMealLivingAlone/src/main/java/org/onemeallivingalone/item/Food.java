package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Food {

	public static class AverageGradeDescendingComparator implements Comparator<Food> {

		@Override
		public int compare(Food a, Food b) {
			return Double.compare(b.averageGrade, a.averageGrade);
		}

	}

	public static class IdDescendingComparator implements Comparator<Food> {

		@Override
		public int compare(Food a, Food b) {
			return Integer.compare(b.foodId, a.foodId);
		}

	}

	private final int foodId;
	private String name;
	private int cookingTime;
	private String recipe;
	private int cookingCost = 0;
	private final List<Integer> ingredients = new ArrayList<Integer>();
	private double averageGrade = 0.0;
	private final List<Integer> reviews = new ArrayList<Integer>();

	public Food(int foodId, String name, int cookingTime, String recipe) {
		this.foodId = foodId;
		this.name = name;
		this.cookingTime = cookingTime;
		this.recipe = recipe;
	}

	public String getDetailedDescription() {
		// print all information
		// foodID / name / cookingTime / cookingCost / ingredients / recipe / 평균평점 /
		// allReviews

		// Get the bulk description of ingredients and update averageGrade;
		String ingreBulkDescription = null;
		if (ingredients.isEmpty()) {
			ingreBulkDescription = "";
			cookingCost = 0;
		} else {
			List<String> ingreBuilder = new ArrayList<>();
			int ingredientPriceSum = 0;
			for (Integer ingredientId : ingredients) {
				Ingredient ingredient = IngredientList.getInstance().get(ingredientId);
				ingreBuilder.add(ingredient.getDescription());
				ingredientPriceSum += ingredient.getPrice();
			}
			ingreBulkDescription = String.join(", ", ingreBuilder);
			cookingCost = ingredientPriceSum;
		}

		// Get all the reviews and update averageGrade
		String reviewsBulk = null;
		if (reviews.isEmpty()) {
			reviewsBulk = "";
			averageGrade = 0.0;
		} else {
			List<String> reviewBuilder = new ArrayList<>();
			int gradeSum = 0;
			for (Integer reviewId : reviews) {
				FoodReview review = FoodReviewList.getInstance().get(reviewId);
				reviewBuilder.add(review.getDescriptionWithoutFoodName());
				gradeSum += review.getGrade();
			}
			reviewsBulk = String.join("\n\n", reviewBuilder);
			averageGrade = (double) gradeSum / reviews.size();
		}

		return String.format("음식 ID: %d\n음식 이름: %s\n조리 시간: %d 분\n예상 조리 비용: %d 원\n식재료: %s\n"
				+ "평균 점수: %d 점\n조리법:\n%s\n\n후기:\n%s",
				foodId, name, cookingTime, cookingCost, ingreBulkDescription, averageGrade, recipe, reviewsBulk);
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public String getSummaryDescription() {
		// foodID / name / cookingTime / cookingCost
		return String.format("%s / %s / 시간: %d 분 / 가격: %d 원 / 점수: %.1lf 점", foodId, name, cookingTime,
				cookingCost);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCookingTime() {
		return cookingTime;
	}

	public void recalculateAverageGrade() {
		if (reviews.isEmpty()) {
			averageGrade = 0.0;
		} else {
			int gradeSum = 0;
			for (Integer reviewId : reviews) {
				FoodReview review = FoodReviewList.getInstance().get(reviewId);
				gradeSum += review.getGrade();
			}
			averageGrade = (double) gradeSum / reviews.size();
		}
	}

	public void recalculateCookingCost() {
		if (ingredients.isEmpty()) {
			cookingCost = 0;
		} else {
			int ingredientPriceSum = 0;
			for (Integer ingredientId : ingredients) {
				Ingredient ingredient = IngredientList.getInstance().get(ingredientId);
				ingredientPriceSum += ingredient.getPrice();
			}
			cookingCost = ingredientPriceSum;
		}
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public int getCookingCost() {
		return cookingCost;
	}

	public void setCookingCost(int cookingCost) {
		this.cookingCost = cookingCost;
	}

	public List<Integer> getIngredients() {
		return ingredients;
	}

	public List<Integer> getReviews() {
		return reviews;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setAverageGrade(int averageGrade) {
		this.averageGrade = averageGrade;
	}

}
