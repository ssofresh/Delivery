package delivery.view;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import delivery.controller.DeliveryManager;
import delivery.model.vo.Delivery;

public class DeliveryMenu {

	private Scanner sc = new Scanner(System.in);
	private DeliveryManager dm = new DeliveryManager();

	public DeliveryMenu() {
	}

	public void login() {
		String[] idpw = new String[2];
		System.out.println("ID 입력 : ");
		idpw[0] = sc.next();
		System.out.println("PW 입력 : ");
		idpw[1] = sc.next();

		dm.login(idpw);
	}

	public void mainMenu() {
		int num;
		do {
			System.out.println("***********사용자 화면 ***********");
			System.out.println("***********메뉴**********");
			System.out.println("1. 주문");
			System.out.println("2. 주문조회");
			System.out.println("3. 리뷰");
			System.out.println("메뉴선택 : ");

			num = sc.nextInt();
			switch (num) {
			case 1:
				//this.order(dm.printMenu());
				dm.insertOrder(this.order(dm.printMenu()));
				break;
			case 2: //printOrderNum(dm.selectOrderNum(this.inputOrderNum());
					this.printOrderNum(dm.selectOrderNum(this.inputOrderNum()));
					break;
			case 3:
			default:
				System.out.println("잘못입력함. 다시 입력 하세요");
			}
		} while (true);
	}

	public void adminMenu() {

		int num;
		do {
			System.out.println("**********관리자 화면*********");
			System.out.println("**********메뉴**********");
			System.out.println("1. 메뉴 관리");
			System.out.println("2. 리뷰 관리");
			System.out.println("메뉴선택 : ");

			num = sc.nextInt();
			switch (num) {
			case 1:
				adminSubMenu();
				break;
			case 2:
			default:
				System.out.println("잘못입력함. 다시입력하세요");
			}
		} while (true);

	}

	public void adminSubMenu() {

		int num;

		do {
			System.out.println("**********Sub메뉴**********");
			System.out.println("1. 메뉴 추가");
			System.out.println("2. 메뉴 삭제");
			System.out.println("3. 메뉴 저장");
			System.out.println("메뉴선택 : ");

			num = sc.nextInt();
			switch (num) {
			case 1:
			case 2:
			case 3:
			default:
				System.out.println("잘못입력함. 다시입력하세요");
			}
		} while (true);
	}

	/*
	 * public void printMenu(ArrayList<Delivery> dList) {
	 * System.out.println("*******메뉴판********");
	 * 
	 * for(Delivery d:dList) { System.out.println(d); } }
	 */

	public Delivery[] order(ArrayList<Delivery> dList) {
		System.out.println("*******메뉴판********");
		
		Delivery[] d2 = new Delivery[dList.size()+1];
		int[] price = new int[dList.size()+1];
		int c=1;
		for (Delivery d : dList) {
			d2[c] = new Delivery();
			d2[c] = d;
			System.out.println(d2[c++]);
			//System.out.println(d);
			//d2[c].setPrice(d.getPrice());
			//price[c++] = d.getPrice();
		}
		
		Delivery[] d = new Delivery[10];
		char ch;
		int count = 0;
		do {
			d[count] = new Delivery();
			System.out.println("주문할 메뉴 넘버 입력 : ");
			d[count].setNum(sc.nextInt());
			System.out.println("수량 입력 : ");
			d[count].setCount(sc.nextInt());
			//d[0].setTotPrice(d[0].getTotPrice()+price[d[count].getNum()]*d[count].getCount());
			d[0].setTotPrice(d[0].getTotPrice()+d2[d[count].getNum()].getPrice()*d[count].getCount());
			d[count].setFoodName(d2[d[count].getNum()].getFoodName());
			System.out.println("주문을 더 ㄱㄱ?(Y/N)");
			ch = sc.next().toUpperCase().charAt(0);
			d[0].setQuantity(count);
			count++;
		} while (ch == 'Y');
		//System.out.println(d[0].getTotPrice());
		//System.out.println(d[0].getFoodName()+"  "+d[0].getCount());
		//System.out.println(d[1].getFoodName()+"  "+d[1].getCount());
		//System.out.println(d[0].getQuantity());
		return d;
	}
	
	public int inputOrderNum() {
		System.out.println("조회할 주문번호 입력 : ");
		return sc.nextInt();
	}
	
	public void printOrderNum(ArrayList<Delivery> dList) {
		int price=0;
		for(Delivery d:dList) {
			System.out.println(d.getFoodName()+"  "+d.getCount()+"개");
			price = d.getTotPrice();
		}
		Delivery d = dList.get(0);
		System.out.println(d.getTotPrice()+"원");
	}

}
