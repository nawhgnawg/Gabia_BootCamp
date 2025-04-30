package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.mvc.tool.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

@Component("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {

    @Autowired
    Security security;

    @Autowired
    private ReplyDAOInter replyDAO;

    @Override
    public int create(ReplyVO replyVO) {
        return replyDAO.create(replyVO);
    }

    @Override
    public List<ReplyVO> list() {
        return replyDAO.list();
    }

    @Override
    public List<ReplyUserVO> list_user_join() {
        List<ReplyUserVO> list = replyDAO.list_user_join();

        for (ReplyUserVO replyUserVO:list) {
          String content = replyUserVO.getContent();
          content = Tool.convertChar(content);
          replyUserVO.setContent(content);
        }

        return list;
    }

    @Override
    public List<ReplyVO> list_by_contentsno(int contentsno) {
        List<ReplyVO> list = replyDAO.list_by_contentsno(contentsno);
        String content = "";

        for (ReplyVO replyVO:list) {
          content = replyVO.getContent();
          content = Tool.convertChar(content);
          replyVO.setContent(content);
        }

        return list;
    }

    @Override
    public List<ReplyUserVO> list_by_contentsno_join(int contentsno) {
        List<ReplyUserVO> list = replyDAO.list_by_contentsno_join(contentsno);
        String content = "";

        for (ReplyUserVO replyUserVO:list) {
          content = replyUserVO.getContent();
          content = Tool.convertChar(content);
          replyUserVO.setContent(content);
        }
        return list;
    }


    @Override
    public int delete(int replyno) {
        return replyDAO.delete(replyno);
    }

    @Override
    public List<ReplyUserVO> list_by_contentsno_join_add(HashMap<String, Object> map) {
        int record_per_page = 2;

        int beginOfPage = ((Integer) map.get("replyPage") - 1) * record_per_page;

        int startNum = beginOfPage + 1;
        int endNum = beginOfPage + record_per_page;
        map.put("startNum", startNum);
        map.put("endNum", endNum);

        List<ReplyUserVO> list = replyDAO.list_by_contentsno_join_add(map);
        String content = "";

        for (ReplyUserVO replyUserVO:list) {
          content = replyUserVO.getContent();
          content = Tool.convertChar(content);
          replyUserVO.setContent(content);
        }
        return list;
    }

    @Override
    public int delete_all(int contentsno) {
        return replyDAO.delete_all(contentsno);
    }

}

