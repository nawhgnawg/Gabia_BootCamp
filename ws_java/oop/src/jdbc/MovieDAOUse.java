package jdbc;

public class MovieDAOUse {

  public static void main(String[] args) {
    MovieDAO movieDAO = new MovieDAO();
//    System.out.println(movieDAO.create("베테랑", 10, "황정민")); 
//    System.out.println(movieDAO.create("기생충", 10, "송강호")); 
//    movieDAO.list();
//    movieDAO.read(2);
//    movieDAO.update("러브 액추얼리", 10.0, "휴 그랜트", 4);
    movieDAO.delete(1);
  }

}
