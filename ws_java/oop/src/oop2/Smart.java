package oop2;

public class Smart extends PDA {
  private String name;
  
  public Smart () {
    this.name = "Android";
  }
  
  public void internet() {
    System.out.println("인터넷 기능: "+ this.name);
  } 
}