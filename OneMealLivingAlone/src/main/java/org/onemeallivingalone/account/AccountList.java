package org.onemeallivingalone.account;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
	
	public static void loadAllStatic(Collection<Object> records) throws IllegalArgumentException {
		Map<String, Account> thisAccounts = getInstance().accounts;
			thisAccounts.clear();
		for (Object record : records) {
			if (record instanceof Account) {
				Account account = (Account) record;
				thisAccounts.put(account.getAccountId(), account);
			} else {
				thisAccounts.clear();
				throw new IllegalArgumentException("The element type must be Account!");
			}
		}
	}
	
	public static Account put(Account account) {
		return getInstance().accounts.put(account.getAccountId(), account);
	}
	
	public static Account remove(String accountId) {
		return getInstance().accounts.remove(accountId);
	}

}
