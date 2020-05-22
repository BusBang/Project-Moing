<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>

<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>

<!-- 버튼, 인풋 스타일 시트 -->
<link rel="stylesheet" type="text/css" href="/css/btn.css">
<link rel="stylesheet" type="text/css" href="/css/formInput.css">

<!-- jquery추가 -->
<script type="text/javascript"
   src="http://code.jquery.com/jquery-3.3.1.js"></script>
   
<!-- 다음API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>    
   
<script>
	/* 	카테고리 분류 셀렉트 옵션 */
	$(function () {
		$("#cateSel1").change(function() {
			var cateSel1 = $("#cateSel1").val();
			console.log(cateSel1);
 			$.ajax({
				url : "/categorySelect",
				data : {cateSel1 : cateSel1},
				type : "get",
				success : function(data) {
					var cateSel2 = $("#cateSel2");
					cateSel2.find("option").remove();
					for(var i=0; i<data.length; i++) {
						var name = data[i].name;
						var no = data[i].no;
						cateSel2.append("<option value='"+no+"'>"+name+"</option>");
					}
				},
				error : function() {
					console.log("error");
				}
			});
		});
	});
	$(function () {
		$("#countrySel1").change(function() {
			var countrySel1 = $("#countrySel1").val();
			console.log(countrySel1);
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
						countrySel2.append("<option value='"+no+"'>"+name+"</option>");
					}
				},
				error : function() {
					console.log("error");
				}
			});
		});
	});
</script>

    <style>
    	div {
    		box-sizing : border-box;
    	}
        #wrapper {
            width: 1280px;
            margin :0 auto;
        }
        #header, #footer {
            width:100%;
            height: 200px;
            background-color: gray;
        }
        #section {
            width: 100%;
            height: 100%;
        }
        
        #title {
        	width: 100%;
        	height : 170px;
        	text-align: center;
        	font-size : 2em;
         	font-weight: bold; 
        	line-height: 170px;
        }
        #sec1 {
        	width : 100%;
        	height : 500px;
        	box-sizing : border-box; 
        }
        #sec1-1, #sec1-2 {
        	height:500px;
        	float : left;
        }
        #sec1-1 {
        	width:50%;        
        }
        #sec1-2 {
        	width:50%;
        
        }
        #pic1 {
        	width : 75%;
        	height : 75%;
     		border : 1px solid gray;
     		margin : 0 auto;
     		margin-top : 30px;
        }
        #pic2 {
        	width : 100%;
        	height : 25%;
        	font-size : 1.3em;
        	line-height:80px;
        }
        #uploadBox {
        	background-color:  #ff9742;
   			border: 2px solid #ff9742;
            border-radius: 10px;
        	width: 90px;
        	height : 40px;
        	line-height : 40px;
        	font-size : 0.7em;
        	padding-left : 5px;
        	box-shadow : 1px 1px 3px gray;
        }
        #dateBtn, #addrSearchBtn {
       		font-weight : bold;
       	   	box-shadow : 1px 1px 3px gray;        
        }
        #sec3>input {
        	box-shadow : 1px 1px 3px gray;        
        }
        #uploadLabel {
        	display : inline-block;
        	text-align: center;
        	width: 90px;
        	height : 40px;
        	margin-left : 230px;
        	margin-top : 23px;
        	font-weight : bold;
        }
        #info {
        	font-size : 0.6em;
        	font-weight : bold;
    	}
       	#sec1-2, #sec2, #sec3 {
       		font-weight : bold;
       	}
       	#sec1-2>div {
       		width: 100%;
       		height : 16.6%;
       	}
       	#sec2 {
       		width : 100%;
       		overflow : hidden;
       		text-align : center;
       	}
       	#sec2Text {
       		width:100%;
       		height : 50px;
       		text-align : left;
       		padding-left : 30px;       		
       	}
       	#sec2Textarea {
       		width:100%;
       		padding-left :100px;
       		margin : 0 auto;
       	}
       	#sec1-2 input, #sec1-2 select {
       		margin : 5px;
       		margin-top : 10px;
       		margin-left : 10px;
     		box-shadow : 1px 1px 3px gray;
       	}
       	#sec3 {
       		width : 100%;
       		height : 100px;
       		line-height: 40px;
	       	text-align : center;
       	}
       	#sec3 input {
       		margin : 30px;
     		font-weight : bold;
       	}
		.cateSizing {
			width : 140px;
			height : 28px;
			padding-left : 10px;
		}
		.inputSizing {
			width : 300px;
		}
		#pic1 {
			background-image: url("/img/meetingDefault.jpg");
			background-size:100% 100%;
			background-repeat: no-repeat;
		}
		#peopleMinInput, #peopleMaxInput {
			width: 140px;
		}
		#nameInput {
			width : 140px;
		} 
		#sec3 input:hover, #uploadBox:hover, #addrSearchBtn:hover, #dateBtn:hover {
			font-weight : bold;
			cursor : pointer;
			color : #f0E0C0;
			border : 2px solid #404020;
			background-color : #404020;
		}
    </style>

