-- 1) 데이터 준비
/**********************************/
/* Table Name: 카테고리 */
/**********************************/

DROP TABLE category CASCADE CONSTRAINTS;
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
-- 등록되는 값을 사전에 검증된 데이터만 사용 가능하도록 제약
-- 예) 학번, 주민등록번호, 상품 번호, 주문 번호, 뉴스 기사 번호, 전동차 번호, 생산되는 제품의 번호...
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
         
INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 10, 20, '엑소시스트', sysdate);
--ORA-02291: integrity constraint (KD.SYS_C007148) violated - parent key not found
-- manager는 10번이 없음, categoryno는 20번이 없음.

DROP TABLE manager;
-- ORA-02449: unique/primary keys in table referenced by foreign keys
-- FOREIGN KEY로 연결(참조)되어 있는 경우 부모 테이블은 삭제 할 수 없다. 

DELETE FROM manager WHERE managerno=1;
-- ORA-02292: integrity constraint (KD.SYS_C007147) violated - child record found
-- 부모 테이블의 PK 컬럼의 값이 자식 테이블에서 사용되고 있으면 해당하는 부모 레코드 삭제 불가


-- FK가 PK로 있는 곳이 부모 테이블(그룹/코드), FK가 사용되는 곳이 자식 테이블(PK, FK 같이 존재함, 데이터)         
-- manager 테이블에서 managerno가 1번인 레코드 삭제 절차는?
DELETE FROM gallery WHERE managerno = 1;    -- managerno가 쓰이고 있는 곳(자식)을 먼저 지움
DELETE FROM manager WHERE managerno = 1;

-- category 테이블에서 gategoryno가 1번인 레코드 삭제 절차는?
DELETE FROM gallery WHERE categoryno = 1;    -- categoryno가 쓰이고 있는 곳(자식)을 먼저 지움
DELETE FROM category WHERE categoryno = 1;

-- gallery 테이블에 레코드 추가 절차는?
-- 추가할떄는 부모 먼저, 삭제할 떄는 자식 먼저
INSERT INTO manager(managerno, id, mname) VALUES(manager_seq.nextval, 'manager1', '관리자1');     -- manager 추가
INSERT INTO category(categoryno, name, rdate) VALUES (category_seq.nextval, '퇴마', sysdate);     -- category 추가
INSERT INTO gallery(galleryno, managerno, categoryno, title, rdate)
VALUES(gallery_seq.nextval, 4, 4, '검은 사제들', sysdate);
SELECT * FROM gallery;
-- manager와 category 테이블에 사용하려는 PK가 있어야함.
-- 만약 사용하려는 PK가 없으면 manager와 category 테이블에 먼저 insert를 실행할 것.

commit;

-- managerno가 4번인 관리자의 성명은?
SELECT managerno, id, mname FROM manager WHERE managerno = 4;

-- categoryno가 4번인 카테고리의 이름은?
SELECT categoryno, name, rdate FROM category WHERE categoryno = 4;

