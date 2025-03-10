<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>http://localhost:9091/core/script3.jsp</title>
</head>
<body>
  <%  // JAVA 영역, 스크립틀릿
  String name = "왕눈이";
  int kor = 90;
  int eng = 70;
  int mat = 80;
  
  int tot = kor + eng + mat;
  int avg = tot / 3;
  %>
  <h2>성적 조회: <% out.println(name);%> script3.jsp</h2>
  <ol>
    <li>국어 : <% out.println(kor); %></li>
    <li>영어 : <% out.println(eng); %></li>
    <li>수학 : <% out.println(mat); %></li>
  </ol>
  총점: <% out.println(tot); %>
  <br>
  평균: <% out.println(avg); %>
</body>
</html>
  