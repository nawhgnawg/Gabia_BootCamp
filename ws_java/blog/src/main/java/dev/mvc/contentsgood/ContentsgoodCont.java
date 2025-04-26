package dev.mvc.contentsgood;

import dev.mvc.bloguser.UserProcInter;
import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVOMenu;
import dev.mvc.contents.ContentsProcInter;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/contentsgood")
public class ContentsgoodCont {
    @Autowired
    @Qualifier("dev.mvc.bloguser.UserProc")
    private UserProcInter userProc;

    @Autowired
    @Qualifier("dev.mvc.category.CategoryProc")
    private CategoryProcInter categoryProc;

    @Autowired
    @Qualifier("dev.mvc.contents.ContentsProc") // @Component("dev.mvc.contents.ContentsProc")
    private ContentsProcInter contentsProc;

    @Autowired
    @Qualifier("dev.mvc.contentsgood.ContentsgoodProc")
    private ContentsgoodProcInter contentsgoodProc;

    /**
     * POST 요청시 새로고침 방지, POST 요청 처리 완료 → redirect → url → GET → forward -> html 데이터 전송
     */
    @GetMapping(value = "/post2get")
    public String post2get(Model model, @RequestParam(name="url", defaultValue = "") String url) {
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return url;
    }

    @PostMapping(value="/create")
    @ResponseBody
    public String create(HttpSession session, @RequestBody ContentsgoodVO contentsgoodVO) {
        System.out.println("-> 수신 데이터:" + contentsgoodVO.toString());

        int userno = 1; // test
        // int userno = (int)session.getAttribute("userno"); // 보안성 향상
        contentsgoodVO.setUserno(userno);

        int cnt = contentsgoodProc.create(contentsgoodVO);

        JSONObject json = new JSONObject();
        json.put("res", cnt);

        return json.toString();
    }

    /**
     * 목록
     * http://localhost:9092/contentsgood/list_all
     */
    @GetMapping(value = "/list_all")
    public String list_all(Model model) {
        ArrayList<ContentsContentsgoodUserVO> list = contentsgoodProc.list_all_join();
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return "contentsgood/list_all";
    }

    /**
     * 삭제 처리
     * http://localhost:9092/contentsgood/delete?calendarno=1
     */
    @PostMapping(value = "/delete")
    public String delete_proc(HttpSession session, Model model,
                              @RequestParam(name="contentsgoodno", defaultValue = "0") int contentsgoodno,
                              RedirectAttributes ra) {
        // 관리자 로그인 확인
        if (userProc.isAdmin(session)) {
            ContentsgoodVO contentsgoodVO = contentsgoodProc.read(contentsgoodno);
            int contentsno = contentsgoodVO.getContentsno();
            contentsProc.decreaseRecom(contentsno);        // 추천 카운트 감소

            contentsgoodProc.delete(contentsgoodno);
            return "redirect:/contentsgood/list_all";
        } else {
            // 정상적인 로그인이 아닌 경우 로그인 유도
            return "redirect:/bloguser/login_cookie_need?url=/contentsgood/delete?contentsgoodno=" + contentsgoodno;
        }
    }
}