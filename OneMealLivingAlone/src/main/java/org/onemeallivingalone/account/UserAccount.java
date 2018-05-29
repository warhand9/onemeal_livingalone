package org.onemeallivingalone.account;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 이용자 계정을 나타내는 클래스.
 *
 * 계정 ID와 비밀번호 외에도 이메일 주소, 개인 식재료 목록, 관심 요리 목록, 요리 리뷰 목록을 갖고 있습니다.
 * 
 * @see AdminAccount
 */
public class UserAccount extends Account {

	private static final String EMAIL_REGEX = "^[_a-zA-Z0-9\\.+-]+@[_a-zA-Z0-9\\.+-]+\\.[a-zA-Z]+$"; /// < 이메일 정규 표현식
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX); /// < 이메일 Pattern 객체

	private String email; /// < 이메일 주소
	private List<Integer> personalIngredients = new ArrayList<Integer>(); /// < 개인 식재료 목록
	private List<Integer> personalFavoriteFoods = new ArrayList<Integer>(); /// < 관심 요리 목록
	private List<Integer> personalFoodReviews = new ArrayList<Integer>(); /// < 요리 리뷰 목록

	public UserAccount(String accountId, String pw, String email) throws IllegalArgumentException {
		super(accountId, pw);
		if (!checkEmailValidity(email))
			throw new IllegalArgumentException();
		this.email = email;
	}

	public void printPersonalIngredients() {
	}

	public void printPersonalFavoriteFoods() {
	}

	public void printpersonalFoodReviews() {
	}

	public void addPersonalIngredient(Integer ingredientId) {
		personalIngredients.add(0, ingredientId);
	}

	public void deletePersonalIngredient() {
	}

	public void addPeronsalFood() {
	}

	public void deletePersonalFood() {
	}

	public void addFoodReview() {
	}

	public void deleteFoodReview() {
	}

	/**
	 * 계정의 이메일 주소를 변경합니다.
	 * 
	 * 새로운 이메일 주소는 checkEmailValidity() 를 통해, 유효성을 검사하게 됩니다. 비밀번호가 계정 ID와 같거나 유효하지
	 * 않을 경우, 계정의 비밀번호는 바뀌지 않습니다.
	 * 
	 * @param newEmail
	 *            새로운 이메일 주소
	 * @return 이메일 주소의 변경 여부
	 */
	public boolean changeEmail(String newEmail) {
		if (!checkEmailValidity(newEmail))
			return false;
		this.email = newEmail;
		return true;
	}

	/**
	 * 이메일 주소의 유효성을 검사합니다.
	 * 
	 * 이메일 주소는 UserAccount.EMAIL_REGEX 에 지정된 정규 표현식에 맞아야 합니다.
	 * 
	 * @param email
	 *            이메일 주소
	 * @return 이메일 주소의 유효성 여부
	 */
	public static boolean checkEmailValidity(String email) {
		if (email == null)
			return false;
		return EMAIL_PATTERN.matcher(email).matches();
	}

	@Override
	public boolean equals(Object another) {
		return another instanceof UserAccount && super.equals(another) && email.equals(((UserAccount) another).email)
				&& personalIngredients.equals(((UserAccount) another).personalIngredients)
				&& personalFavoriteFoods.equals(((UserAccount) another).personalFavoriteFoods)
				&& personalFoodReviews.equals(((UserAccount) another).personalFoodReviews);
	}

	/**
	 * 개인 식재료 목록의 reference를 반환합니다.
	 * 
	 * 각 요소는 Integer 타입의 상품 ID로 이루어져 있습니다.
	 * 
	 * @return 개인 식재료 목록
	 */
	public List<Integer> getPersonalIngredients() {
		return personalIngredients;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * 관심 요리 목록의 reference를 반환합니다.
	 * 
	 * 각 요소는 Integer 타입의 상품 ID로 이루어져 있습니다.
	 * 
	 * @return 관심 요리 목록
	 */
	public List<Integer> getPersonalFavoriteFoods() {
		return personalFavoriteFoods;
	}

	/**
	 * 요리 리뷰 목록의 reference를 반환합니다.
	 * 
	 * 각 요소는 Integer 타입의 상품 ID로 이루어져 있습니다.
	 * 
	 * @return 요리 리뷰 목록
	 */
	public List<Integer> getPersonalFoodReviews() {
		return personalFoodReviews;
	}
}
