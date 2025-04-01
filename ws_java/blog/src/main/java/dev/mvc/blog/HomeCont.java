package dev.mvc.blog;

import dev.mvc.bloguser.UserProcInter;
import dev.mvc.bloguser.UserVOMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeCont {

    @Autowired
    @Qualifier("dev.mvc.bloguser.UserProc")
    private UserProcInter userProc;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("word", "");
        model.addAttribute("now_page", "1");
        return "index";
    }

}
