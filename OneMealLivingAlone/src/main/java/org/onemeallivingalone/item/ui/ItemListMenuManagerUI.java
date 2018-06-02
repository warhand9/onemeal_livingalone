package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.center.ui.ManagerUI;

public class ItemListMenuManagerUI extends ManagerUI{

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		int key =0;
		while((key = selectMenu())!= 0) {
			switch(key) {
			case 1: addFood(); break;
			case 2: removeFood(); break;
			case 3: addIngredient(); break;
			case 4: removeIngredient(); break;
			
			default: System.out.println("You chosed wrong number"); break;
			}
		}
		System.out.println("End");
		
	}
	int selectMenu() {
		System.out.println("1. add food 2. remove food, 3. add ingredient, 4. remove ingredient, 0 to quit the menu" );
		int key = scan.nextInt();
		scan.nextLine();
		
		return key;
	}
	
	void addFood() {
		
	}
	void removeFood() {
		
	}
	void addIngredient() {
		
	}
	void removeIngredient(){
		
	}
}

