// 2017 PhoneSajo
package org.onemeallivingalone.center;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AdminAccount;

/** 현재 로그인된 계정 클래스.
 * 
 * 현재 시스템에 로그인된 계정을 나타냅니다. null일 경우, 로그인되어 있지 않은 상태입니다.
 */
public class CurrentUser {

	private Account loggedIn = null; ///< 현재 로그인 된 계정
	
	/** 싱글톤 홀더로부터 인스턴스 객체를 얻어옵니다. 
	 */
	private static CurrentUser getInstance() {
		return SingletonHolder.INSTANCE;
	}

	/** 현재 계정을 나타내는 Account 객체를 반환합니다.
	 * 
	 * 아무 계정도 로그인되어 있지 않은 경우, null을 반환합니다.
	 * @return 현재 계정을 나타내는 Account 객체
	 */
	public static Account get() {
		return getInstance().loggedIn;
	}
	
	/** 현재 로그인 상태를 나타내는 메시지를 반환합니다.
	 * 
	 * 관리자( AdminAccount )일 경우, '관리자'가 표시됩니다.
	 * @return 현재 로그인 상태 메시지
	 */
	public static String getStatusString() {
		// 로그아웃된 상태 출력
		if (getInstance().loggedIn == null)
			return "현재 로그인되어 있지 않습니다.";
	
		// 회원 이름 선택
		String userName = getInstance().loggedIn.getAccountId();
		if (getInstance().loggedIn instanceof AdminAccount)
			userName += " (관리자)";
	
		return "환영합니다, " + userName + " 님!";
	}

	/** 현재 로그인 상태를 다른 계정으로 변경합니다. 
	 * 
	 * 인자로 null을 전달하는 것은 로그아웃을 의미합니다.
	 * @param loggedIn 로그인할 계정을 나타내는 Account 객체
	 */
	public static void set(Account loggedIn) {
		getInstance().loggedIn = loggedIn;
	}

	/** 싱글톤 홀더 내부 클래스
	 */
	private static class SingletonHolder {
		private static final CurrentUser INSTANCE = new CurrentUser();
	}
}
