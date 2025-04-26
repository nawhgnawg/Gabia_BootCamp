package dev.mvc.memo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemoVO {

    /** 메모 번호 */
    private int memono;

    /** 제목 */
    private String title = "";

    /** 메모 내용 */
    private String contents = "";

    /** 메모 등록 날짜 */
    private String rdate = "";

    /** 회원 번호 */
    private int userno;




}
