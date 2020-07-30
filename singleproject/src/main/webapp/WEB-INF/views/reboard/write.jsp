<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/mainTop.jsp" %>

<div class="content-wrapper">
	<div class="card">
		<div class="card-body">

            <form name="write" id="fileform" method="post" enctype="multipart/form-data" action="<c:url value='/write'/>">
				<fieldset>
					<div id="aa">
						<label for="title">제목</label>
						<input type="text" name="reboardTitle" id="title" class="form-control form-control-fw">
					</div>
					<div id="divdiv">
						<label for="bo_content">내용</label>
						<textarea id="bo_content" name="reboardContent"></textarea>
					</div>
					<div id="divdiv">
						<label for="upfile">첨부파일</label>
						<input type="file" name="upfile" id="upfile" class="form-control form-control-fw">
						
					</div>
					<div id="lastdiv">
						<button type="submit" class="btn btn-gradient-danger btn-rounded btn-fw">작성완료</button>
						<button type="button" class="btn btn-gradient-danger btn-rounded btn-fw" id="over">오버레이</button>
					</div>
				</fieldset>
			</form>

		
		</div>
	</div>
</div>
<div class="body-overlay" id="overray">
<div id="progressdiv">
	<!-- Ajax Progress Status -->
<div id="viewLoading">
</div>
	<hr>
	<button type="button" class="btn btn-gradient-danger btn-rounded btn-fw" id="cancle">취소</button>
</div>

</div>
<%@ include file="../inc/mainBottom.jsp" %>
<script type="text/javascript" src="<c:url value='/resources/ckeditor/ckeditor.js'/>"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<style type="text/css">
#overray{
position: fixed;
        bottom: 0;
        left: 0;
        right: 0;
        overflow: hidden;
        width: 100%;
        height: 0;
        color: #fff;
        background: rgba(0, 0, 0, 0.6);
        -webkit-transition: .6s ease;
        transition: .6s ease;
        z-index: 1000;
}
#progressdiv{
        position: absolute;
        top: 50%;
        left: 50%;
        -webkit-transform: translate(-50%, -50%);
        -ms-transform: translate(-50%, -50%);
        transform: translate(-50%, -50%);
        text-align: center;
}
#fileform{
	width: 100%;
	margin: 0 auto;
}
#fileform #aa,#divdiv,#lastdiv{
	clear: both;
		border-bottom: 1px solid #eee;
		padding: 5px 0;
		margin: 0;
		overflow: auto;
}
#fileform label{
		text-align: right;
		padding: 3px 15px 0 0;
		clear: left;		
		font-weight: bold;
}
#bo_content{
	overflow: hidden;
	width: 10%;
}
#fileform fieldset{
	width: 90%;
		padding: 0;
		margin: 0 auto;
		border: 0;
		border-bottom: 2px solid #e2e2e2;
		}
