package jcf;

import java.util.Date;

public class ObjectTest1 {

  public static void main(String[] args) {
    Object obj1 = "fall";
    System.out.println(obj1);
    System.out.println(obj1.toString().toUpperCase());
    System.out.println(((String) obj1).toUpperCase());  // Object를 사용했기 때문에 객체 형변환을 하는게 더 나음
    
    System.out.println("--------------------");
    
    System.out.println(obj1.getClass().getName()); // 부모가 가지고있는 객체의 타입
    System.out.println(obj1 instanceof Object);
    System.out.println(obj1 instanceof String);
    System.out.println(obj1 instanceof Integer);
    
    System.out.println("--------------------");
    
    obj1 = 2024; // int -> Integer -> Object 자동으로 클래스 타입으로 변경됨.
    System.out.println(obj1.getClass().getName()); // 부모가 가지고있는 객체의 타입
    System.out.println(obj1 instanceof Object);
    System.out.println(obj1 instanceof String);
    System.out.println(obj1 instanceof Integer);
    
    System.out.println("--------------------");
    
    obj1 = 10.5;
    obj1 = true;
    obj1 = new Date();
    
    // Object 타입은 다양한 객체를 저장 받을 수 있어서 객체 저장소 타입으로 사용됨.
    

  }

}