</head>
<body>
    <div id="wrapper">
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
        <div id="section">
			<div id="title">
				모임 개최하기
			</div>
			<form id="frm" action="/moimInsert" method="post" onsubmit="return submitTest()"  enctype="multipart/form-data">
			<div id="sec1">
				<div id="sec1-1">
					<div id="pic1">
						<img id="img-view" width="478px" height="373px">
					</div>
					<div id="pic2">
						<label id="uploadLabel">
							<div id="uploadBox">
								<span>사진업로드</span>
							</div>
							<input type="file" id="uploadBtn" name="filename" style="display:none;" onchange="loadImg(this);">
						</label>
						<span id="info">
							메인 사진 등록하기						
						</span>
					</div>
				</div>
				<div id="sec1-2">
					<div id="cateDiv" style="width:24%; display:inline-block;">
						<span>*카테고리 분류</span><br>
						<select class="input-origin cateSizing" id="cateSel1" name="category1">
							<option value="">카테고리 대분류</option>
								<c:forEach items="${categoryList }" var="cate">
									<c:if test="${cate.categoryLevel == '1'}">
										<option value="${cate.categoryNo }">${cate.categoryName }</option>
									</c:if>		
								</c:forEach>					
						</select>
					</div>
					<div id="cateDiv" style="width:24%; display:inline-block;">
						<br>
						<select class="input-origin cateSizing" id="cateSel2" name="category2">
							<option value="">소분류</option>
						</select>
					</div>
					<div id="sectorDiv" style="width:24%; display:inline-block;" >
						<span>*모임 지역 분류</span><br>
						<select class="input-origin cateSizing" id="countrySel1" name="country1">
							<option value="">지역 대분류</option>
								<c:forEach items="${countryList }" var="country">
									<c:if test="${country.countryLevel == '1'}">
										<option value="${country.countryNo }">${country.countryName }</option>
									</c:if>		
								</c:forEach>
						</select>				
					</div>	
					<div id="sectorDiv" style="width:24%; display:inline-block;" >
						<br>
						<select class="input-origin cateSizing" id="countrySel2" name="country2">
							<option value="">소분류</option>
						</select>	
					</div>			
					<div id="titleDiv" style="width:49%; display:inline-block;">
						<br><span>*글제목</span><span id="titleCount" style="font-weight:500; font-size:0.8em;">(0 / 최대 30자)</span><br>
						<input id="titleInput" class="input-origin inputSizing" type="text" name="moimTitle"  required placeholder=" [모집]매주 토요일 오후에 배드민턴 치실 20대">					
					</div>
					<div id="nameDiv" style="width:49%; display:inline-block;">
						<span>*모임명</span><span id="nameCount" style="font-weight:500; font-size:0.8em;">(0 / 최대 8자)</span><br>
						<input id="nameInput" id="nameInput" class="input-origin inputSizing" type="text" name="moimName"  required placeholder=" ex) 배드민턴동호회">
						<span style="font-weight:300; font-size:0.5em;">모임명은 수정이 불가능합니다</span>					
					</div>
					<div id="peopleDiv" style="width:24%; display:inline-block;">
						<br><span>*모임 인원 (명)</span><br>
						<input class="input-origin" id="peopleMinInput" type="text" placeholder=" [최소 인원] ex) 5" required name="minPerson"><br>
					</div>
					<div id="peopleDiv" style="width:24%; display:inline-block;">
						<br>
						<input required class="input-origin" id="peopleMaxInput" type="text" placeholder=" [최대 인원] ex) 15" required name="maxPerson">
					</div>

					<div id="calDiv" style="width:49%; display:inline-block;">
						<span>*모임 개최일</span><br>
						<input required type="text" id="datepicker1" name="date" size="20" class="input-origin" readonly>
						<input type="button" class="btn-xsm-orange2 btn-xsm-orange1" id="dateBtn" value="달력보기" onclick="$('#datepicker1').datepicker('show');" />
					</div><br><br>
				    <div id="addrDiv">
						<span>*모임 장소</span>	<br>
							<!-- 우편번호가 들어가는 칸 -->
						<div>						
							<input type="text" name="addrCode" id="postCode" style="width:60px;display:inline-block;" class="input-origin" placeholder=" 우편번호" readonly> 
							<input type="text" name="addrRoad" id="roadAddr" style="width:250px;display:inline-block;" class="input-origin" placeholder=" 도로명주소" readonly>
							<button type="button" id ="addrSearchBtn" onclick="addrSearch();" class="btn-xsm-orange2 btn-xsm-orange1">주소검색</button>						
							<input type="hidden" name="addrJibun" id="jibunAddr" style="width:200px; display:inline-block;" type="text" class="input-origin" placeholder=" 지번주소" readonly>										
						</div>
						<div>
							<br><input id="detailAddr" name="addrDetail" style="width:330px;display:inline-block;" type="text" class="input-origin" placeholder=" 상세주소">										
						</div>
				    </div>					
				</div>
			</div>
			<hr>
			<div id="sec2">
				<div id="sec2Text">
					*모임 글 작성
				</div>
				<div id="sec2Textarea">
					<textarea rows="30" cols="150" id="ir1" name="content" style="margin-top:40px;" placeholder="내용을 입력하세요."></textarea>			
