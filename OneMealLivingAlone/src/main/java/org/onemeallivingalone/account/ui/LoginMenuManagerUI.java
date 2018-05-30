package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;

public class LoginMenuManagerUI extends ManagerUI{
	
	public LoginMenuManagerUI() {}
	
	public void interact()
	{
		//로그인 된 상태면 log-in / 회원가입 / 계정 찾기  사용불가
		if(CurrentUser.get() != null)
		{
			int choose;
			System.out.println("현재 로그인된 상태 입니다.");
			System.out.println("1.로그아웃  2.계정관리  3.종료");
			System.out.print("입력 : ");
	
			while(true)
			{
				choose = scan.nextInt();
				if(choose == 1)
				{
					CurrentUser.set(null);
					System.out.println("로그아웃 되었습니다.\n");
					return;
				}
				else if(choose == 2)
				{
					CustomerAccountMenuManagerUI camm = new CustomerAccountMenuManagerUI();
					camm.interact();
					return;
				}
				else if(choose == 3)
					return;
				
				else
					System.out.print("잘못된 입력입니다. 다시 입력하십시오 (1.로그아웃  2.계정관리) : ");
			}
		}
		
		boolean run = true;
		int choose;
		while(run)
		{
			System.out.println("기능을 선택하십시오");
			System.out.print("1. Log-in\n");
			System.out.print("2. 회원가입\n");
			System.out.print("3. 계정 찾기\n");
			System.out.print("0. EXIT\n");
			
			choose = scan.nextInt();
			switch(choose)
			{
			case 1:
				//new LoginManagerUI().loginManage();
				logIn();
				if(CurrentUser.get() != null)	// login 성공하면 loginmenumanager 탈출
					return;
				break;
			case 2:
				new GetMembershipManagerUI().interact();
				break;
			case 3:
				new FindAccountManagerUI().interact();
				break;
			case 0:
				run = false;
				break;
			default:
				System.out.println("Wrong choose!!");
			}
		}
	}
	
	private void logIn()
	{
		String accountID;
		String passward;
		Account temp = null;
		
		System.out.print("Enter the ID : ");
		accountID = scan.next();
		System.out.print("Enter the passward : ");
		passward = scan.next();
		
		temp = AccountList.get(accountID);
		
		if(temp == null)
			System.out.println("계정이 없습니다.!!\n");
		else
		{
			if(temp.comparePassword(passward))
			{
				CurrentUser.set(temp); 
				System.out.println("Login SUCCESS!!\n");
			}
			else
				System.out.println("Passward가 틀렸습니다!!\n");
		}
	}

}