DROP TABLE cate;

CREATE TABLE cate (
	cateno	NUMBER(10)		NOT NULL    PRIMARY KEY,
	grp	    VARCHAR(30)		NOT NULL,
	name	VARCHAR(30)		NOT NULL,
	cnt	    NUMBER(7)	    DEFAULT 0	NOT NULL,
	seqno	NUMBER(5)   	DEFAULT 1	NOT NULL,
	visible	CHAR(1)	        DEFAULT 'N'	NOT NULL,
	rdate	DATE		    NOT NULL
);

-- COMMENT ON COLUMN cate IS '카테고리';

COMMENT ON COLUMN cate.cateno IS '카테고리 번호';

COMMENT ON COLUMN cate.grp IS '그룹 이름';

COMMENT ON COLUMN cate.name IS '카테고리 이름';

COMMENT ON COLUMN cate.cnt IS '등록 자료수';

COMMENT ON COLUMN cate.seqno IS '출력 순서';

COMMENT ON COLUMN cate.visible IS '출력 모드';

COMMENT ON COLUMN cate.rdate IS '등록일';

DROP SEQUENCE CATE_SEQ;

CREATE SEQUENCE CATE_SEQ
START WITH 1         -- 시작 번호
INCREMENT BY 1       -- 증가값
MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
CACHE 2              -- 2번은 메모리에서만 계산
NOCYCLE;             -- 다시 1부터 생성되는 것을 방지

--> CREATE 
INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate)
VALUES(CATE_SEQ.nextval, '드라마', 'K드라마', 0, 1, 'Y', SYSDATE);

INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate)
VALUES(CATE_SEQ.nextval, '드라마', '미드', 0, 1, 'Y', SYSDATE);

INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate)
VALUES(CATE_SEQ.nextval, '영화', 'SF', 0, 1, 'Y', SYSDATE);

INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate)
VALUES(CATE_SEQ.nextval, '영화', '드라마', 0, 1, 'Y', SYSDATE);

INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate)
VALUES(CATE_SEQ.nextval, '개발', 'JAVA', 0, 1, 'Y', SYSDATE);

SELECT cateno, grp, name, cnt, seqno, visible, rdate 
FROM cate 
ORDER BY cateno ASC;

--> SELECT 조회
SELECT cateno, grp, name, cnt, seqno, visible, rdate
FROM cate
WHERE cateno = 1;

--> UPDATE
UPDATE cate SET grp='여행', name='국내', rdate=SYSDATE WHERE cateno=5;

--> DELETE
DELETE FROM cate WHERE cateno=5;

SELECT cateno, grp, name, cnt, seqno, visible, rdate
FROM cate
WHERE cateno=5;

--> COUNT(*)
SELECT COUNT(*) AS cnt FROM cate;

COMMIT;

DELETE FROM cate;
COMMIT;

-- 데이터 준비
INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate) 
VALUES(cate_seq.nextval, '여행', '--', 0, 0, 'Y', sysdate);

INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate) 
VALUES(cate_seq.nextval, '까페', '--', 0, 0, 'Y', sysdate);

INSERT INTO cate(cateno, grp, name, cnt, seqno, visible, rdate) 
VALUES(cate_seq.nextval, '영화', '--', 0, 0, 'Y', sysdate);

-- 목록 변경됨
SELECT cateno, grp, name, cnt, seqno, visible, rdate FROM cate ORDER BY seqno ASC;
    CATENO GRP                            NAME                                  CNT      SEQNO V RDATE              
---------- ------------------------------ ------------------------------ ---------- ---------- - -------------------
        22 여행                            --                                      0          0 Y 2025-03-26 01:15:50
        24 영화                            --                                      0          0 Y 2025-03-26 01:15:50
        23 까페                            --                                      0          0 Y 2025-03-26 01:15:50
        
-- 출력 우선순위 낮춤
UPDATE cate SET seqno=seqno+1 WHERE cateno=22;
UPDATE cate SET seqno=seqno+1 WHERE cateno=24;
SELECT cateno, grp, name, cnt, seqno, visible, rdate FROM cate ORDER BY seqno ASC;
    CATENO GRP                            NAME                                  CNT      SEQNO V RDATE              
---------- ------------------------------ ------------------------------ ---------- ---------- - -------------------
        23 까페                            --                                      0          0 Y 2025-03-26 01:15:50
        24 영화                            --                                      0          1 Y 2025-03-26 01:15:50
        22 여행                            --                                      0          1 Y 2025-03-26 01:15:50

UPDATE cate SET seqno=seqno+1 WHERE cateno=22;
UPDATE cate SET seqno=seqno+1 WHERE cateno=22;
SELECT cateno, grp, name, cnt, seqno, visible, rdate FROM cate ORDER BY seqno ASC;
    CATENO GRP                            NAME                                  CNT      SEQNO V RDATE              
---------- ------------------------------ ------------------------------ ---------- ---------- - -------------------
        23 까페                            --                                      0          0 Y 2025-03-26 01:15:50
        24 영화                            --                                      0          1 Y 2025-03-26 01:15:50
        22 여행                            --                                      0          3 Y 2025-03-26 01:15:50
         
-- 테스트를 위하여 우선순의 2단계 낮춤
UPDATE cate SET seqno=seqno+2;
SELECT cateno, grp, name, cnt, seqno, visible, rdate FROM cate ORDER BY seqno ASC;
    CATENO GRP                            NAME                                  CNT      SEQNO V RDATE              
---------- ------------------------------ ------------------------------ ---------- ---------- - -------------------
        23 까페                            --                                      0          2 Y 2025-03-26 01:15:50
        24 영화                            --                                      0          3 Y 2025-03-26 01:15:50
        22 여행                            --                                      0          5 Y 2025-03-26 01:15:50
         
-- 출력 우선순위 높임
UPDATE cate SET seqno=seqno-1 WHERE cateno=22;
SELECT cateno, grp, name, cnt, seqno, visible, rdate FROM cate ORDER BY seqno ASC;
    CATENO GRP                            NAME                                  CNT      SEQNO V RDATE              
---------- ------------------------------ ------------------------------ ---------- ---------- - -------------------
        23 까페                            --                                      0          2 Y 2025-03-26 01:15:50
        24 영화                            --                                      0          3 Y 2025-03-26 01:15:50
        22 여행                            --                                      0          4 Y 2025-03-26 01:15:50

COMMIT;
