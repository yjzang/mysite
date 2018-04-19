<%@page import="com.javaex.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	
	BoardVO vo = (BoardVO)request.getAttribute("vo");
	int hit = Integer.parseInt(vo.getHit());
	hit++;
	vo.setHit(String.valueOf(hit));
	String result = request.getParameter("result");
	
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
	#content{font-family: 'Jeju Hallasan', serif; line-height : 150%;}
	
</style>


<% if("fail_mod".equals(result)){%>		
								<script type="text/javascript">alert("글쓴이만 수정이 가능합니다.");</script> 
								<%} %>

<body>
	<div id="container">

		<jsp:include page= "/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page= "/WEB-INF/views/includes/navigation.jsp"></jsp:include>

		
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td><%=vo.getTitle() %></td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
								<pre id="content"><%=vo.getContent() %></pre>
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="/mysite/board?cmd=hit&hit=<%=hit%>&no=<%=vo.getNo()%>">글목록</a>
					<a href="/mysite/board?cmd=modifyform&no=<%=vo.getNo()%>">글수정</a>
				</div>
			</div>
		</div>
	</div>
		<jsp:include page= "/WEB-INF/views/includes/footer.jsp"></jsp:include>
</body>
</html>