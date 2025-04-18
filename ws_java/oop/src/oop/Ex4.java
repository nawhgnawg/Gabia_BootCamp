package oop;

public class Ex4 {

  public static void main(String[] args) {
    int avg = 0;
    
    try {
      
      int tot = Integer.parseInt(args[0]); // String -> int
      int cnt = Integer.parseInt(args[1]);
      
      avg = tot / cnt;
      
      System.out.println("총점: " + tot);
      System.out.println("과목수: " + cnt);
      System.out.println("평균: " + avg);
      
//      String str1 = null;   // 메모리를 할당받지 못함, str1 변수만 선언됨.
//      System.out.println(str1.toUpperCase()); // NullPointException 발생
      
    } catch (NumberFormatException e) {
      System.out.println("정수를 입력하세요.");
      
    } catch (ArithmeticException e) {
      System.out.println("0으로 나눌 수 없습니다.");
      
    } catch (NullPointerException e) {
      System.out.println("객체가 생성되지 않았습니다.");
      
    } catch (Exception e) {
      System.out.println("알 수 없는 예외가 발생했습니다.");
      
    } finally {
      System.out.println("진행을 계속합니다.");
      avg = avg + 10;
      System.out.println("평균 추가 점수 부여: " + avg);
    }
    
    
  }

}
