package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapExampleWithStream {

  public static void main(String[] args) {
    // 정수로 이루어진 리스트 생성
    List<Integer> numbers = new ArrayList<Integer>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    numbers.add(4);
    numbers.add(5);
    
    System.out.println(numbers.stream()); // java.util.stream.ReferencePipeline$Head@3fb4f649

    // 'map' 함수와 스트림을 사용하여 각 숫자 제곱
    // item: numbers의 객체가 순차적으로 자동 할당
    List<Integer> squaredNumbers = numbers.stream().map(item -> item * item).collect(Collectors.toList());

    // 결과 출력
    System.out.println("Original Numbers: " + numbers);
    System.out.println("Squared Numbers: " + squaredNumbers);
}
  
}
