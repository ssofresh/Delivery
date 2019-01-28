package delivery.model.service;

import java.sql.*;
import java.util.ArrayList;

import delivery.model.dao.DeliveryDao;
import delivery.model.vo.Delivery;

import static common.JDBCTemplate.*;
public class DeliveryService {

	private DeliveryDao ddao = new DeliveryDao();
	
	public DeliveryService() {}
	
	public String[] login(String[] idpw) {
		Connection conn = getConnection();
		String[] idpw2 = ddao.login(conn, idpw);
		close(conn);
		return idpw2;
	}

	public ArrayList<Delivery> printMenu() {
		Connection conn = getConnection();
		ArrayList<Delivery> dList = ddao.printMenu(conn);
		close(conn);
		
		return dList;
	}

	public int insertOrder(Delivery[] d) {
		Connection conn = getConnection();
		int result = ddao.insertOrder(conn, d);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertSubOrder(Delivery[] d, int num) {
		Connection conn = getConnection();
		int result = ddao.insertSubOrder(conn, d, num);
		if(result > 0) 
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public ArrayList<Delivery> selectOrderNum(int num) {
		Connection conn = getConnection();
		ArrayList<Delivery> dList = ddao.selectOrderNum(conn, num);
		close(conn);
		return dList;
	}

	public int insertReview(String[] review) {
		Connection conn = getConnection();
		int result = ddao.insertReview(conn, review);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}


	
}
