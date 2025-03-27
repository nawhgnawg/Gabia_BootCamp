package dev.mvc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("dev.mvc.category.CategoryProc")
public class CategoryProc implements CategoryProcInter {

    @Autowired
    private CategoryProcInter categoryProc;

    @Override
    public int create(CategoryVO categoryVO) {
        return categoryProc.create(categoryVO);
    }

    @Override
    public ArrayList<CategoryVO> list_all() {
        return categoryProc.list_all();
    }

    @Override
    public CategoryVO read(int categoryNo) {
        return categoryProc.read(categoryNo);
    }

    @Override
    public int update(CategoryVO categoryVO) {
        return categoryProc.update(categoryVO);
    }

    @Override
    public int delete(int categoryNo) {
        return categoryProc.delete(categoryNo);
    }

    @Override
    public int update_sortNo_forward(int categoryNo) {
        return categoryProc.update_sortNo_forward(categoryNo);
    }

    @Override
    public int update_sortNo_backward(int categoryNo) {
        return categoryProc.update_sortNo_backward(categoryNo);
    }

    @Override
    public int update_visible_y(int categoryNo) {
        return categoryProc.update_visible_y(categoryNo);
    }

    @Override
    public int update_visible_n(int categoryNo) {
        return categoryProc.update_visible_n(categoryNo);
    }

    @Override
    public ArrayList<CategoryVO> list_all_categoryGrp_y() {
        return categoryProc.list_all_categoryGrp_y();
    }

    @Override
    public ArrayList<CategoryVO> list_all_categoryName_y(String categoryGrp) {
        return categoryProc.list_all_categoryName_y(categoryGrp);
    }

    @Override
    public ArrayList<CategoryVOMenu> menu() {
        ArrayList<CategoryVOMenu> menu = new ArrayList<>();
        ArrayList<CategoryVO> categoryGrps = categoryProc.list_all_categoryGrp_y();

        for (CategoryVO categoryVO : categoryGrps) {
            CategoryVOMenu categoryVOMenu = new CategoryVOMenu();
            categoryVOMenu.setCategoryGrp(categoryVO.getCategoryGrp());

            ArrayList<CategoryVO> list_categoryName = categoryProc.list_all_categoryName_y(categoryVO.getCategoryGrp());
            categoryVOMenu.setList_categoryName(list_categoryName);

            menu.add(categoryVOMenu);
        }

        return menu;
    }
}
