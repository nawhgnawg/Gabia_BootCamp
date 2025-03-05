DROP TABLE pay PURGE;
 
CREATE TABLE pay(
    payno   NUMBER(7)   NOT NULL,  -- 1 ~ 9999999
    sawon   VARCHAR(15) NOT NULL,  -- 사원명
    address VARCHAR(30) NOT NULL,  -- 주소
    rdate   DATE        NOT NULL,  -- 수령일
    bonbong NUMBER(8)   DEFAULT 0, -- 본봉  
    tax     NUMBER(9, 2)   DEFAULT 0, -- 세금, 정수 7자리, 소수 2자리
    bonus   NUMBER(7)       NULL,  -- 보너스
    PRIMARY KEY(payno)
);

DROP SEQUENCE pay_seq;

CREATE SEQUENCE pay_seq
  START WITH 1           -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 99999999 -- 최대값: 99999999 --> NUMBER(8) 대응
  CACHE 2                  -- 2번은 메모리에서만 계산
  NOCYCLE;                -- 다시 1부터 생성되는 것을 방지

INSERT INTO pay(payno, sawon, address, rdate, bonbong, tax, bonus)
VALUES(pay_seq.nextval, '가길동', '경기도 성남시', '2022-04-27', 2100000, 12345.67, 0); -- 2022-04-20 12:00:00
       
INSERT INTO pay(payno, sawon, address, rdate, bonbong, tax, bonus)
VALUES(pay_seq.nextval, '나길순', '경기도 성남시', '2022-04-24 10:16:50', 2890000, 0, 300000); -- string -> date
 
INSERT INTO pay(payno, sawon, address, rdate, bonbong, tax)
VALUES(pay_seq.nextval, 'Lee Jung Jae', '경기도 부천시', '2022-04-26 10:16:50', 4070000, 0);
 
INSERT INTO pay(payno, sawon, address, rdate, bonbong, tax)
VALUES(pay_seq.nextval,  'Jung Ho Yeon', '경기도 부천시', '2022-04-27 10:16:50', 2960000, 0);

INSERT INTO pay(payno, sawon, address, rdate, bonbong, tax)
VALUES(pay_seq.nextval,  '1유연석', '서울시', '2022-04-28 10:16:50', 6800000, 0);

INSERT INTO pay(payno, sawon, address, rdate, bonbong, tax)
VALUES(pay_seq.nextval,  'keira', '헐리우드', '2022-05-05 10:16:50', 9100000, 0);

COMMIT;

SELECT payno, sawon, address, bonbong, tax, bonus FROM pay;

     PAYNO SAWON           ADDRESS                           BONBONG        TAX      BONUS
---------- --------------- ------------------------------ ---------- ---------- ----------
         1 가길동          경기도 성남시                      2100000   12345.67          0
         2 나길순          경기도 성남시                      2890000          0     300000
         3 Lee Jung Jae    경기도 부천시                      4070000          0           
         4 Jung Ho Yeon    경기도 부천시                      2960000          0           
         5 1유연석         서울시                            6800000          0           
         6 keira           헐리우드                           9100000          0              
         
-- 급여가 가장 높은 직원 정보 출력
SELECT payno, sawon, address, bonbong, tax, bonus 
FROM pay
WHERE bonbong = (SELECT MAX(bonbong) FROM pay);

-- 평균 급여 이상 급여를 받는 직원 출력
SELECT payno, sawon, address, bonbong, tax, bonus 
FROM pay
WHERE bonbong >= (SELECT AVG(bonbong) FROM pay);

- 세금: 10.23456% 계산
UPDATE pay SET tax = bonbong * 0.1023456;

SELECT * FROM pay;

     PAYNO SAWON           ADDRESS                        RDATE                  BONBONG        TAX      BONUS
---------- --------------- ------------------------------ ------------------- ---------- ---------- ----------
         1 가길동          경기도 성남시                   2022-04-27 12:00:00    2100000  214925.76          0
         2 나길순          경기도 성남시                   2022-04-24 10:16:50    2890000  295778.78     300000
         3 Lee Jung Jae    경기도 부천시                   2022-04-26 10:16:50    4070000  416546.59           
         4 Jung Ho Yeon    경기도 부천시                   2022-04-27 10:16:50    2960000  302942.98           
         5 1유연석         서울시                         2022-04-28 10:16:50    6800000  695950.08           
         6 keira           헐리우드                        2022-05-05 10:16:50    9100000  931344.96           

-- 세금 소수 첫째자리, ROUND() 사용 -> 반올림이 됨
SELECT payno, sawon, address, bonbong, ROUND(tax, 1) as tax, bonus
FROM pay
ORDER BY tax DESC;

     PAYNO SAWON           ADDRESS                           BONBONG        TAX      BONUS
---------- --------------- ------------------------------ ---------- ---------- ----------
         6 keira           헐리우드                           9100000     931345           
         5 1유연석         서울시                            6800000   695950.1           
         3 Lee Jung Jae    경기도 부천시                      4070000   416546.6           
         4 Jung Ho Yeon    경기도 부천시                      2960000     302943           
         2 나길순          경기도 성남시                      2890000   295778.8     300000
         1 가길동          경기도 성남시                      2100000   214925.8          0         
         
