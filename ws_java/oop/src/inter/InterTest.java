package inter;

public class InterTest {

  public static void main(String[] args) {
    PaymentInter payment = new Pay2015Impl();
    System.out.println("              2015년            ");
    System.out.println("--------------------------------");
    payment.cash();
    payment.card();
    payment.mobile();
    System.out.println("--------------------------------");
    
    payment = new Pay2016Impl();
    System.out.println("              2016년            ");
    System.out.println("--------------------------------");
    payment.cash();
    payment.card();
    payment.mobile();
    System.out.println("--------------------------------");
    
    payment = new Pay2017Impl();
    System.out.println("              2017년            ");
    System.out.println("--------------------------------");
    payment.cash();
    payment.card();
    payment.mobile();
    System.out.println("--------------------------------");
    

  }

}
