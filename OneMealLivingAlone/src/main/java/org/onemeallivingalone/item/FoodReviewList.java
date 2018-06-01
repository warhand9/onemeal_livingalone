package org.onemeallivingalone.item;

import java.util.Collection;
import java.util.TreeMap;

public class FoodReviewList {

	private static class SingletonHolder {

		private static final FoodReviewList INSTANCE = new FoodReviewList();

	}

	/** FoodReview.reviewId 문자열을 key로, FoodReview 객체를 value로 하는 TreeMap */
	private final TreeMap<Integer, FoodReview> foodReviews = new TreeMap<Integer, FoodReview>();

	public void clear() {
		foodReviews.clear();
	}

	public FoodReview get(Integer foodReviewId) {
		return foodReviews.get(foodReviewId);
	}
	
	public static FoodReviewList getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public Collection<FoodReview> getvalues() {
		return foodReviews.values();
	}
	
	public void loadAll(Collection<Object> records) throws IllegalArgumentException {
		this.foodReviews.clear();
		for (Object record : records) {
			if (record instanceof FoodReview) {
				FoodReview review = (FoodReview) record;
				this.foodReviews.put(review.getReviewId(), review);
			} else {
				this.foodReviews.clear();
				throw new IllegalArgumentException("The element type must be FoodReview!");
			}
		}
	}

	public FoodReview put(FoodReview foodReview) {
		return foodReviews.put(foodReview.getReviewId(), foodReview);
	}

	public FoodReview remove(Integer foodReviewId) {
		return foodReviews.remove(foodReviewId);
	}

}
