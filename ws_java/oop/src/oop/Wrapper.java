package oop;

public class Wrapper {

  public static void main(String[] args) throws InterruptedException {
    int year = 2025;
    Integer year_object = 2025;   // wrapper class -> 값을 저장하려는 목적보다는 다양한 추가 기능을 하는 함수를 사용하는 목적
    
    System.out.println(year_object.intValue());
    System.out.println(year_object);
    
    year = Integer.parseInt("2025");
    System.out.println(Integer.parseInt("16", 16));  // 16 진수 계산 -> return 22 
    System.out.println(Integer.parseInt("1010", 2)); // 2 진수 계산 -> return 10 
    System.out.println("---------------");
    System.out.println(Integer.toBinaryString(10)); // 10 진수 -> 2 진수 
    System.out.println(Integer.toHexString(14));    // 10 진수 -> 16 진수  
    
    System.out.println(String.format("%,d", 2500000));  
    System.out.println(String.format("%,.1f", 123.56));
    
    Thread.sleep(1000); // 1초 정지
    
    System.out.println(Math.round(123.56));
    
    long num = Math.round(123.56);
    System.out.println(num);
    
    
    
    
    
  }

}
