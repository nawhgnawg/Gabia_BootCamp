package school;

import sungjuk.Student; // 다른 패키지에 있을 때는 주소를 붙여줘야 한다.

public class StudentUse {

  public static void main(String[] args) {
    
    Student student = new Student();
    student.print();

  }
}
