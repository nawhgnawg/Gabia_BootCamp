package dev.mvc.bloguser;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class UserVO {

    /** 회원 번호, Sequence에서 자동 생성 */
    private Integer userNo = 0;

    /** 회원 이름 */
    @NotEmpty(message="회원 이름은 필수 항목입니다.")
    @Size(min=2, max=10, message="회원 번호은 최소 2자에서 최대 10자입니다.")
    private String userName;

    /** 회원 이메일 */
    @NotEmpty(message="회원 이메일은 필수 항목입니다.")
    @Size(min=2, max=10, message="회원 이메일은 최소 2자에서 최대 10자입니다.")
    private String userEmail;

    /** 회원 비밀번호 */
    @NotEmpty(message="카테고리 입력은 필수 항목입니다.")
    @Size(min=2, max=10, message="회원 비밀번호는 최소 2자에서 최대 10자입니다.")
    private String userPassword;

    /** 회원 등급 */
    @NotNull
    @Min(value=1)
    @Max(value=5)
    private Integer userGrade = 1;

    /** 등록일, sysdate 자동 생성 */
    private String rdate = "";

}
