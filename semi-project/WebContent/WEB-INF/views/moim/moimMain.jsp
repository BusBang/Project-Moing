<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet" type="text/css" href="/css/btn.css">
<link rel="stylesheet" type="text/css" href="/css/formInput.css">

</head>
    <style>
   
		.wrapper {
			margin:0 auto;
			width : 1280px;
			overflow:hidden;
		}
        #title {
        	width: 100%;
        	height : 170px;
        	text-align: center;
        	font-size : 2em;
         	font-weight: bold; 
        	line-height: 170px;
/*         	background-color : #ff9742; */
        }
        
        #searchBar1 {
        	width:100%;
        	height : 130px;
        	font-size : 1.1em;
        	font-weight : bold;
        }
        #searchBar1 select {
        	margin : 15px;
        	width: 160px;
      		height: 40px;  
			box-shadow : 1px 1px 3px gray;
        }
        #searchBar1>div {
        	width:230px;
        	height:100px;
        	display : inline-block;
        	margin-left:40px;
        	margin-right:40px;
        }
        #searchBar2Div {
        	width:100%;
        	height:120px;
        }
        #searchBar2 {
        	margin:0 auto;
			width: 600px;
			margin-top : 20px;
        	font-size : 1.1em;
        	font-weight : bold;
        }
        #searchBar2>span {
        	padding-left : 20px;
        }
        #searchCate1, #searchCate2, #searchPlace1, #searchPlace2 {
			margin : 0 auto;
			margin-top : 30px;
        }
        .inputSizing {
			width : 500px;      
			height: 40px; 
			margin : 10px; 
			margin-right : -7px;
			border-top-right-radius: 0px;
			border-bottom-right-radius: 0px;
			box-shadow : 1px 1px 3px gray;
        }
        #contentDiv {
        	width:100%;
        	height:850px;
        	margin : 0 auto;
        }
        #contentDiv>div {
        	width:22.5%;
        	height : 260px;
        	margin : 12.5px;
        	display:inline-block;
        	box-sizing : border-box;
        	box-shadow: 2px 2px 5px grey;

        }
        #conImg {
        	width : 100%;
        	height : 170px;
        	box-shadow: 1px 1px 2px grey;
        }
        #conTitle {
        	width: 280px;
        	height : 50px;
        	font-weight : bold;
        	font-size : 1.3em;
        	padding : 5px;
			overflow:hidden;
			text-overflow:ellipsis;

        }
        #selectDiv {
        	width : 100%;
        	height : 30px;
        }
        
        .spanBold {
        	font-weight: bold;
        	padding-left : 2px;
        }
        .noneStyle {
        	border-style : none;
        	background-color : white;
        	font-size : 1.1em;
      		cursor: pointer;
      		float : right;
        }
        #btnDiv {
        	width : 100%;
        	height : 70px;
        }
        #writeBtn {
        	font-size : 1.1em;
        	font-weight : bold;
        	float : right;
        	margin : 10px;
        	cursor : pointer;
        	line-height : 45px;
        	box-shadow : 2px 2px 5px gray;
        }
        #searchBtn {
        	border-top-left-radius : 0px;
        	border-bottom-left-radius : 0px;
        	font-weight : bold;
            height : 46px;
            width : 84px;
			box-shadow : 2px 2px 2px gray;
        }
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
        #contents:hover {
        	cursor : pointer;
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
		#searchbarDiv {
			width :100%;
			height : 300px;
		}
/*         //hover */
		#searchBtn:hover, #writeBtn:hover {
			font-weight : bold;
			cursor : pointer;
			color : #f0E0C0;
			border : 2px solid #404020;
			background-color : #404020;
		}
		#lineUpBtn:hover {
			color : #ff9742;
		}
		      /*확대 호버*/
      #conImg {
         overflow:hidden;
      }
      img:hover {
         transition: transform 1s;
         transform : scale(1.2);
      }

    </style>
    <script>
    	function search() {
    		alert("클릭");
    	};
    	function writeFrm() {
    		alert("글쓰러가기");
    	};
    	function selectCon() {
    		alert("페이지이동");
    	};
    	function selectLineUp(value){
    		
    	}

    </script>
<script>
	/* 	카테고리 분류 셀렉트 옵션 */
	$(function () {
		$("#cateSel1").change(function() {
			var cateSel1 = $("#cateSel1").val();
 			$.ajax({
				url : "/categorySelect",
				data : {cateSel1 : cateSel1},
				type : "get",
				success : function(data) {
					var cateSel2 = $("#cateSel2");	
			 		cateSel2.find("option").remove();
			 		var cateSel2Opt = '${cateSel2}';
					for(var i=0; i<data.length; i++) {
						var name = data[i].name;
						var no = data[i].no;
						if(cateSel2Opt == data[i].no) {
							cateSel2.append("<option value='"+no+"' selected>"+name+"</option>");														
						}else {
							cateSel2.append("<option value='"+no+"'>"+name+"</option>");							
						}
					}					
				},
				error : function() {
					console.log("error");
				}
			});
		});
	});
	$(function () {
 		var conSel2Opt = '${conSel2}';
		$("#countrySel1").change(function() {
			var countrySel1 = $("#countrySel1").val();
 			$.ajax({
				url : "/countrySelect",
				data : {countrySel1 : countrySel1},
				type : "get",
				success : function(data) {
					var countrySel2 = $("#countrySel2");
					countrySel2.find("option").remove();
					for(var i=0; i<data.length; i++) {
						var name = data[i].name;
						var no = data[i].no;
						if(conSel2Opt == data[i].no) {
							countrySel2.append("<option value='"+no+"' selected>"+name+"</option>");														
						}else {
							countrySel2.append("<option value='"+no+"'>"+name+"</option>");							
						}
					}
				},
				error : function() {
					console.log("error");
				}
			});
		});
	});

