
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- 다음 지도 api -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=18612570b4aea4ca147f89d641c21adc&libraries=services"></script>
<!-- 결제  -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<style>
section {
	width: 1280px;
	margin: 0 auto;
	border: 1px solid black;
}

section>div {
	display: block;
	width: 100%;
	height: 450px;
}

section>div:first-child>div, section>div:nth-child(2)>div {
	float: left;
	height: 450px;
}

.brief>div:first-child {
	width: 50%;
	margin: 62.5px;
}

.brief>div:last-child {
	width: 390px;
	margin: 62.5px;
}

.detail div {
	display: inline-block;
}

.fas, .far {
	line-height: 70px;
	font-size: 30px;
}

.brief h1 {
	font-size: 35px;
}

.brief button {
/* 	margin-top: 30px; */
}

.image>img {
	width: 640px;
	height: 430px;
}

i:nth-of-type(3) {
	color: red;
}

.detail {
	background-color: #F0E0C0;
}

.detail>div:last-child {
	width: 390px;
	margin: 0 62.5px;
}

.review {
	float: none;
	text-align: center;
}

h2 {
	margin-left: 62.5px;
	margin-top: 0;
	padding-top: 40px;
	text-align: justify;
}

textarea {
	resize: none;
}

.review_button {
	float: right;
	position: relative;
	right: 68.5px;
}

