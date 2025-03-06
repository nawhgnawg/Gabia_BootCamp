-- 1) 데이터 준비
/**********************************/
/* Table Name: 카테고리 */
/**********************************/

DROP TABLE category CASCADE CONSTRAINTS;    -- 자식이 있어도 삭제 가능 CASCADE
DROP TABLE category;

CREATE TABLE category(
        categoryno       NUMBER(10)     NOT NULL         PRIMARY KEY,
        name             VARCHAR2(20) NOT NULL,
        rdate            DATE         NOT NULL
);

COMMENT ON TABLE category is '카테고리';
COMMENT ON COLUMN category.categoryno is '카테고리번호';
COMMENT ON COLUMN category.name is '카테고리 이름';
COMMENT ON COLUMN category.rdate is '등록일';

-- SEQUENCE
DROP SEQUENCE category_seq;

CREATE SEQUENCE category_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 999999999 --> NUMBER(10) 대응
  CACHE 2                       -- 2번은 메모리에서만 계산
  NOCYCLE;                     -- 다시 1부터 생성되는 것을 방지
  
-- CREATE
INSERT INTO category(categoryno, name, rdate) VALUES (category_seq.nextval, '퇴마', sysdate);
INSERT INTO category(categoryno, name, rdate) VALUES (category_seq.nextval, '드라마', sysdate);
INSERT INTO category(categoryno, name, rdate) VALUES (category_seq.nextval, 'XMAS', sysdate);
-- INSERT INTO category(categoryno, name, rdate) VALUES (9999999999, '장르 컴토중', sysdate);
commit;

-- SELECT 목록
-- PK 컬럼은 최초 등록시 값이 sequence에의해 고정됨.
SELECT categoryno, name, rdate
FROM category
ORDER BY categoryno ASC;

CATEGORYNO NAME                 RDATE              
---------- -------------------- -------------------
         1 퇴마                  2022-09-19 03:34:29
         2 드라마                2022-09-19 03:34:29
         3 XMAS                  2022-09-19 03:34:29
       
/**********************************/
/* Table Name: 관리자 */
/**********************************/
DROP TABLE manager CASCADE CONSTRAINTS;
DROP TABLE manager;

CREATE TABLE manager(
    managerno         NUMBER(10)     NOT NULL    PRIMARY KEY,
    id                VARCHAR2(20)   NOT NULL,
    mname             VARCHAR(30)    NOT NULL
);

COMMENT ON TABLE manager is '관리자';
COMMENT ON COLUMN manager.managerno is '관리자 번호';
COMMENT ON COLUMN manager.id is '아이디';
COMMENT ON COLUMN manager.mname is '성명';

DROP SEQUENCE manager_seq;

CREATE SEQUENCE manager_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

INSERT INTO manager(managerno, id, mname) VALUES(manager_seq.nextval, 'manager1', '관리자1');
INSERT INTO manager(managerno, id, mname) VALUES(manager_seq.nextval, 'manager2', '관리자2');
INSERT INTO manager(managerno, id, mname) VALUES(manager_seq.nextval, 'manager3', '관리자3');
-- INSERT INTO manager(managerno, id, mname) VALUES(9999999999, 'manager3', '신입사원중 관리자로 결정 검토중');
commit;

SELECT managerno, id, mname FROM manager ORDER BY managerno ASC;

 MANAGERNO ID                   MNAME                         
---------- -------------------- ------------------------------
         1 manager1             관리자1                       
         2 manager2             관리자2                       
         3 manager3             관리자3                     

/**********************************/
-- Table Name: Gallery 
-- FK 컬럼은 다른 테이블에 사용하려는 값이 등록되어 있는지 확인 후 등록된 값만 사용 가능. 
-- FOREIGN KEY (managerno) REFERENCES manager (managerno):
-- managerno 컬럼의 값은 manager 테이블의 managerno 컬럼에 미리 등록된 값만 사용 가능.
/**********************************/
DROP TABLE gallery CASCADE CONSTRAINTS;
DROP TABLE gallery;

