package dev.mvc.contents;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVO;
import dev.mvc.cate.CateVOMenu;
import dev.mvc.contentsgood.ContentsgoodProc;
import dev.mvc.contentsgood.ContentsgoodProcInter;
import dev.mvc.contentsgood.ContentsgoodVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/contents")
public class ContentsCont {
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;

    @Autowired
    @Qualifier("dev.mvc.cate.CateProc") // @Component("dev.mvc.cate.CateProc")
    private CateProcInter cateProc;

    @Autowired
    @Qualifier("dev.mvc.contents.ContentsProc") // @Component("dev.mvc.contents.ContentsProc")
    private ContentsProcInter contentsProc;

    @Autowired
    @Qualifier("dev.mvc.contentsgood.ContentsgoodProc")
    private ContentsgoodProcInter contentsgoodProc;

    public ContentsCont() {
        System.out.println("-> ContentsCont created.");
    }

    /**
     * POST 요청시 새로고침 방지, POST 요청 처리 완료 → redirect → url → GET → forward -> html 데이터
     * 전송
     *
     * @return
     */
    @GetMapping(value = "/msg")
    public String post2get(Model model, String url) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        return url; // forward, /templates/...
    }

    // 등록 폼, contents 테이블은 FK로 cateno를 사용함.
    // http://localhost:9091/contents/create X
    // http://localhost:9091/contents/create?cateno=1 // cateno 변수값을 보내는 목적
    // http://localhost:9091/contents/create?cateno=2
    // http://localhost:9091/contents/create?cateno=5
    @GetMapping(value = "/create")
    public String create(Model model,
                         @ModelAttribute("contentsVO") ContentsVO contentsVO,
                         @RequestParam(name="cateno", defaultValue="0") int cateno) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        CateVO cateVO = cateProc.read(cateno); // 카테고리 정보를 출력하기위한 목적
        model.addAttribute("cateVO", cateVO);

        return "contents/create"; // /templates/contents/create.html
    }

    /**
     * 등록 처리 http://localhost:9091/contents/create
     *
     * @return
     */
    @PostMapping(value = "/create")
    public String create(HttpSession session,
                         Model model,
                         @ModelAttribute("contentsVO") ContentsVO contentsVO,
                         RedirectAttributes ra) {

        if (memberProc.isAdmin(session)) { // 관리자로 로그인한경우
            // ------------------------------------------------------------------------------
            // 파일 전송 코드 시작
            // ------------------------------------------------------------------------------
            String file1 = ""; // 원본 파일명 image
            String file1saved = ""; // 저장된 파일명, image
            String thumb1 = ""; // preview image

            String upDir = Contents.getUploadDir(); // 파일을 업로드할 폴더 준비
            // upDir = upDir + "/" + 한글을 제외한 카테고리 이름
            System.out.println("-> upDir: " + upDir);

            // 전송 파일이 없어도 file1MF 객체가 생성됨.
            // <input type='file' class="form-control" name='file1MF' id='file1MF'
            // value='' placeholder="파일 선택">
            MultipartFile mf = contentsVO.getFile1MF();

            file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
            System.out.println("-> 원본 파일명 산출 file1: " + file1);

            long size1 = mf.getSize(); // 파일 크기
            if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
                if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
                    // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
                    file1saved = Upload.saveFileSpring(mf, upDir);

                    if (Tool.isImage(file1saved)) { // 이미지인지 검사
                        // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
                        thumb1 = Tool.preview(upDir, file1saved, 200, 150);
                    }

                    contentsVO.setFile1(file1); // 순수 원본 파일명
                    contentsVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
                    contentsVO.setThumb1(thumb1); // 원본이미지 축소판
                    contentsVO.setSize1(size1); // 파일 크기

                } else { // 전송 못하는 파일 형식
                    ra.addFlashAttribute("code", Tool.UPLOAD_FILE_CHECK_FAIL); // 업로드 할 수 없는 파일
                    ra.addFlashAttribute("cnt", 0); // 업로드 실패
                    ra.addFlashAttribute("url", "/contents/msg"); // msg.html, redirect parameter 적용
                    return "redirect:/contents/msg"; // Post -> Get - param...
                }
            } else { // 글만 등록하는 경우
                System.out.println("-> 글만 등록");
            }

            // ------------------------------------------------------------------------------
            // 파일 전송 코드 종료
            // ------------------------------------------------------------------------------

            // Call By Reference: 메모리 공유, Hashcode 전달
            int memberno = (int) session.getAttribute("memberno"); // memberno FK
            contentsVO.setMemberno(memberno);
            int cnt = contentsProc.create(contentsVO);

            // ------------------------------------------------------------------------------
            // PK의 return
            // ------------------------------------------------------------------------------
            // System.out.println("--> contentsno: " + contentsVO.getContentsno());
            // mav.addObject("contentsno", contentsVO.getContentsno()); // redirect
            // parameter 적용
            // ------------------------------------------------------------------------------

            if (cnt == 1) {
                // type 1, 재업로드 발생
                // return "<h1>파일 업로드 성공</h1>"; // 연속 파일 업로드 발생

                // type 2, 재업로드 발생
                // model.addAttribute("cnt", cnt);
                // model.addAttribute("code", "create_success");
                // return "contents/msg";

                // type 3 권장
                // return "redirect:/contents/list_all"; // /templates/contents/list_all.html

                // System.out.println("-> contentsVO.getCateno(): " + contentsVO.getCateno());
                // ra.addFlashAttribute("cateno", contentsVO.getCateno()); // controller ->
                // controller: X

//                return "redirect:/contents/list_all";
                CateVO cateVO = cateProc.read(contentsVO.getCateno());
                Integer cnt1 = cateVO.getCnt();
                cateVO.setCnt(cnt1 + 1);

                ra.addAttribute("cateno", contentsVO.getCateno()); // controller -> controller: O
                return "redirect:/contents/list_by_cateno";

                // return "redirect:/contents/list_by_cateno?cateno=" + contentsVO.getCateno();
                // // /templates/contents/list_by_cateno.html
            } else {
                ra.addFlashAttribute("code", Tool.CREATE_FAIL); // DBMS 등록 실패
                ra.addFlashAttribute("cnt", 0); // 업로드 실패
                ra.addFlashAttribute("url", "/contents/msg"); // msg.html, redirect parameter 적용
                return "redirect:/contents/msg"; // Post -> Get - param...
            }
        } else { // 로그인 실패 한 경우
            // /member/login_cookie_need?url=/contents/create?cateno=1
            return "redirect:/member/login_cookie_need?url=/contents/create?cateno=" + contentsVO.getCateno();
        }
    }

    /**
     * 전체 목록, 관리자만 사용 가능 http://localhost:9091/contents/list_all
     *
     * @return
     */
    @GetMapping(value = "/list_all")
    public String list_all(HttpSession session, Model model) {
        // System.out.println("-> list_all");
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        if (memberProc.isAdmin(session)) { // 관리자만 조회 가능
            ArrayList<ContentsVO> list = contentsProc.list_all(); // 모든 목록

            // Thymeleaf는 CSRF(크로스사이트) 스크립팅 해킹 방지 자동 지원
            // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
//      for (ContentsVO contentsVO : list) {
//        String title = contentsVO.getTitle();
//        String content = contentsVO.getContent();
//
//        title = Tool.convertChar(title);  // 특수 문자 처리
//        content = Tool.convertChar(content);
//
//        contentsVO.setTitle(title);
//        contentsVO.setContent(content);
//
//      }

            model.addAttribute("list", list);
            return "contents/list_all";

        } else {
            return "redirect:/member/login_cookie_need";

        }

    }

