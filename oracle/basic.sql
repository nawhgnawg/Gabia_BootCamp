-- 공지사항
DROP TABLE notice;  -- 테이블 삭제
 
CREATE TABLE notice(
    noticeno NUMBER(7)   NOT NULL,  -- -9999999 ~ 9999999, 1부터 사용
    title    VARCHAR(50) NOT NULL,  -- 내용
    good     NUMBER(5)   NOT NULL,  -- 추천
    rdate    DATE            NULL,   -- 등록일
    PRIMARY KEY(noticeno)

);

-- INSERT INTO notice VALUES(1, '월요일 안내', 0);   -- X , SQL 오류: ORA-00947: 값의 수가 충분하지 않습니다
INSERT INTO notice VALUES(1, '월요일 안내', 0, sysdate);   -- MySQL: now() -> sysdate

SELECT * FROM notice;

INSERT INTO notice(noticeno, title, good, rdate) 
VALUES(2, '화요일 안내', 0, sysdate);   -- column을 명시해주는게 좋음

SELECT * FROM notice;

INSERT INTO notice(noticeno, title, good) 
VALUES(3, '수요일 안내', 0);   -- rdate : null

SELECT noticeno, title, good, rdate FROM notice;

  NOTICENO TITLE                                                    GOOD RDATE              
---------- -------------------------------------------------- ---------- -------------------
         1 월요일 안내                                                  0 2025-03-04 12:10:58
         2 화요일 안내                                                  0 2025-03-04 12:13:57
         3 수요일 안내                                                  0                    
         
-- ORDER BY          
SELECT noticeno, title, good, rdate FROM notice ORDER BY noticeno DESC;

  NOTICENO TITLE                                                    GOOD RDATE              
---------- -------------------------------------------------- ---------- -------------------
         3 수요일 안내                                                  0                    
         2 화요일 안내                                                  0 2025-03-04 12:13:57
         1 월요일 안내                                                  0 2025-03-04 12:10:58
         
-- 조회
SELECT noticeno, title, good, rdate FROM notice WHERE noticeno = 1;

  NOTICENO TITLE                                                    GOOD RDATE              
---------- -------------------------------------------------- ---------- -------------------
         1 월요일 안내                                                  0 2025-03-04 12:10:58

-- 수정
UPDATE notice SET title = '목요일 안내', good = 100, rdate = sysdate WHERE noticeno = 3;
SELECT noticeno, title, good, rdate FROM notice WHERE noticeno = 3;

-- 삭제
DELETE FROM notice WHERE noticeno = 3;
SELECT noticeno, title, good, rdate FROM notice ORDER BY noticeno DESC;

COMMIT;
