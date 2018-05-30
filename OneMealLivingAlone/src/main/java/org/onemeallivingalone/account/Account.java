package org.onemeallivingalone.account;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 사용자 계정을 나타내는 추상 클래스.
 * 
 * 계정 ID와 비밀번호를 특성으로 갖고 있습니다.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = AdminAccount.class, name = "admin"),
		@JsonSubTypes.Type(value = CustomerAccount.class, name = "customer")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Account {

	private final String accountId; /// < 계정 ID
	@JsonProperty("password")
	private String password; /// < 비밀번호

	public Account(String accountId, String password) throws IllegalArgumentException {
		if (!checkAccountIdValidity(accountId) || !checkPasswordValidity(password)) {
			throw new IllegalArgumentException();
		}
		this.accountId = accountId.toLowerCase();
		this.password = password;
	}

	/**
	 * 계정 ID 기준 오름차순 비교기.
	 */
	public static class IdAscendingComparator implements Comparator<Account> {

		@Override
		public int compare(Account a, Account b) {
			return a.accountId.compareTo(b.accountId);
		}

	}

	/**
	 * 계정 ID의 유효성을 검사합니다.
	 * 
	 * 계정 ID는 알파벳으로 시작해야 하며, 알파벳, 숫자, 밑줄('_')로 구성돼야 합니다.
	 * 
	 * @param id
	 *            계정 ID
	 * @return 계정 ID의 유효성 여부
	 */
	public static boolean checkAccountIdValidity(String id) {
		if (id == null)
			return false;
		if (id.isEmpty() || !Character.isAlphabetic(id.charAt(0)))
			return false;
		for (char ch : id.toCharArray()) {
			if (!Character.isAlphabetic(ch) && !Character.isDigit(ch) && ch != '_')
				return false;
		}
		return true;
	}

	/**
	 * 비밀번호의 유효성을 검사합니다.
	 * 
	 * 비밀번호는 알파벳, 숫자, 특수문자 중 두 가지 이상으로 구성돼야 하며, 길이는 8자리 이상이어야 합니다.
	 * 
	 * @param pw
	 *            비밀번호
	 * @return 비밀번호의 유효성 여부
	 */
	public static boolean checkPasswordValidity(String pw) {
		if (pw == null)
			return false;

		if (pw.length() < 8)
			return false;

		String numberstring = "0123456789";
		String characterstring = "abcdefghijklmnopqrstuvwxyzABCDEFGHIZKLMNOPQRSTUVWXYZ";
		String specialstring = "!@#$%^&*_";
		boolean numbercheck = false;
		boolean charcheck = false;
		boolean specialcheck = false;
		int i;

		for (i = 0; i < pw.length(); i++) {
			String charToString = Character.toString(pw.charAt(i));

			if (numberstring.indexOf(charToString) != -1)
				numbercheck = true;
			if (characterstring.indexOf(charToString) != -1)
				charcheck = true;
			if (specialstring.indexOf(charToString) != -1)
				specialcheck = true;
		}

		if ((!numbercheck && !charcheck) || (!numbercheck && !specialcheck) || (!charcheck && !specialcheck))
			return false;

		return true;
	}

	/**
	 * 계정의 비밀번호를 변경합니다.
	 *
	 * 새로운 비밀번호는 계정 ID와 동일할 수 없습니다. 또한 checkPasswordValidity()를 통해, 유효성을 검사하게 됩니다.
	 * 비밀번호가 계정 ID와 같거나 유효하지 않을 경우, 계정의 비밀번호는 바뀌지 않습니다.
	 * 
	 * @param newPw
	 *            새로운 비밀번호
	 * @return 비밀번호의 변경 여부
	 */
	public boolean changePassword(String newPw) {
		// 계정 ID와 비밀번호가 같거나, 비밀번호 유효성 검사를 실패하면 바꾸지 않음
		if (accountId.equals(newPw) || !checkPasswordValidity(newPw))
			return false;
		this.password = newPw;
		return true;
	}

	/**
	 * 사용자가 입력한 비밀번호와 일치하는지 조사합니다.
	 * 
	 * @param pw
	 *            사용자가 입력한 비밀번호
	 * @return 비밀번호 일치 여부
	 */
	public boolean comparePassword(String pw) {
		return password.equals(pw);
	}

	@Override
	public boolean equals(Object another) {
		return another instanceof Account && accountId.equals(((Account) another).accountId)
				&& password.equals(((Account) another).password);
	}

	public String getAccountId() {
		return accountId;
	}

}
