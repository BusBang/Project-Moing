<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>

<!--카카오 -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0ef61c6cbff2ec9e9cbbc57f49bc8ae2&libraries=services"></script> 
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 

<!-- 달력 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/formInput.css">
<link rel="stylesheet" href="/css/btn.css">

    <style>    	
    	.container{
    		margin : 0 auto;
    		width : 1280px;
    	}
        #place_div{
            width: 100%;
            margin: 0 auto;
        }
        th{
        	width:300px;
        	text-align:right;
        	padding-right : 20px;
        	font-size : 20px;
        }
        #title{
        	text-align:center;
        	height:100px;
        	line-height:100px;
        	font-size : 30px;
        }
        label{
			margin:0px;
			color : #656565;
			border: 0.5px solid rgb(235,235,235) ;
		}
		label:hover{
			cursor:pointer;
		}
        
    </style>
</head>
<body>
	 <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	 
	 <!-- 달력 script : header.jsp 위에 있으면 먹히지가 않음 -->
    <script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<section class="container" style="margin-bottom:60px;">
        <div id="place_div">
            <div id="title"><span>장소제공글 수정</span></div>
            <br>
            <form action="/updatePlace" method="post" enctype="multipart/form-data" id="form" class="form-orange-line">
            	<input type="hidden" name="placeNo" value="${p.placeNo }"> <!-- 수정을 위한 게시글의 번호 -->
               	<input type="hidden" name="checked_option" value="${p.checkOption }">         
                <table class="table">
                    <tr>
                        <th>*소개제목</th>
                        <td>
                            <input name="placeTitle" type="text" class="form-control" autocomplete="off" value="${p.placeTitle }" required>
                        </td>
                    </tr>
                    <tr>
                        <th>*소개내용</th>
                        <td>
                            <textarea id="content" name="placeContent"  style="height:150px;" class="form-control" style="resize:none;" autocomplete="off" required>${p.placeContent }</textarea>
                            <span id="count" style="float:right;">(0/100)</span>
                        </td>
                    </tr>
                    <tr>
                        <th>*주소</th>
                        <td>
							<button type="button" id="addrSearchBtn" onclick="addrSearch();" class="btn btn-info" style="margin-bottom:10px;">주소검색</button>
							<input name="placeAddr" style="display:inline-block; margin-bottom:10px;" type="text" class="form-control" value="${p.placeAddr }"required readonly>														
							<div id="map" style="width:100%;height:350px;"></div>			
                        </td>
                    </tr>
                    <tr>
                    	<th>*이용가능 시작날짜</th>
                    	<td><input type="text" name="placeStartDate" autocomplete="off" class="form-control" style="width:300px;" value="${p.placeStartDate }"required readonly>
                    		<!-- <input name="placeStartDate" type="text" placeholder="ex)2020-05-08" class="form-control">  -->
                    	</td> 
                    </tr>
                    <tr>
                    	<th>*이용가능 마감날짜</th>
                    	<td><input type="text" name="placeEndDate" autocomplete="off"  class="form-control" style="width:300px;" value="${p.placeEndDate }"required readonly>
                    		<!-- <input name="placeEndDate" type="text" placeholder="ex)2020-05-08" class="form-control">  -->
                    	</td> 
                    </tr>
                    <tr>
                    	<th>예약금</th>
                    	<td>
                    		<input name="placeDeposit" type="text" autocomplete="off" placeholder="금액을 입력하세요" value="${p.placeDeposit }"class="form-control" style="width:300px;">
                    	</td>
                    </tr>
                    <tr>
                    	<th>*계좌번호</th>
                    	<td>
                    		<input name="placeAccountNumber" type="text" autocomplete="off" placeholder="계좌번호를 입력하세요" value="${p.placeAccountNumber }" style="width:300px;" class="form-control" required>
                    	</td>
                    </tr>
                    <tr>
                    	<th>첨부파일</th>
                    	<td><!-- 타입 파일로 바꾸기! -->
                    		<div>                    			
		                    	<input name="placefilename"  class="form-control" type="file" style="width:300px;" onchange="uploadImg(this)" style="margin-bottom:10px;">		                    	
                    			<c:if  test="${not empty p.placefilename }">
                    				<img id="img-view" src="/upload/place_photo/${p.placefilepath }" width="400px">
                    				<input type="hidden" name="oldfilename" value="${p.placefilename }">		                    		
                    			</c:if>
                    			<c:if  test="${empty p.placefilename }">
                    				<img id="img-view" width="400px">
                    			</c:if>
                    		</div>
                    	</td>
                    </tr>
                    <tr>
                    	<th>*지역코드</th>
                    	<td>                    		
                    		<select id="sel1" class="form-control" style="width:148px;" required>
                    			<option>1차 지역명</option> 
                    			<c:forEach items="${cList }" var="c">
                    				<c:if test="${c.countryLevel == 1 }" > 
                    					<c:if test="${c.countryName eq sel1 }">
                    						<option value="${c.countryName }" selected>${c.countryName }</option>
                    					</c:if>
                    					<c:if test="${c.countryName ne sel1 }">
                    						<option value="${c.countryName }">${c.countryName }</option>
                    					</c:if>
                    					
                    				</c:if>
	                    		</c:forEach>
                    		</select>
                    		<select id="sel2" name="countryName" class="form-control" style="width:148px;" required>
                    			<option value="${sel2 }"></option>
                    		</select>
                    	</td>
                    </tr>
                    <tr>
                    	<th>*장소형식</th>
                    	<td>
                    		<select name="placeKindName" id="placeKindName" class="form-control" style="width:300px;" required>
                    			<option>장소형식</option> 
                    			<c:forEach items="${pkList}" var="pk">
                    				<c:if test="${pk.placeKindName eq  placekindname}">
                    					<option value="${placekindname }" selected> ${placekindname }</option>
                    				</c:if>
                    				<c:if test="${pk.placeKindName ne placekindname }">
                    					<option value="${pk.placeKindName }">${pk.placeKindName}</option>
                    				</c:if>
	                    		</c:forEach>
                    		</select>
                    	</td>
                    </tr>
                    <tr>
                    	<th>옵션</th>
                    	<td>
                    		<table>
                    		<tr>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk1" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk1" onclick="check_click(this)">&nbsp; 야외 <input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk2" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk2" onclick="check_click(this)">&nbsp; Wifi<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk3" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk3" onclick="check_click(this)">&nbsp; 반려동물 출입 <input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk4" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk4" onclick="check_click(this)">&nbsp; 주차 <input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    	</tr>
	                    	<tr>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk5" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk5" onclick="check_click(this)">&nbsp; 흡연실<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk6" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk6" onclick="check_click(this)">&nbsp; 음식물반입<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk7" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk7" onclick="check_click(this)">&nbsp; TV, 프로젝터<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk8" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk8" onclick="check_click(this)">&nbsp; 의자, 테이블<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    	<tr>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk9" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk9" onclick="check_click(this)">&nbsp; 음향, 마이크<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk10" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk10" onclick="check_click(this)">&nbsp; PC<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk11" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk11" onclick="check_click(this)">&nbsp; 취사시설<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    		<td style="text-align:center; line-height:50px;">
	                    			<label for="chk12" style="width:239px; height:50px;">
	                    				<input type="checkbox" name="check_option" id="chk12" onclick="check_click(this)">&nbsp; CCTV<input type="hidden" name="check_option2">
	                    			</label>
	                    		</td>
	                    	</tr>
                    		</table>
                    	</td>
                    </tr>
                    <tr>
                    	<td colspan="2" style="text-align:center;">
                    		<input type="submit" value="등록하기" class="btn btn-info">
                    		<input type="reset" id="reset" value="초기화" class="btn btn-info">
                    	</td>
                    </tr>
                </table>
            </form>
        </div>
    </section>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
    <script>
		    $("#reset").click(function(){
				$("label").each(function(i, item){
					$(item).css("background-color","");
				});
				$("#map").css("display","none");
			});
			
			function check_click(check){
				if($(check).is(":checked")){
					$(check).parent().css("background-color","orange");
				}else{
					$(check).parent().css("background-color","");				
				}
			}
    	  //-------------------------------------등록했던 장소글 정보 표시해주기------------------------------------------
    	  	var selectedSel2 = ""; //$("#sel1").change() 함수에 쓰이는 전역변수
    	  	
			$(function(){
				$("#content").keyup(); //로드하고 나서 Content 길이 재서 표시
				selectedSel2 = $("#sel2").val();
				$("#sel1").change(); //로드하고 나서 1차지역에 대한 2차 지역 리스트 표시
				var addr = $("input[name=placeAddr]").val(); //지도 띄울 주소값
				var checked_option = $("input[name=checked_option]").val();
				var checkboxs = document.getElementsByName("check_option");
				placeNo =  $("input[name=placeNo]").val();
				for(var i=0; i<12; i++){
					
					if(checked_option.charAt(i)=='1'){
						checkboxs[i].checked = true;
					}
				}
				
				//------------------------체크되어 있는 박스 색 칠해주기
				$("input[name=check_option]").each(function(i, item){
					if($(item).is(":checked")){
						$(item).parent().css("background","orange");
					}
				});
				
				//------------------------------달력-----------------------------------------
				$( "input[name=placeStartDate], input[name=placeEndDate]" ).datepicker({
			    	dateFormat: 'yy-mm-dd', //DB에 들어갈 형식으로 지정해주기
			    	prevText: '이전 달',
			    	nextText: '다음 달',
			    	closeText : '닫기',
			    	monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			    	monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			    	dayNames: ['일','월','화','수','목','금','토'],
			    	dayNamesShort: ['일','월','화','수','목','금','토'],
			    	dayNamesMin: ['일','월','화','수','목','금','토'],
			    	showMonthAfterYear: true,
			    	changeMonth: true,
			    	changeYear: true,
			    	constrainInput : false,
			    	yearSuffix: '년',
			    	minDate: 1
			    	
			  });
				
				//------------------------입력되어 있는 주소대로 지도 띄워주기-------------------------
				
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
    	  	
		  //--------------------------------------------------소개내용 글자수 ---------------------------------------------------------
			$("#content").keyup(function(){
				var len = $(this).val().length;
							
				if(len >= 101){ //10글자 넘어가면 입력되어있던 값 고정 시키기
					$(this).val($(this).val().substr(0,100));
					alert('최대 100 글자까지 입력 가능합니다.');				
					len = $(this).val().length;
					$("#count").html("(" + (len-1) + "/100)");
				}
				$("#count").html("(" + (len) + "/100)");
			});
			
			//--------------------------------------------------submit 시 검사--------------------------------------------------------
			$("#form").submit(function(){ //submit할 때				
				
				//시작날짜 마감날짜 비교
				var start = $("input[name=placeStartDate]").val().split("-");
				var end = $("input[name=placeEndDate]").val().split("-");
				var start_month = Number(start[1]);
				var start_day = Number(start[2]);
				var end_month = Number(end[1]);
				var end_day = Number(end[2]);
				
				if(end_month < start_month){ // 5월에서 4월 고른경우
					alert("시작 [월]보다 마감 [월]이 빠릅니다. 다시 선택하세요.");
					$("input[name=placeEndDate]").focus();
					return false;
				}else if(end_month == start_month && end_day < start_day){
					alert("시작 [일]보다 마감 [일]이 빠릅니다. 다시 선택하세요.");
					$("input[name=placeEndDate]").focus();
					return false;
				}
				
				//주소에 값이 입력되어 있는지
				if($("input[name=placeAddr]").val() == ""){
					alert("주소는 필수 입력사항입니다.");
					addrSearch();
					return false;
				}
				
				//예약금에 숫자만 입력되어 있는지 정규표현식 검사
				var deposit = $("input[name=placeDeposit]").val(); //예약금 value
				exp = /^[0-9]*$/; //정규표현식
				if(!exp.test(deposit)){
					alert("예약금은 숫자만 입력 가능합니다. 다시 입력하세요");
					$("input[name=placeDeposit]").val("");
					$("input[name=placeDeposit]").focus();
					
					return false;
				}
				
				//계좌번호에 숫자만 입력되어 있는지 정규표현식 검사
				var accountNumber = $("input[name=placeAccountNumber]").val();
				
				if(!exp.test(accountNumber)){
					alert("계좌번호는 숫자만 입력 가능합니다. 다시 입력하세요");
					$("input[name=placeAccountNumber]").val("");
					$("input[name=placeAccountNumber]").focus();
					
					return false;
				}
				
				//-----------------------체크박스 옵션-----------------------
				$("input[name=check_option]").each(function(index,item){
					if($(item).is(":checked")){
						$(item).next().val('y');
					}else{
						$(item).next().val('n');
					}
				});
				//-----------------------지역명 선택 여부---------------------
				
				//------------------------주소 값 비교-----------------------
				var inAddr = $("input[name=placeAddr]").val().split(" ");//api를 이용한 주소값을 공백단위로 잘라 배열에 저장
				var selAddr = $("#sel1").val().substring(0,2);
				var sel2Addr = $("#sel2").val().substring(0,2);
				
				
				if(selAddr != inAddr[0].substring(0,2)){ //선택지역명의 앞 두글자 and 입력한 주소 앞 두글자
					alert('주소와 지역명이 일치하지 않습니다. 다시 선택하세요');
					addrSearch();
					return false;				
				}
				if(sel2Addr != inAddr[1].substring(0,2)){
					alert('주소와 지역명이 일치하지 않습니다. 다시 선택하세요');
					addrSearch();
					return false;	
				}	
				
				if($("#placeKindName").val() == "" || $("#placeKindName").val() == "장소형식"){
					alert("장소형식은 필수값 입니다.");
					return false;
				}	
			});
			
			
			$("input[name=placeStartDate]").change(function(){
				
				var date1 = $(this).val().split("-"); //날짜 합치기
				var date2 = new Date(date1[0], Number(date1[1])-1, date1[2]);
				var betweenDate = (getToday().getTime() - date2.getTime()) /1000/60/60/24; 
				
			});
			function getToday(){
				var date = new Date(); //날짜 구하는 객체 생성
				return date;
			}
			
			//---------------------------------------------------select(지역명) 선택------------------------------------
			
			//첫번째 지역명을 선택할때마다 두번째 지역명의 값을 가져오는 작업
			$("#sel1").change(function(){ 
				var sel1 = $("#sel1").val();
				//var sel2Selected = $("input[name=sel2Selected]").val(); //등록했을때의 2차 지역명
				$.ajax({
					url : "/selectOption",
					data : {countryName : sel1},
					type : "get",
					success : function(data){
						var sel2 = $("#sel2");
						sel2.find("option").remove(); //누적되지 않게 원래 값 삭제
						$("#sel2").append("<option>2차 지역명</option>");
						for(var i=0; i<data.length; i++){
							var names = data[i].names;
							if(data[i].names == selectedSel2){ //지역명 리스트중 전역변수 selectedSel2와 같은것(즉, 글 등록했을떄 선택한 2차지역명을 selected로 설정하기 위함)
								$("#sel2").append("<option value='"+names+"' selected>" + names + "</option>");
							}else{
								$("#sel2").append("<option value='"+names+"'>" + names + "</option>");								
							}
						}
					}
				});
			});
			
		
			//----------------------------------------------파일 업로드-------------------------------------------------
			var prevImgURL = "";
			function uploadImg(file){
				
				if(file.files.length != 0 && file.files[0] != 0){ //파일을 1개 이상 업로드했고, 파일의 길이가 0이 아닐때
					var fileReader = new FileReader(); //해당 파일 정보를 읽어와 저장할 객체
					fileReader.readAsDataURL(file.files[0]); //1개 업로드할때 해당 파일의 정보를 읽어옴
					
					fileReader.onload = function(e){ //다 읽어오면
						$("#img-view").attr("src",e.target.result);
						prevImgURL = $("#img-view").attr("src"); 
						$("#img-view").css("height","400px");
					}
				}else{ //파일을 업로드 했다가 취소 했을때?				
					$("#img-view").css("display","none");
				}
			}
			//----------------------------------------------카카오 지도 API----------------------------------------------
			
			function addrSearch(){ //주소검색 버튼 눌렀을 때
					
					new daum.Postcode({
						oncomplete: function(data) {
							//지번을 클릭하면 지번에만 저장됨.
							//도로명을 클릭하면 지번에도 저장이 되고, 도로명에도 저장이되고
							$("input[name=placeAddr]").val(data.jibunAddress);
							
							//주소 검색 후 입력 완료하면 지도 띄우기!
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
								geocoder.addressSearch(data.jibunAddress, function(result, status) {
								
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
							//test
									
						}
					}).open();
			}
    </script>
</body>
</html>