-- 정수만
SELECT payno, sawon, address, bonbong, ROUND(tax, 0) as tax, bonus
FROM pay
ORDER BY tax DESC;

     PAYNO SAWON           ADDRESS                           BONBONG        TAX      BONUS
---------- --------------- ------------------------------ ---------- ---------- ----------
         6 keira           헐리우드                           9100000     931345           
         5 1유연석         서울시                            6800000     695950           
         3 Lee Jung Jae    경기도 부천시                      4070000     416547           
         4 Jung Ho Yeon    경기도 부천시                      2960000     302943           
         2 나길순          경기도 성남시                      2890000     295779     300000
         1 가길동          경기도 성남시                      2100000     214926          0

-- 십원단위 거래
SELECT payno, sawon, address, bonbong, ROUND(tax, -1) as tax, bonus
FROM pay
ORDER BY tax DESC;

     PAYNO SAWON           ADDRESS                           BONBONG        TAX      BONUS
---------- --------------- ------------------------------ ---------- ---------- ----------
         6 keira           헐리우드                           9100000     931340           
         5 1유연석         서울시                            6800000     695950           
         3 Lee Jung Jae    경기도 부천시                      4070000     416550           
         4 Jung Ho Yeon    경기도 부천시                      2960000     302940           
         2 나길순          경기도 성남시                      2890000     295780     300000
         1 가길동          경기도 성남시                      2100000     214930          0
         
-- 실수령액 계산 : receive = bonbong + bonus - tax, 100의 단위 이상 출력 컬럼 생성 -> bonus가 null인게 있기떄문에 NVL() 사용
SELECT payno, sawon, address, bonbong, bonus, tax, ROUND(bonbong + NVL(bonus, 0) - tax, -2) AS receive
FROM pay
ORDER BY receive DESC;

     PAYNO SAWON           ADDRESS                           BONBONG      BONUS        TAX    RECEIVE
---------- --------------- ------------------------------ ---------- ---------- ---------- ----------
         6 keira           헐리우드                           9100000             931344.96    8168700
         5 1유연석         서울시                            6800000             695950.08    6104000
         3 Lee Jung Jae    경기도 부천시                      4070000             416546.59    3653500
         2 나길순          경기도 성남시                      2890000     300000  295778.78    2894200
         4 Jung Ho Yeon    경기도 부천시                      2960000             302942.98    2657100
         1 가길동          경기도 성남시                      2100000          0  214925.76    1885100

         
-- tax, bonus, receive 컬럼 - 천단위 구분자 
-- TO_CHAR(_, 'FM9,999,999') -> FM(Fill Mode) : 공백 제거 
SELECT payno, sawon, address, TO_CHAR(bonbong, 'FM9,999,999') AS bonbong,
        TO_CHAR(bonus, 'FM9,999,999') AS bonus,
        TO_CHAR(tax, 'FM9,999,999') AS tax,
        TO_CHAR(ROUND(bonbong + NVL(bonus, 0) - tax, -2), 'FM9,999,999') AS receive
FROM pay
ORDER BY receive DESC;

     PAYNO SAWON           ADDRESS                        BONBONG    BONUS      TAX        RECEIVE   
---------- --------------- ------------------------------ ---------- ---------- ---------- ----------
         6 keira           헐리우드                        9,100,000             931,345    8,168,700 
         5 1유연석         서울시                         6,800,000             695,950    6,104,000 
         3 Lee Jung Jae    경기도 부천시                   4,070,000             416,547    3,653,500 
         2 나길순          경기도 성남시                   2,890,000  300,000    295,779    2,894,200 
         4 Jung Ho Yeon    경기도 부천시                   2,960,000             302,943    2,657,100 
         1 가길동          경기도 성남시                   2,100,000  0          214,926    1,885,100 

-- 지역별(group) 직원 수 출력, 
-- 실행 순서 : FWGHSRO <- Order By는 마지막에 실행 
-- 구현 순서 : SELECT -> FROM -> WHERE -> GROUP BY -> HAVING -> ORDER BY
SELECT COUNT(*) as cnt 
FROM pay
GROUP BY address
ORDER BY cnt DESC; 

       CNT
----------
         2
         2
         1
         1

-- 일반적으로는 COUNT는 일반 컬럼과 쓸 수 없음. 하지만 GROUP BY에 선언된 컬럼은 SELECT에 명시할 수 있음. 
SELECT address, COUNT(*) as cnt 
FROM pay
GROUP BY address
ORDER BY cnt DESC; 

ADDRESS                               CNT
------------------------------ ----------
경기도 부천시                            2
경기도 성남시                            2
서울시                                  1
헐리우드                                 1

-- 지역별 BONBONG 평균 출력
SELECT address, TO_CHAR(AVG(bonbong), 'FM9,999,999') AS avg
FROM pay
GROUP BY address
ORDER BY avg DESC;

ADDRESS                        AVG       
------------------------------ ----------
헐리우드                        9,100,000 
서울시                         6,800,000 
경기도 부천시                   3,515,000 
경기도 성남시                   2,495,000 

-- 
