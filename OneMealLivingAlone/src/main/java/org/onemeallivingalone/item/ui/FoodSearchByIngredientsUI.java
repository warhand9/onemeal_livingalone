package org.onemeallivingalone.item.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.*;

public class FoodSearchByIngredientsUI extends ManagerUI{

	CustomerAccount cusacnt;
	ArrayList<Integer> ingredientsForSearch = new ArrayList<Integer>();
	int ingredientID;
	ArrayList<Food> searchedFood;
	
	@Override
	public void interact() {
		int select1, i, cnt=0;
		System.out.println("검색에 사용할 식재료들을 어디서 가지고 오시겠습니까(1.식재료검색기능을 통해	2.개인 식재료 리스트에서	3.종료)");
		select1 = scan.nextInt();
		while(true)
		{
			if(select1<1 || select1>3)
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
		System.out.println("선택한 재료들>>");
		for(i=0; i<ingredientsForSearch.size(); i++)
		{	
			System.out.print(IngredientList.getInstance().get(ingredientsForSearch.get(i)).getName());
			System.out.print(" ");
			cnt++;
			if(cnt%5==0)
			{
				System.out.println();
				cnt=0;
			}
		}
		System.out.print("\n위의 재료들로 검색한 결과>>\n");
		searchedFood = (ArrayList<Food>)FoodList.getInstance().filteringByIngredients(ingredientsForSearch);
		
		// 결과 출력 // 복잡한 부분
		showResultOfSearchByIngredients();
		
		new FoodViewUI().interact();
		
		
	}
	
	public void getIngredientsForSearchBySearching()
	{
		int select;
		
		//
		List<Ingredient> ingres = new ArrayList<Ingredient>(IngredientList.getInstance().getvalues());
		int cur_page=1;
		int listcnt=0;
		int entire_page = (ingres.size()/5==0) ? ingres.size()/5 + 1 : ingres.size()/5+1+1;
		int i;
		
		listcnt = ingres.size();
		if(listcnt==0)
		{
			System.out.println("시스템에 식재료 정보가 비었습니다.");
			select=4;
			return;
		}
		
		while(true)
		{
			// 5개씩 출력
			System.out.println("<식재료 목록>");
			for(i=0; i<5; i++)
			{
				if(((cur_page-1)*5 + i)>=listcnt)
					break;
				
				System.out.printf("%d. ", i+1);
				System.out.println(ingres.get((cur_page-1)*5+i).getDescription());
			}
			//
			
			System.out.print("(1.이전페이지		2.다음페이지	3.선택	4.선택종료) 입력 : ");
			select = scan.nextInt();
			while(select<1 || select>4)
			{
				System.out.println("잘못된입력입니다. 다시 입력하십시오.");
				select = scan.nextInt();
			}
			switch(select)
			{
			case 1:
				if(cur_page == 1)
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
				System.out.print("식재료 번호를 입력하십시오 : ");
				ingredientID = scan.nextInt();
				if(IngredientList.getInstance().get(ingredientID)==null)
					System.out.println("선택하신 식재료가 없습니다.");
				else
				{
					ingredientsForSearch.add(ingredientID);
					System.out.println("입력되었습니다.");
				}
				break;
			case 4:
				System.out.println("검색에 이용할 식재료 선택이 완료되었습니다.");
				return;
			}
		}	
	}
	
	/*public void getIngredientsForSearchBySearching()
	{
		new IngredientSearchManagerUI().showAllIngredients();
		
		System.out.println("검색에 이용할 식재료를 선택하십시오(선택종료 : -1)");
		while(true)
		{
			System.out.print("입력(종료 : -1) : ");
			ingredientID = scan.nextInt();
			if(ingredientID == -1)
				break;
			
			if(IngredientList.get(ingredientID) == null)
			{
				System.out.println("잘못 입력하셨습니다. 다시 입력하십시오.");
			}
			else
			{
				ingredientsForSearch.add(ingredientID);
				System.out.println("입력되었습니다.");
			}
		}
		System.out.println("검색에 이용할 식재료 선택이 완료되었습니다.");
	}*/
	
	public void getIngredientsForSearchByPersonalIngredients()
	{
		int i;
		
		if(CurrentUser.get()==null || CurrentUser.get() instanceof AdminAccount)
		{
			System.out.println("이용자 계정 로그인이 필요한 서비스 입니다.");
			return;
		}
		cusacnt = (CustomerAccount)CurrentUser.get();
		
		for(i=0; i<cusacnt.getPersonalIngredients().size(); i++)
		{
			int cnt=0;
			Ingredient ingre = IngredientList.getInstance().get(cusacnt.getPersonalIngredients().get(i));
			System.out.print(ingre.getIngredientId() + "_" + ingre.getName() + " ");
			cnt++;
			if(cnt%5 == 0)
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
		System.out.println("검색에 이용할 식재료 선택이 완료되었습니다.");
	}
	
	public void showResultOfSearchByIngredients()
	{
		int i=0;
		int j=0;
		int cnt=0;
		int index=0;
		int extraCost;
		
		ArrayList<Food> factor100foods = new ArrayList<>();
		ArrayList<Food> exactly100food = new ArrayList<>();
		ArrayList<Food> factorlt100foods = new ArrayList<>();

		List<ImmutablePair<Double, Food>> foodsByIngredientPairs = FoodList.getInstance().filteringByIngredients(ingredientsForSearch);
		
		for (ImmutablePair<Double, Food> pair : foodsByIngredientPairs) {
			if(pair.getKey() >= Double.valueOf(1.0))
			{
				index++;
				factor100foods.add(pair.getValue());
			}
			else
				factorlt100foods.add(pair.getValue());	// factor 기준으로 내림차순 순으로 추가됨
		}
		
		for(Food f : factor100foods)
		{
			if(f.getIngredients().size()==ingredientsForSearch.size())
			{
				cnt++;
				exactly100food.add(f);
			}
			else
				break;
		}
		
		System.out.println("입력한 식재료들과 일치도 100%인 요리들>>");
		if(exactly100food.size()==0)
			System.out.println("없습니다.");
		else
		{
			for(Food f:exactly100food)
			{
				System.out.printf("%d. ", (i++) + 1);
				System.out.println(f.getSummaryDescription());
			}
		}
		
		System.out.println("\n추가적인 식재료를 필요로 하는 요리들>>");
		if(cnt==factor100foods.size())
			System.out.println("없습니다.");
		else
		{
			for(i=cnt; i<factor100foods.size(); i++)
			{
				System.out.printf("%d. ", (j++) + 1);
				System.out.println(factor100foods.get(i).getSummaryDescription());
				extraCost = factor100foods.get(i).getCookingCost() - searchIngredientsSum();
				System.out.printf("추가비용 : %d\n", extraCost);
			}
		}
		
		System.out.println("\n일치도가 낮은 요리들>>");
		if(factorlt100foods.size()==0)
			System.out.println("없습니다.");
		else
		{
			i=0;
			for(Food f:factorlt100foods)
			{
				System.out.printf("%d. ", (i++) + 1);
				System.out.println(f.getSummaryDescription());
				System.out.println("일치도 : " + foodsByIngredientPairs.get(index).getKey()*100 + "%");
			}
		}
		
	}
	int searchIngredientsSum()
	{
		int sum=0;
		int i;
		for(i=0; i<ingredientsForSearch.size();i++)
			sum+= IngredientList.getInstance().get(ingredientsForSearch.get(i)).getPrice();
		
		return sum;
	}

}
