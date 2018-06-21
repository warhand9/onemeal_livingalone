package org.onemeallivingalone.item.ui;

import java.util.Collection;
import java.util.List;

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
	
	FoodReview selectedFoodReview=null;
	FoodReview foodreview;
	
	
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
			if(cusacnt.getPersonalFavoriteFoods().size() == 0)
			{
				System.out.println("관심요리가 없습니다.");
				System.out.println("\n");
				return;
			}
			for(i=0; i<cusacnt.getPersonalFavoriteFoods().size(); i++)
			{
				//System.out.printf("%d. ", i+1);
				System.out.println(FoodList.getInstance().get(cusacnt.getPersonalFavoriteFoods().get(i)).getSummaryDescription());
			}
			System.out.println("기능을 선택하십시오(1.요리내용보기     2.관심요리 삭제     3.관심요리 후기관리     4.내가 작성한 후기들 보기     5.종료)");
			select = scan.nextInt();
			while(select<1 || select>5)
			{
				System.out.print("잘못된 입력입니다. 다시입력하십시오. : ");
				select = scan.nextInt();
			}
			switch (select)
			{
			case 1:
				showFoodContent();
				System.out.println("요리내용보기 End\n");
				break;
			case 2:
				deleteFavoriteFood();
				System.out.println("관심요리삭제 End\n");
				break;
			case 3:
				foodReviewManage();
				System.out.println("관심요리 후기관리 End\n");
				break;
			case 4:
				showMyReviews();
				System.out.println("내가 작성한 후기들 보기 End\n");
				break;
			case 5:
				return;
			}
		}
	}
	
	public void foodReviewManage()
	{
		int i;
		
		System.out.println("후기관리할 요리번호를 입력하십시오. : ");
		selectedFoodId = scan.nextInt();
		if(cusacnt.getPersonalFavoriteFoods().contains(selectedFoodId) == false)
		{
			System.out.println("입력하신 요리가 없습니다.");
			return;
		}
		
		selectedFood = FoodList.getInstance().get(selectedFoodId);
		for(i=0; i < selectedFood.getReviews().size(); i++)
		{	// 선택한 요리에 대하여 현재 사용자가 작성한 후기 객체 가져옴
			if(FoodReviewList.getInstance().get(selectedFood.getReviews().get(i)).getUserId().equals(CurrentUser.get().getAccountId()))
				selectedFoodReview = FoodReviewList.getInstance().get(selectedFood.getReviews().get(i));
		}
		if(selectedFoodReview == null)
			System.out.println("작성하신 후기가 없습니다.");
		
		System.out.println("기능을 입력하십시오. (1.후기작성     2.후기수정     3.후기삭제     4.종료)");
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
			addFoodReview();
			System.out.println();
			break;
		case 2:
			if(selectedFoodReview == null)
			{
				System.out.println("수정할 후기가 없습니다.");
				return;
			}
			modifyFoodReview();
			System.out.println();
			break;
		case 3:
			if(selectedFoodReview == null)
			{
				System.out.println("삭제할 후기가 없습니다.");
				return;
			}
			deleteFoodReview();
			System.out.println();
			break;
		case 4:
			return;
		}
		
	}
	
	public void showFoodContent()
	{
		int foodid;
		
		System.out.print("상세보기할 요리번호를 입력하십시오.(종료 : -1)");
		foodid = scan.nextInt();
		if(foodid==-1)
			return;
		if(cusacnt.getPersonalFavoriteFoods().contains(foodid) == false)
		{
			System.out.println("입력하신 요리가 없습니다.");
			return;
		}
		
		System.out.println("선택한 요리의 상세정보입니다.>>");
		System.out.println(FoodList.getInstance().get(foodid).getDetailedDescription());
	}
	
	public void deleteFavoriteFood()
	{
		int foodid;
		int i;
		int FoodReviewIDOfDeletingFood=-1;
		List<Integer> temp;
		Food deletingfood;
		
		System.out.print("삭제할 요리번호를 입력하십시오.(종료 : -1)");
		foodid = scan.nextInt();
		if(foodid==-1)
			return;
		if(cusacnt.getPersonalFavoriteFoods().contains(foodid) == false)
		{
			System.out.println("입력하신 요리가 없습니다.");
			return;
		}
		
		temp = FoodList.getInstance().get(foodid).getReviews();
		for(i=0; i<temp.size(); i++)
		{
			FoodReview tempreview=FoodReviewList.getInstance().get(temp.get(i));
			if(tempreview.getUserId().equals(cusacnt.getAccountId()))
			{
				FoodReviewIDOfDeletingFood = tempreview.getReviewId();
				break;
			}
		}
		
		// 이용자의 favorite food list에서도 삭제하고
		cusacnt.getPersonalFavoriteFoods().remove(cusacnt.getPersonalFavoriteFoods().indexOf(foodid));
		
		if(FoodReviewIDOfDeletingFood == -1)	// 삭제할 선호 요리에 이용자가 후기를 남기지 않았을 경우
			return;
		
		// 삭제할 food가 가지고있던 이용자의 후기도 FoodReviewList에서 삭제하고
		FoodReviewList.getInstance().remove(FoodReviewIDOfDeletingFood);
		// 해당 후기를 해당 food의 요리정보에서도 삭제하고
		deletingfood = FoodList.getInstance().get(foodid);
		deletingfood.getReviews().remove(deletingfood.getReviews().indexOf(FoodReviewIDOfDeletingFood));
		
		deletingfood.recalculateAverageGrade();
	}
	
	public void showMyReviews()
	{
		int i;
		FoodReview foodreview;
		
		System.out.println("내가 작성한 후기들>>");
		if(cusacnt.getPersonalFoodReviews().size() == 0)
		{
			System.out.println("작성한 후기가 없습니다.");
			return;
		}
		for(i=0; i<cusacnt.getPersonalFoodReviews().size(); i++)
		{
			foodreview = FoodReviewList.getInstance().get(cusacnt.getPersonalFoodReviews().get(i));
			System.out.printf("%d. ", i+1);
			System.out.println(foodreview.getDescription());
		}
		System.out.println("\n작성한 후기 관리는 관심 요리 목록에서 특정 요리를 선택하여 실행하실 수 있습니다.");
	}
	
	public int idValidcheck(int newfoodreviewid)
	{
		Collection<FoodReview> frs = FoodReviewList.getInstance().getvalues();
		for(FoodReview fr : frs)
		{
			if(fr.getFoodId() == newfoodreviewid)
				return 1;
		}
		return 0;
	}
	
	void addFoodReview()
	{
		int newfoodreviewid=-1;
		int grade;
		String reviewstring;
		
		
		System.out.println("평점 ID를 입력하십시오.");
		newfoodreviewid = scan.nextInt();
		while(idValidcheck(newfoodreviewid)==1)
		{
			System.out.println("중복된 ID입니다.");
			System.out.println("다시입력하십시오.");
			newfoodreviewid = scan.nextInt();
		}
		System.out.println("해당 요리의 평점을 입력하십시오.(종료:-1 / 10점 만점 기준) : ");
		grade = scan.nextInt();
		if(grade==-1)
			return;
		System.out.println("해당 요리의 후기를 작성하십시오.(종료:q or Q) : ");
		scan.nextLine();
		reviewstring = scan.nextLine();
		if(reviewstring.equals("q") || reviewstring.equals("Q"))
			return;
		
		
		foodreview = new FoodReview(newfoodreviewid, cusacnt.getAccountId(), selectedFoodId, grade, reviewstring);
		
		// FoodReviewList에도 추가 시키고,
		FoodReviewList.getInstance().put(foodreview);
		
		// customerAccount의 foodReviewList에도 추가시키고,
		cusacnt.getPersonalFoodReviews().add(newfoodreviewid);
		
		// 해당 Food 객체에도 추가시켜줘야됨
		selectedFood.getReviews().add(newfoodreviewid);
		
		selectedFood.recalculateAverageGrade();
	}
	
	void modifyFoodReview()
	{
		int choose;
		int grade;
		String reviewstring;
		
		System.out.println("작성한 요리 후기>> ");
		System.out.println(selectedFoodReview.getDescription());
		
		System.out.println("기능선택(1.평점 수정     2.후기 수정     3.종료) : ");
		choose = scan.nextInt();
		while(choose<1 || choose>3)
		{
			System.out.println("잘못입력하셨습니다. 다시입력하십시오. : ");
			choose = scan.nextInt();
		}
		switch(choose)
		{
		case 1:
			System.out.println("새로운 평점을 입력해주십시오(1점~10점) : ");
			grade = scan.nextInt();
			selectedFoodReview.setGrade(grade);
			System.out.println("평점이 수정되었습니다.");
			selectedFood.recalculateAverageGrade();
			break;
		case 2:
			System.out.println("새로운 후기를 작성해주십시오 : ");
			reviewstring = scan.next();
			selectedFoodReview.setReview(reviewstring);
			System.out.println("후기가 수정되었습니다.");
			break;
		case 3:
			return;
		}
	}
	
	void deleteFoodReview()
	{

		// FoodReviewList에서도 해당 review를 삭제하고 
		FoodReviewList.getInstance().remove(selectedFoodReview.getReviewId());
		
		// 현재 사용자의 리뷰 리스트에서도 삭제하고
		cusacnt.getPersonalFoodReviews().remove(cusacnt.getPersonalFoodReviews().indexOf(selectedFoodReview.getReviewId()));
		
		// food가 가지고 있는 review list에서도 삭제하고
		selectedFood.getReviews().remove(selectedFood.getReviews().indexOf(selectedFoodReview.getReviewId()));
		
		System.out.println("선택한 요리의 후기정보가 삭제되었습니다.");
		
		selectedFood.recalculateAverageGrade();
	}

}
