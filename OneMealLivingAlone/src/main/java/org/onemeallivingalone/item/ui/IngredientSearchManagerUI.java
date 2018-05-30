package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Ingredient;
import org.onemeallivingalone.item.IngredientList;

public class IngredientSearchManagerUI extends ManagerUI{
		
	String ingredientName;
	Ingredient searchedIngredient=null;
	int select;

	@Override
	public void interact() {
		
		
		showAllIngredients();
		if(select == 4)
			return;
		
		System.out.println("검색할 식재료의 이름을 입력하십시오.");
		ingredientName = scan.next();
		
		searchedIngredient = IngredientList.searchByName(ingredientName);
		System.out.println("검색결과>>");
		if(searchedIngredient == null)
		{
			System.out.println("검색된 식재료가 없습니다.");
			return;
		}
		System.out.println(searchedIngredient.getDescription());
		
		System.out.print("개인식재료 리스트에 추가하시겠습니까(yes:1, no:2) : ");
		select = scan.nextInt();
		while((select!=1) && (select!=2))
		{
			System.out.print("잘못입력하셨습니다. 다시입력하십시오(yes:1, no:2) : ");
			select = scan.nextInt();
		}
		
		if(select==1)
		{
			if(CurrentUser.get()==null || CurrentUser.get() instanceof AdminAccount)
			{
				System.out.println("이용자 계정 로그인이 필요한 서비스 입니다.");
				return;
			}
			CustomerAccount cusacnt = (CustomerAccount)CurrentUser.get();
			cusacnt.addPersonalIngredient(searchedIngredient.getItemId());
			System.out.println(searchedIngredient.getName() +"이(가) 개인 식재료 리스트에 추가 되었습니다.");
		}
		
		return;
	}
	
	public void showAllIngredients()
	{
		/*for(Ingredient ingredient : IngredientList.getvalues())
		{
			System.out.println(ingredient.getDescription());
		}*/
		while(true)
		{
			// 5개 출력
			
			System.out.print("(1.이전페이지		2.다음페이지	3.검색하기		4.종료)입력 : ");
			select = scan.nextInt();
			while(select<1 || select>4)
			{
				System.out.println("잘못된 입력입니다. 다시입력하십시오. : ");
				select = scan.nextInt();
			}
			switch (select)
			{
			case 1:
				break;
			case 2:
				break;
			case 3:
				return;
			case 4:
				return;
			}
				
		}

		
	}
	

}
