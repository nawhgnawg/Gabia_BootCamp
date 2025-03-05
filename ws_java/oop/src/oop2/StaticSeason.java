package oop2;

public class StaticSeason {
  //static 변수는 메모리에 소스 상주시 최초 1회만 초기화됨.
  public static String SPRING = "SPRING";
  public static String SUMMER = "SUMMER";
  public static String FALL = "FALL";
  public static String WINTER = "WINTER";
  
  public static int cnt_static = 0;
  public int cnt_field = 0;
}
