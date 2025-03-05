package inter;

public class AbsTest {

  public static void main(String[] args) {
    Payment payment2015 = new Pay2015();
    System.out.println("              2015년            ");
    System.out.println("--------------------------------");
    payment2015.cash();
    payment2015.card();
    payment2015.mobile();
    System.out.println("--------------------------------");
    System.out.println();
    
    Payment payment2016 = new Pay2016();
    System.out.println("              2016년            ");
    System.out.println("--------------------------------");
    payment2016.cash();
    payment2016.card();
    payment2016.mobile();
    System.out.println("--------------------------------");
    System.out.println();
    
    Payment payment2017 = new Pay2017();
    System.out.println("              2017년            ");
    System.out.println("--------------------------------");
    payment2017.cash();
    payment2017.card();
    payment2017.mobile();
    System.out.println("--------------------------------");
    System.out.println();
    
    Payment payment = new Pay2015();
    System.out.println("              2015년            ");
    System.out.println("--------------------------------");
    payment.cash();
    payment.card();
    payment.mobile();
    System.out.println("--------------------------------");
    System.out.println();
    
    payment = new Pay2016();
    System.out.println("              2016년            ");
    System.out.println("--------------------------------");
    payment.cash();
    payment.card();
    payment.mobile();
    System.out.println("--------------------------------");
    System.out.println();
    
    payment = new Pay2017();
    System.out.println("              2017년            ");
    System.out.println("--------------------------------");
    payment.cash();
    payment.card();
    payment.mobile();
    System.out.println("--------------------------------");
    System.out.println();

  }

}
