<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>http://localhost:9091/redirect/rd_a3.jsp</title>
    <link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
    <div style='text-align: center;'>
        <img src='./images/fall27.jpg' style='width: 40%;'>
    </div>
    <div style="width: 40%; margin: 10px auto;">
        <h2>rd_a3.jsp</h2>
        영화 제목: <%=request.getParameter("title") %><br>
        주연 배우: <%=request.getParameter("actor") %><br>
        개봉 연도: <%=request.getParameter("year") %><br>
        <br>
        <div style="width: 100%; text-align: center;">
            <input type='submit' value='서버로 전송'>
        </div>
    </div>
</body>
</html>