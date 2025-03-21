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
}

