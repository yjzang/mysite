package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javaex.util.ConnectionManager;
import com.javaex.vo.BoardVO;

public class BoardDAO {

	
	public ArrayList<BoardVO> getlist() {
		
		System.out.println("getlist");
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select * from board_tbl";
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = null;
		BoardVO vo = null;
		try {
			stmt = con.createStatement();
			list= new  ArrayList<BoardVO>();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				vo= new BoardVO();
				vo.setNo(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getString(4));
				vo.setDate(rs.getString(5));
				vo.setUser_no(rs.getString(6));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, stmt, rs);
		}
		
		return list;
		
		
	}
	public BoardVO getText(String no) {
		System.out.println("getText");
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select * from board_tbl where no="+no;
		Statement stmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				vo= new BoardVO();
				vo.setNo(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getString(4));
				vo.setDate(rs.getString(5));
				vo.setUser_no(rs.getString(6));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, stmt, rs);
		}
		
		return vo;
		
		
	}
	
	
	public void update(String no,String title,String content) {
		System.out.println("update");
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "update board_tbl set title=?, content=? where no="+no;
		PreparedStatement pstmt = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			int affectedCount = pstmt.executeUpdate();
			
			if (affectedCount>0) {
				
				System.out.println("수정완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, null);
		}
		
		
	}
	
}
