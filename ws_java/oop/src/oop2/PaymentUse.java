package oop2;

public class PaymentUse {

  public static void main(String[] args) {
    Cash cash = new Cash();
    cash.order("짜장면", 6000, 2);
    cash.calc();
    cash.payment();
    
    Card card = new Card();
    card.order("삼겹살", 13000, 5);
    card.calc();
    card.payment();

    Mobile mobile = new Mobile();
    mobile.order("아메리카노", 2000, 3);
    mobile.calc();
    mobile.payment();
    System.out.println();    

  }

}