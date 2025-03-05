package oop;

public class Ex3 {

  public static void main(String[] args) {
    
    
   int avg = 0;
    
    try {
      // 전달 인자를 try 안에 넣어줘야 Exception 이 생기면 catch 로 넘어감
      int tot = Integer.parseInt(args[0]); // String -> int
      int cnt = Integer.parseInt(args[1]);
      
      avg = tot / cnt;
      
      System.out.println("총점: " + tot);
      System.out.println("과목수: " + cnt);
      System.out.println("평균: " + avg);
      
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println("진행을 계속합니다.");
      avg = avg + 10;
      System.out.println("평균 추가 점수 부여: " + avg);
    }
    
    
  }

}
