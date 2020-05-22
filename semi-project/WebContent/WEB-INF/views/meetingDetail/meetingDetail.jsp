<%@page import="meetingDetail.model.vo.Meeting"%>
<%@page import="meetingDetail.model.vo.LikeList"%>
<%@page import="login.model.vo.LoginMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <% LoginMember m = (LoginMember)session.getAttribute("member"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5d8d206fa6e7c1392311dd0f31d40fbb&libraries=services"></script>
    <style>
        section{
            width: 1280px;
            margin: 0 auto;
        }
        section>div{
            display: block;
            width: 100%;
            height: 575px;
        }
        section>div:first-child>div, section>div:nth-child(2)>div{
            float: left;
            height: 450px;
        }
        .brief>div:first-child{
            width: 50%;
            margin: 62.5px;
        }
        .brief>div:last-child{
            width: 390px;
            margin: 62.5px;
            
        }
        .detail div{
            display: inline-block;
        }
        .fas, .far{
            line-height: 70px;
            font-size: 30px; 
        }
        .brief h1{
            font-size: 35px;
        }
        .brief button{
            margin-top: 30px;
        }
        .image>img{
            width: 640px;
            height: 430px;
        }
        i:nth-of-type(3){
            color: red;
        }
        .detail{
            background-color: #F0E0C0;
            height : 650px;
        }
        .meeting_detail{
            width: 50%;
            margin-left: 62.5px;
            margin-right: 62.5px;
        }
        .detail>div:last-child{
            width: 390px;
            height: 100%;
            margin: 0 62.5px;
        }
        .detail span{
        	position: relative;
        	bottom :6px;
        }
        .review{
            float: none;
            text-align: center;
            
        }
        h2{
            margin-left: 62.5px;
            margin-top: 0;
            padding-top: 40px;
            text-align: justify;
        }
        textarea{
            resize: none;
        }
        .review_button{
            float: right;
            position: relative;
            right: 68.5px;
        }
        .read_review{
            width: 100%;
   			height:100%;
            text-align: center;
        }
        .write_review {
            width: 100%;
            height : 100px;
            text-align: center;
        
        }
        table{
        	width:80%;
        	margin:0 auto;
        }
        
        td, th{
        	border-bottom : 1px solid #F0E0C0;
        	height :40px;
        }
        
        th{
        	width : 200px;
        	border-right : 1px solid #F0E0C0;
        }
        #page{
        	padding-bottom : 62.5px;
        	box-sizing : border-box;
        }
        a{
        	text-decoration : none;
        	color : black;
        }
        h3{
        margin-left: 130px;
    	}
    	.btn-xsm-orange1{/*작은 오렌지 테두리 버튼*/
		    width: 70px;
		    height: 35px;
		    line-height: 35px;
		    text-align: center;
		    border: 2px solid #ff9742;
		    border-radius: 10px;
		    font-weight : bold;
		    box-shadow : 1px 1px 3px gray;
		}
		.btn-xsm-orange2{/*작은 오렌지 바탕 버튼*/
		     width: 70px;
		    height: 35px;
		    line-height: 35px;
		    text-align: center;
		   background-color:  #ff9742;
		    border-radius: 10px;
		    font-weight : bold;

		}
		input{
		    border: 1px solid gray;
		    border-radius: 5px;
		    height: 33px;
		    box-shadow : 1px 1px 2px lightgray;
		}
		/*페이지  */
        #pageDiv {
        	height : 80px;
        	text-align : center;
        	margin : 0 auto;
        	margin-top : 40px;
        }
        #pageDiv>button:hover {
        	background-color : #ff9742;
        	font-weight : bold;
      	    color: #f0E0C0;
        }
        #pageDiv>div {
        	display : inline-block;
        	width:35px;
        	height:35px;
        	line-height : 35px;
        	font-size : 1.3em;
        	margin : 5px;
        	border-radius: 5px;
        	background-color : #ff9742;
        }
        #pageDiv>div:hover {
        	background-color : #404020;
        	font-weight : bold; 
        	cursor : pointer;
        	font-weight : bold;
        }
        #pageDiv>div>a {
        	text-decoration: none;
        	color : black;
        }
        #pageDiv>div>a:hover {
        	text-decoration: none;
		}		

    </style>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>

