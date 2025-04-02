package dev.mvc.bloguser;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

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
    public int checkID(String useremail) {
        return userDAO.checkID(useremail);
    }

    @Override
    public UserVO readByEmail(String useremail) {
        return userDAO.readByEmail(useremail);
    }

    @Override
    public int passwd_check(HashMap<String, Object> map) {
        return userDAO.passwd_check(map);
    }

    @Override
    public int passwd_update(HashMap<String, Object> map) {
        return userDAO.passwd_update(map);
    }

    @Override
    public int login(HashMap<String, Object> map) {
        return userDAO.login(map);
    }

    @Override
    public boolean isMember(HttpSession session) {
        boolean sw = false; // 로그인하지 않은 것으로 초기화
        int grade = 99;

        // System.out.println("-> grade: " + session.getAttribute("grade"));
        if (session != null) {
            String id = (String)session.getAttribute("id");
            if (session.getAttribute("grade") != null) {
                grade = (int)session.getAttribute("grade");
            }

            if (id != null && grade <= 20){ // 관리자 + 회원
                sw = true;  // 로그인 한 경우
            }
        }

        return sw;
    }

    @Override
    public boolean isMemberAdmin(HttpSession session) {
        boolean sw = false; // 로그인하지 않은 것으로 초기화
        int grade = 99;

        // System.out.println("-> grade: " + session.getAttribute("grade"));
        if (session != null) {
            String id = (String)session.getAttribute("id");
            if (session.getAttribute("grade") != null) {
                grade = (int)session.getAttribute("grade");
            }

            if (id != null && grade <= 10){ // 관리자
                sw = true;  // 로그인 한 경우
            }
        }

        return sw;
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
    public ArrayList<UserVO> list_search_user(String word) {
        return userDAO.list_search_user(word);
    }
}
