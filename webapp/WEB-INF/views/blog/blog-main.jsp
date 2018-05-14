<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE hstml>
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
					<c:if test="${list.get(0).postCount == 0 }">
						<h4>글이 없습니다.</h4>
					</c:if>
				</div>

				<table>
					<c:if test="${sessionScope.authUser != null}">
						<thead>
							<tr>
								<td><input id="cmtid" type="text" value="${sessionScope.authUser.id }" data-userno="${sessionScope.authUser.userNo }" size="4"></td>
								<td colspan="2"><input id="cmtcontent" type="text" placeholder="댓글을 입력하세요" size="50"></td>
								<td><button id="cmtbtn">저장</button></td>
							</tr>
						</thead>
					</c:if>
					<tbody id="cmtbody">

					</tbody>
				</table>

				<ul class="blog-list">

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
					<li><button style="border: 0px; background-color: white;" data-cateno="${list.cateNo}">${list.cateName}</button></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>

	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		categoryPrint();
	});

	// post List
	function categoryPrint() {
		$("#navigation").on("click", "button", function() {
			$("[class=blog-list]").children("li").remove();
			var $cateNo = $(this).data("cateno");
			$.ajax({
				url : "${pageContext.request.contextPath}/${requestScope.blogVo.id}/postList",
				type : "post",
				dataType : "json",
				data : {
					cateNo : $cateNo
				},
				success : function(cateList) {
					console.log(cateList.length);
					for (var i = 0; i < cateList.length; i++) {
						category(cateList[i]);
					}
				}
			});
		});
		postPrint();
	}

	// post 
	function postPrint() {
		$("[class=blog-list]").on("click", "button", function() {
			$("[class=blog-content]").children("h4").remove();
			$("[class=blog-content]").children("p").remove();
			var $postNo = $(this).data("postno");
			$.ajax({
				url : "${pageContext.request.contextPath}/${requestScope.blogVo.id}/post",
				type : "post",
				data : {
					postNo : $postNo
				},
				dataType : "json",
				success : function(blogpostVo) {
					post(blogpostVo);
				}
			});
			console.log("변경되는 포스트 넘버" + $postNo);
			commentList($postNo);
		});
	}

	// comment print
	function commentList($postNo) {
		$("#cmtbody").children("tr").remove();
		$.ajax({
			url : "${pageContext.request.contextPath}/${requestScope.blogVo.id}/commentList",
			type : "get",
			data : {
				postNo : $postNo
			},
			dataType : "json",
			success : function(list) {
				console.log("list 가져옴");
				for (var i = 0; i < list.length; i++) {
					comment(list[i], "down");
				}
			}
		});
	}

	// comment insert 
	$("#cmtbtn").on("click", function() {
		var $userNo = $("#cmtid").data("userno"),
			$cmtcontent = $("#cmtcontent").val(),
			$postNo = $("#postNo").data("postno");
		$.ajax({
			url : "${pageContext.request.contextPath}/${requestScope.blogVo.id}/cmtinsert",
			type : "post",
			data : {
				userNo : $userNo,
				cmtContent : $cmtcontent,
				postNo : $postNo
			},
			dataType : "json",
			success : function(cmtVo) {
				comment(cmtVo, "up");
				$("#cmtcontent").val("");
			}
		});
	});

	// category list
	function category(cateVo) {
		var str = "";
		str += "<li>";
		str += "	<button style='border:0px; background-color:white;' data-postno='" + cateVo.postNo + "'>" + cateVo.postTitle + "</button>";
		str += "	<span>" + cateVo.regDate + "</span>";
		str += "</li>";

		$("[class=blog-list]").append(str);
	}

	// post list
	function post(postVo) {
		var str = "";
		str += "<h4 id='postNo' data-postno='" + postVo.postNo + "'>" + postVo.postTitle + "</h4>";
		str += "<p>" + postVo.postContent + "</p>";
		$("[class=blog-content]").append(str);
	}

	// comment list
	function comment(commentVo, updown) {
		var str = "";
		str += "<tr>";
		str += "	<td>" + commentVo.userName + "</td>";
		str += "	<td>" + commentVo.cmtContent + "</td>";
		str += "	<td>" + commentVo.regDate + "</td>";
		str += "</tr>";

		if (updown == "up") {
			$("#cmtbody").prepend(str);
		} else if (updown == "down") {
			$("#cmtbody").append(str);
		} else {
			console.log("오류");
		}
	}
</script>
</html>
