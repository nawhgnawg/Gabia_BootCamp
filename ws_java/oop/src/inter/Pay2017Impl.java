package inter;

public class Pay2017Impl implements PaymentInter {

  @Override
  public void cash() {
    System.out.println("현금 결재(계좌 번호): 1111-1111-1111");
  }

  @Override
  public void card() {
    System.out.println("Credit card 1.0% discount");
  }

  @Override
  public void mobile() {
    System.out.println("Commission 100₩");
  }

}
