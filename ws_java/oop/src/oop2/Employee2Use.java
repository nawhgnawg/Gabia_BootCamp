package oop2;

public class Employee2Use {

  public static void main(String[] args) {
    Employee2 emp = new Employee2();
    // emp.name = "가길동"; // The field Employee2.name is not visible <- private
    // 변수 접근 불가능 함으로 어떻게 필드를 초기화 할 것인가?
    
    Employee2 emp2 = new Employee2("아로미", 100, 90, 80);
    // emp2.tot = emp2.java + emp2.web + emp2.dbms; // X
    emp2.calc();
    emp2.print();
    
    Employee2 emp3 = new Employee2("왕눈이", 90, 70, 85);
    // emp2.tot = emp2.java + emp2.web + emp2.dbms; // X
    emp3.calc();
    emp3.print();

    Employee2 emp4 = new Employee2("투투투", 80, 60, 75);
    // emp2.tot = emp2.java + emp2.web + emp2.dbms; // X
    emp4.calc();
    emp4.print();
    
  }

}