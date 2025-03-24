package dev.mvc.cate;

import dev.mvc.tool.Tool;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
     *
     * @return
     */
    @GetMapping("/create")
    public String create(@ModelAttribute("cateVO") CateVO cateVO) {
        cateVO.setName("SF");
        cateVO.setGrp("영화/여행/개발...");
        return "/cate/create";  // templates/cate/create.html
    }

    /**
     * 등록 처리
     * Model model: controller -> html로 데이터 전송 제공
     *
     * @return
     * @Valid: @NotEmpty, @Size, @NotNull, @Min, @Max, @Pattern.. 규칙 위반 검사 지원
     * CateVO cateVO: Form 태그의 값이 자동 저장, Integer.parseInt(request.getParameter("seqno")) 자동 실행
     */
    @PostMapping("/create")
    public String create(Model model, @Valid CateVO cateVO, BindingResult bindingResult) {
        System.out.println("-> create post");
        if (bindingResult.hasErrors()) {
            return "/cate/create";
        }

        int cnt = cateProc.create(cateVO);

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
     *
     * @return
     */
    @GetMapping("/list_all")
    public String list_all(Model model) {
        ArrayList<CateVO> list = cateProc.list_all();
        model.addAttribute("list", list);

        return "/cate/list_all";    // templates/cate/list_all.html
    }

    /**
     * 조회
     * http://localhost:9091/cate/read?cateno=1
     */
    @GetMapping("/read")
    public String read(Model model, @RequestParam(value = "cateno", defaultValue = "0") int cateno) {
        log.info("-> read cateno: {}", cateno);
        CateVO cateVO = cateProc.read(cateno);

        model.addAttribute("cateVO", cateVO);

        return "/cate/read";    // templates/cate/read.html
    }

    /**
     * 수정 폼
     * http://localhost:9091/cate/update?cateno=1
     */
    @GetMapping("/update")
    public String update(Model model, @RequestParam(value = "cateno", defaultValue = "0") int cateno) {
        log.info("-> update read cateno: {}", cateno);
        CateVO cateVO = cateProc.read(cateno);

        model.addAttribute("cateVO", cateVO);

        return "/cate/update";    // templates/cate/update.html
    }

    /**
     * 수정 처리
     * http://localhost:9091/cate/update?cateno=1
     */
    @PostMapping("/update")
    public String update(Model model, @Valid CateVO cateVO, BindingResult bindingResult) {
        log.info("-> update cateVO: {}", cateVO);
        if (bindingResult.hasErrors()) {
            return "cate/update";   // templates/cate/update.html
        }
        int cnt = cateProc.update(cateVO);
        log.info("-> update cnt: {}", cnt);

        if (cnt == 1) {
            model.addAttribute("code", Tool.UPDATE_SUCCESS);
            model.addAttribute("name", cateVO.getName());
        } else {
            model.addAttribute("code", Tool.UPDATE_FAIL);
        }
        model.addAttribute("cnt", cnt);
        return "/cate/msg";  // templates/cate/msg.html
    }

    /**
     * 삭제 폼
     * http://localhost:9091/cate/delete?cateno=1
     */
    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(name="cateno") int cateno) {
        CateVO cateVO = cateProc.read(cateno);
        model.addAttribute("cateVO", cateVO);

        return "/cate/delete";      // templates/cate/delete.html
    }

    /**
     * 삭제 처리
     */
    @PostMapping("/delete")
    public String delete_process(Model model, @RequestParam("cateno") int cateno) {

        CateVO cateVO = cateProc.read(cateno);  // cateno로 cateVO 객체 가져옴
        model.addAttribute("cateVO", cateVO);

        int cnt = cateProc.delete(cateno);

        if (cnt == 1) {
            model.addAttribute("code", Tool.DELETE_SUCCESS);
        } else {
            model.addAttribute("code", Tool.DELETE_FAIL);
        }

        model.addAttribute("grp", cateVO.getGrp());
        model.addAttribute("name", cateVO.getName());
        model.addAttribute("cnt", cnt);

        return "/cate/msg";
    }

}
