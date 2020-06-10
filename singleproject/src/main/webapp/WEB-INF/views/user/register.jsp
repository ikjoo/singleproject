<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>싱글프로젝트 - 회원가입</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/vendors/mdi/css/materialdesignicons.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/assets/vendors/css/vendor.bundle.base.css'/>">
    <script type="text/javascript" src ="<c:url value = '/resources/js/jquery-3.4.1.min.js'/>"></script>
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/style.css'/>" >
    <!-- End layout styles -->
    <link rel="shortcut icon" href="<c:url value='/resources/assets/images/favicon.png'/>" >
    <script type="text/javascript">
    $(function() {
    	$("#email2").change(function(){
    		if($(this).val()=='etc'){
    			$("#email3").val("");
    			$("#email3").css("visibility","visible");
    			$("#email3").focus();				
    		}else{
    			$("#email3").css("visibility","hidden");
    		}
    	});
    	
    	$("#email3Chk").click(function() {
    		$(this).val("메일 전송 중...");
    		$(this).attr("disabled",true);
    		
    		$.ajax({
    			url:"<c:url value=''/>",
    			type:"post",
    			data:
    				{
    					email1 : $("#email1").val(),
    					email2: $("#email2").val(),
    					email3:$("#email3").val()
    				
    				}
    			,
    			success:function(res){
    				 if(res==1){
    					
    				}else if(res==2){
    					
    				}else{
    					
    				} 
    				
    			},
    			error:function(xhr,status,error){
    				alert("Error : "+status+", "+error);
    			}
    		});
		});
	})
    </script>
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
          <div class="row flex-grow">
            <div class="col-lg-6 mx-auto">
              <div class="auth-form-light text-left p-5">
              
                <h4>회원가입</h4>

                <form class="pt-3">
                  <div class="form-group">
                    <input type="text" class="form-control form-control-lg" id="exampleInputUsername1" placeholder="아이디" name="userId" style="width: 50%; display: inline;">
                    <span id="idchk" style="visibility: hidden;">안보이지?</span>
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="비밀번호" name="userPw" style="width: 50%;">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="비밀번호확인" name="userPw2" style="width: 50%;">
                  </div>
                  <div class="form-group">	
					<input type="text" name="email1" id = "email1" 
						placeholder="이메일" required="required"  class="form-control form-control-sm" style="width: 20%; display: inline;">
						<span id = "emailType">@</span>
					
					<!-- SELECT-OPTION -->
					<span class="w3ls-text w3ls-name" id = "email2Chk">
						<select id = "email2" name = "email2" class="form-control form-control-sm" style="width: 30%; display: inline;">
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="hanmail.net">hanmail.net</option>
							<option value="nate.com">nate.com</option>
							<option value="etc">직접 입력</option>
						</select>
					</span>
					<!-- email3 -->
					<input type="text" name="email3" id = "email3" title="직접입력인 경우 이메일 뒷자리 보여줌" class="form-control form-control-sm"
        				style="visibility:hidden; display: inline; width: 30%;"/><br>
					 <div class="form-check">
        			<input type="text" name="emailCh" id = "emailCh" class="form-control form-control-sm" style="width: 30%; display: inline;"/>
					<input type="button" value="인증번호발송" name="email3Chk" id="email3Chk"  class="btn btn-gradient-dark btn-rounded btn-sm"/>
					<span id="output" style="color: white;margin-left: 20px;">180 초</span>
					 </div>
                  </div>
                  <div class="mb-4">
                    <div class="form-check"  style="display: inline;">
                      <label class="form-check-label text-muted" style="display: inline;">
                        <input type="checkbox" class="form-check-input"> 약관 동의 </label>
                    </div>

	                        <input type="button" value="약관 보기" class="btn btn-gradient-dark btn-rounded btn-sm">

                  </div>
                  <div class="mt-3">
                    <a class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" href="">회원가입</a>
                  </div>
                  <div class="text-center mt-4 font-weight-light"> 이미 아이디가 있나요? <a href="<c:url value='/login'/>" class="text-primary">로그인</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="<c:url value='/resources/assets/vendors/js/vendor.bundle.base.js'/>"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="<c:url value='/resources/assets/js/off-canvas.js'/>" ></script>
    <script src="<c:url value='/resources/assets/js/hoverable-collapse.js'/>" ></script>
    <script src="<c:url value='/resources/assets/js/misc.js'/>" ></script>
    <!-- endinject -->
  </body>
</html>