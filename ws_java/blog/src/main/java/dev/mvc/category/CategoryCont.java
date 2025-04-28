package dev.mvc.category;

import dev.mvc.bloguser.UserProc;
import dev.mvc.bloguser.UserProcInter;
import dev.mvc.contents.ContentsProc;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/category")
@Slf4j
public class CategoryCont {

    @Autowired
    @Qualifier("dev.mvc.category.CategoryProc")
    private CategoryProcInter categoryProc;

    @Autowired
    private UserProc userProc;

    @Autowired
    private ContentsProc contentsProc;

    /**
     * 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작
     */
    public int record_per_page = 5;

    /**
     * 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨
     */
    public int page_per_block = 10;

    /**
     * 페이징 목록 주소, @GetMapping("/list_search")
     */
    private String list_file_name = "/category/list_search";

    /**
     * 등록 폼
     * http://localhost:9091/category/create
     */
    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("categoryVO") CategoryVO categoryVO,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

        ArrayList<CategoryVO> list = categoryProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        int search_cnt = categoryProc.list_search_count(word);
        model.addAttribute("search_cnt", search_cnt);

        model.addAttribute("word", word);

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = categoryProc.list_search_count(word);
        String paging = categoryProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: ((현재 페이지수 -1) * 페이지당 레코드 수) + 1
        int no = ((now_page - 1) * record_per_page) + 1;
        model.addAttribute("no", no);

