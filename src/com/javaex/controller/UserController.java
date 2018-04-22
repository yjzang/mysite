package com.javaex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDAO;
import com.javaex.vo.UserVO;

public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String url = "";
		String cmd = request.getParameter("cmd");
		cmd = cmd == null ? "" : cmd;

		if (cmd.equals("joinform")) {
			url = "/WEB-INF/views/user/joinform.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);

		} else if (cmd.equals("join")) {
			System.out.println("CMD= JOIN");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVO vo = new UserVO();
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);
			UserDAO dao = new UserDAO();
			dao.insert(vo);

			url = "/WEB-INF/views/user/joinsuccess.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);

		} else if (cmd.equals("loginform")) {
			
			url = "/WEB-INF/views/user/loginform.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);

		} else if (cmd.equals("logout")) {

			HttpSession session = request.getSession();
			session.removeAttribute("authVO");
			session.invalidate();
			// invalidate는 모든 세션 끊기
			// remove 는 해당 이름의 세션만 삭제
			url = "/mysite/main";
			resp.sendRedirect(url);

		} else if (cmd.equals("login")) {

			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserDAO dao = new UserDAO();
			UserVO vo = dao.getUser(email, password);

			if (vo == null) {
				System.out.println("로그인 실패");
				resp.sendRedirect("/mysite/user?cmd=loginform&result=fail");

			} else {
				System.out.println("로그인 성공");
				HttpSession session = request.getSession();
				System.out.println(vo.getName());
				session.setAttribute("authVO", vo);

				resp.sendRedirect("/mysite/main");

			}

		} else if (cmd.equals("modifyform")) {

			url = "/WEB-INF/views/user/modifyform.jsp";
			HttpSession session = request.getSession();
			UserVO authVO = (UserVO) session.getAttribute("authVO");
			UserVO vo = new UserVO();
			UserDAO dao = new UserDAO();
			vo = dao.getUserAll(authVO.getNo());
			request.setAttribute("vo", vo);
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);

		} else if (cmd.equals("modify")) {

			String no = request.getParameter("no");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVO vo = new UserVO();
			vo.setNo(no);
			vo.setName(name);
			vo.setEmail(email);
			vo.setPassword(password);
			vo.setGender(gender);

			UserDAO dao = new UserDAO();
			dao.update(vo);

			HttpSession session = request.getSession();
			UserVO authVO = (UserVO) session.getAttribute("authVO");
			authVO.setName(name);
		//	session.setAttribute("authVO", authVO);  이미 authVO.setName(name) 에서  세션의 name값이 바뀌었기 때문에 안 해줘도 된다.
			
			resp.sendRedirect("/mysite/main");

		} else {
			url = "./main";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, resp);
		}

	}

}
