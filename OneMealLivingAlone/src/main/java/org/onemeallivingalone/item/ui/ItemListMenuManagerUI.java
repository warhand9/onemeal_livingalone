package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;

public class ItemListMenuManagerUI extends ManagerUI{

	@Override
	public void interact() {
		int order = -1;
		
		if(CurrentUser.get()==null || CurrentUser.get() instanceof CustomerAccount)
		{
			System.out.println("관리자 계정 로그인이 필요한 서비스 입니다.");
			return;
		}

		while (order != 0) {
			System.out.println("기능을 선택하십시오.");
			System.out.println("1. 식재료 리스트 관리");
			System.out.println("2. 요리 리스트 관리");
			System.out.println("3. 종료");

			order = scan.nextInt();

			switch(order) {
			case 1:
				new IngredientListManager().interact();
				System.out.println("\n");
				break;
			case 2:
				new FoodListManager().interact();
				System.out.println("\n");
				break;
			case 3:
				return;
			default:
				System.out.println("알 수 없는 명령어입니다.");
				System.out.println("\n");
				break;
			}
		}
	}
}

