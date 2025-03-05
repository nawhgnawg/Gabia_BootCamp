DROP TABLE memo;

CREATE TABLE memo(
  memono NUMBER(7)    NOT NULL, -- -9999999 ~ +9999999
  title  VARCHAR(100) NOT NULL, -- 한글: 33, 영숫자: 100 
  PRIMARY KEY(memono)
);

DROP SEQUENCE memo_seq;

CREATE SEQUENCE memo_seq
  START WITH 1      -- 시작 번호
  INCREMENT BY 1    -- 증가값
  MAXVALUE 9999999  -- 최대값: 1~9999999 --> NUMBER(7) 대응
  CACHE 2           -- 2번은 메모리에서만 계산, System 테이블 update 횟수 감소 -> 속도 증가
  NOCYCLE;          -- 다시 1부터 생성되는 것을 방지
  
-- SELECT sysdate; -- X, FROM 필수 선언 필요

SELECT * FROM dual; -- FROM 절 지원용 테이블
D
-
X

SELECT sysdate FROM dual;

SYSDATE            
-------------------
2024-08-27 04:08:15

SELECT memo_seq.nextval FROM dual;  -- 다음 일련번호 생성
   NEXTVAL
----------
         1
         
SELECT memo_seq.currval FROM dual;  -- 현재 생성된 일련번호
   CURRVAL
----------
         1

INSERT INTO memo(memono, title) VALUES(memo_seq.nextval, 'GPT 3.5');
SELECT * FROM memo;
    MEMONO TITLE                         
---------- ------------------------------
         2 GPT 3.5                       

INSERT INTO memo(memono, title) VALUES(memo_seq.nextval, 'GPT 4');
SELECT * FROM memo;
    MEMONO TITLE                         
---------- ------------------------------
         2 GPT 3.5                       
         3 GPT 4    

INSERT INTO memo(memono, title) VALUES(memo_seq.nextval, 'GPT 4o');
SELECT * FROM memo;
    MEMONO TITLE                         
---------- ------------------------------
         2 GPT 3.5                       
         3 GPT 4                         
         4 GPT 4o
         
DELETE FROM memo WHERE memono=4;

INSERT INTO memo(memono, title) VALUES(memo_seq.nextval, 'GPT 4o mini');
SELECT * FROM memo;
    MEMONO TITLE                         
---------- ------------------------------
         2 GPT 3.5                       
         3 GPT 4                         
         5 GPT 4o        
         

-- Oracle 12C+ IDENTITY 
CREATE TABLE memo2(
  memono NUMBER(7)    GENERATED AS IDENTITY, -- NOT NULL이 자동 적용
  title  VARCHAR(100) NOT NULL, -- 한글: 33, 영숫자: 100 
  PRIMARY KEY(memono)
);

INSERT INTO memo2(title) VALUES('o1');
SELECT * FROM memo2;



CREATE TABLE memo3(
        memono NUMBER(7)    GENERATED AS IDENTITY(
        START WITH 1      -- 시작 번호
        INCREMENT BY 1    -- 증가값
        MAXVALUE 9999999  -- 최대값: 1~9999999 --> NUMBER(7) 대응
        CACHE 2           -- 2번은 메모리에서만 계산, System 테이블 update 횟수 감소 -> 속도 증가
        NOCYCLE   
    ),
    title  VARCHAR(100) NOT NULL, -- 한글: 33, 영숫자: 100 
    PRIMARY KEY(memono)
);

INSERT INTO memo3(title) VALUES('o3-mini');
SELECT * FROM memo3;

