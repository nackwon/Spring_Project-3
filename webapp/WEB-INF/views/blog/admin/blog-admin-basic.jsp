<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected"><a href="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/write">글작성</a></li>
				</ul>
				
				<form enctype="multipart/form-data" action="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/update" method="post">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="blogTitle" value="${requestScope.blogVo.blogTitle}"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${pageContext.request.contextPath}/upload/${requestScope.blogVo.logoFile}"></td>   
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s">
			      			<input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	</div>
</body>

</html>