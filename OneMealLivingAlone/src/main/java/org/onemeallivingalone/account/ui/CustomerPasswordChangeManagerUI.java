package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.ui.ManagerUI;

public class CustomerPasswordChangeManagerUI extends ManagerUI{
	
	CustomerAccount custom = null;
	
	public CustomerPasswordChangeManagerUI(CustomerAccount custom)
	{
		this.custom = custom;
	}
	
	public void interact()
	{
		String temp1, temp2;
		String newPW;
		
		System.out.print("Enter the new password : ");
		temp1 = scan.next();
		System.out.print("Password check : ");
		temp2 = scan.next();
		
		//비밀번호와 비밀번호 확인을 일치하게 입력 해야 하며 이전 비밀번호와 달라야 한다.
		if(!temp1.equals(temp2) && custom.comparePassword(temp1))
		{
			System.out.println("input password error\n");
			return;
		}
		
		if(!temp1.equals(temp2))
		{
			System.out.println("password check error!!");
			return;
		}
		
		newPW = temp1;
		if(!custom.changePassword(newPW))
			System.out.println("password validty error!!");
		else	// 수정한부분
			System.out.println("Password changed!!\n");
	}
}