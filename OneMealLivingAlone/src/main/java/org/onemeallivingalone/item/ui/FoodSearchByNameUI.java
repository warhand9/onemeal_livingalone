package org.onemeallivingalone.item.ui;

import java.util.ArrayList;
import java.util.List;

import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;

public class FoodSearchByNameUI extends ManagerUI{

	@Override
	public void interact() {
		int i=1;
		String foodname;
		ArrayList<Food> filteringFoods = null; 
		System.out.print("검색하고 하는 요리 이름을 입력하십시오. : ");
		scan.nextLine();
		foodname = scan.nextLine();
		
		filteringFoods=(ArrayList<Food>)FoodList.getInstance().filteringByName(foodname);
		
		/*if((filteringFoods=FoodList.getInstance().filteringByName(foodname)) == null)
		{
			System.out.println("검색하신 요리가 없습니다.");
			return;
		}*/
		for(Food f : filteringFoods)
		{
			System.out.printf("%d. ",i+1);
			System.out.println(f.getSummaryDescription());
		}
		
		/*for(i=0; i<filteringFoods.size(); i++)
		{
			Food food = FoodList.get(filteringFoods.get(i));
			System.out.println(food.getSummaryDescription());
		}*/
		
		new FoodViewUI().interact();
		
	}

}
