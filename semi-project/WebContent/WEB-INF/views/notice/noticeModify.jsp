<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="btn.css">
<link rel="stylesheet" href="formInput.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<script type="text/javascript" src="/smart/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript"> 
$(function(){
	   var oEditors = [];
	   nhn.husky.EZCreator.createInIFrame({
	      oAppRef:oEditors,
	      elPlaceHolder:"ir1",
	      sSkinURI: "/smart/SmartEditor2Skin.html",
	      
	         htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
	            bUseToolbar : true,
	            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
	         	bUseVerticalResizer : true, 
	         // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
	            bUseModeChanger : true 
	         }, 
	         fCreator: "createSEditor2"
	      });
	   
	   function submitContents(elClickedObj) {
	       // 에디터의 내용이 textarea에 적용된다.
	       oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	    
	       // 에디터의 내용에 대한 값 검증은 이곳에서
	       // document.getElementById("textAreaContent").value를 이용해서 처리한다.
	     

	   }
	   $("#save").click(function(){
			  oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", [ ]);
			  $("#frm").submit();
		});
});


</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section style="margin:0 auto; width:1280px;">
		<form action="/noticeModify" method="post" id="updateFrm" id="frm" style="margin:0 auto;">
			<div class="content" style="width:1280px; margin: 0 auto; text-align:center;">
				<h1>공지 수정 사항</h1>
				<hr>
				<input type="hidden" name="noticeNo" value="${n.noticeNo }">
				<div style="width: 100%; margin: 0 auto;">
					<label><span>제목</span>
					<input style="width:400px;"type="text" id="noticeTitle" name="noticeTitle" value="${n.noticeTitle }"></label>
				</div>
				<br><br>
				<div style="width: 1280px; margin-left: 260px;">
					<textarea id="ir1" name="content" cols="100" rows="10"
						placeholder="공지사항 내용을 입력하세요." style="width: 800px; height: 600px; resize: none;"
						>${n.noticeContent }</textarea>
				</div>
				<br><br>
				<div style="width: 500px; margin: 0 auto;">
				<button class="btn btn-warning" type="submit" id="save">공지사항 수정</button>
				<button class="btn btn-warning" type="reset" id="modibyBtn">초기화</button>
				</div>
			</div>
		</form>
	</section>
	<br><br><br>
	<div style="width:1280px; margin:0 auto;">
			<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</div>
	
</body>
</html>