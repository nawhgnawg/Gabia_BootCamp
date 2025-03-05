package jcf;

import java.util.ArrayList;

public class GenericsPrint {
  
  public void print(ArrayList<String> season) {
    for (String item : season) {
      System.out.println(item.toUpperCase());
    }
  }
  
  // 요소의 타입은 메소드 구분에 반영이 되지 않는다. 즉 메서드 오버로딩이 되지 않는다.
/*
  public void print(ArrayList<Integer> season) {
    for (String item : season) {
      System.out.println(item.toUpperCase());
    }
  }
*/
  
  // 함수명을 다르게 해줘야 함
  public void printPrice(ArrayList<Integer> price) {
    for (int item : price) {
      System.out.println(String.format("%,d", item));
    }
  }
  
  // 질문 > 그러면 타입을 String과 Integer의 부모인 Object를 사용하면 안될까? -> 그렇게되면 제네릭을 쓰는 의미가 없어지기 때문에 안됨!
  
}
