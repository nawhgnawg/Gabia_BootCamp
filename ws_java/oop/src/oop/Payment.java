package oop;

public class Payment {
  
  private String name;
  private int price;
  private int count;
  private int total;
  
  public Payment() {
    
  }
  
  public Payment(String name, int price, int count, int total) {
    this.name = name;
    this.price = price;
    this.count = count;
    this.total = total;
  }
  
  public void order(String name, int price, int count) {
    this.name = name;
    this.price = price;
    this.count = count;
  }
  
  public void calc() {
    if (this.name.equals("짜장면")) {
      this.total = this.price * this.count;
    } else if (this.name.equals("삼겹살")) {
      this.total = this.price * this.count;
    } else if (this.name.equals("아메리카노")) {
      this.total = this.price * this.count;
    } else {
      this.total = 0;
    }
    System.out.println("주문 내역");
    System.out.println("---------------------");
    System.out.println("메뉴: " + this.name);
    System.out.println("가격: " + String.format("%,d", this.price) + " 원");
    System.out.println("수량: " + this.count);
    System.out.println("합계: " + String.format("%,d", this.total) + " 원");
    System.out.println("---------------------");
  }
  
  

}
