-- DDL(Data Definition Language, 데이터 정의어) : 테이블 구조의 생성, 수정, 삭제
-- CREATE, ALTER TABLE (ADD)/(DROP COLUMN), DROP TABLE

DROP TABLE itpay;  -- 테이블 삭제
 
CREATE TABLE itpay(
    payno    NUMBER(7)   NOT NULL,  -- -9999999 ~ 9999999, 1부터 사용
    sawon    VARCHAR(20) NOT NULL,  -- 사원명
    mon      CHAR(6)     NOT NULL,  -- 급여달, 201905
    rdate    DATE            NULL,  -- 수령일
    PRIMARY KEY(payno)
);

-- 컬럼 추가
DESC itpay;
이름    널?       유형           
----- -------- ------------ 
PAYNO NOT NULL NUMBER(7)    
SAWON NOT NULL VARCHAR2(20) 
MON   NOT NULL CHAR(6)      
RDATE          DATE

INSERT INTO itpay(payno, sawon, mon, rdate)
VALUES(1, '왕눈이', '202408', sysdate);

SELECT * FROM itpay;

ALTER TABLE itpay ADD (test1 VARCHAR(10));

DESC itpay;

이름    널?       유형           
----- -------- ------------ 
PAYNO NOT NULL NUMBER(7)    
SAWON NOT NULL VARCHAR2(20) 
MON   NOT NULL CHAR(6)      
RDATE          DATE         
TEST1          VARCHAR2(10) 

SELECT * FROM itpay;

     PAYNO SAWON                MON    RDATE               TEST1     
---------- -------------------- ------ ------------------- ----------
         1 왕눈이               202408 2024-08-27 03:09:14  null           

-- 데이터가 존재하는 경우는 사용안됨, 레코드가 없어야 추가 가능.
ALTER TABLE itpay ADD (test2 VARCHAR(10) NOT NULL);

-- 컬럼 삭제
ALTER TABLE itpay DROP COLUMN test1;

DESC itpay;
이름    널?       유형           
----- -------- ------------ 
PAYNO NOT NULL NUMBER(7)    
SAWON NOT NULL VARCHAR2(20) 
MON   NOT NULL CHAR(6)      
RDATE          DATE

rollback;   -- INSERT, UPDATE, DELETE 취소

SELECT * FROM itpay;

     PAYNO SAWON                MON    RDATE              
---------- -------------------- ------ -------------------
         1 왕눈이               202408 2024-08-27 03:09:14

-- 테이블 구조를 변경하면 자동 commit 실행됨 ★.

-- NOT NULL 방법이 있음, 기존의 레코드들의 값이 DEFAULT 값으로 설정됨.
ALTER TABLE itpay ADD (test2 VARCHAR(10) DEFAULT '-' NOT NULL);

SELECT * FROM itpay;

     PAYNO SAWON                MON    RDATE               TEST2     
---------- -------------------- ------ ------------------- ----------
         1 왕눈이               202408 2024-08-27 03:09:14 -         
         
DESC itpay;
이름    널?       유형           
----- -------- ------------ 
PAYNO NOT NULL NUMBER(7)    
SAWON NOT NULL VARCHAR2(20) 
MON   NOT NULL CHAR(6)      
RDATE          DATE         
TEST2 NOT NULL VARCHAR2(10) 

-- MODIFY : 타입 속성 변경
ALTER TABLE itpay MODIFY(test2 VARCHAR(30) null);
DESC itpay;
이름    널?       유형           
----- -------- ------------ 
PAYNO NOT NULL NUMBER(7)    
SAWON NOT NULL VARCHAR2(20) 
MON   NOT NULL CHAR(6)      
RDATE          DATE         
TEST2          VARCHAR2(30) 

-- 컬럼명의 변경
ALTER TABLE itpay RENAME COLUMN test2 to etc;
DESC itpay;
이름    널?       유형           
----- -------- ------------ 
PAYNO NOT NULL NUMBER(7)    
SAWON NOT NULL VARCHAR2(20) 
MON   NOT NULL CHAR(6)      
RDATE          DATE         
ETC            VARCHAR2(30)

-- 컬럼 삭제
ALTER TABLE itpay DROP COLUMN etc;

-- 테이블 구조 변경시 자동 Commit이 발생함.

DROP TABLE itpay;
