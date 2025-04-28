package dev.mvc.memo;

import dev.mvc.bloguser.UserProcInter;
import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVOMenu;
import dev.mvc.contentsgood.ContentsgoodVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/memo")
public class MemoCont {

    @Autowired
    @Qualifier("dev.mvc.bloguser.UserProc")
    private UserProcInter userProc;

    @Autowired
    @Qualifier("dev.mvc.category.CategoryProc")
    private CategoryProcInter categoryProc;

    @Autowired
    @Qualifier("dev.mvc.memo.MemoProc")
    private MemoProcInter memoProc;

    @PostMapping("/create")
    @ResponseBody
    public String create(@RequestBody MemoVO memoVO) {
        int cnt = memoProc.create(memoVO);

        JSONObject obj = new JSONObject();
        obj.put("cnt", cnt); // 성공하면 1

        log.info("obj: {}", obj);
        return obj.toString();
    }

    @GetMapping("/list_all")
    public String list_all(Model model) {
        ArrayList<MemoUserVO> list = memoProc.list_all_join();
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return "memo/list_all";
    }

    @GetMapping("/read")
    public String read(Model model) {

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);



        return "memo/read";
    }

    @PostMapping("/delete")
    public String delete(HttpSession session, Model model,
                         @RequestParam(value = "memono", defaultValue = "0") int memono) {

        // 관리자 로그인 확인
        if (userProc.isAdmin(session)) {
            memoProc.delete(memono);
            return "redirect:/memo/list_all";
        } else {
            // 정상적인 로그인이 아닌 경우 로그인 유도
            return "redirect:/bloguser/login_cookie_need";
        }
    }
}
