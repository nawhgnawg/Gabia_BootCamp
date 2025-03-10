<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>http://localhost:9091/core/script2.jsp</title>
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
  <h2>성적 조회: <% out.println(name);%> script2.jsp</h2>
  <%
  out.println("<ol>");
  out.println(" <li>국어 : " + kor + "</li>");
  out.println(" <li>영어 : " + eng + "</li>");
  out.println(" <li>수학 : " + mat + "</li>");
  out.println("</ol>");
  out.println("총점: " + tot + "<br>");
  out.println("평균: " + avg);
  %>
</body>
</html>
  