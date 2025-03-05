package oop2;

public class Cash extends Payment {
  // method overriding, 부모 클래스의 재구현
  @Override // 선언 절대 권장
  public void payment() {
    System.out.println("현금 결재");
  }
  
}