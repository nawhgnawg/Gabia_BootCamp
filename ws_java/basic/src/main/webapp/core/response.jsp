<!-- url 값에 따른 분기문이 사용 -->
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=10.0, width=device-width" />
<title>http://localhost:9091/core/response.jsp</title>
<style>
  *{ font-family: Malgun Gothic; font-size: 22px;}
</style>
</head>
<body>
<%
// http://localhost:9091/core/response.jsp?url=kma
String url = request.getParameter("url");

if (url != null) {
    if (url.equals("")) {
            out.println("url 변수값을 입력해주세요.");
    } else if (url.equals("kma")) {
        response.sendRedirect("http://www.kma.go.kr");    // 주소 이동
    } else if (url.equals("daum")) {
        response.sendRedirect("http://www.daum.net");    // 주소 이동
    } else if (url.equals("naver")) {
        response.sendRedirect("http://www.naver.com");    // 주소 이동
    }
}
%>
<a href="https://www.weather.go.kr/w/index.do">
</body>
</html>