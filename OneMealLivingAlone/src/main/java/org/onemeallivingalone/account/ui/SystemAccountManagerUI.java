package org.onemeallivingalone.account.ui;

import java.util.ArrayList;
import java.util.List;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;

public class SystemAccountManagerUI extends ManagerUI{

	public SystemAccountManagerUI() {}
	
	public void interact()
	{
		int choice=0;
		
		if(CurrentUser.get()==null || !(CurrentUser.get() instanceof AdminAccount))
		{
			System.out.println("권한이 없습니다.");
			return;
		}
		
		//관리자가 바로 삭제나 비밀번호 초기화를 할수도있고, 출력된 계정들에 대해서 삭제나 비밀번호 초기화를 할수도 있음
		while(true)
		{
			System.out.println("Choose what to do>");
			System.out.println("1. Change Other Account Passward");
			System.out.println("2. Delete Other Account");
			System.out.println("3. Show all Account");
			System.out.println("0. Exit");
			System.out.print("입력 : ");
			choice = scan.nextInt();
			
			if(choice == 1)
				changeOthersPW();
			else if(choice == 2)
				deleteOtherAccount();
			else if(choice == 3)
				showAllAccount();
			else if(choice == 0)
				break;
			else
				System.out.println("Wrong Choose!!");
		}
	}
	
	public void changeOthersPW()
	{
		String accountID, newPW;
		Account match=null;
		
		System.out.print("Enter the accountId to change the passward : ");
		accountID = scan.next();
		match = AccountList.get(accountID);
		
		
		if(match == null)
		{
			System.out.println("변경하려는 계정이 없습니다.!!\n");
			return;
		}
		
		// SystemAccountManagerUI를 통해 관리자도 자신의 비밀번호를 변경 할 수 있음
		else
		{
			System.out.print("Enter the new passward : ");
			newPW = scan.next();
			
			if(!match.changePassword(newPW))
				System.out.println("password validty error!!");
			else	// 수정한부분
				System.out.println("Password changed!!\n");
		}
	}
	
	public void deleteOtherAccount()
	{
		String accountID;
		System.out.print("Enter the accountId to delete : ");
		accountID = scan.next();
		
		Account match = AccountList.get(accountID);
		if(match instanceof AdminAccount)
		{
			System.out.println("admin account를 삭제할려고하면 어떡합니까!!!! 삭제 안됩니다!!");
			return;
		}
		
		if(AccountList.remove(accountID) != null)
			System.out.println("삭제하였습니다.\n");
		else
			System.out.println("삭제하려는 계정이 없습니다!!\n");
	}
	
	public void showAllAccount()
	{
		List<Account> list = new ArrayList<Account>(AccountList.getValues()); 
		int choose=0;
		int cur_page = 1;
		int entire_page = (list.size()/10 == 0) ? list.size()/10 : list.size()/10 + 1;
		int i;
		int listcnt = list.size();
		boolean run = true;
		
		while(run)
		{
			// 변경된 값 적용
			list = new ArrayList<Account>(AccountList.getValues());
			entire_page = (list.size()/10 == 0) ? list.size()/10 +1: list.size()/10 + 1 +1;
			listcnt = list.size();
			
			System.out.println("<계정 목록>");
			for(i=0; i < 10; i++)
			{
				if(((cur_page-1)+i) >= listcnt)
				{	break;	}
				
				Account temp = list.get((cur_page-1)*10+i); 
				if(temp instanceof AdminAccount)
				{
					AdminAccount admin_temp = (AdminAccount)temp;
					System.out.printf("%d. accountID : %s (admin)\n", i+1, admin_temp.getAccountId());

				}
				if(temp instanceof CustomerAccount)
				{
					CustomerAccount custom_temp = (CustomerAccount)temp;
					System.out.printf("%d. accountID : %s, email : %s\n", i+1, custom_temp.getAccountId(),custom_temp.getEmail() );
				}
			}
			System.out.println("기능을 선택하십시오\n");
			System.out.println("1.이전페이지	2.다음페이지	3.비밀번호수정	4.계정삭제		0.EXIT");
			System.out.print("입력 : ");
			choose = scan.nextInt();
			switch(choose)
			{
				case 1:
					if(cur_page == 1)
						System.out.println("현재 페이지가 처음 페이지입니다.!!\n");
					else
						cur_page--;
					break;
				case 2:
					if(cur_page == entire_page)
						System.out.println("현재 페이지가 마지막 페이지입니다.!!\n");
					else
						cur_page++;
					break;
				case 3:
					changeOthersPW();
					break;
				case 4:
					deleteOtherAccount();
					break;
				case 0:
					run = false;
					break;
			}
		}	
	}
}
