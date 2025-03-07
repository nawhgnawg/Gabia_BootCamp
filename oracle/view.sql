1. 기본적인 View의 생성
 
1) 테이블 구조
DROP TABLE test PURGE;
 
CREATE TABLE test(
    testno NUMBER(5)   NOT NULL, -- 일련번호
    name   VARCHAR(30) NOT NULL, -- 성명
    mat    NUMBER(3)   NOT NULL, -- 수학
    eng    NUMBER(3)   NOT NULL, -- 영어
    tot    NUMBER(3)       NULL, -- 총점
    avg    NUMBER(4, 1)    NULL, -- 평균
    PRIMARY KEY (testno) 
);
 
DELETE FROM test;
 
2) 기초 데이터 추가
INSERT INTO test(testno, name, mat, eng)
VALUES((SELECT NVL(MAX(testno), 0)+1 as cnt FROM test), '피어스 브러스넌', 80, 100);
 
INSERT INTO test(testno, name, mat, eng)
VALUES((SELECT NVL(MAX(testno), 0)+1 as cnt FROM test), '메릴스트립', 80, 100);
 
INSERT INTO test(testno, name, mat, eng)
VALUES((SELECT NVL(MAX(testno), 0)+1 as cnt FROM test), '사이프리드', 85, 80);
 
INSERT INTO test(testno, name, mat, eng)
VALUES((SELECT NVL(MAX(testno), 0)+1 as cnt FROM test),  '콜린퍼스', 65, 60);
 
INSERT INTO test(testno, name, mat, eng)
VALUES((SELECT NVL(MAX(testno), 0)+1 as cnt FROM test), '스텔란 스카스가드', 75, 70);

SELECT testno, name, mat, eng FROM test;
    TESTNO NAME                                  MAT        ENG
---------- ------------------------------ ---------- ----------
         1 피어스 브러스넌                        80        100
         2 메릴스트립                            80        100
         3 사이프리드                            85         80
         4 콜린퍼스                              65         60
         5 스텔란 스카스가드                      75         70
         
SELECT * FROM test;
    TESTNO NAME                                  MAT        ENG        TOT        AVG
---------- ------------------------------ ---------- ---------- ---------- ----------
         1 피어스 브러스넌                        80        100                      
         2 메릴스트립                             80        100                      
         3 사이프리드                             85         80                      
         4 콜린퍼스                               65         60                      
         5 스텔란 스카스가드                      75         70          
         
commit;

-- 총점 산출
UPDATE test SET tot = mat + eng;

-- 평균 산출
UPDATE test SET avg = tot / 2;

commit;

SELECT testno, name, mat, eng, tot,avg FROM test ORDER BY testno ASC;

    TESTNO NAME                                  MAT        ENG        TOT        AVG
---------- ------------------------------ ---------- ---------- ---------- ----------
         1 피어스 브러스넌                        80        100        180         90
         2 메릴스트립                             80        100        180         90
         3 사이프리드                             85         80        165       82.5
         4 콜린퍼스                               65         60        125       62.5
         5 스텔란 스카스가드                      75         70        145       72.5
 
3) VIEW의 생성
-- 사용하려는 테이블을 필터링하여 SQL의 복잡도를 낮추고 컬럼을 감출수 있어 보안성을 향상 시킴.
-- 평균이 90점 이상인 우수생 목록
SELECT testno, name, mat, eng, tot,avg 
FROM test 
WHERE avg >= 90 
ORDER BY testno ASC;

    TESTNO NAME                                  MAT        ENG        TOT        AVG
---------- ------------------------------ ---------- ---------- ---------- ----------
         1 피어스 브러스넌                         80        100        180         90
         2 메릴스트립                             80        100        180         90

-- View 생성
CREATE VIEW vtest_90 
AS 
SELECT testno, name, mat, eng, tot,avg 
FROM test 
WHERE avg >= 90 
ORDER BY testno ASC;

SELECT * FROM tab;
TNAME                          TABTYPE  CLUSTERID
------------------------------ ------- ----------
SKGRP                          TABLE             
TEST                           TABLE             
VTEST_90                       VIEW   <- 뷰

