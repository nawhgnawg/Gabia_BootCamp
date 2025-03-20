package dev.mvc.bloguser;

import java.util.ArrayList;

public interface UserDAOInter {

    public int create(UserVO userVO);

    public ArrayList<UserVO> list_all();
}
