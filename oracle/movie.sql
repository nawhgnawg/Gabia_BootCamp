-- DML(Data Manipulation Language, 데이터 조작어) : 데이터를 등록, 목록, 조회, 수정, 삭제하는 SQL
-- INSERT, SELECT, UPDATE, DELETE

DROP TABLE movie;

CREATE TABLE movie(
  movieno NUMBER(7)    NOT NULL, -- -9999999 ~ +9999999
  title   VARCHAR(50)  NOT NULL, -- 한글: 33, 영숫자: 100
  score   NUMBER(3, 1) DEFAULT 0 NOT NULL, -- 평점
  PRIMARY KEY(movieno)
);

DROP SEQUENCE movie_seq;

CREATE SEQUENCE movie_seq
  START WITH 1      -- 시작 번호
  INCREMENT BY 1    -- 증가값
  MAXVALUE 9999999  -- 최대값: 1~9999999 --> NUMBER(7) 대응
  CACHE 2           -- 2번은 메모리에서만 계산, System 테이블 update 횟수 감소 -> 속도 증가
  NOCYCLE;          -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '고요의 바다', 9.5);
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '넷플릭스 추천 고요의 바다', 8.5);
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '고요의 바다를 시청하고나서...', 9.0);
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '공유가 주연한 고요의 바다 시청률', 8.0);
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '고요의 바다', 7.5);
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '겨울 동해 바다', 10.0);
INSERT INTO movie(movieno, title, score) VALUES(movie_seq.nextval, '넷플릭스 미세 플라스틱 바다', 8.0);


SELECT movieno, title, score FROM movie ORDER BY score DESC;
   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         1 고요의 바다                                               9.5
         3 고요의 바다를 시청하고나서...                               9
         2 넷플릭스 추천 고요의 바다                                 8.5
         4 공유가 주연한 고요의 바다 시청률                            8
         5 고요의 바다                                               7.5      
        
SELECT movieno, title, score FROM movie WHERE title='고요의 바다';        
   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         1 고요의 바다                                               9.5
         5 고요의 바다                                               7.5         
         
SELECT movieno, title, score FROM movie WHERE title LIKE '고요의 바다';
   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         1 고요의 바다                                               9.5
         5 고요의 바다                                               7.5         
         
SELECT movieno, title, score FROM movie WHERE title='%고요의 바다';  
선택된 행 없음

SELECT movieno, title, score FROM movie WHERE title LIKE '%고요의 바다';
   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         1 고요의 바다                                               9.5
         2 넷플릭스 추천 고요의 바다                                   8.5
         5 고요의 바다                                               7.5

-- 띄어쓰기
SELECT movieno, title, score FROM movie WHERE title LIKE '%고요의바다';
선택된 행 없음

SELECT movieno, title, score FROM movie WHERE title LIKE '고요의 바다%';
   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         1 고요의 바다                                               9.5
         3 고요의 바다를 시청하고나서...                               9
         5 고요의 바다                                               7.5
        
SELECT movieno, title, score FROM movie WHERE title LIKE '%고요의 바다%';
   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         1 고요의 바다                                               9.5
         2 넷플릭스 추천 고요의 바다                                   8.5
         3 고요의 바다를 시청하고나서...                               9
         4 공유가 주연한 고요의 바다 시청률                             8
         5 고요의 바다                                               7.5
         
-- '고요의' 또는 '바다가' 들어간 레코드 검색
SELECT movieno, title, score FROM movie 
WHERE title LIKE '%고요의%' OR title LIKE '%바다%'
ORDER BY score DESC;

   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         6 겨울 동해 바다                                              10
         1 고요의 바다                                                9.5
         3 고요의 바다를 시청하고나서...                                  9
         2 넷플릭스 추천 고요의 바다                                    8.5
         4 공유가 주연한 고요의 바다 시청률                                8
         5 고요의 바다 

-- '넷플릭스'에서 '플라스틱' 관련 컨텐츠만 검색
SELECT movieno, title, score FROM movie ORDER BY score DESC;

SELECT movieno, title, score FROM movie 
WHERE title LIKE '%넷플릭스%' AND title LIKE '%플라스틱%'
ORDER BY score DESC;

-- SELECT 결과를 이용하여 메모리 상에서 가상의 테이블 생성 (SUB QUERY 사용)
SELECT movieno, title, score 
FROM (
    SELECT movieno, title, score
    FROM movie
    WHERE title LIKE '%넷플릭스%'
);

   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         2 넷플릭스 추천 고요의 바다                                    8.5
         7 넷플릭스 미세 플라스틱 바다                                    8

SELECT movieno, title, score 
FROM (
    SELECT movieno, title, score
    FROM movie
    WHERE title LIKE '%넷플릭스%'
)
WHERE title LIKE '%플라스틱%'
ORDER BY score DESC;

   MOVIENO TITLE                                                   SCORE
---------- -------------------------------------------------- ----------
         7 넷플릭스 미세 플라스틱 바다                                    8


