package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDAO;
import com.javaex.vo.GuestVO;


public class GuestController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		String url = "";
		cmd = cmd==null?"":cmd;
		
		if("add".equals(cmd)) {
			System.out.println("add");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			GuestVO vo = new GuestVO();
			GuestDAO dao = new GuestDAO();
			
			
			vo.setNo("");
			vo.setDate("");
			vo.setName(name);
			vo.setPw(password);
			vo.setContent(content);
			dao.add(vo);
			url= "./guest?cmd=getList";
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);		
		
			
			
		} else if ("getList".equals(cmd)) {
			
			GuestDAO dao = new GuestDAO();
			ArrayList<GuestVO> list = dao.getList();
			
			url = "./WEB-INF/views/guestbook/list.jsp";
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);		
		
			
		} else if ("deleteform".equals(cmd)) {
			
			url = "./WEB-INF/views/guestbook/deleteform.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);		
		
	
		} else if ("delete".equals(cmd)) {
			
			GuestDAO dao = new GuestDAO();
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			/*			ArrayList<GuestVO> list = dao.delete();*/
			if(password.equals("")) {
				url = "./WEB-INF/views/guestbook/deleteform.jsp?result=empty";
			} else  {
				boolean flag=dao.delete(no,password);
				if(flag) {
					url = "./guest?cmd=getList";
				} else {
					url = "./WEB-INF/views/guestbook/deleteform.jsp?result=fail";
				}
				
			}
			/*request.setAttribute("list", list);*/
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);		
		
			
		} else {
			
			url = "./guest?cmd=getList";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);		
		
		}
		
	}
	

}
