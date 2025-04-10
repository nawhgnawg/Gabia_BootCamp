package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 스프링은 자동화 되어있어서 이런 기법은 안씀
public class MovieDAO {
  private Connection con = null;               // DBMS 연결
  private PreparedStatement pstmt = null;      // SQL 실행
  private ResultSet rs = null;                 // SELECT 결과를 저장
  private StringBuffer sql = null;             // SQL 문장
  private int count = 0;                       // 처리된 레코드 갯수

//  private String className = "com.mysql.jdbc.Driver"; // 구형
  private String className = "com.mysql.cj.jdbc.Driver"; // 신형

  private String url = "jdbc:mysql://localhost:3306/javadb"; 
  private String user = "javauser"; 
  private String password = "1234";
  
  /**
   * 등록
   * @param title
   * @param grade
   * @param actor
   * @return
   */
  public int create(String title, double grade, String actor) {
    
    try {
      Class.forName(this.className); // memory로 클래스를 로딩함, 객체는 생성하지 않음.
      this.con = DriverManager.getConnection(this.url, this.user, this.password ); // MySQL 연결

      this.sql = new StringBuffer();
      this.sql.append("insert into movie(title, grade, actor, rdate)");
      this.sql.append("values(?, ?, ?, now())");


      this.pstmt = this.con.prepareStatement(this.sql.toString()); // SQL 실행 객체 생성
      this.pstmt.setString(1, title);
      this.pstmt.setDouble(2, grade);
      this.pstmt.setString(3, actor);
      
      this.count = this.pstmt.executeUpdate(); // INSERT, UPDATE, DELETE
      // this.rs = this.pstmt.executeQuery();    // SELECT 

      if (this.count == 1){
        System.out.println("처리 성공");
      } else {
        System.out.println("처리 실패");
      }    
    } catch (SQLException e) {
      System.out.println("SQL 실행중 예외 발생");
      e.printStackTrace(); // 예외가 발생하기까지의 실행과정을 출력
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버가 없는것 같습니다.");
      e.printStackTrace();
    } finally {
      // try{ if (this.rs != null){ this.rs.close(); } }catch(Exception e){ } 
      try{ if (this.pstmt != null){ this.pstmt.close(); } }catch(Exception e){ }
      try{ if (this.con != null){ this.con.close(); } }catch(Exception e){ }
    } 
    
    return this.count; 
  }
  
  
  /**
   * 목록
   */
  public void list() {
    
    try {
      Class.forName(this.className); // memory로 클래스를 로딩함, 객체는 생성하지 않음.
      this.con = DriverManager.getConnection(this.url, this.user, this.password ); // MySQL 연결

      this.sql = new StringBuffer();
      this.sql.append(" select movieno, title, grade, actor, rdate");
      this.sql.append(" from movie");
      this.sql.append(" order by movieno ASC");

      this.pstmt = this.con.prepareStatement(this.sql.toString()); // SQL 실행 객체 생성      
//      this.count = this.pstmt.executeUpdate(); // INSERT, UPDATE, DELETE
      this.rs = this.pstmt.executeQuery();    // SELECT
      
      while (true) {
        if (rs.next()) { // 첫번째 레코드로 이동 -> 다음 레코드로 이동 -> 계속 호출시 전체 레코드로 이동
          System.out.print(rs.getInt("movieno") + ". ");
          System.out.print(rs.getString("title") + " ");
          System.out.print("(" + rs.getDouble("grade") + ") ");
          System.out.print("출연: " + rs.getString("actor") + " ");
          System.out.print(rs.getString("rdate").substring(0, 10));
        } else {
          break;
        }
        System.out.println();
      }
         
    } catch (SQLException e) {
      System.out.println("SQL 실행중 예외 발생");
      e.printStackTrace(); // 예외가 발생하기까지의 실행과정을 출력
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버가 없는것 같습니다.");
      e.printStackTrace();
    } finally {
      try { if (this.rs != null){ this.rs.close(); } }catch(Exception e){ } 
      try { if (this.pstmt != null){ this.pstmt.close(); } }catch(Exception e){ }
      try { if (this.con != null){ this.con.close(); } }catch(Exception e){ }
    } 
  }
  
  
  /**
   * 조회
   */
  public void read(int movieno) {
    
    try {
      Class.forName(this.className); // memory로 클래스를 로딩함, 객체는 생성하지 않음.
      this.con = DriverManager.getConnection(this.url, this.user, this.password ); // MySQL 연결

      this.sql = new StringBuffer();
      this.sql.append(" select movieno, title, grade, actor, rdate");
      this.sql.append(" from movie");
      this.sql.append(" where movieno = ?");

      this.pstmt = this.con.prepareStatement(this.sql.toString()); // SQL 실행 객체 생성
      this.pstmt.setInt(1, movieno);
//      this.count = this.pstmt.executeUpdate(); // INSERT, UPDATE, DELETE
      this.rs = this.pstmt.executeQuery();    // SELECT, 값을 꺼냄
      
      if (rs.next()) { // 첫번째 레코드로 이동 -> 다음 레코드로 이동 -> 계속 호출시 전체 레코드로 이동
          System.out.print(rs.getInt("movieno") + ". ");  // rs.get자료형(ColumnLabel)
          System.out.print(rs.getString("title") + " ");
          System.out.print("(" + rs.getDouble("grade") + ") ");
          System.out.print("출연: " + rs.getString("actor") + " ");
          System.out.print(rs.getString("rdate").substring(0, 10));
      } else {
        System.out.println("해당 번호의 영화가 없습니다.");
      }
        System.out.println();
         
    } catch (SQLException e) {
      System.out.println("SQL 실행중 예외 발생");
      e.printStackTrace(); // 예외가 발생하기까지의 실행과정을 출력
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버가 없는것 같습니다.");
      e.printStackTrace();
    } finally {
      try { if (this.rs != null){ this.rs.close(); } }catch(Exception e){ } 
      try { if (this.pstmt != null){ this.pstmt.close(); } }catch(Exception e){ }
      try { if (this.con != null){ this.con.close(); } }catch(Exception e){ }
    } 
  }
  
  
  /**
   * 수정
   * @param movieno
   * @param title
   * @param grade
   * @param actor
   * @return
   */

