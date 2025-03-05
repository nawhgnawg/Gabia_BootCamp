
package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda4 {

  public static void main(String[] args) {
    
    List<String> seasons = Arrays.asList("spring", "summer", "fall", "winter");

    // Lambda 사용
    seasons.sort((a, b) -> a.compareToIgnoreCase(b)); // ASC
    seasons.forEach(season -> System.out.println(season));
    
    System.out.println("--------------");
    seasons.sort((a, b) -> b.compareToIgnoreCase(a)); // DESC
    seasons.forEach(season -> System.out.println(season));
    
    
  }
  
}
