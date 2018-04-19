package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.util.ConnectionManager;
import com.javaex.vo.UserVO;

public class UserDAO {

	
	public void insert(UserVO vo) {
		
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		PreparedStatement pstmt = null; 
		System.out.println(vo.getName()+vo.getEmail()+vo.getPassword()+vo.getGender());
		String sql = "insert into user_tbl values(seq_user_no.nextval,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int affectedCount = pstmt.executeUpdate();
			
			if(affectedCount>0) {
				
				System.out.println("등록완료");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			mgr.connectClose(con, pstmt, null);
		}
		
	}
	public void update(UserVO vo) {
		
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		PreparedStatement pstmt = null; 
		System.out.println(vo.getNo() + vo.getName()+vo.getEmail()+vo.getPassword()+vo.getGender());
		String sql = "update user_tbl set name=?,password=?,gender=? where no=? and email=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getNo());
			pstmt.setString(5, vo.getEmail());
			
			int affectedCount = pstmt.executeUpdate();
			
			if(affectedCount>0) {
				
				System.out.println("수정완료");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			mgr.connectClose(con, pstmt, null);
		}
		
	}
		
		
		public UserVO getUser(String email,String password) {
			
			ConnectionManager mgr = new ConnectionManager();
			Connection con = mgr.getConnetion();
			PreparedStatement pstmt = null; 
			String sql = "select no,name from user_tbl where email=? and password = ?";
			ResultSet rs = null;
			UserVO vo = null;
			try {
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, password);
		
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo=new UserVO();
					String no =  rs.getString(1);
					String name = rs.getString(2);
					System.out.println(no);
					vo.setNo(no);
					vo.setName(name);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mgr.connectClose(con, pstmt, rs);
			}
			return vo;
	}
		public UserVO getUserAll(String no) {
			
			ConnectionManager mgr = new ConnectionManager();
			Connection con = mgr.getConnetion();
			PreparedStatement pstmt = null; 
			String sql = "select * from user_tbl where no ="+no;
			ResultSet rs = null;
			UserVO vo = null;
			try {
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo=new UserVO();
					String name = rs.getString(2);
					String email = rs.getString(3);
					String gender = rs.getString(5);
					
					vo.setNo(no);
					vo.setName(name);
					vo.setEmail(email);
					vo.setGender(gender);
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mgr.connectClose(con, pstmt, rs);
			}
			return vo;
		}
		
}
