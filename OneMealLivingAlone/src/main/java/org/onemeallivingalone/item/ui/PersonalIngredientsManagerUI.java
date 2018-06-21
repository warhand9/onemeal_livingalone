package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.center.ui.ManagerUI;
import org.onemeallivingalone.item.IngredientList;

public class PersonalIngredientsManagerUI extends ManagerUI{

	int select=-1;
	CustomerAccount cusacnt;
	
	@Override
	public void interact() {
		int i;
		
		if(CurrentUser.get()==null || CurrentUser.get() instanceof AdminAccount)
		{
			System.out.println("이용자 계정 로그인이 필요한 서비스 입니다.");
			return;
		}
		cusacnt = (CustomerAccount)CurrentUser.get();
		
		while(select!=0)
		{
			int choose;
			
			System.out.printf("%s님의 개인 식재료 리스트>>\n", CurrentUser.get().getAccountId());
			
			for(i=0; i<cusacnt.getPersonalIngredients().size(); i++)
			{
				int ingredientID = cusacnt.getPersonalIngredients().get(i);
				System.out.println("ID:" + IngredientList.getInstance().get(ingredientID).toString());
			}
			
			System.out.println("기능을 선택하십시오.(1.개인식재료 추가	2.개인식재료 삭제		3.종료) : ");
			choose = scan.nextInt();
			switch (choose)
			{
			case 1:
				addPersonalIngredient();
				System.out.println("\n");
				break;
			case 2:
				removePersonalIngredient();
				System.out.println("\n");
				break;
			case 3:
				return;
				
			default:
				System.out.println("알 수 없는 명령어입니다.");
				System.out.println("\n");
				break;
			}
		}
	}
	
	void addPersonalIngredient()
	{
		System.out.println("식재료를 검색기능을 통하여 개인식재료를 추가하십시오.!!");
		new IngredientSearchManagerUI().interact();
	}
	
	void removePersonalIngredient()
	{
		int ingreID;
		System.out.println("삭제할 식재료의 ID를 입력하십시오.(종료:0)");
		ingreID = scan.nextInt();
		if(ingreID == 0)
			return;
		if(cusacnt.getPersonalIngredients().contains(ingreID)==false)
		{
			System.out.println("입력하신 식재료가 개인식재료 리스트에 존재하지않습니다.");
			return;
		}
		
		cusacnt.getPersonalIngredients().remove(cusacnt.getPersonalIngredients().indexOf(ingreID));
		System.out.println("해당 식재료가 개인 식재료 리스트에서 제거되었습니다.");
	}

}
