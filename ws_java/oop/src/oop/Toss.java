package oop;

public class Toss {
  
  int wonkum;
  int year;
  int month;
  int day;
  int ija;
  
  public Toss() {
    
  }
  
  public Toss(int wonkum) {
    this.wonkum = wonkum;
  }
  
  public void calc() {
    this.year = (int) (this.wonkum * 0.02);
    this.month = (this.year / 12);
    this.day = this.year / 365;
    this.ija = (int) (this.day - (this.day * 0.14));
  }
    
  public int getWonkum() {
    return this.wonkum;
  }
  
  public int getYear() {
    return this.year;
  }
  
  public int getMonth() {
    return this.month;
  }
  
  public int getDay() {
    return this.day;
  }
  
  public int getIja() {
    return this.ija;
  }

}