<script>
	$(function(){
		$("#like").click(function(){
			var meetingNo = ${meet.meetingNo};
			$.ajax({
				url : "/likesCount",
				data : {meetingNo:meetingNo},
				type : "get",
				success : function(data){
					/* $("#newLike").html(data); */
					$("#like").hide();
					$("#like").next().css("display","inline");
				}
			});
		});
		
		$("#dislike").click(function(){
			var meetingNo = ${meet.meetingNo};
			$.ajax({
				url : "/dislikeCount",
				data : {meetingNo:meetingNo},
				type : "get",
				success : function(data){
					/* $("#newLike").html(data); */
					$("#dislike").hide();
					$("#dislike").prev().show(); 
				},
				error : function(data){
					console.log("실패");
				}
			});
		});
	})
	
	function joinMeeting(meetingNo){
		if(confirm("지금 보고있는 모임에 가입하시겠습니까?")){
			location.href="/joinMeeting?meetingNo="+meetingNo;
		}else{
			console.log('n');
		}
	}
	
	function deleteReview(reviewNo, meetingNo){
		if(confirm("삭제하시겠습니까?")){
			location.href="/reviewDelete?meetingNo="+meetingNo+"&reviewNo="+reviewNo;
		}
	}
	
	function updateReview(obj, reviewNo, meetingNo){
		$(obj).parent().parent().find("input").css("display","block");
		$(obj).parent().parent().find("span").css("display","none");
		$(obj).html("수정완료");
		$(obj).attr('onclick','updateComplete(this,"'+reviewNo+'","'+meetingNo+'")');
		$(obj).attr('id','ajupdate');
		$(obj).next().html("취소");
		$(obj).next().attr('onclick','updateCancel(this,"'+reviewNo+'","'+meetingNo+'")');
	}
	
	function updateCancel(obj, reviewNo, meetingNo){
		$(obj).parent().parent().find("input").css("display","inline");
		$(obj).parent().parent().find("input").hide();
		$(obj).prev().html("수정");
		$(obj).prev().attr('onclick','updateReview(this,"'+reviewNo+'","'+meetingNo+'")');
		$(obj).html("삭제");
		$(obj).attr('onclick','deleteReview("'+reviewNo+'","'+meetingNo+'")');
	}
	
	 function updateComplete(obj, reviewNo, meetingNo){
		 var $form=$("<form action='/reviewUpdate' method='post'></form>");
			$form.append($("<input type='text' name='reviewNo' value='"+reviewNo+"'>"));
			$form.append($("<input type='text' name='meetingNo' value='"+meetingNo+"'>"));
			$form.append($(obj).parent().parent().find('input'));
			$('body').append($form);
			
			$form.submit();
	}
	
	//수정 서블릿으로 전달
	function updateMeeting(meetingNo){
		if(confirm("모임을 수정하시겠습니까?")){
	         location.href="/moimModifyFrm?meetingNo="+meetingNo;
	      }
	}
	//수정 서블릿으로 전달
	function reopenMeeting(meetingNo){
		if(confirm("모임을 재개최하시겠습니까?")){
	         location.href="/moimReOpenFrm?meetingNo="+meetingNo;
	      }
	}
	
	window.onload=function(){
		var container = document.getElementById('meeting_map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
			level: 3 //지도의 레벨(확대, 축소 정도)
		};

		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		var geocoder = new kakao.maps.services.Geocoder();
		var addrArr = "";
		addrArr = '${meet.meetingAddr}';
		var addrSplit = addrArr.split(" ");
		var position = "";
		console.log(addrSplit.length);
		for(var i=1; i<addrSplit.length; i++) {
			position += addrSplit[i]+" ";
		}
/* 		var position = document.getElementById('addr').val(); */
		geocoder.addressSearch(position,
		function(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		        var coords = new kakao.maps.LatLng(result[0].y,result[0].x);
		        var marker = new kakao.maps.Marker({
		        	map:map,
		        	position:coords
		        });
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">모임장소</div>'
		        });
		        infowindow.open(map, marker);
		        
		        map.setCenter(coords);
		    }
		});
	} 
