package org.onemeallivingalone.item.ui;

import java.util.ArrayList;
import java.util.List;

import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Ingredient;
import org.onemeallivingalone.item.IngredientList;

public class IngredientSearchManagerUI extends ManagerUI{
	
	CustomerAccount cusacnt;
	String ingredientName;
	int ingredientID;
	Ingredient searchedIngredient=null;
	int select;

	@Override
	public void interact() {
		
		
		showAllIngredients();
		if(select == 4)
			return;
		
		System.out.println("검색할 식재료의 이름을 입력하십시오.");
		scan.nextLine();
		ingredientName = scan.nextLine();
		//System.out.println("검색할 식재료의 ID를 입력하십시오.");
		//ingredientID = scan.nextInt();
		
		searchedIngredient = IngredientList.getInstance().searchByName(ingredientName);
		//searchedIngredient = IngredientList.getInstance().get(ingredientID);
		
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
			cusacnt = (CustomerAccount)CurrentUser.get();
			cusacnt.addPersonalIngredient(searchedIngredient.getIngredientId());
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
		
		//
		List<Ingredient> ingres = new ArrayList<Ingredient>(IngredientList.getInstance().getvalues());
		int cur_page=1;
		int listcnt=0;
		int entire_page = (ingres.size()/5==0) ? ingres.size()/5 + 1 : ingres.size()/5+1+1;
		int i;
		
		while(true)
		{
			// 5개 출력
			//ingres = new ArrayList<Ingredient>(IngredientList.getInstance().getvalues());
			//entire_page = (ingres.size()/5==0) ? ingres.size()/5 + 1 : ingres.size()/5+1+1;
			listcnt = ingres.size();
			if(listcnt==0)
			{
				System.out.println("시스템에 식재료 정보가 비었습니다.");
				select=4;
				return;
			}
			System.out.println("<식재료 목록>");
			for(i=0; i<5; i++)
			{
				if(((cur_page-1)*5 + i)>=listcnt)
					break;
				
				System.out.printf("%d. ",i+1);
				System.out.println(ingres.get((cur_page-1)*5+i).getDescription());
			}
			//
			
			System.out.print("(1.이전페이지    2.다음페이지    3.검색하기    4.종료)입력 : ");
			select = scan.nextInt();
			while(select<1 || select>4)
			{
				System.out.println("잘못된 입력입니다. 다시입력하십시오. : ");
				select = scan.nextInt();
			}
			switch (select)
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
				return;
			case 4:
				return;
			}
				
		}

		
	}
	

}
