package dev.mvc.cate;

import dev.mvc.tool.Tool;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/cate")
@Slf4j
public class CateCont {

    @Autowired
    @Qualifier("dev.mvc.cate.CateProc")
    private CateProcInter cateProc;

    public CateCont() {
        System.out.println("-> CateCont created");
    }

    /**
     * 등록 폼
     * http://localhost:9091/cate/create
     * @return
     */
    @GetMapping("/create")
    public String create(@ModelAttribute("cateVO") CateVO cateVO) {
        cateVO.setName("SF");
        cateVO.setGrp("영화/야행/개발...");
        return "/cate/create";  // templates/cate/create.html
    }

    /**
     * 등록 처리
     * Model model: controller -> html로 데이터 전송 제공
     * @Valid: @NotEmpty, @Size, @NotNull, @Min, @Max, @Pattern.. 규칙 위반 검사 지원
     * CateVO cateVO: Form 태그의 값이 자동 저장, Integer.parseInt(request.getParameter("seqno")) 자동 실행
     * @return
     */
    @PostMapping("/create")
    public String create(Model model, @Valid CateVO cateVO, BindingResult bindingResult) {
        System.out.println("-> create post");
        if (bindingResult.hasErrors()) {
            return "/cate/create";
        }
//        System.out.println(cateVO.getName());
//        System.out.println(cateVO.getSeqno());

        int cnt = cateProc.create(cateVO);
//        System.out.println("-> cnt: " + cnt);
        if (cnt == 1) {
            model.addAttribute("code", Tool.CREATE_SUCCESS);
            model.addAttribute("name", cateVO.getName());
        } else {
            model.addAttribute("code", Tool.CREATE_FAIL);
        }
        model.addAttribute("cnt", cnt);
        return "/cate/msg";  // templates/cate/msg.html
    }

    /**
     * 전체 목록
     * http://localhost:9091/cate/list_all
     * @return
     */
    @GetMapping("/list_all")
    public String list_all(Model model) {
        ArrayList<CateVO> list = cateProc.list_all();
        model.addAttribute("list", list);
        for (CateVO cate : list) {
            log.info(String.valueOf(cate));
        }
        return "/cate/list_all";    // templates/cate/list_all.html
    }

}
