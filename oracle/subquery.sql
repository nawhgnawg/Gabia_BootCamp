DROP TABLE pay2;

CREATE TABLE pay2(
    payno   NUMBER(7)   NOT NULL,  -- 1 ~ 9999999
    part    VARCHAR(20) NOT NULL,  -- 부서명
    sawon   VARCHAR(15) NOT NULL,  -- 사원명
    bonbong NUMBER(8)   DEFAULT 0, -- 본봉  
    tax     NUMBER(10, 2)   DEFAULT 0, -- 세금, 전체 자리, +-99999999.99
    bonus   NUMBER(7)       NULL,  -- 보너스
    PRIMARY KEY(payno)
);

INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(1, '개발팀', '이정재', 2512345, 12345.67, 0);
       
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(2, '분석팀', '정호연', 3500000, 300000, 0);
 
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(3, '개발팀', '이정재', 4070000, 0, 0);
 
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(4, '개발팀', '가길동', 7000000, 1200000, 0);

INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(5, '분석팀', '나길순', 5000000, 800000, 0);

SELECT payno, part, sawon, bonbong, tax, bonus
FROM pay2;

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         1 개발팀               이정재             2512345   12345.67          0
         2 분석팀               정호연             3500000     300000          0
         3 개발팀               이정재             4070000          0          0
         4 개발팀               가길동             7000000    1200000          0
         5 분석팀               나길순             5000000     800000          0

COMMIT;

-- 1) 개발팀의 평균 급여(bonbong 컬럼, 백원단위 이상 출력, 십원 단위에서 반올림)
SELECT TO_CHAR(ROUND(AVG(bonbong), -2), 'FM9,999,999') AS bonbong
FROM pay2
WHERE part = '개발팀';

BONBONG   
----------
4,527,400
   
-- 2) 개발팀의 평균 급여 이상을 받는 전체 직원 출력
SELECT payno, part, sawon, bonbong, tax, bonus
FROM pay2
WHERE bonbong >= (SELECT AVG(bonbong) AS bonbong FROM pay2 WHERE part = '개발팀');

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0
         5 분석팀               나길순             5000000     800000          0

-- subquery


-- 3) 가길동과 같은 부서의 평균 급여 출력
SELECT TO_CHAR(ROUND(AVG(bonbong), -2), 'FM9,999,999') AS bonbong
FROM pay2
WHERE part = (SELECT part FROM pay2 WHERE sawon = '가길동');

PART                
--------------------
개발팀



BONBONG   
----------
4,527,400

-- 나길순과 같은 부서의 평균 급여 출력
SELECT TO_CHAR(ROUND(AVG(bonbong), -2), 'FM9,999,999') AS bonbong 
FROM pay2
WHERE part = (SELECT part FROM pay2 WHERE sawon = '나길순');

BONBONG   
----------
4,250,000
   
-- 나길순이 근무하는 부서의 평균 급여 이상을 수령하는 모든 부서의 직원 출력
SELECT payno, part, sawon, bonbong, tax, bonus
FROM pay2
WHERE bonbong >= (SELECT AVG(bonbong) FROM pay2 WHERE part = (SELECT part FROM pay2 WHERE sawon = '나길순'));

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0
         5 분석팀               나길순             5000000     800000          0

         
-- 나길순이 근무하는 부서의 평균 급여 이상을 수령하는 모든 부서의 직원을 출력하되, TAX를 1000000 이상 공제하는 직원 출력


     PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0


-- ----------------------------------------------------------------------------
-- 페이징: 레코드 분할하여 읽기
-- ----------------------------------------------------------------------------
INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(6, '개발팀', '왕눈이', 5500000, 800000, 0);

INSERT INTO pay2(payno, part, sawon, bonbong, tax, bonus)
VALUES(7, '분석팀', '아로미', 6500000, 1000000, 0);

SELECT payno, part, sawon, bonbong, tax, bonus
FROM pay2;

    PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         1 개발팀               이정재             2512345   12345.67          0
         2 분석팀               정호연             3500000     300000          0
         3 개발팀               이정재             4070000          0          0
         4 개발팀               가길동             7000000    1200000          0
         5 분석팀               나길순             5000000     800000          0
         6 개발팀               왕눈이             5500000     800000          0
         7 분석팀               아로미             6500000    1000000          0

-- sawon 컬럼 오름차순 정렬

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS
---------- -------------------- --------------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0
         5 분석팀               나길순             5000000     800000          0
         7 분석팀               아로미             6500000    1000000          0
         6 개발팀               왕눈이             5500000     800000          0
         3 개발팀               이정재             4070000          0          0
         1 개발팀               이정재             2512345   12345.67          0
         2 분석팀               정호연             3500000     300000          0

-- 레코드 분할시 사용할 정보는? PK는 정렬시 일정하게 출력되지 않음으로 사용 불가

     PAYNO PART                 SAWON              BONBONG        TAX      BONUS          R
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         1 개발팀               이정재             2512345   12345.67          0          1
         2 분석팀               정호연             3500000     300000          0          2
         3 개발팀               이정재             4070000          0          0          3
         4 개발팀               가길동             7000000    1200000          0          4
         5 분석팀               나길순             5000000     800000          0          5
         6 개발팀               왕눈이             5500000     800000          0          6
         7 분석팀               아로미             6500000    1000000          0          7

-- FWGHSR(Rownum)O: SQL 마지막에 정렬이 발생함.★


     PAYNO PART                 SAWON              BONBONG        TAX      BONUS          R
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0          4 <- 1페이지
         5 분석팀               나길순             5000000     800000          0          5
         7 분석팀               아로미             6500000    1000000          0          7 
         
         6 개발팀               왕눈이             5500000     800000          0          6 <- 2페이지
         3 개발팀               이정재             4070000          0          0          3
         1 개발팀               이정재             2512345   12345.67          0          1 
         
         2 분석팀               정호연             3500000     300000          0          2 <- 3페이지
         
-- FWGHSRO: 정렬 -> ROWNUM


     PAYNO PART                 SAWON              BONBONG        TAX      BONUS          R
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0          1
         5 분석팀               나길순             5000000     800000          0          2
         7 분석팀               아로미             6500000    1000000          0          3
         6 개발팀               왕눈이             5500000     800000          0          4
         3 개발팀               이정재             4070000          0          0          5
         1 개발팀               이정재             2512345   12345.67          0          6
         2 분석팀               정호연             3500000     300000          0          7

-- 분할, FWGHSRO에의하면 rownum 인식 불가


     PAYNO PART                 SAWON              BONBONG        TAX      BONUS          R
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         4 개발팀               가길동             7000000    1200000          0          1
         5 분석팀               나길순             5000000     800000          0          2
         7 분석팀               아로미             6500000    1000000          0          3
         


     PAYNO PART                 SAWON              BONBONG        TAX      BONUS          R
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         6 개발팀               왕눈이             5500000     800000          0          4
         3 개발팀               이정재             4070000          0          0          5
         1 개발팀               이정재             2512345   12345.67          0          6
         

         
     PAYNO PART                 SAWON              BONBONG        TAX      BONUS          R
---------- -------------------- --------------- ---------- ---------- ---------- ----------
         2 분석팀               정호연             3500000     300000          0          7