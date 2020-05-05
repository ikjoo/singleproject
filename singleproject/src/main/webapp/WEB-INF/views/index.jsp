<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>싱글프로젝트</title>


<link rel="stylesheet"
	href="<c:url value='/resources/assets/vendors/mdi/css/materialdesignicons.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/assets/vendors/css/vendor.bundle.base.css'/>">

<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/style.css'/>">
<!-- End layout styles -->
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.4.1.min.js'/>"></script>
<script type="text/javascript">
	$(function() {
		$("tbody a").click(function() {
			alert($(this).find("input[class=na]").val());
		});
	});
</script>
</head>
<body>
	<div class="content-wrapper">
		<div class="card">
			<div class="card-body">
				<div>
					<input type="button" value="상위폴더로"
						class="btn btn-gradient-light btn-rounded btn-sm">
				</div>

				<div>
					<table class="table  text-center table-bordered">
						<colgroup>
							<col style="width: 5%;">
							<col style="width: 80%;">
							<col style="width: 15%;">
						</colgroup>
						<thead class="table-danger">
							<tr>
								<th><input type="checkbox"></th>
								<th>파일명</th>
								<th>파일크기</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="map" items="${list }">
							<tr>
							<td><input type="checkbox"></td>
							<td><a href="#">
							<c:set value="${map['fileName'] }"  var="name"></c:set>
							
							<c:if test="${ fn:indexOf(name,'?')==-1}">
							<img alt="" src="<c:url value='/resources/images/file.gif'/>">
							<input type="text" value="${name }" class="na">
							${map['fileName'] }
							</c:if>
							<c:if test="${ fn:indexOf(name,'?')!=-1}">
							<input type="text" value='${fn:replace(name,"?","") }' class="na">
							${fn:replace(name,"?","") }
							</c:if>
							
							</a></td>
							<td>

							<fmt:formatNumber value="${map['size']}" pattern="#,###"/> KB


							</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>