</script>
<section>
    <div class="brief">
        <div id="image">
        	<c:if test="${not empty meet.meetingFilepath }">
            	<img style="width:100%; height:100%;" src="/upload/${meet.meetingFilepath }">               
         	</c:if>
         	<c:if test="${empty meet.meetingFilepath}">
            	<img style="width:100%; height:100%;" src="/img/meetingDefault.jpg">               
         	</c:if></div>
        <div class="meeting_info">.
            <h1>${meet.meetingTitle }</h1><input type="hidden" name="meetingNo" id="meetingNo" value="${meet.meetingNo}"><br><br>
            <i class="fas fa-users"></i> &nbsp; <span style="font-weight:bold; font-size : 1.05em;">회원 </span> <span>${meet.meetingNowPerson}명</span> / <span>${meet.meetingMaxPerson }명</span><br><br>
            <i class="fas fa-user-check"></i> &nbsp; <span style="font-weight:bold; font-size : 1.05em;">모임장 </span> <span>${meet.memberId}</span><br><br>
            
            
    
           	<c:if test="${result eq 1}">
          		<i class="far fa-heart" id="like" style="color:red; display:none"></i><i class="fas fa-heart" style="color:red;" id="dislike"></i><br>
           	</c:if>
           	<c:if test="${result eq 0 || empty sessionScope.member}">
           		<i class="far fa-heart" id="like" style="color:red; display:inline"></i><i class="fas fa-heart" style="color:red; display:none" id="dislike"></i><br>
           	</c:if>
     
            
           
            <c:if test="${sessionScope.member.memberId ne meet.memberId && not empty sessionScope.member}">		<!--로그인회원이 개최자가 아니고, 이미 이 모임에 가입된 멤버가 아닐 경우-->
            	<button class="btn-xsm-orange2 btn-xsm-orange1" style="width:100px" onclick="joinMeeting('${meet.meetingNo}')">모임 참여하기</button>
            </c:if>
            <c:if test="${sessionScope.member.memberId == meet.memberId }">
            	<button class="btn-xsm-orange2 btn-xsm-orange1" style="width:100px" onclick="updateMeeting('${meet.meetingNo }')">수정하기</button>
            	<button class="btn-xsm-orange2 btn-xsm-orange1" style="width:100px" onclick="reopenMeeting('${meet.meetingNo}')">모임 재개최</button>
            </c:if>
        </div>
    </div>
    
    <div class="detail">
        <h2>활동 계획</h2>
        <div class="meeting_detail">${meet.meetingContent }</div>
        <div>
            <i class="far fa-clock"></i> &nbsp; <span>${meet.meetingDate }</span><br><br><br>
            <i class="fas fa-map-marked-alt"></i> &nbsp; <span id="addr">${meet.meetingAddr }</span><br><br>
            <div id="meeting_map" style="width:400px; height:350px; border:1px solid black;"></div>
        </div>
    </div>
    
    
    
    
    <h3>후기</h3>
    <hr style="width : 80%; border-bottom : 3px solid orange;"><br>
    <c:if test="${empty sessionScope.member }">
    
    </c:if>
    <c:forEach items="${mp}" var="mp">
    	<c:if test="${mp.memberId eq sessionScope.member.memberId}">
		    <form action="/reviewInsert" method="post">
			    <div class="review">
			        <input type="hidden" name="reviewWriter" value="qowo1">
			        <input type="hidden" name="meetingNo" value="${meet.meetingNo }">
			        <div class="write_review">
			        <input type="text" size="125" placeholder="내용을 입력해주세요" name="reviewContent" id="review">
			        <button class="btn-xsm-orange2 btn-xsm-orange1" style="width:100px;" id="review_button">후기 남기기</button>
			        </div>
			    </div>
		    </form>
    	</c:if>
    </c:forEach>
    
    
    
    
    <div class="read_review">
	            	<input type="hidden" name="reviewNo" value="${r.reviewNo }"> <input type="hidden" name="meetingNo" value="${r.meetingNo }">
	            	<table>
	            	<c:forEach items="${review}" var="r">
	            		<tr>
	            			<th>${r.memberId }</th>
	            			<td>${r.reviewContent }<input type="text" size="100" name="update" value="${r.reviewContent }" style="display:none">
	            			</td>
	            			<th>
	            				<c:if test="${not empty sessionScope.member and sessionScope.member.memberId eq r.memberId || sessionScope.member.memberId eq 'admin'}">
	            					<a href="javascript:void(0)" class="modify" onclick="updateReview(this, '${r.reviewNo}', '${r.meetingNo }')">수정</a>
	            					<a href="javascript:void(0)" onclick="deleteReview('${r.reviewNo}', '${r.meetingNo }')">삭제</a>
	            				</c:if>	            			
	            			</th>
	            		</tr>
	            		</c:forEach>
	            	</table><br>
	            	
        <div id="pageDiv">${page }</div>
        <div style="height:50px;">
    <c:if test="${sessionScope.member.memberId == meet.memberId || sessionScope.member.memberId  == 'admin'}">
        <button type="button" class="btn-xsm-orange2 btn-xsm-orange1" style="width:100px; display:block; float:right; margin-right:135px;" onclick="deleteMeeting('${meet.meetingNo}');">모임 삭제</button>
    </c:if>
    </div>
    </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>


<script>
	function deleteMeeting(meetingNo){
		if(confirm("모임을 삭제하시겠습니까?")){
			console.log("this"+this);
			console.log(meetingNo);
			location.href="/meetingDelete?meetingNo="+meetingNo;
		}
	}
</script>
</html>