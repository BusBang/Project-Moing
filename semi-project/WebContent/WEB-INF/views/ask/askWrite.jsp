<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link href="/css/btn.css" rel="stylesheet" type="text/css">
    <link href="/css/formInput.css" rel="stylesheet" type="text/css">
    <link href="/css/letterList.css" rel="stylesheet" type="text/css">
    <link href="/css/letterWriteCss.css" rel="stylesheet" type="text/css">
    
    <style>
    	#arrString{
             text-align: left;
             margin-left: 30px;
             font-size: 13px;
             color:#808060;
             font-weight: 700;
         }
         #askImgviewer{
         	float: left;
         	margin-left: 20px;
         }
         #mm{
         	clear: left;
         }
    </style>
                
               
                
</head>
<body>
    
  		
        <div class="letterWrapper">
            <div>문의하기</div>
         <form action="/askSend" enctype="multipart/form-data" method="post" class="form-origin form-green-line">
            <input type="hidden" name="memberId" value="${sessionScope.member.memberId }">
            <lable class="la">관리자 : </lable>
            	<div id="arrString">admin</div>
            <label for="askTitle" class="la">제목 : </label>
            <input name="askTitle" class="input-origin inputTitile">
            <label for="askContent" class="la">내용 : </label>
            <textarea name="askContent" ></textarea>
            <label class="la">첨부파일 : </label>
            <input type="file" name="askFilename" onchange="loadImg(this);">
            <div id="askImgviewer">
				<img id="askImg" width="70">
			</div>
			 <div id="mm">
			<button type="submit" class="btn-sm-orange1">전송</button>
            <button type="reset" class="btn-sm-orange1">초기화</button>
            </div>
         </form>
        </div>
        
<script>
		function loadImg(f){
			console.log(f.files);//첨부파일에 대한 배열로 나타난다.(여러개의 파일을 가져올 수 있기 때문에)-->우리는 하나만 가져올 거라서 0번 인덱스만 확인함
			if(f.files.length!=0 && f.files[0]!=0){//파일이 1개 이상 올라오고 0번째 파일의 크기가 0이 아닐 때 (파일을 선택했을 때)
				var reader = new FileReader();//이 객체를 이용해서 해당 파일을 읽어옴
				reader.readAsDataURL(f.files[0]); //0번 파일에 대한 정보를 reader에 저장. (readAsDataURL)
				reader.onload = function(e){//파일을 읽어오는 것을 기다리고 다 읽어오면 function에 e라는 매개변수를 통해 결과가 들어오고
					$("#askImg").attr("src", e.target.result);//경로를 넣어준다.
				}
			}else{//파일을 선택하지 않고 취소를 눌렀을 때
				$("#askImg").attr("src", "");//경로를 비워줌.
			}
		}
		
	</script>
</body>
</html>