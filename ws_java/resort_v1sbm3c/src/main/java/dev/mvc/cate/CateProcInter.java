package dev.mvc.cate;

import java.util.ArrayList;

public interface CateProcInter {
    /**
     * 등록
     * @param cateVO
     * @return
     */
    public int create(CateVO cateVO);

    /**
     * 전체 목록
     * @return
     */
    public ArrayList<CateVO> list_all();
}
