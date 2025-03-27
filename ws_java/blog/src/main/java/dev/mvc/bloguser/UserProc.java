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

    @Override
    public int update_grade_forward(int userno) {
        return userDAO.update_grade_forward(userno);
    }

    @Override
    public int update_grade_backward(int userno) {
        return userDAO.update_grade_backward(userno);
    }

    @Override
    public int update_visible_y(int userno) {
        return userDAO.update_visible_y(userno);
    }

    @Override
    public int update_visible_n(int userno) {
        return userDAO.update_visible_n(userno);
    }

    @Override
    public ArrayList<UserVO> list_all_usersex_y() {
        return userDAO.list_all_usersex_y();
    }

    @Override
    public ArrayList<UserVO> list_all_userage_y(String usersex) {
        return userDAO.list_all_userage_y(usersex);
    }

    @Override
    public ArrayList<UserVOMenu> menu() {
        ArrayList<UserVOMenu> menu = new ArrayList<>();
        ArrayList<UserVO> usersexs = userDAO.list_all_usersex_y();

        for (UserVO userVO : usersexs) {
            UserVOMenu userVOMenu = new UserVOMenu();
            userVOMenu.setUsersex(userVO.getUsersex());

            ArrayList<UserVO> list_userage = userDAO.list_all_userage_y(userVO.getUsersex());
            userVOMenu.setList_userage(list_userage);

            menu.add(userVOMenu);
        }

        return menu;
    }
}
