package dev.mvc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {

    @Autowired
    private CategoryDAOInter categoryDAO;

    @Override
    public int create(CategoryVO categoryVO) {
        return categoryDAO.create(categoryVO);
    }

    @Override
    public ArrayList<CategoryVO> list_all() {
        return categoryDAO.list_all();
    }

    @Override
    public CategoryVO read(int categoryNo) {
        return categoryDAO.read(categoryNo);
    }

    @Override
    public int update(CategoryVO categoryVO) {
        return categoryDAO.update(categoryVO);
    }

    @Override
    public int delete(int categoryNo) {
        return categoryDAO.delete(categoryNo);
    }

    @Override
    public int update_sortNo_forward(int categoryNo) {
        return categoryDAO.update_sortNo_forward(categoryNo);
    }

    @Override
    public int update_sortNo_backward(int categoryNo) {
        return categoryDAO.update_sortNo_backward(categoryNo);
    }

    @Override
    public int update_visible_y(int categoryNo) {
        return categoryDAO.update_visible_y(categoryNo);
    }

    @Override
    public int update_visible_n(int categoryNo) {
        return categoryDAO.update_visible_n(categoryNo);
    }

    @Override
    public ArrayList<CategoryVO> list_all_categoryGrp_y() {
        return categoryDAO.list_all_categoryGrp_y();
    }

    @Override
    public ArrayList<CategoryVO> list_all_categoryName_y(String categoryGrp) {
        return categoryDAO.list_all_categoryName_y(categoryGrp);
    }

    @Override
    public ArrayList<CategoryVOMenu> menu() {
        ArrayList<CategoryVOMenu> menu = new ArrayList<>();
        ArrayList<CategoryVO> categoryGrps = categoryDAO.list_all_categoryGrp_y();

        for (CategoryVO categoryVO : categoryGrps) {
            CategoryVOMenu categoryVOMenu = new CategoryVOMenu();
            categoryVOMenu.setCategoryGrp(categoryVO.getCategoryGrp());

            ArrayList<CategoryVO> list_categoryName = categoryDAO.list_all_categoryName_y(categoryVO.getCategoryGrp());
            categoryVOMenu.setList_categoryName(list_categoryName);

            menu.add(categoryVOMenu);
        }

        return menu;
    }

    @Override
    public ArrayList<String> categorygrpset() {
        return categoryDAO.categorygrpset();
    }

    @Override
    public ArrayList<CategoryVO> list_search(String word) {
        return categoryDAO.list_search(word);
    }

    @Override
    public int list_search_count(String word) {
        return categoryDAO.list_search_count(word);
    }

    @Override
    public ArrayList<CategoryVO> list_search_paging(String word, int now_page, int record_per_page) {
        int start_num = ((now_page - 1) * record_per_page) + 1;
        int end_num=(start_num + record_per_page) - 1;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("word", word);
        map.put("start_num", start_num);
        map.put("end_num", end_num);

        ArrayList<CategoryVO> list = categoryDAO.list_search_paging(map);

        return list;
    }

    @Override
    public String pagingBox(int now_page, String word, String list_file_name, int search_count, int record_per_page, int page_per_block) {
        // 전체 페이지 수: (double)1/10 -> 0.1 -> 1 페이지, (double)12/10 -> 1.2 페이지 -> 2 페이지
        int total_page = (int)(Math.ceil((double)search_count / record_per_page));
        // 전체 그룹  수: (double)1/10 -> 0.1 -> 1 그룹, (double)12/10 -> 1.2 그룹-> 2 그룹
        int total_grp = (int)(Math.ceil((double)total_page / page_per_block));
        // 현재 그룹 번호: (double)13/10 -> 1.3 -> 2 그룹
        int now_grp = (int)(Math.ceil((double)now_page / page_per_block));

        // 1 group: 1, 2, 3 ... 9, 10
        // 2 group: 11, 12 ... 19, 20
        // 3 group: 21, 22 ... 29, 30
        int start_page = ((now_grp - 1) * page_per_block) + 1; // 특정 그룹의 시작 페이지
        int end_page = (now_grp * page_per_block);               // 특정 그룹의 마지막 페이지

        StringBuffer str = new StringBuffer(); // String class 보다 문자열 추가등의 편집시 속도가 빠름

        str.append("<div id='paging'>");
//    str.append("현재 페이지: " + nowPage + " / " + totalPage + "  ");

        // 이전 10개 페이지로 이동
        // now_grp: 1 (1 ~ 10 page)
        // now_grp: 2 (11 ~ 20 page)
        // now_grp: 3 (21 ~ 30 page)
        // 현재 2그룹일 경우: (2 - 1) * 10 = 1그룹의 마지막 페이지 10
        // 현재 3그룹일 경우: (3 - 1) * 10 = 2그룹의 마지막 페이지 20
        int _now_page = (now_grp - 1) * page_per_block;
        if (now_grp >= 2){ // 현재 그룹번호가 2이상이면 페이지수가 11페이지 이상임으로 이전 그룹으로 갈수 있는 링크 생성
            str.append("<span class='span_box_1'><a href='" + list_file_name + "?&word=" + word + "&now_page=" + _now_page + "'>이전</a></span>");
        }

        // 중앙의 페이지 목록
        for(int i = start_page; i <= end_page; i++){
            if (i > total_page){ // 마지막 페이지를 넘어갔다면 페이 출력 종료
                break;
            }

            if (now_page == i){ // 목록에 출력하는 페이지가 현재페이지와 같다면 CSS 강조(차별을 둠)
                str.append("<span class='span_box_2'>" + i + "</span>"); // 현재 페이지, 강조
            }else{
                // 현재 페이지가 아닌 페이지는 이동이 가능하도록 링크를 설정
                str.append("<span class='span_box_1'><a href='" + list_file_name + "?word=" + word + "&now_page=" + i + "'>" + i + "</a></span>");
            }
        }

        // 10개 다음 페이지로 이동
        // nowGrp: 1 (1 ~ 10 page),  nowGrp: 2 (11 ~ 20 page),  nowGrp: 3 (21 ~ 30 page)
        // 현재 페이지 5일경우 -> 현재 1그룹: (1 * 10) + 1 = 2그룹의 시작페이지 11
        // 현재 페이지 15일경우 -> 현재 2그룹: (2 * 10) + 1 = 3그룹의 시작페이지 21
        // 현재 페이지 25일경우 -> 현재 3그룹: (3 * 10) + 1 = 4그룹의 시작페이지 31
        _now_page = (now_grp * page_per_block)+1; //  최대 페이지수 + 1
        if (now_grp < total_grp){
            str.append("<span class='span_box_1'><a href='" + list_file_name + "?&word=" + word + "&now_page=" + _now_page + "'>다음</a></span>");
        }
        str.append("</div>");

        return str.toString();
    }

    public int update_cnt(CategoryVO categoryVO) {
        return categoryDAO.update_cnt(categoryVO);
    }
}
