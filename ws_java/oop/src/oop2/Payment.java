package oop2;

public class Payment {
  
  private String menu = ""; // 필드, 인스턴스 변수, 전역 변수, 속성, Property
  private int price = 0;
  private int count = 0;
  private int total = 0;

  public void order(String menu, int price, int count) {
    this.menu = menu;
    this.price = price;
    this.count = count;
  }

  public void calc() {
    this.total = this.price * this.count;

    System.out.println();
    System.out.println("      주문 내역       ");
    System.out.println("---------------------");
    System.out.println("메뉴: " + this.menu);
    System.out.println("가격: " + String.format("%,d", this.price) + " 원");
    System.out.println("수량: " + this.count);
    System.out.println("합계: " + String.format("%,d", this.total) + " 원");
    System.out.println("---------------------");
  }
  
  public void payment() {
    System.out.println("부모 클래스 payment()");
  }
  
}



