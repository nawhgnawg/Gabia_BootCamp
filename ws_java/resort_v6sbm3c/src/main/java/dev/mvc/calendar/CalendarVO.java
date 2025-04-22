package dev.mvc.calendar;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CalendarVO {

    /** 일정 번호 */
    private int calendarno;

    /** 회원 번호 */
    private int memberno;

    /** 출력할 날짜 */
    private String labeldate = "";

    /** 출력할 레이블 */
    private String label = "";

    /** 제목 */
    private String title = "";

    /** 글 내용 */
    private String content = "";

    /** 조회수 */
    private int cnt = 0;

    /** 일정 출력 순서 */
    private int seqno;

    /** 등록 날짜 */
    private String regdate = "";
}
