package dev.mvc.category;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CategoryVO {

    /** 카테고리 번호, Sequence에서 자동 생성 */
    private Integer categoryNo = 0;

    /** 카테고리 그룹명 */
    @NotEmpty(message="카테고리 그룹명은 필수 항목입니다.")
    @Size(min=2, max=10, message="카테고리 그룹명은 최소 2자에서 최대 10자입니다.")
    private String categoryGrp;

    /** 카테고리 이름 */
    @NotEmpty(message="카테고리 입력은 필수 항목입니다.")
    @Size(min=2, max=10, message="카테고리 이름은 최소 2자에서 최대 10자입니다.")
    private String categoryName;

    /** 출력 순서 */
    @NotNull(message="출력 순서는 필수 입력 항목입니다.")
    @Min(value=1)
    @Max(value=10000)
    private Integer sortNo = 1;

    /** 출력 모드 */
    @NotEmpty(message="출력 모드는 필수 항목입니다.")
    @Pattern(regexp="^[YN]$", message="Y 또는 N만 입력 가능합니다.")  // Y ^ N 이 아니면 message 출력
    private String visible = "N";

    /** 등록일, sysdate 자동 생성 */
    private String rdate = "";

}
