package dev.mvc.calendar;

import dev.mvc.bloguser.UserProcInter;
import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVOMenu;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarCont {
    @Autowired
    @Qualifier("dev.mvc.bloguser.UserProc")
    private UserProcInter userProc;

    @Autowired
    @Qualifier("dev.mvc.category.CategoryProc")
    private CategoryProcInter categoryProc;

    @Autowired
    @Qualifier("dev.mvc.calendar.CalendarProc")
    private CalendarProcInter calendarProc;

    /**
     * POST 요청시 새로고침 방지, POST 요청 처리 완료 → redirect → url → GET → forward -> html 데이터 전송
     */
    @GetMapping(value = "/post2get")
    public String post2get(Model model,
                           @RequestParam(name="url", defaultValue = "") String url) {
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return url;
    }

    // http://localhost:9092/calendar/create
    @GetMapping(value = "/create")
    public String create(Model model) {
        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return "calendar/create";
    }

    /**
     * 등록 처리
     * http://localhost:9092/calendar/create
     */
    @PostMapping(value = "/create")
    public String create(HttpSession session, Model model,
                         @ModelAttribute("calendarVO") CalendarVO calendarVO) {
        int userno = 1; // 테스트용
        calendarVO.setUserno(userno);

        int cnt = calendarProc.create(calendarVO);

        if (cnt == 1) {

            return "redirect:/calendar/list_all";
        } else {
            model.addAttribute("code", "create_fail");
        }
        model.addAttribute("cnt", cnt);

        return "calendar/msg";
    }

    /**
     * 목록
     * http://localhost:9092/calendar/list_all
     */
    @GetMapping("/list_all")
    public String list_all(Model model) {
        ArrayList<CalendarVO> list = calendarProc.list_all();
        model.addAttribute("list", list);

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        return "calendar/list_all";
    }

    /**
     * 조회
     * http://localhost:9092/calendar/read/1
     */
    @GetMapping(path = "/read/{calendarno}")
    public String read(Model model, @PathVariable("calendarno") int calendarno) {

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        calendarProc.increaseCnt(calendarno); // 조회수 증가

        CalendarVO calendarVO = calendarProc.read(calendarno);

        model.addAttribute("calendarVO", calendarVO);

        return "calendar/read";
    }

    /**
     * 수정 폼
     * http://localhost:9092/calendar/update?calendarno=1
     */
    @GetMapping(value = "/update")
    public String update_text(HttpSession session, Model model,
                              @RequestParam(name="calendarno", defaultValue = "0") int calendarno,
                              RedirectAttributes ra) {

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        if (userProc.isAdmin(session)) { // 관리자로 로그인한경우
            CalendarVO calendarVO = calendarProc.read(calendarno);
            model.addAttribute("calendarVO", calendarVO);
            return "calendar/update";
        } else {
            return "redirect:/bloguser/login_cookie_need?url=/calendar/update?calendarno=" + calendarno;
        }
    }

    /**
     * 수정 처리
     * http://localhost:9092/calendar/update?calendarno=1
     */
    @PostMapping("/update")
    public String update(HttpSession session, Model model,
                         @ModelAttribute("calendarVO") CalendarVO calendarVO,
                         RedirectAttributes ra) {
        // 관리자 로그인 확인
        if (userProc.isAdmin(session)) {
          calendarProc.update(calendarVO);
          return "redirect:/calendar/read/" + calendarVO.getCalendarno();
        } else { // 정상적인 로그인이 아닌 경우 로그인 유도
            return "redirect:/bloguser/login_cookie_need?url=/calendar/update?calendarno=" + calendarVO.getCalendarno();
        }
    }

    /**
     * 삭제 폼
     * http://localhost:9092/calendar/delete?calendarno=1
     */
    @GetMapping(path = "/delete/{calendarno}")
    public String delete(HttpSession session, Model model,
                         @PathVariable("calendarno") int calendarno,
                         RedirectAttributes ra) {

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        if (userProc.isAdmin(session)) { // 관리자로 로그인한경우
            CalendarVO calendarVO = calendarProc.read(calendarno);
            model.addAttribute("calendarVO", calendarVO);

            return "calendar/delete"; // /templates/calendar/delete.html
        } else {
            return "redirect:/bloguser/login_cookie_need?url=/calendar/delete?calendarno=" + calendarno;
        }
    }

    /**
     * 삭제 처리
     * http://localhost:9092/calendar/delete?calendarno=1
     */
    @PostMapping("/delete")
    public String delete_proc(HttpSession session, Model model,
                              @RequestParam(name="calendarno", defaultValue = "0") int calendarno,
                              RedirectAttributes ra) {
        if (userProc.isAdmin(session)) { // 관리자 로그인 확인
            calendarProc.delete(calendarno);
            return "redirect:/calendar/list_all";
        } else { // 정상적인 로그인이 아닌 경우 로그인 유도
            return "redirect:/bloguser/login_cookie_need?url=/calendar/delete?calendarno=" + calendarno;
        }
    }

    /**
     * 특정 날짜의 목록
     * 현재 월: http://localhost:9092/calendar/list_calendar
     * 이전 월: http://localhost:9092/calendar/list_calendar?year=2024&month=12
     * 다음 월: http://localhost:9092/calendar/list_calendar?year=2024&month=1
     */
    @GetMapping(value = "/list_calendar")
    public String list_calendar(Model model,
                                @RequestParam(name="year", defaultValue = "0") int year,
                                @RequestParam(name="month", defaultValue = "0") int month) {
        if (year == 0) {
            // 현재 날짜를 가져옴
            LocalDate today = LocalDate.now();

            // 년도와 월 추출
            year = today.getYear();
            month = today.getMonthValue();
        }

        String month_str = String.format("%02d", month); // 두 자리 형식으로
        String date = year + "-" + month;

        ArrayList<CategoryVOMenu> menu = categoryProc.menu();
        model.addAttribute("menu", menu);

        model.addAttribute("year", year);
        model.addAttribute("month", month-1);  // javascript는 1월이 0임.

        return "calendar/list_calendar"; // /templates/calendar/list_calendar.html
    }

    /**
     * 특정 날짜의 목록
     * http://localhost:9092/calendar/list_calendar_day?labeldate=2025-01-03
     */
    @GetMapping(value = "/list_calendar_day")
    @ResponseBody
    public String list_calendar_day(Model model,
                                    @RequestParam(name="labeldate", defaultValue = "") String labeldate) {

        ArrayList<CalendarVO> list = calendarProc.list_calendar_day(labeldate);
        model.addAttribute("list", list);

        JSONArray schedule_list = new JSONArray();

        for (CalendarVO calendarVO: list) {
            JSONObject schedual = new JSONObject();
            schedual.put("calendarno", calendarVO.getCalendarno());
            schedual.put("labeldate", calendarVO.getLabeldate());
            schedual.put("label", calendarVO.getLabel());
            schedual.put("seqno", calendarVO.getSeqno());

            schedule_list.put(schedual);
        }

        return schedule_list.toString();
    }
}

