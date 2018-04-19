<%@page import="com.javaex.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%

	UserVO vo =  (UserVO) session.getAttribute("authVO");
%>
<style type="text/css">

	#goMain{text-decoration:none;}
    a:visited {color: #79B30B;}
    
</style>
    
		<div id="header">
			<h1><a href = "/mysite/main" id="goMain">나의 사이트</a></h1>
			<ul>
<% 			if(vo==null){
	
%>		
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?cmd=loginform">로그인</a></li>
				<li><a href="/mysite/user?cmd=joinform">회원가입</a></li>
				
				
<% 			} else{
%>		
				<!-- 로그인 후 -->
				
				<li><a href="/mysite/user?cmd=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?cmd=logout">로그아웃</a></li> 
				<li> <%=vo.getName() %>님 반가워요</li>
				
<% 			}
%>		
			</ul>
		</div> <!-- /header -->