<!-- 				<textarea name="ir1" id="ir1" rows="30" cols="150" style="resize: none; margin-top : 40px;"></textarea> -->
				</div>
			</div>
			<hr>
			<div id="sec3">
				<input type="hidden" name="moimWriter" value="${sessionScope.member.memberId }">
				<input type="submit" class="btn-xsm-orange2 btn-xsm-orange1" id="save" value="글쓰기">
				<input type="reset" class="btn-xsm-orange2 btn-xsm-orange1" value="초기화">
			</div>
			</form>
        </div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    </div>
  
</body>
    <style>
    	#addrDiv>ul {
    		list-style: none;
    	}
    </style>

<script>
	//글자수 실시간 카운팅
	$(function() {
		$("#titleInput").keyup(function (){
			var content = $(this).val();
			$("#titleCount").html("("+content.length+" / 최대 30자)");
			
		    if (content.length > 30){
		        alert("최대 30자까지 입력 가능합니다.");
/* 		        $(this).val(content.substring(0, 30)); */
		        $('#titleCount').html("("+content.length+" / 최대 30자)");
		    }
		});
	});
	
	$(function() {
		$("#titleInput").click(function () {
			var content1 = $("#cateSel1").val();
			var content2 = $("#countrySel1").val();
			if(content1 =="" && content2 =="") {
				alert("모임, 지역 분류를 선택하셔야합니다.");
				$("#cateSel1").focus();
			} else if (content1 == "") {
				alert("모임 분류를 선택하셔야합니다.");
				$("#cateSel1").focus();				
			} else if (content2 == "") {
				alert("지역 분류를 선택하셔야합니다.");
				$("#countrySel1").focus();
			}
		});
		$("#nameInput").click(function () {
			var content1 = $("#cateSel1").val();
			var content2 = $("#countrySel1").val();
			if(content1 =="" && content2 =="") {
				alert("모임, 지역 분류를 선택하셔야합니다.");
				$("#cateSel1").focus();
			} else if (content1 == "") {
				alert("모임 분류를 선택하셔야합니다.");
				$("#cateSel1").focus();				
			} else if (content2 == "") {
				alert("지역 분류를 선택하셔야합니다.");
				$("#countrySel1").focus();
			}
		});
	});

	$(function() {
		$("#nameInput").keyup(function (){
			var content = $(this).val();
			$("#nameCount").html("("+content.length+" / 최대 8자)");
			
		    if (content.length > 8){
		        alert("최대 8자까지 입력 가능합니다.");
		        $('#nameCount').html("("+content.length+" / 최대 8자)");
		    }			
		});
	});

	function loadImg(f){
		console.log(f.files);
		if(f.files.length != 0 && f.files[0]!=0) {
			var reader = new FileReader();
			reader.readAsDataURL(f.files[0]);
			reader.onload = function(e) {
				$("#img-view").attr("src",e.target.result);
			}
		}else {
			$("#img-view").attr("src","");			
		}
	}
	
	////////////
	//Daum Address
	function addrSearch() {
		new daum.Postcode({
			oncomplete:function(data) { //창을 닫았을 때 실행되는 함수
				$("#postCode").val(data.zonecode);
				$("#roadAddr").val(data.roadAddress);
				$("#jibunAddr").val(data.jibunAddress);
			}
		}).open();
	}
</script>

<script type="text/javascript">
$(function(){
		
var oEditors = [];

// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "/smarteditor/SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
		
});
//저장버튼 클릭시 form 전송
$("#save").click(function(){
    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
    $("#frm").submit();
	});    
});
</script>
<!-- 달력 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- 부트스트랩 -->


<script>
///onsubmit

	function submitTest() {
		var titleInput = $("#titleInput").val()
		var nameInput = $("#nameInput").val();
		var peopleInput1 = $("#peopleMinInput").val();
		var peopleInput2 = $("#peopleMaxInput").val();
		var date = $("#datepicker1").val();
		var postCode = $("#postCode").val();
		var result = true; 
		if(titleInput == "") {
			result = false;
		}
		if(nameInput == "") {
			result = false;
		}
		if(peopleInput1 = "") {
			result = false;
		}
		if(peopleInput2 == "") {
			result = false;
		}
		if(date == "") {
			result = false;			
		}
		if(postCode == "") {
			result = false;
		}
		if(result == false) {
			alert("채우지 않은 항목이 있습니다. ");
		}
		return result;
	};

$(function() {
  $( "#datepicker1" ).datepicker({
	    dateFormat: 'yy-mm-dd',
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    showMonthAfterYear: true,
	    changeMonth: true,
	    changeYear: true,
	    minDate: 1,
	    yearSuffix: '년'
  });
});

</script> 
</html>