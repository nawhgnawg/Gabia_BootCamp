package dev.mvc.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("dev.mvc.memo.MemoProc")
public class MemoProc implements MemoProcInter {

    @Autowired
    private MemoDAOInter memoDAO;
    @Override
    public int create(MemoVO memoVO) {
        return memoDAO.create(memoVO);
    }

    @Override
    public ArrayList<MemoVO> list_all() {
        return memoDAO.list_all();
    }

    @Override
    public ArrayList<MemoVO> read(int userno) {
        return memoDAO.read(userno);
    }

    @Override
    public int update(MemoVO memoVO) {
        return memoDAO.update(memoVO);
    }

    @Override
    public int delete(int memono) {
        return memoDAO.delete(memono);
    }

    @Override
    public ArrayList<MemoUserVO> list_all_join() {
        return memoDAO.list_all_join();
    }
}