        return "category/create";  // templates/cate/create.html
    }

    /**
     * 등록 처리
     * Model model: controller -> html로 데이터 전송 제공
     *
     * @Valid: @NotEmpty, @Size, @NotNull, @Min, @Max, @Pattern.. 규칙 위반 검사 지원
     * CateVO cateVO: Form 태그의 값이 자동 저장, Integer.parseInt(request.getParameter("seqno")) 자동 실행
     */
    @PostMapping("/create")
    public String create(Model model, @Valid CategoryVO categoryVO, BindingResult bindingResult,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                         RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            return "category/create";
        }

        int cnt = categoryProc.create(categoryVO);

        if (cnt == 1) {
            // ----------------------------------------------------------------------------------------------------------
            // 마지막 페이지에서 레코드 추가시 페이지가 추가 될 때
            int search_cnt = categoryProc.list_search_count(word);
            if (search_cnt % record_per_page == 0) {
                now_page = now_page + 1;
            }
            // ----------------------------------------------------------------------------------------------------------
            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);

            return "redirect:/category/list_search";    // @GetMapping("/list_all")
        } else {
            model.addAttribute("code", Tool.CREATE_FAIL);
        }
        model.addAttribute("cnt", cnt);

        return "category/msg";  // templates/cate/msg.html
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
        String grpset = String.join("/", categorygrpset);
        model.addAttribute("grpset", grpset);


        return "category/list_all";    // templates/category/list_all.html
    }

    /**
     * 조회 (@PathVariable 방식)
     * http://localhost:9091/category/read/1
     */
    @GetMapping("/read/{categoryNo}")
    public String read(Model model, @PathVariable("categoryNo") int categoryNo,
                       @RequestParam(name = "word", defaultValue = "") String word,
                       @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

        CategoryVO categoryVO = categoryProc.read(categoryNo);
        model.addAttribute("categoryVO", categoryVO);

        ArrayList<CategoryVO> list = categoryProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        int search_cnt = categoryProc.list_search_count(word);
        model.addAttribute("search_cnt", search_cnt);

        model.addAttribute("word", word);

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = categoryProc.list_search_count(word);
        String paging = categoryProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: ((현재 페이지수 -1) * 페이지당 레코드 수) + 1
        int no = ((now_page - 1) * record_per_page) + 1;
        model.addAttribute("no", no);

        return "category/read";    // templates/cate/read.html
    }


    /**
     * 수정 폼
     * http://localhost:9091/category/update/1
     */
    @GetMapping("/update/{categoryNo}")
    public String update(Model model, @PathVariable("categoryNo") int categoryNo,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
        CategoryVO categoryVO = categoryProc.read(categoryNo);
        model.addAttribute("categoryVO", categoryVO);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<CategoryVO> list = categoryProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        int search_cnt = list.size();
        model.addAttribute("search_cnt", search_cnt);

        model.addAttribute("word", word);

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = categoryProc.list_search_count(word);
        String paging = categoryProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: ((현재 페이지수 -1) * 페이지당 레코드 수) + 1
        int no = ((now_page - 1) * record_per_page) + 1;
        model.addAttribute("no", no);

        return "category/update";    // templates/cate/update.html
    }

    /**
     * 수정 처리
     * http://localhost:9091/category/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @Valid CategoryVO categoryVO, BindingResult bindingResult,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                         RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            return "category/update";   // templates/cate/update.html
        }

        int cnt = categoryProc.update(categoryVO);

        if (cnt == 1) {
            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);
            return "redirect:/category/update/" + categoryVO.getCategoryNo();
        } else {
            model.addAttribute("code", Tool.UPDATE_FAIL);
        }
        model.addAttribute("cnt", cnt);

        return "category/msg";  // templates/cate/msg.html
    }

    /**
     * 삭제 폼
     * http://localhost:9091/category/delete/1
     */
    @GetMapping("/delete/{categoryNo}")
    public String delete(Model model, @PathVariable("categoryNo") int categoryNo,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
        CategoryVO categoryVO = categoryProc.read(categoryNo);
        model.addAttribute("categoryVO", categoryVO);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<CategoryVO> list = categoryProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        model.addAttribute("word", word);

        int search_cnt = list.size();
        model.addAttribute("search_cnt", search_cnt);

        int contents_cnt = contentsProc.count_by_categoryno(categoryNo);
        // 삭제하는 카테고리에 Contents 가 있는 경우
        if (contents_cnt > 0) {
            model.addAttribute("contents_cnt", contents_cnt);
            model.addAttribute("code", "contents_exist_y");
        } else {
            model.addAttribute("code", "contents_exist_n");
        }

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = categoryProc.list_search_count(word);
        String paging = categoryProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: ((현재 페이지수 -1) * 페이지당 레코드 수) + 1
        int no = ((now_page - 1) * record_per_page) + 1;
        model.addAttribute("no", no);

        return "category/delete";      // templates/cate/delete.html
    }

    /**
     * 삭제 처리
     * http://localhost:9091/category/delete/1
     */
    @PostMapping("/delete/{categoryNo}")
    public String delete_process(Model model, @PathVariable("categoryNo") int categoryNo,
                                 @RequestParam(name = "word", defaultValue = "") String word,
                                 @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                                 RedirectAttributes ra) {

        CategoryVO categoryVO = categoryProc.read(categoryNo);// cateno로 cateVO 객체 가져옴
        model.addAttribute("categoryVO", categoryVO);

        int contents_cnt = contentsProc.count_by_categoryno(categoryNo);
        // 삭제하는 카테고리에 Contents 가 있는 경우
        if (contents_cnt > 0) {
            contentsProc.delete_by_categoryno(categoryNo);
            model.addAttribute("contents_cnt", contents_cnt);
            model.addAttribute("code", "contents_exist_y");
        } else {
            model.addAttribute("code", "contents_exist_n");
        }

        int cnt = categoryProc.delete(categoryNo);

        if (cnt == 1) {
            // 마지막 페이지에서 모든 레코드가 삭제되면 페이지수를 1 감소 시켜야함.
            int search_cnt = categoryProc.list_search_count(word);
            if (search_cnt % record_per_page == 0) {
                now_page = now_page - 1;
                if (now_page < 1) {
                    now_page = 1; // 최소 시작 페이지
                }
            }
            // ----------------------------------------------------------------------------------------------------------
            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);
            return "redirect:/category/list_search";
        } else {
            model.addAttribute("code", Tool.DELETE_FAIL);
        }

        model.addAttribute("categoryGrp", categoryVO.getCategoryGrp());
        model.addAttribute("categoryName", categoryVO.getCategoryName());
        model.addAttribute("cnt", cnt);

        return "category/msg";
    }

    /**
     * 우선 순위 높임, 10등 -> 1등
     * http://localhost:9091/category/update_sortNo_forward/1
     */
    @GetMapping("/update_sortNo_forward/{categoryNo}")
    public String update_seqno_forward(Model model, @PathVariable("categoryNo") int categoryNo,
                                       @RequestParam(name = "word", defaultValue = "") String word,
                                       @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                                       RedirectAttributes ra) {
        categoryProc.update_sortNo_forward(categoryNo);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/category/list_search";       // @GetMapping("/list_all")
    }

    /**
     * 우선 순위 낮춤, 1등 -> 10등
     * http://localhost:9091/category/update_sortNo_backward/1
     */
    @GetMapping("/update_sortNo_backward/{categoryNo}")
    public String update_sortNo_backward(Model model, @PathVariable("categoryNo") int categoryNo,
                                         @RequestParam(name = "word", defaultValue = "") String word,
                                         @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                                         RedirectAttributes ra) {
        categoryProc.update_sortNo_backward(categoryNo);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/category/list_search";       // @GetMapping("/list_all")
    }

    /**
     * 카테고리 공개 설정
     * http://localhost:9091/category/update_visible_y/1
     */
    @GetMapping("/update_visible_y/{categoryNo}")
    public String update_visible_y(Model model, @PathVariable("categoryNo") int categoryNo,
                                   @RequestParam(name = "word", defaultValue = "") String word,
                                   @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                                   RedirectAttributes ra) {
        categoryProc.update_visible_y(categoryNo);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/category/list_search";
    }

    /**
     * 카테고리 비공개 설정
     * http://localhost:9091/category/update_visible_n/1
     */
    @GetMapping("/update_visible_n/{categoryNo}")
    public String update_visible_n(Model model, @PathVariable("categoryNo") int categoryNo,
                                   @RequestParam(name = "word", defaultValue = "") String word,
                                   @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                                   RedirectAttributes ra) {
        categoryProc.update_visible_n(categoryNo);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/category/list_search";
    }

