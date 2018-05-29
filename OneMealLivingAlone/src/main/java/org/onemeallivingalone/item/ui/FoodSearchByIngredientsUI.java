package org.onemeallivingalone.item.ui;

import java.util.ArrayList;

import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.FoodList;
import org.onemeallivingalone.item.IngredientList;
import org.onemeallivingalone.item.*;

public class FoodSearchByIngredientsUI extends ManagerUI{

	ArrayList<Integer> ingredientsForSearch = new ArrayList<Integer>();
	
	@Override
	public void interact() {
		int select1, i;
		System.out.println("검색에 사용할 식재료들을 어디서 가지고 오시겠습니까(1.식재료검색기능을 통해	2.개인 식재료 리스트에서	3.종료)");
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
			getIngredientsForSearchBySearching();
			break;
		case 2:
			getIngredientsForSearchByPersonalIngredients();
			break;
		case 3:
			return;
				
		}
		
		if(ingredientsForSearch == null)
		{
			System.out.println("선택된 재료들이 없습니다.");
			return;
		}
		
		System.out.println();
		for(i=0; i<ingredientsForSearch.size(); i++)
		{	
			System.out.print(IngredientList.get(ingredientsForSearch.get(i)).getName());
			System.out.print(" ");
		}
		System.out.print("\n위의 재료들로 검색한 결과>>\n");
		// 결과 출력
		
		new FoodViewUI().interact();
		
		
	}
	
	public void getIngredientsForSearchBySearching()
	{
		
	}
	
	public void getIngredientsForSearchByPersonalIngredients()
	{
		int i;
		int ingredientID;
		
		if(CurrentUser.get()==null || CurrentUser.get() instanceof AdminAccount)
		{
			System.out.println("이용자 계정 로그인이 필요한 서비스 입니다.");
			return;
		}
		CustomerAccount cusacnt = (CustomerAccount)CurrentUser.get();
		
		for(i=0; i<cusacnt.getPersonalIngredients().size(); i++)
		{
			int cnt=0;
			Ingredient ingre = IngredientList.get(cusacnt.getPersonalIngredients().get(i));
			System.out.print(ingre.getName() + " ");
			cnt++;
			if(cnt%10 == 0)
			{
				System.out.println();
				cnt=0;
			}
		}
		System.out.println("검색에 이용할 식재료를 선택하십시오(선택종료 : -1)");
		while(true)
		{
			System.out.print("입력(종료 : -1) : ");
			ingredientID = scan.nextInt();
			if(ingredientID == -1)
				break;
			
			if(cusacnt.getPersonalIngredients().contains(ingredientID)==false)
			{
				System.out.println("잘못 입력하셨습니다. 다시 입력하십시오.");
			}
			else
			{
				ingredientsForSearch.add(ingredientID);
				System.out.println("입력되었습니다.");
			}
		}
	}

}