-- View 사용, 평균 90점이상, testno ASC 정렬인 사원 출력 
SELECT testno, name, mat, eng, tot, avg FROM vtest_90;

    TESTNO NAME                                  MAT        ENG        TOT        AVG
---------- ------------------------------ ---------- ---------- ---------- ----------
         1 피어스 브러스넌                        80        100        180         90
         2 메릴스트립                             80        100        180         90 

DROP VIEW vtest_90;
 
2. 일부 컬럼만 View의 대상으로 지정
CREATE VIEW vtest_80 
AS 
SELECT testno, name, tot, avg 
FROM test 
WHERE avg >= 80 
ORDER BY testno ASC;

 
-- View에 만들어진 컬럼만 출력됨.
SELECT * FROM vtest_80;

SELECT testno, name, tot, avg FROM vtest_80;

    TESTNO NAME                                  TOT        AVG
---------- ------------------------------ ---------- ----------
         1 피어스 브러스넌                       180         90
         2 메릴스트립                           180         90
         3 사이프리드                           165         82.5
         

 
-- ERROR, View에 없는 컬럼 mat, eng 접근 못함.
SELECT testno, name, mat, eng, tot, avg FROM vtest_80; 
 
 
3. ()안의 컬럼은 생성되는 View의 컬럼의 별명입니다.
- 실제 컬럼명이 감추어짐.(보안상 장점)
CREATE VIEW vtest_70 (
    hakbun, student_name, total, average
)
AS 
SELECT testno, name, tot, avg 
FROM test 
WHERE avg >= 70 
ORDER BY testno ASC;
 
SELECT * FROM vtest_70;

    HAKBUN STUDENT_NAME                      TOTAL    AVERAGE
---------- ------------------------------ ---------- ----------
         1 피어스 브러스넌                       180         90
         2 메릴스트립                           180         90
         3 사이프리드                           165         82.5
         5 스텔란 스카스가드                     145         72.5

-- 설계의 변경으로 컬럼명이 변경되는 경우 View를 이용한 컬럼명 통일 -> VO class 동일 -> interface, Controller 동일
CREATE VIEW vtest_70(
  hakbun, student_name, total, average  <- 컬럼명 통일
)
AS
SELECT testno, name, tot, mean  <- 실제 컬럼명은 다름
.....

CREATE VIEW vtest_70(
  hakbun, student_name, total, average  <- 컬럼명 통일
)
AS
SELECT testno, name, scoresum, py  <- 실제 컬럼명은 다름
.....

-- 개발시 table, view의 컬럼은 동일한 것이 가장 편리함.

4. 함수를 이용한 View의 생성
-- CREATE OR REPLACE VIEW: 기존에 동일한 이름의 View가 있으면 삭제하고 새롭게 생성함.

CREATE OR REPLACE VIEW vtest_func (
    max_total, min_total, avg_total
)
AS
SELECT MAX(tot), MIN(tot), AVG(tot)
FROM test;

SELECT * FROM vtest_func;
 MAX_TOTAL  MIN_TOTAL  AVG_TOTAL
---------- ---------- ----------
       180        125        159
       
SELECT max_total, min_total, avg_total FROM vtest_func;
 
 
5. WITH CHECK OPTION
   - VIEW는 INSERT, UPDATE, DELETE 모두 가능하나 SELECT의 최적화 목적
   - VIEW 생성시 WHERE문에 명시한 컬럼의 값을 변경 할 수 없습니다.
 
1) 실습용 테이블
DROP TABLE employee;
 
CREATE TABLE employee(
    name          varchar(10) not null,
    salary        number(7)   not null,
    department_id number(4)   not null
);
 
INSERT INTO employee(name, salary,department_id)
VALUES('aaa', 1000000, 20);
INSERT INTO employee(name, salary,department_id)
VALUES('bbb', 1100000, 20);
INSERT INTO employee(name, salary,department_id)
VALUES('ccc', 1200000, 20);
INSERT INTO employee(name, salary,department_id)
VALUES('ddd', 1300000, 30);
INSERT INTO employee(name, salary,department_id)
VALUES('eee', 1400000, 40);
 
