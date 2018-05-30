package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;
import org.onemeallivingalone.item.FoodReview;
import org.onemeallivingalone.item.FoodReviewList;

public class FavoriteFoodnFoodReviewManagerUI extends ManagerUI{

	CustomerAccount cusacnt;
	int select;
	int selectedFoodId;
	Food selectedFood;
	
	@Override
	public void interact() {
		
		int i;
		if(CurrentUser.get()==null || CurrentUser.get() instanceof AdminAccount)
		{
			System.out.println("이용자 계정 로그인이 필요한 서비스 입니다.");
			return;
		}
		cusacnt = (CustomerAccount)CurrentUser.get();
		
		while(true)
		{
			System.out.println("관심요리 목록>>");
			for(i=0; i<cusacnt.getPersonalFavoriteFoods().size(); i++)
			{
				System.out.println(FoodList.get(cusacnt.getPersonalFavoriteFoods().get(i)).getSummaryDescription());
			}
			System.out.print("기능을 선택하십시오(1.요리내용보기	 2.관심요리 삭제		3.관심요리 후기관리	4.종료)");
			select = scan.nextInt();
			while(select<1 || select>4)
			{
				System.out.print("잘못된 입력입니다. 다시입력하십시오. : ");
				select = scan.nextInt();
			}
			switch (select)
			{
			case 1:
				showFoodContent();
				break;
			case 2:
				deleteFavoriteFood();
				break;
			case 3:
				foodReviewManage();
				break;
			case 4:
				return;
			}
		}
	}
	
	public void foodReviewManage()
	{
		int i;
		FoodReview selectedFoodReview=null;
		
		System.out.println("요리번호를 입력하십시오. : ");
		selectedFoodId = scan.nextInt();
		if(cusacnt.getPersonalFavoriteFoods().contains(selectFoodId) == false)
		{
			System.out.println("입력하신 요리가 없습니다.");
			return;
		}
		
		selectedFood = FoodList.get(selectedFoodId);
		for(i=0;i<selectedFood.getFoodReviews().size(); i++)
		{
			if(FoodReviewList.get(selectedFood.getFoodReviews().get(i)).getUserId.equals(CurrentUser.get().getAccountId()))
				selectedFoodReview = FoodReviewList.get(selectedFood.getFoodReviews().get(i));
		}
		
		System.out.println("기능을 입력하십시오. (1.후기작성		2.후기수정		3.후기삭제		4.종료)");
		select = scan.nextInt();
		while(select < 1 || select >4)
		{
			System.out.println("잘못된 입력입니다. 다시입력하십시오. : ");
			select = scan.nextInt();
		}
		switch (select)
		{
		case 1:
			if(selectedFoodReview != null)
			{
				System.out.println("이미 후기가 있습니다.");
				return;
			}
			System.out.println("해당 요리의 평점을 입력하십시오. : ");
			System.out.println("해당 요리의 후기를 작성하십시오. : ");
			//  FoodReviewList에도 추가 시키고, customerAccount의 foodReviewList에도 추가시키고, 해당 Food 객체에도 추가시켜줘야됨
			break;
		case 2:
			if(selectedFoodReview == null)
			{
				System.out.println("수정할 후기가 없습니다.");
				return;
			}
			System.out.println("1.평점 수정		2.후기 수정");
			break;
		case 3:
			if(selectedFoodReview == null)
			{
				System.out.println("삭제할 후기가 없습니다.");
				return;
			}
			FoodReviewList.remove(selectedFoodReview.getItemId());
			cusacnt.getPersonalFoodReviews().remove();
			selectedFood.getFoodReviews().remove();
			System.out.println("선택한 요리의 후기정보가 삭제되었습니다.");
			
			break;
		case 4:
			return;
		}
		
	}

}
