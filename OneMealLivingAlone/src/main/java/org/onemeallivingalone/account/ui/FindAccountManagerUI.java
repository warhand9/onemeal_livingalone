package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.ui.ManagerUI;

public class FindAccountManagerUI extends ManagerUI{

	public FindAccountManagerUI() {}
	
	public void interact()
	{
		int select = 0;
		String email, accountID;
		while(true)
		{
			System.out.println("Choose what to find");
			System.out.println("1. Account ID");
			System.out.println("2. Password");
			System.out.println("0. EXIT");
			System.out.println("입력 : ");
			select = scan.nextInt();
		
			if(select == 1)
			{
				boolean result=false;
				System.out.print("Enter your email : ");
				email = scan.next();
				for(Account acc : AccountList.getValues())
				{
					if(acc instanceof CustomerAccount)
					{
						CustomerAccount temp = (CustomerAccount)acc;
						if(temp.getEmail().equals(email))
						{
							System.out.printf("accountID : %s\n\n", acc.getAccountId());
							result = true;
						}
					}
				}	
				if(result == false)
					System.out.println("계정이 없습니다.\n");
			}
			else if(select == 2)
			{
				System.out.println("Enter your ID");
				accountID = scan.next();
				System.out.println("Enter your email");
				email = scan.next();
				
				Account acc = AccountList.get(accountID);
				if(acc instanceof CustomerAccount && ((CustomerAccount)acc).getEmail().equals(email))
					System.out.println("관리자 연락처 : 010-4335-0152\nCall me if you want to find your password.\n");
				else
					System.out.println("해당하는 계정이 없습니다!!\n");
			}
			else if(select == 0)
				break;
			else
				System.out.println("Wrong selection!!");
		}
	}
	
}
