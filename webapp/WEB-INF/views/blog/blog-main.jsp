<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>
<body>

	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>어벤져스: 인피니티 워</h4>
					<p>
						새로운 조합을 이룬 어벤져스, <br> 역대 최강 빌런 타노스에 맞서 세계의 운명이 걸린<br> 인피니티 스톤을 향한 무한 대결이 펼쳐진다! <br> <br> 4월, 마블의 클라이맥스를 목격하라!<br>
					</p>
					<c:if test="">
						<!-- 등록된 글이 없는경우 -->
						<h4>등록된 글이 없습니다.</h4>
						<p>
						<p>
					</c:if>

				</div>

				<ul class="blog-list" >
					<c:forEach items="${cateList}" var="cateList">
						<li>
							<a href=""></a>
							<span>${cateList.regDate}</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }/${requestScope.blogVo.logoFile }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${list }" var="list">
					<li><a data-cateno="${list.cateNo}">${list.cateName}</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>

	</div>
</body>
<script type="text/javascript">

	$("#navigation").on("click", "a", function() {
		var $cateNo = $(this).data("cateno");
		console.log($cateNo);

		$.ajax({
			url : "${pageContext.request.contextPath}/${requestScope.blogVo.id}/cate",
			type : "post",
			data : {
				cateNo : $cateNo
			},
			dataType : "json",
		});
	});

</script>
</html>
