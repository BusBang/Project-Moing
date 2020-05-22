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
        .btn-xsm-green1{
            height: 30px;
            line-height: 27px;
            font-weight: 700;

        }
        .btnG{
            width: 100px;
        }
        .mymeetings>tr>td:last-children, .mymeetings>tr>th{
            width: 200px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>	
	<section class="container">
    <div class="letter-wrapper">
        <div class="pageMainTitle">마이페이지</div>
        <c:if test="${sessionScope.member.memberId eq id }">
        <div class="pageSubTitle">내 모임</div>
        </c:if>
        <c:if test="${sessionScope.member.memberId ne id }">
        <div class="pageSubTitle">${id} 님의 모임</div>
        </c:if>
        <div class="meetingList">
            <div class="meetings">
            <form id="allLetter">
            	<input type="hidden" name="meetingNo">
            	<input type="hidden" name="letterSendMemberId">
            	<input type="hidden" name="to" value="1">
            </form>
                <table id="mymeetings">
                    <tr>
                        <th>NO</th>
                        <th>TITLE</th>
                        <th>MEETING</th>
                        <th>Category</th>
                        <th>Country</th>
                        <th width="250px">MY MEETING</th>
                    </tr>
                    <tr>
                        <td colspan="6" style="border-bottom: 3px solid #ff9742;"></td>
                    </tr>
                    
                    <c:forEach items="${list }" var="l">
                    	<tr>
                    		<td>${l.meetingRNum }</td>
                    		<td>
                    			<a href="/meetingDetail?reqPage=1&meetingNo=${l.meetingInfo.meetingNo }" class="titleBlock">${l.meetingInfo.meetingTitle }</a>
                    		</td>
                   			<td>${l.meetingInfo.meetingName }</td>
                    		<td>${l.meetingInfo.categoryName }</td>
                    		<td>${l.meetingInfo.countryName }</td>
                    		<td>
       
	                    		<c:if test="${sessionScope.member.memberId == l.meetingInfo.bossId || sessionScope.member.memberId eq 'admin' }">
	                    			<button onclick="updateMeeting('${l.meetingInfo.meetingNo }')" class="btn-xsm-green1">수정</button>
	                            	<button onclick="deleteMeeting('${l.meetingInfo.meetingNo }');" class="btn-xsm-green1">삭제</button>
	                            	<button onclick="funcAllLetter('${sessionScope.member.memberId}','${l.meetingInfo.meetingNo }');" class="btn-xsm-green1 btnG">전체쪽지</button>
	                            </c:if>
                    		</td>
                    	</tr>
                    	<tr>
                        	<td colspan="6" style="border-bottom: 0.5px solid lightgray;"></td>
                        </tr>
                    </c:forEach>
                    
                </table>
                
                <div id="pageNaviGet" class="letterNavi">${pageNavi }</div><!-- 내 모임 페이지 네비 -->
                
                <div class="searchGetDiv" class="letterSearch"><!-- 모임 검색창 -->
                	<form action="/searchMeetingKeyword" id="searchGetDiv">
						<c:if test="${opt eq 'memberId'}">
							 <select name="opt">
		                        <option class="opt" value="memberId" selected>모임장 아이디</option>
		                        <option class="opt" value="meetingName">제목</option>
		                   </select>
						</c:if>
						<c:if test="${opt eq 'meetingName'}">
							<select name="opt">
		                        <option class="opt" value="memberId">모임장 아이디</option>
		                        <option class="opt" value="meetingName" selected>제목</option>
		                   </select>
						</c:if>
						<c:if test="${empty opt}">
							
		                       <select name="opt">
		                        <option class="opt" value="memberId" selected>모임장 아이디</option>
		                        <option class="opt" value="meetingName">제목</option>
		                   </select>
						</c:if>
						<input type="text" name="keyword" class="input-origin" value="${keyword }">
	                   <input type="hidden" name="memberId" value="${sessionScope.member.memberId }">
	                   <input type="hidden" name="reqPage" value="1">
	                   <button class="btn-xsm-green2 searchBtn" type="submit">검색</button>
					</form>
                </div>
            </div>
            
        </div>
    
    </div>
    </section>
 <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>   
    
    
    <script>
    	function funcAllLetter(memberId, meetingNo){
    		var url="/letterWrite";
    		var title="letterWrite";
    		var status = "left=500px, top=100px, width=470px, height=550px, menubar=no, status=no, scrollbars=yes";
        	var popup = window.open("", title, status);
        	$("input[name=meetingNo]").val(meetingNo);
        	$("input[name=letterSendMemberId]").val(memberId);
			$("#allLetter").attr("action",url);
			$("#allLetter").attr("method","post");
			$("#allLetter").attr("target",title);//새로 열린 popup창과 form태그를 연결
			$("#allLetter").submit();
    	}
    	function updateMeeting(meetingNo){
    		if(confirm("모임을 수정하시겠습니까?")){
    	         location.href="/moimModifyFrm?meetingNo="+meetingNo;

   	      }
    	}
    	function deleteMeeting(meetingNo){
    		if(confirm("모임을 삭제하시겠습니까?")){
    			location.href="/meetingDelete?meetingNo="+meetingNo;
   	         	alert("["+meetingNo+"]번 모임의 삭제가 완료되었습니다. ");
   	         }
    	}   
    </script>
</body>
</html>