package dev.mvc.ajax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/json")
@Controller
public class JSONCont {
    public JSONCont() {
        System.out.println("-> JSONCont created");
    }

    // http://localhost:9091/json/object1
    // {"no": 1, "name": "모닝", "price": 7000000, "grade": true}
    @GetMapping("/object1")
    @ResponseBody
    public String object1() {
        String object="";
        object += "{";
        object += "\"no\": 1, ";
        object += "\"name\": \"모닝\", ";
        object += "\"price\": 7000000, ";
        object += "\"grade\": true";
        object += "}";

        return object;
    }

    // http://localhost:9091/json/array1
    //  ["모닝", "스파크", "트렉스"]
    @GetMapping("/array1")
    @ResponseBody
    public String array1() {
        String array="";
        array = "[\"모닝\", \"스파크\", \"트렉스\"]";

        return array;
    }
}
