package dev.mvc.bloguser;

import java.util.ArrayList;

public interface UserProcInter {
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
    /** 회원 공개 */
    public int update_visible_y(int userno);
    /** 회원 숨김 */
    public int update_visible_n(int userno);

    public ArrayList<UserVO> list_all_usersex_y();

    public ArrayList<UserVO> list_all_userage_y(String usersex);

    public ArrayList<UserVOMenu> menu();
}

