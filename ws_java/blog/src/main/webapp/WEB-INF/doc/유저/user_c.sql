DROP TABLE bloguser;

CREATE TABLE bloguser (
	userno	        NUMBER(10)		NOT NULL    PRIMARY KEY,
	username	    VARCHAR(30)		NOT NULL,
	useremail	    VARCHAR(30)		NOT NULL,
	userpassword    VARCHAR(200)	   	NOT NULL,
    usergrade       NUMBER(5)       DEFAULT 1   NOT NULL,
    usersex         VARCHAR(10)     DEFAULT '남자' NOT NULL,
    userage         NUMBER(10)      NOT NULL,
    visible	        CHAR(1)         DEFAULT 'N'	NOT NULL,
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
INSERT INTO bloguser(userno, username, useremail, userpassword, usergrade, usersex, userage, visible, rdate)
VALUES(BLOGUSER_SEQ.nextval, '임광환', 'ghlim100@naver.com', '1234', 2, '남자', 29,'Y', SYSDATE);

--> SELECT
SELECT userno, username, useremail, userpassword, usergrade, visible, rdate 
FROM bloguser
ORDER BY userno ASC;

commit;
