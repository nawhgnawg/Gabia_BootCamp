package dev.mvc.reply;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReplyUserVO {
    /** 아이디(이메일) */
    private String useremail = "";

    /** 댓글 번호 */
    private int replyno;

    /** 관련 글 번호 */
    private int contentsno;

    /** 회원 번호 */
    private int userno;

    /** 내용 */
    private String content = "";

    /** 비밀번호 */
    private String passwd = "";

    /** 등록일 */
    private String rdate = "";
}
