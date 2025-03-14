package dev.boot.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MsgCont {
    public MsgCont() {
        System.out.println("-> MsgCont created");
    }

    // http://localhost:9091
    // http://localhost:9091/
    // http://localhost:9091/msg
    // http://localhost:9091/msg.seoul
    @GetMapping(value = {"/", "/msg", "/msg.seoul"})
    @ResponseBody
    public String msg() {
        return "<h2>컨트롤러 정상 작동됨</h2>";
    }

}
