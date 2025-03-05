package oop2;

import java.util.Date;

public class Final {
  //객체 생성해야 사용 가능, 최초 값 변경 불가능, 상수 선언 
  public final String com = "(주)행복 여행사";

  // 객체 생성 안해도 사용 가능, 최초 값 변경 불가능, 상수 선언, 변수 공유 목적, Enum과 비슷한 기능 지원
  // public static final String TEL = "☎ 02-1111-1111";  
  public final static String TEL = "☎ 02-1111-1111";
 
  public static final String HOBBY = null;
  public static final Date START = null;
}
