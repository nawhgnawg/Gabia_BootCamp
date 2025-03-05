package oop;

import java.util.Scanner;

public class CalcbmiTest {

  public static void main(String[] args) {
    
    Scanner scan = new Scanner(System.in);
    
    System.out.print("몸무게(kg): "); 
    double kg = Integer.parseInt(scan.nextLine());
    
    System.out.print("키(신장, cm): "); 
    double cm = Integer.parseInt(scan.nextLine());
    
    Calcbmi calcBmi = new Calcbmi(kg, cm);
    calcBmi.calc();
    System.out.printf("BMI: %,.2f", calcBmi.getBmi());
    
    scan.close();
    
  }

}
