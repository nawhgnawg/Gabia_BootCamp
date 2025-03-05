package oop;

public class Calcroom {
  
  int width;
  int height;
  int jegob;
  double pysu;
  
  public Calcroom() { // 생성자는 return type을 선언하지 않음.
    
  }

  public Calcroom(int width, int height) { // 생성자는 return type을 선언하지 않음.
    this.width = width;
    this.height = height;
  }
 
  public void calc() { // void: return 값이 없음.
    this.jegob = this.width * this.height;
    this.pysu = this.jegob / 3.3;
  }
  
  public int getJegob() {
    return this.jegob;
  }
  
  public double getPysu() {
    return this.pysu;
  }
  
}
