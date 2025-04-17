package dev.mvc.pilot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapCont {

    @GetMapping("/map/map")
    public String map() {
        return "/map/map";
    }

}