#title,#upfile{
	width: 90%;
}
#lastdiv{
	text-align: center;
}
div#viewLoading {text-align:center;padding-top:120px;filter:alpha(opacity=60);opacity: alpha*0.6;background-color:#222222;color:#bcbfc4;}
div#viewLoading div.progressTitle{text-align:left;border:2px solid #111111;border-bottom:1px solid #111111;padding:15px 0 15px 0;width:99.2%;}
div#viewLoading div.progressTitle span{padding-left:3px;padding-bottom:5px;}
div#viewLoading div.progressWrapper{border:1px solid #111111;width:99.2%;text-align:center;}
div#viewLoading div.progressWrapper div.progresspercent{background-color:#1a1a1a;height:40px;border:1px solid #000000;}
div#viewLoading div.progressWrapper div.progresspercent span.percentwrapper{line-height:38px;}
div#viewLoading div.progressWrapper div.progresspercent span.percentwrapper span.pgbar{display:block;float:left;background-color:#fecf23;width:90%;}
div#viewLoading div.progressWrapper div.progresspercent span.percentwrapper span.pgpercent{position:absolute;left:30%;right:30%;color:#c0c0c0;}
div#viewLoading div.progressWrapper div.progresspercent span.percentwrapper span.pgpercent strong{font-weight:bold;}
div#viewLoading div.progressfilereadsize{margin:0 0 5px 0;height:40px;border:1px solid #111111;}
div#viewLoading div.progressfilereadsize span{line-height:40px;}
div#viewLoading div.progressfilereadsize span.divider strong{font-weight:400;}
div#viewLoading div.progressSpeed{margin:0 0 5px 0;height:40px;border:1px solid #111111;text-align:center;}
div#viewLoading div.progressSpeed span.kbps{line-height:40px;}
div#viewLoading div.progressSpeed span.kbps strong{font-weight:400;}
.pgbarbgcolor{background-color:#fecf23;}

</style>


<script type="text/javascript">
	$(function() {
		$("#mainBoard").addClass("active");
		
		CKEDITOR.replace('bo_content',{height: '300', width: '99%',
			filebrowserUploadUrl: "<c:url value='/ckimageup'/>"
		});
		
	
		$("#over").click(function() {
			$("#overray").css("display","block");
			$("#overray").css("height","100%");
		});
		
		$("#cancle").click(function() {
			$("#overray").css("display","block");
			$("#overray").css("height","0");
		});
		
		//  --------------------------------------------------------------------------------------------- 
		var fileUploadBtnElem = $("span.sendData");
		var fileUploadFrm = $("#fileform");	
		

		//ajax Progress image view Elem
		var viewLoadingImgElem = $("div#viewLoading");
		//$(viewLoadingImgElem).hide();	//초기로딩시에는 이미지를 숨긴다.
		
		var intervalID = 0;
		//ajax 요청시작과 완료시의 프로그레스 이미지 element의 동작
		$(viewLoadingImgElem).ajaxStart(function(){
			// 로딩이미지의 위치 및 크기조절	
			$(viewLoadingImgElem).css('position', 'absolute');
			$(viewLoadingImgElem).css('left', $("body").offset().left);
			$(viewLoadingImgElem).css('top', $("body").offset().top);
			$(viewLoadingImgElem).css('width', $(document).width());
			$(viewLoadingImgElem).css('height', $(document).height());
					
			intervalID = setInterval(function(){			
				getFileUploadProgress();	//ajax요청중에 파일업로드 상태를 주기적으로 요청한다.	
			},50);
			$(this).fadeIn(250);
		}).ajaxStop(function(){		
			clearInterval(intervalID); //Stop updating		
			$(this).fadeOut(250);
		});					

		$(fileUploadBtnElem).click(function(){
			$(fileUploadFrm).ajaxSubmit({
				url : '',
				type : 'POST',
				data : $(fileUploadFrm).serialize(),
				success : function(data){
					alert("전송 완료 되었습니다.");				
					clearInterval(intervalID); //Stop updating				
				},error : function(){
					alert("전송 실패 했습니다.");
				}
			});	
		});
	    
		//파일업로드 상태를 주기적으로 확인해서 가져온다.
		var getFileUploadProgress = function(){
			$.ajax({
				url : '',
				success : function(data){
					var jsonData = eval('('+ data +')');

					$(viewLoadingImgElem).html(										
							"<div class='progressTitle'>" +
							"	<span><strong>업로드 진행상태</strong></span>" +		
							"</div>" +
							"<div class='progressWrapper'>" +
							"	<div class='progresspercent'>" +
							"		<span class='percentwrapper'>"+
							"			<span class='pgbar'>&nbsp;</span>"+
							"			<span class='pgpercent'><strong>"+ jsonData.percent+"%</strong></span>" +				
							"		</span>" +
							"	</div>"+
							"	<div class='progressfilereadsize'>"+
							"		<span class='readsize'>" + jsonData.bytesread + "<strong> bytes</strong></span>" +
							"		<span class='divider'><strong>/</strong></span>" +
							"		<span class='filelength'>" + jsonData.contentlength + "<strong> bytes</strong></span>" +
							"	</div>" +
							"	<div class='progressSpeed'>" +
							"		<span class='kbps'>" + jsonData.kbps + "<strong> kbps</strong></span>" +
							"	</div>" +								
							"</div>"	);										
					$(viewLoadingImgElem).find("div.progresspercent span.pgbar").width(jsonData.percent+"%").addClass("pgbarbgcolor");				
				}
			});
		};
		
		    
		 /* --------------------------------------------------------------------------------------------- */
		
	});
</script>