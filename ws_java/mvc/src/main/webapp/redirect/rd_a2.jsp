<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>http://localhost:9091/redirect/rd_a2.jsp</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
  <DIV style='text-align: center;'>
    <IMG src='./images/switch01.jpg' style='width: 40%;'>
  </DIV>
  <DIV style="width: 40%; margin: 10px auto;">
    <h2>rd_a2.jsp</h2>
    영화 제목: <%=request.getParameter("title") %><br>
    주연 배우: <%=request.getParameter("actor") %><br>
    개봉 연도: <%=request.getParameter("year") %><br>
    <br>
    <div style="width: 100%; text-align: center;">
      <input type='submit' value='서버로 전송'>
    </div>
  </DIV>
  <%
  System.out.println("▶ rd_a2.jsp 실행됨");
  System.out.println(new java.util.Date().toLocaleString());
  System.out.println(request.getParameter("title"));
  System.out.println(request.getParameter("actor"));
  System.out.println(request.getParameter("year"));
  %>
 
  <%
  // 주소 이동, 새로운 페이지에서 새로운 request 객체가 생성됨.
  response.sendRedirect("./rd_a3.jsp");
  %>
</form>
</body>
</html>
