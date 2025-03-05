DROP TABLE pay2;

CREATE TABLE pay2(
    payno   NUMBER(7)     NOT NULL,  -- 1 ~ 9999999
    part    VARCHAR(20)   NOT NULL,  -- 부서명
    sawon   VARCHAR(15)   NOT NULL,  -- 사원명
    bonbong NUMBER(8)     DEFAULT 0, -- 본봉  
    tax     NUMBER(10, 2) DEFAULT 0, -- 세금, 전체 자리, +-99999999.99
    bonus   NUMBER(7)     DEFAULT 0, -- 보너스
    receive NUMBER(8)     DEFAULT 0, -- 실수령액
    PRIMARY KEY(payno)
);

DROP SEQUENCE pay2_seq;

CREATE SEQUENCE pay2_seq
  START WITH 1           -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 99999999 -- 최대값: 99999999 --> NUMBER(8) 대응
  CACHE 2                  -- 2번은 메모리에서만 계산
  NOCYCLE;                -- 다시 1부터 생성되는 것을 방지

INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '개발1팀', '이정재', 2500000, 12345.67, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '분석팀', '정호연', 3500000, 300000, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '개발1팀', '이정재', 4070000, 0, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '개발1팀', '가길동', 7000000, 1200000, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '분석팀', '나길순', 5000000, 800000, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '개발2팀', '이성재', 2500000, 0, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '개발2팀', '이성재', 6000000, 0, 0);
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus) VALUES(pay2_seq.nextval, '개발2팀', '이상우', 7500000, 0, 0);

COMMIT;

SELECT payno, part, sawon, bonbong, tax, bonus
FROM pay2;

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         1 개발1팀              이정재             2500000   12345.67          0
         2 분석팀               정호연             3500000     300000          0
         3 개발1팀              이정재             4070000          0          0
         4 개발1팀              가길동             7000000    1200000          0
         5 분석팀               나길순             5000000     800000          0
         6 개발2팀              이성재             2500000          0          0
         7 개발2팀              이성재             6000000          0          0
         8 개발2팀              이상우             7500000          0          0
         
-- 컬럼 추가 : receive = bonbong + bonus - tax, 100원 단위 출력 
SELECT payno, part, sawon, TO_CHAR(bonbong, 'FM9,999,999') AS bonbong,
        TO_CHAR(tax, 'FM9,999,999') AS tax,
        TO_CHAR(bonus, 'FM9,999,999') AS bonus,
        TO_CHAR(ROUND(bonbong + bonus - tax, -2), 'FM9,999,999') AS receive
FROM pay2
ORDER BY receive DESC;

     PAYNO PART                 SAWON           BONBONG    TAX        BONUS      RECEIVE   
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         8 개발2팀              이상우          7,500,000  0          0          7,500,000 
         7 개발2팀              이성재          6,000,000  0          0          6,000,000 
         4 개발1팀              가길동          7,000,000  1,200,000  0          5,800,000 
         5 분석팀               나길순          5,000,000  800,000    0          4,200,000 
         3 개발1팀              이정재          4,070,000  0          0          4,070,000 
         2 분석팀               정호연          3,500,000  300,000    0          3,200,000 
         6 개발2팀              이성재          2,500,000  0          0          2,500,000 
         1 개발1팀              이정재          2,500,000  12,346     0          2,487,700 

-- 부서명을 중복 없이 출력 -> DISTINCT
SELECT DISTINCT part FROM pay2 ORDER BY part ASC;

-- receive 값을 저장 
UPDATE pay2 SET receive = bonbong + bonus - tax; 

SELECT payno, part, sawon, bonbong, tax, bonus, receive
FROM pay2;

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS    RECEIVE
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         1 개발1팀              이정재             2500000   12345.67          0    2487654
         2 분석팀               정호연             3500000     300000          0    3200000
         3 개발1팀              이정재             4070000          0          0    4070000
         4 개발1팀              가길동             7000000    1200000          0    5800000
         5 분석팀               나길순             5000000     800000          0    4200000
         6 개발2팀              이성재             2500000          0          0    2500000
         7 개발2팀              이성재             6000000          0          0    6000000
         8 개발2팀              이상우             7500000          0          0    7500000
         
