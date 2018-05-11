<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		
		<!-- 메인해더 -->
	 	<a href="${pageContext.request.contextPath}/">
			<img class="logo" src="${pageContext.request.contextPath}/assets/images/logo.jpg">
		</a>
		<ul class="menu">
			<c:if test="${sessionScope.authUser == null }">
				<!-- 로그인 전 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			</c:if>
			<c:if test="${sessionScope.authUser != null }">
				<!-- 로그인 후 메뉴 -->
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/${sessionScope.authUser.id}">내블로그</a></li> 
			</c:if>
 		</ul>
		
		<form class="login-form" method="post" action="${pageContext.request.contextPath}/user/login">
      		<label>아이디</label> 
      		<input type="text" name="id">
      		
      		<label>패스워드</label> 
      		<input type="password" name="password">
      		
      		<c:if test="${param.result != null }">
	      		<p class="form-error">
					로그인 실패<br>
					아이디/패스워드를 확인해 주세요
				</p>
      		</c:if>
      		
      		<input type="submit" value="로그인">
		</form>
		
	</div>
</body>

</html>