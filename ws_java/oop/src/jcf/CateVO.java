package jcf;

public class CateVO {
  
  private int cateno;
  private String name;
  private String rdate;
  private int cnt;
  
  public CateVO() {
    
  }

  public CateVO(int cateno, String name, String rdate, int cnt) {
    this.cateno = cateno;
    this.name = name;
    this.rdate = rdate;
    this.cnt = cnt;
  }

  public int getCateno() {
    return cateno;
  }

  public String getName() {
    return name;
  }

  public String getRdate() {
    return rdate;
  }

  public int getCnt() {
    return cnt;
  }

}
