package dev.mvc.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReplyVO {

    /** 댓글 번호 */
    private int replyno;

    /** 관련 글 번호 */
    private int contentsno;

    /** 회원 번호 */
    private int userno;

    /** 내용 */
    private String content = "";

    /** 등록일 */
    private String rdate = "";
}
