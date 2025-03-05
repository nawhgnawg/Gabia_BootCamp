package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda3 {

  public static void main(String[] args) {
    
    List<String> seasons = Arrays.asList("spring", "summer", "fall", "winter");
    // 일반적인 구현
    Collections.sort(seasons, new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        return a.compareTo(b); // ASC
      }
    });
    
    for (String season:seasons) {
      System.out.println(season);
    }
    
    System.out.println("--------------");
    
    Collections.sort(seasons, new Comparator<String>() {
      @Override
      public int compare(String a, String b) {
        return b.compareTo(a); // DESC
      }
    });  
    
    for (String season:seasons) {
      System.out.println(season);
    }
   
  }
  
}
