package jcf;

import java.util.ArrayList;

public class Generics1 {
  
  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<Integer> price = new ArrayList<Integer>();
    
    list.add("spring");
//    list.add(2025);   // X -> ArrayList 의 Data Type 이 String 으로 지정이 되어 있음
    list.add("summer");
    list.add("fall");
    list.add("winter");
    
    price.add(1_000_000);
    price.add(1_500_000);
    price.add(2_000_000);
    price.add(2_500_000);
    
    System.out.println(list.get(0));  // 형변환을 안해줘도 되는 장점이 있다.
    
    System.out.println("------------------");
    
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).toUpperCase());
    }
    
    System.out.println("------------------");
    
    for (String item : list) {
      System.out.println(item.toUpperCase());
    }
    
    System.out.println("------------------");
    
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).toUpperCase() + ": " + String.format("%,d", price.get(i)));
      
    }
    
    
    
    
  }
  
}