</script>
<body>
	<div class="wrapper">
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section class = "container">
	<div id="sectionDiv">
	
	
		<div id="title">
			나의 모임을 찾아보세요!
		</div>
		<form action = "/moimSearch" method="get">
		<div id=searchBarDiv>
			<div id="searchBar1">
				<div id="searchCate1">
					<span>모임 카테고리 대분류</span><br>
					<select class="input-origin"  id="cateSel1" name="category1">
						<option value="">카테고리 대분류</option>
							<c:forEach items="${categoryList }" var="cate">
								<c:if test="${cate.categoryLevel == '1'}">
									<option value="${cate.categoryNo }" >
										${cate.categoryName }
									</option>
								</c:if>		
							</c:forEach>	
					</select>
				</div>
				<div id="searchCate2">
					<span>모임 카테고리 소분류</span><br>
					<select class="input-origin cateSizing cateSel2" id="cateSel2" name="category2">
						<option value="">소분류</option>
					</select>
				</div>
				<div id="searchPlace1">
					<span>모임 지역 분류</span><br>
					<select class="input-origin cateSizing" id="countrySel1" name="country1">
						<option value="">지역 대분류</option>
							<c:forEach items="${countryList }" var="country">
								<c:if test="${country.countryLevel == '1'}">
									<option value="${country.countryNo }">${country.countryName }</option>
								</c:if>
							</c:forEach>
					</select>	
				</div>
				<div id="searchPlace2">
					<span>모임 지역 소분류</span><br>
					<select class="input-origin cateSizing" id="countrySel2" name="country2">
						<option value="">지역 소분류</option>
					</select>
				</div>
			</div>
			<div id="searchBar2Div">
				<div id="searchBar2">
					<span>키워드 검색</span><br>
					<input type="text" name="keywordSearch" class="input-origin inputSizing" placeholder=" ex)배드민턴">
					<input type="submit" id="searchBtn" class="btn-xsm-orange1 btn-xsm-orange2" value="검 색">
				</div>
			</div>
		</div>
		<hr>
		<div id="selectDiv">
			<c:if test="${lineUp eq '최신순' }">
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="이름순"  onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=이름순'">이름순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="인기순" onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=인기순'">인기순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="최신순" style='cursor:default; color:#ff9742; font-weight:bold;' disabled>최신순</button>
			</c:if>
			<c:if test="${empty lineUp }">
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="이름순" onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=이름순'">이름순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="인기순" onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=인기순'">인기순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="최신순" style='cursor:default; color:#ff9742; font-weight:bold;'  disabled>최신순</button>
			</c:if>
			<c:if test="${lineUp eq '이름순' }">
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="이름순" style='cursor:default; color:#ff9742; font-weight:bold;'  disabled>이름순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="인기순" onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=인기순'">인기순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="최신순" onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=최신순'">최신순</button>
			</c:if>
			<c:if test="${lineUp eq '인기순' }">
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="이름순"  onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=이름순'">이름순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="인기순" style='cursor:default; color:#ff9742; font-weight:bold;' disabled>인기순</button>
				<button class="noneStyle" id="lineUpBtn" name="lineUp" value="최신순" onclick="location.href='/moimSearch?category1=${category1}&category2=${category2}&country1=${country1}&country2=${country2}&keywordSearch=&lineUp=최신순'">최신순</button>
			</c:if>
		</div>
		</form>
		<hr>
		<div id="btnDiv">
			<c:if test="${not empty sessionScope.member.memberId }">
				<button id="writeBtn" class="btn-sm-orange2 btn-sm-orange1" onclick="location.href='/moimWriteFrm'">모임개최하기</button>			
			</c:if>
		</div>
		<div id="contentDiv">
			<c:forEach items="${meetingList}" var="m">
				<div id="contents" onclick="location.href='/meetingDetail?meetingNo=${m.meetingNo}&reqPage=1'">
					<div id="conImg">
					<c:if test="${not empty m.meetingFilepath }">
						<img style="width:100%; height:100%;" src="/upload/${m.meetingFilepath }">					
					</c:if>
					<c:if test="${empty m.meetingFilepath}">
						<img style="width:100%; height:100%;" src="/img/meetingDefault.jpg">					
					</c:if>
					</div>
					<input type="hidden" name="${m.meetingNo }" value="${m.meetingNo }">
					<div id="conTitle">${m.meetingTitle }</div>
					<div id="conContents">
						<span style="margin-left:5px;" class="spanBold">모임일시 : </span>
						<span>${m.meetingDate}</span>
						<span style="margin-left:35px;" class="spanBold">Like : </span >
						<span>${m.likeCount }</span>
					</div>
				</div>				
			</c:forEach>
		</div>
		<hr>
		<div id="pageDiv">
				${pageNavi }
		</div>
		</div>
	</section>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>

	</div>
	<script>
	var cateSel1 = '${cateSel1}';
	var conSel1 = '${conSel1}';
	var cateSel2 = '${cateSel2}';	
	var conSel2 = '${conSel2}';	
 	$(function () {	
  		if(cateSel1 != "") {
			for(var i=0; i<$("#cateSel1").children().length; i++) {
				if(cateSel1==$("#cateSel1").children().eq(i).val()) {
					$("#cateSel1").children().eq(i).prop("selected", true);
				}
			} 				
 		}

 		if(conSel1 != "") {
			for(var i=0; i<$("#countrySel1").children().length; i++) {
				if(conSel1==$("#countrySel1").children().eq(i).val()) {
					$("#countrySel1").children().eq(i).prop("selected", true);
				}
			} 				
 		}
 		$("#cateSel1").change();
 		$("#countrySel1").change();
	}); 
 	
	</script>
</body>
</html>