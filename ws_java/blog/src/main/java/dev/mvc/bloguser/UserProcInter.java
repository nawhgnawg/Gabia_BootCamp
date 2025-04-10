package dev.mvc.bloguser;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserProcInter {

    /**
     * 중복 아이디 검사
     * @param useremail
     * @return 중복 아이디 갯수
     */
    public int checkID(String useremail);

    /** id로 회원 정보 조회 */
    public UserVO readByEmail(String useremail);

    /** 현재 패스워드 검사*/
    public int passwd_check(HashMap<String, Object> map);

    /** 패스워드 변경 */
    public int passwd_update(HashMap<String, Object> map);

    /** 로그인 처리 */
    public int login(HashMap<String, Object> map);


    /** 로그인된 회원 계정인지 검사합니다. */
    public boolean isMember(HttpSession session);

    /** 로그인된 회원 관리자 계정인지 검사합니다. */
    public boolean isMemberAdmin(HttpSession session);

    /** 등록 */
    public int create(UserVO userVO);
    /** 전체 목록 */
    public ArrayList<UserVO> list_all();
    /** 조회 */
    public UserVO read(int userno);
    /** 수정 */
    public int update(UserVO userVO);
    /** 삭제 */
    public int delete(int userno);
    /** 등급 높임 */
    public int update_grade_forward(int userno);
    /** 등급 낮춤 */
    public int update_grade_backward(int userno);

    public ArrayList<UserVO> list_search_user(String word);
}