CREATE TABLE gallery(
        galleryno                            NUMBER(10)         NOT NULL         PRIMARY KEY,
        managerno                            NUMBER(10)         NOT NULL , -- FK
        categoryno                           NUMBER(10)         NOT NULL , -- FK
        title                                VARCHAR2(30)       NOT NULL,
        rdate                                DATE               NOT NULL,
  FOREIGN KEY (managerno) REFERENCES manager (managerno),
  FOREIGN KEY (categoryno) REFERENCES category (categoryno)
);

COMMENT ON TABLE gallery is 'Gallery';
COMMENT ON COLUMN gallery.galleryno is 'Gallery 번호';
COMMENT ON COLUMN gallery.managerno is '관리자 번호';
COMMENT ON COLUMN gallery.categoryno is '카테고리 번호';
COMMENT ON COLUMN gallery.title is '제목';
COMMENT ON COLUMN gallery.rdate is '등록일';

DROP SEQUENCE gallery_seq;

CREATE SEQUENCE gallery_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- category 테이블에 categoryno 컬럼의 값이 1이고, manager테이블의 managerno가 1이며,
-- gallery 테이블의 title 컬럼의 값이 '검은 사제들'인 레코드 추가
INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 1, 1, '검은 사제들', sysdate);

INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 1, 1, '더 라이트', sysdate);

INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 1, 2, '수리남', sysdate);

INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 1, 2, '이상한 변호사 우영우', sysdate);

INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 2, 3, '눈속의 트리', sysdate);

INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 2, 1, '엑소시스트', sysdate);

commit;

SELECT galleryno, managerno, categoryno, title, rdate
FROM gallery
ORDER BY galleryno ASC;

 GALLERYNO  MANAGERNO CATEGORYNO TITLE                          RDATE              
---------- ---------- ---------- ------------------------------ -------------------
         1          1          1 검은 사제들                    2023-04-27 01:06:42
         2          1          1 더 라이트                      2023-04-27 01:06:42
         3          1          2 수리남                         2023-04-27 01:06:42
         4          1          2 이상한 변호사 우영우           2023-04-27 01:06:42
         5          2          3 눈속의 트리                    2023-04-27 01:06:42
         6          2          1 엑소시스트                     2023-04-27 03:12:10
         
-- 2) Cross Join
-- --------------------------------------------------------------------
-- 정보로서 가치가 없음.
-- 부모(PK) 테이블 레코드 2 건 x 자식(FK) 테이블 레코트 3건 = 6건 출력

-- SQL 오류: ORA-00918: column ambiguously defined: rdate 컬럼은 어느 테이블 소속인지 구분이 안된다.
SELECT categoryno, name, rdate, galleryno, managerno, categoryno, title, rdate
FROM category, gallery
ORDER BY galleryno ASC;

-- 고유한 레코드가 되도록 컬럼의 값이 조합됨.
-- 컬럼이 속하는 테이블명 명시, 테이블을 합치기는하나 join 기준이 없음, 정보로서의 가치가 매우 부족함.
-- MANAGER: 3, GALLERY: 6 -> 18 ?
SELECT category.categoryno, category.name, category.rdate, 
       gallery.galleryno, gallery.managerno, gallery.categoryno, gallery.title, gallery.rdate
FROM category, gallery
ORDER BY galleryno ASC;

-- 테이블 별명의 사용, SQL문 감소
SELECT c.categoryno, c.name, c.rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate
FROM category c, gallery a
ORDER BY galleryno ASC;

-- 컬럼 별명의 사용
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
ORDER BY galleryno ASC;


-- 미국 국가표준 협회(American National Standards Institute, ANSI)
-- ANSI(결과는 동일), 컬럼 별명의 사용, 정보로서의 가치가 매우 부족함.
SELECT c.categoryno, c.name, c.rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate
FROM category c CROSS JOIN gallery a
ORDER BY galleryno ASC;


3) Equal join(INNER join), 가장 흔한 형태, FWGHSRO
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
WHERE c.categoryno = a.categoryno   -- PK = FK 이면 메모리상에서 결합(Join)
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         1 퇴마                 2024-03-27 10:07:24          1          1          1 검은 사제들                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          2          1          1 더 라이트                      2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          3          1          2 수리남                         2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          4          1          2 이상한 변호사 우영우           2024-03-27 10:19:26
         3 XMAS                 2024-03-27 10:07:24          5          2          3 눈속의 트리                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          6          2          1 엑소시스트                     2024-03-27 10:19:26

