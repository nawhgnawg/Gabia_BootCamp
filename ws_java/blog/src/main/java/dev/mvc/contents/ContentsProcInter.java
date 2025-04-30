package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ContentsProcInter {

    /**
     * 등록
     */
    public int create(ContentsVO contentsVO);

    /**
     */
    public ArrayList<ContentsVO> list_all();

    /**
     * 카테고리별 등록된 글 목록
     */
    public ArrayList<ContentsVO> list_by_categoryno(int categoryno);

    /**
     * 조회
     */
    public ContentsVO read(int contentsno);

    /**
     * map 등록, 수정, 삭제
     */
    public int map(HashMap<String, Object> map);

    /**
     * youtube 등록, 수정, 삭제
     */
    public int youtube(HashMap<String, Object> map);

    /**
     * 카테고리별 검색 목록
     */
    public ArrayList<ContentsVO> list_by_categoryno_search(HashMap<String, Object> hashMap);

    /**
     * 카테고리별 검색된 레코드 갯수
     */
    public int list_by_categoryno_search_count(HashMap<String, Object> hashMap);

    /**
     * 카테고리별 검색 목록 + 페이징
     */
    public ArrayList<ContentsVO> list_by_categoryno_search_paging(HashMap<String, Object> map);

    /**
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작
     * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음]
     *
     * @param categoryno 카테고리 번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @param list_file 목록 파일명
     * @param search_count 검색 레코드수
     * @param record_per_page 페이지당 레코드 수
     * @param page_per_block 블럭당 페이지 수
     * @return 페이징 생성 문자열
     */
    public String pagingBox(int categoryno, int now_page, String word, String list_file, int search_count,
                            int record_per_page, int page_per_block);

    /**
     * 패스워드 검사
     */
    public int password_check(HashMap<String, Object> hashMap);

    /**
     * 글 정보 수정
     */
    public int update_text(ContentsVO contentsVO);

    /**
     * 파일 정보 수정
     */
    public int update_file(ContentsVO contentsVO);

    /**
     * 삭제
     */
    public int delete(int contentsno);

    /**
     * FK categoryno 값이 같은 레코드 갯수 산출
     */
    public int count_by_categoryno(int categoryno);

    /**
     * 특정 카테고리에 속한 모든 레코드 삭제
     */
    public int delete_by_categoryno(int categoryno);

    /**
     * FK userno 값이 같은 레코드 갯수 산출
     */
    public int count_by_userno(int userno);

    /**
     * 특정 카테고리에 속한 모든 레코드 삭제
     */
    public int delete_by_userno(int userno);

    /**
     * 글 수 증가
     */
    public int increaseReplycnt(int contentsno);

    /**
     * 글 수 감소
     */
    public int decreaseReplycnt(int contentsno);

    public int increaseRecom(int contentsno);

    public int decreaseRecom(int contentsno);

    public List<Integer> read_contentsno(int categoryno);
}
