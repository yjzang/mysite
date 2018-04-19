<%@page import="com.javaex.dao.BoardDAO"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javaex.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String result = request.getParameter("result");
	BoardDAO dao = new BoardDAO();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
	@import url(http://fonts.googleapis.com/earlyaccess/jejuhallasan.css);
	#container{font-family: 'Jeju Hallasan', serif;}
	#fail{color:red;}
</style>
<% if("fail".equals(result)){%>		
								<script type="text/javascript">alert("글쓴이만 삭제가 가능합니다.");</script> 
								<%} %>
<body>
	<div id="container">
		
		<jsp:include page= "/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page= "/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>		
					
<%
			ArrayList<BoardVO> list = (ArrayList<BoardVO>)request.getAttribute("list");

			for(BoardVO vo : list){
			
%>		
					<tr>
						<td><%=vo.getNo() %></td>
						<td><a href="/mysite/board?cmd=viewText&no=<%=vo.getNo()%>"> <%=vo.getTitle()%></a></td>
						<td><%=dao.getName(vo.getUser_no()) %></td>
						<td><%=vo.getHit() %></td>
						<td><%=vo.getDate() %></td>
						<td><a href="/mysite/board?cmd=delete&no=<%=vo.getNo()%>" class="del">삭제</a></td>
					</tr>
<%
  			 }
%>					
				
				</table>
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>				
				<div class="bottom">
					
								
								<a href="/mysite/board?cmd=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		
		
		<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
		
	</div>
</body>
</html>