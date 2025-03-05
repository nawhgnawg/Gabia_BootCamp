package oop;

import java.util.Scanner;

public class TossTest {

  public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    System.out.print("원금: ");
    int wonkum = scan.nextInt();
    
    Toss toss = new Toss(wonkum);
    toss.calc();
    System.out.printf("원금: %,d 원\n", toss.getWonkum());
    System.out.printf("년이자: %,d 원\n", toss.getYear());
    System.out.printf("월이자: %,d 원\n", toss.getMonth());
    System.out.printf("오늘이자: %,d 원\n", toss.getDay());
    System.out.printf("세후이자: %,d 원\n", toss.getIja());
    
    scan.close();
  }

}
