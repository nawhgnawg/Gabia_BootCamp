package inter;

// 추상 클래스로만 구성되어 있다.
public interface PaymentInter {
  
  public void cash(); // abstract 생략 가능 
  
  public void card(); // 카드 결재는 구현을 못함.
  
  public void mobile(); // 모바일 결재도 구현을 미룸.

}
