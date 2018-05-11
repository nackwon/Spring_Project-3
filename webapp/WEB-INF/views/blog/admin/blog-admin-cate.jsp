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
					<li><a href="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/write">글작성</a></li>
				</ul>
				
		      	<table class="admin-cat">
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id=cateList>
		      		
					</tbody>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add" >
		      		<tr >
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btnAddCate" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function(){
		fetchList();
	});
	
	$("#btnAddCate").on("click",function(){
		var $name = $("[name=name]").val(),
			$desc = $("[name=desc]").val();
		
		$.ajax({
			url : "${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/cateinsert",
			type : "POST",
			data : {cateName : $name,
					description : $desc
			},
			dataType : "json",
			success : function(categoryVo){
				random(categoryVo, "up");
				$("[name=name]").val("");
				$("[name=desc]").val("");
			}
		});
	});
	
	function fetchList(){
		$.ajax({
			url : "${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/categoryList",
			type : "get",
			dataType : "json",
			success : function(list){
				for(var i=0;i<list.length;i++){
					random(list[i], "down");
				}
			},
		});
	};

	function random(categoryVo, updown){
		var str = "";
		str += "<tr id='"+categoryVo.cateNo+"'>";
		str += "		<td>"+categoryVo.cateNo+"</td>";
		str += "		<td>"+categoryVo.cateName+"</td>";
		str += "		<td>"+categoryVo.postCount+"</td>";
		str += "		<td>"+categoryVo.description+"</td>";
		str += "		<td><button style='border:0px;' id='del' data-cateno='"+categoryVo.cateNo+"'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></button></td>";
		str += "</tr>";
		
		if(updown == "down"){
			$("#cateList").append(str);	
		}else if(updown == "up"){
			$("#cateList").prepend(str);
		}else{
			console.log("오류!");
		}
	}
	
	$("#cateList").on("click", "button", function(){
		var $cateNo = $(this).data("cateno");
		console.log($cateNo);
		$.ajax({
			url :"${pageContext.request.contextPath }/${sessionScope.authUser.id}/admin/catedel",
			type : "get",
			dataType : "json",
			data : {cateNo : $cateNo},
			success : function(flag){
				if(flag == true){
					$("#"+$cateNo).remove();
				} else{
					alert("삭제할 수 없습니다.");
				}
			}
		});
	});
</script>


</html>