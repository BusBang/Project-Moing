<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="/css/btn.css">
    <style>
        body{
            width: 1280px;
            margin: 0 auto;
        }
        section tr{
            height: 80px;
        }
        section table{
            border-bottom: 2px solid #ff9742;
            border-top: 2px solid #ff9742; 
        }
        section .default{
            border: 1px solid #f0e0c0;
            border-left: none;
            border-right: none;
            border-bottom: none;
        }
        
        section .top{
            border-top: none;
        }
        section th{
            width: 100px;
            text-align: left;
            padding: 5px;
        }
        section button{
            width: 100px;
            height: 40px;
            background-color: #ff9742;
            border: #ff9742;
            border-radius: 5px;
            font-size: 15px;
            font-weight: bolder;
            color: #204000;
        }
        section input[type="reset"]{
            float: right;
        }
        section table{
            margin: 0 auto;
        }
        section section div{
            position: relative;
            width: 300px;
            margin: 0 auto;
        }
        section h1{
            text-align: center;
        }
        section textarea{
            margin-top: 20px;
            margin-bottom: 20px;
            resize: none;
        }
        .send{
        	height: 40px;
        }
    </style>
    <script>
        $(function(){
            $("input").focusin(function(){
                $(this).css("outline","none");
            })
            
            $("textarea").focusin(function(){
                $(this).css("outline","none");
            })
        })
        
        $("#qnaSend").click(function(){
        	var email
        })
    </script>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
        <h1>1:1 문의</h1>
        <form action="#" method="post">
        <table>
            <tr>
                <th class="default top">회원정보</th>
                <td class="default top"><span>이름</span></td>
            </tr>
            <tr>
                <th class="default">제목</th>
                <td class="default"><input type="text" placeholder="제목을 입력해주세요" size="70" required></td>
            </tr>
            <tr>
                <th class="default">문의내용</th>
                <td class="default"><textarea cols="70" rows="20" required></textarea></td>
            </tr>
            <tr>
                <th class="default">파일첨부</th>
                <td class="default"><label for="attach"><input type="text" placeholder="선택된 파일 없음" size="50" readonly></label> <input type="button" id="attach" value="파일선택"></td>
            </tr>
        </table><br><br>
            <div class="send"><button id="qnaSend">보내기</button> <button type="reset">취소</button></div>
        </form>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>