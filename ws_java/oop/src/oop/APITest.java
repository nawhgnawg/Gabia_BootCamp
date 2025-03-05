package oop;

import tool.Tool;

public class APITest {

  public static void main(String[] args) {
    String season1 = "Spring";
    String season2 = new String("Spring");   // 권장하지 않음.
    
    System.out.println(season1);
    System.out.println(season2);
    
    System.out.println(season1.toUpperCase());
    System.out.println(season2.toUpperCase());
    
    System.out.println(season1.toLowerCase());
    System.out.println(season2.toLowerCase());
    
    // == : 주소 비교
    if (season1 == "Spring") {
      System.out.println("season1 == Spring");
    }
    
    if (season2 == "Spring") {
      System.out.println("season2 == Spring");
    }
    
    // 주소가 같음 -> 근데 왜 season2 == "Spring" 은 출력이 안될까? 
    System.out.println(season1.hashCode());
    System.out.println(season2.hashCode());
    
    
    // equals() : 값 비교
    if (season1.equals("Spring")) {
      System.out.println("season1 == Spring");
    }
    
    if (season2.equals("Spring")) {
      System.out.println("season2 == Spring");
    }
    
    // toLower/UpperCase, endsWith()
    System.out.println("Flower.jpg".toLowerCase());
    System.out.println("floower.jpg".endsWith("jpg"));
    System.out.println("floower.jpg".toLowerCase().endsWith("jpg"));
    
    // substring()
    System.out.println("라면 1개 외상 달라던 청년..취업 후 슈퍼 사장에 20만원 봉투".substring(0, 6));
    
    String str = null;
    System.out.println(str);
    System.out.println("------------------");
    
    // String, StringBuffer의 차이점  -> String은 객체를 계속 생성 되지만, StringBuffer는 문자열이 계속 추가되어도 최초 객체에 부여받은 해시코드가 바뀌지 않는다. 즉 객체 1개
    String s = "Spring";
    System.out.println(s + " " + s.hashCode());
    s += "Summer";
    System.out.println(s + " " + s.hashCode());
    s += "Fall";
    System.out.println(s + " " + s.hashCode());
    s += "Winter";
    System.out.println(s + " " + s.hashCode());
    
    System.out.println("------------------");
    
    StringBuffer sb = new StringBuffer();
    sb.append("Spring");
    System.out.println(sb + " " + sb.hashCode());
    sb.append("Summer");
    System.out.println(sb + " " + sb.hashCode());
    sb.append("Fall");
    System.out.println(sb + " " + sb.hashCode());
    sb.append("Winter");
    System.out.println(sb + " " + sb.hashCode());
  }

}
