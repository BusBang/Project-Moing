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
        <div class="pageMainTitle">Admin Page</div>
        <div class="pageSubTitle">장소 게시글 전체 조회</div>
        <div class="myPlaceList">
            
            <div class="places">
                <table id="myPlace">
                
                    <tr>
                        <th width="5%">NO</th>
                        <th width="40%">Name</th>
                        <th>District</th>
                        <th>Type</th>
                        <th width="30%">MyPlace</th>
                    </tr>
                    
                    
                    <tr>
                        <td colspan="5" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    
                    <input type="hidden" id="reqPagePlace" name="reqPagePlace" value="${reqPagePlace }">
                    
                    <c:forEach items="${list }" var="l">
                    	<tr>
		                    
	                        <td>${l.rnum }</td>
	                        <td><a href="/placeDetailFrm?memberId=${sessionScope.member.memberId }&placeNo=${l.pi.placeNo}">${l.pi.placeTitle }</a></td>
	                        <td>${l.pi.countryName }</td>
	                        <td>${l.pi.placeKindName }</td>
	                        <td>
	                            <button onclick="deletePlace('${l.pi.placeNo}');" class="btn-xsm-green1">삭제</button>
	                            <button class="btn-xsm-green1 bookingBtn">예약</button>
	                            <input type="hidden" id="placeNo" name="placeNo" value="${l.pi.placeNo }">
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="5" style="border-bottom: 0.5px solid lightgray;"></td>
	                    </tr>
	                    
	                    <tr class="reserve" style="display:none;">
	                        <td colspan="5" class="bookingFloor">
	                            <div class="bookingList">
	                                <div class="bookingTitle">예약 현황</div>
	                                <table class="bookingTable">
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
	                            </div>
	                        </td>
	                    </tr>
                    </c:forEach>
                   
                </table>
                               
            </div>
            
        </div>
    
    </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    
    
    <script>
    	
    	
    	$(".bookingBtn").click(function(){
        	var index = $(".bookingBtn").index($(this));
        	
        	if($(".reserve").eq(index).css("display")=="none"){
        		
	        	$(".reserve").eq(index).css("display","table-row");
	       		var reqPagePlace = $("#reqPagePlace").val();
	       		var reqPagePlaceBooking = "1";
	       		var placeNo = $("input[name=placeNo]").eq(index).val();
	        		
	        	$.ajax({
	        		
	        		url:"/bookingList",
	        		data: {placeNo:placeNo, reqPagePlace:reqPagePlace, reqPagePlaceBooking:reqPagePlaceBooking},
	        		type: "get",
	        		success : function(data){
						var html = "";
						var navi = data[0].pageNavi;
						console.log(navi);
						console.log(data[0].rnum);
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
	        				
	        				html+="<tr class='reserveList'><td>"+rnum+"</td>";
	                        html+="<td>"+bookingDate+"</td>";
	                        html+="<td>"+visitDate+"</td>";
	                        html+="<td><a href='/meetingDetail?meetingNo="+meetingNo+"&reqPage=1'>"+meetingName+"</a></td>";
	                        html+="<td>"+sit+"</td>";
	                        html+="<td><button class='btn-xsm-green1' href='/bookingCancel?bookingNo="+bookingNo+"'>예약취소</button></td></tr>";
	                        html+="<tr class='reserveList'><td colspan='6' style='border-bottom: 0.5px solid lightgray;'></td></tr>";
	        			}
	        			console.log(html);
	        			$(".bookingTable").eq(index).append(html);
	        			$(".bookingTable").eq(index).after(navi);
        			
        		},
        		error : function(){
        			console.log("실패");
        		}
        	});
       	}else{
       		$(".reserve").eq(index).css("display","none");
       		$(".reserveList").remove();
       		$(".bookingTable").eq(index).nextAll().remove();
       	}
        	
    	});
    	
    
    </script>
</body>
</html>