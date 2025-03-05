package oop;

public class Calc {
  int su1;
  int su2;
  
  public Calc() {
    
  }

  public Calc(int su1, int su2) {
    this.su1 = su1;
    this.su2 = su2;
  }
  
  public int add() {
    return this.su1 + this.su2;
  }
  
  public int sub() {
    return this.su1 - this.su2;
  }
  
  public int mul() {
    return this.su1 * this.su2;
  }
  
  public int div() {
    return this.su1 / this.su2; // 정수 / 정수 -> return 정수 
  }
  
  
  // setter, 변수에 값을 저장하는 목적
  public void setSu1(int su1) {
    this.su1 = su1;
  }
  
  public void setSu2(int su2) {
    this.su2 = su2;
  }
  
  // getter, 변수의 값을 조회하는 목적
  public int getSu1() {
    return this.su1;
  }
  
  public int getSu2() {
    return this.su2;
  }
  
  
}
