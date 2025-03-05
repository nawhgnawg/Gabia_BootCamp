package exam;

public class TestUse {

  public static void main(String[] args) {
    Test test = new Test();
    
    test = new Test(90, 100, 80);
    test.print();
    System.out.println("----------------");
    test.setJava(50); test.getJava();
    test.setPython(80); test.getPython();
    test.setAlgorithm(100); test.getAlgorithm();
    test.print();
  }

}
