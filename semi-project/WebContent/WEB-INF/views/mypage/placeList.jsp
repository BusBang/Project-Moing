<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="/css/btn.css" rel="stylesheet" type="text/css">
    <link href="/css/formInput.css" rel="stylesheet" type="text/css">
    <link href="/css/letterListCss.css" rel="stylesheet" type="text/css">
    
    <style>
        
        .bookingFloor{
            background-color: #F4ECDE;
            width: 100%;
            text-align: center;
        }
    
        .bookingList{
            width: 85%;
            margin: 30px auto;
            
        }
        .bookingTitle{
            font-size: 17px;
            font-weight: 700;
            text-align: left;
            margin-left: 20px;
            margin-bottom: 15px;
            color: #ff9742;
        }
        
        .bookingTable th{
            font-size: 14px;
            color: #808060;
        }
        section a{
        	text-decoration: none;
        	display: block;
        	color: #404020;
        }
    
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
     <section class="container">
    <div class="letter-wrapper">
        <div class="pageMainTitle">마이페이지</div>
        <c:if test="${sessionScope.member.memberId eq id }">
        <div class="pageSubTitle">내 장소</div>
        </c:if>
        <c:if test="${sessionScope.member.memberId ne id }">
        <div class="pageSubTitle">${id} 님의 장소</div>
        </c:if>
        <div class="myPlaceList">
            <div class="letterBtn">
                <div class="btn-long-orange1 or1 focusBtn"><a href="javascript:void(0)" onclick="listfunc('${sessionScope.member.memberId }','1','1','0');" id="kk">My Booking</a></div>
                <div class="btn-long-orange1 or2"><a href="javascript:void(0)" onclick="listfunc('${sessionScope.member.memberId }','1','1','1');">My Place</a></div>
            </div>
            <div class="places">
                <table id="myPlace">
                
                
                	<c:if test="${num==1 }"><!-- 내 장소 제공 리스트 페이지 -->
                    <tr>
                        <th width="5%">NO</th>
                        <th width="40%">Name</th>
                        <th>District</th>
                        <th>Type</th>
                        <th width="30%">MyPlace</th>
                    </tr>
                    </c:if>
                    
                    
                    <c:if test="${num==0 }"><!-- 내 장소 예약 리스트 페이지 -->
                    	<tr>
	                        <th width="5%">NO</th>
	                        <th width="40%">Name</th>
	                        <th>District</th>
	                        <th>Type</th>
	                        <th width="30%">My Booking</th>
                    	</tr>
                    </c:if>
                    
                    
                    <tr>
                        <td colspan="5" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    
                    <input type="hidden" id="reqPagePlace0" name="reqPagePlace0" value="${reqPagePlace0 }">
                    <input type="hidden" id="reqPagePlace1" name="reqPagePlace1" value="${reqPagePlace1 }">
                    <input type="hidden" id="memberId" name="memberId" value="${sessionScope.member.memberId }">
                   
                   
                   
                    <c:if test="${num==0 }"><!-- 장소 별 내 예약 리스트 -->
                    
                    	<c:forEach items="${list0 }" var="l0">
	                    	<tr>
		                        <td>${l0.rnum }</td>
		                        <td><a href="/placeDetailFrm?memberId=${sessionScope.member.memberId }&placeNo=${l0.pi.placeNo}">${l0.pi.placeTitle }</a></td>
		                        <td>${l0.pi.countryName }</td>
		                        <td>${l0.pi.placeKindName}</td>
		                        <td>
		                             <button class="btn-xsm-green1 bookingBtn0">예약</button>
		                            <input type="hidden" id="placeNo" name="placeNo" value="${l0.pi.placeNo }">
		                        </td>
	                    	</tr>
	                    	<tr>
	                        	<td colspan="5" style="border-bottom: 0.5px solid lightgray;"></td>
	                    	</tr>
	                    	
	                    	
		                    <tr class="reserve0" style="display:none;"><!-- 예약 현황 -->
		                        <td colspan="5" class="bookingFloor">
		                        
		                            <div class="bookingList bk1">
		                                <div class="bookingTitle">예약 현황</div>
		                                <table class="bookingTable btb0" id="booking0Table">
		                                    <tr>
		                                        <th>NO</th>
		                                        <th>예약 날짜</th>
		                                        <th>방문 날짜</th>
		                                        <th width="40%">모임 이름</th>
		                                        <th>상태</th>
		                                        <th>취소</th>
		                                    </tr>
		                                    <tr class="boldLine">
		                                        <td colspan="6" style="border-bottom: 3px solid #204000;"></td>
		                                    </tr>	                                   
		                                </table>
		                                <div id="reserveNavi0">
	                                
	                                </div>
		                            </div>
		                            
		                        </td>		                        
		                    </tr>
		                    
		                    
	                    </c:forEach>
                    </c:if>
                    
                    
                    <c:if test="${num==1 }"><!-- 내 장소 제공 리스트 -->
                    <c:forEach items="${list1 }" var="l1">
                    	<tr>
	                        <td>${l1.rnum }<input type="hidden" name="places" value="${l1.pi.placeNo }"></td>
	                        <td><a href="/placeDetailFrm?memberId=${sessionScope.member.memberId }&placeNo=${l1.pi.placeNo}">${l1.pi.placeTitle }</a></td>
	                        <td>${l1.pi.countryName }</td>
	                        <td>${l1.pi.placeKindName }</td>
	                        <td>
	                            <button onclick="location.href='/updatePlaceFrm?updateNo=${l1.pi.placeNo}'" class="btn-xsm-green1">수정</button>
	                            <button onclick="deletePlace('${l1.pi.placeNo}');" class="btn-xsm-green1">삭제</button>
	                            <button class="btn-xsm-green1 bookingBtn1">예약</button>
	                            <input type="hidden" id="placeNo1" name="placeNo1" value="${l1.pi.placeNo }">
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="5" style="border-bottom: 0.5px solid lightgray;"></td>
	                    </tr>
	                    
	                    <tr class="reserve1" style="display:none;">
	                        <td colspan="5" class="bookingFloor">
	                            <div class="bookingList bk1">
	                                <div class="bookingTitle">예약 현황</div>
	                                <table class="bookingTable btb1" id="booking1Table">
	                                    <tr>
	                                        <th>NO</th>
	                                        <th>예약 날짜</th>
	                                        <th>방문 날짜</th>
	                                        <th width="40%">모임 이름</th>
	                                        <th>상태</th>
	                                        <th>취소</th>
	                                    </tr>
	                                    <tr>
	                                        <td colspan="6" style="border-bottom: 3px solid #204000;"></td>
	                                    </tr>
	                                </table>
	                                <div id="reserveNavi1">
	                                
	                                </div>
	                            </div>
	                        </td>
	                    </tr>
                    </c:forEach>
                    	
	                    
                    </c:if>
                </table>
                               
            </div>
            
        </div>
    
    </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
    
    <script>
    function deletePlace(placeNo){
		$.ajax({
			url : "/deletePlace",
			data : {deleteNo:placeNo},
			type : "get",
			success : function(data){
				if(Number(data) > 0){
					$("input[name=places]").each(function(i, item){
						if($(item).val() == Number(placeNo)){
							$(item).parent().parent().remove();
						}
					});
    				alert("게시글이 삭제 되었습니다.");    					
				}
			}
		});
	};
    	$(function(){
    		var num = "${num}";
            if(num==0){
            	$(".or1").addClass("focusBtn");
            	$(".or2").removeClass("focusBtn");
            }else{
            	$(".or2").addClass("focusBtn");
            	$(".or1").removeClass("focusBtn");
            }
            
    	});
    	
    	$(".bookingBtn1").click(function(){
        	var index = $(".bookingBtn1").index($(this));
        	
        	if($(".reserve1").eq(index).css("display")=="none"){
        		
	        	$(".reserve1").eq(index).css("display","table-row");
	       		var memberId = $("#memberId").val();
	       		var reqPagePlace1 = $("#reqPagePlace1").val();
	       		var reqPagePlace1Booking = "1";
	       		var placeNo = $("input[name=placeNo1]").eq(index).val();
	        	
	        	$.ajax({
	        		
	        		url:"/bookingList1",
	        		data: {memberId: memberId, placeNo:placeNo, reqPagePlace1:reqPagePlace1, reqPagePlace1Booking:reqPagePlace1Booking},
	        		type: "get",
	        		success : function(data){
						var html = "";
						var navi = data[0].pageNavi;
						console.log(navi);
	        			for(var i=0; i<data.length;i++){
	        				var rnum = data[i].rnum;
	        				var bookingDate = data[i].bookingDate;
	        				var meetingName = data[i].meetingName;
	        				var visitDate = data[i].visitDate;
	        				var bookingCancel = data[i].bookingCancel;
	        				var meetingNo = data[i].meetingNo;
	        				var placeNo = data[i].placeNo;
	        				var bookingNo = data[i].bookingNo;
	        				var today = data[i].today;
	        				var sit = data[i].sit;
	        				
	        				if(bookingCancel=='예약'){
		        				html+="<tr class='reserve1List'><td>"+rnum+"</td>";
		                        html+="<td>"+bookingDate+"</td>";
		                        html+="<td>"+visitDate+"</td>";
		                        html+="<td><a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage=1'>"+meetingName+"</a></td>";
		                        html+="<td>"+sit+"</td>";
		                        html+="<td><button class='btn-xsm-green1' href='/bookingCancel?bookingNo="+bookingNo+"'>예약취소</button></td></tr>";
		                        html+="<tr class='reserve1List'><td colspan='6' style='border-bottom: 0.5px solid lightgray;'></td></tr>";
	        					
	        				}else{
		        				html+="<tr class='reserve1List'><td>"+rnum+"</td>";
		                        html+="<td>"+bookingDate+"</td>";
		                        html+="<td>"+visitDate+"</td>";
		                        html+="<td><a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage=1'>"+meetingName+"</a></td>";
		                        html+="<td>취소된 예약</td>";
		                        html+="<td></td></tr>";
		                        html+="<tr class='reserve1List'><td colspan='6' style='border-bottom: 0.5px solid lightgray;'></td></tr>";

	        				}
	        				
	        			}
	        			$(".btb1").eq(index).append(html);
	        			$(".btb1").eq(index).after(navi);
        			
        		},
        		error : function(){
        			console.log("실패");
        		}
        	});
       	}else{
       		$(".reserve1").eq(index).css("display","none");
       		$(".reserve1List").remove();
       		$(".btb1").eq(index).nextAll().remove();
       	}
        	
    	});
    	
    	
    	
    	
    	
    	
        $(".bookingBtn0").click(function(){
        	var index = $(".bookingBtn0").index($(this));
        	
        	if($(".reserve0").eq(index).css("display")=="none"){
        		
	        	$(".reserve0").eq(index).css("display","table-row");
	       		var memberId = $("#memberId").val();
	       		var reqPagePlace0 = $("#reqPagePlace0").val();
	       		var reqPagePlace0Booking = "1";
	       		var placeNo = $("input[name=placeNo]").eq(index).val();
	        		
	        	$.ajax({
	        		
	        		url:"/bookingList0",
	        		data: {memberId: memberId, placeNo:placeNo, reqPagePlace0:reqPagePlace0, reqPagePlace0Booking:reqPagePlace0Booking},
	        		type: "get",
	        		success : function(data){
						var html = "";
						var navi = data[0].pageNavi;
						console.log(navi);
	        			for(var i=0; i<data.length;i++){
	        				var rnum = data[i].rnum;
	        				var bookingDate = data[i].bookingDate;
	        				var meetingName = data[i].meetingName;
	        				var visitDate = data[i].visitDate;
	        				var bookingCancel = data[i].bookingCancel;
	        				var meetingNo = data[i].meetingNo;
	        				var placeNo = data[i].placeNo;
	        				var bookingNo = data[i].bookingNo;
	        				var today = data[i].today;
	        				var sit = data[i].sit;
	        				
	        				html+="<tr class='reserve0List'><td>"+rnum+"</td>";
	                        html+="<td>"+bookingDate+"</td>";
	                        html+="<td>"+visitDate+"</td>";
	                        html+="<td><a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage=1'>"+meetingName+"</a></td>";
	                        html+="<td>"+sit+"</td>";
	                        html+="<td><button class='btn-xsm-green1' href='/bookingCancel?bookingNo="+bookingNo+"'>예약취소</button></td></tr>";
	                        html+="<tr class='reserve0List'><td colspan='6' style='border-bottom: 0.5px solid lightgray;'></td></tr>";
	        			}
	        			$(".btb0").eq(index).append(html);
	        			$(".btb0").eq(index).after(navi);
        			
        		},
        		error : function(){
        			console.log("실패");
        		}
        	});
       	}else{
       		$(".reserve0").eq(index).css("display","none");
       		$(".reserve0List").remove();
       		$(".btb0").eq(index).nextAll().remove();
       	}
        	
    	});
        
        
    	function listfunc(memberId, reqPagePlace0, reqPagePlace1, num){
        	location.href="/myPlaceList?reqPagePlace0="+reqPagePlace0+"&reqPagePlace1="+reqPagePlace1+"&memberId="+memberId+"&num="+num;
        	
        }
    	
    	function funcBookingList(num, placeNo, memberId, reqPagePlace0, reqPagePlace1, reqPagePlace0Booking, reqPagePlace1Booking){
    		location.href="/bookingList1?num="+num+"&placeNo="+placeNo+"&memberId="+memberId+"&reqPagePlace0="+reqPagePlace0+"&reqPagePlace1="+reqPagePlace1+"&reqPagePlace0Booking="+reqPagePlace0Booking+"&reqPagePlace1Booking="+reqPagePlace1Booking;
    		
    	}
    
    </script>
</body>
</html>