package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;

public class FoodViewUI extends ManagerUI{

	CustomerAccount cusacnt;
	
	@Override
	public void interact() {
		int foodID, select;
		Food food;
		System.out.print("보고자하는 요리 번호를 입력하십시오(종료 : 0) : ");
		foodID = scan.nextInt();
		if(foodID == 0)
			return;

		while((food = FoodList.getInstance().get(foodID))==null)
		{
			System.out.println("해당 요리가 없습니다.");
			System.out.print("다시선택하십시오(종료 : 0) : ");
			foodID = scan.nextInt();
			if(foodID == -1)
				return;
		}
		
		System.out.println("선택하신 요리의 상세정보입니다.");
		System.out.println(food.getDetailedDescription());
		
		System.out.println("\n관심요리로 추가하시겠습니까(yes:1, no:0) : ");
		select = scan.nextInt();
		while((select != 1) && (select != 0))
		{
			System.out.println("잘못된 입력입니다.");
			System.out.print("다시 선택해주십시오. : ");
			select = scan.nextInt();
		}
		
		if(select == 1)
		{
			if(CurrentUser.get()==null || CurrentUser.get() instanceof AdminAccount)
			{
				System.out.println("이용자 계정 로그인이 필요한 서비스 입니다.");
				return;
			}
			
			cusacnt = (CustomerAccount)CurrentUser.get();
			if(cusacnt.getPersonalFavoriteFoods().contains(foodID))
			{
				System.out.println("이미 관심 요리 목록에 존재합니다.");
				return;
			}
			cusacnt.addPeronsalFavoriteFood(foodID);
			System.out.println("관심 요리로 추가되었습니다.");
		}
		
		return;
	}

}
