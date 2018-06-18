package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class FoodList {

	public static class FoodSearchByIngredientResult {

		private final List<FoodIndexByIngredient> foodIndexes;
		private final int perfectMatchCount;
		private final int noExtraCostCount;

		public FoodSearchByIngredientResult(List<FoodIndexByIngredient> foodIndexes, int perfectMatchCount,
				int noExtraCostCount) {
			this.foodIndexes = foodIndexes;
			this.perfectMatchCount = perfectMatchCount;
			this.noExtraCostCount = noExtraCostCount;
		}

		public List<FoodIndexByIngredient> getFoodIndexes() {
			return foodIndexes;
		}

		public int getNoExtraCostCount() {
			return noExtraCostCount;
		}

		public int getPerfectMatchCount() {
			return perfectMatchCount;
		}

	}

	private static class SingletonHolder {

		private static final FoodList INSTANCE = new FoodList();

	}

	private static final double INGREDIENT_SEARCH_FACTOR = 0.5;

	public static FoodList getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final TreeMap<Integer, Food> foods = new TreeMap<Integer, Food>();

	public void clear() {
		foods.clear();
	}

	public FoodSearchByIngredientResult filteringByIngredients(Collection<Integer> keyIngredients) {
		// Check the emptiness of the argument list
		if (keyIngredients.isEmpty()) {
			return new FoodSearchByIngredientResult(Collections.emptyList(), 0, 0);
		}

		List<FoodIndexByIngredient> foodPairsByIngre = new ArrayList<>();

		// Search by the sameness factor
		int perfectMatchCount = 0;
		int noExtraCostCount = 0;
		for (Food food : foods.values()) {
			Collection<Integer> ingreIds = food.getIngredients();
			if (!ingreIds.isEmpty()) {
				int matched = 0;
				int extraCost = food.getCookingCost();
				for (Integer keyIngreId : keyIngredients) {
					if (ingreIds.contains(keyIngreId)) {
						matched++;
						extraCost -= IngredientList.getInstance().get(keyIngreId).getPrice();
					}
				}

				double sameness = (double) matched / keyIngredients.size();
				if (Double.compare(sameness, INGREDIENT_SEARCH_FACTOR) >= 0) {
					foodPairsByIngre.add(new FoodIndexByIngredient(sameness, extraCost, food));

					if (matched == keyIngredients.size()) {
						perfectMatchCount++;
						if (extraCost <= 0) {
							noExtraCostCount++;
						}
					}
				}
			}
		}

		// Sort the result list
		Collections.sort(foodPairsByIngre);

		return new FoodSearchByIngredientResult(foodPairsByIngre, perfectMatchCount, noExtraCostCount);
	}

	public Collection<Food> filteringByName(String foodName) {
		List<Food> foodsSearched = new ArrayList<>();
		for (Food food : foods.values()) {
			if (food.getName().contains(foodName)) {
				foodsSearched.add(food);
			}
		}
		return foodsSearched;
	}

	public Food get(int foodId) {
		return foods.get(foodId);
	}

	public Collection<Food> getHighGradeFoods() {
		List<Food> foodsSortedByGrade = new ArrayList<>(foods.values());
		Collections.sort(foodsSortedByGrade, new Food.AverageGradeDescendingComparator());
		return foodsSortedByGrade;
	}

	public Collection<Food> getvalues() {
		return foods.values();
	}

	public void loadAll(Collection<Object> records) throws IllegalArgumentException {
		this.foods.clear();
		for (Object record : records) {
			if (record instanceof Food) {
				Food food = (Food) record;
				this.foods.put(food.getFoodId(), food);
			} else {
				this.foods.clear();
				throw new IllegalArgumentException("The element type must be Food!");
			}
		}
	}

	public Food put(Food food) {
		return foods.put(food.getFoodId(), food);
	}

	public Food remove(Integer foodId) {
		return foods.remove(foodId);
	}

}
