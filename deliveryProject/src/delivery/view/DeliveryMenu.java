package delivery.view;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import delivery.controller.DeliveryManager;
import delivery.model.vo.Delivery;

public class DeliveryMenu {

	private Scanner sc = new Scanner(System.in);
	private DeliveryManager dm = new DeliveryManager();
	private static String[] idpw = new String[2];
	
	public DeliveryMenu() {
	}

	public void login() {
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
			case 1: dm.insertOrder(this.order(dm.printMenu()));
				    break;
			case 2: this.printOrderNum(dm.selectOrderNum(this.inputOrderNum()));
					break;
			case 3: this.reviewMenu();
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
			case 1: adminSubMenu();
					break;
			case 2: adminSubReview();
					break;
			default:
				System.out.println("잘못입력함. 다시입력하세요");
			}
		} while (true);

	}

	public void adminSubMenu() {
		int num;

		do {
			System.out.println("**********메뉴관리페이지**********");
			System.out.println("1. 메뉴 보기");
			System.out.println("2. 메뉴 추가");
			System.out.println("3. 메뉴 삭제");
			System.out.println("메뉴선택 : ");

			num = sc.nextInt();
			switch (num) {
			case 1: this.printMenu(dm.printMenu());
					break;
			case 2: dm.insertMenu(this.inputMenu());
					break;
			case 3: dm.deleteMenu(this.inputFoodId());
					break;
			default:
				System.out.println("잘못입력함. 다시입력하세요");
			}
		} while (true);
	}
	
	public void adminSubReview() {
		int num;

		do {
			System.out.println("**********리뷰관리페이지**********");
			System.out.println("1. 리뷰 보기");
			System.out.println("2. 리뷰 삭제");
			System.out.println("메뉴선택 : ");

			num = sc.nextInt();
			switch (num) {
			case 1: this.printReview(dm.selectReview());
					break;
			case 2: dm.deleteReview(inputNum());
					break;
			default:
				System.out.println("잘못입력함. 다시입력하세요");
			}
		} while (true);
	}
	public void reviewMenu() {
		int num;

		do {
			System.out.println("**********Review메뉴**********");
			System.out.println("1. 리뷰 보기");
			System.out.println("2. 리뷰 작성");
			System.out.println("메뉴선택 : ");

			num = sc.nextInt();
			switch (num) {
			case 1: this.printReview(dm.selectReview());
					break;
			case 2: dm.insertReview(this.inputReview());
					break;
			case 3: 
			default:
				System.out.println("잘못입력함. 다시입력하세요");
			}
		} while (true);
	}

	public Delivery[] order(ArrayList<Delivery> dList) {
		System.out.println("*******메뉴판********");
		
		Delivery[] d2 = new Delivery[dList.size()+1];
		int[] price = new int[dList.size()+1];
		int c=1;
		for (Delivery d : dList) {
			d2[c] = new Delivery();
			d2[c] = d;
			System.out.println(d2[c++]);
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
			d[0].setTotPrice(d[0].getTotPrice()+d2[d[count].getNum()].getPrice()*d[count].getCount());
			d[count].setFoodName(d2[d[count].getNum()].getFoodName());
			System.out.println("주문을 더 ㄱㄱ?(Y/N)");
			ch = sc.next().toUpperCase().charAt(0);
			d[0].setQuantity(count);
			count++;
		} while (ch == 'Y');
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
	
	public void printReview(ArrayList<String> rList) {
		for(int i=0;i<rList.size();i++) {
			System.out.print("리뷰 번호 : ");
			System.out.println(rList.get(i));
			i++;
			System.out.print("아이디 : ");
			System.out.println(rList.get(i));
			i++;
			System.out.print("리뷰 내용 : ");
			System.out.println(rList.get(i));
			System.out.println("--------------------------");
		}
	}
	public String[] inputReview() {
		String[] review = new String[2];
		review[0] = idpw[0];
		System.out.println("리뷰 내용을 입력하세요 : ");
		sc.nextLine();
		review[1] = sc.nextLine();
		
		return review;
	}

	public Delivery inputMenu() {
		Delivery d = new Delivery();
		System.out.println("추가할 음식 이름 입력 : ");
		d.setFoodName(sc.next());
		System.out.println("가격 입력 : ");
		d.setPrice(sc.nextInt());
		return d;	
	}
	
	public int inputFoodId() {
		System.out.println("삭제할 음식번호 입력 : ");
		
		return sc.nextInt();
	}
	
	public void printMenu(ArrayList<Delivery> dList) {
		for(Delivery d:dList) {
			System.out.println(d);
		}
	}
	
	public int inputNum() {
		System.out.println("삭제할 리뷰 번호 입력 : ");
		
		return sc.nextInt();
	}
}
