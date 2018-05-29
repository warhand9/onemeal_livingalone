package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;

public class FoodList {
	
	private static class SingletonHolder{
		private static final FoodList INSTANCE = new FoodList();
	}
	
	/** BillPlanItem.itemId 문자열을 key로, BillPlanItem 객체를 value로 하는 TreeMap */
	private final TreeMap<Integer, Food> foods = new TreeMap<Integer, Food>();
	
	
	public static void clear() {
		getInstance().foods.clear();
	}
	
	public static Food get(int foodId){
		return getInstance().foods.get(foodId);
	}
	
	private static FoodList getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static Collection<Food> getvalues(){
		return getInstance().foods.values();
	}
	
	public static Food put(Food food){
		return getInstance().foods.put(food.getItemId(), food);
	}
	public static Food remove(Integer foodId){
		return getInstance().foods.remove(foodId);
	}
	
	
	public static ArrayList<Integer> getHighGradeFoods()
	{
		
	}
	public static ArrayList<Integer> filteringByName(String food_name)
	{
		
	}
	public static ArrayList<Integer> filteringByIngredients(ArrayList<Integer> searchIngredients)
	{
		
	}
	
	public static void printHighGradeFoods()
	{
		
	}

}