-- 추가적인 조건의 사용, categoryno가 1번인 레코드만 출력
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
WHERE c.categoryno = a.categoryno AND c.categoryno = 1   -- PK = FK 이면 메모리상에서 결합(Join)
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         1 퇴마                 2024-03-27 10:07:24          1          1          1 검은 사제들                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          2          1          1 더 라이트                      2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          6          2          1 엑소시스트                     2024-03-27 10:19:26

-- ANSI (INNER JOIN ... ON 조건)
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c INNER JOIN gallery a ON c.categoryno = a.categoryno     -- PK = FK 이면 메모리상에서 결합(Join)
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         1 퇴마                 2024-03-27 10:07:24          1          1          1 검은 사제들                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          2          1          1 더 라이트                      2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          3          1          2 수리남                         2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          4          1          2 이상한 변호사 우영우           2024-03-27 10:19:26
         3 XMAS                 2024-03-27 10:07:24          5          2          3 눈속의 트리                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          6          2          1 엑소시스트                     2024-03-27 10:19:26

SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c INNER JOIN gallery a ON c.categoryno = a.categoryno     -- FROM ~ INNER JOIN ~ ON
WHERE c.categoryno = 1
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         1 퇴마                 2024-03-27 10:07:24          1          1          1 검은 사제들                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          2          1          1 더 라이트                      2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24          6          2          1 엑소시스트                     2024-03-27 10:19:26

4) LEFT Outer join
DELETE FROM gallery WHERE categoryno=1;
commit;

SELECT * FROM gallery;

 GALLERYNO  MANAGERNO CATEGORYNO TITLE                          RDATE              
---------- ---------- ---------- ------------------------------ -------------------
         3          1          2 수리남                         2024-03-27 10:19:26
         4          1          2 이상한 변호사 우영우           2024-03-27 10:19:26
         5          2          3 눈속의 트리                    2024-03-27 10:19:26

-- INNER JOIN, gallery에서 categoryno 1번을 사용하지 않음으로 출력 안됨.
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
WHERE c.categoryno = a.categoryno   -- PK = FK 이면 메모리상에서 결합(Join)
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         2 드라마               2025-03-06 02:57:12          3          1          2 수리남                         2025-03-06 02:59:28
         2 드라마               2025-03-06 02:57:12          4          1          2 이상한 변호사 우영우           2025-03-06 02:59:28
         3 XMAS                 2025-03-06 02:57:12          5          2          3 눈속의 트리                    2025-03-06 02:59:28

-- 모든 카테고리를 확인하고 싶다. 사용되지 않는 카테고리 레코드도 모두 출력
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
WHERE c.categoryno = a.categoryno(+)   -- +가 붙은 테이블의 반대편 레코드 모두 출력, 즉 category 테이블 출력
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         2 드라마               2024-03-27 10:07:24          3          1          2 수리남                         2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          4          1          2 이상한 변호사 우영우           2024-03-27 10:19:26
         3 XMAS                 2024-03-27 10:07:24          5          2          3 눈속의 트리                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24       null        null      null null                           null                                                                                                            

-- ANSI
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c LEFT OUTER JOIN gallery a ON c.categoryno = a.categoryno   -- FROM ~ LEFT OUTER JOIN ~ ON ~
ORDER BY galleryno ASC;

5) RIGHT Outer join(자식 테이블에 FK값이 부모테이블에 없는 레코드 출력, 레코드가 있는 경우는 권장하지 않음.), 자식 테이블에만 존재하는 레코드가 있으면 안됨.
예) 시험을 응시한 점수는 있으나 누구 점수인지 모르는 경우
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
WHERE c.categoryno(+) = a.categoryno   -- +가 붙은 테이블의 반대편 레코드 모두 출력, 즉 category 테이블 출력
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         2 드라마               2025-03-06 02:57:12          3          1          2 수리남                         2025-03-06 02:59:28
         2 드라마               2025-03-06 02:57:12          4          1          2 이상한 변호사 우영우           2025-03-06 02:59:28
         3 XMAS                 2025-03-06 02:57:12          5          2          3 눈속의 트리                    2025-03-06 02:59:28

