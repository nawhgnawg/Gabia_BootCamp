package inter;

// Payment 추상 클래스를 상속받는 Pay2015 클래스
public class Pay2017 extends Payment {
  
  @Override
  public void card() {
    System.out.println("Credit card 1.0% discount");
  }

  @Override
  public void mobile() {
    System.out.println("Commission 100₩");    
  }

}
