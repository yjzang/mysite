<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		
	
		<c:import url= "/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url= "/WEB-INF/views/includes/navigation.jsp"></c:import>
	
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook" class="delete-form">
					
					<form method="post" action="/mysite/guest?cmd=delete">
						
					 	<input type="hidden" name="no" value="${param.no}">>
						<label>비밀번호</label>
						<input type="password" name="password" value="">
						<input type="submit" value="확인">
					</form>
						<c:if test="%{param.result=='fail'}">
							<P class="alert">비밀번호가 틀렸습니다. 다시입력해주세요</P>
						</c:if>
						<c:if test="%{param.result=='empty'}">
							<P class="alert">비밀번호를 입력해 주세요</P>
						</c:if>
							
								
					<br><br>		
					<a href="/mysite/guest">방명록 리스트</a>
				
				</div>
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url= "/WEB-INF/views/includes/footer.jsp"></c:import>

		
	</div> <!-- /container -->

</body>
</html>
