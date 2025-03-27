package dev.mvc.bloguser;

import java.util.ArrayList;

public interface UserDAOInter {

    public int create(UserVO userVO);

    public ArrayList<UserVO> list_all();

    public UserVO read(int userno);

    public int update(UserVO userVO);

    public int delete(int userno);

    public int update_grade_forward(int userno);

    public int update_grade_backward(int userno);

    public int update_visible_y(int userno);

    public int update_visible_n(int userno);

    public ArrayList<UserVO> list_all_usersex_y();

    public ArrayList<UserVO> list_all_userage_y(String usersex);

}
