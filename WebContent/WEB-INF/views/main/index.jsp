<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css">
	<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
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
				<div id="site-introduction">
					<img style="width: 300px" id="profile" src="/mysite/assets/images/profile2.png">
					<h2>  <br> 환영합니다 <br/> </h2>
					<p>
						
						<br>
						
						<br>
						<br>
						<a href="/mysite/guest">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>
		
	</div>
</body>
</html>