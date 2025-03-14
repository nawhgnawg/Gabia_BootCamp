<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=10.0, width=device-width">

    <title>DATE/TIME 처리</title>

    <style>
      *{ font-family: Malgun Gothic; font-size: 20px;}
    </style>
</head>

<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String reser_date = request.getParameter("reser_date");
        String reser_time = request.getParameter("reser_time");
        String shopping_begin = request.getParameter("shopping_begin");
        String shopping_end = request.getParameter("shopping_end");
    %>
    예약날짜: <%=reser_date %><br><br>
    예약시간: <%=reser_time %><br><br>
    구입 내역 검색 기간: <%=shopping_begin %> ~ <%=shopping_end %>
</body>
</html>