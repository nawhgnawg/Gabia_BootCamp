package dev.mvc.bloguser;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserDAOInter {
    /**
     * 중복 아이디 검사
     * @param useremail
     * @return 중복 아이디 갯수
     */
    public int checkID(String useremail);

    /**
     * 회원 가입
     * @param userVO
     * @return 추가한 레코드 갯수
     */
    public int create(UserVO userVO);

    /**
     * 회원 전체 목록
     * @return
     */
    public ArrayList<UserVO> list_all();

    /**
     * userno로 회원 정보 조회
     * @param userno
     * @return
     */
    public UserVO read(int userno);

    /**
     * id로 회원 정보 조회
     * @param useremail
     * @return
     */
    public UserVO readByEmail(String useremail);

    /**
     * 수정 처리
     * @param userVO
     * @return
     */
    public int update(UserVO userVO);

    /**
     * 회원 삭제 처리
     * @param userno
     * @return
     */
    public int delete(int userno);

    /**
     * 로그인 처리
     */
    public int login(HashMap<String, Object> map);

    /**
     * 현재 패스워드 검사
     * @param map
     * @return 0: 일치하지 않음, 1: 일치함
     */
    public int passwd_check(HashMap<String, Object> map);

    /**
     * 패스워드 변경
     * @param map
     * @return 변경된 패스워드 갯수
     */
    public int passwd_update(HashMap<String, Object> map);


}
