package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDAO;
import com.javaex.dao.UserDAO;
import com.javaex.vo.BoardVO;
import com.javaex.vo.UserVO;

public class BoardController extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String url ="";
		String cmd =request.getParameter("cmd");
		cmd = cmd==null?"":cmd;
		BoardDAO dao = new BoardDAO();
		System.out.println(cmd);
		HttpSession session = request.getSession();
		
		if ("viewText".equals(cmd)) {
			
			String no = request.getParameter("no");
			BoardVO vo = dao.getText(no);
			request.setAttribute("vo", vo);
			url = "/WEB-INF/views/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		} else if("modifyform".equals(cmd)){
			
			UserVO authVO = (UserVO) session.getAttribute("authVO");
			
			if(authVO==null) {
				
				url = "./user?cmd=loginform";
				
				
			}	else {
				
				
				String no = request.getParameter("no");
				String user_no = authVO.getNo();
				BoardVO vo = dao.getText(no,user_no);
		
				if(vo==null) {
					
					url="./board?cmd=viewText&result=fail_mod";
					
				} else {
					System.out.println("ctrl"+vo.getTitle());
					request.setAttribute("vo", vo);
					url = "/WEB-INF/views/board/modify.jsp";
				}
				
				
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		} else if("modify".equals(cmd)){
			
			System.out.println("modify");
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dao.update(no,title,content);
			System.out.println(title);
			url = "/mysite/board";
			
			resp.sendRedirect(url);
			
			
		} else if("hit".equals(cmd)){
			
			System.out.println("uphit");
			String no = request.getParameter("no");
			String hit = request.getParameter("hit");
			dao.upHit(no,hit);
			
			url = "/mysite/board";
			
			resp.sendRedirect(url);
			
			
		} else if("writeform".equals(cmd)){
			
			
			UserVO authVO = (UserVO) session.getAttribute("authVO");
			if(authVO==null) {
				
				url = "./user?cmd=loginform";
				
				
			}	else {
				
				url = "./WEB-INF/views/board/write.jsp";
				
			}
		
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		} else if("write".equals(cmd)){
			
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			UserVO authVO = (UserVO) session.getAttribute("authVO");
			if(authVO==null) {
				
				url = "./user?cmd=loginform";
				
				
			}	else {
				
				String user_no = authVO.getNo();
				dao.insert(title,content,user_no);
				
				url = "/mysite/board";
				
			}
					
		
			resp.sendRedirect(url);
			
		} else if("delete".equals(cmd)){
			
			System.out.println("delete");
			UserVO authVO = (UserVO) session.getAttribute("authVO");
			String no = request.getParameter("no");
			
			if(authVO==null) {
				
				url = "./user?cmd=loginform";
				
				
			} else {
				
				String user_no = authVO.getNo();
				boolean flag = dao.delete(no,user_no);
				if(flag) {
					
					url = "/mysite/board?result=succ";
					
				} else  {
					
					url = "/mysite/board?result=fail";

				}
				
			}
			resp.sendRedirect(url);
			
		
		}	else {
	
			
			ArrayList<BoardVO> list = null;
			list = dao.getlist();
			
			if(list==null) {
				url ="/WEB-INF/views/board/list2.jsp";
			} else {
				request.setAttribute("list", list);
				url ="/WEB-INF/views/board/list.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		}
		
		
	
		
	}

}
