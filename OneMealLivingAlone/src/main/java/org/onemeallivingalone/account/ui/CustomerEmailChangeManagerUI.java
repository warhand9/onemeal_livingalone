package org.onemeallivingalone.account.ui;

import org.onemeallivingalone.account.UserAccount;
import org.onemeallivingalone.center.ui.ManagerUI;

public class CustomerEmailChangeManagerUI extends ManagerUI{
	UserAccount custom = null;
	
	public CustomerEmailChangeManagerUI(UserAccount custom) {
		this.custom = custom;
	}

	public void interact()
	{
		String email;
		System.out.print("Enter the new email : ");
		email = scan.next();
		if(custom.changeEmail(email))
			System.out.println("Email changed!!\n");
		else
			System.out.println("Email validity error!");
	}

}
