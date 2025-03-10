<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>http://localhost:9091/core/script1.jsp</title>
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
  <h2>성적 처리 광환</h2>
  <%
  System.out.println("-> name: " + name);   // System.out.println()은 터미널에 출력
  out.println("Total: " + tot); // out.println()은 화면에 출력
  out.println("mean: " + avg);
  %>

</body>
</html>
  