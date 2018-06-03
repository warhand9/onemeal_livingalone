package org.onemeallivingalone.item.ui;

import org.onemeallivingalone.center.ui.ManagerUI;
import java.util.Scanner;
import java.util.Vector;
import org.onemeallivingalone.item.Food;

public class ItemListMenuManagerUI extends ManagerUI{

	Scanner scan = new Scanner(System.in);	
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
		int num = 0;
		String name = "";
		System.out.print("Let me know the new number of Food ID");
		num = scan.nextInt();
		scan.nextLine();
		if((num < 1)) {
			System.out.println("That number is not valid");
			return ;
		}
		//checking existence
		if() {
			System.out.println("That food is already registered");
			return ;
		}
		
		System.out.print("Food name : ");
		name = scan.nextLine();
		
		System.out.println("finished to add new food.");
		
	}
	void removeFood() {
		int num = 0;
		System.out.print("Which food do you want to delete ?");
		num = scan.nextInt();
		scan.nextLine();
	}
	void addIngredient() {
		int num = 0;
		System.out.print("Let me know the new number of ingredient ID");
		num = scan.nextInt();
		scan.nextLine();
	}
	void removeIngredient(){
		int num = 0;
		System.out.print("Which ingredient do you want to delete ?");
		num = scan.nextInt();
		scan.nextLine();
	}
}

