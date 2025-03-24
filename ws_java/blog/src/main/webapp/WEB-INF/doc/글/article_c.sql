DROP TABLE article;

CREATE TABLE article (
	articleno	    NUMBER(10)		NOT NULL    PRIMARY KEY,
	userenickname   VARCHAR(30)		NOT NULL,
	articlename	    VARCHAR(30)		NOT NULL,
	articlecontent  NUMBER(7)	    DEFAULT 0	NOT NULL,
    articlepassword VARCHAR(30)     NOT NULL,
	rdate	    DATE		    NOT NULL
);