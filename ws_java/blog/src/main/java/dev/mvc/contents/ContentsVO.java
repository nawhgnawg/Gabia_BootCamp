package dev.mvc.contents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter @ToString
public class ContentsVO {

    /** 컨텐츠 번호 */
    private int contentsno;
    /** 관리자 권한의 회원 번호 */
    private int userno;
    /** 카테고리 번호 */
    private int categoryno;
    /** 제목 */
    private String title = "";
    /** 내용 */
    private String content = "";
    /** 추천수 */
    private int recom;
    /** 조회수 */
    private int cnt = 0;
    /** 댓글수 */
    private int replycnt = 0;
    /** 패스워드 */
    private String passwd = "";
    /** 검색어 */
    private String word = "";
    /** 등록 날짜 */
    private String rdate = "";
    /** 지도 */
    private String map = "";
    /** Youtube */
    private String youtube = "";

    /** mp4 */
    private String mp4 = "";

    // 파일 업로드 관련
    // -----------------------------------------------------------------------------------
    /**
     이미지 파일
     <input type='file' class="form-control" name='file1MF' id='file1MF'
     value='' placeholder="파일 선택">
     */
    private MultipartFile file1MF = null;
    /** 메인 이미지 크기 단위, 파일 크기 */
    private String size1_label = "";
    /** 메인 이미지 */
    private String file1 = "";
    /** 실제 저장된 메인 이미지 */
    private String file1saved = "";
    /** 메인 이미지 preview */
    private String thumb1 = "";
    /** 메인 이미지 크기 */
    private long size1 = 0;

    // 쇼핑몰 상품 관련
    // -----------------------------------------------------------------------------------
    /** 정가 */
    private int price = 0;
    /** 할인률 */
    private int dc = 0;
    /** 판매가 */
    private int saleprice = 0;
    /** 포인트 */
    private int point = 0;
    /** 재고 수량 */
    private int salecnt = 0;
}
