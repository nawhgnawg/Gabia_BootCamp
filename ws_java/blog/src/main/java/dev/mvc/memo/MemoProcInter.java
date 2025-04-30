package dev.mvc.memo;

import java.util.ArrayList;

public interface MemoProcInter {

    /** 생성 */
    public int create(MemoVO memoVO);

    /** 전체 조회 */
    public ArrayList<MemoVO> list_all();

    /** 특정 유저 메모 조회 */
    public MemoVO read(int memono);

    /** 수정 */
    public int update(MemoVO memoVO);

    /** 삭제 */
    public int delete(int memono);

    /** 전체 조회 (BlogUser, Memo 조인) */
    public ArrayList<MemoUserVO> list_all_join();

}
