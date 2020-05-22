<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>

<!-- 달력 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!-- bootstrap 필터 버튼-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!-- bootstrap 이미지 위에 글씨-->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<style>
		.section{
			width : 1280px;
			margin : 0 auto;
			margin-bottom : 30px;
			border-left: 2px solid rgb(235,235,235);
			border-right: 2px solid rgb(235,235,235);
			border-bottom: 2px solid rgb(235,235,235);
			padding-bottom : 50px;
			overflow :hidden;
		}
		#pageNavi{
			text-align:center;
			width : 1000px;
			margin : 0 auto;
			margin-bottom : 50px;
		}
		#pageNavi>*{
			margin:10px;
			border-radius : 7px;
			padding : 5px;
			box-shadow : 1px 1px 5px gray;
		}
		.selectPage{
			font-size:20px;
			color:#F0C040;
		}
		.place_photo{
			height:230px;
		}
		.place_board{
			overflow:hidden;
		}
		input[name=keyword]{
			border-radius:0px;
			font-size:20px; 
			height:50px; 
			border:none; 
			border-bottom:3px solid orange;
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
	
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	
	
	<!-- bootstrap 필터 버튼 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<!-- 달력 script : header.jsp 위에 있으면 먹히지가 않음 -->
	<script src="//code.jquery.com/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	
	<section class="section">		
		<form action="/searchPlace" method="get">
			<input type="hidden" name="reqPage" value=1>
			<input type="hidden" name="check_option2"> 
			<div class="container" style="width:1200px;">
				<div style="text-align:center; color:#656565; "><h1>마음에 드는 장소를 골라보세요!</h1></div><br><br><br>
				<div class="input-group">
					<input type="text" class="form-control" style="font-weight:bold;" name="keyword" value="${keyword }" placeholder="게시글 제목을 입력하세요.">
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit" autocomplete="off" style="height:50px; border:none;"><i class="glyphicon glyphicon-search"></i></button>
					</div>
				</div>
			</div>
			<input type="hidden" name="checked_option" value="${check }"> <!-- 검색하면 원래값 유지하기 위함 -->
			<div class="container" style="width:1200px; text-align:right; margin-top:50px; margin-bottom:30px;">
				<button type="button" class="btn btn-info" style="background-color:orange; border-radius:3px; border:orange;" data-toggle="collapse" data-target="#container">필터</button>
				<c:if test="${not empty sessionScope.member }">					
					<button type="button" class="btn btn-info" style="background-color:orange; border-radius:3px; border:orange;" onclick="location.href='/insertPlaceFrm'">글쓰기</button>
				</c:if>
			</div>
			
			<div id="container" class="collapse" style="width:1200px; margin:0 auto; padding-left:100px;">
				<table style="cellspacing:0; cellpadding:0; padding-left:100px;">
					<tr>
						<td style="padding-left:15px;">1차 지역명</td>
						<td style="padding-left:15px;">2차 지역명</td>
						<td style="padding-left:15px;">장소형식</td>
						<td style="padding-left:15px;">이용희망날짜</td>
					</tr>
					<tr><td style="padding-top:17px;" colspan="4"></td></tr>
					<tr>
						<td>
							<select id="sel1" class="input-origin form-control"  style="width:200px; margin-right:50px;" >
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
						</td>
						<td>
							<select id="sel2" name="countryName" class="input-origin form-control" style="width:200px; margin-right:50px;" required>
	                    		<option value="${sel2 }"></option>
	                    	</select>
						</td>
						<td>
							<select name="placeKindName" class="input-origin form-control" style="width:200px; margin-right:50px;">
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
						<td>
							<input type="text" name="placeUseDate" class="form-control" autocomplete="off" value="${useDate }" readonly>
						</td>
					</tr>
				</table>
				<br><br><br>
				<div style="width:962px;">
					<span style="color:#656565; font-weight:bold;">옵션을 선택하세요.</span>
					<table>
		                <tr>
			            	<td style="text-align:center; line-height:50px;">
				            	<label for="chk1" style="width:239px; height:50px;">
				            		<input type="checkbox" id="chk1" name="check_option" onclick="check_click(this)">&nbsp; 야외
				            	</label>
			            	</td>
			                <td style="text-align:center; line-height:50px;">
			                	<label for="chk2" style="width:239px; height:50px;">
			                		<input type="checkbox" id="chk2" name="check_option" onclick="check_click(this)">&nbsp; Wifi
			                	</label>
			                </td>
			                <td style="text-align:center; line-height:50px;">
			                	<label for="chk3" style="width:239px; height:50px;">
			                		<input type="checkbox" id="chk3" name="check_option" onclick="check_click(this)">&nbsp; 반려동물 출입
			                	</label>
			                </td>
			                <td style="text-align:center; line-height:50px;">
			                	<label for="chk4" style="width:239px; height:50px;">
			                		<input type="checkbox" id="chk4" name="check_option" onclick="check_click(this)">&nbsp; 주차
			                	</label>
			                </td>
			            </tr>
			            <tr>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk5" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk5" name="check_option" onclick="check_click(this)">&nbsp; 흡연실
			                 	</label>
			                 </td>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk6" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk6" name="check_option" onclick="check_click(this)">&nbsp; 음식물 반입
			                 	</label>
			                 </td>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk7" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk7" name="check_option" onclick="check_click(this)">&nbsp; TV, 프로젝터
			                 	</label>
			                 </td>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk8" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk8" name="check_option" onclick="check_click(this)">&nbsp; 의자, 테이블
			                 	</label>
			                 </td>
			            <tr>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk9"  style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk9" name="check_option" onclick="check_click(this)">&nbsp; 음향, 마이크
			                 	</label>
			                 </td>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk10" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk10" name="check_option" onclick="check_click(this)">&nbsp; PC
			                 	</label>
			                 </td>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk11" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk11" name="check_option" onclick="check_click(this)">&nbsp; 취사시설
			                 	</label>
			                 </td>
			                 <td style="text-align:center; line-height:50px;">
			                 	<label for="chk12" style="width:239px; height:50px;">
			                 		<input type="checkbox" id="chk12" name="check_option" onclick="check_click(this)">&nbsp; CCTV
			                 	</label>
			                 </td>
			            </tr>
		            </table>
				</div>
				
			</div>
		</form>
			
 			<%int i=0; %>
			<c:forEach items="${pList }" var="p">				
				<%if(i%4==0){%>
				  <div class="place_board">		
					  <div class="container" style="width:1200px; margin-top : 50px;">				  
         		<%} i++;%>
						<div class="card" style="width:270px; box-shadow:3px 3px 5px gray; float:left; margin-left:15px;" onclick="location.href='/placeDetailFrm?placeNo=${p.placeNo}&memberId=${sessionScope.member.memberId}'">
							<div style="overflow:hidden;">
								<c:if test="${not empty p.placefilepath}">
									<img class="place_photo card-img-top" src="/upload/place_photo/${p.placefilepath }" style="width:270px;">
								</c:if>
								<c:if test="${empty p.placefilepath }">
									<img class="place_photo card-img-top" src="/upload/place_photo/noImg.gif" style="width:270px;">
								</c:if>
							</div>
							<div class="card-body" style="padding:10px;border-bottom:1px solid #656565;">
								<div style="font-weight:bold; font-size:17px; ">
									${p.placeTitle }
								</div>
								<div style="font-size:13px; text-align:right; font-weight:bold;">
									${p.placeKindName }									
								</div>
								<div style="font-size:13px; text-align:right;">								
									${p.placeStartDate} ~ ${p.placeEndDate}
								</div>
								<div style="text-align:right;">
									<span style="font-size:16px; font-weight:bold;color:#D0AEE8;">${p.placeDeposit }</span><span style="color:#656565; font-size:10px;">원/예약금</span>
								</div>
							</div>
						</div>
				<%if(i%4==0){ %>
					  </div>
				  </div>
				  <br>					
				<%} %>
			</c:forEach>
		
	</section>	
		<div id="pageNavi">${pageNavi}</div>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	
	<script>
		function check_click(check){
			if($(check).is(":checked")){
				$(check).parent().css("background-color","orange");
			}else{
				$(check).parent().css("background-color","");				
			}
		}
		//------------------------------------------hover 기능 적용
		$(".card").hover(function(){
			$(this).css("cursor","pointer");
			$(this).children().first().children().css("transform", "scale(1.2,1.2)"); 
			$(this).children().first().children().css("transition-duration","1s");
		},function(){
			$(this).css("cursor","none");
			$(this).children().first().children().css("transform", "none");
			$(this).children().first().children().css("transition-duration","0s");
		});
		//---------------------------------------------------검색 필터 적용 후 검색 필터 화면 적용 작업------------------------------
		
		var selectedSel2 = ""; //$("#sel1").change() 함수에 쓰이는 전역변수 
		$(function(){
			selectedSel2 = $("#sel2").val(); //처음 로드할때 이전에 선택한 지역명을 저장(바로 밑에 change() 함수와 순서 중요)
			
			$("#sel1").change(); //로드하고 나서 1차지역에 대한 2차 지역 리스트 표시
			var checked_option = $("input[name=checked_option]").val();
			var checkboxs = document.getElementsByName("check_option");
			
			for(var i=0; i<12; i++){
				if(checked_option.charAt(i)=='1'){
					checkboxs[i].checked = true;
					
					$("input[name=check_option]").eq(i).parent().css("background-color","orange");
				}
			}			
		});
		
		
		//---------------------------------------------------달력--------------------------------------------
		$("input[name=placeUseDate]" ).datepicker({
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
		    	yearSuffix: '년',
		    	minDate: 1
		    	
		 });
		
		
		//------------------------------------------------검색 버튼 누를시(submit)--------------------------------------------------
		$("form").submit(function(){ 
			
			//체크박스 옵션 값
			var check = "";
			$("input[name=check_option]").each(function(index,item){ 
				if($(item).is(":checked")){
					check += '1';
				}else{
					check += '0';
				}			
			});
			$("input[name=check_option2]").val(check); //서블릿으로 넘겨줄때는 check_option2를 넘겨주기 때문에
			
			if($("#sel1").val() != "" && $("#sel1").val() != '1차 지역명'){ //1차 지역명을 선택했는데
				if($("#sel2").val() == "2차 지역명"){ //2차 지역명을 선택하지 않은 경우
					alert('2차 지역명을 선택하세요');
					$("#sel2").focus();
					return false;
				}
			}
			
		});
		
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
		
		
	</script>
</body>
</html>