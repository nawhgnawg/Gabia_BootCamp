package exam;

public class Test {
  
  private int java;
  private int python;
  private int algorithm;
  
  public Test() {
    
  }
  
  public Test(int java, int python, int algorithm) {
    this.java = java;
    this.python = python;
    this.algorithm = algorithm;
  }
  
  public void print() {
    System.out.println("Java: " + this.java);
    System.out.println("Python: " + this.python);
    System.out.println("Algorithm: " + this.algorithm);
  }
  
  public int getJava() {
    return java;
  }

  public void setJava(int java) {
    this.java = java;
  }

  public int getPython() {
    return python;
  }

  public void setPython(int python) {
    this.python = python;
  }

  public int getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(int algorithm) {
    this.algorithm = algorithm;
  }
  
  
  
  
  
}
