package dev.mvc.resort_v3sbm3c;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tag")
@Controller
public class HTMLCont {

    /**
     * radio 태그 초기값 설정
     * http://localhost:9091/tag/init
     */
    @GetMapping("/init")
    public String showForm(Model model) {
        // radio
        String radio_value = "Y";
        model.addAttribute("radio_value", radio_value);

        // checkbox
        boolean isCode1 = true;
        boolean isCode2 = false;
        boolean isCode3 = true;
        model.addAttribute("isCode1", isCode1);
        model.addAttribute("isCode2", isCode2);
        model.addAttribute("isCode3", isCode3);

        String isCode4 = "서울";
        String isCode5 = "인천";
        String isCode6 = "경기";
        model.addAttribute("isCode4", isCode4);
        model.addAttribute("isCode5", isCode5);
        model.addAttribute("isCode6", isCode6);

        // select box
        String select_value = "A02";    // 인천
        model.addAttribute("select_value", select_value);

        // date
        String rdate = "2024-09-10 12:25:30".substring(0, 10);
        model.addAttribute("rdate", rdate);

        return "/tag/init";
    }

    @PostMapping("/init")
    @ResponseBody
    public String processForm(Model model,
                              @RequestParam(value = "radio_value", defaultValue = "N") String radio_value,
                              @RequestParam(value = "code1", defaultValue = "") String code1,
                              @RequestParam(value = "code2", defaultValue = "") String code2,
                              @RequestParam(value = "code3", defaultValue = "") String code3,
                              @RequestParam(value = "code4", defaultValue = "") String code4,
                              @RequestParam(value = "code5", defaultValue = "") String code5,
                              @RequestParam(value = "code6", defaultValue = "") String code6,
                              @RequestParam(value = "area", defaultValue = "") String area,
                              @RequestParam(value = "rdate", defaultValue = "") String rdate) {

        StringBuffer sb = new StringBuffer();

        sb.append("<h2>radio_value: " + radio_value + "</h2>");
        sb.append("<h2>code1: " + code1 + "</h2>");
        sb.append("<h2>code2: " + code2 + "</h2>");
        sb.append("<h2>code3: " + code3 + "</h2>");
        sb.append("<h2>code4: " + code4 + "</h2>");
        sb.append("<h2>code5: " + code5 + "</h2>");
        sb.append("<h2>code6: " + code6 + "</h2>");
        sb.append("<h2>area: " + area + "</h2>");
        sb.append("<h2>rdate: " + rdate + "</h2>");

        return sb.toString();
    }

}
