package dev.mvc.ajax;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/ajax")
@Controller
public class JSONContFetch {

    // http://localhost:9091/ajax/fetch
    @GetMapping("/fetch")
    public String fetch(Model model) {

        return "ajax/fetch";   // templates/ajax/fetch.html
    }

    // http://localhost:9091/ajax/fetch2
    @GetMapping("/fetch2")
    public String fetch2(Model model) {
        return "/ajax/fetch2";
    }

    // http://localhost:9091/ajax/pay
    @GetMapping("/pay")
    public String pay(Model model) {
        return "/ajax/pay";
    }

    @PostMapping("/pay")
    @ResponseBody
    public String pay_fetch(Model model, @RequestParam("id") String id, @RequestParam("passwd") String passwd) {
        try {
            Thread.sleep(2000);
        } catch (Exception e){ }

        JSONObject object = new JSONObject();
        if (id.equals("user1") && passwd.equals("1234")) {
            object.put("code", "success");
            object.put("mname", "아로미");
            object.put("id", id);
            object.put("bonbong", 2500000);
            object.put("sudang", 500000);
            object.put("total", 3000000);
        } else {
            object.put("code", "fail");
        }

        return object.toString();
    }

    // http://localhost:9091/ajax/object1
    // {"no": 1, "name": "모닝", "price": 7000000, "grade": true}
    @GetMapping("/object1")
    @ResponseBody
    public String object1() {
        System.out.println("JSONContFetch.object1");

        try {
            Thread.sleep(2000);
        } catch (Exception e) { }

        JSONObject object = new JSONObject();
        object.put("no", 1);
        object.put("name", "모닝");
        object.put("price", 7000000);
        object.put("grade", true);

        return object.toString();
    }

    // http://localhost:9091/ajax/array1
    //  ["모닝", "레이", "트렉스", "투싼"]
    @GetMapping("/array1")
    @ResponseBody
    public String array1() {

        try {
            Thread.sleep(2000);
        } catch (Exception e) { }

        JSONArray array = new JSONArray();
        array.put("모닝");
        array.put("레이");
        array.put("트렉스");
        array.put("투싼");

        return array.toString();
    }

    // JSON 객체 배열
    // http://localhost:9091/ajax/array2
/*
    [{"no":1,"price":7000000,"grade":true,"name":"모닝"},
    {"no":2,"price":13000000,"grade":true,"name":"레이"},
    {"no":3,"price":16000000,"grade":true,"name":"트렉스"}]
*/
    @GetMapping("/array2")
    @ResponseBody
    public String array2() {

        try {
            Thread.sleep(2000);
        } catch (Exception e) { }

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

    // JSON 객체 배열
    // http://localhost:9091/ajax/array3
    @GetMapping("/array3")
    @ResponseBody
    public String array3(@RequestParam(name = "part", defaultValue = "") String part) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) { }

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();

        if (part.equals("micro")) {
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

        } else if (part.equals("small")) {
            object = new JSONObject();
            object.put("no", 1);
            object.put("name", "소형차");
            object.put("price", 13000000);
            object.put("grade", true);
            array.put(object);

        } else if (part.equals("suv")) {
            object = new JSONObject();
            object.put("no", 1);
            object.put("name", "suv");
            object.put("price", 7000000);
            object.put("grade", true);
            array.put(object);

            object = new JSONObject();
            object.put("no", 2);
            object.put("name", "스포티지");
            object.put("price", 13000000);
            object.put("grade", true);
            array.put(object);
        } else {
            object = new JSONObject();
            object.put("no", 1);
            object.put("name", "캐스퍼");
            object.put("price", 13000000);
            object.put("grade", true);
            array.put(object);
        }

