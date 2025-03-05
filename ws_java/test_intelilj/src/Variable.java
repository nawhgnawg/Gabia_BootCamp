import java.util.Random;
import java.util.Scanner;
import java.util.random.*;

public class Variable {

    public static void main(String[] args) {

/*
        int python = 95;
        int java = 100;
        int algorithm = 60;

        int total = (python + java + algorithm);
        double avg = total / 3;

        String resultScore;

        if (avg >= 60) {
            resultScore = "합격";
        } else {
            resultScore = "다음에 응시해주세요.";
        }

        System.out.println("Python: " + python);
        System.out.println("java: " + java);
        System.out.println("algorithm: " + algorithm);
        System.out.println("평균: " + String.format("%.1f", avg));
        System.out.println(resultScore);
*/

        // -----------------------------------------------------------------

/*
        int wonkum = 15000;
        wonkum = wonkum * 10000;

        if (wonkum > 100_000_000) {
            System.out.println("원금이 1억을 초과합니다.");
            System.out.println("-------------------");

            int won1 = (int) (100_000_000 * 0.02);
            int won2 = wonkum - 100_000_000;
            int won3 = (int) (won2 * 0.01);

            System.out.println("100,000,000원 -> " + String.format("%,d", won1) + " 원");
            System.out.println(String.format("%,d", won2) + " 원 -> " + String.format("%,d", won3) + " 원");
            // System.out.println(String.format("원금1 = %,d\n원금2 = %,d", won1, won3));
        } else {
            System.out.println("원금이 1억 미만입니다.");
            System.out.println("------------------");

            int won1 = (int) (wonkum * 0.02);
            // System.out.println(String.format("%,d", wonkum) + " 원 -> " + String.format("%,d", won1) + " 원");
            System.out.println(String.format("%,d 원 -> %,d 원", wonkum, won1));
            // System.out.println(String.format("원금1 = %,d", won1));
        }
*/

        // -----------------------------------------------------------------

/*
        int score = 95;
        String grade;

        if (score >= 90) {
            grade = "A";
        } else if (score >= 80) {
            grade = "B";
        } else if (score >= 70) {
            grade = "C";
        } else if (score >= 60) {
            grade = "D";
        } else if (score >= 0) {
            grade = "F";
        } else {
            grade = "없음";
            System.out.println("음수는 처리하지 않습니다.");
        }

        System.out.println("등급: " + grade);
        System.out.println("------------------------------");
*/

        // -----------------------------------------------------------------

/*
        int height = 177;
        double weight = 60.0;

        double bmi = (weight / (height * height) * 10000);
        System.out.println(bmi);
        String grade1 = "";

        if (bmi < 18.5) {
            grade1 = "저체중";
        }
        else if (bmi < 23) {
            grade1 = "정상";
        }
        else if (bmi < 25) {
            grade1 = "과체중";
        }
        else if (bmi >=25) {
            grade1 = "비만";
        }

        System.out.println("키(신장, cm): " + height);
        System.out.println("몸무게(kg): " + weight);
        System.out.println("BMI: " + String.format("%.2f", bmi));
        System.out.println("판정: " + grade1);
*/

        // -----------------------------------------------------------------

/*
        int carNum = 1;
        String msg = "";

        if (carNum == 1 || carNum == 2) {
            msg = "월요일 주차 가능합니다.";
        } else if (carNum == 3 || carNum == 4) {
            msg = "화요일 주차 가능합니다.";
        } else if (carNum == 5 || carNum == 6) {
            msg = "수요일 주차 가능합니다.";
        } else if (carNum == 7 || carNum == 8) {
            msg = "목요일 주차 가능합니다.";
        } else if (carNum == 9 || carNum == 0) {
            msg = "금요일 주차 가능합니다.";
        } else {
            msg = "인실 할 수 없는 번호입니다.";
        }

        System.out.println("차량 번호: " + carNum);
        System.out.println(msg);
*/

        // -----------------------------------------------------------------


/*
        int score3 = 202;
        String msg1 = "";

        if (score3 >= 1 && score3 <= 9) {
            msg1 = "1의 자리";
        } else if (score3 >= 10 && score3 <= 99) {
            msg1 = "10의 자리";
        } else if (score3 >= 100 && score3 <= 999) {
            msg1 = "100의 자리";
        } else if (score3 >= 1000) {
            msg1 = "1000의 자리 이상";
        }

        System.out.println("수: " + score3 + " -> " + msg1);
*/
        // -----------------------------------------------------------------

/*
        String grade = "p";
        int price = 30000;

        double discount = 0;

        if (grade == "P" || grade == "p") {
            discount = 0.07;
        } else if (grade == "G" || grade == "g") {
            discount = 0.06;
        } else if (grade == "S" || grade == "s") {
            discount = 0.05;
        } else if (grade == "F" || grade == "f") {
            discount = 0.02;
        } else if (grade == "N" || grade == "n") {
            discount = 0;
        }

        int discountPrice = (int) (price - (price * discount));

        System.out.println("회원 등급: " + grade.toUpperCase());
        System.out.println("도서 정가: " + String.format("%,d" ,price) + " 원");
        System.out.println("결제 금액: " + String.format("%,d", discountPrice) + " 원");
    }
*/

        // -----------------------------------------------------------------

/*
        int data = 201;
        double price = 0;   // 금액
        double dPrice = 0;  // 단가
        double bPrice = 0;  // 기본 요금

        if (data <= 200) {
            dPrice = 120;
            bPrice = 910;
        } else if (data >= 201 && data <= 400) {
            dPrice = 214.6;
            bPrice = 1600;
        } else if (data > 400) {
            dPrice = 307.3;
            bPrice = 7300;
        }

        price = (data * dPrice) + bPrice;
        System.out.println("사용량(kwh): " + data);
        System.out.println("단가(원): " + String.format("%,.1f", dPrice)+ " 원");
        System.out.println("기본요금(원): " + String.format("%,.0f", bPrice)+ " 원");
        System.out.println("금액(원): " + String.format("%,.0f", price) + " 원");
*/

        // -----------------------------------------------------------------

/*
        String id = "user1";
        String password = "1234";

        if (id == "user1") {
            if (password == "1234") {
                System.out.println("로그인 성공");
            }
            else {
                System.out.println("패스워드가 일치하지 않습니다.");
            }
        } else {
            System.out.println("존재하지 않는 아이디 입니다.");
        }
*/


    }
}
