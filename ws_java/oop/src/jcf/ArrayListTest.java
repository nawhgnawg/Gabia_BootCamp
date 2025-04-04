package jcf;

import java.util.ArrayList;
import java.util.Date;

public class ArrayListTest {

  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    
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
