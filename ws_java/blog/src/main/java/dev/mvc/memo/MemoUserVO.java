package dev.mvc.memo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemoUserVO {

    /** 메모 번호 */
    private int memono;

    /** 제목 */
    private String title = "";

    /** 메모 내용 */
    private String content = "";

    /** 메모 등록 날짜 */
    private String rdate = "";

    /** 회원 번호 */
    private int userno;

    /** 회원 이메일 */
    private String useremail;

    /** 회원 이름 */
    private String username;




}
