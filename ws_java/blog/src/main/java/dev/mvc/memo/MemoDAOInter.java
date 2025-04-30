package dev.mvc.memo;

import dev.mvc.contents.ContentsVO;

import java.util.ArrayList;

public interface MemoDAOInter {
    /** 생성 */
    public int create(MemoVO memoVO);

    /** 전체 조회 */
    public ArrayList<MemoVO> list_all();

    /** 메모 단일 조회 */
    public MemoVO read(int memono);

    /** 수정 */
    public int update(MemoVO memoVO);

    /** 삭제 */
    public int delete(int memono);

    /** 전체 조회 (BlogUser, Memo 조인) */
    public ArrayList<MemoUserVO> list_all_join();




}
