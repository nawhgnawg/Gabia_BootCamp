package oop;

public class ConstructorUse {

  public static void main(String[] args) {
    
    Constructor ct1 = new Constructor();
    System.out.println("브랜드: " + ct1.brand);
    System.out.println("CPU: " + ct1.cpu);
    System.out.println("RAM: " + ct1.ram + "GB");
    System.out.println("-----------------------");

    Constructor ct2 = new Constructor("LG", "Core i7 12세대", 16);
    System.out.println("브랜드: " + ct2.brand);
    System.out.println("CPU: " + ct2.cpu);
    System.out.println("RAM: " + ct2.ram + "GB");
    System.out.println("-----------------------");
    
    
  }

}
