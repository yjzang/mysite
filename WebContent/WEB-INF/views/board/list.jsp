
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
		alert("찾는 글이 없습니다.");
	</c:if>
	<c:if test="${param.result=='search_empty'}">
		alert("검색어를 입력하세요");
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
	
	
			
			<c:set var="p_check" value="${param.p_check==null?1:param.p_check}"> </c:set>
			<c:set var="page" value="${param.page==null?1:param.page}"> </c:set>
			<c:set var="count" value="${param.count==null?1:param.count}"> </c:set>
			<c:set var="state" value="${param.state==null?'':param.state}"> </c:set>
			<c:set var="kwd" value="${param.state==null?'':param.kwd}"> </c:set>
			
			<c:out value="${p_check}" />
			<c:out value="${page}" />
			<c:out value="${count}" />
			<c:out value="${fn:length(list)}" />
			
			<c:forEach items="${list}"	var="vo" begin="0" end="${fn:length(list)}" >
				
					<tr>
						<td>${vo.no}</td>
						<td><a href="/mysite/board?cmd=viewText&no=${vo.no}&hit=${vo.hit}&p_check=${p_check}&begin=${begin}&end=${end}&page=${page}&state=${state}&kwd=${kwd}"> ${vo.title}</a></td>
						<td>${vo.name}</td>
						<td>${vo.hit}</td>
						<td>${vo.date}</td>
						<td><a href="/mysite/board?cmd=delete&no=${vo.no}" class="del">삭제</a></td>
					</tr>
					
			</c:forEach>	
			
				</table>
				
				<div class="pager">
					<ul>
						<li><a href="/mysite/board?page=${page<6?page:page-5}&p_check=${page<6?p_check:5}&begin=${page<6?begin:(page-1)*10-9}&end=${page<6?end:(page-1)*10}" >◀</a></li>
						<li class = ${p_check==1? "selected":""}><a href="/mysite/board?p_check=1&begin=${(page-1)*10+1}&end=${(page-1)*10+10}&page=${page}">${page}</a></li>
						<li class = ${p_check==2? "selected":""}><a href="/mysite/board?p_check=2&begin=${(page-1)*10+11}&end=${(page-1)*10+20}&page=${page}">${count/10>=page+1?page+1:""}</a></li>
						<li class = ${p_check==3? "selected":""}><a href="/mysite/board?p_check=3&begin=${(page-1)*10+21}&end=${(page-1)*10+30}&page=${page}">${count/10>=page+2?page+2:""}</a></li>
						<li class = ${p_check==4? "selected":""}><a href="/mysite/board?p_check=4&begin=${(page-1)*10+31}&end=${(page-1)*10+40}&page=${page}">${count/10>=page+3?page+3:""}</a></li>
						<li class = ${p_check==5? "selected":""}><a href="/mysite/board?p_check=5&begin=${(page-1)*10+41}&end=${(page-1)*10+50}&page=${page}">${count/10>=page+4?page+4:""}</a></li>
						<li><a href="/mysite/board?page=${page+4<(count/10)?page+5:page}&p_check=${page+4<(count/10)?1:p_check}&begin=${page+4<(count/10)?(page-1)*10+51:begin}&end=${page+4<(count/10)?(page-1)*10+60:end}">▶</a></li>
						
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