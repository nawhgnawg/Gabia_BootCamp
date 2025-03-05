package oop;


public class Condition_cycle {

    public static void main(String[] args) {
        
        // switch ~ case ~
        System.out.println("switch 문 실행 시작");
        int month = 1;
        
        switch(month) {
        case 1:
          System.out.println("1월 January");
          break;    // break 를 사용하지 않으면 원하지 않는 결과 값 발생
        case 2:
          System.out.println("2월 February");
          break;
        case 3:
          System.out.println("3월 March");
          break;
        default:
          System.out.println("1/4 분기만 가능합니다.");
        }
        
        System.out.println("switch 문 실행 종료");
        System.out.println("--------------------------------------------------------------------------------");
        
        
        // while, while 조건에 if 조건식이 들어가면 실수할 확률이 높기때문에 따로 빼는게 좋음
        System.out.println("while 문 실행 시작");
        int cnt = 0;
        while (true) {
            cnt = cnt + 1;
            
            if (cnt % 2 == 0 && cnt % 3 == 0 && cnt % 4 == 0 && cnt % 5 == 0) {
                System.out.println("-> " + cnt);
                break;
            }
        }
        System.out.println("while 문 실행 종료");
        System.out.println("--------------------------------------------------------------------------------");
        
        
        // do ~ while
        System.out.println("do ~ while 문 실행 시작");
        int avg = -60;
        
        do {
            System.out.println("평균: " +avg);
        } while (avg >= 0);
        
        System.out.println("do ~ while 문 실행 종료");
        
        
        // 
        
    }

}
