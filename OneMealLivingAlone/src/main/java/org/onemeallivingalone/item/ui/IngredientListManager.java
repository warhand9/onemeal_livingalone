package org.onemeallivingalone.item.ui;

import java.util.ArrayList;
import java.util.List;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;
import org.onemeallivingalone.item.Ingredient;
import org.onemeallivingalone.item.IngredientList;

public class IngredientListManager extends ManagerUI{

	@Override
	public void interact() {
		//List<Ingredient> ingres = new ArrayList<Ingredient>(IngredientList.getInstance().getvalues());
		List<Ingredient> ingres;
		int cur_page=1;
		int listcnt=0;
		//int entire_page = (ingres.size()/5==0) ? ingres.size()/5 + 1 : ingres.size()/5+1+1;
		int entire_page;
		int i;
		int select;
		
		while(true)
		{
			ingres = new ArrayList<Ingredient>(IngredientList.getInstance().getvalues());
			entire_page = (ingres.size()/5==0) ? ingres.size()/5 + 1 : ingres.size()/5+1+1;
			// 5개 출력
			listcnt = ingres.size();
			if(listcnt==0)
			{
				System.out.println("시스템에 식재료 정보가 비었습니다.");
			}
			else
			{
				System.out.println("<식재료 목록>");
				for(i=0; i<5; i++)
				{
					if(((cur_page-1)*5 + i)>=listcnt)
						break;
					
					System.out.printf("%d. ",i+1);
					System.out.println(ingres.get((cur_page-1)*5+i).getDescription());
				}
			}
			
				
			System.out.print("(1.이전페이지		2.다음페이지	3.식재료 추가	4.식재료 수정	5.식제료 삭제	6.종료)입력 : ");
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
				addIngredient();
				break;
			case 4:
				modifyIngredient();
				break;
			case 5:
				deleteIngredient();
				break;
			case 6:
				return;
			}
		}
	}
	
	public void addIngredient()
	{
		int ingreId;
		String ingreName;
		int ingrePrice;
		Ingredient ingre;
		
		System.out.println("식재료 추가>>");
		
		System.out.println("새로운 식재료 ID 입력 : ");
		ingreId = scan.nextInt();
		while(idValidCheck(ingreId)!=null)
		{
			System.out.println("이미 존재하는 ID입니다. 다시 입력하십시오.");
			ingreId = scan.nextInt();
		}
		
		System.out.println("추가하고자 하는 식재료 이름을 입력하십시오.");
		ingreName = scan.next();
		while(nameValidCheck(ingreName)!=null)
		{
			System.out.println("이미 존재하는 이름입니다. 다시 입력하십시오.");
			ingreName = scan.next();
		}
		
		System.out.println("추가하고자 하는 식재료의 가격을 입력하십시오.");
		ingrePrice = scan.nextInt();
		
		ingre = new Ingredient(ingreId, ingreName, ingrePrice);
		System.out.println("새로운 식재료>");
		System.out.println(ingre.getDescription());
		
		IngredientList.getInstance().put(ingre);
	}
	
	public void modifyIngredient()
	{
		int ingreId;
		int price;
		Ingredient ingre;
		
		System.out.println("식재료 정보 수정>>");
		System.out.println("수정하고자 하는 식재료 id번호를 입력하십시오.");
		ingreId = scan.nextInt();
		if((ingre=idValidCheck(ingreId))==null)
		{	
			System.out.println("존재하지 않는 식재료 입니다.");
			return;
		}
		else
		{
			System.out.println("선택하신 식재료>>");
			System.out.println(ingre.getDescription());
			System.out.print("수정할 가격을 입력하십시오. : ");
			price = scan.nextInt();
			ingre.setPrice(price);
			System.out.println("수정이 완료되었습니다.");
			System.out.println("수정후 > " + ingre.getDescription());
		}	
	}
	public void deleteIngredient()
	{
		int ingreId;
		int i;
		CustomerAccount cus;
		
		System.out.println("식재료 삭제>>");
		System.out.println("삭제할 식재료 Id를 입력하십시오. : ");
		ingreId = scan.nextInt();
		if(idValidCheck(ingreId)==null)
		{	
			System.out.println("존재하지 않는 식재료 입니다.");
			return;
		}
		else
		{
			// ingredient리스트에서도 지우고
			IngredientList.getInstance().remove(ingreId);
			
			// 해당 ingredient를 재료로 가지고 있는 요리들에서도 지우고
			for(Food f : FoodList.getInstance().getvalues())
			{
				if(f.getIngredients().contains(ingreId))
					f.getIngredients().remove(f.getIngredients().indexOf(ingreId));
			}
			
			// 개인 식재료 리스트에서도 지우고
			for(Account acc : AccountList.getValues())
			{
				if(acc instanceof CustomerAccount)
				{
					cus = (CustomerAccount)acc;
					if(cus.getPersonalIngredients().contains(ingreId))
					{
						cus.getPersonalIngredients().remove(cus.getPersonalIngredients().indexOf(ingreId));
					}
				}
			}
		}	
	}
	
	Ingredient idValidCheck(int ingreId)
	{
		for(Ingredient ingre : IngredientList.getInstance().getvalues())
		{
			if(ingre.getIngredientId()==ingreId)
				return ingre;
		}
		return null;
	}
	
	Ingredient nameValidCheck(String ingreName)
	{
		for(Ingredient ingre : IngredientList.getInstance().getvalues())
		{
			if(ingre.getName().equals(ingreName))
				return ingre;
		}
		return null;
	}
}
