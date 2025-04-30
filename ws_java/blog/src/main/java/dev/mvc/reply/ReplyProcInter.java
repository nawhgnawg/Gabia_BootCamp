package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ReplyProcInter {
  public int create(ReplyVO replyVO);
  
  public List<ReplyVO> list();
  
  public List<ReplyVO> list_by_contentsno(int contentsno);
  
  public List<ReplyUserVO> list_user_join();

  public List<ReplyUserVO> list_by_contentsno_join(int contentsno);

  public int delete(int replyno);

  public List<ReplyUserVO> list_by_contentsno_join_add(HashMap<String, Object> map);

  public int delete_all(int contentsno);
  
}

