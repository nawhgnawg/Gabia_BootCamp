package dev.mvc.bloguser;

import dev.mvc.category.CategoryProc;
import dev.mvc.category.CategoryVOMenu;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/bloguser")
@Slf4j
public class UserCont {

    @Autowired
    private UserProc userProc;

    @Autowired
    private CategoryProc categoryProc;

    /**
     * 회원 중복
     * @param useremail
     * @return
     */
    @GetMapping("/checkID") // http://localhost:9092/bloguser/checkID?useremail=admin
    @ResponseBody
    public String checkID(@RequestParam(name="useremail", defaultValue = "") String useremail) {
        System.out.println("-> useremail: " + useremail);
        int cnt = userProc.checkID(useremail);

        JSONObject obj = new JSONObject();
        obj.put("cnt", cnt);

        return obj.toString();
    }

    /**
     * 회원 등록 폼 (GET)
     * http://loacalhost:9092/bloguser/create
     */
    @GetMapping("/create")
    public String create_form(Model model, @ModelAttribute("userVO") UserVO userVO) {
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);
        return "bloguser/create";
    }

    /**
     * 회원 등록 처리 (POST)
     * http://loacalhost:9092/bloguser/create
     */
    @PostMapping("/create")
    public String create_proc(Model model, @ModelAttribute("userVO") UserVO userVO) {
        int checkID_cnt = userProc.checkID(userVO.getUseremail());
        System.out.println(checkID_cnt);
        if (checkID_cnt == 0) {
            userVO.setUsergrade(1); // 기본 회원 1
            int cnt = userProc.create(userVO);

            if (cnt == 1) {
                model.addAttribute("code", "create_success");
                model.addAttribute("username", userVO.getUsername());
                model.addAttribute("useremail", userVO.getUseremail());
            } else {
                model.addAttribute("code", "create_fail");
            }

            model.addAttribute("cnt", cnt);
        } else { // id 중복
            model.addAttribute("code", "duplicte_fail");
            model.addAttribute("cnt", 0);
        }

        return "bloguser/msg"; // /templates/bloguser/msg.html
    }

    /**
     * 회원 전체 조회
     * http://loacalhost:9092/bloguser/list
     */
    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        if (userProc.isAdmin(session)) {
            ArrayList<UserVO> list = userProc.list_all();
            model.addAttribute("list", list);

            ArrayList<CategoryVOMenu> menu = categoryProc.menu();
            model.addAttribute("menu", menu);

            return "bloguser/list_all";  // /templates/bloguser/list.html
        } else {
            return "redirect:/bloguser/login_cookie_need?url=/bloguser/list";
        }
    }

    /**
     * 단일 회원 조회
     * http://loacalhost:9092/bloguser/read
     */
    @GetMapping("/read")
    public String read(HttpSession session,
                       Model model,
                       @RequestParam(name="userno", defaultValue = "") int userno) {
        String usergrade = (String) session.getAttribute("usergrade");

        if (usergrade.equals("member") && (userno == (int) session.getAttribute("userno"))) {
            UserVO userVO = userProc.read(userno);
            model.addAttribute("userVO", userVO);

            ArrayList<CategoryVOMenu> menu = categoryProc.menu();
            model.addAttribute("menu", menu);

            return "member/read";  // templates/member/read.html
        } else if (usergrade.equals("admin")) {
            UserVO userVO = userProc.read(userno);
            model.addAttribute("userVO", userVO);

            ArrayList<CategoryVOMenu> menu = categoryProc.menu();
            model.addAttribute("menu", menu);

            return "bloguser/read";
        } else {
            return "redirect:/bloguser/login_cookie_need";
        }
    }

    /**
     * 회원 수정 처리 (POST)
     * http://loacalhost:9092/bloguser/update/1
     */
    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("userVO") UserVO userVO) {

        int cnt = userProc.update(userVO);

        if (cnt == 1) {
            model.addAttribute("code", "update_success");
            model.addAttribute("username", userVO.getUsername());
            model.addAttribute("id", userVO.getUseremail());
        } else {
            model.addAttribute("code", "update_fail");
        }
        model.addAttribute("cnt", cnt);

        return "bloguser/msg";
    }

    @GetMapping("/delete")
    public String delete(Model model, int userno) {
        UserVO userVO = userProc.read(userno);
        model.addAttribute("userVO", userVO);

        return "bloguser/delete";
    }

    @PostMapping("/delete")
    public String delete_process(Model model, int userno) {
        int cnt = userProc.delete(userno);

        if (cnt == 1) {
            return "redirect:/bloguser/list";
        } else {
            model.addAttribute("code", "delete_fail");
            return "bloguser/msg";
        }
    }

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login_form(Model model, HttpServletRequest request, HttpSession session) {

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        String ck_email = ""; // email 저장
        String ck_email_save = ""; // email 저장 여부를 체크
        String ck_password = ""; // password 저장
        String ck_password_save = ""; // password 저장 여부를 체크

        if (cookie != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];    // 쿠키 객체 추출

                if (cookie.getName().equals("ck_email")){
                    ck_email = cookie.getValue();              // email
                } else if(cookie.getName().equals("ck_email_save")){
                    ck_email_save = cookie.getValue();         // Y, N
                } else if (cookie.getName().equals("ck_password")){
                    ck_password = cookie.getValue();          // 1234
                } else if(cookie.getName().equals("ck_password_save")){
                    ck_password_save = cookie.getValue();     // Y, N
                }
            }
        }

        model.addAttribute("ck_email", ck_email);
        model.addAttribute("ck_email_save", ck_email_save);
        model.addAttribute("ck_password", ck_password);
        model.addAttribute("ck_password_save", ck_password_save);

        return "bloguser/login_cookie";  // templates/member/login_cookie.html
    }

    /**
     * Cookie 기반 로그인 처리
     */
    @PostMapping("/login")
    public String login_proc(HttpSession session,
                             HttpServletRequest request,
                             HttpServletResponse response,
                             Model model,
                             @RequestParam(value = "useremail", defaultValue = "") String useremail,
                             @RequestParam(value = "userpassword", defaultValue = "") String userpassword,
                             @RequestParam(value = "email_save", defaultValue = "") String email_save,
                             @RequestParam(value = "password_save", defaultValue = "") String password_save,
                             @RequestParam(value = "url", defaultValue = "") String url) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("useremail", useremail);
        map.put("userpassword", userpassword);
        
        int cnt = userProc.login(map);

        model.addAttribute("cnt", cnt);
        log.info("login id -> {}", useremail);
        log.info("login passwd -> {}", userpassword);
        log.info("login map.passwd -> {}", map.get("passwd"));

        if (cnt == 1) {
            // id를 이용하여 회원 정보 조회
            UserVO userVO = userProc.readByEmail(useremail);
            session.setAttribute("userno", userVO.getUserno());
            session.setAttribute("useremail", userVO.getUseremail()); // 시스템 변수와 중복됨, 권장하지 않음.
            session.setAttribute("username", userVO.getUsername());
            // session.setAttribute("usergrade", userVO.getUsergrade());

            // -------------------------------------------------------------------
            // 회원 등급 처리
            // -------------------------------------------------------------------
            if (userVO.getUsergrade() >= 1 && userVO.getUsergrade() <= 10) {
                session.setAttribute("usergrade", "admin");
            } else if (userVO.getUsergrade() >= 11 && userVO.getUsergrade() <= 20) {
                session.setAttribute("usergrade", "member");
            } else if (userVO.getUsergrade() >= 21) {
                session.setAttribute("usergrade", "guest");
            }

            // Cookie 관련 코드---------------------------------------------------------
            // -------------------------------------------------------------------
            // id 관련 쿠기 저장
            // -------------------------------------------------------------------
            if (email_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
                Cookie ck_email = new Cookie("ck_email", useremail);
                ck_email.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
                ck_email.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
                response.addCookie(ck_email); // id 저장
            } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
                Cookie ck_email = new Cookie("ck_email", "");
                ck_email.setPath("/");
                ck_email.setMaxAge(0);
                response.addCookie(ck_email); // id 저장
            }

            // id를 저장할지 선택하는  CheckBox 체크 여부
            Cookie ck_email_save = new Cookie("ck_email_save", email_save);
            ck_email_save.setPath("/");
            ck_email_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(ck_email_save);
            // -------------------------------------------------------------------

            // -------------------------------------------------------------------
            // Password 관련 쿠기 저장
            // -------------------------------------------------------------------
            if (password_save.equals("Y")) { // 패스워드 저장할 경우
                Cookie ck_password = new Cookie("ck_password", userpassword);
                ck_password.setPath("/");
                ck_password.setMaxAge(60 * 60 * 24 * 30); // 30 day
                response.addCookie(ck_password);
            } else { // N, 패스워드를 저장하지 않을 경우
                Cookie ck_password = new Cookie("ck_password", "");
                ck_password.setPath("/");
                ck_password.setMaxAge(0);
                response.addCookie(ck_password);
            }
            // passwd를 저장할지 선택하는  CheckBox 체크 여부
            Cookie ck_password_save = new Cookie("ck_password_save", password_save);
            ck_password_save.setPath("/");
            ck_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(ck_password_save);
            // -------------------------------------------------------------------
            // ----------------------------------------------------------------------------

            if (!url.isEmpty()){
                return "redirect:" + url;
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("code", "login_fail");
            return "bloguser/msg";
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
        if (userProc.isMember(session)) {
            int userno = (int) session.getAttribute("userno"); // session에서 가져오기

            UserVO userVO = userProc.read(userno);
            model.addAttribute("userVO", userVO);

            ArrayList<CategoryVOMenu> menu = categoryProc.menu();
            model.addAttribute("menu", menu);

            return "bloguser/passwd_update";
        } else {
            return "redirect:/bloguser/login_cookie_need";
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

        String current_password = (String) src.get("current_password"); // 값 가져오기
        log.info("current_passwd -> {}", current_password);

        try {
            Thread.sleep(3000);
        } catch(Exception e) { }

        int userno = (int) session.getAttribute("userno"); // session에서 가져오기
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userno", userno);
        map.put("userpassword", current_password);

        int cnt = userProc.passwd_check(map);

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
                                     @RequestParam(value="current_password", defaultValue = "") String current_password,
                                     @RequestParam(value="userpassword", defaultValue = "") String userpassword) {
        if (userProc.isMember(session)) {
            int userno = (int) session.getAttribute("userno"); // session에서 가져오기
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userno", userno);
            map.put("userpassword", current_password);

            int cnt = userProc.passwd_check(map);

            if (cnt == 0) { // 패스워드 불일치
                model.addAttribute("code", "passwd_not_equal");
                model.addAttribute("cnt", 0);

            } else { // 패스워드 일치
                map = new HashMap<String, Object>();
                map.put("userno", userno);
                map.put("userpassword", userpassword); // 새로운 패스워드

                int password_change_cnt = userProc.passwd_update(map);

                if (password_change_cnt == 1) {
                    model.addAttribute("code", "passwd_change_success");
                    model.addAttribute("cnt", 1);
                } else {
                    model.addAttribute("code", "passwd_change_fail");
                    model.addAttribute("cnt", 0);
                }
            }

            return "bloguser/msg";   // /templates/bloguser/msg.html
        } else {
            return "redirect:/bloguser/login_cookie_need";
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

        String ck_email = ""; // email 저장
        String ck_email_save = ""; // email 저장 여부를 체크
        String ck_password = ""; // password 저장
        String ck_password_save = ""; // password 저장 여부를 체크

        if (cookies != null) { // 쿠키가 존재한다면
            for (int i = 0; i < cookies.length; i++){
                cookie = cookies[i]; // 쿠키 객체 추출

                if (cookie.getName().equals("ck_email")){
                    ck_email = cookie.getValue();  // email
                } else if(cookie.getName().equals("ck_email_save")){
                    ck_email_save = cookie.getValue();  // Y, N
                } else if (cookie.getName().equals("ck_password")){
                    ck_password = cookie.getValue();         // 1234
                } else if(cookie.getName().equals("ck_password_save")){
                    ck_password_save = cookie.getValue();  // Y, N
                }
            }
        }
        // ----------------------------------------------------------------------------

        //    <input type='text' class="form-control" name='id' id='id'
        //            th:value='${ck_id }' required="required"
        //            style='width: 30%;' placeholder="아이디" autofocus="autofocus">
        model.addAttribute("ck_email", ck_email);

        //    <input type='checkbox' name='id_save' value='Y'
        //            th:checked="${ck_id_save == 'Y'}"> 저장
        model.addAttribute("ck_email_save", ck_email_save);

        model.addAttribute("ck_password", ck_password);
        model.addAttribute("ck_password_save", ck_password_save);

        //    model.addAttribute("ck_id_save", "Y");
        //    model.addAttribute("ck_passwd_save", "Y");

        model.addAttribute("url", url);

        return "bloguser/login_cookie_need";  // templates/member/login_cookie_need.html
    }

}

