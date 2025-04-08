package dev.mvc.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/json")
@Controller
public class JSONCont {
    public JSONCont() {
        System.out.println("-> JSONCont created");
    }
}
