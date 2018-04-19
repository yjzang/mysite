package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDAO;
import com.javaex.vo.BoardVO;

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
		if ("viewText".equals(cmd)) {
			
			String no = request.getParameter("no");
			BoardVO vo = dao.getText(no);
			request.setAttribute("vo", vo);
			url = "/WEB-INF/views/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		} else if("modifyform".equals(cmd)){
			
			String no = request.getParameter("no");
			BoardVO vo = dao.getText(no);
			request.setAttribute("vo", vo);
			url = "/WEB-INF/views/board/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
			
		} else if("modify".equals(cmd)){
			
			System.out.println("modify");
			String no = request.getParameter("no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			dao.update(no,title,content);
			
			url = "/mysite/board";
			
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
