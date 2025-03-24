package dev.mvc.bloguser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("dev.mvc.bloguser.UserProc")
public class UserProc implements UserProcInter{
    @Autowired
    private UserDAOInter userDAO;

    @Override
    public int create(UserVO userVO) {
        return userDAO.create(userVO);
    }

    @Override
    public ArrayList<UserVO> list_all() {
        return userDAO.list_all();
    }

    @Override
    public UserVO read(int userno) {
        return userDAO.read(userno);
    }

    @Override
    public int update(UserVO userVO) {
        return userDAO.update(userVO);
    }

    @Override
    public int delete(int userno) {
        return userDAO.delete(userno);
    }
}
