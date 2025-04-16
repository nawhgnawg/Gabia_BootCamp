package dev.mvc.cate;

import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
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
@RequestMapping("/cate")
@Slf4j
public class CateCont {

    @Autowired
    @Qualifier("dev.mvc.cate.CateProc")
    private CateProcInter cateProc;

    @Autowired
    private MemberProc memberProc;

    /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
    public int record_per_page = 5;

    /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
    public int page_per_block = 10;

    /** 페이징 목록 주소, @GetMapping("/list_search") */
    private String list_file_name = "/cate/list_search";

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
    public String create(Model model, @Valid CateVO cateVO, BindingResult bindingResult,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page,
                         RedirectAttributes ra) {
        System.out.println("-> create post");
        if (bindingResult.hasErrors()) {
            return "/cate/create";
        }

        int cnt = cateProc.create(cateVO);

        if (cnt == 1) {
            // ----------------------------------------------------------------------------------------------------------
            // 마지막 페이지에서 레코드 추가시 페이지가 추가 될 때
            int search_cnt = cateProc.list_search_count(word);
            if (search_cnt % record_per_page == 0) {
                now_page = now_page + 1;
            }
            // ----------------------------------------------------------------------------------------------------------
            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);
//            V1 -> V2 : 등록을 성공하면 등록 성공 창 대신 바로 전체 목록으로 redirect
            return "redirect:/cate/list_search";    // @GetMapping("/list_search")
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

        // 카테고리 그룹 목록
        ArrayList<String> grpset = cateProc.grpset();
        cateVO.setGrp(String.join("/", grpset));

        return "/cate/list_all";    // templates/cate/list_all.html
    }

    /**
     * 조회 (@PathVariable 방식)
     * http://localhost:9091/cate/read/1
     */
    @GetMapping("/read/{cateno}")
    public String read(Model model, @PathVariable("cateno") int cateno,
                       @RequestParam(name = "word", defaultValue = "") String word,
                       @RequestParam(name="now_page", defaultValue="1") int now_page) {
        log.info("-> read cateno: {}", cateno);

        CateVO cateVO = cateProc.read(cateno);
        model.addAttribute("cateVO", cateVO);

        // 2단 메뉴
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        // 검색 목록
        ArrayList<CateVO> list = cateProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        // 검색된 레코드 갯수
        int search_cnt = list.size();
        model.addAttribute("search_cnt", search_cnt);

        model.addAttribute("word", word);

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = cateProc.list_search_count(word);
        String paging = cateProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
        int no = search_count - ((now_page - 1) * record_per_page);
        model.addAttribute("no", no);


        return "/cate/read";    // templates/cate/read.html
    }

