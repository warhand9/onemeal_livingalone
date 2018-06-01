package org.onemeallivingalone.item;

import java.util.Collection;
import java.util.TreeMap;

public class IngredientList {

	private static class SingletonHolder {

		private static final IngredientList INSTANCE = new IngredientList();

	}

	private final TreeMap<Integer, Ingredient> ingredients = new TreeMap<Integer, Ingredient>();

	public void clear() {
		ingredients.clear();
	}

	public Ingredient get(int ingredientId) {
		return ingredients.get(ingredientId);
	}

	public static IngredientList getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public Collection<Ingredient> getvalues() {
		return ingredients.values();
	}

	public void loadAll(Collection<Object> records) throws IllegalArgumentException {
		this.ingredients.clear();
		for (Object record : records) {
			if (record instanceof Ingredient) {
				Ingredient ingredient = (Ingredient) record;
				this.ingredients.put(ingredient.getIngredientId(), ingredient);
			} else {
				this.ingredients.clear();
				throw new IllegalArgumentException("The element type must be Ingredient!");
			}
		}
	}

	public Ingredient put(Ingredient ingredient) {
		return ingredients.put(ingredient.getIngredientId(), ingredient);
	}

	public Ingredient remove(Integer ingredientId) {
		return ingredients.remove(ingredientId);
	}

	/*public Collection<Ingredient> searchByName(String ingredientName) {
		List<Ingredient> result = new ArrayList<>();
		for (Ingredient ingre : ingredients.values()) {
			if (ingre.getName().contains(ingredientName)) {
				result.add(ingre);
			}
		}
		return result;
	}
	*/
	public Ingredient searchByName(String ingredientName) {
		Ingredient result = null;
		for (Ingredient ingre : ingredients.values()) {
			if (ingre.getName().equals(ingredientName)) {
				result = ingre;
				break;
			}
		}
		return result;
	}

}
