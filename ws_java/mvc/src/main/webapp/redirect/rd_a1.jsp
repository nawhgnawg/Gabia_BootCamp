<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>http://localhost:9091/redirect/rd_a1.jsp</title>
    <link href="../css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
    <form name='frm' method='post' action='./rd_a2.jsp'>
        <div style='text-align: center;'>
            <img src='./images/fall27.jpg' style='width: 40%;'>
        </div>
        <div style="width: 40%; margin: 10px auto;">
            <h2>rd_a1.jsp</h2>
            영화 제목: <input type='text' name='title' value='인터스텔라'><br>
            주연 배우: <input type='text' name='actor' value='앤 헤서웨이' style="width: 77%;"><br>
            개봉 연도: <input type='text' name='year' value='2014-11-06'><br>
            <br>
            <div style="width: 100%; text-align: center;">
                <input type='submit' value='서버로 전송'>
            </div>
        </div>
    </form>
</body>
</html>