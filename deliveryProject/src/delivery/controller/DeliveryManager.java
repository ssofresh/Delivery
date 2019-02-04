package delivery.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import delivery.model.service.DeliveryService;
import delivery.model.vo.Delivery;
import delivery.view.DeliveryMenu;

public class DeliveryManager {
	
	private DeliveryService dservice = new DeliveryService();
	
	public DeliveryManager() {}
	
	
	
	public void login(String[] idpw) {
		String[] idpw2 = dservice.login(idpw);
		String adminID = "admin";
		String adminPW = "admin";
		
		if(idpw[0].equals(null) || idpw[1].equals(null)) {
			System.out.println("틀렸음. 다시입력 ㄱㄱ");
			new DeliveryMenu().login();
		} else if(idpw2[0].equals(adminID) && idpw2[1].equals(adminPW)) {
			new DeliveryMenu().adminMenu();
		} else if(idpw2[0] != null && idpw2[1] != null) {
			new DeliveryMenu().mainMenu();
		} else {
			System.out.println("틀렸음. 다시입력 ㄱㄱ");
			new DeliveryMenu().login();
		}
		
	}

	public ArrayList<Delivery> printMenu() {
		ArrayList<Delivery> dList = dservice.printMenu();		
		if(dList.size() == 0) {
			System.out.println("메뉴 불러오기 실패");
			new DeliveryMenu().mainMenu();
		}
		return dList;
	}

	public void insertOrder(Delivery[] d) {
		
		int num = dservice.insertOrder(d);
		int result = dservice.insertSubOrder(d, num);
		if(result > 0) {
			System.out.println("주문 성공 - 주문번호:"+num);
		}else {
			System.out.println("주문 실패");
		}
		
	}

	public ArrayList<Delivery> selectOrderNum(int num) {
		ArrayList<Delivery> dList = dservice.selectOrderNum(num); 
		if(dList.size()==0)
			System.out.println("해당 주문번호 조회 실패.");
		else
			System.out.println("해당 주문번호 조회 성공");
		return dList;
	}
	
	public void insertReview(String[] review) {
		int result = dservice.insertReview(review);
		
		if(result > 0) {
			System.out.println("리뷰 등록 성공");
		}else {
			System.out.println("리뷰 등록 실패");
		}
	}



	public ArrayList<String> selectReview() {
		ArrayList<String> rList = dservice.selectReview();
		if(rList.size() == 0) {
			System.out.println("리뷰 불러오기 실패");
			new DeliveryMenu().mainMenu();
		}
		return rList;
	}



	public void insertMenu(Delivery d) {
		int result = dservice.insertMenu(d);
		if(result > 0) {
			System.out.println("메뉴 등록 성공");
		}else {
			System.out.println("메뉴 등록 실패");
		}
	}



	public void deleteMenu(int foodId) {
		int result = dservice.deleteMenu(foodId);
		if(result > 0) {
			System.out.println("메뉴 삭제 성공");
		}else {
			System.out.println("메뉴 삭제 실패");
		}
		
	}



	public void deleteReview(int num) {
		int result = dservice.deleteReview(num);
		if(result > 0) {
			System.out.println("리뷰 삭제 성공");
		}else {
			System.out.println("리뷰 삭제 실패");
		}
		
	}
}
