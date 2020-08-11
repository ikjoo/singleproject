<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/mainTop.jsp" %>

<div class="content-wrapper">
	<div class="card">
		<div class="card-body">
		<div class="divForm">
		<div class="firstDiv">
			<span class="sp1">제목</span> <span>${vo.reboardTitle}</span>
		</div>
		<div>
			<span class="sp1">작성자</span> <span>${vo.userid}</span>
		</div>
		<div>
			<span class="sp1">등록일</span> <span>${vo.reboardReg}</span>
		</div>
		<div>
			<span class="sp1">조회수</span> <span>${vo.readcount}</span>
		</div>
		<div>
			<span class="sp1">첨부파일</span> 
			<span>
			<c:if test="${!empty vo.filename }">
			<img src="<c:url value='/resources/images/file.gif'/>">
			 <a href="<c:url value=''/>">
			 ${vo.filename }
			 </a>
			  다운 : ${vo.downcount }
			</c:if>
			</span>
		</div>
		<hr>
		<div class="lastDiv">		
			<p class="content">${vo.reboardContent }</p>
		</div>
		<hr>
		<div class="text-center">
			<a href
			='<c:url value="/reBoard/edit.do?no=${param.reoboardNo}"/>'>수정</a> |
        	<a href
        	='<c:url value="/reBoard/delete.do?no=${param.reboardNo }&groupNo=${vo.groupno }&step=${vo.step }"/>'>삭제</a> |
        	<a href
			='<c:url value="/reBoard/reply.do?no=${param.reboardNo}"/>'>답변</a> | 
        	<a href='<c:url value="/main"/>'>
        		목록</a>			
		</div>
	</div>
            

		
		</div>
	</div>
</div>

<%@ include file="../inc/mainBottom.jsp" %>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<style type="text/css">


</style>


<script type="text/javascript">
$(function() {
	$("#mainBoard").addClass("active");
	


	
});


</script>