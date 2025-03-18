package dev.mvc.cate;

// MyBatis 기준으로 추상 메소드를 만들면 Spring Boot가 자동으로 class 구현함.
public interface CateDAOInter {
    /**
     * <pre>
     * MyBatis : <insert id="create" parameterType="dev.mvc.cate.CateVO">
     * insert : INSERT SQL, 처리후 등록된 레코드 갯수를 리턴
     * id : 자바 메소드명
     * parameterType : MyBatis가 전달 받는 VO 객체 타입
     * </pre>
     * @param cateVO
     * @return 등록된 레코드 갯수
     */
    public int create(CateVO cateVO);
}
