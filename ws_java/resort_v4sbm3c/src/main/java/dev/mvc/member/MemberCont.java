package dev.mvc.member;

import dev.mvc.cate.CateProcInter;
import dev.mvc.cate.CateVOMenu;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping(value="/checkID") // http://localhost:9091/member/checkID?id=admin
    @ResponseBody
    public String checkID(@RequestParam(name="id", defaultValue = "") String id) {
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
     * @param model
     * @param memberVO
     * @return
     */
    @GetMapping("/create") // http://localhost:9091/member/create
    public String create_form(Model model,
                              @ModelAttribute("memberVO") MemberVO memberVO) {
        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        return "/member/create";    // /template/member/create.html
    }

    @PostMapping(value="/create")
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

    @GetMapping(value="/list")
    public String list(Model model) {
        ArrayList<MemberVO> list = memberProc.list();

        model.addAttribute("list", list);

        return "member/list";  // /templates/member/list.html
    }

    /**
     * 조회
     * @param model
     * @param memberno 회원 번호
     * @return 회원 정보
     */
    @GetMapping(value="/read")
    public String read(Model model,
                       @RequestParam(name="memberno", defaultValue = "") int memberno) {
        System.out.println("-> read memberno: " + memberno);

        MemberVO memberVO = memberProc.read(memberno);
        model.addAttribute("memberVO", memberVO);

        ArrayList<CateVOMenu> menu = cateProc.menu();
        model.addAttribute("menu", menu);

        return "member/read";  // templates/member/read.html
    }

    /**
     * 수정 처리
     * @param model
     * @param memberVO
     * @return
     */
    @PostMapping(value="/update")
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
     * @param model
     * @param memberno 회원 번호
     * @return 회원 정보
     */
    @GetMapping(value="/delete")
    public String delete(Model model, int memberno) {
        System.out.println("-> delete memberno: " + memberno);

        MemberVO memberVO = memberProc.read(memberno);
        model.addAttribute("memberVO", memberVO);

        return "member/delete";  // templates/member/delete.html
    }

    /**
     * 회원 Delete process
     * @param model
     * @param memberno 삭제할 레코드 번호
     * @return
     */
    @PostMapping(value="/delete")
    public String delete_process(Model model, Integer memberno) {
        int cnt = memberProc.delete(memberno);

        if (cnt == 1) {
            return "redirect:/member/list";
        } else {
            model.addAttribute("code", "delete_fail");
            return "member/msg"; // /templates/member/msg.html
        }
    }
}
