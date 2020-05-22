<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/btn.css">
    <link rel="stylesheet" href="/css/formInput.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js">
    </script>
    <title>Moing</title>
    <style>

        .login-page {
            width: 450px;
            padding: 10%;
            margin: auto;
        }

        .form {
            position: relative;
            background: #f0e0c0;
            max-width: 360px;
            margin: 0 auto;
            padding: 45px;
            text-align: center;
        }

       
        .form input{
/*
            font-family: 
            outline: 1;
            background: 
*/
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
            text-align-last: left;
        }

        .form button{
            font-family: ;
            text-transform: uppercase;
            outline: 0;
            color: black;
            font-size: 14px;
            cursor: pointer;
            width: 100%;
        }
        .form button:hover,.form button:active{
            background: #f0e0c0;
        }
        .form {
            font-size: 12px;
        }
        .form a{
            color: grey;
            text-decoration: none;
            float: left;
            padding-top: 15px;
        }
         #searchPw, #searchId, #searchMiddle{
            color: grey;
            text-decoration: none;
            float: right;
            padding-top: 15px;

        }

        #confirmMsg{
            width: 35%;
            font-size: 10px;
        }
        #emailInput{
            float: left;
            width: 60%;
        }
        .addr{
        	display: inline-block;
        	width: 100%;
        }
    </style>
</head>

<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <section class="login-page container">
        <div class="form form-origin form-orange-line">
            <form action="/login" method="post">
                <h3>로그인</h3>
                <input type="text" placeholder="아이디를 입력하세요" id="memberId" name="memberId">
                <input type="password" placeholder="비밀번호를 입력하세요" id="memberPw" name="memberPw">
                <button type="submit" class="btn-long-orange2 btn-long-orange1" id="returnLogin">로그인</button>
                <a id="gotoJoin" href="/joinFrm">회원가입</a>
                <a id="searchPw" href="/searchPwFrm">비밀번호찾기</a>
                <a id="searchMiddle">&nbsp/&nbsp</a>
                <a id="searchId" href="/searchIdFrm">아이디찾기</a>
                
                
            </form>
        </div>
    </section>
    <script>

    
    
    </script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body></html>