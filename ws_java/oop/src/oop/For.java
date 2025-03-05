package oop;

import java.util.Scanner;
import java.util.Random;

public class For {

    public static void main(String[] args) {
        
        // Scanner
/*
        Scanner scan = new Scanner(System.in);
        System.out.print("년도: ");   // 라인 변경 안
        // String year = scan.nextLine();  // return String
        int year = scan.nextInt();
        System.out.println(year + 1);   // 2026이 아닌 20251이 나옴, 문자열 + 정수
        scan.close();
*/        
        
        // Random
/*
        Random random = new Random();
        // int target = random.nextInt(5) + 1; // 1 ~ 5 사이 숫자
        // System.out.println(target);

        for (int i = 1; i <= 5; i++) {
            int target = random.nextInt(5) + 1;
            if (target == 4) {
                continue;
            }
            System.out.print(target + " ");
        }
*/        
        
        
        // for 
/*        
        for (int i = 1; i <= 10; i = i + 2) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        for (int i = 9; i >= 1; i = i - 2) {
            System.out.print(i + " ");
        }
*/        
        

    }

}
