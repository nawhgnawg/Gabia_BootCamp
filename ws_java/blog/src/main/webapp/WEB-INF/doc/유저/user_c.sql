DROP TABLE bloguser;

CREATE TABLE bloguser (
	userno	        NUMBER(10)		NOT NULL    PRIMARY KEY,
	username	    VARCHAR(30)		NOT NULL,
	useremail	    VARCHAR(30)		NOT NULL,
	userpassword    VARCHAR(200)	NOT NULL,
    zipcode         VARCHAR(5)      NULL,        
    address1        VARCHAR(80)     NULL,        
    address2        VARCHAR(50)     NULL,        
    usergrade       NUMBER(5)       DEFAULT 15   NOT NULL,
	rdate	        DATE		    NOT NULL
);

DROP SEQUENCE BLOGUSER_SEQ;

CREATE SEQUENCE BLOGUSER_SEQ
START WITH 1         -- 시작 번호
INCREMENT BY 1       -- 증가값
MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
CACHE 2              -- 2번은 메모리에서만 계산
NOCYCLE;             -- 다시 1부터 생성되는 것을 방지

--> INSERT
-- 회원 관리용 계정, Q/A 용 계정
INSERT INTO bloguser(userno, username, useremail, userpassword, zipcode, address1, address2, usergrade, rdate)
VALUES (bloguser_seq.nextval, '임광환', 'admin', 'fS/kjO+fuEKk06Zl7VYMhg==', '12345', '서울시 종로구', '관철동', 1, sysdate);

INSERT INTO bloguser(userno, username, useremail, userpassword, zipcode, address1, address2, usergrade, rdate)
VALUES(BLOGUSER_SEQ.nextval, '임광환', 'ghlim100@naver.com', 'fS/kjO+fuEKk06Zl7VYMhg==', '00000', '강원도', '홍천군', 15, SYSDATE);


--> SELECT
SELECT userno, username, useremail, userpassword, zipcode, address1, address2, usergrade, rdate
FROM bloguser
ORDER BY userno ASC;

commit;