SELECT * FROM employee;
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20
bbb           1100000            20
ccc           1200000            20
ddd           1300000            30
eee           1400000            40
 
COMMIT;
 
2) WITH CHECK OPTION 사용하지 않은 경우
-- DEPARTMENT_ID 컬럼이 20인 레코드를 대상으로 view 생성
CREATE OR REPLACE VIEW vemp20
AS
SELECT name, salary,department_id
FROM employee
WHERE department_id = 20
ORDER BY name ASC;
 
SELECT name, salary, department_id FROM vemp20;
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20
bbb           1100000            20
ccc           1200000            20

SELECT name, salary, department_id FROM vemp20 ORDER BY name DESC;

NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
ccc           1200000            20
bbb           1100000            20
aaa           1000000            20
 
-- vemp20은 20번 부서만 작업 대상으로 하나
-- WHERE문에 나타난 부서를 30번으로 변경함으로 논리적 에러가
-- 발생합니다.
-- View를 이용한 UPDATE는 권장이 아닙니다.
UPDATE vemp20 SET department_id=30;
 
-- 부서가 모두 30번으로 변경되어 결과가 없습니다.
SELECT * FROM vemp20;
선택된 행 없음

SELECT * FROM employee;
AME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            30  <- 20에서 30으로 변경됨
bbb           1100000            30  <- 20에서 30으로 변경됨
ccc           1200000            30  <- 20에서 30으로 변경됨
ddd           1300000            30
eee           1400000            40
 
-- INSERT, DELETE, UPDATE 실행 취소
ROLLBACK;
 
SELECT * FROM employee;
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20 <- 복구됨.
bbb           1100000            20
ccc           1200000            20
ddd           1300000            30
eee           1400000            40
  
 
3) WITH CHECK OPTION을 사용한 경우
-- WITH CHECK OPTION CONSTRAINT 제약 조건명;
CREATE OR REPLACE VIEW vemp20
AS
SELECT name, salary,department_id
FROM employee
WHERE department_id = 20
ORDER BY name ASC
WITH CHECK OPTION CONSTRAINT vemp20_ck;
   
SELECT * FROM vemp20;
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20
bbb           1100000            20
ccc           1200000            20
 
-- UPDATE가 금지되어 실행이 안됩니다. UPDATE를 실행하고자 할 경우는
-- 실제의 테이블을 대상으로 합니다.
-- SQL 오류: ORA-01402: view WITH CHECK OPTION where-clause violation
-- 01402. 00000 -  "view WITH CHECK OPTION where-clause violation"
-- ORA-01402: 뷰의 WITH CHECK OPTION의 조건에 위배 됩니다
UPDATE vemp20 SET department_id=30;

-- where문에 명시하지 않은 컬럼은 변경 가능 
UPDATE vemp20 SET salary = 5000000 WHERE name = 'ccc';
 
SELECT * FROM vemp20;  -- view
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20
bbb           1100000            20
ccc           5000000            20   <- 1200000에서 5000000으로 인상

SELECT * FROM employee; -- table
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20
bbb           1100000            20
ccc           5000000            20   <- 1200000에서 5000000으로 인상
ddd           1300000            30
eee           1400000            40

DELETE FROM vemp20 WHERE salary <= 1100000;

SELECT * FROM employee; -- table, 원본 데이터가 삭제되는 문제 발생, View는 SELECT가 목적
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
ccc           5000000            20
ddd           1300000            30
eee           1400000            40

ROLLBACK;
롤백 완료.

SELECT * FROM employee;
NAME           SALARY DEPARTMENT_ID
---------- ---------- -------------
aaa           1000000            20
bbb           1100000            20
ccc           1200000            20
ddd           1300000            30
eee           1400000            40
 
  
6. WITH READ ONLY 옵션(권장)
  - View에서 UPDATE, INSERT, DELETE 기능을 금지시킵니다.
  - View는 SELECT 목적임으로 변경 금지

