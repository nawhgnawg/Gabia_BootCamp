package oop2;

public class Inhe {

  public static void main(String[] args) {
    Phone phone = new Phone();

    phone.tel();
    phone.sms();
    
    System.out.println();
    
    PDA pda = new PDA();
    pda.tel();
    pda.sms();
    pda.mms();
    pda.memo();

    System.out.println();
    
    Smart smart = new Smart();
    smart.tel();
    smart.sms();
    smart.mms();
    smart.memo();
    smart.internet();
    
    System.out.println();

    FIR fir = new FIR();
    fir.tel();
    fir.sms();
    fir.mms();
    fir.memo();
    fir.internet();
    fir.iot();
    fir.bigdata();
    fir.ai();
    
  }

}