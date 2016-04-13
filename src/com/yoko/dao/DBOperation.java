package com.yoko.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * @author
 * @数据库操作
 */
public class DBOperation {
	DB db = new DB();

	/** 判断登陆 */
	public boolean login(String user, String pass) {
		boolean ret = false;
		Connection conn = DB.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from Manager where username='" + user
					+ "' and password='" + pass + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ret = true;
				System.out.println("登陆成功");
			}
		} catch (SQLException e) {
			ret = false;
			e.printStackTrace();
		} finally {
			DB.close(conn, stmt, rs);
		}
		return ret;
	}

	/**
	 * 上传成功案例
	 * 
	 * @return boolean
	 */
	public boolean uploadCase(String name, String prePic1, String prePic2,
			String aftPic1, String aftPic2, String intro) {
		boolean ret = false;
		String sql = "insert into ZNcase values('" + name + "','" + prePic1
				+ "','" + prePic2 + "','" + aftPic1 + "','" + aftPic2 + "','"
				+ intro + "')";
		Connection conn = DB.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			if (name != "" && name != "null" && name != null && prePic1 != ""
					&& prePic1 != "null" && prePic1 != null && prePic2 != ""
					&& prePic2 != "null" && prePic2 != null && aftPic1 != ""
					&& aftPic1 != "null" && aftPic1 != null && aftPic2 != ""
					&& aftPic2 != "null" && aftPic2 != null && intro != ""
					&& intro != "null" && intro != null) {
				stmt.executeUpdate(sql);
				System.out.println("上传成功");
				ret = true;
			} else {
				System.out.println("输入项不合法");
				ret = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("上传失败");
			ret = false;
		} finally {
			DB.close(conn, stmt, rs);
		}
		return ret;
	}

	public void addMessage(String username, String phone, String message) throws SQLException {
		Connection conn = DB.getConn();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("INSERT INTO ZNmessage VALUES('"+username+"', '"+phone+"', '"+message+"')");
		DB.close(conn, stmt, null);
	}

	public String getSixReviews() throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append((char)1);
		Connection conn = DB.getConn();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT TOP 6 name, phone, review FROM ZNreviews ORDER BY id DESC");
		while(rs.next()) {
			String name = rs.getString("name");
			String phone = rs.getString("phone");
			String review = rs.getString("review");
			sb.append(name); sb.append((char)3);
			sb.append(phone); sb.append((char)3);
			sb.append(review); sb.append((char)4);
		}
		DB.close(conn, stmt, rs);
		if(sb.length() > 1) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
}