/*
    @GetMapping("/list_search")
    public String list_search(Model model,
                              @ModelAttribute("categoryVO") CategoryVO categoryVO,
                              @RequestParam(name = "word", defaultValue = "") String word) {

        ArrayList<CategoryVO> list = categoryProc.list_search(word);
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<String> categorygrpset = categoryProc.categorygrpset();
        String grpset = String.join("/", categorygrpset);
        model.addAttribute("grpset", grpset);

        model.addAttribute("word", word);

        int list_search_count = categoryProc.list_search_count(word);
        model.addAttribute("list_search_count", list_search_count);

        return "/category/list_search";
    }
*/

    @GetMapping("/list_search")
    public String list_search_paging(HttpSession session,
                                     Model model,
                                     @RequestParam(name="word", defaultValue = "") String word,
                                     @RequestParam(name="now_page", defaultValue="1") int now_page) {
        if (userProc.isAdmin(session)) {
            CategoryVO categoryVO = new CategoryVO();
            model.addAttribute("categoryVO", categoryVO);

            ArrayList<CategoryVOMenu> menu = categoryProc.menu();
            model.addAttribute("menu", menu);

            ArrayList<String> categorygrpset = categoryProc.categorygrpset();
            String grpset = String.join("/", categorygrpset);
            model.addAttribute("grpset", grpset);

            word = Tool.checkNull(word);

            ArrayList<CategoryVO> list = categoryProc.list_search_paging(word, now_page, record_per_page);
            model.addAttribute("list", list);

            ArrayList<CategoryVO> list2 = categoryProc.list_all();

            for (CategoryVO category: list2) {
                // 카테고리 별 cnt
                int cnt = contentsProc.count_by_categoryno(category.getCategoryNo());
                category.setCategoryCnt(cnt);
                categoryProc.update_cnt(category);
            }

            for (CategoryVO category: list2) {
                // 카테고리 그룹 total
                ArrayList<CategoryVO> grpList = categoryProc.list_all_categoryName_y(category.getCategoryGrp());
                int size = categoryProc.list_all_categoryName_y(category.getCategoryGrp()).size();
                if (category.getCategoryName().equals("--")) {
                    int total = 0;
                    for (int i = 0; i < size; i++) {
                        total += grpList.get(i).getCategoryCnt();
                    }
                    category.setCategoryCnt(total);
                    categoryProc.update_cnt(category);
                }
            }

            int search_cnt = categoryProc.list_search_count(word);
            model.addAttribute("search_cnt", search_cnt);

            model.addAttribute("word", word);

            // --------------------------------------------------------------------------------------
            // 페이지 번호 목록 생성
            // --------------------------------------------------------------------------------------
            int search_count = categoryProc.list_search_count(word);
            String paging = categoryProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
            model.addAttribute("paging", paging);
            model.addAttribute("now_page", now_page);
            // --------------------------------------------------------------------------------------
            // 일련 변호 생성: ((현재 페이지수 -1) * 페이지당 레코드 수) + 1
            int no = ((now_page - 1) * record_per_page) + 1;
            model.addAttribute("no", no);

            return "category/list_search";
        } else {
            return "redirect:/bloguser/login_cookie_need?url=/category/list_search";
        }
    }
}
