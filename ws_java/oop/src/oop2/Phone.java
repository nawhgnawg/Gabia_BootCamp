package oop2;

public class Phone {
  String phone_name;

  public Phone() {
    this.phone_name = "모토롤라";
  }

  public void tel() {
    System.out.println("전화 기능: " + this.phone_name);
  }

  public void sms() {
    System.out.println("문자 기능: " + this.phone_name);
  }

}
