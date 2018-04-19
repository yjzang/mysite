<%@page import="com.javaex.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");
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
	#submit{font-family: 'Jeju Hallasan', serif;}
</style>
<body>
	<div id="container">
		
		<jsp:include page= "/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page= "/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		
		
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="/mysite/board?cmd=modify">
					<input type="hidden" name="no" value=<%=vo.getNo() %> >
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글수정</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=<%=vo.getTitle() %>></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="content"><%=vo.getContent()%></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="/mysite/board">취소</a>
						<input id="submit" type="submit" value="수정">
					</div>
				</form>				
			</div>
		</div>
	</div>	
		<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>
</body>
</html>