package oop;

public class CalcUse {

  public static void main(String[] args) {
    
    Calc calc = new Calc(7, 3);
    
    int result = calc.add();
    System.out.println(result);
    
    System.out.println("add: " + calc.add());
    System.out.println("sub: " + calc.sub());
    System.out.println("mul: " + calc.mul());
    System.out.println("div: " + calc.div());
    System.out.println("------");
    
    calc.setSu1(100);
    calc.setSu2(30);
    System.out.println("su1 -> " + calc.getSu1());
    System.out.println("su2 -> " + calc.getSu2());
    System.out.println("------");
    
    System.out.println("add: " + calc.add());
    System.out.println("sub: " + calc.sub());
    System.out.println("mul: " + calc.mul());
    System.out.println("div: " + calc.div());
    
  }

}
