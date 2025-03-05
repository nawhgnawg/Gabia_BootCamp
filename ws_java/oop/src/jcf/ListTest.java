package jcf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ListTest {

  public static void main(String[] args) {
//    List itemList = new List();   // Cannot instantiate the type List
    
//    List list = new ArrayList();
    List list = new Vector();       // interface 를 사용하면 추상 메소드를 다양하게 구현한 클래스로 교체해도 소스가 변경이 안됨. 다형성
    
    list.add("fall"); // String(인수/Argument) -> Object(매개 변수/Parameter)
    list.add(2025);   // int -> Integer -> Object
    list.add(true);   // boolean -> Boolean -> Object
    list.add(10.5);   // double -> Double -> Object
    list.add(new Date()); // Date -> Object
    
    Object obj1 = list.get(0);
    String str = (String) obj1;
    
    System.out.println(str.toUpperCase());
    
    System.out.println(((String) obj1).toUpperCase());
    
    // 2025 + 1 -> 2026
    System.out.println((int) list.get(1) + 1);
    System.out.println((Integer) list.get(1) + 1);
    
    System.out.println(((Date) list.get(4)).toLocaleString());  // Deprecated 사용을 권장하지 않음
  }

}
