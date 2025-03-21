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


}