package dev.mvc.contents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ContentsDAOInter {
    /**
     * 등록, 추상 메소드
     */
    public int create(ContentsVO contentsVO);

    /**
     * 모든 카테고리의 등록된 글목록
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
