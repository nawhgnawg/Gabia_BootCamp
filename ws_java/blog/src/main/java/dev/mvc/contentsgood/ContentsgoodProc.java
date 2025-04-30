package dev.mvc.contentsgood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component("dev.mvc.contentsgood.ContentsgoodProc")
public class ContentsgoodProc implements ContentsgoodProcInter {
    @Autowired
    ContentsgoodDAOInter contentsgoodDAO;

    @Override
    public int create(ContentsgoodVO contentsgoodVO) {
        return contentsgoodDAO.create(contentsgoodVO);
    }

    @Override
    public ArrayList<ContentsgoodVO> list_all() {
        return contentsgoodDAO.list_all();
    }

    @Override
    public int delete(int contentsgoodno) {
        return contentsgoodDAO.delete(contentsgoodno);
    }

    @Override
    public int hartCnt(HashMap<String, Object> map) {
        return contentsgoodDAO.hartCnt(map);
    }

    @Override
    public int currentHartCnt(int contentsno) {
        return contentsgoodDAO.currentHartCnt(contentsno);
    }

    @Override
    public ContentsgoodVO read(int contentsgoodno) {
        return contentsgoodDAO.read(contentsgoodno);
    }

    @Override
    public ContentsgoodVO readByContentsnoUserno(HashMap<String, Object> map) {
        return contentsgoodDAO.readByContentsnoUserno(map);
    }

    @Override
    public ArrayList<ContentsContentsgoodUserVO> list_all_join() {
        return contentsgoodDAO.list_all_join();
    }

    @Override
    public int delete_contentsno(int contentsno) {
        return contentsgoodDAO.delete_contentsno(contentsno);
    }

}



