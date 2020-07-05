<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/mainTop.jsp" %>
<div class="content-wrapper">
	<div class="card">
		<div class="card-body" style="width: 100%; overflow: auto;">
			<table class="table" style="width: 100%;">
				<colgroup>
					<col style="width:10%;" />
					<col style="width:50%;" />
					<col style="width:15%;" />
					<col style="width:15%;" />
					<col style="width:10%;" />		
				</colgroup>
				<thead style="width: 100%;">
				  <tr>
				    <th scope="col">번호</th>
				    <th scope="col">제목</th>
				    <th scope="col">작성자</th>
				    <th scope="col">작성일</th>
				    <th scope="col">조회수</th>
				  </tr>
				</thead>
				<tbody style="width: 100%;">
					<tr>
						<td>1</td>
						<td>제목1</td>
						<td>작성자1</td>
						<td>2020-07-05</td>
						<td>1232</td>
					</tr>
				</tbody>
			</table>
			
				<div class="divPage">
				<!-- 이전블럭으로 이동 -->
				<c:set var="firstPage" value="${pageVo.firstPage }"/>
				<c:set var="lastPage" value="${pageVo.lastPage }"/>
				<c:set var="totalPage" value="${pageVo.totalPage }"/>
				<c:set var="currentPage" value="${pageVo.currentPage }"/>
				
				<c:if test="${fistPage>1 }">
				
					<%-- <a href="<%=request.getContextPath() %>/board/list.do?currentPage=<%=firstPage-1%>&searchCondition=<%=condition %>&searchKeyword=<%=keyword %>"> --%>
					<a href="#" onclick="pageFunc(${firstPage-1})">
						<img src="<c:url value='/images/first.JPG'/>" alt="이전 블럭으로">
					</a>
				
				</c:if>
				<!-- 페이지 번호 추가 -->					
				<!-- [1][2][3][4][5][6][7][8][9][10] -->
				<c:forEach var="i" begin="${firstPage }" end="${lastPage }">
					<c:if test="${i==currentPage }">
						<span>${i }</span>
					</c:if>
					<c:if test="${totalPage>i }">
						<a href="#" onclick="pageFunc(${i})">[${i}]</a>
					</c:if>
			
					<!--  페이지 번호 끝 -->
					</c:forEach>
					<!-- 다음블럭으로 이동 -->
					<c:if test="${lastPage<totalPage }">
						<a href="#" onclick="pageFunc(${lastPage+1})">
						<img src="<c:url value='/images/last.JPG'/>" alt="다음 블럭으로">
						</a>
					</c:if>
			
			</div>
			<div class="divSearch">
			
			   	<form name="frmSearch" method="post" action='<c:url value=''/>'>
			        <select name="searchCondition">
			            <option value="title" 
			            	<c:if test="${param.searchCondition=='title'}"> 
			            		selected="selected"
			            	</c:if>
			            >제목</option>
			            <option value="content" 
			            <c:if test="${param.searchCondition=='content'}"> 
			            		selected="selected"
			            	</c:if>
			            	>내용</option>
			            <option value="name" 
			             <c:if test="${param.searchCondition=='name'}"> 
			            		selected="selected"
			            	</c:if>
			            	>작성자</option>
			        </select>   
			        <input type="text" name="searchKeyword" title="검색어 입력"
			        value="${param.searchKeyword }">   
					<input type="submit" value="검색">
			    </form>
			</div>
			
			<div class="divBtn">
			    <a href='<c:url value=''/>' >쓰기</a>
			</div>
			
		</div>
	</div>
</div>



<%@include file="inc/mainBottom.jsp" %>

<script type="text/javascript">
	$(function() {
		$("#mainBoard").addClass("active");
	});
</script>