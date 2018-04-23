
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>


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
	#submit{font-family: 'Jeju Hallasan', serif;}
</style>
<script type="text/javascript">

	<c:if test="${param.result=='fail'}">
		alert("글쓴이만 삭제가 가능합니다.");
	</c:if>
	<c:if test="${param.result=='succ'}">
		alert("해당 글이 삭제되었습니다.");
	</c:if>
	<c:if test="${param.result=='search_fail'}">
		alert("찾으시는 글이 없습니다.");
	</c:if>
</script>							 
			
			
<body>

	<div id="container">
		
		<c:import url= "/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url= "/WEB-INF/views/includes/navigation.jsp"></c:import>

			
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board?cmd=search" method="post">
					<input type="text" id="kwd" name="kwd" value="" placeholder="제목으로 검색하세요">
					<input id="submit" type="submit" value="찾기">
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
			
			<c:set var="num" value="${param.num==null?0:param.num}"> </c:set>
			<c:set var="page" value="${param.page==null?1:param.page}"> </c:set>
			<c:set var="page_idx" value="${param.page_idx==null?1:param.page_idx}"> </c:set>
			<c:out value="${num}" />
			<c:out value="${page}" />
			<c:out value="${page_idx}" />
			<c:out value="${fn:length(list)}" />
			
			<c:forEach items="${list}"	var="vo" begin="${num}" end="${list_size<(num+9)? list_size:(num+9)}" >
				
					<tr>
						<td>${vo.no}</td>
						<td><a href="/mysite/board?cmd=viewText&no=${vo.no}&hit=${vo.hit}"> ${vo.title}</a></td>
						<td>${vo.name}</td>
						<td>${vo.hit}</td>
						<td>${vo.date}</td>
						<td><a href="/mysite/board?cmd=delete&no=${vo.no}" class="del">삭제</a></td>
					</tr>
					
			</c:forEach>	
			
				</table>
				
				<div class="pager">
					<ul>
						<li><a href="/mysite/board?page_idx=${page_idx<6?page_idx:page_idx-5}&page=${page_idx<6?page:5}&num=${page_idx<6?num:10*(page_idx-1-1)}" >◀</a></li>
						<li class = ${(page%5)==1? "selected":""}><a href="/mysite/board?page=1&num=${10*(page_idx-1)}&page_idx=${page_idx}">${page_idx}</a></li>
						<li class = ${(page%5)==2? "selected":""}><a href="/mysite/board?page=2&num=${10*(page_idx-1+1)}&page_idx=${page_idx}">${fn:length(list)/10>=page_idx+1?page_idx+1:""}</a></li>
						<li class = ${(page%5)==3? "selected":""}><a href="/mysite/board?page=3&num=${10*(page_idx-1+2)}&page_idx=${page_idx}">${fn:length(list)/10>=page_idx+2?page_idx+2:""}</a></li>
						<li class = ${(page%5)==4? "selected":""}><a href="/mysite/board?page=4}&num=${10*(page_idx-1+3)}&page_idx=${page_idx}">${fn:length(list)/10>=page_idx+3?page_idx+3:""}</a></li>
						<li class = ${(page%5)==0? "selected":""}><a href="/mysite/board?page=5&num=${10*(page_idx-1+4)}&page_idx=${page_idx}">${fn:length(list)/10>=page_idx+4?page_idx+4:""}</a></li>
						<li><a href="/mysite/board?page_idx=${page_idx+4<(fn:length(list)/10)?page_idx+5:page_idx}&page=${page_idx+4<(fn:length(list)/10)?1:page}&num=${page_idx+4<(fn:length(list)/10)?10*(page_idx-1+5):num}">▶</a></li>
					</ul>
				</div>				
				<div class="bottom">
					
								
								<a href="/mysite/board?cmd=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		
		
		<c:import url= "/WEB-INF/views/includes/footer.jsp"></c:import>
		
		
	</div>
</body>
</html>