package oop;

public class Constructor {
  
  String brand;
  String cpu;
  int ram;
  int hdd;
  boolean odd;
  String graphic;
  int usb20;
  int usb30;
  boolean wifi;
  String comcase;
  
  // 기본 생성자, 인수 있는 생성자가 선언될시 자동으로 만들어지지 않음!
  // 자동 생성 조건: 인수 있는 생성자가 없어야함.
  public Constructor() {
    // 기본 생성자, 선언 권장
  }

  public Constructor(String brand, String cpu, int ram) {
    this.brand = brand;
    this.cpu = cpu;
    this.ram = ram;
  }

  public Constructor(String brand, String cpu, int ram, int hdd, boolean odd) {
    this.brand = brand;
    this.cpu = cpu;
    this.ram = ram;
    this.hdd = hdd;
    this.odd = odd;
  }

  public Constructor(String brand, String cpu, int ram, int hdd, boolean odd, String graphic,
      int usb20, int usb30, boolean wifi, String comcase) {
    this.brand = brand;
    this.cpu = cpu;
    this.ram = ram;
    this.hdd = hdd;
    this.odd = odd;
    this.graphic = graphic;
    this.usb20 = usb20;
    this.usb30 = usb30;
    this.wifi = wifi;
    this.comcase = comcase;
  }

  
  
  
}
