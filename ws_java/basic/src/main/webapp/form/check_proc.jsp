<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지

DecimalFormat df = new DecimalFormat("￦ #,###,### 원");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(new Date());
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, minimum-scale=1.0,
                                 maximum-scale=10.0, width=device-width" />
<title>http://localhost:9091/form/check_proc.jsp</title>
<style>
  *{ font-family: Malgun Gothic; font-size: 22px;}
</style>
</head>
<body>
  여행하고 싶은 지역은?<br>
  <%
  String travel1 = request.getParameter("travel1");
  String travel2 = request.getParameter("travel2");
  String travel3 = request.getParameter("travel3");
  String travel4 = request.getParameter("travel4");
  String travel5 = request.getParameter("travel5");
  %>

  <%= travel1 != null ? travel1 + "<br>" : "" %>
  <%= travel2 != null ? travel2 + "<br>" : "" %>
  <%= travel3 != null ? travel3 + "<br>" : "" %>
  <%= travel4 != null ? travel4 + "<br>" : "" %>
  <%= travel5 != null ? travel5 + "<br>" : "" %>

  <br>
  주문할 메뉴<br>
  <ol>
  <%
  String[] foods = request.getParameterValues("food");
  for (String food: foods) {
  %>
    <li><%=food %></li>
  <%
  }
  %>
  </ol>
  <a href="./check_form.html">변경하기</a> <!-- 초기화 -->
  <a href="javascript: history.back();">변경하기(Javascript)</a> <!-- 상태 유지 -->
</body>
</html>