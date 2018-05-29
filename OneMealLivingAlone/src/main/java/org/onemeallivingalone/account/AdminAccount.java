package org.onemeallivingalone.account;

import org.onemeallivingalone.account.Account;

/** 관리자 계정을 나타내는 클래스.
*
* Account와 다른 기능을 구현하고 있지는 않으나, 명시적으로 관리자 계정임을 의미합니다.
* @see CustomerAccount
*/
public class AdminAccount extends Account {

	public AdminAccount(String accountId, String pw) throws IllegalArgumentException {
		super(accountId, pw);
	}

}
