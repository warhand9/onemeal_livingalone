package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.account.UserAccount;
import org.onemeallivingalone.center.ui.ManagerUI;

public class GetMembershipManagerUI extends ManagerUI{
	public GetMembershipManagerUI() {}
	
	public void interact()
	{
		String accountID="", passward="", email="";
		String temp1=null, temp2=null;
		UserAccount newcustom;
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
			
			if(AccountList.get(accountID) != null)
			{
				System.out.println("중복된 ID입니다.!!");
				check = false;
			}
			
			for(Account acc : AccountList.getValues())
			{
				if(acc instanceof UserAccount)
				{
					UserAccount temp =(UserAccount)acc;
					if(temp.getEmail().equals(email))
					{
						System.out.println("중복된 email입니다.!!");
						check = false;
						break;
					}
				}
			}
			if(!UserAccount.checkEmailValidity(email))
			{
				System.out.println("잘못된 email입니다.!!");
				check = false;
			}
			if(!Account.checkPasswordValidity(temp1))
			{
				System.out.println("보안이 낮은 비밀번호입니다.!!");
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
		newcustom = new UserAccount(accountID, passward, email);
		AccountList.put(newcustom);
		System.out.println("회원가입이 완료되었습니다.\n");
	}
	
}
