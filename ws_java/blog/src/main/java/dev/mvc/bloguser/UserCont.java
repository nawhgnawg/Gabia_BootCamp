package dev.mvc.bloguser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bloguser")
public class UserCont {

    @GetMapping("/create")
    public String create(@ModelAttribute("userVO") UserVO userVO) {
        return "/bloguser/create";
    }
}
