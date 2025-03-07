1) 테이블 생성
CREATE TABLE department(
  no       NUMBER(10)   NOT NULL,
  name   VARCHAR(100)   NOT NULL,
  PRIMARY KEY(no)
);

2) 제약 조건의 확인
-- 시스템에는 모두 대문자로 저장됨으로 WHERE 조건을 대문자로 구성 할 것.
SELECT constraint_name, constraint_type, search_condition
FROM ALL_CONSTRAINTS
WHERE TABLE_NAME='DEPARTMENT'; 

CONSTRAINT_NAME                C SEARCH_CONDITION                                                                
------------------------------ - --------------------------------------------------------------------------------
SYS_C007435                    C "NO" IS NOT NULL                                                                
SYS_C007436                    C "NAME" IS NOT NULL                                                              
SYS_C007437                    P <-- PK 제약 조건                                                                                

2) PK 제약 조건의 삭제
ALTER TABLE department 
DROP CONSTRAINT SYS_C007437;

SELECT constraint_name, constraint_type, search_condition
FROM ALL_CONSTRAINTS
WHERE TABLE_NAME='DEPARTMENT';

CONSTRAINT_NAME                C SEARCH_CONDITION                                                                
------------------------------ - --------------------------------------------------------------------------------
SYS_C007435                    C "NO" IS NOT NULL                                                                
SYS_C007436                    C "NAME" IS NOT NULL                                                        

3) 이름을 지정한 제약 조건의 추가
ALTER TABLE department 
ADD CONSTRAINT department_pk PRIMARY KEY (no);

SELECT constraint_name, constraint_type, search_condition
FROM ALL_CONSTRAINTS
WHERE TABLE_NAME='DEPARTMENT';

CONSTRAINT_NAME                C SEARCH_CONDITION                                                                
------------------------------ - --------------------------------------------------------------------------------
SYS_C007435                    C "NO" IS NOT NULL                                                                
SYS_C007436                    C "NAME" IS NOT NULL                                                              
DEPARTMENT_PK                  P <-- PK 제약 조건                                         

4) FK 테이블 생성
DROP TABLE employee;
CREATE TABLE employee(
  empno   NUMBER(10)      NOT NULL,  -- PK
  name    VARCHAR(100)    NOT NULL,
  no      NUMBER(10)      NOT NULL,  -- FK
  PRIMARY KEY(empno)
);

5) FK 추가, employee 테이블의 no 컬럼은 department 테이블의 no컬럼에 relationship을 설정한다.
   - ORA-02270: no matching unique or primary key for this column-list: PK-FK 연결 설정은 unique, primary key 속성이 선언되어 있어야함. 
ALTER TABLE employee 
ADD CONSTRAINT employee_department_fk 
FOREIGN KEY (no) REFERENCES department(no);
                                 
SELECT constraint_name, constraint_type, search_condition
FROM ALL_CONSTRAINTS
WHERE TABLE_NAME='EMPLOYEE';

CONSTRAINT_NAME                  C   SEARCH_CONDITION                                                                
------------------------------   -   --------------------------------------------------------------------------------
EMPLOYEE_DEPARTMENT_FK           R <-- FK 제약 조건                                                                                 
SYS_C007404                      C   "EMPNO" IS NOT NULL                                                             
SYS_C007405                      C   "NAME" IS NOT NULL                                                              
SYS_C007406                      C   "NO" IS NOT NULL                                                                
SYS_C007407                      P <-- PK 제약 조건           