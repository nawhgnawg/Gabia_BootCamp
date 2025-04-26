package dev.mvc.member;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVOMenu;
import dev.mvc.tool.Security;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberCont {
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")  // @Service("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;

    @Autowired
    @Qualifier("dev.mvc.cate.CateProc")
    private CateProcInter cateProc;

    public MemberCont() {
        System.out.println("-> MemberCont created.");
    }

    @GetMapping(value = "/checkID") // http://localhost:9091/member/checkID?id=admin
    @ResponseBody
    public String checkID(@RequestParam(name = "id", defaultValue = "") String id) {
        System.out.println("-> id: " + id);
        int cnt = memberProc.checkID(id);

        // return "cnt: " + cnt;
        // return "{\"cnt\": " + cnt + "}";    // {"cnt": 1} JSON

        JSONObject obj = new JSONObject();
        obj.put("cnt", cnt);

        return obj.toString();
    }

    /**
     * 회원 가입 폼
     *
     * @param model
     * @param memberVO
     * @return
     */
    @GetMapping("/create") // http://localhost:9091/member/create
    public String create_form(Model model,
                              @ModelAttribute("memberVO") MemberVO memberVO) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        return "member/create";    // /template/member/create.html
    }

    @PostMapping(value = "/create")
    public String create_proc(Model model,
                              @ModelAttribute("memberVO") MemberVO memberVO) {
        int checkID_cnt = memberProc.checkID(memberVO.getId());

        if (checkID_cnt == 0) {
            memberVO.setGrade(15); // 기본 회원 15
            int cnt = memberProc.create(memberVO);

            if (cnt == 1) {
                model.addAttribute("code", "create_success");
                model.addAttribute("mname", memberVO.getMname());
                model.addAttribute("id", memberVO.getId());
            } else {
                model.addAttribute("code", "create_fail");
            }

            model.addAttribute("cnt", cnt);
        } else { // id 중복
            model.addAttribute("code", "duplicte_fail");
            model.addAttribute("cnt", 0);
        }

        return "member/msg"; // /templates/member/msg.html
    }

    @GetMapping(value = "/list")
    public String list(HttpSession session, Model model) {
        if (memberProc.isAdmin(session)) {
            ArrayList<MemberVO> list = memberProc.list();
            model.addAttribute("list", list);

            ArrayList<CateVOMenu> menu = cateProc.menu();
            model.addAttribute("menu", menu);

            return "member/list";  // /templates/member/list.html
        } else {
            return "redirect:/member/login_cookie_need?url=/member/list";
        }

    }

    /**
     * 조회
     * @param model
     * @param memberno 회원 번호
     * @return 회원 정보
     */
    @GetMapping(value = "/read")
    public String read(HttpSession session,
                       Model model,
                       @RequestParam(name = "memberno", defaultValue = "") int memberno) {
        String grade = (String) session.getAttribute("grade");

        if (grade.equals("member") && (memberno == (int) session.getAttribute("memberno"))) {
            log.info("read memberno -> {}", memberno);

            MemberVO memberVO = memberProc.read(memberno);
            model.addAttribute("memberVO", memberVO);

            return "member/read";  // templates/member/read.html
        } else if (grade.equals("admin")) {
            log.info("read memberno -> {}", memberno);

            MemberVO memberVO = memberProc.read(memberno);
            model.addAttribute("memberVO", memberVO);

            return "member/read";
        } else {
            return "redirect:/member/login_cookie_need";
        }
    }

    /**
     * 수정 처리
     *
     * @param model
     * @param memberVO
     * @return
     */
    @PostMapping(value = "/update")
    public String update_proc(Model model,
                              @ModelAttribute("memberVO") MemberVO memberVO) {
        int cnt = memberProc.update(memberVO); // 수정


        if (cnt == 1) {
            model.addAttribute("code", "update_success");
            model.addAttribute("mname", memberVO.getMname());
            model.addAttribute("id", memberVO.getId());
        } else {
            model.addAttribute("code", "update_fail");
        }
        model.addAttribute("cnt", cnt);

        return "member/msg"; // /templates/member/msg.html
    }

    /**
     * 삭제
     *
     * @param model
     * @param memberno 회원 번호
     * @return 회원 정보
     */
    @GetMapping(value = "/delete")
    public String delete(Model model, int memberno) {
        System.out.println("-> delete memberno: " + memberno);

        MemberVO memberVO = memberProc.read(memberno);
        model.addAttribute("memberVO", memberVO);

        return "member/delete";  // templates/member/delete.html
    }

    /**
     * 회원 Delete process
     *
     * @param model
     * @param memberno 삭제할 레코드 번호
     * @return
     */
    @PostMapping(value = "/delete")
    public String delete_process(Model model, Integer memberno) {
        int cnt = memberProc.delete(memberno);

        if (cnt == 1) {
            return "redirect:/member/list";
        } else {
            model.addAttribute("code", "delete_fail");
            return "member/msg"; // /templates/member/msg.html
        }
    }

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login_form(Model model, HttpServletRequest request, HttpSession session) {
        log.info("session ID -> {}", session.getId());

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        String ck_id = ""; // id 저장
        String ck_id_save = ""; // id 저장 여부를 체크
        String ck_passwd = ""; // passwd 저장
        String ck_passwd_save = ""; // passwd 저장 여부를 체크

        if (cookie != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];    // 쿠키 객체 추출

                if (cookie.getName().equals("ck_id")){
                    ck_id = cookie.getValue();              // email
                } else if(cookie.getName().equals("ck_id_save")){
                    ck_id_save = cookie.getValue();         // Y, N
                } else if (cookie.getName().equals("ck_passwd")){
                    ck_passwd = cookie.getValue();          // 1234
                } else if(cookie.getName().equals("ck_passwd_save")){
                    ck_passwd_save = cookie.getValue();     // Y, N
                }
            }
        }

        model.addAttribute("ck_id", ck_id);
        model.addAttribute("ck_id_save", ck_id_save);
        model.addAttribute("ck_passwd", ck_passwd);
        model.addAttribute("ck_passwd_save", ck_passwd_save);

        return "member/login_cookie";  // templates/member/login_cookie.html
    }

    /**
     * Cookie 기반 로그인 처리
     */
    @PostMapping("/login")
    public String login_proc(HttpSession session,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             Model model,
                             @RequestParam(value = "id", defaultValue = "") String id,
                             @RequestParam(value = "passwd", defaultValue = "") String passwd,
                             @RequestParam(value = "id_save", defaultValue = "") String id_save,
                             @RequestParam(value = "passwd_save", defaultValue = "") String passwd_save,
                             @RequestParam(value = "url", defaultValue = "") String url) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("passwd", passwd);


        int cnt = memberProc.login(map);

        model.addAttribute("cnt", cnt);
        log.info("login id -> {}", id);
        log.info("login passwd -> {}", passwd);
        log.info("login map.passwd -> {}", map.get("passwd"));
        if (cnt == 1) {
            // id를 이용하여 회원 정보 조회
            MemberVO memberVO = memberProc.readById(id);
            session.setAttribute("memberno", memberVO.getMemberno());
            session.setAttribute("id", memberVO.getId()); // 시스템 변수와 중복됨, 권장하지 않음.
            session.setAttribute("mname", memberVO.getMname());

            // -------------------------------------------------------------------
            // 회원 등급 처리
            // -------------------------------------------------------------------
            if (memberVO.getGrade() >= 1 && memberVO.getGrade() <= 10) {
                session.setAttribute("grade", "admin");
            } else if (memberVO.getGrade() >= 11 && memberVO.getGrade() <= 20) {
                session.setAttribute("grade", "member");
            } else if (memberVO.getGrade() >= 21) {
                session.setAttribute("grade", "guest");
            }

            log.info("grade -> {}", session.getAttribute("grade"));

            // Cookie 관련 코드---------------------------------------------------------
            // -------------------------------------------------------------------
            // id 관련 쿠기 저장
            // -------------------------------------------------------------------
            if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
                Cookie ck_id = new Cookie("ck_id", id);
                ck_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
                ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
                response.addCookie(ck_id); // id 저장
            } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
                Cookie ck_id = new Cookie("ck_id", "");
                ck_id.setPath("/");
                ck_id.setMaxAge(0);
                response.addCookie(ck_id); // id 저장
            }

            // id를 저장할지 선택하는  CheckBox 체크 여부
            Cookie ck_id_save = new Cookie("ck_id_save", id_save);
            ck_id_save.setPath("/");
            ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(ck_id_save);
            // -------------------------------------------------------------------

            // -------------------------------------------------------------------
            // Password 관련 쿠기 저장
            // -------------------------------------------------------------------
            if (passwd_save.equals("Y")) { // 패스워드 저장할 경우
                Cookie ck_passwd = new Cookie("ck_passwd", passwd);
                ck_passwd.setPath("/");
                ck_passwd.setMaxAge(60 * 60 * 24 * 30); // 30 day
                response.addCookie(ck_passwd);
            } else { // N, 패스워드를 저장하지 않을 경우
                Cookie ck_passwd = new Cookie("ck_passwd", "");
                ck_passwd.setPath("/");
                ck_passwd.setMaxAge(0);
                response.addCookie(ck_passwd);
            }
            // passwd를 저장할지 선택하는  CheckBox 체크 여부
            Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
            ck_passwd_save.setPath("/");
            ck_passwd_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(ck_passwd_save);
            // -------------------------------------------------------------------
            // ----------------------------------------------------------------------------
            log.info("url -> {}", url);
            if (!url.isEmpty()){
                return "redirect:" + url;
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("code", "login_fail");
            return "member/msg";
        }
    }

    /**
     * 로그아웃
     */
    @GetMapping(value="/logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();  // 모든 세션 변수 삭제
        return "redirect:/";
    }

    /**
     * 패스워드 수정 폼
     */
    @GetMapping(value="/passwd_update")
    public String passwd_update_form(HttpSession session, Model model) {
        if (memberProc.isMember(session)) {
            int memberno = (int) session.getAttribute("memberno"); // session에서 가져오기

            MemberVO memberVO = memberProc.read(memberno);
            model.addAttribute("memberVO", memberVO);

            ArrayList<CateVOMenu> menu = cateProc.menu();
            model.addAttribute("menu", menu);


            return "member/passwd_update";
        } else {
            return "redirect:/member/login_cookie_need";
        }
    }

    /**
     * 현재 패스워드 확인
     * @return 1: 일치, 0: 불일치
     */
    @PostMapping(value="/passwd_check")
    @ResponseBody
    public String passwd_check(HttpSession session, @RequestBody String json_src) {
        log.info("json_src -> {}", json_src);    // json_src: {"current_passwd":"1234"}

        JSONObject src = new JSONObject(json_src); // String -> JSON

        String current_passwd = (String) src.get("current_passwd"); // 값 가져오기
        log.info("current_passwd -> {}", current_passwd);

        try {
            Thread.sleep(3000);
        } catch(Exception e) { }

        int memberno = (int) session.getAttribute("memberno"); // session에서 가져오기
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("memberno", memberno);
        map.put("passwd", current_passwd);

        int cnt = memberProc.passwd_check(map);

        JSONObject json = new JSONObject();
        json.put("cnt", cnt); // 1: 현재 패스워드 일치
        log.info("{}", json.toString());

        return json.toString();
    }

    /**
     * 패스워드 변경
     */
    @PostMapping(value="/passwd_update_proc")
    public String update_passwd_proc(HttpSession session,
                                     Model model,
                                     @RequestParam(value="current_passwd", defaultValue = "") String current_passwd,
                                     @RequestParam(value="passwd", defaultValue = "") String passwd) {
        if (memberProc.isMember(session)) {
            int memberno = (int) session.getAttribute("memberno"); // session에서 가져오기
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("memberno", memberno);
            map.put("passwd", current_passwd);

            int cnt = memberProc.passwd_check(map);

            if (cnt == 0) { // 패스워드 불일치
                model.addAttribute("code", "passwd_not_equal");
                model.addAttribute("cnt", 0);

            } else { // 패스워드 일치
                map = new HashMap<String, Object>();
                map.put("memberno", memberno);
                map.put("passwd", passwd); // 새로운 패스워드

                int passwd_change_cnt = memberProc.passwd_update(map);

                if (passwd_change_cnt == 1) {
                    model.addAttribute("code", "passwd_change_success");
                    model.addAttribute("cnt", 1);
                } else {
                    model.addAttribute("code", "passwd_change_fail");
                    model.addAttribute("cnt", 0);
                }
            }

            return "member/msg";   // /templates/member/msg.html
        } else {
            return "redirect:/member/login_cookie_need";
        }
    }

    /**
     * 로그인 요구에 따른 로그인 폼 출력
     * @param model
     * @param memberno 회원 번호
     * @return 회원 정보
     */
    @GetMapping(value="/login_cookie_need")
    public String login_cookie_need(Model model, HttpServletRequest request,
                                    @RequestParam(name = "url", defaultValue = "") String url) {
        // Cookie 관련 코드---------------------------------------------------------
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        String ck_id = ""; // id 저장
        String ck_id_save = ""; // id 저장 여부를 체크
        String ck_passwd = ""; // passwd 저장
        String ck_passwd_save = ""; // passwd 저장 여부를 체크

        if (cookies != null) { // 쿠키가 존재한다면
            for (int i=0; i < cookies.length; i++){
                cookie = cookies[i]; // 쿠키 객체 추출

                if (cookie.getName().equals("ck_id")){
                    ck_id = cookie.getValue();  // email
                } else if(cookie.getName().equals("ck_id_save")){
                    ck_id_save = cookie.getValue();  // Y, N
                } else if (cookie.getName().equals("ck_passwd")){
                    ck_passwd = cookie.getValue();         // 1234
                } else if(cookie.getName().equals("ck_passwd_save")){
                    ck_passwd_save = cookie.getValue();  // Y, N
                }
            }
        }
        // ----------------------------------------------------------------------------

        //    <input type='text' class="form-control" name='id' id='id'
        //            th:value='${ck_id }' required="required"
        //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
        model.addAttribute("ck_id", ck_id);

        //    <input type='checkbox' name='id_save' value='Y'
        //            th:checked="${ck_id_save == 'Y'}"> 저장
        model.addAttribute("ck_id_save", ck_id_save);

        model.addAttribute("ck_passwd", ck_passwd);
        model.addAttribute("ck_passwd_save", ck_passwd_save);

        //    model.addAttribute("ck_id_save", "Y");
        //    model.addAttribute("ck_passwd_save", "Y");

        model.addAttribute("url", url);

        return "member/login_cookie_need";  // templates/member/login_cookie_need.html
    }

}
