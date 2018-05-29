// 2017 PhoneSajo
package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;


/** 고객 계정 정보를 관리하는 클래스.
*
* 고객 계정 password변경과 email 변경 기능이 있습니다. 
*/
public class CustomerAccountMenuManagerUI extends ManagerUI{
	
	CustomerAccount custom;
	
	public void interact()
	{
		int choice = 0;
		
		if(CurrentUser.get() == null)
		{
			System.out.println("구매자 전용 기능입니다.");
			return;
		}
		if(!(CurrentUser.get() instanceof CustomerAccount))
		{
			System.out.println("구매자 계정으로 로그인 하십시오.");
			return;
		}
		
		custom = (CustomerAccount)CurrentUser.get();
		while(true)
		{
			System.out.print("1. Change Passward\n");
			System.out.print("2. Change Email\n");
			System.out.print("0. Exit\n");
			System.out.print("입력 : ");
			
			choice = scan.nextInt();
			
			if(choice == 1)
				new CustomerPasswordChangeManagerUI(custom).interact();
			else if(choice == 2)
				new CustomerEmailChangeManagerUI(custom).interact();
			else if(choice == 0)
				break;
			else
				System.out.println("Wrong Choose!!");
		}
	}

}
