<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>

<style>

		.contentBox{
			text-align: center;
            margin: 0 auto;
            width: 1110px;
		}
	
        .context{
            text-align: center;
            margin: 0 auto;
            width: 1110px;
        }
        #ir1{
        	padding-left: 10%;
        }
        #title{
        	font-size: 22px;
        }
        .btnst{
            color: black;
            background-color: #B6EAFA;
            width: 100px;
            height: 50px;
            border: 0px solid white;
            border-radius: 10px;
            margin-top: 10px;
            margin-left: 10px; 
        }
        .bigTitle{
        }
        
    </style>
    
<!-- SmartEditor를 사용하기 위해서 다음 js파일을 추가 (경로 확인) -->
<script type="text/javascript" src="/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
      crossorigin="anonymous"></script>

 
</head>
<body>


<h1 style="text-align: center;">헤더 1</h1>
<br><hr><br>

<div class="contentBox">

	<div class="bigTitle">강아지 자랑게시판</div>

<div class="context">
    
<form id="frm" action="insert.jsp" method="post" >
<table width="100%">
        <tr>

            	<td><input type="text" id="title" name="title" style="width:1100px; height: 30px;" placeholder="제목을 입력하세요."/></td>

        </tr>
        
        <tr>
            <td>
              <textarea rows="10" cols="30" id="ir1" name="content" style="width:1100px; height:400px;" placeholder="내용을 입력하세요."></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" class="btnst" id="save" value="게시글 작성"/>
                <input type="button" class="btnst" value="게시글 취소"/>
            </td>
        </tr>
</table>
</form>
</body>
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
</html>