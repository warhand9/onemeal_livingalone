package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.ui.ManagerUI;


public class GetMembershipManagerUI extends ManagerUI{
	public GetMembershipManagerUI() {}
	
	public void interact()
	{
		String accountID="", passward="", email="";
		String temp1=null, temp2=null;
		CustomerAccount newcustom;
		boolean check = true;
		boolean run = true;
		
		System.out.println("<회원가입>");
		
		while(run)
		{
			System.out.print("Enter Account ID(종료 : 0) : ");
			accountID = scan.next();
			if(accountID.equals("0"))
				return;
			System.out.print("Enter email(종료 : 0) : ");
			email = scan.next();
			if(email.equals("0"))
				return;
			System.out.print("Enter passward(종료 : 0) : ");
			temp1 = scan.next();
			if(temp1.equals("0"))
				return;
			System.out.print("Enter passward check(종료 : 0) : ");
			temp2 = scan.next();
			if(temp2.equals("0"))
				return;
			
			if(Account.checkAccountIdValidity(accountID)==false)
			{
				System.out.println("ID 입력형식에 맞지 않습니다.");
				System.out.println("계정 ID는 5글자 이상으로, 시작 문자는 알파벳이어야 하며, 나머지는 알파벳, 숫자, 밑줄('_')로 구성돼야 합니다.");
				check = false;
			}
			if(AccountList.get(accountID) != null)
			{
				System.out.println("중복된 ID입니다.!!");
				check = false;
			}
			
			for(Account acc : AccountList.getValues())
			{
				if(acc instanceof CustomerAccount)
				{
					CustomerAccount temp =(CustomerAccount)acc;
					if(temp.getEmail().equals(email))
					{
						System.out.println("중복된 email입니다.!!");
						check = false;
						break;
					}
				}
			}
			if(!CustomerAccount.checkEmailValidity(email))
			{
				System.out.println("잘못된 email입니다.!!");
				System.out.println("이메일 주소는 CustomerAccount.EMAIL_REGEX 에 지정된 정규 표현식에 맞아야 합니다.");
				check = false;
			}
			if(!Account.checkPasswordValidity(temp1))
			{
				System.out.println("보안이 낮은 비밀번호입니다.!!");
				System.out.println("비밀번호는 알파벳, 숫자, 특수문자 중 두 가지 이상으로 구성돼야 하며, 길이는 8자리 이상이어야 합니다.");
				check = false;
			}
			if(!temp1.equals(temp2))
			{
				System.out.println("passward mismatch!!\n");
				check = false;
			}
			
			if(check)
			{
				System.out.println("No error");
				run = false;
			}
			check = true; // check 초기화
		}
		
		passward = temp1;
		newcustom = new CustomerAccount(accountID, passward, email);
		AccountList.put(newcustom);
		System.out.println("회원가입이 완료되었습니다.\n");
	}
	
}