-- test 테이블 대상으로 tot가 150 이상이고, 출력 컬럼을 testno -> no, name, tot -> total로 변경하여 View 생성
CREATE OR REPLACE VIEW test_read (
    no, name, total
)
AS
SELECT testno, name, tot
FROM test
WHERE tot >= 150
ORDER BY tot DESC
WITH READ ONLY;

SELECT * FROM test_read;
        NO NAME                                TOTAL
---------- ------------------------------ ----------
         1 피어스 브러스넌                        180
         2 메릴스트립                            180
         3 사이프리드                            165

-- SQL 오류
INSERT INTO test_read(no, name, total)
VALUES((SELECT NVL(MAX(testno), 0)+1 as cnt FROM test), '줄리 월터스', 150);
 
-- SQL 오류: ORA-42399: cannot perform a DML operation on a read-only view
-- SQL 오류: ORA-42399: 읽기 전용 뷰에서는 DML 작업을 수행할 수 없습니다.
-- 42399.0000 - "cannot perform a DML operation on a read-only view"
UPDATE test_read SET total = 200; -- X
 
 
※ VIEW는 INSERT, UPDATE, DELETE에는 사용을 권장하지 않습니다. ★
 
 
7. FROM 절에 기록된 Subquery는 INLINE VIEW라고 해서
   SQL 내부에 포함된 임시 VIEW라고 부릅니다.(페이징에서 많이 사용됨.)
    
1) 레코드 정렬
SELECT testno, name, mat, eng, tot, avg
FROM test
ORDER BY testno DESC;
 
2) rownum 산출
-- Inline View: subquery에서 만들어져 1회성으로 사용되는 view
SELECT testno, name, mat, eng, tot, avg, rownum as r
FROM(
    SELECT testno, name, mat, eng, tot, avg -- Inline View
    FROM test
    ORDER BY testno DESC
);
   
3) record 분할
SELECT testno, name, mat, eng, tot, avg, r -- 레코드 분할
FROM(
    SELECT testno, name, mat, eng, tot, avg, rownum as r -- rownum 생성
    FROM(
        SELECT testno, name, mat, eng, tot, avg -- Inline View, 정렬
        FROM test
        ORDER BY testno DESC
    )
)
WHERE r > =1 AND r <= 3;
    TESTNO NAME                                MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         5 스텔란 스카스가드                       75         70        145       72.5          1
         4 콜린퍼스                              65         60        125       62.5          2
         3 사이프리드                             85         80        165       82.5          3
 
SELECT testno, name, mat, eng, tot, avg, r
FROM(
    SELECT testno, name, mat, eng, tot, avg, rownum as r
    FROM(
        SELECT testno, name, mat, eng, tot, avg -- Inline View
        FROM test
        ORDER BY testno DESC
    )
)
WHERE r > =4 AND r <= 6;
    TESTNO NAME                               MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         2 메릴스트립                            80        100        180         90          4
         1 피어스 브러스넌                        80        100        180         90          5
 
4) 검색 + 페이징
SELECT testno, name, mat, eng, tot, avg, r
FROM(
    SELECT testno, name, mat, eng, tot, avg, rownum as r
    FROM(
        SELECT testno, name, mat, eng, tot, avg -- Inline View
        FROM test
        WHERE name LIKE '%메릴%'
        ORDER BY testno DESC
    )
)
WHERE r > =1 AND r <= 3;
    TESTNO NAME                                  MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         2 메릴스트립                             80        100        180         90          1
         
 
5) 페이징을위한 View의 생성 - 서브쿼리 갯수를 줄일 수 있음 
CREATE OR REPLACE VIEW vtest_list
AS 
SELECT testno, name, mat, eng, tot, avg -- Inline View
FROM test
ORDER BY testno DESC
WITH READ ONLY;

6) Subquery의 View의 사용
 
SELECT testno, name, mat, eng, tot, avg, rownum r
FROM vtest_list;
    TESTNO NAME                                  MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         5 스텔란 스카스가드                      75         70        145       72.5          1
         4 콜린퍼스                              65         60        125       62.5          2
         3 사이프리드                            85         80        165       82.5          3
         2 메릴스트립                            80        100        180         90          4
         1 피어스 브러스넌                        80        100        180         90          5

