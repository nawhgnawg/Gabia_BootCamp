package dev.mvc.contentsgood;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ContentsgoodVO {
  /** 컨텐츠 추천 번호 */
  private int contentsgoodno;
  
  /** 등록일 */
  private String rdate;
  
  /** 컨텐츠 번호 */
  private int contentsno;
  
  /** 회원 번호 */
  private int userno;
  
}



