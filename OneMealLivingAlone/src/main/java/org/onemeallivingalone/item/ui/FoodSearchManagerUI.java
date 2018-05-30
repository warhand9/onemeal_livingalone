package org.onemeallivingalone.item.ui;

import java.util.ArrayList;
import java.util.List;

import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;

public class FoodSearchManagerUI extends ManagerUI{

	@Override
	public void interact() {
		
		int select1;
		int i;
		
		//
		ArrayList<Food> highScoreFoods = (ArrayList<Food>)FoodList.getInstance().getHighGradeFoods();
		List<Food> foods = new ArrayList<Food>(FoodList.getInstance().getvalues());	// 시스템에 저장된 순서
		int cur_page = 1;
		int listcnt = foods.size();
		int entire_page = (foods.size()/5==0) ? foods.size()/5 + 1 : foods.size()/5+1+1;
		
		if(listcnt==0)
		{
			System.out.println("시스템에 요리 정보가 비었습니다.");
			return;
		}
		
		while(true)
		{
			// 평점순으로 요리 출력
			System.out.println("<<이용자 추천 요리 입니다.>>");
			for(i=0; i<5; i++)
			{
				if(i>=listcnt)
					break;
				
				System.out.printf("%d. ",i+1);
				System.out.println(highScoreFoods.get(i).getSummaryDescription());
				//System.out.println(foods.get(i).getSummaryDescription());
			}
			System.out.println("///////////////////");
			System.out.println("<<요리 목록>>");
			for(i=0; i<5; i++)
			{
				if(((cur_page-1)*5 + i)>=listcnt)
					break;
				
				System.out.printf("%d. ",i);
				System.out.println(foods.get((cur_page-1)*5+i).getSummaryDescription());
			}
			
			System.out.print("1.이전페이지		2.다음페이지\n3.요리 내용 보기	4.이름으로 요리검색	5.식재료로 요리검색	0.종료\n");
			System.out.print("기능을 선택하십시오 : ");
			select1 = scan.nextInt();
			while(true)
			{
				if(select1<0 || select1>5)
				{
					System.out.println("잘못 입력하셨습니다. 다시 입력하십시오.");
					select1 = scan.nextInt();
				}
				else
					break;
			}
			switch(select1)
			{
			case 1:
				if(cur_page==1)
					System.out.println("현재 페이지가 처음 페이지입니다.");
				else
					cur_page--;
				break;
			case 2:
				if(cur_page == entire_page)
					System.out.println("현재 페이지가 마지막 페이지입니다.");
				else
					cur_page++;
				break;
			case 3:
				new FoodViewUI().interact();
				break;	
			case 4:
				new FoodSearchByNameUI().interact();
				break;
			case 5:
				new FoodSearchByIngredientsUI().interact();
				break;
			case 0:
				return;
			}
		}
		
	}

}
