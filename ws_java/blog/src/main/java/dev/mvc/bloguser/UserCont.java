package dev.mvc.bloguser;

import dev.mvc.category.CategoryProc;
import dev.mvc.category.CategoryVOMenu;
import dev.mvc.tool.Tool;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/bloguser")
@Slf4j
public class UserCont {

    @Autowired
    private UserProc userProc;

    @Autowired
    private CategoryProc categoryProc;

    /**
     * 회원 중복
     * @param useremail
     * @return
     */
    @GetMapping("/checkID") // http://localhost:9092/bloguser/checkID?useremail=admin
    @ResponseBody
    public String checkID(@RequestParam(name="useremail", defaultValue = "") String useremail) {
        System.out.println("-> useremail: " + useremail);
        int cnt = userProc.checkID(useremail);

        JSONObject obj = new JSONObject();
        obj.put("cnt", cnt);

        return obj.toString();
    }

    /**
     * 회원 등록 폼 (GET)
     * http://loacalhost:9092/bloguser/create
     */
    @GetMapping("/create")
    public String create_form(Model model, @ModelAttribute("userVO") UserVO userVO) {
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);
        return "/bloguser/create";
    }

    /**
     * 회원 등록 처리 (POST)
     * http://loacalhost:9092/bloguser/create
     */
    @PostMapping("/create")
    public String create_proc(Model model, @ModelAttribute("userVO") UserVO userVO) {
        int checkID_cnt = userProc.checkID(userVO.getUseremail());
        System.out.println(checkID_cnt);
        if (checkID_cnt == 0) {
            userVO.setUsergrade(1); // 기본 회원 1
            int cnt = userProc.create(userVO);

            if (cnt == 1) {
                model.addAttribute("code", "create_success");
                model.addAttribute("username", userVO.getUsername());
                model.addAttribute("useremail", userVO.getUseremail());
            } else {
                model.addAttribute("code", "create_fail");
            }

            model.addAttribute("cnt", cnt);
        } else { // id 중복
            model.addAttribute("code", "duplicte_fail");
            model.addAttribute("cnt", 0);
        }

        return "bloguser/msg"; // /templates/bloguser/msg.html
    }

    /**
     * 회원 전체 조회
     * http://loacalhost:9092/bloguser/list
     */
    @GetMapping("/list")
    public String list(Model model) {
        ArrayList<UserVO> list = userProc.list_all();

        model.addAttribute("list", list);

        return "bloguser/list_all";  // /templates/bloguser/list.html
    }

    /**
     * 단일 회원 조회
     * http://loacalhost:9092/bloguser/read
     */
    @GetMapping("/read")
    public String read(Model model, @RequestParam(name="userno", defaultValue = "") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return "/bloguser/read";
    }

    /**
     * 회원 수정 처리 (POST)
     * http://loacalhost:9092/bloguser/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("userVO") UserVO userVO) {

        int cnt = userProc.update(userVO);

        if (cnt == 1) {
            model.addAttribute("code", "update_success");
            model.addAttribute("username", userVO.getUsername());
            model.addAttribute("id", userVO.getUseremail());
        } else {
            model.addAttribute("code", "update_fail");
        }
        model.addAttribute("cnt", cnt);

        return "/bloguser/msg";
    }

    @GetMapping("/delete")
    public String delete(Model model, int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        return "/bloguser/delete";
    }

    @PostMapping("/delete")
    public String delete_process(Model model, int userno) {
        int cnt = userProc.delete(userno);

        if (cnt == 1) {
            return "redirect:/bloguser/list";
        } else {
            model.addAttribute("code", "delete_fail");
            return "bloguser/msg";
        }
    }

    @GetMapping("/update_grade_forward/{userno}")
    public String update_grade_forward(Model model, @PathVariable("userno") int userno) {
        userProc.update_grade_forward(userno);

        return "redirect:/bloguser/list_all";
    }

    @GetMapping("/update_grade_backward/{userno}")
    public String update_grade_backward(Model model, @PathVariable("userno") int userno) {
        userProc.update_grade_backward(userno);

        return "redirect:/bloguser/list_all";
    }

    @GetMapping("/list_search")
    public String list_all(Model model,
                           @ModelAttribute("userVO") UserVO userVO,
                           @RequestParam(name = "word", defaultValue = "") String word) {

        ArrayList<UserVO> list = userProc.list_search_user(word);
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        model.addAttribute("word", word);
        model.addAttribute("now_page", "1");

        return "/bloguser/list_search";
    }

}

