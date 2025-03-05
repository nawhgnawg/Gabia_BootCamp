package oop;

public class ArrayTest {

    public static void main(String[] args) {
        
        // 1차원 배열
        int[] years = new int[3];
        years[0] = 2023;
        years[1] = 2024;
        years[2] = 2025;
        
        System.out.println(years[0]);
        System.out.println(years[1]);
        System.out.println(years[2]);
        
        
        int index = 0;
        
        while (true) {
            System.out.println("-> " + years[index]);
            if (index == 2) {
                break;
            }
            index = index + 1;
        }
        
        
        // 2차원 배열
        // 1 2 3
        // 4 5 6
        // 7 8 9
        
        int[][] score = new int[3][3];  // 3행 3열
        score[0][0] = 1; score[0][1] = 2; score[0][2] = 3;
        score[1][0] = 4; score[1][1] = 5; score[1][2] = 6;
        score[2][0] = 7; score[2][1] = 8; score[2][2] = 9;
        
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < score[0].length; j++) {
                System.out.print(score[i][j] + " ");
            }
            System.out.println();
        }
        
        
        int[][] score2 = new int[3][3];
        int i = 0;
        int cnt = 0;
        while (i < score2.length) {
            score2[i][0] = cnt + 1; score2[i][1] = cnt + 2; score2[i][2] = cnt + 3;
            i++;
            cnt += 3;
        }
        
        int j = 0;;
        while (j < score2.length) {
            System.out.print(score2[j][0] + " ");
            System.out.print(score2[j][1] + " ");
            System.out.print(score2[j][2] + " ");
            System.out.println();
            j++;
        }
        
        // 3 차원 배열
        int[][][] score3 = new int[3][2][4]; // 3면 2행 4열
        score3[0][0][0] = 90; score3[0][0][1] = 85; score3[0][0][2] = 100;  score3[0][0][3] = 95;
        score3[0][1][0] = 80; score3[0][1][1] = 75; score3[0][1][2] = 90;  score3[0][1][3] = 85;
        
        score3[1][0][0] = 80; score3[1][0][1] = 80; score3[1][0][2] = 75;  score3[1][0][3] = 100;
        score3[1][1][0] = 50; score3[1][1][1] = 60; score3[1][1][2] = 70;  score3[1][1][3] = 100;
        
        score3[2][0][0] = 100; score3[2][0][1] = 95; score3[2][0][2] = 65;  score3[2][0][3] = 80;
        score3[2][1][0] = 50; score3[2][1][1] = 60; score3[2][1][2] = 70;  score3[2][1][3] = 80;
        
        int side = 0;
        
        while (side < 3) {
          System.out.println(score3[side][0][0] + " " + score3[side][0][1] + " " + score3[side][0][2] + " " + score3[side][0][3]);
          System.out.println(score3[side][1][0] + " " + score3[side][1][1] + " " + score3[side][1][2] + " " + score3[side][1][3]);
          System.out.println();
          side = side + 1;
        }
        
        
        
        
        
    }

}
