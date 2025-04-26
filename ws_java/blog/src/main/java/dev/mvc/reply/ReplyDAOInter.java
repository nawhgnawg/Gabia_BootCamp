package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReplyDAOInter {
  /** 댓글 생성 */
  public int create(ReplyVO replyVO);

  /** 댓글 목록 */
  public List<ReplyVO> list();

  /** 특정 유저 댓글 목록 */
  public List<ReplyUserVO> list_user_join();

  /** 댓글 목록 */
  public List<ReplyVO> list_by_contentsno(int contentsno);

  /** 댓글 목록 */
  public List<ReplyUserVO> list_by_contentsno_join(int contentsno);

  /** 댓글 목록 */
  public int checkPasswd(Map<String, Object> map);

  /** 댓글 목록 */
  public int delete(int replyno);

  /** 댓글 목록 */
  public List<ReplyUserVO> list_by_contentsno_join_add(HashMap<String, Object> map);
  
}

