package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.onemeallivingalone.item.Ingredient;
import org.onemeallivingalone.item.IngredientList;

public class IngredientList {
	
	private static class SingletonHolder{
		private static final IngredientList INSTANCE = new IngredientList();
	}
	
	/** BillPlanItem.itemId 문자열을 key로, BillPlanItem 객체를 value로 하는 TreeMap */
	private final TreeMap<Integer, Ingredient> ingredients = new TreeMap<Integer, Ingredient>();
	
	
	public static void clear() {
		getInstance().ingredients.clear();
	}
	
	public static Ingredient get(int ingredientId){
		return getInstance().ingredients.get(ingredientId);
	}
	
	private static IngredientList getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static Collection<Ingredient> getvalues(){
		return getInstance().ingredients.values();
	}
	
	public static Ingredient put(Ingredient ingredient){
		return getInstance().ingredients.put(ingredient.getItemId(), ingredient);
	}
	public static Ingredient remove(Integer ingredientId){
		return getInstance().ingredients.remove(ingredientId);
	}
	
	
	public static Ingredient searchByName(String ingredientName)
	{
		
	}
}

