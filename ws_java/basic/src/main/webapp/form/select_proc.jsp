<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=10.0, width=device-width" />
<title>http://localhost:9091/form/select_proc2.jsp</title>
<style>
  *{ font-family: Malgun Gothic; font-size: 22px;}
</style>
</head>
<body>
<%
String travel1 = request.getParameter("travel1");
%>
1박 2일 여행: <%=travel1 %><br><br>
<%
String[] travel2 = request.getParameterValues("travel2");
%>
지역 지정 여행:
<ul style="list-style-type: square;">
    <%
    for (String item : travel2) {
    %>
        <li><%=item %></li>
    <%
    }
    %>
</ul>
</body>
</html>