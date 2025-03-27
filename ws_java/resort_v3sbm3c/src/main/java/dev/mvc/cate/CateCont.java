package dev.mvc.cate;

import dev.mvc.tool.Tool;
import jakarta.validation.Valid;
import lombok.Getter;
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
     */
    @GetMapping("/create")
    public String create(@ModelAttribute("cateVO") CateVO cateVO) {
        return "/cate/create";  // templates/cate/create.html
    }

    /**
     * 등록 처리
     * Model model: controller -> html로 데이터 전송 제공
     *
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
//            V1 -> V2 : 등록을 성공하면 등록 성공 창 대신 바로 전체 목록으로 redirect
            return "redirect:/cate/list_all";    // @GetMapping("/list_all")
        } else {
            model.addAttribute("code", Tool.CREATE_FAIL);
        }
        model.addAttribute("cnt", cnt);
        return "/cate/msg";  // templates/cate/msg.html
    }

    /**
     * 전체 목록
     * http://localhost:9091/cate/list_all
     */
    @GetMapping("/list_all")
    public String list_all(Model model, @ModelAttribute("cateVO") CateVO cateVO) {
        ArrayList<CateVO> list = cateProc.list_all();
        model.addAttribute("list", list);

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        return "/cate/list_all";    // templates/cate/list_all.html
    }

    /**
     * 조회 (@PathVariable 방식)
     * http://localhost:9091/cate/read/1
     */
    @GetMapping("/read/{cateno}")
    public String read(Model model, @PathVariable("cateno") int cateno) {
        log.info("-> read cateno: {}", cateno);

        CateVO cateVO = cateProc.read(cateno);
        ArrayList<CateVO> list = cateProc.list_all();

        model.addAttribute("list", list);
        model.addAttribute("cateVO", cateVO);

        return "/cate/read";    // templates/cate/read.html
    }


    /**
     * 수정 폼
     * http://localhost:9091/cate/update/1
     */
    @GetMapping("/update/{cateno}")
    public String update(Model model, @PathVariable("cateno") int cateno) {
        log.info("-> update read cateno: {}", cateno);
        CateVO cateVO = cateProc.read(cateno);
        ArrayList<CateVO> list = cateProc.list_all();

        model.addAttribute("cateVO", cateVO);
        model.addAttribute("list", list);

        return "/cate/update";    // templates/cate/update.html
    }

    /**
     * 수정 처리
     * http://localhost:9091/cate/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @Valid CateVO cateVO, BindingResult bindingResult) {
        log.info("-> update cateVO: {}", cateVO);

        if (bindingResult.hasErrors()) {
            ArrayList<CateVO> list = cateProc.list_all();
            model.addAttribute("list", list);
            return "cate/update";   // templates/cate/update.html
        }

        int cnt = cateProc.update(cateVO);
        log.info("-> update cnt: {}", cnt);

        if (cnt == 1) {
            return "redirect:/cate/update/" + cateVO.getCateno();
        } else {
            model.addAttribute("code", Tool.UPDATE_FAIL);
        }
        model.addAttribute("cnt", cnt);

        return "/cate/msg";  // templates/cate/msg.html
    }

    /**
     * 삭제 폼
     * http://localhost:9091/cate/delete/1
     */
    @GetMapping("/delete/{cateno}")
    public String delete(Model model, @PathVariable("cateno") int cateno) {
        CateVO cateVO = cateProc.read(cateno);
        model.addAttribute("cateVO", cateVO);

        ArrayList<CateVO> list = cateProc.list_all();
        model.addAttribute("list", list);

        return "/cate/delete";      // templates/cate/delete.html
    }

    /**
     * 삭제 처리
     * http://localhost:9091/cate/delete/1
     */
    @PostMapping("/delete/{cateno}")
    public String delete_process(Model model, @PathVariable("cateno") int cateno) {

        CateVO cateVO = cateProc.read(cateno);  // cateno로 cateVO 객체 가져옴
        model.addAttribute("cateVO", cateVO);

        int cnt = cateProc.delete(cateno);

        if (cnt == 1) {
            return "redirect:/cate/list_all";
        } else {
            model.addAttribute("code", Tool.DELETE_FAIL);
        }

        model.addAttribute("grp", cateVO.getGrp());
        model.addAttribute("name", cateVO.getName());
        model.addAttribute("cnt", cnt);

        return "/cate/msg";
    }

    /**
     * 우선 순위 높임, 10등 -> 1등
     * http://localhost:9091/cate/update_seqno_forward/1
     */
    @GetMapping("/update_seqno_forward/{cateno}")
    public String update_seqno_forward(Model model, @PathVariable("cateno") int cateno) {
        cateProc.update_seqno_forward(cateno);

        return "redirect:/cate/list_all";       // @GetMapping("/list_all")
    }

    /**
     * 우선 순위 낮춤, 1등 -> 10등
     * http://localhost:9091/cate/update_seqno_backward/1
     */
    @GetMapping("/update_seqno_backward/{cateno}")
    public String update_seqno_backward(Model model, @PathVariable("cateno") int cateno) {
        cateProc.update_seqno_backward(cateno);

        return "redirect:/cate/list_all";       // @GetMapping("/list_all")
    }

    /**
     * 카테고리 공개 설정
     * http://localhost:9091/cate/update_visible_y/1
     */
    @GetMapping("/update_visible_y/{cateno}")
    public String update_visible_y(Model model, @PathVariable("cateno") int cateno) {
        cateProc.update_visible_y(cateno);

        return "redirect:/cate/list_all";
    }

    /**
     * 카테고리 비공개 설정
     * http://localhost:9091/cate/update_visible_n/1
     */
    @GetMapping("/update_visible_n/{cateno}")
    public String update_visible_n(Model model, @PathVariable("cateno") int cateno) {
        cateProc.update_visible_n(cateno);

        return "redirect:/cate/list_all";
    }



}
