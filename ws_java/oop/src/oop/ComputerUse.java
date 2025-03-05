package oop;

public class ComputerUse {

  public static void main(String[] args) {
    
    Computer com = new Computer();
    System.out.println("제품명: " + com.name);
    System.out.println("가격: " + String.format("%,d", com.price) + " 원");
    System.out.println("화면: " + com.screen);
    System.out.println("CPU: " + com.cpu);
    System.out.println("-----------------------");
    
    Computer com2 = new Computer("N95", 320000, 15.6, "intel N95");
    System.out.println("제품명: " + com2.name);
    System.out.println("가격: " + String.format("%,d", com2.price) + " 원");
    System.out.println("화면: " + com2.screen);
    System.out.println("CPU: " + com2.cpu);
    System.out.println("-----------------------");
    
    Computer com3 = new Computer("ASUS", 1200000, 16.3, "i9");
    System.out.println("제품명: " + com3.name);
    System.out.println("가격: " + String.format("%,d", com3.price) + " 원");
    System.out.println("화면: " + com3.screen);
    System.out.println("CPU: " + com3.cpu);
    System.out.println("-----------------------");
    
    Computer com4 = new Computer("Dell", 600000, 14);
    System.out.println("제품명: " + com4.name);
    System.out.println("가격: " + String.format("%,d", com4.price) + " 원");
    System.out.println("화면: " + com4.screen);
    System.out.println("CPU: " + com4.cpu);
    System.out.println("-----------------------");
    
    
  }

}