    /**
     * 수정 폼
     * http://localhost:9091/cate/update/1
     */
    @GetMapping("/update/{cateno}")
    public String update(Model model, @PathVariable("cateno") int cateno,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name="now_page", defaultValue="1") int now_page) {
        log.info("-> update read cateno: {}", cateno);
        CateVO cateVO = cateProc.read(cateno);
        model.addAttribute("cateVO", cateVO);

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<CateVO> list = cateProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        model.addAttribute("word", word);

        // 검색된 레코드 갯수
        int search_cnt = list.size();
        model.addAttribute("search_cnt", search_cnt);

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = cateProc.list_search_count(word);
        String paging = cateProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
        int no = search_count - ((now_page - 1) * record_per_page);
        model.addAttribute("no", no);


        return "/cate/update";    // templates/cate/update.html
    }

    /**
     * 수정 처리
     * http://localhost:9091/cate/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @Valid CateVO cateVO, BindingResult bindingResult,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name="now_page", defaultValue="1") int now_page,
                         RedirectAttributes ra) {
        log.info("-> update cateVO: {}", cateVO);

        if (bindingResult.hasErrors()) {
            ArrayList<CateVO> list = cateProc.list_search_paging(word, now_page, record_per_page);
            model.addAttribute("list", list);

            // --------------------------------------------------------------------------------------
            // 페이지 번호 목록 생성
            // --------------------------------------------------------------------------------------
            int search_count = cateProc.list_search_count(word);
            String paging = cateProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
            model.addAttribute("paging", paging);
            model.addAttribute("now_page", now_page);
            // --------------------------------------------------------------------------------------
            // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
            int no = search_count - ((now_page - 1) * record_per_page);
            model.addAttribute("no", no);

            return "cate/update";   // templates/cate/update.html
        }

        int cnt = cateProc.update(cateVO);
        log.info("-> update cnt: {}", cnt);

        if (cnt == 1) {
            log.info("-> word: {}", word);
            ra.addAttribute("word", word);  // redirect로 데이터 전송, 한글 깨짐 방지
            ra.addAttribute("now_page", now_page);
            return "redirect:/cate/update/" + cateVO.getCateno();
//            return "redirect:/cate/update/" + cateVO.getCateno() + "?word=" + word; // 한글 깨짐 오류

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
    public String delete(Model model, @PathVariable("cateno") int cateno,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name="now_page", defaultValue="1") int now_page) {
        CateVO cateVO = cateProc.read(cateno);
        model.addAttribute("cateVO", cateVO);

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<CateVO> list = cateProc.list_search_paging(word, now_page, record_per_page);
        model.addAttribute("list", list);

        model.addAttribute("word", word);

        // 검색된 레코드 갯수
        int search_cnt = list.size();
        model.addAttribute("search_cnt", search_cnt);

        // --------------------------------------------------------------------------------------
        // 페이지 번호 목록 생성
        // --------------------------------------------------------------------------------------
        int search_count = cateProc.list_search_count(word);
        String paging = cateProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        // --------------------------------------------------------------------------------------
        // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
        int no = search_count - ((now_page - 1) * record_per_page);
        model.addAttribute("no", no);


        return "/cate/delete";      // templates/cate/delete.html
    }

    /**
     * 삭제 처리
     * http://localhost:9091/cate/delete/1
     */
    @PostMapping("/delete/{cateno}")
    public String delete_process(Model model, @PathVariable("cateno") int cateno,
                                 @RequestParam(name = "word", defaultValue = "") String word,
                                 @RequestParam(name="now_page", defaultValue="1") int now_page,
                                 RedirectAttributes ra) {

        CateVO cateVO = cateProc.read(cateno);  // cateno로 cateVO 객체 가져옴
        model.addAttribute("cateVO", cateVO);

        int cnt = cateProc.delete(cateno);

        if (cnt == 1) {
            // ----------------------------------------------------------------------------------------------------------
            // 마지막 페이지에서 모든 레코드가 삭제되면 페이지수를 1 감소 시켜야함.
            int search_cnt = cateProc.list_search_count(word);
            if (search_cnt % record_per_page == 0) {
                now_page = now_page - 1;
                if (now_page < 1) {
                    now_page = 1; // 최소 시작 페이지
                }
            }
            // ----------------------------------------------------------------------------------------------------------

            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);
            return "redirect:/cate/list_search";
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
    public String update_seqno_forward(Model model, @PathVariable("cateno") int cateno,
                                       @RequestParam(name = "word", defaultValue = "") String word,
                                       @RequestParam(name="now_page", defaultValue="1") int now_page,
                                       RedirectAttributes ra) {
        cateProc.update_seqno_forward(cateno);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/cate/list_search";
    }

    /**
     * 우선 순위 낮춤, 1등 -> 10등
     * http://localhost:9091/cate/update_seqno_backward/1
     */
    @GetMapping("/update_seqno_backward/{cateno}")
    public String update_seqno_backward(Model model, @PathVariable("cateno") int cateno,
                                        @RequestParam(name = "word", defaultValue = "") String word,
                                        @RequestParam(name="now_page", defaultValue="1") int now_page,
                                        RedirectAttributes ra) {
        cateProc.update_seqno_backward(cateno);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/cate/list_search";
    }

    /**
     * 카테고리 공개 설정
     * http://localhost:9091/cate/update_visible_y/1
     */
    @GetMapping("/update_visible_y/{cateno}")
    public String update_visible_y(Model model, @PathVariable("cateno") int cateno,
                                   @RequestParam(name = "word", defaultValue = "") String word,
                                   @RequestParam(name="now_page", defaultValue="1") int now_page,
                                   RedirectAttributes ra) {
        cateProc.update_visible_y(cateno);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/cate/list_search";
    }

    /**
     * 카테고리 비공개 설정
     * http://localhost:9091/cate/update_visible_n/1
     */
    @GetMapping("/update_visible_n/{cateno}")
    public String update_visible_n(Model model, @PathVariable("cateno") int cateno,
                                   @RequestParam(name = "word", defaultValue = "") String word,
                                   @RequestParam(name="now_page", defaultValue="1") int now_page,
                                   RedirectAttributes ra) {
        cateProc.update_visible_n(cateno);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        return "redirect:/cate/list_search";
    }


    /** 검색
     * http://localhost:9091/cate/list_search
     */
    /*
    @GetMapping("/list_search")
    public String list_search(Model model,
                              @ModelAttribute("cateVO") CateVO cateVO,
                              @RequestParam(value = "word", defaultValue = "") String word) {

        ArrayList<CateVO> list = cateProc.list_search(word);
        model.addAttribute("list", list);

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        ArrayList<String> grpset = cateProc.grpset();
        String joinGrpSet = String.join("/", grpset);
        model.addAttribute("grpset", joinGrpSet);

        // @RequestParam 에 받은 값을 model에 바로 추가 할 수 있다.
        model.addAttribute("word", word);

        // 검색된 레코드 갯수
        int list_search_count = cateProc.list_search_count(word);
        model.addAttribute("list_search_count", list_search_count);

        return "/cate/list_search";
    }
    */


    /**
     * 등록 폼 및 검색 목록 + 페이징
     * http://localhost:9091/cate/list_search
     * http://localhost:9091/cate/list_search?word=&now_page=
     * http://localhost:9091/cate/list_search?word=까페&now_page=1
     * @param model
     * @return
     */
    @GetMapping("/list_search")
    public String list_search_paging(HttpSession session,
                                     Model model,
                                     @RequestParam(name="word", defaultValue = "") String word,
                                     @RequestParam(name="now_page", defaultValue="1") int now_page) {

        if (memberProc.isAdmin(session)) {
            CateVO cateVO = new CateVO();
            model.addAttribute("cateVO", cateVO);

            ArrayList<CateVOMenu> menu = cateProc.menu();
            model.addAttribute("menu", menu);

            // 카테고리 그룹 목록
            ArrayList<String> grpset = cateProc.grpset();
            String joinGrpSet = String.join("/", grpset);
            model.addAttribute("grpset", joinGrpSet);

            word = Tool.checkNull(word);


            ArrayList<CateVO> list = cateProc.list_search_paging(word, now_page, record_per_page);
            model.addAttribute("list", list);

            int search_cnt = cateProc.list_search_count(word);
            model.addAttribute("search_cnt", search_cnt);

            model.addAttribute("word", word); // 검색어

            // --------------------------------------------------------------------------------------
            // 페이지 번호 목록 생성
            // --------------------------------------------------------------------------------------
            int search_count = cateProc.list_search_count(word);
            String paging = cateProc.pagingBox(now_page, word, list_file_name, search_count, record_per_page, page_per_block);
            model.addAttribute("paging", paging);
            model.addAttribute("now_page", now_page);

            // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)

            int no = search_count - ((now_page - 1) * record_per_page);
            model.addAttribute("no", no);

            return "/cate/list_search";  // /templates/cate/list_search.html
        } else {
            return "redirect:/member/login_cookie_need?url=/cate/list_search";
        }

    }
}
