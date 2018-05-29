package org.onemeallivingalone.account;

import java.util.Collection;
import java.util.HashMap;

/** 파일로부터 읽어들인 Account 객체들을 보관하는 싱글톤 클래스.
 */
public class AccountList {
	
	/** 싱글톤 홀더 클래스.
	 */
	private static class SingletonHolder {
		private static final AccountList INSTANCE = new AccountList();
	}
	
	/** Account.accountId 문자열을 key로, Account 객체를 value로 하는 HashMap */
	private final HashMap<String, Account> accounts = new HashMap<String, Account>();
	
	public void clear() {
		accounts.clear();
	}
	
	public Account get(String accountId) {
		return accounts.get(accountId);
	}
	
	private static AccountList getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	public Collection<Account> getValues() {
		return accounts.values();
	}
	
	public Account put(Account account) {
		return accounts.put(account.getAccountId(), account);
	}
	
	public static Account remove(String accountId) {
		return getInstance().accounts.remove(accountId);
	}

}
