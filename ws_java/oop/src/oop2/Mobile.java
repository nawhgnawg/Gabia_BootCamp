package oop2;

public class Mobile extends Payment {
  @Override // 선언 절대 권장
  public void payment() {
    System.out.println("모바일 결재");
  }
}