  public int update(String title, double grade, String actor, int movieno) {
    
    try {
      Class.forName(this.className); // memory로 클래스를 로딩함, 객체는 생성하지 않음.
      this.con = DriverManager.getConnection(this.url, this.user, this.password ); // MySQL 연결

      this.sql = new StringBuffer();
      this.sql.append(" update movie set title = ?, grade = ?, actor = ?, rdate = now()");
      this.sql.append(" where movieno = ?");


      this.pstmt = this.con.prepareStatement(this.sql.toString()); // SQL 실행 객체 생성
      this.pstmt.setString(1, title);
      this.pstmt.setDouble(2, grade);
      this.pstmt.setString(3, actor);
      this.pstmt.setInt(4, movieno);
      
      this.count = this.pstmt.executeUpdate(); // INSERT, UPDATE, DELETE
      // this.rs = this.pstmt.executeQuery();    // SELECT 

      if (this.count == 1){
        System.out.println("처리 성공");
      } else {
        System.out.println("처리 실패");
      }    
    } catch (SQLException e) {
      System.out.println("SQL 실행중 예외 발생");
      e.printStackTrace(); // 예외가 발생하기까지의 실행과정을 출력
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버가 없는것 같습니다.");
      e.printStackTrace();
    } finally {
      // try{ if (this.rs != null){ this.rs.close(); } }catch(Exception e){ } 
      try{ if (this.pstmt != null){ this.pstmt.close(); } }catch(Exception e){ }
      try{ if (this.con != null){ this.con.close(); } }catch(Exception e){ }
    } 
    
    return this.count; 

  }
  
  /**
   * 삭제
   * @param movieno
   * @return
   */
public int delete(int movieno) {
    
    try {
      Class.forName(this.className); // memory로 클래스를 로딩함, 객체는 생성하지 않음.
      this.con = DriverManager.getConnection(this.url, this.user, this.password ); // MySQL 연결

      this.sql = new StringBuffer();
      this.sql.append(" delete from movie");
      this.sql.append(" where movieno = ?");


      this.pstmt = this.con.prepareStatement(this.sql.toString()); // SQL 실행 객체 생성
      this.pstmt.setInt(1, movieno);
      
      this.count = this.pstmt.executeUpdate(); // INSERT, UPDATE, DELETE
      // this.rs = this.pstmt.executeQuery();    // SELECT 

      if (this.count == 1){
        System.out.println("처리 성공");
      } else {
        System.out.println("처리 실패");
      }    
    } catch (SQLException e) {
      System.out.println("SQL 실행중 예외 발생");
      e.printStackTrace(); // 예외가 발생하기까지의 실행과정을 출력
    } catch (ClassNotFoundException e) {
      System.out.println("JDBC 드라이버가 없는것 같습니다.");
      e.printStackTrace();
    } finally {
      // try{ if (this.rs != null){ this.rs.close(); } }catch(Exception e){ } 
      try{ if (this.pstmt != null){ this.pstmt.close(); } }catch(Exception e){ }
      try{ if (this.con != null){ this.con.close(); } }catch(Exception e){ }
    } 
    
    return this.count; 

  }
}
