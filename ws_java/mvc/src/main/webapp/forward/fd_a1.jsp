<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>http://localhost:9091/forward/fd_a1.jsp</title>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
 
<form name='frm' method='post' action='./fd_a2.jsp'>
  <DIV style='text-align: center;'>
    <IMG src='./images/walter01.jpg' style='width: 40%;'>
  </DIV>
  <DIV style="width: 80%; margin: 10px auto;">
    <h2>fd_a1.jsp</h2>
    영화 제목: <input type='text' name='title' value='폭삭 속았수다.'><br>
    주연 배우: <input type='text' name='actor' value='박보검, 아이유' style="width: 77%;"><br>
    개봉 연도: <input type='text' name='year' value='2025-03-01'><br>
    <br>
    <div style="width: 100%; text-align: center;">
      <input type='submit' value='서버로 전송'>
    </div>
  </DIV>
</form>
</body>
</html>
