package dev.mvc.pilot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YoutubeCont {

    // http://localhost:9091/youtube/youtube
    @GetMapping(value="/youtube/youtube")
    public String youtube() {
        return "/youtube/youtube"; // /templates/youtube/youtube.html
    }
}
