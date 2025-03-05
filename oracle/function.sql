DROP TABLE product;

CREATE TABLE product(
  productno  NUMBER(8)      NOT NULL, -- 판매 번호
  title      VARCHAR(20)    NOT NULL, -- 상품명
  price      NUMBER(8)      NOT NULL, -- 가격
  cnt        NUMBER(5)          NULL, -- 주문 수량
  dc         NUMBER(5, 2)   NOT NULL, -- 할인율, 정수 3자리, 소수 2자리
  rdate      DATE               NULL, -- 주문 날짜
  PRIMARY KEY(productno)
);

DROP SEQUENCE product_seq;

CREATE SEQUENCE product_seq
  START WITH 1          -- 시작 번호
  INCREMENT BY 1        -- 증가값
  MAXVALUE 99999999     -- 최대값: 99999999 --> NUMBER(8) 대응
  CACHE 2               -- 2번은 메모리에서만 계산
  NOCYCLE;              -- 다시 1부터 생성되는 것을 방지
  
INSERT INTO product(productno, title, price, cnt, dc, rdate)
VALUES(product_seq.nextval, 'AN517-55 3070Ti', 1549000, 1, 10.0, sysdate);

INSERT INTO product(productno, title, price, cnt, dc, rdate)
VALUES(product_seq.nextval, 'B12UGSZ', 1692000, 1, 5.5, sysdate);

INSERT INTO product(productno, title, price, cnt, dc, rdate)
VALUES(product_seq.nextval, 'B12UGSZ WIN11', 1728000, 2, 5.5, sysdate);

INSERT INTO product(productno, title, price, cnt, dc) -- rdate 생략
VALUES(product_seq.nextval, 'B12UGSZ 32GB', 1804000, 1, 4.0);

INSERT INTO product(productno, title, price, cnt, dc)
VALUES(product_seq.nextval, 'B12UGSZ WIN11 32GB ', 1838000, 1, 4.5);  -- rdate 생략

SELECT productno, title, price, cnt, dc, rdate FROM product ORDER BY productno;

-- PRODUCTNO TITLE                     PRICE        CNT         DC RDATE              
------------ -------------------- ---------- ---------- ---------- -------------------
--         1 AN517-55 3070Ti         1549000          1         10 2024-03-06 02:41:08
--         2 B12UGSZ                 1692000          1        5.5 2024-03-06 02:41:08
--         3 B12UGSZ WIN11           1728000          2        5.5 2024-03-06 02:41:08
--         4 B12UGSZ 32GB            1804000          1          4                    
--         5 B12UGSZ WIN11 32GB      1838000          1        4.5                    

-- 상품 갯수
SELECT COUNT(*) FROM product;

SELECT COUNT(*) AS cnt FROM product;

-- NULL 값은 포함 안됨
SELECT COUNT(rdate) AS cnt FROM product;

SELECT COUNT(productno) AS cnt FROM product;

-- TITLE 컬럼에 '32GB'가 있는 레코드의 수는
SELECT COUNT(title) AS cnt 
FROM product 
WHERE title LIKE '%32GB%';

-- 값에 대해서는 대소문자를 구분한다.
SELECT productno, title, price, cnt, dc, rdate
FROM product 
WHERE title LIKE '%b12ugsz%'
ORDER BY productno;

SELECT productno, title, price, cnt, dc, rdate
FROM product 
WHERE title LIKE UPPER('%b12ugsz%')
ORDER BY productno;

SELECT productno, title, price, cnt, dc, rdate
FROM product 
WHERE title LIKE '%' || UPPER('b12') || '%'
ORDER BY productno;

-- SUBSTR(string, start_ position, [length]): index 1부터 시작
SELECT SUBSTR('123 ABC 가나다', 1, 5) as word FROM dual;
WORD 
-----
123 A

SELECT SUBSTR('123 ABC 가나다', 1, 9) as word FROM dual; -- 공백 1자, 한글 1자
WORD     
---------
123 ABC 가

-- SUBSTR 함수를 이용하여 year, month, day 컬럼 추가 
SELECT productno, title, SUBSTR(rdate, 1, 4) as year, SUBSTR(rdate, 6, 2) as month, SUBSTR(rdate, 9, 2) as day
FROM product
ORDER BY productno;

-- 가장 저렴한 가격의 상품 목록부터 출력
SELECT productno, title, price, cnt, dc, rdate 
FROM product 
ORDER BY price ASC;

 PRODUCTNO TITLE                     PRICE        CNT         DC RDATE              
---------- -------------------- ---------- ---------- ---------- -------------------
         1 AN517-55 3070Ti         1549000          1         10 2025-03-04 04:57:11
         2 B12UGSZ                 1692000          1        5.5 2025-03-04 04:57:11
         3 B12UGSZ WIN11           1728000          2        5.5 2025-03-04 04:57:11
         4 B12UGSZ 32GB            1804000          1          4                    
         5 B12UGSZ WIN11 32GB      1838000          1        4.5                    


-- 고가의 상품 목록부터 출력
SELECT productno, title, price, cnt, dc, rdate 
FROM product 
ORDER BY price DESC;

 PRODUCTNO TITLE                     PRICE        CNT         DC RDATE              
