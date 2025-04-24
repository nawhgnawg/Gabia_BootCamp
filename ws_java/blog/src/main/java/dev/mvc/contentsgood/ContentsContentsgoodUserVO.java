package dev.mvc.contentsgood;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-- 테이블 3개 join
//SELECT r.contentsgoodno, r.rdate, r.contentsno, c.title as c_title, r.userno, m.id, m.username
//FROM contents c, contentsgood r, bloguser m
//WHERE c.contentsno = r.contentsno AND r.userno = m.userno
//ORDER BY contentsgoodno DESC;

@Getter @Setter @ToString
public class ContentsContentsgoodUserVO {
  /** 컨텐츠 추천 번호 */
  private int contentsgoodno;
  
  /** 등록일 */
  private String rdate;
  
  /** 컨텐츠 번호 */
  private int contentsno;
  
  /** 제목 */
  private String c_title = "";
  
  /** 회원 번호 */
  private int userno;
  
  /** 아이디(이메일) */
  private String useremail = "";
  
  /** 회원 성명 */
  private String username = "";
  
}