        return array.toString();
    }

    // http://localhost:9091/ajax/select_food_fetch
    @GetMapping("/select_food_fetch")
    public String food(Model model) {
        ArrayList<FoodVO> foods = new ArrayList<FoodVO>();

        foods.add(new  FoodVO(1, "김밥 천국"));
        foods.add(new  FoodVO(2, "종로 짬뽕"));
        foods.add(new  FoodVO(3, "남산 한정식"));
        foods.add(new  FoodVO(4, "탄도항 횟집"));
        foods.add(new  FoodVO(5, "남산 돈까스"));

        model.addAttribute("foods", foods);

        return "ajax/select_food_fetch"; // /templates/ajax/select_food_fetch.html
    }

    // http://localhost:9091/ajax/select_menu_fetch?foodno=1
    // http://localhost:9091/ajax/select_menu_fetch?foodno=2
    @GetMapping("/select_menu_fetch")
    @ResponseBody
    public String select_menu_fetch(@RequestParam(name="foodno", defaultValue="") int foodno) {
        System.out.println("-> foodno: " + foodno);

        try {
            Thread.sleep(3000); // 3s
        } catch (Exception e) { }

        // ArrayList<MenuVO> menus = new ArrayList<MenuVO>();
        JSONArray menus = new JSONArray();
        JSONObject menu = null;

        if (foodno == 1) { // 김밥 천국
            menu = new JSONObject();
            menu.put("menuno", 1);
            menu.put("name", "라면");
            menu.put("price", 4000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 2);
            menu.put("name", "김밥");
            menu.put("price", 3000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 3);
            menu.put("name", "만두");
            menu.put("price", 5000);
            menus.put(menu);

        } else if (foodno == 2) { // 종로 짬뽕
            menu = new JSONObject();
            menu.put("menuno", 4);
            menu.put("name", "짬뽕");
            menu.put("price", 8000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 5);
            menu.put("name", "짜장면");
            menu.put("price", 7000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 6);
            menu.put("name", "탕수육");
            menu.put("price", 18000);
            menus.put(menu);

        } else if (foodno == 3) { // 남산 한정식
            menu = new JSONObject();
            menu.put("menuno", 7);
            menu.put("name", "VIP 한정식");
            menu.put("price", 70000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 8);
            menu.put("name", "진달래 한정식");
            menu.put("price", 50000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 9);
            menu.put("name", "개나리 한정식");
            menu.put("price", 40000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 10);
            menu.put("name", "소갈비찜");
            menu.put("price", 60000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 11);
            menu.put("name", "간장 게장");
            menu.put("price", 50000);
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 12);
            menu.put("name", "육회");
            menu.put("price", 45000);
            menus.put(menu);

        } else {
            System.out.println("-> ERROR");
        }

        return menus.toString();
    }

    // http://localhost:9091/ajax/select_store_fetch
    @GetMapping("/select_store_fetch")
    public String store(Model model) {
        ArrayList<StoreVO> foods = new ArrayList<StoreVO>();

        foods.add(new  StoreVO(1, "김밥 천국"));
        foods.add(new  StoreVO(2, "종로 짬뽕"));
        foods.add(new  StoreVO(3, "남산 한정식"));
        foods.add(new  StoreVO(4, "탄도항 횟집"));
        foods.add(new  StoreVO(5, "남산 돈까스"));

        model.addAttribute("foods", foods);

        return "ajax/select_store_fetch"; // /templates/ajax/select_store_fetch.html
    }

    // http://localhost:9091/ajax/select_store_menu_fetch?foodno=1
    // http://localhost:9091/ajax/select_store_menu_fetch?foodno=2
    @GetMapping("/select_store_menu_fetch")
    @ResponseBody
    public String select_store_menu_fetch(@RequestParam(name="foodno", defaultValue="") int foodno) {
        System.out.println("-> foodno: " + foodno);

        try {
            Thread.sleep(3000); // 3s
        } catch (Exception e) { }

        // ArrayList<MenuVO> menus = new ArrayList<MenuVO>();
        JSONArray menus = new JSONArray();
        JSONObject menu = null;

        if (foodno == 1) { // 김밥 천국
            menu = new JSONObject();
            menu.put("menuno", 1);
            menu.put("name", "라면");
            menu.put("price", 4000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 2);
            menu.put("name", "김밥");
            menu.put("price", 3000);
            menu.put("img", "김밥.jpeg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 3);
            menu.put("name", "만두");
            menu.put("price", 5000);
            menu.put("img", "만두.jpeg");
            menus.put(menu);

        } else if (foodno == 2) { // 종로 짬뽕
            menu = new JSONObject();
            menu.put("menuno", 4);
            menu.put("name", "짬뽕");
            menu.put("price", 8000);
            menu.put("img", "짬뽕.jpeg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 5);
            menu.put("name", "짜장면");
            menu.put("price", 7000);
            menu.put("img", "짜장면.jpeg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 6);
            menu.put("name", "탕수육");
            menu.put("price", 18000);
            menu.put("img", "탕수육.jpeg");
            menus.put(menu);

        } else if (foodno == 3) { // 남산 한정식
            menu = new JSONObject();
            menu.put("menuno", 7);
            menu.put("name", "VIP 한정식");
            menu.put("price", 70000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 8);
            menu.put("name", "진달래 한정식");
            menu.put("price", 50000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 9);
            menu.put("name", "개나리 한정식");
            menu.put("price", 40000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 10);
            menu.put("name", "소갈비찜");
            menu.put("price", 60000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 11);
            menu.put("name", "간장 게장");
            menu.put("price", 50000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

            menu = new JSONObject();
            menu.put("menuno", 12);
            menu.put("name", "육회");
            menu.put("price", 45000);
            menu.put("img", "라면.jpg");
            menus.put(menu);

        } else {
            System.out.println("-> ERROR");
        }

        return menus.toString();
    }

}
