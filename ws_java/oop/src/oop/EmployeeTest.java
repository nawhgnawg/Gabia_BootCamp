package oop;

public class EmployeeTest {

  public static void main(String[] args) {
    
    int python = 50;
    int java = 70;
    int bigdata = 95;
    
    Employee employee = new Employee(python, java, bigdata);
    employee.calc();
    
    System.out.printf("Pyhton 입사 점수: %,d\n", python);
    System.out.printf("Java 입사 점수: %,d\n", java);
    System.out.printf("Bigdata 입사 점수: %,d\n", bigdata);
    System.out.println(employee.getMsg());
    System.out.printf("평균: %,.2f", employee.getAvg());
    
    

  }

}
