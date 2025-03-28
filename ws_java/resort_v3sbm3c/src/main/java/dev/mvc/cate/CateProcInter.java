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

    /** 우선 순위 높임 */
    public int update_seqno_forward(int cateno);

    /** 우선 순위 낮춤 */
    public int update_seqno_backward(int cateno);

    /** 카테고리 공개 설정 */
    public int update_visible_y(int cateno);

    /** 카테고리 비공개 설정 */
    public int update_visible_n(int cateno);

    /** 공개된 대분류만 출력 */
    public ArrayList<CateVO> list_all_grp_y();

    /** 특정 그룹의 중분류 출력 */
    public ArrayList<CateVO> list_all_name_y(String grp);

    /** 화면 상단 메뉴 */
    public ArrayList<CateVOMenu> menu();

    /** 카테고리 그룹 목록 */
    public ArrayList<String> grpset();

}


