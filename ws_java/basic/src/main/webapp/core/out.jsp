<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8"><meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0, maximum-scale=10.0, width=device-width" />
<title>http://localhost:9091/core/out.jsp</title>
<style>
  *{ font-family: Malgun Gothic; font-size: 26px;}
</style>
</head>
<body>
<%
System.out.println("요청 처리됨.");
out.println("<DL>");
out.println("<DT>인공지능 영화</DT>");
out.println("<DD>AI 2001</DD>");
out.println("<DD>트랜센던스 2014</DD>");
out.println("<DD>엣지 오브 투모로우 2014</DD>");
out.println("<DD>이미테이션게임 2015</DD>");
out.println("</DL>");

out.print("<DL>");
out.print("<DT>영어 학습 영화</DT>");
out.print("<DD>맘마미아</DD>");
out.print("<DD>러브 액츄얼리</DD>");
out.print("<DD>악마는 프라다를 입는다.</DD>");
out.print("<DD>김씨네 편의점(캐나다)</DD>");
out.print("</DL>");
%>
</body>
</html>
