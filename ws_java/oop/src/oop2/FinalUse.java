package oop2;

public class FinalUse {

  public static void main(String[] args) {
    // System.out.println(Final.com);  // X
    Final obj = new Final();
    System.out.println(obj.com);
     // obj.com = "렌터카"; // The final field Final.com cannot be assigned.
     // final 변수는 값 변경 불가능
    
    System.out.println(Final.TEL);       // 객체 생성 안하고 사용
    // Final.TEL = "☎ 02-0000-0000"; //  값 변경 불가능
    
  }

}