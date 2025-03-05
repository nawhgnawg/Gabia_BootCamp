package oop;

public class SplitTest {
  
  public static void main(String[] args) {
    String season = "봄,여름,가을,겨울";
    String[] list = season.split(",");
    
    // 1번
    System.out.println(list[0]);
    System.out.println(list[1]);
    System.out.println(list[2]);
    System.out.println(list[3]);
    System.out.println("------------");
    
    // 2번
    for (String item : list) {
      System.out.println(item);
    }
    System.out.println("------------");
    
    // 3번
    season = "봄, 여름, 가을, 겨울";
    list = season.split(", ");
    for (int i = 0; i < list.length; i++) {
      System.out.println(list[i]);
    }
    System.out.println("------------");
    
    // 4번
    season = "봄,,가을,겨울";
    list = season.split(",");
    for (int i = 0; i < list.length; i++) {
      System.out.println(list[i]);
    }
    System.out.println("------------");
    
    // 5번
    season = "봄,,가을,겨울";
    list = season.split(",");
    for (String item : list) {
      if (item.equals("")) {
        System.out.println("데이터 누락 발견");
      } else {
        System.out.println(item);
      }
        
    }
    System.out.println("------------");
    
  }
  
}