-- 부서별 실수령액(receive) 평균, 백원단위 이상 출력, 천단위 구분자 출력
SELECT part, TO_CHAR(ROUND(AVG(receive), -2), 'FM9,999,999') AS receive
FROM pay2
GROUP BY part
ORDER BY receive DESC;

PART                 RECEIVE   
-------------------- ----------
개발2팀              5,333,300 
개발1팀              4,119,200 
분석팀               3,700,000 

-- 사원별 실수령액(receive) 평균, 백원단위 이상 출력, 천단위 구분자 출력
SELECT sawon, TO_CHAR(ROUND(AVG(receive), -2), 'FM9,999,999') AS receive
FROM pay2
GROUP BY sawon
ORDER BY receive DESC;

SAWON           RECEIVE   
--------------- ----------
이상우          7,500,000 
가길동          5,800,000 
이성재          4,250,000 
나길순          4,200,000 
이정재          3,278,800 
정호연          3,200,000 

-- 사원의 평균 급여를 파악하고, 평균 급여보다 많이 수령하는 직원의 평균 급여를 출력

SELECT TO_CHAR(ROUND(AVG(receive), -2), 'FM9,999,999') AS receive FROM pay2;

RECEIVE   
----------
4,469,700

-- GROUP BY sawon: sawon별 분리 -> HAVING AVG(receive): sawon별로 분리된 레코드에서 평균을 구함.
SELECT sawon, TO_CHAR(ROUND(AVG(receive), -2), 'FM9,999,999') AS receive
FROM pay2
GROUP BY sawon
HAVING AVG(receive) > (SELECT AVG(receive) AS receive FROM pay2)
ORDER BY receive DESC;

SAWON           RECEIVE   
--------------- ----------
이상우          7,500,000 
가길동          5,800,000 

-- 이상우를 제외하고 
-- GROUP BY sawon: sawon별 분리 -> HAVING AVG(receive): sawon별로 분리된 레코드에서 평균을 구함.
SELECT sawon, TO_CHAR(ROUND(AVG(receive), -2), 'FM9,999,999') AS receive
FROM pay2
WHERE sawon != '이상우'
GROUP BY sawon
HAVING AVG(receive) > (SELECT AVG(receive) AS receive FROM pay2)
ORDER BY receive DESC;

SAWON           RECEIVE   
--------------- ----------
가길동          5,800,000 

-- 개발 1팀, 개발 2팀의 팀원 정보 출력
SELECT payno, sawon, part, bonbong, tax, bonus, receive 
FROM pay2
WHERE part = '개발1팀' OR part = '개발2팀';

     PAYNO SAWON           PART                    BONBONG        TAX      BONUS    RECEIVE
---------- --------------- -------------------- ---------- ---------- ---------- ----------
         1 이정재          개발1팀                 2500000   12345.67          0    2487654
         3 이정재          개발1팀                 4070000          0          0    4070000
         4 가길동          개발1팀                 7000000    1200000          0    5800000
         6 이성재          개발2팀                 2500000          0          0    2500000
         7 이성재          개발2팀                 6000000          0          0    6000000
         8 이상우          개발2팀                 7500000          0          0    7500000
         
SELECT DISTINCT part, sawon
FROM pay2
WHERE part = '개발1팀' OR part = '개발2팀'
ORDER BY part ASC, sawon ASC;

PART                 SAWON          
-------------------- ---------------
개발1팀              가길동         
개발1팀              이정재         
개발2팀              이상우         
개발2팀              이성재         

SELECT DISTINCT part, sawon
FROM pay2
WHERE part IN('개발1팀', '개발2팀')       -- IN : 포함
ORDER BY part ASC, sawon ASC;

PART                 SAWON          
-------------------- ---------------
개발1팀              가길동         
개발1팀              이정재         
개발2팀              이상우         
개발2팀              이성재         

SELECT DISTINCT part, sawon
FROM pay2
WHERE sawon IN('이정재', '가길동')        
ORDER BY part ASC, sawon ASC;

PART                 SAWON          
-------------------- ---------------
개발1팀              가길동         
개발1팀              이정재         

SELECT DISTINCT part, sawon
FROM pay2
WHERE sawon NOT IN('이정재', '가길동')    -- NOT IN : 제외
ORDER BY part ASC, sawon ASC;

PART                 SAWON          
-------------------- ---------------
개발2팀              이상우         
개발2팀              이성재         
분석팀               나길순         
분석팀               정호연         




