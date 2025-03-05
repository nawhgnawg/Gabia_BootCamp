package jcf;

public class DataVO {
  
  private String title;
  private double score;
  private String actor;
  
  
  public DataVO() {
    
  }
  
  public DataVO(String title, double score, String actor) {
    this.title = title;
    this.score = score;
    this.actor = actor;
  }

  public String getTitle() {
    return title;
  }

  public double getScore() {
    return score;
  }

  public String getActor() {
    return actor;
  }
  
}
