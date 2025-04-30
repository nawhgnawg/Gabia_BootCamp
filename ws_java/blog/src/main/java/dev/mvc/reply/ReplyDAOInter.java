package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReplyDAOInter {
  /** 댓글 생성 */
  public int create(ReplyVO replyVO);

  /** 전체 댓글 조회 */
  public List<ReplyVO> list();

  /** 특정 유저 댓글 목록 */
  public List<ReplyUserVO> list_user_join();

  /** 특정글에 대한 댓글 조회 */
  public List<ReplyVO> list_by_contentsno(int contentsno);

  /** 특정글에 대한 전체 댓글 목록 (아이디 포함) */
  public List<ReplyUserVO> list_by_contentsno_join(int contentsno);

  /** 댓글 삭제 */
  public int delete(int replyno);

  /** 댓글 페이징 */
  public List<ReplyUserVO> list_by_contentsno_join_add(HashMap<String, Object> map);

  public int delete_all(int contentsno);
  
}

