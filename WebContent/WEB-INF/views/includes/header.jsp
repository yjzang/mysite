<%@page import="com.javaex.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">

	#goMain{text-decoration:none;}
    a:visited {color: #79B30B;}
    
</style>
    
		<div id="header">
			<h1><a href = "/mysite/main" id="goMain">영주네</a></h1>
			<ul>
			<c:if test="${authVO==null}">
				<!-- 로그인 전 -->
				<li><a href="/mysite/user?cmd=loginform">로그인</a></li>
				<li><a href="/mysite/user?cmd=joinform">회원가입</a></li>
			</c:if>
			
			<c:if test="${authVO!=null}">
				<!-- 로그인 후 -->
				
				<li><a href="/mysite/user?cmd=modifyform">회원정보수정</a></li>
				<li><a href="/mysite/user?cmd=logout">로그아웃</a></li> 
				<li> ${authVO.name}님 반가워요</li>
			</c:if>

			</ul>
		</div> <!-- /header -->