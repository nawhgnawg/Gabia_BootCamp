package dev.mvc.bloguser;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.util.ArrayList;

@Getter @Setter
public class UserVOMenu {

    /** 카테고리 그룹(대분류) */
    public String usersex;

    /** 카테고리 (중분류) */
    private ArrayList<UserVO> list_userage;
}
