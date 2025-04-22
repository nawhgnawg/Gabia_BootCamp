package dev.mvc.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("dev.mvc.calendar.CalendarProc")
public class CalendarProc implements CalendarProcInter{
  @Autowired // ContentsDAOInter interface를 구현한 클래스의 객체를 만들어 자동으로 할당해라.
  private CalendarDAOInter calendarDAO;
  
  @Override
  public int create(CalendarVO calendarVO) {
      return calendarDAO.create(calendarVO);
  }

  @Override
  public ArrayList<CalendarVO> list_all() {
      return calendarDAO.list_all();
  }

  @Override
  public CalendarVO read(int calendarno) {
      return calendarDAO.read(calendarno);
  }

  @Override
  public int increaseCnt(int calendarno) {
      return calendarDAO.increaseCnt(calendarno);
  }

  @Override
  public int update(CalendarVO calendarVO) {
      return calendarDAO.update(calendarVO);
  }

  @Override
  public int delete(int calendarno) {
      return calendarDAO.delete(calendarno);
  }

  @Override
  public ArrayList<CalendarVO> list_calendar(String date) {
      return calendarDAO.list_calendar(date);
  }

  @Override
  public ArrayList<CalendarVO> list_calendar_day(String date) {
      return calendarDAO.list_calendar_day(date);
  }
  
}



