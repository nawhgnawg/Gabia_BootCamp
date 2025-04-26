package dev.mvc.contentsgood;

import java.util.ArrayList;
import java.util.HashMap;

public interface ContentsgoodDAOInter {
  /**
   * 등록, 추상 메소드
   */
  public int create(ContentsgoodVO contentsgoodVO);
  
  /**
   * 모든 목록
   */
  public ArrayList<ContentsgoodVO> list_all();
  
  /**
   * 삭제
   */
  public int delete(int contentsgoodno);
  
  /**
   * 특정 컨텐츠의 특정 회원 추천 갯수 산출
   */
  public int hartCnt(HashMap<String, Object> map);

  /**
   * 현재 추천수
   */
  public int currentHartCnt(int contentsno);

  /**
   * 조회
   */
  public ContentsgoodVO read(int contentsgoodno);

  /**
   * contentsno, memberno로 조회
   */
  public ContentsgoodVO readByContentsnoUserno(HashMap<String, Object> map);
  
  /**
   * 모든 목록, 테이블 3개 join
   */
  public ArrayList<ContentsContentsgoodUserVO> list_all_join();
  
}




