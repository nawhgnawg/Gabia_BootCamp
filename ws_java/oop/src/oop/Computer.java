package oop;

public class Computer {
    
  String name;
  int price;
  double screen;
  String cpu;
  
  // 숨겨진 기본 생성자, 생성자 함수, 메서드
  public Computer() {
    System.out.println("Computer 객체 생성");
    name = "LG i7";
    price = 1_670_000;
    screen = 17.3;
    cpu = "Core i7 12세대";
  }
  
  // 메서드 오버로딩 - 같은 이름을 가지는 메서드지만 파라미터가 다른 메서드
  public Computer(String name, int price, double screen, String cpu) {
    this.name = name;
    this.price = price;
    this.screen = screen;
    this.cpu = cpu;
  }
  
  // 메서드 오버로딩 - 같은 이름을 가지는 메서드지만 파라미터가 다른 메서드
  public Computer(String name, int price, double screen) {
    this.name = name;
    this.price = price;
    this.screen = screen;
    this.cpu = "Intel i9";
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setPrice(int price) {
    this.price = price;
  }
  
  public int getPrice() {
    return this.price;
  }
  
  public void setScreen(double screen) {
    this.screen = screen;
  }
  
  public double getScreen() {
    return this.screen;
  }
  
  public void setCpu(String cpu) {
    this.cpu = cpu;
  }
  
  public String getCpu() {
    return this.cpu;
  }
  
}
