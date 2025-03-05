
package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lambda5 {

  public static void main(String[] args) {
    
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    // java.util.stream.ReferencePipeline$2@7adf9f5f
    System.out.println(list.stream().filter(n -> n % 2 == 0));
    
    int total = list.stream().filter(n -> n % 2 == 0).mapToInt(Integer::intValue).sum();
    System.out.println(list);
    System.out.println(total);
  }
  
}
