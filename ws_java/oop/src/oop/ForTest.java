package oop;

import java.util.Random;
import java.util.Scanner;

public class ForTest {
    
    public static void main(String[] args) {
/*             
        for (int i = 1; i < 1001; i++) {
            if (i % 2 == 0 && i % 3 == 0 && i % 4 == 0 && i % 5 == 0) {
                System.out.print(i + " ");
            }
        }
*/
        
/*        
        Scanner sc = new Scanner(System.in);

        int even = 0;
        int odd = 0;

        for (int i = 0; i < 100; i++) {
            System.out.print("입력: ");
            int num = sc.nextInt();

            if (num == 9999) {
                System.out.println("사용해 주셔서 감사합니다.");
                break;
            }

            if (num % 2 == 0) {
                even++;
                System.out.println("짝수입니다.");
            }
            else {
                odd++;
                System.out.println("홀수입니다.");
            }
            System.out.println("짝수 갯수: " + even + " 홀수 갯수: " + odd);
            System.out.println("----------------------------");
            
        }
        sc.close();
*/        
        
/*
        Scanner sc = new Scanner(System.in);

        Random random = new Random();
        int num = random.nextInt(10) + 1;
        System.out.println(num);

        for (int i = 1; i <= 3; i++) {
            System.out.print("예상되는 수 입력: ");
            int pNum = sc.nextInt();

            if (pNum == num) {
                System.out.println("맞습니다.");
                if (i == 1) {
                    System.out.println("상금: 10만원");
                } else if (i == 2) {
                    System.out.println("상금: 5만원");
                } else {
                    System.out.println("상금: 1만원");
                }
                break;
            } else if (pNum > num) {
                System.out.println("틀렸습니다.");
                System.out.println("좀더 작은 수를 입력하세요.");
            } else if (pNum < num) {
                System.out.println("틀렸습니다.");
                System.out.println("좀더 큰 수를 입력하세요.");
            }

            if (i == 3) {
                System.out.println("최대 기회 3회를 모두 사용");
            }
        }
        
        sc.close();
*/
        
/*
        for (int i = 1; i < 10; i++) {
            System.out.println(3 + " X " + i + " = " + (3 * i));
        }
        
        for (int i = 1; i < 10; i++) {
            System.out.printf("3 X %d = %d\n", i, (3 * i));
        }
        
        for (int i = 9; i > 0; i--) {
            System.out.printf("2 X %d = %d\n", i, (2 * i));
        }
*/
        
        for (int i = 2; i < 10; i++) {
            System.out.printf("%d 단\n", i);
            System.out.println("----------------------------");
            for (int j = 1; j < 10; j++) {
                System.out.printf("%d X %d = %d\n", i, j, (i * j));
            }
           System.out.println();
        }
        
    }

}
