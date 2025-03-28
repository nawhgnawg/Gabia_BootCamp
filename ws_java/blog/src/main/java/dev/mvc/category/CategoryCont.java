package dev.mvc.category;

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
@RequestMapping("/category")
@Slf4j
public class CategoryCont {

    @Autowired
    @Qualifier("dev.mvc.category.CategoryProc")
    private CategoryProcInter categoryProc;

    /**
     * 등록 폼
     * http://localhost:9091/category/create
     */
    @GetMapping("/create")
    public String create(@ModelAttribute("categoryVO") CategoryVO categoryVO) {
        return "/category/create";  // templates/cate/create.html
    }

    /**
     * 등록 처리
     * Model model: controller -> html로 데이터 전송 제공
     *
     * @Valid: @NotEmpty, @Size, @NotNull, @Min, @Max, @Pattern.. 규칙 위반 검사 지원
     * CateVO cateVO: Form 태그의 값이 자동 저장, Integer.parseInt(request.getParameter("seqno")) 자동 실행
     */
    @PostMapping("/create")
    public String create(Model model, @Valid CategoryVO categoryVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/category/create";
        }

        int cnt = categoryProc.create(categoryVO);

        if (cnt == 1) {
//            V1 -> V2 : 등록을 성공하면 등록 성공 창 대신 바로 전체 목록으로 redirect
            return "redirect:/category/list_all";    // @GetMapping("/list_all")
        } else {
            model.addAttribute("code", Tool.CREATE_FAIL);
        }
        model.addAttribute("cnt", cnt);
        return "/category/msg";  // templates/cate/msg.html
    }

    /**
     * 전체 목록
     * http://localhost:9092/category/list_all
     */
    @GetMapping("/list_all")
    public String list_all(Model model, @ModelAttribute("categoryVO") CategoryVO categoryVO) {
        ArrayList<CategoryVO> list = categoryProc.list_all();
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<String> categorygrpset = categoryProc.categorygrpset();
        categoryVO.setCategoryGrp(String.join("/", categorygrpset));

        return "/category/list_all";    // templates/category/list_all.html
    }

    /**
     * 조회 (@PathVariable 방식)
     * http://localhost:9091/category/read/1
     */
    @GetMapping("/read/{categoryNo}")
    public String read(Model model, @PathVariable("categoryNo") int categoryNo) {
        log.info("-> read cateno: {}", categoryNo);

        CategoryVO categoryVO = categoryProc.read(categoryNo);
        ArrayList<CategoryVO> list = categoryProc.list_all();
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        model.addAttribute("list", list);
        model.addAttribute("categoryVO", categoryVO);

        return "/category/read";    // templates/cate/read.html
    }


    /**
     * 수정 폼
     * http://localhost:9091/category/update/1
     */
    @GetMapping("/update/{categoryNo}")
    public String update(Model model, @PathVariable("categoryNo") int cateNo) {
        CategoryVO categoryVO = categoryProc.read(cateNo);
        ArrayList<CategoryVO> list = categoryProc.list_all();
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);


        model.addAttribute("categoryVO", categoryVO);
        model.addAttribute("list", list);

        return "/category/update";    // templates/cate/update.html
    }

    /**
     * 수정 처리
     * http://localhost:9091/category/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @Valid CategoryVO categoryVO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ArrayList<CategoryVO> list = categoryProc.list_all();
            model.addAttribute("list", list);
            return "category/update";   // templates/cate/update.html
        }

        int cnt = categoryProc.update(categoryVO);

        if (cnt == 1) {
            return "redirect:/category/update/" + categoryVO.getCategoryNo();
        } else {
            model.addAttribute("code", Tool.UPDATE_FAIL);
        }
        model.addAttribute("cnt", cnt);

        return "/category/msg";  // templates/cate/msg.html
    }

    /**
     * 삭제 폼
     * http://localhost:9091/category/delete/1
     */
    @GetMapping("/delete/{cateNo}")
    public String delete(Model model, @PathVariable("cateNo") int cateNo) {
        CategoryVO categoryVO = categoryProc.read(cateNo);
        model.addAttribute("categoryVO", categoryVO);

        ArrayList<CategoryVO> list = categoryProc.list_all();
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return "/category/delete";      // templates/cate/delete.html
    }

    /**
     * 삭제 처리
     * http://localhost:9091/category/delete/1
     */
    @PostMapping("/delete/{cateNo}")
    public String delete_process(Model model, @PathVariable("cateNo") int cateNo) {

        CategoryVO categoryVO = categoryProc.read(cateNo);// cateno로 cateVO 객체 가져옴
        model.addAttribute("categoryVO", categoryVO);

        int cnt = categoryProc.delete(cateNo);

        if (cnt == 1) {
            return "redirect:/category/list_all";
        } else {
            model.addAttribute("code", Tool.DELETE_FAIL);
        }

        model.addAttribute("categoryGrp", categoryVO.getCategoryGrp());
        model.addAttribute("categoryName", categoryVO.getCategoryName());
        model.addAttribute("cnt", cnt);

        return "/category/msg";
    }

    /**
     * 우선 순위 높임, 10등 -> 1등
     * http://localhost:9091/category/update_sortNo_forward/1
     */
    @GetMapping("/update_sortNo_forward/{categoryNo}")
    public String update_seqno_forward(Model model, @PathVariable("categoryNo") int categoryNo) {
        categoryProc.update_sortNo_forward(categoryNo);

        return "redirect:/category/list_all";       // @GetMapping("/list_all")
    }

    /**
     * 우선 순위 낮춤, 1등 -> 10등
     * http://localhost:9091/category/update_sortNo_backward/1
     */
    @GetMapping("/update_sortNo_backward/{categoryNo}")
    public String update_sortNo_backward(Model model, @PathVariable("categoryNo") int categoryNo) {
        categoryProc.update_sortNo_backward(categoryNo);

        return "redirect:/category/list_all";       // @GetMapping("/list_all")
    }

    /**
     * 카테고리 공개 설정
     * http://localhost:9091/category/update_visible_y/1
     */
    @GetMapping("/update_visible_y/{categoryNo}")
    public String update_visible_y(Model model, @PathVariable("categoryNo") int categoryNo) {
        categoryProc.update_visible_y(categoryNo);

        return "redirect:/category/list_all";
    }

    /**
     * 카테고리 비공개 설정
     * http://localhost:9091/category/update_visible_n/1
     */
    @GetMapping("/update_visible_n/{categoryNo}")
    public String update_visible_n(Model model, @PathVariable("categoryNo") int categoryNo) {
        categoryProc.update_visible_n(categoryNo);

        return "redirect:/category/list_all";
    }

}
