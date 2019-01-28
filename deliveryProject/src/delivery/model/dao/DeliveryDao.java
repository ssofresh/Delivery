package delivery.model.dao;

import java.sql.*;
import java.util.ArrayList;

import delivery.model.vo.Delivery;

import static common.JDBCTemplate.*;

public class DeliveryDao {

	public String[] login(Connection conn, String[] idpw) {
		String[] idpw2 = new String[2];
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from idpw where id = ? and pw = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, idpw[0]);
			pstmt.setString(2, idpw[1]);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				idpw2[0] = rset.getString("id");
				idpw2[1] = rset.getString("pw");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return idpw2;
	}

	public ArrayList<Delivery> printMenu(Connection conn) {
		ArrayList<Delivery> dList = new ArrayList<Delivery>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from menu";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Delivery d = new Delivery();
		
				d.setNum(rset.getInt("food_id"));
				d.setFoodName(rset.getString("food_name"));
				d.setPrice(rset.getInt("price"));
				
				dList.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return dList;
	}

	public int insertOrder(Connection conn, Delivery[] d) {
		int result=0;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		String query = "insert into order_num values (seq_order_num.nextval, ?)";
		ResultSet rset = null;
		String query2 = "select max(num) from order_num";
		
		int num=0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, d[0].getTotPrice());
			stmt = conn.createStatement();
			result = pstmt.executeUpdate();
			
			rset = stmt.executeQuery(query2);
			if(rset.next())
				num = rset.getInt("max(num)");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			close(pstmt);
		}
		return num;
	}

	public int insertSubOrder(Connection conn, Delivery[] d, int num) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "insert into order_sub values (?, ?, ?)";
		
		try {
			for(int i=0;i<=d[0].getQuantity();i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, num);
				pstmt.setString(2, d[i].getFoodName());
				pstmt.setInt(3, d[i].getCount());
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

	public ArrayList<Delivery> selectOrderNum(Connection conn, int num) {
		ArrayList<Delivery> dList = new ArrayList<Delivery>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select food_name, count, price from order_sub join order_num using (num) where num = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Delivery d = new Delivery();
				
				d.setFoodName(rset.getString("food_name"));
				d.setCount(rset.getInt("count"));
				d.setTotPrice(rset.getInt("price"));
				
				dList.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dList;
	}

}
