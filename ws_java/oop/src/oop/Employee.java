package oop;

public class Employee {
  
  int python;
  int java;
  int bigdata;
  double total;
  double avg;
  String msg;
  
  public Employee() {
    
  }
  
  public Employee(int python, int java, int bigdata) {
    this.python = python;
    this.java = java;
    this.bigdata = bigdata;
  }
  
  public void calc() {
    this.total = this.python + this.java + this.bigdata;
    this.avg = this.total / 3;
    
    if (this.avg >= 60) {
      this.msg = "합격";
    } else {
      this.msg = "다음에 응시해주세요.";
    }
  }
  
  public double getAvg() {
    return this.avg;
  }

  
  public String getMsg() {
    return this.msg;
  }

}
