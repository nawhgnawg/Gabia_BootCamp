package dev.mvc.bloguser;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class UserVO {

    /** 회원 번호, Sequence에서 자동 생성 */
    private Integer userno = 0;

    /** 회원 이름 */
    private String username;

    /** 회원 이메일 */
    private String useremail;

    /** 회원 비밀번호 */
    private String userpassword;

    /** 회원 등급 */
    private Integer usergrade = 15;

    /** 우편 번호 */
    private String zipcode = "";

    /** 주소 1 */
    private String address1 = "";

    /** 주소 2 */
    private String address2 = "";

    /** 회원 등록일, sysdate 자동 생성 */
    private String rdate = "";

}
