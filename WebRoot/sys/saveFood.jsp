<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 包含公共的JSP代码片段 -->
<title>无线点餐平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/style/css/index_1.css" />
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13" src="${pageContext.request.contextPath}/sys/style/images/title_arrow.gif" /> 添加新菜品
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<!-- 表单内容 -->
		<form id="myForm" action="${pageContext.request.contextPath }/FoodServlet?method=add" method="post" enctype="multipart/form-data">
			<!-- <input type="hidden" name="method" value="add" /> -->
			<!-- 本段标题（分段标题） -->
			<div class="ItemBlock_Title">
				<img width="4" height="7" border="0" src="${pageContext.request.contextPath}/sys/style/images/item_point.gif"> 菜品信息&nbsp;
			</div>
			<!-- 本段表单字段 -->
			<div class="ItemBlockBorder">
				<div class="ItemBlock">
					<div class="ItemBlock2">
						<table cellpadding="0" cellspacing="0" class="mainForm">
							<tr>
								<td width="80px">菜系</td>
								<td><select name="dishes" style="width: 80px">
										<c:choose>
											<c:when test="${not empty requestScope.listDishes }">
												<c:forEach var="dishe" items="${requestScope.listDishes }" varStatus="vs">
													<option value="${dishe.id }">${dishe.dishesName }</option>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<option selected="selected">还没有菜系，请去添加</option>
											</c:otherwise>
										</c:choose>
								</select> *</td>
								<td></td>
							</tr>
							<tr>
								<td width="80px">菜名</td>
								<td><input type="text" name="foodName" class="InputStyle" /> *</td>
								<td></td>
							</tr>
							<tr>
								<td>价格</td>
								<td><input id="price" type="text" name="price" class="InputStyle" /> *</td>
								<td><span id="s1" style="color: red;"></span></td>
							</tr>
							<tr>
								<td>会员价格</td>
								<td><input id="memberPrice" type="text" name="memberPrice" class="InputStyle" /> *</td>
								<td><span id="s2" style="color: red;"></span></td>
							</tr>
							<tr>
								<td>简介</td>
								<td><textarea name="intro" class="TextareaStyle"></textarea></td>
								<td></td>
							</tr>
							<tr>
								<td width="80px">菜品图片</td>
								<td><input type="file" name="foodImage" /> *</td>
								<td></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<!-- 表单操作 -->
			<div id="InputDetailBar">
				<input id="add" type="button" value="添加" class="FunctionButtonInput"> <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
			</div>
			<script type="text/javascript">
				document.getElementById("add").onclick = function() {
					var priceElement = document.getElementById("price");
					var priceValue = priceElement.value
					var memberPriceElement = document
							.getElementById("memberPrice");
					var memberPriceValue = memberPriceElement.value;

					//alert(flage);
					//alert(isNumber(value))
					if (isNumber(priceValue) && isNumber(memberPriceValue)) {
						document.getElementById("myForm").submit();
					}
				};
				document.getElementById("price").onkeyup = function() {

					if (!isNumber(this.value)) {
						document.getElementById("s1").innerHTML = "请输入数字";
					} else {
						document.getElementById("s1").innerHTML = "";
					}
				}
				document.getElementById("memberPrice").onkeyup = function() {

					if (!isNumber(this.value)) {
						document.getElementById("s2").innerHTML = "请输入数字";
					} else {
						document.getElementById("s2").innerHTML = "";
					}
				}
				function isNumber(value) {
					var flag = false;
					for (var i = 0; i < value.length; i++) {
						var s = value.charAt(i);
						if (s == 1 || s == 2 || s == 3 || s == 4 || s == 5
								|| s == 6 || s == 7 || s == 8 || s == 9
								|| s == 0) {
							flag = true;
						}
					}
					return flag;
				}
			</script>
		</form>
	</div>
</body>
</html>
