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
	<div class="center-content">

		<!-- 메인해더 -->
		<a href="${pageContext.request.contextPath}/"> <img class="logo" src="${pageContext.request.contextPath}/assets/images/logo.jpg">
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
				<li><a href="">내블로그</a></li>
			</c:if>
		</ul>

		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input type="text" name="userName" />
			<div id="namemsg"></div><br>
			<label class="block-label" for="id">아이디</label> 
			<input type="text" name="id" value="" />
			
			<input id="btn-checkid" type="button" value="id 중복체크">
			<input type="hidden" id="isIdCheck" value="false">
			<p id="checkid-msg" class="form-error">&nbsp;</p>
			<label class="block-label" for="password">패스워드</label> 
			<input type="password" name="password" value="" />
			<div id="passwordmsg"></div><br>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y"> 
				<label class="l-float">서비스 약관에 동의합니다.</label>
				<div id="agreemsg"></div><br>
			</fieldset>

			<input type="submit" id="submit" value="가입하기">
		</form>
	</div>
</body>
<script type="text/javascript">
	$("#submit").on("click", function() {
		if(finalCheck()){
			return true;
		}
	});

	function finalCheck() {
		var $userName = $("[name=userName]").val(),
			$id = $("[name=id]").val(),
			$password = $("[name=password]").val(),
			$agree = $("#agree-prov").is(":checked"),
			$isIdCheck = $("#isIdCheck").val();
			
		console.log($userName);
		console.log($id);
		console.log($password);

		if (!$userName) {
			$("#namemsg").html("필수 사항 입니다.");
			return false;
		}
		if (!$id) {
			$("#checkid-msg").html("필수 사항 입니다.");
			return false;
		}
		if (!$password) {
			$("#passwordmsg").html("필수 사항 입니다.");
			return false;
		}
		if($agree != true){
			$("#agreemsg").html("약관에 동의해 주세요.");
			return false;
		}
		if($isIdCheck == "false"){
			$("checkid-msg").html("아이디 중복 체크를 해주세요.");
			return false;
		}
		return true;
	}

	$("#btn-checkid").on("click", function() {
		var $id = $("[name=id]").val();
		console.log($id);

		$.ajax({
			url : "${pageContext.request.contextPath}/user/idcheck",
			type : "get",
			data : {
				id : $id
			},
			dataType : "json",
			success : function(flag) {
				if (flag == true) {
					$("#isIdCheck").val("true");
				} else {
					$("#checkid-msg").html("사용할 수 없는 아이디 입니다.");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			} // 실패 시 실행될 함수
		});
	});
</script>

</html>

