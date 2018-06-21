package org.onemeallivingalone.item.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;
import org.onemeallivingalone.item.FoodReview;
import org.onemeallivingalone.item.FoodReviewList;
import org.onemeallivingalone.item.Ingredient;
import org.onemeallivingalone.item.IngredientList;

public class FoodListManager extends ManagerUI{

	@Override
	public void interact() {
		List<Food> foods;
		int cur_page=1;
		int listcnt=0;
		int entire_page;
		int i;
		int select;
		
		while(true)
		{
			foods = new ArrayList<Food>(FoodList.getInstance().getvalues());
			entire_page = foods.size()/5 + 1;
			// 5개 출력
			listcnt = foods.size();
			if(listcnt==0)
			{
				System.out.println("시스템에 요리 정보가 비었습니다.");
			}
			else
			{
				System.out.println("<요리 목록>");
				for(i=0; i<5; i++)
				{
					if(((cur_page-1)*5 + i)>=listcnt)
						break;
					
					System.out.printf("%d. ",i+1);
					System.out.println(foods.get((cur_page-1)*5+i).getSummaryDescription());
					//System.out.println(" / ID : " + foods.get((cur_page-1)*5+i).getFoodId());
				}
			}
			
				
			System.out.print("(1.이전페이지		2.다음페이지	3.요리 추가		4.요리 수정		5.요리 삭제		6.종료)입력 : ");
			select = scan.nextInt();
			while(select<1 || select>6)
			{
				System.out.println("잘못된 입력입니다. 다시입력하십시오. : ");
				select = scan.nextInt();
			}
			switch(select)
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
				addFood();
				System.out.println();
				break;
			case 4:
				modifyFood();
				System.out.println();
				break;
			case 5:
				deleteFood();
				System.out.println();
				break;
			case 6:
				return;
			}
		}
	}

	public void addFood()
	{
		int foodId;
		String name;
		int cookingTime;
		String recipe;
		//Ingredient ingre;
		//String ingreName;
		//int i=1;
		Food food;
		
		System.out.println("요리 추가>>");
		
		System.out.println("새로운 요리 ID 입력 : ");
		foodId = scan.nextInt();
		while(idValidCheck(foodId)!=null)
		{
			System.out.println("이미 존재하는 ID입니다. 다시 입력하십시오.");
			foodId = scan.nextInt();
		}
		
		System.out.println("추가하고자 하는 요리 이름을 입력하십시오.");
		scan.nextLine();
		name = scan.nextLine();
		while(foodNameValidCheck(name)!=null)
		{
			System.out.println("이미 존재하는 이름입니다. 다시 입력하십시오.");
			name = scan.nextLine();
		}
		
		System.out.println("추가하고자 하는 요리의 소요시간을 입력하십시오.");
		cookingTime = scan.nextInt();
		
		System.out.println("추가하고자 하는 요리의 레시피를 입력하십시오.");
		scan.nextLine();
		recipe = scan.nextLine();
		
		food = new Food(foodId, name, cookingTime, recipe);
		/*System.out.println("추가하고자 하는 요리에 필요한 재료들을 입력하십시오.(종료 : q)");
		while(true)
		{
			System.out.printf("재료 %d : ", i);
			ingreName = scan.next();
			if(ingreName.equals("q"))
				break;
			
			if((ingre=ingredientNameValidCheck(ingreName)) != null)
			{
				food.getIngredients().add(ingre.getIngredientId());
				System.out.println("입력 완료");
				i++;
			}
			else
			{
				System.out.println("입력하신 재료가 시스템에 존재하지 않습니다.");
			}
		}*/
		inputIngredientsInfo(food);
		System.out.println("재료정보 입력 완료");
		food.recalculateCookingCost();
		
		System.out.println("새로운 요리>");
		System.out.println(food.getDetailedDescription());
		
		FoodList.getInstance().put(food);
		System.out.println("입력하신 요리가 추가되었습니다.");
	}
	
	public void modifyFood()
	{
		int foodId;
		Food food;
		int choose;
		int flag = 1;
		int cookingtime;
		String recipe;
		
		System.out.println("요리 정보 수정>>");
		System.out.println("수정하고자 하는 요리 id번호를 입력하십시오.");
		foodId = scan.nextInt();
		if((food=idValidCheck(foodId))==null)
		{	
			System.out.println("존재하지 않는 식재료 입니다.");
			return;
		}
		else
		{
			System.out.println("선택하신 요리>>");
			System.out.println(food.getDetailedDescription());
			
			while(flag==1)
			{
				System.out.println("어떤 정보를 수정하시겠습니까.(1.소요시간   2.레시피   3.재료들   4.종료)");
				choose = scan.nextInt();
				if(choose<1 || choose>4)
				{
					System.out.println("잘못된 입력입니다. 다시입력하십시오.");
					continue;
				}
				switch(choose)
				{
				case 1:
					System.out.println("새로운 소요시간을 입력하십시오.");
					cookingtime = scan.nextInt();
					food.setCookingTime(cookingtime);
					break;
				case 2:
					System.out.println("새로운 레시피를 입력하십시오.");
					scan.nextLine();
					recipe = scan.nextLine();
					food.setRecipe(recipe);
					break;
				case 3:
					//System.out.println("새로운 식재료들을 입력하십시오.");
					food.getIngredients().clear();
					inputIngredientsInfo(food);
					food.recalculateCookingCost();
					break;
				case 4:
					flag = 0;
					break;
				}
			}
			
			System.out.println("수정이 완료되었습니다.");
			System.out.println("수정후 > " + food.getDetailedDescription());
		}	
	}
	
	public void deleteFood()
	{
		CustomerAccount cus;
		int foodId;
		int foodReviewId;
		FoodReview foodReview;
		
		System.out.println("요리 삭제>>");
		System.out.println("삭제할 요리 Id를 입력하십시오. : ");
		foodId = scan.nextInt();
		if(idValidCheck(foodId)==null)
		{	
			System.out.println("존재하지 않는 식재료 입니다.");
			return;
		}
		else
		{
			// food리스트에서도 지우고
			FoodList.getInstance().remove(foodId);
			
			// CustomerAccount의 favorite Food리스트에서도 지우고 그러면 해당 CustomerAccount의 해당 요리에 관한 review도 지워야 함
			// 그 reviewID를 reveiwList에서도 지우면 밑에거는 자동해결
			// ReviewList에서 해당 요리의 reviews들도 모두 삭제
			for(Account acc : AccountList.getValues())
			{
				if(acc instanceof CustomerAccount)
				{
					cus = (CustomerAccount)acc;
					if(cus.getPersonalFavoriteFoods().contains(foodId))
					{
						cus.getPersonalFavoriteFoods().remove(cus.getPersonalFavoriteFoods().indexOf(foodId));
					}
					
					// customer가 favorite food list에서 해당 food에 대해서 review를 작성했을 수도 있고 안했을 수도 있고
					foodReview = getFoodReviewByFood(cus, foodId);
					if(foodReview == null)	continue;
					foodReviewId = foodReview.getReviewId();
					// 이 문장이 실행된다는 것은 customer가 favorite food list에서 해당 food에 대해서 review를 작성했다는 의미
					// 굳이 if문 없어도 됨. 항상 참.
					if(cus.getPersonalFoodReviews().contains(foodReviewId))
					{
						cus.getPersonalFoodReviews().remove(cus.getPersonalFoodReviews().indexOf(foodReviewId));
						FoodReviewList.getInstance().remove(foodReviewId);
					}
				}
			}
		}	
	}
	
	Food idValidCheck(int foodId)
	{
		for(Food food : FoodList.getInstance().getvalues())
		{
			if(food.getFoodId() ==foodId)
				return food;
		}
		return null;
	}
	
	Ingredient ingredientNameValidCheck(String ingreName)
	{
		Collection<Ingredient> ingres = IngredientList.getInstance().getvalues();
		for(Ingredient ingre : ingres)
		{
			if(ingre.getName().equals(ingreName))
				return ingre;
		}
		return null;
	}
	
	Food foodNameValidCheck(String foodName)
	{
		for(Food food : FoodList.getInstance().getvalues())
		{
			if(food.getName().equals(foodName))
				return food;
		}
		return null;
	}
	
	void inputIngredientsInfo(Food food)
	{
		int i=1;
		String ingreName;
		Ingredient ingre;
		
		System.out.println("요리에 필요한 재료들을 입력하십시오.(종료 : q)");
		while(true)
		{
			System.out.printf("재료 %d : ", i);
			//scan.nextLine();
			ingreName = scan.next();
			if(ingreName.equals("q"))
				break;
			
			if((ingre=ingredientNameValidCheck(ingreName)) != null)
			{
				
				if(food.getIngredients().contains(ingre.getIngredientId()))
				{
					System.out.println("이미 입력하신 재료입니다.");
					continue;
				}
				
				food.getIngredients().add(ingre.getIngredientId());
				System.out.println("입력 완료");
				i++;
			}
			else
			{
				System.out.println("입력하신 재료가 시스템에 존재하지 않습니다.");
			}
		}
	}
	
	FoodReview getFoodReviewByFood(CustomerAccount cus, int foodId)
	{
		for(FoodReview fr : FoodReviewList.getInstance().getvalues())
		{
			if(fr.getFoodId() == foodId && fr.getUserId().equals(cus.getAccountId()))
				return fr;
		}
		
		return null;
	}
}
