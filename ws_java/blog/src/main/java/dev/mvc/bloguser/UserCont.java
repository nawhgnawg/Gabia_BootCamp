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

    /**
     * 회원 등록 폼 (GET)
     * http://loacalhost:9092/bloguser/create
     */
    @GetMapping("/create")
    public String create(@ModelAttribute("userVO") UserVO userVO) {
        return "/bloguser/create";
    }

    /**
     * 회원 등록 처리 (POST)
     * http://loacalhost:9092/bloguser/create
     */
    @PostMapping("/create")
    public String create(Model model, @Valid UserVO userVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/bloguser/create";
        }
        int cnt = userProc.create(userVO);

        if (cnt == 1) {
            return "redirect:/bloguser/list_all";
        } else {
            model.addAttribute("code", Tool.CREATE_FAIL);
        }
        model.addAttribute("cnt", cnt);

        return "/bloguser/msg";
    }

    /**
     * 회원 전체 조회
     * http://loacalhost:9092/bloguser/list_all
     */
    @GetMapping("/list_all")
    public String list_all(Model model, @ModelAttribute("userVO") UserVO userVO) {
        ArrayList<UserVO> list = userProc.list_all();
        model.addAttribute("list", list);

        return "/bloguser/list_all";
    }

    /**
     * 단일 회원 조회
     * http://loacalhost:9092/bloguser/read/1
     */
    @GetMapping("/read/{userno}")
    public String read(Model model, @PathVariable("userno") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        ArrayList<UserVO> list = userProc.list_all();
        model.addAttribute("list", list);

        return "/bloguser/read";
    }

    /**
     * 회원 수정 폼 (GET)
     * http://loacalhost:9092/bloguser/update/1
     */
    @GetMapping("/update/{userno}")
    public String update(Model model, @PathVariable("userno") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        ArrayList<UserVO> list = userProc.list_all();
        model.addAttribute("list", list);

        return "/bloguser/update";
    }

    /**
     * 회원 수정 처리 (POST)
     * http://loacalhost:9092/bloguser/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @Valid UserVO userVO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ArrayList<UserVO> list = userProc.list_all();
            model.addAttribute("list", list);
            return "/bloguser/update";
        }
        int cnt = userProc.update(userVO);

        if (cnt == 1) {
            return "redirect:/bloguser/update/" + userVO.getUserno();
        } else {
            model.addAttribute("code", Tool.UPDATE_FAIL);
        }
        model.addAttribute("username", userVO.getUsername());
        model.addAttribute("useremail", userVO.getUseremail());
        model.addAttribute("cnt", cnt);

        return "/bloguser/msg";
    }

    @GetMapping("/delete/{userno}")
    public String delete(Model model, @PathVariable("userno") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        ArrayList<UserVO> list = userProc.list_all();
        model.addAttribute("list", list);

        return "/bloguser/delete";
    }

    @PostMapping("/delete/{userno}")
    public String delete_process(Model model, @PathVariable("userno") int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        int cnt = userProc.delete(userno);

        if (cnt == 1) {
            return "redirect:/bloguser/list_all";
        } else {
            model.addAttribute("code", Tool.DELETE_FAIL);
        }
        model.addAttribute("username", userVO.getUsername());
        model.addAttribute("useremail", userVO.getUseremail());
        model.addAttribute("cnt", cnt);

        return "/bloguser/msg";
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
}

