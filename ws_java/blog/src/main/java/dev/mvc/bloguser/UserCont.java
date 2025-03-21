package dev.mvc.bloguser;

import dev.mvc.tool.Tool;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/create")
    public String create(@ModelAttribute("userVO") UserVO userVO) {
        return "/bloguser/create";
    }

    @GetMapping("/article")
    public String createArticle(@ModelAttribute("userVO") UserVO userVO) {
        return "/bloguser/article";
    }

    @PostMapping("/create")
    public String create(Model model, @Valid UserVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/bloguser/create";
        }
        int cnt = userProc.create(userVO);
        log.info("cnt: {}", cnt);

        if (cnt == 1) {
            model.addAttribute("code", Tool.CREATE_SUCCESS);
            model.addAttribute("username", userVO.getUsername());
            model.addAttribute("useremail", userVO.getUseremail());
        } else {
            model.addAttribute("code", Tool.CREATE_FAIL);
        }
        model.addAttribute("cnt", cnt);

        return "/bloguser/msg";
    }

    @GetMapping("/list_all")
    public String list_all(Model model) {
        ArrayList<UserVO> list = userProc.list_all();
        model.addAttribute("list", list);

        return "/bloguser/list_all";
    }

    @GetMapping("/article_list_all")
    public String article_list_all(Model model) {
        ArrayList<UserVO> list = userProc.list_all();
        model.addAttribute("list", list);

        return "/bloguser/article_list_all";
    }

    @GetMapping("/read")
    public String read(Model model, @RequestParam(value = "userno", defaultValue = "1") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        return "/bloguser/read";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam(value = "userno", defaultValue = "0") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        return "/bloguser/update";
    }

    @PostMapping("/update")
    public String update(Model model, @Valid UserVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/bloguser/update";
        }
        int cnt = userProc.update(userVO);

        if (cnt == 1) {
            model.addAttribute("code", Tool.UPDATE_SUCCESS);
            model.addAttribute("username", userVO.getUsername());
            model.addAttribute("useremail", userVO.getUseremail());
        } else {
            model.addAttribute("code", Tool.UPDATE_FAIL);
        }

        return "/bloguser/msg";
    }
}

