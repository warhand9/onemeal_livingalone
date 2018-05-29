package org.onemeallivingalone.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.onemeallivingalone.item.FoodReview;
import org.onemeallivingalone.item.FoodReviewList;

public class FoodReviewList {
	
	private static class SingletonHolder{
		private static final FoodReviewList INSTANCE = new FoodReviewList();
	}
	
	/** FoodReview.itemId 문자열을 key로, FoodReview 객체를 value로 하는 TreeMap */
	private final TreeMap<Integer, FoodReview> foodReviews = new TreeMap<Integer, FoodReview>();
	
	
	public static void clear() {
		getInstance().foodReviews.clear();
	}
	
	public static FoodReview get(int ingredientId){
		return getInstance().foodReviews.get(ingredientId);
	}
	
	private static FoodReviewList getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static Collection<FoodReview> getvalues(){
		return getInstance().foodReviews.values();
	}
	
	public static FoodReview put(FoodReview foodReview){
		return getInstance().foodReviews.put(foodReview.getItemId(), foodReview);
	}
	public static FoodReview remove(Integer foodReviewId){
		return getInstance().foodReviews.remove(foodReviewId);
	}
	
	
}
