<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>http://localhost:9091/redirect/fd_a3.jsp</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
  <DIV style='text-align: center;'>
    <IMG src='./images/26.jpg' style='width: 80%;'>
  </DIV>
  <DIV style="width: 40%; margin: 10px auto;">
    <h2>fd_a4.jsp</h2>
    영화 제목: <%=request.getParameter("title") %><br>
    주연 배우: <%=request.getParameter("actor") %><br>
    개봉 연도: <%=request.getParameter("year") %><br>
    <br>
    <div style="width: 100%; text-align: center;">
      <a href="./fd_a1.jsp">첫 페이지로 이동</a>
    </div>
  </DIV>
</form>
</body>
</html>

