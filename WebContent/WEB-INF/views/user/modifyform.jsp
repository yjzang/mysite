<%@page import="com.javaex.dao.UserDAO"%>
<%@page import="com.javaex.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	UserVO authVO = (UserVO) session.getAttribute("authVO");
	UserVO vo = new UserVO();
	UserDAO dao = new UserDAO();
	vo = dao.getUserAll(authVO.getNo());


%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<title>회원정보 수정</title>
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
				<div id="user">
	
					<form id="join-form" name="modifyform" method="post" action="/mysite/user?cmd=modify">

						<input id="no" name="no" type="hidden" value=<%=vo.getNo()%> />
						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value=<%=vo.getName()%> />
						
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value=<%=vo.getEmail()%> readonly="readonly"/>
						<strong></strong>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="" />
						
						<fieldset>
							<legend>성별</legend>
							
							<label>여</label> <input type="radio" name="gender" value="female" <%=vo.getGender().equals("female")?"checked":""%> >
							<label>남</label> <input type="radio" name="gender" value="male" <%=vo.getGender().equals("male")?"checked":""%>>
							
						</fieldset>
						
						<input type="submit" value="수정완료">
						
					</form>
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
			<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
	</div> <!-- /container -->

</body>
</html>
