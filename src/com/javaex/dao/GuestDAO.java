package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javaex.util.ConnectionManager;
import com.javaex.vo.GuestVO;

public class GuestDAO {

	public void add(GuestVO vo) {
		
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		PreparedStatement pstmt =null;
		String sql = "Insert into guest_tbl values(seq_guestbook2.nextval,?,?,?,sysdate)";
		try {
			
			System.out.println(vo.getNo()+vo.getName()+vo.getPw()+vo.getContent()+vo.getDate());
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getContent());
			
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount > 0) {
				System.out.println("등록 완료");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally  {
			mgr.connectClose(con, pstmt, null);
		}
		
	}
	
	public boolean delete(String no,String password) {
		
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		PreparedStatement pstmt =null;
		String sql = "delete from guest_tbl where no=? and password=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, password);
			
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount > 0) {
				System.out.println("삭제 완료");
				flag=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("비밀번호가 틀렸습니다.");
			e.printStackTrace();
		} finally  {
			mgr.connectClose(con, pstmt, null);
		}
		
		return flag;
	}
	
	public ArrayList<GuestVO> getList() {
		
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		Statement stmt =null;
		ResultSet rs = null;
		ArrayList<GuestVO> list = null;
		String sql = "select * from guest_tbl order by no";
		try {
			stmt = con.createStatement();
			list = new ArrayList<GuestVO>();;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				
				GuestVO vo = new GuestVO();
				vo.setNo(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setPw(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setDate(rs.getString(5));
				list.add(vo);
			}
			
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally  {
			mgr.connectClose(con, stmt, rs);
		}

		return list;
	}
	
	
}
	
	

