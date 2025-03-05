package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class MovieProc {

  public static void main(String[] args) {
    
    String name;
    String seat;
    int sungin_cnt = 0;
    int stu_cnt = 0;
    
    int sungin_price = 0;
    int stu_price = 0;
    int sungin = 0;
    int stu = 0;
    int price = 0;
    int point = 0;
    
    DecimalFormat df = new DecimalFormat("#,###,##0 원");
    
    File movie = null;    // 읽을 파일
    FileReader fr = null; // 파일 읽기
    BufferedReader br = null; // 읽은 파일 내용을 메모리에 저장
    
    // 파일 쓰기 관련
    File movie_proc = null; // 기록할 파일
    FileWriter fw = null;     // 파일 쓰기
    PrintWriter pw = null;  // 메모리에 기록
    
    try {
      // 파일 읽기 관련 객체 생성
      movie = new File("/Users/imgwanghwan/kd/ws_java/oop/src/oop/movie.csv");
      fr = new FileReader(movie);
      br = new BufferedReader(fr);
      
      // 파일 쓰기 관련 객체 생성
      movie_proc = new File("/Users/imgwanghwan/kd/ws_java/oop/src/oop/movie_proc.csv");
      fw = new FileWriter(movie_proc);
      pw = new PrintWriter(fw);
      
      String line = br.readLine();
      
      while (true) {
        line = br.readLine();
        
        if (line == null) {
          break;
        }
        
        String[] split_line = line.split(",");
        
        name = split_line[0].trim();
        seat = split_line[1].trim();
        sungin_cnt = Integer.parseInt(split_line[2].trim());
        stu_cnt = Integer.parseInt(split_line[3].trim());
        
        /*
         * - 좌석(seat)별 성인 금액 단가
         *  A: 11,000
         *  B: 10,000 
         *  C: 9,000
         * 
         * - 좌석별 학생 금액 단가, 학생은 성인 금액의 20% 할인
         *  A: 11,000 -> 8,800 
         *  B: 10,000 -> 8,000 
         *  C: 9,000 -> 7,200
         * 
         * - 성인 금액: 단가 * 성인수 
         * - 학생 금액: 단가 * 학생수 
         * - 결재 금액: 성인 금액 + 학생 금액 
         * - 포인트: 결재 금액의 5% 계산
         */
        
        if (seat.equals("A")) {
          sungin = 11000;
          stu = 8800;
        } else if (seat.equals("B")) {
          sungin = 10000;
          stu = 8000;
        } else if (seat.equals("B")) {
          sungin = 9000;
          stu = 7200;
        }
        
        sungin_price = sungin * sungin_cnt;  
        stu_price = stu * stu_cnt;
        
        price = sungin_price + stu_price;
        point = (int) (price * 0.05);
        
        
        System.out.println("영화명: " + name);
        System.out.println("좌석: " + seat);
        System.out.println("성인수: " + sungin_cnt + " 명");
        System.out.println("학생수: " + stu_cnt + " 명");
        System.out.println("성인 금액: " + df.format(sungin_price));
        System.out.println("학생 금액: " + df.format(stu_price));
        System.out.println("결재 금액: " + df.format(price));
        System.out.println("포인트(5%): " + df.format(point));
        System.out.println("-------------------------------------");
        
        
        pw.println("영화명: " + name);  // 파일 기록
        pw.println("좌석: " + seat);
        pw.println("성인수: " + sungin_cnt + " 명"); 
        pw.println("학생수: " + stu_cnt + " 명");
        pw.println("성인 금액: " + df.format(sungin_price));
        pw.println("학생 금액: " + df.format(stu_price));
        pw.println("결재 금액: " + df.format(price));
        pw.println("포인트(5%): " + df.format(point));
        pw.println("-------------------------------");
        
        System.out.println(" → complete.");
      }
      
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 객체는 생성의 반대 순서로 닫는것을 권장
      try {
        pw.close();
      } catch (Exception e) { }
      try {
        fw.close();
      } catch (Exception e) { }
      try {
        br.close();
      } catch (Exception e) { }
      try {
        fr.close();
      } catch (Exception e) { }
      
    }
    
  }

}
