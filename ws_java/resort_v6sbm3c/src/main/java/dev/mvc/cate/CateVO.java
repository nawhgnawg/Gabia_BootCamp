package dev.mvc.cate;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE cate(
//    CATENO                            NUMBER(10)     NOT NULL     PRIMARY KEY,
//    GRP                               VARCHAR2(30)  NOT NULL,
//    NAME                              VARCHAR2(30)  NOT NULL,
//    CNT                               NUMBER(7)     DEFAULT 0     NOT NULL,
//    SEQNO                             NUMBER(5)     DEFAULT 1     NOT NULL,
//    VISIBLE                           CHAR(1)      DEFAULT 'N'    NOT NULL,
//    RDATE                             DATE          NOT NULL
//);
@Setter @Getter @ToString
public class CateVO {
    /** 카테고리 번호, Sequence에서 자동 생성 */
    private Integer cateno = 0;

    /** 그룹명 */
    @NotEmpty(message="그룹명은 필수 항목입니다.")
    @Size(min=2, max=10, message="그룹명은 최소 2자에서 최대 10자입니다.")
    private String grp;

    /** 카테고리 이름 */
    @NotEmpty(message="카테고리 입력은 필수 항목입니다.")
    @Size(min=2, max=10, message="카테고리 이름은 최소 2자에서 최대 10자입니다.")
    private String name;

    /** 관련 자료수 */
    @NotNull(message="관련 자료수는 필수 입력 항목입니다.")
    @Min(value=0)
    @Max(value=1000000)
    private Integer cnt = 0;

    /** 출력 순서 */
    @NotNull(message="출력 순서는 필수 입력 항목입니다.")
    @Min(value=1)
    @Max(value=10000)
    private Integer seqno = 1;

    /** 출력 모드 */
    @NotEmpty(message="출력 모드는 필수 항목입니다.")
    @Pattern(regexp="^[YN]$", message="Y 또는 N만 입력 가능합니다.")  // Y ^ N 이 아니면 message 출력
    private String visible = "N";

    /** 등록일, sysdate 자동 생성 */
    private String rdate = "";

//  @Override
//  public String toString() {
//    return "CateVO [cateno=" + cateno + ", name=" + name + ", cnt=" + cnt + ", seqno=" + seqno + ", visible=" + visible
//        + ", rdate=" + rdate + "]";
//  }

    // CateVO(cateno=1, name=캠핑, cnt=1, seqno=1, visible=N, rdate=2024-09-03 12:15:57)

}