package oop;

public class Calcbmi {
  
  double kg;
  double cm;
  double bmi;
  
  public Calcbmi() {
    
  }
  
  public Calcbmi(double kg, double cm) {
    this.kg = kg;
    this.cm = cm;
  }
  
  public void calc() {
    this.bmi = (this.kg / (this.cm * this.cm)) * 10000;
  }
  
  public double getBmi() {
    return this.bmi;
  }
}