---------- -------------------- ---------- ---------- ---------- -------------------
         5 B12UGSZ WIN11 32GB      1838000          1        4.5                    
         4 B12UGSZ 32GB            1804000          1          4                    
         3 B12UGSZ WIN11           1728000          2        5.5 2025-03-04 04:57:11
         2 B12UGSZ                 1692000          1        5.5 2025-03-04 04:57:11
         1 AN517-55 3070Ti         1549000          1         10 2025-03-04 04:57:11


-- 가장 고가의 상품 가격은?
SELECT MAX(price) as price FROM product;

     PRICE
----------
   1838000

-- 가장 고가의 상품 정보 출력 
-- SELECT productno, title, MAX(price) as price, cnt FROM product; -- X, 단일 그룹의 그룹 함수가 아닙니다.
-- 최고가의 가격에 해당하는 상품정보를 가져올 기준이 없음.
SELECT productno, title, price, cnt
FROM product
WHERE price = 1838000;

 PRODUCTNO TITLE                     PRICE        CNT
---------- -------------------- ---------- ----------
         5 B12UGSZ WIN11 32GB      1838000          1
         
-- 최고가가 실시간으로 변경되는 경우의 해결책
SELECT productno, title, price, cnt
FROM product
WHERE price = (SELECT MAX(price) as price FROM product);

 PRODUCTNO TITLE                     PRICE        CNT
---------- -------------------- ---------- ----------
         5 B12UGSZ WIN11 32GB      1838000          1
         
-- 가장 저렴한 상품 정보 출력 
SELECT productno, title, price, cnt
FROM product
WHERE price = (SELECT MIN(price) as price FROM product);

 PRODUCTNO TITLE                     PRICE        CNT
---------- -------------------- ---------- ----------
         1 AN517-55 3070Ti         1549000          1

-- MAX, MIN등은 그룹화 함수로 일반 컬럼과 함께 사용 불가능.
-- 그룹화 함수는 여러 레코드의 연산을 통한 집계 결과가 나오기 때문임.

-- 평균 가격, 가격의 합 (집계 함수끼리는 가능)
SELECT AVG(price) as mean, SUM(price) as total FROM product;

      MEAN      TOTAL
---------- ----------
   1722200    8611000
   
-- 평균 가격 이상의 상품 목록 출력 
SELECT productno, title, price, cnt
FROM product
WHERE price >= (SELECT AVG(price) as price FROM product);

 PRODUCTNO TITLE                     PRICE        CNT
---------- -------------------- ---------- ----------
         3 B12UGSZ WIN11           1728000          2
         4 B12UGSZ 32GB            1804000          1
         5 B12UGSZ WIN11 32GB      1838000          1
         
-- null : 값이 없으면  null로 표시
SELECT null + 1 as cnt FROM dual;

       CNT
----------
          
          
DELETE FROM product;

SELECT * FROM product;

-- sequenct를 사용하지 않고 일련번호를 일정하게 증가 시키는 경우 예) 코드의 생성
-- 1. productno + 1 : 컬럼에 값을 직접 더함, 하지만 productno 컬럼의 기준이 없다. X
-- INSERT INTO product(productno, title, price, cnt, dc, rdate)
-- VALUES(productno + 1, 'N95 Window 11', 310000, 1, 5.0, sysdate);

SELECT COUNT(*) as cnt FROM product;

       CNT
----------
         0

-- COUNT는 0이 나오지만 MAX는 null이 나옴, 즉 null을 바꿔주는 함수가 필요
SELECT MAX(productno) as cnt FROM product;

       CNT
----------
     
-- NVL() : 칼럼의 값이 null이면 0으로 변경
SELECT NVL(null, 0) + 1 as cnt FROM dual;     
          
       CNT
----------
         1
         
SELECT NVL(MAX(productno), 0) + 1 as cnt FROM product; 

       CNT
----------
         1
          
-- 2. NVL() 사용
INSERT INTO product(productno, title, price, cnt, dc, rdate)
VALUES((SELECT NVL(MAX(productno), 0) + 1 as cnt FROM product), 'N95 Window 11', 310000, 1, 5.0, sysdate);

INSERT INTO product(productno, title, price, cnt, dc, rdate)
VALUES((SELECT NVL(MAX(productno), 0) + 1 as cnt FROM product), 'N100 Window 11', 340000, 1, 5.0, sysdate);

SELECT * FROM product;

 PRODUCTNO TITLE                     PRICE        CNT         DC RDATE              
---------- -------------------- ---------- ---------- ---------- -------------------
         1 N95 Window 11            310000          1          5 2025-03-05 10:52:50
         2 N100 Window 11           340000          1          5 2025-03-05 10:53:17
    
-- = : 같다. 
SELECT 'A = A' as col FROM dual WHERE 'A' = 'A';

COL
---
A=A

-- != : 같지 않다.
SELECT 'A != A' as col FROM dual WHERE 'A' != 'A';
선택된 행 없음

-- <> : 같지 않다.
SELECT 'A <> B' as col FROM dual WHERE 'A' <> 'B';

COL   
------
A <> B

SELECT 'A <> B' as col FROM dual WHERE 'A' != 'B';

COL   
------
A <> B