package jcf;

import java.util.ArrayList;

public class CateProcess {
  
  ArrayList<CateVO> list = new ArrayList<CateVO>();
  
  CateVO cate1 = new CateVO(1, "SF", "2023-08-31", 500);
  CateVO cate2 = new CateVO(2, "드라마", "2023-09-01", 300);
  CateVO cate3 = new CateVO(3, "휴먼", "2023-09-05", 300);
  

  public void save(CateVO cate1, CateVO cate2, CateVO cate3) {
    this.list.add(cate1);
    this.list.add(cate2);
    this.list.add(cate3);
  }
  
  public void print(ArrayList<CateVO> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i).getCateno());
      System.out.println(list.get(i).getName());
      System.out.println(list.get(i).getRdate());
      System.out.println(list.get(i).getCnt());
      System.out.println("--------------------");
    }
  }

}
