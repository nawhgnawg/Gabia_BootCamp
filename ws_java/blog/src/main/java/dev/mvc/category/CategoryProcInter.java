package dev.mvc.category;

import java.util.ArrayList;

public interface CategoryProcInter {

    public int create(CategoryVO categoryVO);

    public ArrayList<CategoryVO> list_all();

    public CategoryVO read(int categoryNo);

    public int update(CategoryVO categoryVO);

    public int delete(int categoryNo);

    public int update_sortNo_forward(int categoryNo);

    public int update_sortNo_backward(int categoryNo);

    public int update_visible_y(int categoryNo);

    public int update_visible_n(int categoryNo);

    public ArrayList<CategoryVO> list_all_categoryGrp_y();

    public ArrayList<CategoryVO> list_all_categoryName_y(String categoryGrp);

    public ArrayList<CategoryVOMenu> menu();

    public ArrayList<String> categorygrpset();

    public ArrayList<CategoryVO> list_search(String word);

    public int list_search_count(String word);

    public ArrayList<CategoryVO> list_search_paging(String word, int now_page, int record_per_page);

    String pagingBox(int now_page, String word, String list_file_name, int search_count, int record_per_page, int page_per_block);
}
