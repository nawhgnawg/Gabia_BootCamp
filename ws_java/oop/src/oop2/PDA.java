package oop2;

public class PDA extends Phone {
  private String name;
  
  public PDA() {
    this.name = "Samsung";
  }
  
  public void mms() {
    System.out.println("멀티미디어: " + this.name);
  }
  
  public void memo() {
    System.out.println("메모장 기능: " + this.name);
  }
}