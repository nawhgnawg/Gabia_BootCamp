package oop;

import tool.Tool;

public class ToolTest {

  public static void main(String[] args) {
    System.out.println(Tool.comma(2500000));
    
    
    boolean sw = Tool.isImage("Flower.jpg");
    System.out.println(sw);
    
    sw = Tool.isImage("Flower.gif");
    System.out.println(sw);
    
    sw = Tool.isImage("Flower.png");
    System.out.println(sw);
    
    
    String title = Tool.getStringByLength("라면 1개 외상 달라던 청년..취업 후 슈퍼 사장에 20만원 봉투", 5); // return 라면 1개...
    System.out.println(title);
    
    title = Tool.getStringByLength("라면 1개", 5); // return 라면 1개
    System.out.println(title);
    
    
    String result = Tool.checkNull(null);
    System.out.println(result);   // 아무것도 안나옴
    
    result = Tool.checkNull("Spring");
    System.out.println(result);   // "Spring"
    
    System.out.println(Tool.getRandomDate());
    System.out.println(Tool.getRandomDateHeader("MP3"));
    System.out.println(Tool.getRandomDateHeader("MP4"));
    
  }

}
