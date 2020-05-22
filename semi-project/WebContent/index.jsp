<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Moing</title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/240c78171f.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
    section, section>div{
        width: 1280px;
        margin: 0 auto;
    }
    .meeting_category img{
        width: 250px;
        height: 150px;
    }
    .meeting_category>div div{
        display: inline-block;
        margin: 25px;
    }
    .meeting_category>div{
        text-align: center;

    }
    .meeting_category a {
          color : black;
          font-weight : bold;
    }
    
    
    
    .ad, .popular{
        height: 400px;
        margin-top:50px;
    }
    
    /* Make the image fully responsive */
	.carousel-inner img {
	  width: 100%;
	  height: 600px;
	}
	#demo{
	  width: 100%;
	  height: 600px;
	}
	section{
		margin: 0 auto;
	}
	#theme{
		float:right;
		position: relative;
		right:20px;
	}
	
	.ad, .popular{
		text-align : center;

	}
	.ad img, .popular img{
		margin:15px;
	}
	h3, p, h5{
        /* margin-left: 60px; */
        text-align : center;
    }
    #titleTag {
    	margin-top : 80px;
    	font-weight : bold;
    	font-size : 1.8em;
    	position : relative;
    }
/* 	#titleTag>i{
		position : relative;
		top : 75px;
	} */
	#titleSub {
	
	}
    
</style>
<script>
	
	window.onload = function(){
		ad();
		pop();
	}
	
	function ad(){
		$.ajax({
			url:"/adMeeting",
			type : "post",
			success : function(data){
				console.log("성공");
				 /* for(var i=0;i<3;i++){
					if(data[i].meetingFilepath === null){
						$("#ad"+i).attr("src","/upload/"+data[i].meetingFilepath);
						$("#ad"+i).parent().attr("href","/meetingDetail?meetingNo="+data[i].meetingNo+"&reqPage=1");
						}else{
							$("#ad"+i).attr("src","/upload/meetingDefault.jpg");
							$("#ad"+i).parent().attr("href","/meetingDetail?meetingNo="+data[i].meetingNo+"&reqPage=1");
						}
					}	
				}  */
				$("#ad1").attr("src","/upload/"+data[0].meetingFilepath);
				$("#ad1").parent().attr("href","/meetingDetail?meetingNo="+data[0].meetingNo+"&reqPage=1");
				$("#ad2").attr("src","/upload/"+data[1].meetingFilepath);
				$("#ad2").parent().attr("href","/meetingDetail?meetingNo="+data[1].meetingNo+"&reqPage=1");
				$("#ad3").attr("src","/upload/"+data[2].meetingFilepath);
				$("#ad3").parent().attr("href","/meetingDetail?meetingNo="+data[2].meetingNo+"&reqPage=1");
			},
			error : function(data){
				console.log("다시 시도");
			}
		});
	}
		
	function pop(){
		$.ajax({
			url:"/popularMeeting",
			type : "post",
			success : function(data){
				$("#pop1").attr("src","/upload/"+data[0].meetingFilepath);
				$("#pop1").parent().attr("href","/meetingDetail?meetingNo="+data[0].meetingNo+"&reqPage=1");
				$("#pop2").attr("src","/upload/"+data[1].meetingFilepath);
				$("#pop2").parent().attr("href","/meetingDetail?meetingNo="+data[1].meetingNo+"&reqPage=1");
				$("#pop3").attr("src","/upload/"+data[2].meetingFilepath);
				$("#pop3").parent().attr("href","/meetingDetail?meetingNo="+data[2].meetingNo+"&reqPage=1");
			},
			error : function(data){
				console.log("다시 시도");
			}
		})
	}
