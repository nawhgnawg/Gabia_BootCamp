package oop2;

public class EmployeeUse {

  public static void main(String[] args) {
    Employee emp = new Employee();  // 객체 생성
    emp.name = "아로미";
    emp.java = 90;
    emp.web = 100;
    emp.dbms = 85;
    
    emp.tot = emp.java + emp.web + emp.dbms;
    emp.avg = (emp.tot / 3.0);
    
    System.out.println("성명: " + emp.name);
    System.out.println("JAVA: " + emp.java);
    System.out.println("WEB: " + emp.web);
    System.out.println("DBMS: " + emp.dbms);
    System.out.println("총점: " + emp.tot);
    System.out.println("평균: " + (int)emp.avg);
    System.out.println("--------------------------");
    
    emp.name = "왕눈이";
    emp.java = 70;
    emp.web = 80;
    emp.dbms = 89;
    
    emp.tot = emp.java + emp.web + emp.dbms;
    emp.avg = (emp.tot / 3.0);
    
    System.out.println("성명: " + emp.name);
    System.out.println("JAVA: " + emp.java);
    System.out.println("WEB: " + emp.web);
    System.out.println("DBMS: " + emp.dbms);
    System.out.println("총점: " + emp.tot);
    System.out.println("평균: " + (int)emp.avg);
    System.out.println("--------------------------");
    
    emp.name = "가길동";
    emp.java = 90;
    emp.web = 90;
    emp.dbms = 87;
    
    emp.tot = emp.java + emp.web + emp.dbms;
    emp.avg = (emp.tot / 3.0) + 10;  // 가길동만 점수가 10점 상승하는 문제 발생
    
    System.out.println("성명: " + emp.name);
    System.out.println("JAVA: " + emp.java);
    System.out.println("WEB: " + emp.web);
    System.out.println("DBMS: " + emp.dbms);
    System.out.println("총점: " + emp.tot);
    System.out.println("평균: " + (int)emp.avg);
    System.out.println("--------------------------");
    

  }

}
