package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javaex.util.ConnectionManager;
import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;

public class BoardDAO {

	
	public ArrayList<BoardVO> getlist(String begin,String end) {
		

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select * from (select tbl.*,rownum rnum from (select b.*,u.name from board_tbl b,user_tbl u where b.user_no=u.no order by b.no desc) tbl) where rnum between ? and ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = null;
		BoardVO vo = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, begin);
			pstmt.setString(2, end);
			
			list= new  ArrayList<BoardVO>();
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo= new BoardVO();
				vo.setNo(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getString(4));
				vo.setDate(rs.getString(5));
				vo.setUser_no(rs.getString(6));
				vo.setName(rs.getString(7));
				vo.setRnum(rs.getString(8));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, rs);
		}
		
		return list;
		
		
	}
	
	public String getlist_total() {
		

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select count(*) from board_tbl";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String count = null;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			
				count = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, rs);
		}
		
		return count;
		
		
	}
	public BoardVO getText(String no) {

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
				System.out.println("dao"+vo.getTitle());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, stmt, rs);
		}
		
		return vo;
		
		
	}
	
	public BoardVO getText(String no,String user_no) {

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select * from board_tbl where no=? and user_no=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.setString(2, user_no);
			rs = pstmt.executeQuery();
			
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
			mgr.connectClose(con, pstmt, rs);
		}
		
		return vo;
		
		
	}
	
	
	public void update(String no,String title,String content) {
		
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
	public void upHit(String no,int hit) {

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "update board_tbl set hit=? where no="+no;
		PreparedStatement pstmt = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, hit);
			int affectedCount = pstmt.executeUpdate();
			
			if (affectedCount>0) {
				
				System.out.println("조회수 up");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, null);
		}
		
		
	}
	
	public void insert(String title,String content,String user_no) {

		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "insert into board_tbl values(seq_board_no.nextval,?,?,'0',sysdate,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, user_no);
			
			int affectedCount = pstmt.executeUpdate();
			
			if (affectedCount>0) {
				
				System.out.println("등록완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, null);
		}
		
		
	}
	
	public boolean delete(String no, String user_no) {
		
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "delete from board_tbl where no=? and user_no=?";
		PreparedStatement pstmt = null;
		
		try { 
			pstmt=con.prepareStatement(sql);
		
			pstmt.setString(1, no);
			pstmt.setString(2, user_no);
			
			int affectedCount = pstmt.executeUpdate();
			
			if (affectedCount>0) {
				
				System.out.println("삭제완료");
				flag =true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			mgr.connectClose(con, pstmt, null);
		}
		
		return flag;
		
	} public ArrayList<BoardVO> searchList(String kwd) {
		System.out.println(kwd);
		ConnectionManager mgr = new ConnectionManager();
		Connection con = mgr.getConnetion();
		String sql = "select b.*,u.name from board_tbl b,user_tbl u where b.user_no=u.no and b.title like '%"+kwd+"%'";
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = null;
		BoardVO vo = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			list= new ArrayList<BoardVO>();
			while(rs.next()) {
				
				vo= new BoardVO();
				vo.setNo(rs.getString(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getString(4));
				vo.setDate(rs.getString(5));
				vo.setUser_no(rs.getString(6));
				vo.setName(rs.getString(7));
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
	
	
}
