package org.onemeallivingalone.center.ui;

import java.util.Scanner;

/** 기본적인 User Interface를 정의하는 클래스.
 *
 * 모든 ~MangerUI들은 이 클래스를 상속합니다.
 */
public abstract class ManagerUI {

	protected static final Scanner scan = new Scanner(System.in); ///< 공용 Scanner 객체
	
	/** 클래스의 UI 루틴을 호출합니다. 
	 */
	public abstract void interact();
	
}
