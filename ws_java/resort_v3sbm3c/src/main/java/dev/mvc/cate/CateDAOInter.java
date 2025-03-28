package dev.mvc.cate;

import java.util.ArrayList;

// MyBatis 기준으로 추상 메소드를 만들면 Spring Boot가 자동으로 class 구현함.
// 주로 Mapper로 표현 (CateMapper)
public interface CateDAOInter {
    /**
     * <pre>
     * MyBatis : insert id="create" parameterType="dev.mvc.cate.CateVO"
     * insert : INSERT SQL, 처리후 등록된 레코드 갯수를 리턴
     * id : 자바 메소드명
     * parameterType : MyBatis가 전달 받는 VO 객체 타입
     * </pre>
     * @param cateVO
     * @return 등록된 레코드 갯수
     */
    public int create(CateVO cateVO);

    public ArrayList<CateVO> list_all();

    public CateVO read(int cateno);

    public int update(CateVO cateVO);

    /**
     * 삭제 처리
     * delete id="delete" parameterType="int"
     */
    public int delete(int cateno);

    /**
     * 우선 순위 높임
     * update id="update_seqno_forward"
     */
    public int update_seqno_forward(int cateno);

    /**
     * 우선 순위 낮춤
     * update id="update_seqno_backward"
     */
    public int update_seqno_backward(int cateno);

    /**
     * 카테고리 공개 설정
     * update id="update_visible_y"
     */
    public int update_visible_y(int cateno);

    /**
     * 카테고리 비공개 설정
     * update id="update_visible_n"
     */
    public int update_visible_n(int cateno);

    /**
     * 공개된 대분류만 출력
     * select id="list_all_grp_y"
     */
    public ArrayList<CateVO> list_all_grp_y();

    /**
     * 특정 그룹의 중분류 출력
     * select id="list_all_categrp_y"
     */
    public ArrayList<CateVO> list_all_name_y(String grp);

    public ArrayList<String> grpset();
}
