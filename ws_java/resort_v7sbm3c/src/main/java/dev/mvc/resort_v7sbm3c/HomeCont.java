package dev.mvc.resort_v7sbm3c;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVOMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class HomeCont {

    @Autowired
    @Qualifier("dev.mvc.cate.CateProc")
    private CateProcInter cateProc;

    @GetMapping("/")
    public String home(Model model) {

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        // 시작 페이지는 검색을 하지 않음
        model.addAttribute("word", "");
        model.addAttribute("now_page", "1");

        return "index";
    }
}
