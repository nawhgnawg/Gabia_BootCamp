package oop2;

public class Employee3Use {

  public static void main(String[] args) {
    Employee3 emp = new Employee3();
    
    emp = new Employee3("아로미", 100, 90, 80);
    emp.calc();
    System.out.println(emp.getName());
    System.out.println(emp.getAvg());
    emp.print();
    
    emp = new Employee3("왕눈이", 90, 70, 85);
    emp.calc();
    System.out.println(emp.getName());
    System.out.println(emp.getAvg());
    emp.print();
  }

}