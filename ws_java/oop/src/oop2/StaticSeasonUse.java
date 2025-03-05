package oop2;

public class StaticSeasonUse {

  public static void main(String[] args) {
    System.out.println(StaticSeason.SPRING);
    StaticSeason.SPRING = "봄";
    System.out.println(StaticSeason.SPRING);
    
    StaticSeason obj1 = new StaticSeason();
    obj1.cnt_static = obj1.cnt_static + 1; 
    obj1.cnt_field = obj1.cnt_field + 1;
    
    System.out.println(obj1.cnt_static + " " + obj1.cnt_field);  
    
    StaticSeason obj2 = new StaticSeason();
    obj2.cnt_static = obj2.cnt_static + 1; 
    obj2.cnt_field = obj2.cnt_field + 1;
    
    System.out.println(obj2.cnt_static + " " + obj2.cnt_field);  
    
    StaticSeason obj3 = new StaticSeason();
    obj3.cnt_static = obj3.cnt_static + 1; 
    obj3.cnt_field = obj3.cnt_field + 1;
    
    System.out.println(obj3.cnt_static + " " + obj3.cnt_field);  
    
    StaticSeason.cnt_static = StaticSeason.cnt_static + 1; // 권장
    System.out.println(StaticSeason.cnt_static);
    
  }

}
