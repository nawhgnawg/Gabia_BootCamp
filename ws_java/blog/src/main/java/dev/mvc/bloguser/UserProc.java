package dev.mvc.bloguser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.bloguser.UserProc")
public class UserProc implements UserProcInter{
    @Autowired
    private UserDAOInter userDAO;

    @Override
    public int create(UserVO userVO) {
        return userDAO.create(userVO);
    }
}
