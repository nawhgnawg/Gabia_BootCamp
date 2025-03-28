package dev.mvc.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
