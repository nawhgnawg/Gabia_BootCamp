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
out.println("url: " + url);

%>
</body>
</html>