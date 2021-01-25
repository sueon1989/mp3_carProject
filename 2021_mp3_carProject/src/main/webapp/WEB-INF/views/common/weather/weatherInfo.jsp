<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
        
<%@include file="/WEB-INF/views/includes/header.jsp" %>  
        <div class="container-fluid">
          <div class="row justify-content-center">
            <div class="col-12">
              
              
              
              
	<!-- 기본 화면 -->
	<h2 class="mb-2 page-title">날씨예보</h2>
	<hr class="my-4">
	
	<span>      
		<img src="/resources/weatherSVG/cloud.svg" alt="..." class="img-rounded" width="40px" height="35px">
	</span> 
	
	<!-- 로그인하지 않은 경우 -->
	<sec:authorize access="isAnonymous()">
	
		회원가입 후 자동차관리용 플러그를 신청하세요!<br>
		평균 주행거리, 연비 자동계산, 
		실시간 날씨정보에 따른 차량점검 tip 등의<br>
		편리한 기능을 사용하실 수 있습니다!<br>
		
        <a href="/customLogin">로그인</a><br>
        <a href="/member/register">회원가입</a><br>
	</sec:authorize>
	
	
	<!-- 로그인한 경우 -->
	<sec:authorize access="isAuthenticated()">
	
		위치: 경기도 수원시 권선구 평동 </br></br>
		날씨: ${weather} </br></br>
		resultMsg: ${weather.resultMsg}</br></br>
		
	    <hr class="my-4"> 
             <h2 class="mb-2 page-title">날씨 정보</h2>
             <div class="row my-4">
               <!-- Small table -->
               <div class="col-md-12">
                 <div class="card shadow">
                   <div class="card-body">
                     <!-- table -->
                     <table class="table datatables" id="dataTable-1">
                       <thead>
                         <tr>
                           <th width="30" >강수확률</th>
                           <td width="250" >${weather.pop}</td>
                         </tr>
                         <tr>
                           <th width="30" >강수형태</th>
                           <td width="250"></td>
                         </tr>  
                         <tr>
                           <th width="30" >6시간 강수량</th>
                           <td width="250"> mm</td>
                         </tr>  
                         <tr>
                           <th width="30" >Gender</th>
                           <td width="250"><sec:authentication property="principal.member.member_gender"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >Birth</th>
<%--                             <td><fmt:formatDate value="<sec:authentication property="principal.member.member_birth"/>" type="date" pattern="yyyy-MM-dd"  /></td> --%>
<%--                             <sec:authentication property="principal.member.member_birth"/> --%>
<%--    							<fmt:formatDate value="${date }" type="both" pattern="yy-MM-dd"/> --%>
                           <td width="250"><sec:authentication property="principal.member.member_birth"/></td>
                           
                         </tr>  
                         <tr>
                           <th width="30" >Phone</th>
                           <td width="250"><sec:authentication property="principal.member.member_phone"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >Address</th>
                           <td width="250"><sec:authentication property="principal.member.member_address"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >Mail</th>
                           <td width="250"><sec:authentication property="principal.member.member_mail"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >MailAccept</th>
                           <td width="250"><sec:authentication property="principal.member.member_mailaccept"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >RegDate</th>
<%--                        <td width="250"><fmt:formatDate pattern="yyyy-MM-dd" value="<sec:authentication property="principal.member.member_regDate"/>" /></td> --%>
                           <td width="250"><sec:authentication property="principal.member.member_regDate"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >Permission</th>
                           <td width="250"><sec:authentication property="principal.member.member_permission"/></td>
                         </tr>  
                         <tr>
                           <th width="30" >Action</th>
               			<td width="250"><button class="btn btn-sm dropdown-toggle more-horizontal" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                               <span class="text-muted sr-only">Action</span>
                             </button>
                             <div class="dropdown-menu dropdown-menu-right">
                               <a class="dropdown-item" href="/mypage/myInfoUpdate">수정</a>
                               <a class="dropdown-item" href="/mypage/myInfoDelete">탈퇴</a>
                               <a class="dropdown-item" href="/">메인화면으로</a>
                             </div>
                           </td>
                         </tr>  
                       </thead>
             
                        
                        
		      
	</sec:authorize>




            </div> <!-- .col-12 -->
          </div> <!-- .row -->
        </div> <!-- .container-fluid -->
      <script>
      $('#dataTable-1').DataTable(
      {
        autoWidth: true,
        "lengthMenu": [
          [16, 32, 64, -1],
          [16, 32, 64, "All"]
        ]
      });
    </script>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>