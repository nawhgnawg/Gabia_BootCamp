-- 첫번째 레코드 등록시 생성될 PK 값을 FK 값으로 사용해야 최초 등록 가능. 
-- 두번째부터는 FK값으로 생성될 PK를 지정.
           
DROP TABLE position;

-- REFERENCES 테이블이 자기 자신임.
CREATE TABLE position(
  positionno NUMBER(5) NOT NULL,
  name       VARCHAR(20) NOT NULL, 
  employee  VARCHAR(20) NOT NULL,
  officer     NUMBER(5) NOT NULL,       -- self join에서는 동일한 컬럼명 사용 불가하여 officer로 지정함.
  PRIMARY KEY(positionno),
  FOREIGN KEY(officer) REFERENCES position(positionno)
);

COMMENT ON TABLE position is '직책';
COMMENT ON COLUMN position.positionno is '직책 번호';
COMMENT ON COLUMN position.name is '직책 이름';
COMMENT ON COLUMN position.employee is '사원명';
COMMENT ON COLUMN position.officer is '상급 직책 사원';

-- positionno 컬럼의 최대값
SELECT MAX(positionno) FROM position;

MAX(POSITIONNO)
---------------
null

-- 컬럼 별명
SELECT MAX(positionno) AS positionno FROM position;

POSITIONNO
----------
null

-- null 관련 연산은 null 값
SELECT MAX(positionno) + 1 AS positionno FROM position;

POSITIONNO
----------
null

-- NVL: null -> 0
SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position;
POSITIONNO
----------
         1

-- FOREIGN KEY(officer) REFERENCES position(positionno)
-- 최초 레코드 등록시 자기 자신을 참조 할수 있음, FK 지정 가능
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '대표이사', '아로미', 1);

-- 목록
SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1

-- 자기 자신 참고 가능
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '이사', '피어스', 2);

-- 논리적 에러 발생, 이사의 상관은 대표 이사임
SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                       아로미                                  1
         2 이사                           피어스                                  2
         
DELETE FROM position WHERE positionno=2;

commit;

-- 이사의 상관은 대표이사
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '이사', '피어스', 1);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         
-- 상무(직원)의 상관은 이사(주식 10% 이상 취득)
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '상무', '가길동', 2);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         
-- 부장의 상관은 상무
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '부장', '나길순', 3);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

OSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3

-- 차장의 상관은 부장
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '차장', '다길동', 4);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3
         5 차장                           다길동                                  4

-- 과장의 상관은 차장
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '과장', '라길동', 5);

INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '과장', '휴잭맨', 5);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3
         5 차장                           다길동                                  4
         6 과장                           라길동                                  5
         7 과장                           휴잭맨                                  5
         
-- 대리의 상관은 과장
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '대리', '홍길동', 6);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3
         5 차장                           다길동                                  4
         6 과장                           라길동                                  5
         7 과장                           휴잭맨                                  5
         8 대리                           홍길동                                  6
         
-- 대리의 상관은 과장
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '대리', '홍길순', 7);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3
         5 차장                           다길동                                  4
         6 과장                           라길동                                  5
         7 과장                           휴잭맨                                  5
         8 대리                           홍길동                                  6
         9 대리                           홍길순                                  7
         
-- 주임의 상관은 대리
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '주임', '강하늘', 9);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3
         5 차장                           다길동                                  4
         6 과장                           라길동                                  5
         7 과장                           휴잭맨                                  5
         8 대리                           홍길동                                  6
         9 대리                           홍길순                                  7
        10 주임                           강하늘                                  9

-- 사원의 상관은 주임
INSERT INTO position(positionno, name, employee, officer) 
VALUES((SELECT NVL(MAX(positionno), 0) + 1 AS positionno FROM position), '사원', '공효진', 10);

SELECT positionno, name, employee, officer FROM position ORDER BY positionno ASC;

POSITIONNO NAME                           EMPLOYEE                          OFFICER
---------- ------------------------------ ------------------------------ ----------
         1 대표 이사                      아로미                                  1
         2 이사                           피어스                                  1
         3 상무                           가길동                                  2
         4 부장                           나길순                                  3
         5 차장                           다길동                                  4
         6 과장                           라길동                                  5
         7 과장                           휴잭맨                                  5
         8 대리                           홍길동                                  6
         9 대리                           홍길순                                  7
        10 주임                           강하늘                                  9
        11 사원                           공효진                                 10
        
-- Self join


POSITIONNO NAME                           EMPLOYEE                          OFFICER NAME                           EMPLOYEE                      
---------- ------------------------------ ------------------------------ ---------- ------------------------------ ------------------------------
         1 대표 이사                      아로미                                   1   대표 이사                      아로미                        
         2 이사                           피어스                                  1   대표 이사                      아로미                        
         3 상무                           가길동                                  2   이사                           피어스                        
         4 부장                           나길순                                  3   상무                           가길동                        
         5 차장                           다길동                                  4   부장                           나길순                        
         6 과장                           라길동                                  5   차장                           다길동                        
         7 과장                           휴잭맨                                  5   차장                           다길동                        
         8 대리                           홍길동                                  6   과장                           라길동                        
         9 대리                           홍길순                                  7   과장                           휴잭맨                        
        10 주임                           강하늘                                  9   대리                           홍길순                        
        11 사원                           공효진                                 10   주임                           강하늘                        
    
-- ANSI

POSITIONNO NAME                           EMPLOYEE                          OFFICER NAME                           EMPLOYEE                      
---------- ------------------------------ ------------------------------ ---------- ------------------------------ ------------------------------
         1 대표 이사                      아로미                                  1 대표 이사                      아로미                        
         2 이사                           피어스                                  1 대표 이사                      아로미                        
         3 상무                           가길동                                  2 이사                           피어스                        
         4 부장                           나길순                                  3 상무                           가길동                        
         5 차장                           다길동                                  4 부장                           나길순                        
         6 과장                           라길동                                  5 차장                           다길동                        
         7 과장                           휴잭맨                                  5 차장                           다길동                        
         8 대리                           홍길동                                  6 과장                           라길동                        
         9 대리                           홍길순                                  7 과장                           휴잭맨                        
        10 주임                           강하늘                                  9 대리                           홍길순                        
        11 사원                           공효진                                 10 주임                           강하늘    
