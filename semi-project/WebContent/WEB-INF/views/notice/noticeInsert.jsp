<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>Moing</title>
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
	<section>
	<form action="/noticeInsert" method="post" id="frm">
		<div id="content"
			style="width: 800px; height: 1000px; margin: 0 auto;">
			<h1>공지사항 글쓰기</h1>
			<hr>
			<div class="header-title">
				<label for="noticetitle" style="width: 100px; text-align: conter;">
					제목 </label> <input class="form-control" type="text" id="noticeTitle"
					name="noticeTitle"
					style="width: 400px; border-top: none; border-right: none; border-left: none; border-top: none; border-bottom: 2px solid black;"><label
					for="comment"></label>
			</div>
			<textarea id="ir1" rows="5" name="content"
				style="width: 700px; height: 600px; resize: none;"></textarea>

			<div style="width: 300px; margin: 0 auto;">
				<input type="button" class="btn btn-warning" id="save" value="공지게시"/>
				<button type="reset" class="btn btn-warning" id="noticere">공지글
					초기화</button>
			</div>

		</div>
	</form>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</section>

</body>
</html>