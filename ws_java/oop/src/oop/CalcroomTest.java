package oop;

import java.util.Scanner;

public class CalcroomTest {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);  // 키보드 입력

    System.out.print("원룸의 width를 입력하세요(m): ");   // print: 라인 변경 안됨 
    int width = Integer.parseInt(scan.nextLine());      // 라인 단위 입력
    // System.out.println(width);

    System.out.print("원룸의 height를 입력하세요(m): ");   // print: 라인 변경 안됨 
    int height = Integer.parseInt(scan.nextLine());      // 라인 단위 입력
    // System.out.println(height);
    
    Calcroom calcroom = new Calcroom(width, height);
    calcroom.calc();
    System.out.println("원룸의 크기: " + calcroom.getJegob() + " m² " + calcroom.getPysu() + " 평");
    System.out.printf("원룸의 크기: %d m² %.2f 평", calcroom.getJegob(), calcroom.getPysu());
    
    scan.close();

  }

}
