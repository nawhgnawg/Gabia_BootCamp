package oop2;

public class EnumSeasonUse {

  public static void main(String[] args) {
    System.out.println("1) " + EnumSeason.SPRING);
    
    EnumSeason enumSeason = EnumSeason.SPRING;
    System.out.println("2) " + enumSeason.SPRING); //  static way 방법이 아님
    
    String web = "spring".toUpperCase();
    if (EnumSeason.SPRING.equals(web)) {
      System.out.println("3) 봄");
    }
    
    // String season = EnumSeason.SPRING;  // X

    if (enumSeason.name().equals(web)) { // 열거형 이름으로 비교
      System.out.println("4) 봄");
    }
    
    if (enumSeason.toString().equals(web)) { // 열거형 값으로 비교
      System.out.println("5) 봄");
    }
    
    if (enumSeason.toString().equals("march")) {
      System.out.println("6) 봄");
    }
    
  }

}