package dev.mvc.ajax;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/jsonlib")
@Controller
public class JSONContGradle {
    public JSONContGradle() {
        System.out.println("-> JSONContGradle created.");
    }

    // http://localhost:9091/jsonlib/object1
    // {"no": 1, "name": "모닝", "price": 7000000, "grade": true}
    @GetMapping("/object1")
    @ResponseBody
    public String object1() {
        JSONObject object = new JSONObject();
        object.put("no", 1);
        object.put("name", "모닝");
        object.put("price", 7000000);
        object.put("grade", true);

        return object.toString();
    }

    // http://localhost:9091/jsonlib/array1
    //  ["모닝", "레이", "트렉스"]
    @GetMapping("/array1")
    @ResponseBody
    public String array1() {
        JSONArray array = new JSONArray();
        array.put("모닝");
        array.put("레이");
        array.put("트렉스");

        return array.toString();
    }

    // JSON 객체 배열
    // http://localhost:9091/jsonlib/array2
    @GetMapping(value="/array2")
    @ResponseBody
    public String array2() {
    /*
    [
    {"no":1,"price":7000000,"grade":true,"name":"모닝"},
    {"no":2,"price":13000000,"grade":true,"name":"레이"},
    {"no":3,"price":16000000,"grade":true,"name":"트렉스"}
    ]
    */
        JSONArray array = new JSONArray();

        JSONObject object = new JSONObject();
        object.put("no", 1);
        object.put("name", "모닝");
        object.put("price", 7000000);
        object.put("grade", true);

        array.put(object);

        object = new JSONObject();
        object.put("no", 2);
        object.put("name", "레이");
        object.put("price", 13000000);
        object.put("grade", true);

        array.put(object);

        object = new JSONObject();
        object.put("no", 3);
        object.put("name", "트렉스");
        object.put("price", 16000000);
        object.put("grade", true);

        array.put(object);

        return array.toString();
    }



}
