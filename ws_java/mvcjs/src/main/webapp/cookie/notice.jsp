<%@ page contentType="text/html; charset=UTF-8" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<body>
  <DIV class='title' style='margin: 10px auto; 
                            width: 100%; 
                            font-weight: bold; 
                            text-decoration: underline; 
                            text-align: center;'>자격증 안내</DIV>
  <DIV style='margin: 0px auto; width: 90%;'> 
    <UL>
      <!-- 2025년 기준 -->
      <LI>정보처리 기사/산업기사</LI>   <!-- 국가기술, 20/16 학점 -->
      <LI>SQL 개발자(SQLD)</LI>        <!-- 국가공인, 6학점 -->
      <LI>PCCE/PCCP 코딩 테스트</LI>  
      <LI>빅데이터분석 기사</LI>          <!-- 국가기술, 20 학점 -->  
      <LI>데이터분석 준전문가(ADSP)</LI>  <!-- 국가공인, 14학점 -->  
      <LI>선택 1: 데이터분석 전문가(ADP)</LI>   <!-- 국가 공인, 기능장/기술자, 40대 경쟁력, 30학점 -->
      <LI>선택 2: IT 계열 석/박사</LI>  <!-- 40대 경쟁력 -->
    </UL>
  </DIV>
  <br>
  <DIV style='text-align: right; padding-right: 10px;'>
    <form name='frm' method='post' action='./notice_cookie.jsp'>
      <label>
        <input type='checkbox' name='windowOpenCheck' onclick="this.form.submit()">
        30초간 창 열지 않기
      </label>
    </form>
  </DIV>
</body>
 
</html>

