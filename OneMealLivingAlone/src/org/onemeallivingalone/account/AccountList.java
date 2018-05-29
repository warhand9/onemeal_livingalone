package org.onemeallivingalone.account;

import java.util.Collection;
import java.util.HashMap;

/** 파일로부터 읽어들인 Account 객체들을 보관하는 싱글톤 클래스.
 * 
 * Gson 라이브러리가 Reflection을 사용하므로, accounts를 Map<K, V> 타입으로 바꾸지 말아야 합니다.
 * @see BillPlanItemList
 * @see PhoneItemList
 * @see TerminologyList
 */
public class AccountList {
	
	/** 싱글톤 홀더 클래스.
	 */
	private static class SingletonHolder {
		private static final AccountList INSTANCE = new AccountList();
	}
	
	/** Account.accountId 문자열을 key로, Account 객체를 value로 하는 HashMap */
	private final HashMap<String, Account> accounts = new HashMap<String, Account>();
	
	public static void clear() {
		getInstance().accounts.clear();
	}
	
	public static Account get(String accountId) {
		return getInstance().accounts.get(accountId);
	}
	
	private static AccountList getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public static Collection<Account> getValues() {
		return getInstance().accounts.values();
	}
	
	public static Account put(Account account) {
		return getInstance().accounts.put(account.getAccountId(), account);
	}
	
	public static Account remove(String accountId) {
		return getInstance().accounts.remove(accountId);
	}

}
