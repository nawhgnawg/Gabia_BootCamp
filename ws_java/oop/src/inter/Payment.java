package inter;

public abstract class Payment {
  
  public void cash() {
    System.out.println("현금 결재(계좌 번호): 1111-1111-1111");
  }
  
  public abstract void card(); // 카드 결재는 구현을 못함.
  
  public abstract void mobile(); // 모바일 결재도 구현을 미룸.
}