.write_review, .read_review {
	width: 100%;
	height: 130px;
	text-align: center;
}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<!-- 달력 script : header.jsp 위에 있으면 먹히지가 않음 -->
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<section>

	<div class="brief">
		<div id="image"
			style="width: 60%; margin: 0 auto;">
			<c:if test="${empty p.placeFilepath}">
				<img
					style="margin: 0 auto;width: 100%; height: 100%; "
					src="/upload/place_photo/noImg.png" class="img-thumbnail"
					alt="Cinque Terre">
			</c:if>
			<c:if test="${not empty p.placeFilepath }">
				<img
					style="margin: 0 auto;width: 100%; height: 100%;"
					src="/upload/place_photo/${p.placeFilepath }" class="img-thumbnail"
					alt="Cinque Terre">
			</c:if>
		</div>
		
		<input type="hidden" id="placeNo" name="placeNo" value="${p.placeNo }">
		<input type="hidden" id="PlaceAddr" name="placeAddr" value="${p.placeAddr }">
		<input type="hidden" name="memberEmail" value="${m.memberEmail }">
		<input	type="hidden" name="Phone" value="${m.memberPhone }"> 
		<input	type="hidden" name="memberName" value="${m.memberName }">

		<div class="place_info" style="width: 40%; margin: 0 auto; text-align: center;">
			<h1>${p.placeTitle }</h1>

			<div style="font-size: 20px;">주소 _ ${p.placeAddr }</div>
			<div style="font-size: 20px;">
				<!-- <input id="meetingNo" type="hidden" value="${mt.meetingNo}">  -->
				<input type="hidden" id="memberId" name="memberId" value="${p.memberId }"> 장소제공자 _ ${p.memberId }
				<c:if test="${sessionScope.member.memberId != p.memberId }"> 
					<button class="btn btn-outline-primary btn-sm" id="talktalk" type="button" style="font-size: 15px; border: 1px solid black; border-radius:50px;">톡톡</button>
				</c:if>
				<form id="letterWriteFrm">
	 				<input type="hidden" name="letterGetMemberId" value="${p.memberId }"><!-- 답장/쪽지보내기 버튼 누를 때 작성하는 폼(현재 쪽지의 수신/발신자 아이디를 받아 쪽지를 보내기 때문에 수신자가 된다.) -->
	 				<input type="hidden" name="to" value="2"><!-- 답장/쪽지 보내기 버튼과 일반 쪽지보내기 버튼을 누를 때를 구분함 (답장/쪽지 (수신인 정해짐):2, 단체 쪽지(수신인 여러명, 수정 가능):1 뉴쪽지(수신인X):0 -->
	 			</form>
	 			
				
			</div>

			<c:if test="${sessionScope.member.memberId != p.memberId }">
				<div>
					<hr>
					예약날짜 : <input type="text" name="placeUseDate" id="datepicker" autocomplete="off" readonly>
					<hr>
				</div>
				<div style="height : 100px;">
					<fieldset>
						<legend>모임정보</legend>
						<div style="height : 100px; overflow-y:scroll;">
						<c:forEach items="${list}" var="l">
							<input type="radio"  name="meetingNo" value="${l.meetingNo}">${l.meetingName }<br>
						</c:forEach>
						</div>
					</fieldset>
				</div>
				<div id="pay" style="margin-top:50px;">
					<h4>
						<input type="hidden" name="placeDeposit" id="placeDeposit" value="${p.placeDeposit }">
					</h4>
					<h4>
						총 결제 금액 : <span id="totalPrice">${p.placeDeposit }</span>원
					</h4>
					<c:if test="${empty sessionScope.member}">
						<button style="width: 200px; height: 50px;" class="btn btn-danger" id="login">결제하기</button>
					</c:if>
					<c:if test="${not empty sessionScope.member}">
						<button style="width: 200px; height: 50px;" class="btn btn-danger" id="payBtn">결제하기</button>
					</c:if>
					<input type="hidden" id="sessionMemberId" value="${sessionScope.member.memberId }">
					<!-- 
					<c:if test="${not empty sessionScope.meeting.memberId && sessionScope.member.memberId == 'admin' }">
						<button style="width: 200px; height: 50px;" class="btn btn-danger" id="payBtn">결제하기</button>
					</c:if>
					 -->
					<p id="paymentResult"></p>
				</div>
			</c:if>
		</div>
	</div>

	<div class="detail">
		<h3>대관소개</h3>
		<div class="detail-sm" style="font-size: 25px; width: 100%; padding:20px; height: 350px; margin: 0 auto;">
			<h4>${p.placeTitle }</h4>
			<br> ${p.placeContent }
		</div>
	</div>
	
	<div class="option1" style="width: 90%; margin: 0 auto;">
		<br>
		<h3>옵션</h3>
		<div class="option-sm" style="font-size: 25px; width: 90%; height: 90%; margin: 0 auto;">
			<input type="hidden" name="checkOption" value="${p.checkOption }">
			<table style="width: 100%; height: 100%;">
				<tr>
					<th>
						<input type="checkbox" id="check" name="checkOption2" disabled> 야외
					</th>
					<th>
						<input type="checkbox" id="check" name="checkOption2" disabled> 와이파이
					</th>
					<th>
						<input type="checkbox" id="check" name="checkOption2" disabled> 반려동물
					</th>
					<th>
						<input type="checkbox" id="check" name="checkOption2" disabled> 주차
					</th>
				</tr>
				<tr>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> 흡연실</th>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> 음식물반입</th>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> TV,프로젝터</th>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> 의자,테이블</th>
					</tr>
					<tr>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> 음향,마이크</th>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> PC</th>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> 취사시설</th>
						<th><input type="checkbox" id="check" name="checkOption2"
							disabled> cctv</th>
					</tr>
				</table> 
				
				<c:forEach items="${b}" var="booking">
					<input type="hidden" class="rDate" value="${booking.visitDate }">
					<input type="hidden" class="booking_cancel" value="${booking.bookingCancel }">
					<input type="hidden" class="booking_meetingNo" value="${booking.meetingNo }">
					<input type="hidden" class="booking_placeNo" value="${booking.placeNo }">
				</c:forEach>
		</div>
	</div>
	
	<hr>
	
	<div class="place_map" style="width: 90%; margin: 0 auto;">
		<br>
		<h3>찾아오시는길</h3>
		<input name="placeAddr" type="hidden" value="${p.placeAddr }">
		<div id="map" style="width: 500px; height: 400px; margin: 0 auto;"></div>
	</div>
	
	<br>
	<br>
	<br>
	<br>
	</section>
	
	<script>
		$(function() {
			
			function test(){
				console.log($("input[name=meetingNo]").is(":checked").val());
			}
			
			
			
			var checked = $("input[name=checkOption]").val();
			var check = document.getElementsByName("checkOption2");
			for (var i = 0; i < 12; i++) {
				if (checked.charAt(i) == '1') {
					check[i].checked = true;
					$("#check").css("text-decoration", "underline ");

				}

			}
		});
		//현제 booking_info에 들어있는 date 값 확인.
		//for (var i = 0; i < $(".rDate").length; i++) {
			//console.log($(".rDate").eq(i).val());
		//}

		var meetiongStartDate = "";
		$("#datepicker").change(function() {
			var meetiongStartDate = $("#datepicker").val(); //선택 날짜
			
			var bookingCancel = document.getElementsByClassName("booking_cancel");
			var bookingMeetingNo = document.getElementsByClassName("booking_meetingNo");
			var bookingPlaceNo = document.getElementsByClassName("booking_placeNo");
			var rDate = document.getElementsByClassName("rDate");
			
			if (meetiongStartDate == null || meetiongStartDate == "") {
				alert("예약날짜를 선택해주세요.");
			}
			
			
			if(bookingPlaceNo.length == 0){ //해당 장소글에 예약정보가 없는경우(예약가능)
				//예약 가능(insert)
				console.log("1");
			}else{ //장소글에 예약정보가 있으면
				console.log("2");
				for(var i=0; i<rDate.length; i++){
					if (rDate[i].value == meetiongStartDate) { //장소글의 예약 날짜 리스트중 예약하려는 날짜랑 겹치면
						console.log("3");
						if(bookingCancel[i].value == ("F")){ //예약정보의 취소여부에 F가 하나라도 있는지 검사한다. 있으면 예약 불가
							console.log("4");
							alert("이미 예약이 찬 날짜입니다.");
							$("#datepicker").val("");						
						}else{ //T는 여러개 F는 0개인 경우. 예약 가능(insert F로)
							console.log("5");
						}
					}else{//예약 가능(insert)
						console.log("6");
					}					
				}	
				console.log("7");
			}
			
		});
	</script>
	<script>
		//datepicker
		var startDate = "";
		$("input[name=placeUseDate]").datepicker(
				{
					dateFormat : 'yy-mm-dd', //DB에 들어갈 형식으로 지정해주기
					prevText : '이전 달',
					nextText : '다음 달',
					closeText : '닫기',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					showMonthAfterYear : true,
					changeMonth : true,
					changeYear : true,
					yearSuffix : '년',
					minDate : 1

				});
	</script>
	<script>
		$(function(){
			var addr = $("input[name=placeAddr]").val();
		
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
			      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			      level: 3 // 지도의 확대 레벨
			};  
		
			// 지도 객체 생성 구문
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 네이버 지도와 달리 카카오 지도는 services 라이브러리안에 Geocoder 기능이 포함되어 있음
			var geocoder = new kakao.maps.services.Geocoder();
			
			// (주소, function(주소에 대한 위경도, 응답결과))
			geocoder.addressSearch(addr, function(result, status) {
			
				  // 정상적으로 검색이 완료됐으면 
				  if (status === kakao.maps.services.Status.OK) {
			
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
				        // 결과값으로 받은 위치를 마커로 표시
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });
			
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});   
			
		
		});
	</script>
	<script type="text/javascript">
		$("#login").click(function() {
			alert("로그인이 필요합니다.");

		});
	</script>
	<script>
	$(function(){
			
			$("#talktalk").click(function(){
				var url="/letterWrite";
				var title="letterWrite";
				var status = "left=500px, top=100px, width=470px, height=550px, menubar=no, status=no, scrollbars=yes";
		    	var popup = window.open("", title, status);
		    	$("input[name=letterGetMemberId]").val();
		    	$("input[name=to]").val("2");
				$("#letterWriteFrm").attr("action",url);
				$("#letterWriteFrm").attr("method","post");
				$("#letterWriteFrm").attr("target",title);//새로 열린 popup창과 form태그를 연결
				$("#letterWriteFrm").submit();
				
			});
			
		
	});
	
	
		//결제 api
		$(function() {
			
			var totalpay = $("#placeDeposit").val();
			$("#pay input").change(function() {
				if ($(this).is(":checked")) {
					totalpay += Number($(this).val());
				} else {
					totalpay -= Number($(this).val());
				}
				$("#pay span").html(totalpay);
			});
			$("#payBtn").click(
					function() {
						var meetiongStartDate = $("#datepicker").val();
						if (meetiongStartDate == null
								|| meetiongStartDate == "") {
							alert("예약날짜를 선택해주세요.");
							return false;
						}else if(!$("input[name=meetingNo]").is(":checked")) {
							alert("모임을 선택해주세요");
							return false;
						}
						var price = $("#pay span").html();
						var d = new Date();
						var date = d.getFullYear() + "" + (d.getMonth() + 1)
								+ "" + d.getDate() + "" + d.getHours() + ""
								+ d.getMinutes() + "" + d.getSeconds();
						IMP.init("imp90810019");
						IMP.request_pay({
							merchant_uid : '상품명_' + date,
							name : '결제테스트',
							amount : price,
							buyer_email : '${sessionScope.member.memberEmail}',
							buyer_name : '${sessionScope.member.memberName}',
							buyer_tel : '${sessionScope.member.memberPhone}',
							buyer_addr : '${sessionScope.member.memberAddr}',
							buyer_postcode : '33333'
						}, function(rsp) {
							if (rsp.success) {
								var msg = "결제가 완료되었습니다.";
								var r1 = "고유ID : " + rsp.imp_uid;
								var r2 = "상점 거래 아이디 :" + rsp.merchant_uid;
								var r3 = "결제금액 : " + rsp.paid_amount;
								var r4 = "카드 승인번호 : " + rsp.apply_num;
								alert(msg + "\n" + r1 + "\n" + r2
										+ "\n" + r3 + "\n" + r4);

								var meetingNo = $("input[name=meetingNo]:checked").val();
								var placeNo = $("#placeNo").val();
								var visitDate = $('#datepicker').val();
								var memberId = $("#sessionMemberId").val();
								
								
									
								
								$.ajax({
									url : "/placePayment",
									data : {
										meetingNo : meetingNo,
										placeNo : placeNo,
										visitDate : visitDate,
										memberId : memberId
									},
									type : "post",
									success : function(date) {
										console.log("서버전송성공");
									},
									error : function() {
										console.log("서버전송실패");
									},
									complete : function() {
										console.log("무조건 호출");
									}
								});
							} else {
								$("#paymentResult").html(
										'에러내용  : ' + rsp.error_msg);

							}
						})

					});
		});
	</script>

	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>