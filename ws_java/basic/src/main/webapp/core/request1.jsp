<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" />
<title>http://localhost:9091/core/request1.jsp</title>
<style>
  *{ font-family: Malgun Gothic; font-size: 22px;}
</style>
</head>
<body>
<%
String[] images = new String[10];

images[0] = "./images/tu01.jpg";
images[1] = "./images/tu02.jpg";
images[2] = "./images/tu03.jpg";
images[3] = "./images/tu04.jpg";
images[4] = "./images/tu05.jpg";
images[5] = "./images/tu06.jpg";
images[6] = "./images/tu07.jpg";
images[7] = "./images/tu08.jpg";
images[8] = "./images/tu09.jpg";
images[9] = "./images/tu10.jpg";

// 사용자는 자신이 원하는 특정 이미지만 확인하고 싶다.
// 사용자 -> 번호를 전송 or 파일명 전송
// 전송 방법: URL을 이용한 방법, Form을 이용한 방법, Ajax 통신을 이용한 방법
// http://localhost:9091/core/request1.jsp?index=1  -> 요청 -> Tomcat -> 응답

int index = Integer.parseInt(request.getParameter("index"));
String fname = request.getParameter("fname");

%>
index: <%=index %><br>
<img src="<%=images[index - 1] %>" style="width: 50%"><br>
fname: <%=fname %><br>
<img src="./images/<%=fname %>" style="width:50%">

</body>
</html>