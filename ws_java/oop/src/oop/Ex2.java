package oop;

public class Ex2 {

  public static void main(String[] args) {
    
    int tot = Integer.parseInt(args[0]); // String -> int
    int cnt = Integer.parseInt(args[1]);
    
    if (cnt == 0) {
      System.out.println("0으로 나눌 수 없습니다.");
      System.exit(0);
    } else {
      
      int avg = tot / cnt;
      
      System.out.println("총점: " + tot);
      System.out.println("과목수: " + cnt);
      System.out.println("평균: " + avg);
      System.out.println("진행을 계속합니다.");
      avg = avg + 10;
      System.out.println("평균 추가 점수 부여: " + avg);
      
    }
    
    
  }

}