//  /**
//   * 유형 1
//   * 카테고리별 목록
//   * http://localhost:9091/contents/list_by_cateno?cateno=5
//   * http://localhost:9091/contents/list_by_cateno?cateno=6
//   * @return
//   */
//  @GetMapping(value="/list_by_cateno")
//  public String list_by_cateno(HttpSession session, Model model,
//                               @RequestParam(name = "cateno", defaultValue = "") int cateno) {
//    ArrayList<CateVOMenu> menu = cateProc.menu();
//    model.addAttribute("menu", menu);
//
//     CateVO cateVO = cateProc.read(cateno);
//     model.addAttribute("cateVO", cateVO);
//
//    ArrayList<ContentsVO> list = contentsProc.list_by_cateno(cateno);
//    model.addAttribute("list", list);
//
//    // System.out.println("-> size: " + list.size());
//
//    return "contents/list_by_cateno";   // templates/contents/list_by_cateno.html
//  }

//  /**
//   * 유형 2
//   * 카테고리별 목록 + 검색
//   * http://localhost:9091/contents/list_by_cateno?cateno=5&word=카페
//   * http://localhost:9091/contents/list_by_cateno?cateno=6&word=뉴스
//   * @return
//   */
//  @GetMapping(value="/list_by_cateno")
//  public String list_by_cateno_search(HttpSession session, Model model,
//                                      @RequestParam(value = "cateno", defaultValue = "0") int cateno,
//                                      @RequestParam(name="word", defaultValue = "") String word) {
//    ArrayList<CateVOMenu> menu = cateProc.menu();
//    model.addAttribute("menu", menu);
//
//     CateVO cateVO = cateProc.read(cateno);
//     model.addAttribute("cateVO", cateVO);
//
//     word = Tool.checkNull(word).trim();
//
//     HashMap<String, Object> map = new HashMap<>();
//     map.put("cateno", cateno);
//     map.put("word", word);
//
//    ArrayList<ContentsVO> list = contentsProc.list_by_cateno_search(map);
//    model.addAttribute("list", list);
//
//    // System.out.println("-> size: " + list.size());
//    model.addAttribute("word", word);
//
////    int search_count = contentsProc.list_by_cateno_search_count(map);
//      int search_count = list.size();   // 검색된 레코드 갯수
//      model.addAttribute("search_count", search_count);
//
//    return "contents/list_by_cateno_search"; // /templates/contents/list_by_cateno_search.html
//  }

  /**
   * 유형 3
   * 카테고리별 목록 + 검색 + 페이징 http://localhost:9091/contents/list_by_cateno?cateno=5
   * http://localhost:9091/contents/list_by_cateno?cateno=6
   */
  @GetMapping(value = "/list_by_cateno")
  public String list_by_cateno_search_paging(HttpSession session, Model model,
                                             @RequestParam(name = "cateno", defaultValue = "0") int cateno,
                                             @RequestParam(name = "word", defaultValue = "") String word,
                                             @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ArrayList<CateVOMenu> menu = cateProc.menu();
    model.addAttribute("menu", menu);

    CateVO cateVO = cateProc.read(cateno);
    model.addAttribute("cateVO", cateVO);

    word = Tool.checkNull(word).trim();

    HashMap<String, Object> map = new HashMap<>();
    map.put("cateno", cateno);
    map.put("word", word);
    map.put("now_page", now_page);

    ArrayList<ContentsVO> list = contentsProc.list_by_cateno_search_paging(map);
    model.addAttribute("list", list);

    // System.out.println("-> size: " + list.size());
    model.addAttribute("word", word);

    int search_count = contentsProc.list_by_cateno_search_count(map);
    String paging = contentsProc.pagingBox(cateno, now_page, word, "/contents/list_by_cateno", search_count,
    Contents.RECORD_PER_PAGE, Contents.PAGE_PER_BLOCK);
    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);

    model.addAttribute("search_count", search_count);

    // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
    int no = search_count - ((now_page - 1) * Contents.RECORD_PER_PAGE);
    model.addAttribute("no", no);

    return "contents/list_by_cateno_search_paging"; // /templates/contents/list_by_cateno_search_paging.html
  }

    /**
     * 카테고리별 목록 + 검색 + 페이징 + Grid
     * http://localhost:9091/contents/list_by_cateno?cateno=5
     * http://localhost:9091/contents/list_by_cateno?cateno=6
     */
    @GetMapping(value = "/list_by_cateno_grid")
    public String list_by_cateno_search_paging_grid(HttpSession session, Model model,
                                                    @RequestParam(name = "cateno", defaultValue = "0") int cateno,
                                                    @RequestParam(name = "word", defaultValue = "") String word,
                                                    @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        CateVO cateVO = cateProc.read(cateno);
        model.addAttribute("cateVO", cateVO);

        word = Tool.checkNull(word).trim();

        HashMap<String, Object> map = new HashMap<>();
        map.put("cateno", cateno);
        map.put("word", word);
        map.put("now_page", now_page);

        ArrayList<ContentsVO> list = contentsProc.list_by_cateno_search_paging(map);
        model.addAttribute("list", list);

        // System.out.println("-> size: " + list.size());
        model.addAttribute("word", word);

        int search_count = contentsProc.list_by_cateno_search_count(map);
        String paging = contentsProc.pagingBox(cateno, now_page, word, "/contents/list_by_cateno_grid", search_count,
                Contents.RECORD_PER_PAGE, Contents.PAGE_PER_BLOCK);
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);

        model.addAttribute("search_count", search_count);

        // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
        int no = search_count - ((now_page - 1) * Contents.RECORD_PER_PAGE);
        model.addAttribute("no", no);

        // /templates/contents/list_by_cateno_search_paging_grid.html
        return "contents/list_by_cateno_search_paging_grid";
    }

    /**
     * 조회 http://localhost:9091/contents/read?contentsno=17
     *
     * @return
     */
    @GetMapping(value = "/read")
    public String read(HttpSession session, Model model,
                       @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                       @RequestParam(name = "word", defaultValue = "") String word,
                       @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        ContentsVO contentsVO = contentsProc.read(contentsno);
        model.addAttribute("contentsVO", contentsVO);

        long size1 = contentsVO.getSize1();
        String size1_label = Tool.unit(size1);
        contentsVO.setSize1_label(size1_label);

        CateVO cateVO = cateProc.read(contentsVO.getCateno());
        model.addAttribute("cateVO", cateVO);

        // 조회에서 화면 하단에 출력
        // ArrayList<ReplyVO> reply_list = replyProc.list_contents(contentsno);
        // mav.addObject("reply_list", reply_list);

        model.addAttribute("word", word);
        model.addAttribute("now_page", now_page);
        // -------------------------------------------------------------------------------------------
        // 추천 관련
        // -------------------------------------------------------------------------------------------
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("contentsno", contentsno);

        int hartCnt = 0;
        if (session.getAttribute("memberno") != null ) { // 회원인 경우만 카운트 처리
            int memberno = (int) session.getAttribute("memberno");
            map.put("memberno", memberno);

            hartCnt = contentsgoodProc.hartCnt(map);
        }

        model.addAttribute("hartCnt", hartCnt);

        return "contents/read";
    }

    /**
     * 맵 등록/수정/삭제 폼 http://localhost:9091/contents/map?contentsno=1
     * @return
     */
    @GetMapping(value = "/map")
    public String map(Model model,
                      @RequestParam(name = "contentsno", defaultValue = "0") int contentsno) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        ContentsVO contentsVO = contentsProc.read(contentsno); // map 정보 읽어 오기
        model.addAttribute("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

        CateVO cateVO = cateProc.read(contentsVO.getCateno()); // 그룹 정보 읽기
        model.addAttribute("cateVO", cateVO);

        return "contents/map";
    }

    /**
     * MAP 등록/수정/삭제 처리 http://localhost:9091/contents/map
     * @return
     */
    @PostMapping(value = "/map")
    public String map_update(Model model,
                             @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                             @RequestParam(name = "map", defaultValue = "") String map) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("contentsno", contentsno);
        hashMap.put("map", map);

        contentsProc.map(hashMap);

        return "redirect:/contents/read?contentsno=" + contentsno;
    }

    /**
     * Youtube 등록/수정/삭제 폼 http://localhost:9091/contents/youtube?contentsno=1
     * @return
     */
    @GetMapping(value = "/youtube")
    public String youtube(Model model,
                          @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                          @RequestParam(name = "word", defaultValue = "") String word,
                          @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        ContentsVO contentsVO = contentsProc.read(contentsno); // map 정보 읽어 오기
        model.addAttribute("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

        CateVO cateVO = cateProc.read(contentsVO.getCateno()); // 그룹 정보 읽기
        model.addAttribute("cateVO", cateVO);

        model.addAttribute("word", word);
        model.addAttribute("now_page", now_page);

        return "contents/youtube";  // forward
    }

    /**
     * Youtube 등록/수정/삭제 처리 http://localhost:9091/contents/youtube
     * @return
     */
    @PostMapping(value = "/youtube")
    public String youtube_update(Model model,
                                 RedirectAttributes ra,
                                 @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                                 @RequestParam(name = "youtube", defaultValue = "") String youtube,
                                 @RequestParam(name = "word", defaultValue = "") String word,
                                 @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

        if (youtube.trim().length() > 0) { // 삭제 중인지 확인, 삭제가 아니면 youtube 크기 변경
            youtube = Tool.youtubeResize(youtube, 640); // youtube 영상의 크기를 width 기준 640 px로 변경
        }

        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("contentsno", contentsno);
        hashMap.put("youtube", youtube);

        contentsProc.youtube(hashMap);

        ra.addAttribute("contentsno", contentsno);
        ra.addAttribute("word", word);
        ra.addAttribute("now_page", now_page);

        // return "redirect:/contents/read?contentsno=" + contentsno;
        return "redirect:/contents/read";
    }

    /**
     * 수정 폼 http:// localhost:9091/contents/update_text?contentsno=1
     *
     */
    @GetMapping(value = "/update_text")
    public String update_text(HttpSession session, Model model,
                              @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                              RedirectAttributes ra,
                              @RequestParam(name = "word", defaultValue = "") String word,
                              @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        model.addAttribute("word", word);
        model.addAttribute("now_page", now_page);

        if (memberProc.isAdmin(session)) { // 관리자로 로그인한경우
            ContentsVO contentsVO = contentsProc.read(contentsno);
            model.addAttribute("contentsVO", contentsVO);

            CateVO cateVO = cateProc.read(contentsVO.getCateno());
            model.addAttribute("cateVO", cateVO);

            return "contents/update_text"; // /templates/contents/update_text.html
            // String content = "장소:\n인원:\n준비물:\n비용:\n기타:\n";
            // model.addAttribute("content", content);

        } else {
            // ra.addAttribute("url", "/member/login_cookie_need"); // /templates/member/login_cookie_need.html
            // return "redirect:/contents/msg"; // @GetMapping(value = "/read")
            // 로그인 후 텍스트 수정 폼이 자동으로 열림
            return "redirect:/member/login_cookie_need?url=/contents/update_text?contentsno=" + contentsno;
        }

    }

    /**
     * 수정 처리 http://localhost:9091/contents/update_text?contentsno=1
     */
    @PostMapping(value = "/update_text")
    public String update_text_proc(HttpSession session, Model model,
                              @ModelAttribute("contentsVO") ContentsVO contentsVO,
                              RedirectAttributes ra,
                              @RequestParam(name = "search_word", defaultValue = "") String search_word, // contentsVO.word와 구분 필요
                              @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
        ra.addAttribute("word", search_word);
        ra.addAttribute("now_page", now_page);

        if (memberProc.isAdmin(session)) { // 관리자 로그인 확인
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("contentsno", contentsVO.getContentsno());
            map.put("passwd", contentsVO.getPasswd());

            if (contentsProc.password_check(map) == 1) { // 패스워드 일치
                contentsProc.update_text(contentsVO); // 글수정

                // mav 객체 이용
                ra.addAttribute("contentsno", contentsVO.getContentsno());
                ra.addAttribute("cateno", contentsVO.getCateno());
                return "redirect:/contents/read"; // @GetMapping(value = "/read")

            } else { // 패스워드 불일치
                ra.addFlashAttribute("code", "passwd_fail"); // redirect -> forward -> html
                ra.addFlashAttribute("cnt", 0);
                ra.addAttribute("url", "/contents/msg"); // msg.html, redirect parameter 적용

                return "redirect:/contents/msg"; // @GetMapping(value = "/msg")
            }
        } else { // 정상적인 로그인이 아닌 경우 로그인 유도
            return "redirect:/member/login_cookie_need?url=/contents/update_text?contentsno=" + contentsVO.getContentsno();
        }

    }

    /**
     * 파일 수정 폼 http://localhost:9091/contents/update_file?contentsno=1
     */
    @GetMapping(value = "/update_file")
    public String update_file(HttpSession session, Model model,
                              @RequestParam(value = "contentsno", defaultValue = "0") int contentsno,
                              @RequestParam(value = "word", defaultValue = "") String word,
                              @RequestParam(value = "now_page", defaultValue = "1") int now_page) {

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        model.addAttribute("word", word);
        model.addAttribute("now_page", now_page);

        if (memberProc.isAdmin(session)) {
            ContentsVO contentsVO = contentsProc.read(contentsno);
            model.addAttribute("contentsVO", contentsVO);

            CateVO cateVO = cateProc.read(contentsVO.getCateno());
            model.addAttribute("cateVO", cateVO);

            return "contents/update_file";
        } else {
            return "redirect:/member/login_cookie_need?url=/contents/update_text?contentsno=" + contentsno;
        }



    }

    /**
     * 파일 수정 처리 http://localhost:9091/contents/update_file
     */
    @PostMapping(value = "/update_file")
    public String update_file(HttpSession session, Model model, RedirectAttributes ra,
                              @ModelAttribute("contentsVO") ContentsVO contentsVO,
                              @RequestParam(value = "word", defaultValue = "") String word,
                              @RequestParam(value = "now_page", defaultValue = "1") int now_page) {

        if (memberProc.isAdmin(session)) {
            // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드 저장용
            ContentsVO contentsVO_old = contentsProc.read(contentsVO.getContentsno());

            // -------------------------------------------------------------------
            // 파일 삭제 시작
            // -------------------------------------------------------------------
            String file1saved = contentsVO_old.getFile1saved(); // 실제 저장된 파일명
            String thumb1 = contentsVO_old.getThumb1(); // 실제 저장된 preview 이미지 파일명
            long size1 = 0;

            String upDir = Contents.getUploadDir(); // C:/kd/deploy/resort_v4sbm3c/contents/storage/

            Tool.deleteFile(upDir, file1saved); // 실제 저장된 파일삭제
            Tool.deleteFile(upDir, thumb1); // preview 이미지 삭제
            // -------------------------------------------------------------------
            // 파일 삭제 종료
            // -------------------------------------------------------------------

            // -------------------------------------------------------------------
            // 파일 전송 시작
            // -------------------------------------------------------------------
            String file1 = ""; // 원본 파일명 image

            // 전송 파일이 없어도 file1MF 객체가 생성됨.
            // <input type='file' class="form-control" name='file1MF' id='file1MF'
            // value='' placeholder="파일 선택">
            MultipartFile mf = contentsVO.getFile1MF();

            file1 = mf.getOriginalFilename(); // 원본 파일명
            size1 = mf.getSize(); // 파일 크기

            if (size1 > 0) { // 폼에서 새롭게 올리는 파일이 있는지 파일 크기로 체크 ★
                // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
                file1saved = Upload.saveFileSpring(mf, upDir);

                if (Tool.isImage(file1saved)) { // 이미지인지 검사
                    // thumb 이미지 생성후 파일명 리턴됨, width: 250, height: 200
                    thumb1 = Tool.preview(upDir, file1saved, 250, 200);
                }

            } else { // 파일이 삭제만 되고 새로 올리지 않는 경우
                file1 = "";
                file1saved = "";
                thumb1 = "";
                size1 = 0;
            }

            contentsVO.setFile1(file1);
            contentsVO.setFile1saved(file1saved);
            contentsVO.setThumb1(thumb1);
            contentsVO.setSize1(size1);
            // -------------------------------------------------------------------
            // 파일 전송 코드 종료
            // -------------------------------------------------------------------

            contentsProc.update_file(contentsVO); // Oracle 처리
            ra.addAttribute ("contentsno", contentsVO.getContentsno());
            ra.addAttribute("cateno", contentsVO.getCateno());
            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);

            return "redirect:/contents/read";
        } else {
            return "redirect:/member/login_cookie_need?url=/contents/update_text?contentsno=" + contentsVO.getContentsno();
        }
    }

    /**
     * 파일 삭제 폼
     * http://localhost:9091/contents/delete?contentsno=1
     *
     * @return
     */
    @GetMapping(value = "/delete")
    public String delete(HttpSession session, Model model, RedirectAttributes ra,
                         @RequestParam(name = "cateno", defaultValue = "0") int cateno,
                         @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
        if (memberProc.isAdmin(session)) { // 관리자로 로그인한경우
            model.addAttribute("cateno", cateno);
            model.addAttribute("word", word);
            model.addAttribute("now_page", now_page);

            ArrayList<CateVOMenu> menu = cateProc.menu();
            model.addAttribute("menu", menu);

            ContentsVO contentsVO = contentsProc.read(contentsno);
            model.addAttribute("contentsVO", contentsVO);

            CateVO cateVO = cateProc.read(contentsVO.getCateno());
            model.addAttribute("cateVO", cateVO);

            return "contents/delete"; // forward

        } else {
            return "redirect:/member/login_cookie_need?url=/contents/update_text?contentsno=" + contentsno;
        }

    }

    /**
     * 삭제 처리 http://localhost:9091/contents/delete
     *
     * @return
     */
    @PostMapping(value = "/delete")
    public String delete_proc(HttpSession session, Model model, RedirectAttributes ra,
                         @RequestParam(name = "contentsno", defaultValue = "0") int contentsno,
                         @RequestParam(name = "cateno", defaultValue = "0") int cateno,
                         @RequestParam(name = "word", defaultValue = "") String word,
                         @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

        if (memberProc.isAdmin(session)) {
            // -------------------------------------------------------------------
            // 파일 삭제 시작
            // -------------------------------------------------------------------
            // 삭제할 파일 정보를 읽어옴.
            ContentsVO contentsVO_read = contentsProc.read(contentsno);

            String file1saved = contentsVO_read.getFile1saved();
            String thumb1 = contentsVO_read.getThumb1();

            String uploadDir = Contents.getUploadDir();
            Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
            Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
            // -------------------------------------------------------------------
            // 파일 삭제 종료
            // -------------------------------------------------------------------

            contentsProc.delete(contentsno); // DBMS 삭제

            // -------------------------------------------------------------------------------------
            // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
            // -------------------------------------------------------------------------------------
            // 마지막 페이지의 마지막 10번째 레코드를 삭제후
            // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
            // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("cateno", cateno);
            map.put("word", word);

            if (contentsProc.list_by_cateno_search_count(map) % Contents.RECORD_PER_PAGE == 0) {
                now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
                if (now_page < 1) {
                    now_page = 1; // 시작 페이지
                }
            }
            // -------------------------------------------------------------------------------------

            ra.addAttribute("cateno", cateno);
            ra.addAttribute("word", word);
            ra.addAttribute("now_page", now_page);

            return "redirect:/contents/list_by_cateno";
        } else {
            return "redirect:/member/login_cookie_need?url=/contents/update_text?contentsno=" + contentsno;
        }
    }

    /**
     * 추천 처리 http://localhost:9091/contents/good
     *
     * @return
     */
    @PostMapping(value = "/good")
    @ResponseBody
    public String good(HttpSession session, Model model, @RequestBody String json_src){
        System.out.println("-> json_src: " + json_src); // json_src: {"contentsno":"5"}

        JSONObject src = new JSONObject(json_src); // String -> JSON
        int contentsno = (int) src.get("contentsno"); // 값 가져오기
        System.out.println("-> contentsno: " + contentsno);

        if (memberProc.isMember(session)) { // 회원 로그인 확인
            // 추천을 한 상태인지 확인
            int memberno = (int) session.getAttribute("memberno");

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("contentsno", contentsno);
            map.put("memberno", memberno);

            int good_cnt = contentsgoodProc.hartCnt(map);
            System.out.println("-> good_cnt: " + good_cnt);

            if (good_cnt == 1) {
                System.out.println("-> 추천 해제: " + contentsno + ' ' + memberno);

                // contentsgood 테이블에서 추천한 기록을 찾음
                ContentsgoodVO contentsgoodVO = contentsgoodProc.readByContentsnoMemberno(map);

                contentsgoodProc.delete(contentsgoodVO.getContentsgoodno()); // 추천 삭제
                contentsProc.decreaseRecom(contentsno); // 추천 카운트 감소
            } else {
                System.out.println("-> 추천: " + contentsno + ' ' + memberno);

                ContentsgoodVO contentsgoodVO_new = new ContentsgoodVO();
                contentsgoodVO_new.setContentsno(contentsno);
                contentsgoodVO_new.setMemberno(memberno);

                contentsgoodProc.create(contentsgoodVO_new);  // 추천 생성
                contentsProc.increaseRecom(contentsno); // 추천 카운트 증가
            }

            // 추천 여부가 변경되어 다시 새로운 값을 읽어옴.
            int hartCnt = contentsgoodProc.hartCnt(map);
            int recom = contentsProc.read(contentsno).getRecom();

            JSONObject result = new JSONObject();
            result.put("isMember", 1); // 로그인: 1, 비회원: 0
            result.put("hartCnt", hartCnt); // 추천 여부, 추천:1, 비추천: 0
            result.put("recom", recom);   // 추천인수

            System.out.println("-> result.toString(): " + result.toString());
            return result.toString();

        } else { // 정상적인 로그인이 아닌 경우 로그인 유도
            JSONObject result = new JSONObject();
            result.put("isMember", 0); // 로그인: 1, 비회원: 0

            System.out.println("-> result.toString(): " + result.toString());
            return result.toString();
        }

    }

}
