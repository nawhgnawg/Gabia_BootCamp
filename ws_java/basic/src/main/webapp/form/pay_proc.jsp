<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=10.0, width=device-width" />
<title></title>
<style>
  *{ font-family: Malgun Gothic; font-size: 22px;}
</style>
</head>
<body>
<%
    String name = request.getParameter("name");
    int pay = Integer.parseInt(request.getParameter("pay"));
    int sudang = Integer.parseInt(request.getParameter("sudang"));
    String content = request.getParameter("content");   // textarea의 값도 똑같이 가져올 수 있음.

    int tot = pay + sudang;
    int tax = (int)(tot * 0.1);
    int silsu = tot - tax;
    int year = pay * 12;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    String date = sdf.format(new Date());
    System.out.println("-> password: " + request.getParameter("password"));
%>
<h2>급여 처리</h2>
<div>
  성명: <%=name %><br>
  본봉: <%="￦ " + String.format("%,d", pay) + " 원" %><br>
  수당: <%="￦ " + String.format("%,d", sudang) + " 원" %><br>
  총액: <%="￦ " + String.format("%,d", tot) + " 원" %><br>
  세금(10%): <%="￦ " + String.format("%,d", tax) + " 원" %><br>
  실수령액: <%="￦ " + String.format("%,d", silsu) + " 원" %><br>
  연봉: <%="￦ " + String.format("%,d", year) + " 원" %><br>
  근무일: <%=date %><br>
  <br>
  기타
  <pre><%=content %></pre>
</div>
</body>
</html>
