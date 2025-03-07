1. 견본 테이블 생성
 
DROP TABLE pay PURGE;   -- PURGE: 삭제 후 복구 불가 
 
CREATE TABLE pay(
    name varchar(10) NOT NULL,
    pay  number(8)   NOT NULL,
    tax  number(7)   DEFAULT 0
);

-- 모든 테이블 출력 
SELECT * FROM tab;
 
 
2. COMMIT(INSERT, UPDATE, DELETE 적용)

-- 메모리상에 INSERT만 된 상태, 실제 DBMS에는 저장안되어 있음. 
INSERT INTO pay(name, pay, tax) VALUES('왕눈이', 2000000, 100000);
INSERT INTO pay(name, pay, tax) VALUES('왕눈이', 2000000, 100000);

SELECT name, pay, tax FROM pay;

NAME            PAY        TAX
---------- ---------- ----------
왕눈이        2000000     100000
왕눈이        2000000     100000

ROLLBACK WORK; -- INSERT, UPDATE, DELETE 실행 취소

SELECT name, pay, tax FROM pay;

INSERT INTO pay(name, pay, tax) VALUES('왕눈이', 2000000, 100000);

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
왕눈이        2000000     100000

COMMIT; -- 물리 디스크에 기록함으로 취소 불가능

ROLLBACK WORK;

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
왕눈이        2000000     100000

-- 3. 여러 단계의 복구
UPDATE pay SET name='아로미' WHERE name='왕눈이';

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        2000000     100000

UPDATE pay SET pay = pay + 1000000 WHERE name='아로미';

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        3000000     100000

UPDATE pay SET tax = tax + 200000 WHERE name='아로미';

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        3000000     300000

ROLLBACK;

-- 여러 단계를 거쳤어도 이전에 커밋한 시점으로 돌아감
SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
왕눈이        2000000     100000

-- 4. SAVEPOINT: 특정 위치로 실행 결과를 복구 할 수 있습니다.

SAVEPOINT first;

UPDATE pay SET name='아로미' WHERE name='왕눈이';

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        2000000     100000

SAVEPOINT second;

UPDATE pay SET pay = pay + 1000000 WHERE name='아로미';

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        3000000     100000

SAVEPOINT third;

UPDATE pay SET tax = tax + 200000 WHERE name='아로미';

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        3000000     300000

-- third로 복구
ROLLBACK TO SAVEPOINT third;

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
아로미        3000000     100000

-- second로 복구
ROLLBACK TO SAVEPOINT second;

SELECT name, pay, tax FROM pay;

-- first로 복구
ROLLBACK TO SAVEPOINT first;

SELECT name, pay, tax FROM pay;

NAME              PAY        TAX
---------- ---------- ----------
왕눈이        2000000     100000

-- 5. 읽기 일관성
DELETE FROM pay;
COMMIT;

1) SQL Developer
INSERT INTO pay(name,pay,tax) VALUES('Developer', 2000000, 200000);

2) SQL+(Run SQL Command Line)
SQL> connect kd/1234
Connected.

SQL> SELECT name, pay, tax FROM pay;

no rows selected  <- SQL Developer에서 등록한 레코드가 인식이 안됨. (커밋을 안했기 때문) 

3) SQL Developer
commit;

4) SQL+(Run SQL Command Line)
SQL> SELECT name, pay, tax FROM pay;

NAME                        PAY        TAX
-------------------- ---------- ----------
Developer               2000000     200000

2. 삭제된 테이블 복구
-- Oracle 12C+ 지원
 
DROP TABLE pay PURGE;   -- PURGE: 삭제 후 복구 불가, 완전 삭제
 
SELECT * FROM RECYCLEBIN;   -- RECYCLEBIN : 삭제된 목록, (PURGE로 삭제한 테이블은 안나옴)
 
SELECT * FROM RECYCLEBIN WHERE original_name = 'GALLERY';

-- 복구 가능 삭제
CREATE TABLE pay(
    name varchar(10) NOT NULL,
    pay  number(8)   NOT NULL,
    tax  number(7)   DEFAULT 0
);

INSERT INTO pay(name, pay, tax) VALUES('왕눈이', 2000000, 100000);
INSERT INTO pay(name, pay, tax) VALUES('왕눈이', 2000000, 100000);

SELECT name, pay, tax FROM pay;

DROP TABLE pay;

SELECT * FROM RECYCLEBIN;   -- RECYCLEBIN : 삭제된 목록, (PURGE로 삭제한 테이블은 안나옴)
 
SELECT * FROM RECYCLEBIN WHERE original_name = 'PAY';

FLASHBACK TABLE pay TO BEFORE DROP;     -- FLASHBACK : 테이블 구조 뿐만 아니라 데이터도 복구한다.

SELECT name, pay, tax FROM pay;