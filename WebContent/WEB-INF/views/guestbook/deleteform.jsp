<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
	String result = request.getParameter("result");
%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<title>Insert title here</title>
</head>
<style type="text/css">
	@import url(http://fonts.googleapis.com/earlyaccess/jejuhallasan.css);
	#container{font-family: 'Jeju Hallasan', serif;}
	.alert{ color: red; text-align : center;}
</style>
<body>
	<div id="container">
		
	
			<jsp:include page= "/WEB-INF/views/includes/header.jsp"></jsp:include>
			<jsp:include page= "/WEB-INF/views/includes/navigation.jsp"></jsp:include>
	
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook" class="delete-form">
					
					<form method="post" action="/mysite/guest?cmd=delete">
						
					 	<input type="hidden" name="no" value=<%=no%>>
						<label>비밀번호</label>
						<input type="password" name="password" value="">
						<input type="submit" value="확인">
					</form>
						<% if("fail".equals(result)){%>		
								<P class="alert">비밀번호가 틀렸습니다. 다시입력해주세요</P>
								<%} else if("empty".equals(result)){%>
								<P class="alert">비밀번호를 입력해 주세요</P>
								<%} %>
								
					<br><br>		
					<a href="/mysite/guest">방명록 리스트</a>
				
				</div>
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>

		
	</div> <!-- /container -->

</body>
</html>
