package oop;

public class Variable {

  public static void main(String[] args) {
    // char grade = "A"; // X, Type mismatch: cannot convert from String to char
    char grade = 'A';  // 하나의 문자만 저장
    System.out.println("grade: " + grade);
    
    int year = 2024;
    // year.
    
    String msg = "자바 개발자";
    // msg.
    
    // float score = 10.5;  // X
    // float score = 10.5f; // 권장 X
    double score = 10.5;
    System.out.println((int)score);
    
    boolean sw = true;
    // sw = True // X
    
    // int now = (int)"2024";  // X
    int now = Integer.parseInt("2024"); // 문자열 -> 정수
    System.out.println(now + 1 + " 년");
    // System.out.println("-" * 20); // X
    System.out.println("--------------------");
    
    int su1 = 10;
    int su2 = 3;
    
    System.out.println(su1 + su2);
    System.out.println(su1 / su2);  // 3, int / int = int, double 값을 얻고 싶을 떄 원하는 값이 안나올 수 있
    // System.out.println(su1 // su2);  // X
    System.out.println(su1 % su2);
    
    // int sales = 5000000000;  // X, The literal 5000000000 of type int is out of range 
    long sales = 5000000000L;
    
    int cnt = 0;
    cnt = cnt + 1;
    System.out.println("1) " + cnt);
    
    cnt += 1;
    System.out.println("2) " + cnt);
    
    cnt++;  // 파이썬 지원 안
    System.out.println("3) " + cnt);

    cnt--;
    System.out.println("4) " + cnt);
    
    // 권장하지 않는 패턴
    int total = cnt--; // cnt 변수의 값이 쓰이고 -1을 진행함.
    System.out.println("total: " + total);
    
    System.out.println("cnt: " + cnt);
    
    total = --cnt;     // cnt 변수의 값을 먼저 -1을 진행하고 total 변수에 할당함.
    System.out.println("total: " + total);
    
  }

}