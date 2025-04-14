package dev.mvc.member;

import dev.mvc.tool.Security;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component("dev.mvc.member.MemberProc")
public class MemberProc implements MemberProcInter {

    @Autowired
    private MemberDAOInter memberDAO;

    @Autowired
    Security security;

    @Override
    public int checkID(String id) {
        return memberDAO.checkID(id);
    }

    @Override
    public int create(MemberVO memberVO) {
        String passwd = memberVO.getPasswd();
        String passwd_encoded = security.aesEncode(passwd);
        memberVO.setPasswd(passwd_encoded);

        return memberDAO.create(memberVO);
    }

    @Override
    public ArrayList<MemberVO> list() {
        return memberDAO.list();
    }

    @Override
    public MemberVO read(int memberno) {
        return memberDAO.read(memberno);
    }

    @Override
    public MemberVO readById(String id) {
        return memberDAO.readById(id);
    }

    @Override
    public boolean isMember(HttpSession session){
        boolean sw = false; // 로그인하지 않은 것으로 초기화
        String grade = "";

        if (session != null) {
            String id = (String) session.getAttribute("id");
            if (session.getAttribute("grade") != null) {
                grade = (String) session.getAttribute("grade");
            }
            if (id != null && (grade.equals("admin") || grade.equals("member"))) { // 관리자 + 회원
                sw = true;  // 로그인 한 경우
            }
        }
        return sw;
    }

    @Override
    public boolean isAdmin(HttpSession session){
        boolean sw = false; // 로그인하지 않은 것으로 초기화
        String grade = "";
        if (session != null) {
            String id = (String) session.getAttribute("id");
            if (session.getAttribute("grade") != null) {
                grade = (String) session.getAttribute("grade");
            }
            if (id != null && grade.equals("admin")) {
                sw = true;
            }
        }
        return sw;
    }

    @Override
    public int update(MemberVO memberVO) {
        return memberDAO.update(memberVO);
    }

    @Override
    public int delete(int memberno) {
        return memberDAO.delete(memberno);
    }

    @Override
    public int passwd_check(HashMap<String, Object> map) {
        String passwd = (String) map.get("passwd");
        map.put("passwd", security.aesEncode(passwd));

        return memberDAO.passwd_check(map);
    }

    @Override
    public int passwd_update(HashMap<String, Object> map) {
        String passwd = (String) map.get("passwd");
        map.put("passwd", security.aesEncode(passwd));

        return memberDAO.passwd_update(map);
    }

    @Override
    public int login(HashMap<String, Object> map) {
        String passwd = (String) map.get("passwd");
        map.put("passwd", security.aesEncode(passwd));

        return memberDAO.login(map);
    }

}
