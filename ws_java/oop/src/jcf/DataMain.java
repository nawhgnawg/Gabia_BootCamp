package jcf;

public class DataMain {

  public static void main(String[] args) {
    DataVO vo = new DataVO("인셉션", 10.0, "디카프리오");
    DataProcess process = new DataProcess();
    
    process.setData(vo);
    DataVO movie = (DataVO) process.getData();
    System.out.println(movie);
    System.out.println("영화 이름: " + movie.getTitle());
    System.out.println("평점: " + movie.getScore());
    System.out.println("배우: " + movie.getActor());
    
  }
}
