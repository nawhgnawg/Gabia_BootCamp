package dev.boot.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // GET/POST/PUT/DELETE... 처리
public class MsgCont {
  public MsgCont() {
    System.out.println("-> MsgCont created.");
  }
  
  // http://localhost:9091
  // http://localhost:9091/
  // http://localhost:9091/msg
  // http://localhost:9091/msg.seoul
  @GetMapping(value= {"/", "/msg", "/msg.seoul"}) // get
  @ResponseBody // HTML 내용임.
  public String msg() {
    return "<h2>콘트롤러 정상 작동됨</h2>";
  }
}
