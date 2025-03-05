-- DML(Data Manipulation Language, 데이터 조작어) : 데이터를 등록, 목록, 조회, 수정, 삭제하는 SQL
-- INSERT, SELECT, UPDATE, DELETE

-- CRUD 패턴
DROP TABLE movie;
DROP SEQUENCE movie_seq;

CREATE TABLE movie (
    movieno NUMBER(7) NOT NULL,
    title VARCHAR(20) NOT NULL,
    score NUMBER(8) NOT NULL,
    actor VARCHAR(20) NOT NULL,
    rdate VARCHAR(20) NOT NULL,
    PRIMARY KEY(movieno)
);

CREATE SEQUENCE movie_seq
  START WITH 1      
  INCREMENT BY 1    
  MAXVALUE 9999999  
  CACHE 2           
  NOCYCLE;          
  
  

INSERT INTO movie(movieno, title, score, actor, rdate)
VALUES(movie_seq.nextval, '인셉션', 10, '디카프리오', sysdate);

INSERT INTO movie(movieno, title, score, actor, rdate)
VALUES(movie_seq.nextval, '기생충', 10, '송강호', sysdate);

INSERT INTO movie(movieno, title, score, actor, rdate)
VALUES(movie_seq.nextval, '극한직업', 10, '류승룡', sysdate);

SELECT movieno, title, score, actor, rdate FROM movie ORDER BY movieno DESC;

SELECT movieno, title, score, actor, rdate FROM movie
WHERE movieno = 3;

UPDATE movie SET title = '베테랑', score = 9, actor = '유아인', rdate = sysdate 
WHERE movieno = 1;

DELETE FROM movie WHERE movieno = 2;

