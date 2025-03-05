package util;

import java.util.ArrayList;
import java.util.List;

public class MapExample {
  
  public static void main(String[] args) {
    // 정수로 이루어진 리스트 생성
    // Interface <- Interface를 구현한 모든 Class들 할당 가능, 변경 가능한 구조임을 나타냄. 
    List<Integer> numbers = new ArrayList<Integer>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    numbers.add(4);
    numbers.add(5);

    // 각 숫자를 제곱하는 'map' 함수를 사용한 예제
    List<Integer> squaredNumbers = new ArrayList<Integer>();
    for (Integer number : numbers) {
        squaredNumbers.add(number * number);
    }

    // 결과 출력
    System.out.println("Original Numbers: " + numbers);
    System.out.println("Squared Numbers: " + squaredNumbers);
}

}
