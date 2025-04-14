package dev.mvc.bloguser;

import dev.mvc.tool.Security;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service("dev.mvc.bloguser.UserProc")
public class UserProc implements UserProcInter{

    @Autowired
    private UserDAOInter userDAO;

    @Autowired
    Security security;

    @Override
    public int create(UserVO userVO) {
        String password = userVO.getUserpassword();
        String password_encoded = security.aesEncode(password);
        userVO.setUserpassword(password_encoded);

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
        String userpassword = (String) map.get("userpassword");
        map.put("userpassword", security.aesEncode(userpassword));

        return userDAO.passwd_check(map);
    }

    @Override
    public int passwd_update(HashMap<String, Object> map) {
        String userpassword = (String) map.get("userpassword");
        map.put("userpassword", security.aesEncode(userpassword));

        return userDAO.passwd_update(map);
    }

    @Override
    public int login(HashMap<String, Object> map) {
        String userpassword = (String) map.get("userpassword");
        map.put("userpassword", security.aesEncode(userpassword));

        return userDAO.login(map);
    }

    @Override
    public boolean isMember(HttpSession session) {
        boolean sw = false; // 로그인하지 않은 것으로 초기화
        String usergrade = "";

        // System.out.println("-> grade: " + session.getAttribute("grade"));
        if (session != null) {
            String useremail = (String)session.getAttribute("useremail");
            if (session.getAttribute("usergrade") != null) {
                usergrade = (String) session.getAttribute("usergrade");
            }
            if (useremail != null && (usergrade.equals("admin") || usergrade.equals("member"))) { // 관리자 + 회원
                sw = true;  // 로그인 한 경우
            }
        }
        return sw;
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        boolean sw = false; // 로그인하지 않은 것으로 초기화
        String usergrade = "";

        if (session != null) {
            String useremail = (String) session.getAttribute("useremail");
            if (session.getAttribute("usergrade") != null) {
                usergrade = (String) session.getAttribute("usergrade");
            }

            if (useremail != null && usergrade.equals("admin")) { // 관리자
                sw = true;  // 로그인 한 경우
            }
        }

        return sw;
    }

}
