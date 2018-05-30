package org.onemeallivingalone.item;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FoodReview {

	private final int reviewId;
	private final String userId;
	private final int foodId;
	private int grade;
	private String review;

	public FoodReview(int reviewId, String userId, int foodId, int grade, String review) {
		this.reviewId = reviewId;
		this.userId = userId;
		this.foodId = foodId;
		this.grade = grade;
		this.review = review;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		FoodReview other = (FoodReview) obj;
		return Integer.compare(this.reviewId, other.reviewId) == 0
				&& this.userId.equals(other.userId)
				&& Integer.compare(this.foodId, other.foodId) == 0
				&& Integer.compare(this.grade, other.grade) == 0
				&& this.review.equals(other.review);
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@JsonIgnore
	public String getDescription() {
		Food food = FoodList.getInstance().get(foodId);
		return String.format("요리 이름: %d\n%s", food.getName(), getDescriptionWithoutFoodName());
	}
	
	@JsonIgnore
	public String getDescriptionWithoutFoodName() {
		return String.format("이용자 ID: %s\n점수: %d 점\n%s", userId, grade, review);
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getReviewId() {
		return reviewId;
	}

	public String getUserId() {
		return userId;
	}

	public int getFoodId() {
		return foodId;
	}

}