-- ANSI
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c RIGHT OUTER JOIN gallery a ON c.categoryno = a.categoryno   -- FROM ~ RIGHT OUTER JOIN ~ ON ~
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         2 드라마               2024-03-27 10:07:24          3          1          2 수리남                         2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          4          1          2 이상한 변호사 우영우           2024-03-27 10:19:26
         3 XMAS                 2024-03-27 10:07:24          5          2          3 눈속의 트리                    2024-03-27 10:19:26

6) FULL Outer join
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c, gallery a
WHERE c.categoryno(+) = a.categoryno(+)   -- X, error
ORDER BY galleryno ASC;

-- ANSI
SELECT c.categoryno, c.name, c.rdate as c_rdate, 
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate as a_rdate
FROM category c FULL OUTER JOIN gallery a ON c.categoryno = a.categoryno   -- FROM ~ RIGHT OUTER JOIN ~ ON ~
ORDER BY galleryno ASC;

CATEGORYNO NAME                 C_RDATE              GALLERYNO  MANAGERNO CATEGORYNO TITLE                          A_RDATE            
---------- -------------------- ------------------- ---------- ---------- ---------- ------------------------------ -------------------
         2 드라마               2024-03-27 10:07:24          3          1          2 수리남                         2024-03-27 10:19:26
         2 드라마               2024-03-27 10:07:24          4          1          2 이상한 변호사 우영우           2024-03-27 10:19:26
         3 XMAS                 2024-03-27 10:07:24          5          2          3 눈속의 트리                    2024-03-27 10:19:26
         1 퇴마                 2024-03-27 10:07:24      null        null      null null                           null                                                                           


7) 테이블 3개 join
-- WHERE 조건을 chain 식으로 AND 연산자를 이용하여 연결
SELECT c.categoryno, c.name,  
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate,
       m.mname
FROM category c, gallery a, manager m
WHERE c.categoryno = a.categoryno AND a.managerno = m.managerno
ORDER BY galleryno ASC;

CATEGORYNO NAME                  GALLERYNO  MANAGERNO CATEGORYNO TITLE                          RDATE               MNAME                         
---------- -------------------- ---------- ---------- ---------- ------------------------------ ------------------- ------------------------------
         2 드라마                        3          1          2 수리남                         2024-03-27 10:19:26 관리자1                       
         2 드라마                        4          1          2 이상한 변호사 우영우             2024-03-27 10:19:26 관리자1                       
         3 XMAS                          5          2          3 눈속의 트리                    2024-03-27 10:19:26 관리자2           

-- 추가적인 조건의 명시
SELECT c.categoryno, c.name,  
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate,
       m.mname
FROM category c, gallery a, manager m
WHERE (c.categoryno = a.categoryno AND a.managerno = m.managerno) AND a.title LIKE '%수리남%'
ORDER BY galleryno ASC;

CATEGORYNO NAME                  GALLERYNO  MANAGERNO CATEGORYNO TITLE                          RDATE               MNAME                         
---------- -------------------- ---------- ---------- ---------- ------------------------------ ------------------- ------------------------------
         2 드라마                        3          1          2  수리남                          2025-03-06 02:59:28 관리자1                       

-- ANSI
SELECT c.categoryno, c.name,  
       a.galleryno, a.managerno, a.categoryno, a.title, a.rdate,
       m.mname
FROM category c INNER JOIN gallery a ON c.categoryno = a.categoryno INNER JOIN manager m ON a.managerno = m.managerno
WHERE a.title LIKE '%수리남%'
ORDER BY galleryno ASC;

CATEGORYNO NAME                  GALLERYNO  MANAGERNO CATEGORYNO TITLE                          RDATE               MNAME                         
---------- -------------------- ---------- ---------- ---------- ------------------------------ ------------------- ------------------------------
         2 드라마                        3          1          2 수리남                         2024-03-27 10:19:26 관리자1                    
