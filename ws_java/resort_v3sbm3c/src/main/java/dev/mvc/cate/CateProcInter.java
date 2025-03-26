package dev.mvc.cate;

import java.util.ArrayList;

public interface CateProcInter {
    /** 등록 */
    public int create(CateVO cateVO);

    /** 전체 목록 */
    public ArrayList<CateVO> list_all();

    /** 조회 */
    public CateVO read(int cateno);

    /** 수정 */
    public int update(CateVO cateVO);

    /** 삭제 */
    public int delete(int cateno);

}