-- 1 page 
SELECT testno, name, mat, eng, tot, avg, r 
FROM (
     SELECT testno, name, mat, eng, tot, avg, rownum r
     FROM vtest_list
)
WHERE r >= 1 AND r <= 3;

    TESTNO NAME                               MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         5 스텔란 스카스가드                      75         70        145       72.5          1
         4 콜린퍼스                             65         60        125       62.5          2
         3 사이프리드                            85         80        165       82.5          3

-- 2 page  
SELECT testno, name, mat, eng, tot, avg, r 
FROM (
     SELECT testno, name, mat, eng, tot, avg, rownum r
     FROM vtest_list
)
WHERE r >= 4 AND r <= 6;

    TESTNO NAME                               MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         2 메릴스트립                            80        100        180         90          4
         1 피어스 브러스넌                        80        100        180         90          5
         
 
-- 검색 + 페이징 ★
SELECT testno, name, mat, eng, tot, avg, r 
FROM (
     SELECT testno, name, mat, eng, tot, avg, rownum r
     FROM vtest_list
     WHERE name LIKE '%메릴%'
)
WHERE r >= 1 AND r <= 3;

    TESTNO NAME                                  MAT        ENG        TOT        AVG          R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         2 메릴스트립                             80        100        180         90          1

 
8. paging view 제작시 검색에서 문제가 되는 경우, view를 subquery를 사용하여 2단으로 생성한 경우
-- 2단 subquery로 view 생성
CREATE OR REPLACE VIEW v_test_list
AS
SELECT testno, name, mat, eng, tot, avg, rownum as r
FROM(
    SELECT testno, name, mat, eng, tot, avg -- Inline View
    FROM test
    ORDER BY testno DESC
)
WITH READ ONLY;

-- 1 page 
SELECT testno, name, mat, eng, tot, avg, r
FROM v_test_list
WHERE r > =1 AND r <= 3;

    TESTNO NAME                                  MAT        ENG        TOT        AVG       R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         5 스텔란 스카스가드                      75         70        145       72.5          1
         4 콜린퍼스                               65         60        125       62.5         2
         3 사이프리드                             85         80        165       82.5         3
         
-- 2 page  
SELECT testno, name, mat, eng, tot, avg, r
FROM v_test_list
WHERE r > = 4 AND r <= 6;

    TESTNO NAME                                  MAT        ENG        TOT        AVG       R
---------- ------------------------------ ---------- ---------- ---------- ---------- ----------
         2 메릴스트립                             80        100        180         90        4  <- 4번 기억 해둠
         1 피어스 브러스넌                        80        100        180         90        5
 
-- 전체 레코드에서 검색일 경우
-- 2단 서브쿼리를 이용한 view 검색에서 rownum이 제외 될 수 있음.
SELECT testno, name, mat, eng, tot, avg, r
FROM v_test_list
WHERE name LIKE '%메릴%';

    TESTNO NAME                        MAT        ENG        TOT        AVG       R
---------- -------------------- ---------- ---------- ---------- ---------- ----------
         2 메릴스트립                   80        100        180         90        4 ◀-- 문제 발생, rownum이 1이 아님


--  '메릴' 검색 결과가 많아서 1페이지만 출력하려는 경우, 하지만 레코드 출력 안됨.
SELECT testno, name, mat, eng, tot, avg, r
FROM v_test_list
WHERE name LIKE '%메릴%'  AND (r > = 1 AND r <= 3);

선택된 행 없음

-- rownum이 먼저 생성된 후 검색이 진행됨으로 검색 결과를 가지고 페이지구현이 안되는 문제가 있음으로
-- rownum을 생성하는 View는 사용을 권장하지 않음.
-- 페이징이 정상적으로 되는 경우 SQL 실행 순서: FWGHSRO
-- 페이징의 경우 2단 subquery 사용을 권장하지 않음으로 1단을 사용 할 것 ★.
