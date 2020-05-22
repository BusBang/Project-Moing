<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <script src="https://kit.fontawesome.com/240c78171f.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
    header{
        width: 1280px;
        box-sizing: content-box;
        margin: 0 auto;
        position:relative;
    }
    header div{
        display: inline-block;
    }
    .main-wrapper{
        width: 100%;
        box-sizing: content-box;
    }
    
    nav{
        width: 100%;
        height: 100px;   
    }
    nav div{
        width: calc(1280px/5);
        line-height: 42px;
        display: inline-block;
        box-sizing: border-box;
        border: 1px solid white;
        border-bottom-left-radius: 10px;
        border-bottom-right-radius: 10px;
        float: left;
        text-align: center;
        background-color: #f0E0C0;
        font-size: 18px;
        font-weight: bold;
        color: #808060;
        margin: 0 auto;
    }
    
    nav div:hover{
        color: #204000;
        text-shadow: 1px 1px #808060;
        font-size: 1.1em;
    }
    
    header a{
        text-decoration: none;
    }
    
    header .member{
        float: right;
    }
    
    header i{
        font-size: 30px;
        margin: 0 auto;
        color: #204000;
        position: relative;
        top: 90px;
        margin-left: 15px;
    }
    .member span{
        font-size: 15px;
    }
    body{
       height : 1500px;
    }
</style>
    
<script>
    function funcLogin(memberId){
      if(memberId=="admin"){
         location.href= "/askAdmin?reqPage=1";
      }else if(memberId==""){
         location.href = "/loginFrm";
      }else{
         location.href = "/askFrm?reqPage=1&memberId="+memberId;
      }
   }
</script>

<header>
    <div class="main-wrapper" id="top">
        <div>
            <a href = "/index.jsp"><img src="/img/semi_logo.png" width="180px" height="180px"></a>
        </div>
<div class="member">
           <c:if test="${empty sessionScope.member }">
               <a href="/loginFrm" title="로그인" data-toggle="hover" data-trigger="hover" data-placement="bottom"><i class="fas fa-sign-in-alt"></i></a>
               <a href="/joinFrm" title="회원가입" data-toggle="hover" data-trigger="hover" data-placement="bottom"><i class="fas fa-user-plus"></i></a>
            </c:if>
            <c:if test="${not empty sessionScope.member }">
               <a href="/logout" title="로그아웃" data-toggle="hover" data-trigger="hover" data-placement="bottom"><i class="fas fa-sign-out-alt"></i></a>
               <a href="/mypageModifyFrm?id=${sessionScope.member.memberId }" title="마이페이지" data-toggle="hover" data-trigger="hover" data-placement="bottom"><i class="fas fa-heart" style="color:red;"></i></a>
            </c:if>
        </div>
        <nav class="sticky-top">
       <a href="/moimMain?reqPage=1"><div>모임 찾기</div></a>        
        <a href="/placeListMain?reqPage=1"><div>장소 찾기</div></a>
        <a href="/noticeMainFrm?reqPage=1"><div>공지사항</div></a>
        <a href="javascript:void(0)" onclick="funcLogin('${sessionScope.member.memberId}');"><div>1:1 문의</div></a>
        <a href="/qna/faq.jsp"><div>자주 묻는 질문</div></a>
        </nav>
    </div>
</header>