<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<style>
    section{
        width: 1280px;
        margin: 0 auto;
        }
    section tr{
        height: 40px;
    }
    section table{
        width: 70%;
        border-bottom: 2px solid #ff9742;
        border-top: 2px solid #ff9742;
        margin: 0 auto;
        text-align:center;
		margin-bottom : 150px;
    }
    section th{
        padding: 5px;
    }
    section h1{
        text-align: center;
    }
    section .title{
        border: 1px solid #f0e0c0;
        border-left: none;
        border-right: none;
        border-top: none;
    }
    
    section .detail{
    	padding-right : 55px;
    	text-align : center;
    }

    section .content{
        border: 1px solid #f0e0c0;
        border-left: none;
        border-right: none;
        border-bottom: none;
    }
    a{
    	text-decoration: none;
    	color: black;
    }
    </style>
    <script>
        /*$(function(){
            $("tr").child("th").eq(0).css("width","20%");
        })*/
    </script>
    
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
	<% int num = 1; %>
        <h1>내가 좋아요한 모임</h1>
        <table>
            <tr>
                <th class="title"></th>
                <th class="title detail">모임명</th>
            </tr>
            <c:forEach items="${likeList}" var="l">
            <c:set var="num" value="<%=num++%>"/>
	            <tr>
	                <td class="content">${num}</td>
	                <td class="content detail"><a href="/meetingDetail?meetingNo=${l.meetingNo}&reqPage=1">${l.meetingTitle}</a></td>
	            </tr>
            </c:forEach>
        </table>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
<body>
</body>
</html>