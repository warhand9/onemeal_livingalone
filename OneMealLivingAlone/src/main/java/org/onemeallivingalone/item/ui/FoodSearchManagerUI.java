package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.center.ui.ManagerUI;

public class FoodSearchManagerUI extends ManagerUI{

	@Override
	public void interact() {
		
		int select1;
		while(true)
		{
			// 평점순으로 요리 출력
			
			System.out.print("1.요리 내용 보기	2.이름으로 요리검색	3.식재료로 요리검색	4.종료\n");
			System.out.print("기능을 선택하십시오 : ");
			select1 = scan.nextInt();
			while(true)
			{
				if(select1<1 || select1>4)
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
				new FoodViewUI().interact();
				break;	
			case 2:
				new FoodSearchByNameUI().interact();
				break;
			case 3:
				new FoodSearchByIngredientsUI().interact();
				break;
			case 4:
				return;
			}
		}
		
	}

}
