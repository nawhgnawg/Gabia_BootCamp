DROP TABLE category;

CREATE TABLE category (
	categoryno	    NUMBER(10)		NOT NULL    PRIMARY KEY,
	categorygrp	    VARCHAR(30)		NOT NULL,
	categoryname	VARCHAR(30)		NOT NULL,
    cnt	            NUMBER(7)	    DEFAULT 0	NOT NULL,
	sortno	        NUMBER(5)   	DEFAULT 1	NOT NULL,
	visible	        CHAR(1)	        DEFAULT 'N'	NOT NULL,
	rdate	        DATE		    NOT NULL
);

DROP SEQUENCE CATEGORY_SEQ;

CREATE SEQUENCE CATEGORY_SEQ
START WITH 1         -- 시작 번호
INCREMENT BY 1       -- 증가값
MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
CACHE 2              -- 2번은 메모리에서만 계산
NOCYCLE;             -- 다시 1부터 생성되는 것을 방지

INSERT INTO category(categoryno, categorygrp, categoryname, sortno, visible, rdate)
VALUES(CATEGORY_SEQ.nextval, '한식', '--', 1, 'Y', SYSDATE);

INSERT INTO category(categoryno, categorygrp, categoryname, sortno, visible, rdate)
VALUES(CATEGORY_SEQ.nextval, '일식', '--', 101, 'Y', SYSDATE);

INSERT INTO category(categoryno, categorygrp, categoryname, sortno, visible, rdate)
VALUES(CATEGORY_SEQ.nextval, '중식', '--', 201, 'Y', SYSDATE);

SELECT * FROM category;

COMMIT;
