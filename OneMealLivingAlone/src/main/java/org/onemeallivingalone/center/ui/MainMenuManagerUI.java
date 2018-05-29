package org.onemeallivingalone.center.ui;

import org.onemeallivingalone.account.ui.LoginMenuManagerUI;
import org.onemeallivingalone.account.ui.SystemAccountManagerUI;
import org.onemeallivingalone.center.CurrentUser;
import org.onemeallivingalone.item.ui.IngredientSearchManagerUI;
import org.onemeallivingalone.item.ui.FoodSearchManagerUI;
import org.onemeallivingalone.item.ui.PersonalIngredientsManagerUI;
import org.onemeallivingalone.item.ui.FavoriteFoodnFoodReviewManagerUI;
import org.onemeallivingalone.item.ui.ItemListMenuManagerUI;

public class MainMenuManagerUI extends ManagerUI {

	@Override
	public void interact() {
		int order = -1;
		
		while (order != 0) {
			System.out.println(CurrentUser.getStatusString());
			System.out.println("수행할 명령을 선택해주십시오.");
			System.out.println("1. 계정 메뉴");
			System.out.println("2. 식재료 검색");
			System.out.println("3. 요리 검색");		// 요리내용 보기(관심 요리 추가) / 식재료로 요리 검색 / 이름으로 요리 검색 
			System.out.println("4. [이용자] 개인 식재료 관리");	// 개인식재료 보기 / 개인 식재료 추가 / 개인식재료 삭제
			System.out.println("5. [이용자] 관심 요리/요리 후기 관리");	// 관심 요리 보기 / 특정 관심 요리에 대한 요리 후기 추가,수정,삭제
			System.out.println("6. [관리자] 시스템 계정 관리");
			System.out.println("7. [관리자] 식재료/요리 목록 관리");
			System.out.println("0. 프로그램 종료");

			order = scan.nextInt();

			switch(order) {
			case 1:
				LoginMenuManagerUI account = new LoginMenuManagerUI();
				account.interact();
				break;
			case 2:
				IngredientSearchManagerUI ingredientSearch = new IngredientSearchManagerUI();
				ingredientSearch.interact();
				break;
			case 3:
				FoodSearchManagerUI foodSearch = new FoodSearchManagerUI();
				foodSearch.interact();
				break;
			case 4:
				PersonalIngredientsManagerUI personalIngredientSearch = new PersonalIngredientsManagerUI();
				personalIngredientSearch.interact();
				break;
			case 5:
				FavoriteFoodnFoodReviewManagerUI favoriteFoodnReviewMenu = new FavoriteFood_FoodReviewManagerUI();
				favoriteFoodnReviewMenu.interact();
				break;
			case 6:
				SystemAccountManagerUI systemAcc = new SystemAccountManagerUI();
				systemAcc.interact();
				break;
			case 7:
				ItemListMenuManagerUI itemListMenu = new ItemListMenuManagerUI();
				itemListMenu.interact();
				break;
			default:
				System.out.println("알 수 없는 명령어입니다.");
				break;
			case 0: // 종료
				break;
			}
		}
	}

}
