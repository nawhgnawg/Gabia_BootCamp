package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dev.mvc.bloguser.UserProcInter;
import dev.mvc.contents.ContentsProcInter;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping("/reply")
public class ReplyCont {
    @Autowired
    @Qualifier("dev.mvc.reply.ReplyProc")
    private ReplyProcInter replyProc;

    @Autowired
    @Qualifier("dev.mvc.bloguser.UserProc")
    private UserProcInter userProc;

    @Autowired
    @Qualifier("dev.mvc.contents.ContentsProc")
    private ContentsProcInter contentsProc;

    public ReplyCont(){
        System.out.println("--> ReplyCont created.");
    }

    /**
     * 댓글 등록 처리
     * http://localhost:9092/reply/create
     */
    @PostMapping("/create")
    public String create(ReplyVO replyVO) {

        log.info("replyVO: {}", replyVO);
        replyProc.create(replyVO);

        return "redirect:/contents/read?contentsno=" + replyVO.getContentsno();
    }

    /**
     * 댓글 삭제 처리
     * http://localhost:9092/reply/delete
     * {"delete_cnt":0}
     * {"delete_cnt":1}
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(int replyno, String passwd) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("replyno", replyno);

        int delete_cnt = replyProc.delete(replyno);

        JSONObject obj = new JSONObject();
        obj.put("delete_cnt", delete_cnt);

        return obj.toString();
    }

}


