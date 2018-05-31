package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;

public class FoodList {

	private static class SingletonHolder {

		private static final FoodList INSTANCE = new FoodList();

	}

	private static final Double INGREDIENT_SEARCH_FACTOR = Double.valueOf(0.5);

	public static FoodList getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final TreeMap<Integer, Food> foods = new TreeMap<Integer, Food>();

	public void clear() {
		foods.clear();
	}

	public Collection<Food> filteringByIngredients(Collection<Integer> keyIngredients) {
		List<ImmutablePair<Double, Food>> foodsByIngredientPairs = new ArrayList<>();

		// Search by the factor
		for (Food food : foods.values()) {
			Collection<Integer> ingreIds = food.getIngredients();
			if (!ingreIds.isEmpty()) {
				int matched = 0;
				for (Integer ingreId : ingreIds) {
					if (keyIngredients.contains(ingreId)) {
						matched++;
					}
				}

				Double factor = Double.valueOf((double) matched / ingreIds.size());
				if (factor.compareTo(INGREDIENT_SEARCH_FACTOR) >= 0) {
					foodsByIngredientPairs.add(ImmutablePair.of(factor, food));
				}
			}
		}
		
		// Sort by the factor in descending order
		Collections.sort(foodsByIngredientPairs, Comparator.comparing(p -> -p.getKey()));
		
		// Convert pairs to a list of foods
		List<Food> foodsByIngredients = new ArrayList<>();
		for (ImmutablePair<Double, Food> pair : foodsByIngredientPairs) {
			foodsByIngredients.add(pair.getValue());
		}

		return foodsByIngredients;
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

	public Food put(Food food) {
		return foods.put(food.getFoodId(), food);
	}

	public Food remove(Integer foodId) {
		return foods.remove(foodId);
	}

}
