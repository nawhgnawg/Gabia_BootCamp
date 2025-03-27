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
    @NotEmpty(message="이름은 필수 항목입니다.")
    @Size(min=2, max=10, message="이름은 최소 2자에서 최대 10자입니다.")
    private String username;

    /** 회원 이메일 */
    @NotEmpty(message="이메일은 필수 항목입니다.")
    private String useremail;

    /** 회원 비밀번호 */
    @NotEmpty(message="비밀번호 입력은 필수 항목입니다.")
    @Size(min=2, max=10, message="비밀번호는 최소 2자에서 최대 10자입니다.")
    private String userpassword;

    /** 회원 등급 */
    @NotNull
    @Min(value=1)
    @Max(value=10)
    private Integer usergrade = 1;

    /**
     * 회원 성별
     */
    private String usersex;

    /**
     * 회원 나이
     */
    private Integer userage;

    /**
     * 회원 숨김
     */
    @NotEmpty(message = "출력 모드는 필수 항목입니다.")
    @Pattern(regexp = "^[YN]$", message = "Y 또는 N만 입력 가능합니다.")
    private String visible;

    /** 회원 등록일, sysdate 자동 생성 */
    private String rdate = "";

}
