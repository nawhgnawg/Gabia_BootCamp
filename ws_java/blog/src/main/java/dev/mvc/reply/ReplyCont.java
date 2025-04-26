package dev.mvc.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dev.mvc.bloguser.UserProcInter;
import dev.mvc.contents.ContentsProcInter;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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


    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestBody ReplyVO replyVO) {
        int cnt = replyProc.create(replyVO);

        JSONObject obj = new JSONObject();
        obj.put("cnt",cnt);

        return obj.toString(); // {"cnt":1}

    }

//  @RequestMapping(value="/reply/list.do", method=RequestMethod.GET)
//  public ModelAndView list(HttpSession session) {
//    ModelAndView mav = new ModelAndView();
//    
//    if (adminProc.isAdmin(session)) {
//      List<ReplyVO> list = replyProc.list();
//      
//      mav.addObject("list", list);
//      mav.setViewName("/reply/list"); // /webapp/reply/list.jsp
//
//    } else {
//      mav.setViewName("redirect:/admin/login_need.jsp"); // /webapp/admin/login_need.jsp
//    }
//    
//    return mav;
//  }


    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        if (userProc.isAdmin(session)) {
            List<ReplyUserVO> list = replyProc.list_user_join();
            model.addAttribute("list", list);

            return "reply/list";
        } else {
            return "redirect:/bloguser/login_need";
        }
    }
  
    /**
     * http://localhost:9092/reply/list_by_contentsno?contentsno=1
     */
    @GetMapping("/list_by_contentsno")
    @ResponseBody
    public String list_by_contentsno(@RequestParam(value = "contentsno", defaultValue = "0") int contentsno) {
        List<ReplyVO> list = replyProc.list_by_contentsno(contentsno);

        JSONObject obj = new JSONObject();
        obj.put("list", list);

        return obj.toString();
    }

    /**
     * http://localhost:9092/reply/list_by_contentsno_join?contentsno=53
     */
    @GetMapping("/list_by_contentsno_join")
    @ResponseBody
    public String list_by_contentsno_join(@RequestParam(value = "contentsno", defaultValue = "0") int contentsno) {
        List<ReplyUserVO> list = replyProc.list_by_contentsno_join(contentsno);

        JSONObject obj = new JSONObject();
        obj.put("list", list);

        return obj.toString();
    }

    /**
     * http://localhost:9092/reply/delete?replyno=1&passwd=1234
     * {"delete_cnt":0,"passwd_cnt":0}
     * {"delete_cnt":1,"passwd_cnt":1}
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "replyno", defaultValue = "0") int replyno,
                         @RequestParam(value = "passwd", defaultValue = "") String passwd) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("replyno", replyno);
        map.put("passwd", passwd);

        int passwd_cnt = replyProc.checkPasswd(map);
        int delete_cnt = 0;
        if (passwd_cnt == 1) {
          delete_cnt = replyProc.delete(replyno);
        }

        JSONObject obj = new JSONObject();
        obj.put("passwd_cnt", passwd_cnt);
        obj.put("delete_cnt", delete_cnt);

        return obj.toString();
    }

    /**
     * http://localhost:9092/reply/list_by_contentsno_join_add?contentsno=53&replyPage=1
     */
    @GetMapping("/list_by_contentsno_join_add")
    @ResponseBody
    public String list_by_contentsno_join(@RequestParam(value = "contentsno", defaultValue = "0") int contentsno,
                                          @RequestParam(value = "replyPage", defaultValue = "0") int replyPage) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("contentsno", contentsno);
        map.put("replyPage", replyPage);

        List<ReplyUserVO> list = replyProc.list_by_contentsno_join_add(map);

        JSONObject obj = new JSONObject();
        obj.put("list", list);

        return obj.toString();
    }
}


