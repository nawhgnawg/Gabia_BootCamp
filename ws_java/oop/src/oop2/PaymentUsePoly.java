package oop2;

public class PaymentUsePoly {

  public static void main(String[] args) {
    // Cash cash = new Cash();
    // Cash cash = new Card(); // Type mismatch: cannot convert from Card to Cash
    Payment payment = new Cash();
    payment.order("짜장면", 6000, 2);
    payment.calc();
    payment.payment(); // ★, 자식 우선 인식

    payment = new Card();
    payment.order("삼겹살", 13000, 5);
    payment.calc();
    payment.payment(); // ★, 자식 우선 인식
    
    payment = new Mobile();
    payment.order("아메리카노", 2000, 3);
    payment.calc();
    payment.payment(); // ★, 자식 우선 인식
    
    Object object = new Cash();
    object = new Card();
    object = new Mobile();
    
    // Object: 객체 저장소
    
    // Cash cash = new Payment(); // Type mismatch: cannot convert from Payment to Cash
    Cash cash2 = new Cash();
    Payment payment2 = cash2;
    // Cash cash3 = payment2; // X
    Cash cash3 = (Cash)payment2; // 객체 형변환 ★★★★★★★★★★
    
    // Object
    Cash cash4 = new Cash();
    Object object2 = cash4; 
    Cash cash5 = (Cash)object2;
  }

}