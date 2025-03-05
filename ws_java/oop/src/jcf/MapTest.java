package jcf;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

  public static void main(String[] args) {
//    HashMap map = new HashMap();
    Map map = new HashMap();  // interface 의 사용 목적은 구현체를 쉽게 교체하기 위해서이다.
    
    map.put("year", 2025);
    map.put("season", "fall");
    map.put("date", new Date());
    
    System.out.println((int) map.get("year") + 1);
    System.out.println(((String) map.get("season")).toUpperCase());
    System.out.println(((Date) map.get("date")).toLocaleString());

  }

}
