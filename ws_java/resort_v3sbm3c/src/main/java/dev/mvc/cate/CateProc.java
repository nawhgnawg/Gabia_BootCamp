package dev.mvc.cate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

// Service, Process, Manager : DAO 호출 및 알고리즘 구현
// @Component("dev.mvc.cate.CateProc")
@Service("dev.mvc.cate.CateProc")       // @Service 안에 @Component 있음.
public class CateProc implements CateProcInter {

    @Autowired
    private CateDAOInter cateDAO;

    @Override
    public int create(CateVO cateVO) {
        return cateDAO.create(cateVO);
    }

    @Override
    public ArrayList<CateVO> list_all() {
        return cateDAO.list_all();
    }

    @Override
    public CateVO read(int cateno) {
        return cateDAO.read(cateno);
    }

    @Override
    public int update(CateVO cateVO) {
        return cateDAO.update(cateVO);
    }

    @Override
    public int delete(int cateno) {
        return cateDAO.delete(cateno);
    }

    @Override
    public int update_seqno_forward(int cateno) {
        return cateDAO.update_seqno_forward(cateno);
    }

    @Override
    public int update_seqno_backward(int cateno) {
        return cateDAO.update_seqno_backward(cateno);
    }

    @Override
    public int update_visible_y(int cateno) {
        return cateDAO.update_visible_y(cateno);
    }

    @Override
    public int update_visible_n(int cateno) {
        return cateDAO.update_visible_n(cateno);
    }

    @Override
    public ArrayList<CateVO> list_all_grp_y() {
        return cateDAO.list_all_grp_y();
    }

    @Override
    public ArrayList<CateVO> list_all_name_y(String grp) {
        return cateDAO.list_all_name_y(grp);
    }

    @Override
    public ArrayList<CateVOMenu> menu() {
        ArrayList<CateVOMenu> menu = new ArrayList<>();
        ArrayList<CateVO> grps = cateDAO.list_all_grp_y();  // 대분류 목록

        for (CateVO cateVO : grps) {
            CateVOMenu cateVOMenu = new CateVOMenu();
            cateVOMenu.setGrp(cateVO.getGrp());         // 대분류 이름 저장

            ArrayList<CateVO> list_name = cateDAO.list_all_name_y(cateVO.getGrp());// 특정 대분류에 해당하는 중분류 추출
            cateVOMenu.setList_name(list_name);         // 대분류에 중분류 저장

            menu.add(cateVOMenu);
        }

        return menu;
    }


}