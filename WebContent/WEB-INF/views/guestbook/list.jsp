<%@page import="java.util.ArrayList"%>
<%@page import="com.javaex.vo.GuestVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<title>방명록</title>
</head>
<style type="text/css">
	@import url(http://fonts.googleapis.com/earlyaccess/jejuhallasan.css);
	#container{font-family: 'Jeju Hallasan', serif;}
</style>
<body>

	<div id="container">
		
	<jsp:include page= "/WEB-INF/views/includes/header.jsp"></jsp:include>
	<jsp:include page= "/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="/mysite/guest?cmd=add" method="post">
						
						<table>
							<tr>
								<td>이름</td><td><input type="text" name="name" /></td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content"></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					<ul>
						<li>
<%
						ArrayList<GuestVO> list = (ArrayList<GuestVO>)request.getAttribute("list");
				
						for(GuestVO vo : list){
			
%>
							<table>
								<tr>
									<td>[<%=vo.getNo() %>]</td>
									<td><%=vo.getName() %></td>
									<td><%=vo.getDate() %></td>
									<td><a href="/mysite/guest?cmd=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
								</tr>
								<tr>
									<td colspan=4>
									<%=vo.getContent()%>
									</td>
								</tr>
							</table>
							<br>
<%
  		 }
%>
						</li>
					</ul>
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
	<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div> <!-- /container -->

</body>
</html>