</script>
<body>
<h1>안녕 난 깃 테스트야</h1>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<section>
        <p id="titleTag">5월 가정의달을 맞아 즐거운 모임을 가져보세요</p>
        <p id="titleSub">&nbsp;</p>
       	<div id="demo" class="carousel slide" data-ride="carousel">
		  <ul class="carousel-indicators">
		    <li data-target="#demo" data-slide-to="0" class="active"></li>
		    <li data-target="#demo" data-slide-to="1"></li>
		    <li data-target="#demo" data-slide-to="2"></li>
		  </ul>
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img id="theme1" src="/img/main/main1.jfif" alt="캠핑" width="1100px" height="500px">
		      <div class="carousel-caption">
		        <h3></h3>
		        <p></p>
		      </div>   
		    </div>
		    <div class="carousel-item">
		      <img id="theme2" src="/img/main/main2.jfif" alt="캠핑" width="1100px" height="500px">
		      <div class="carousel-caption">
		        <h3></h3>
		        <p></p>
		      </div>   
		    </div>
		    <div class="carousel-item">
		      <img id="theme3" src="/img/main/main4.jpg" alt="캠핑" width="1100px" height="500px">
		      <div class="carousel-caption">
		        <h3></h3>
		        <p></p>
		      </div>   
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#demo" data-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </a>
		  <a class="carousel-control-next" href="#demo" data-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </a>
		</div>
		<div>
        </div>
        
        
 <div class="ad">
      <p id="titleTag">
         <i class="fas fa-audio-description">광고모임</i>
      </p>
      <p id="titleSub">&nbsp;</p>
      <a href="/moimMain?reqPage=1"><img id="ad1" src="/img/자연1.jpg" width="360px" height="270px"></a>
      <a href="/moimMain?reqPage=1"><img id="ad2" src="/img/main/main2.jfif" width="360px" height="270px"></a>
      <a href="/moimMain?reqPage=1"><img id="ad3"
         src="/img/main/main3.jfif" width="360px" height="270px"></a>
   </div>
        <div class="popular">
        	<p id="titleTag">인기 TOP3 모임</p>
            <p id="titleSub">가장 인기 있는 모임들을 만나보세요</p>
            <%-- <c:forEach items="${popList}" var="pop"> --%>
            	<a href="#"><img id="pop1" src="#" width="360px" height="270px"></a>
            	<a href="#"><img id="pop2" src="#" width="360px" height="270px"></a>
            	<a href="#"><img id="pop3" src="#" width="360px" height="270px"></a>
            <%-- </c:forEach> --%>
        </div>
        <div class="meeting_category">
        <p id="titleTag">모임 카테고리</p>
            <p id="titleSub">관심 있는 주제별로 모임을 찾아보세요</p>
            <div>
                <a href="/moimSearch?category1=1&category2=13&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/workout.jpeg"><br>
                    운동
                </div></a>
                    
                <a href="/moimSearch?category1=3&category2=19&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/language.jpeg"><br>
                    언어교환
                </div></a>
                <a href="/moimSearch?category1=4&category2=23&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/painting-writing.jpeg"><br>
                    글/그림
                </div></a>
                <a href="/moimSearch?category1=7&category2=34&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/travel.jpeg"><br>
                    여행
                </div></a>
            </div>
            <div>
                <a href="/moimSearch?category1=5&category2=27&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/pet.jpeg"><br>
                    반려동물
                </div></a>
                <a href="/moimSearch?category1=8&category2=38&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/game.jpeg"><br>
                    게임
                </div></a>
                <a href="/moimSearch?category1=6&category2=31&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/food.jpeg"><br>
                    음식
                </div></a>
                <a href="/moimSearch?category1=2&category2=16&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/music.jpeg"><br>
                    음악
                </div></a>
            </div>
            <div>
                <a href="/moimSearch?category1=10&category2=48&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/exhibit.jpeg"><br>
                    문화/공연관람
                </div></a>
                <a href="/moimSearch?category1=11&category2=55&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/study.jpeg"><br>
                    스터디
                </div></a>
                <a href="/moimSearch?category1=9&category2=43&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/volunteer.jpeg"><br>
                    봉사활동
                </div></a>
                <a href="/moimSearch?category1=12&country1=&country2=&keywordSearch="><div>
                    <img src="/img/category/etc.jpeg"><br>
                    기타
                </div></a>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</body>
</html>