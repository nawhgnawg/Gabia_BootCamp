DROP TABLE memo;

CREATE TABLE memo (
    memono      NUMBER(10)    NOT NULL PRIMARY KEY, -- AUTO_INCREMENT 대체
    title       VARCHAR2(100) NOT NULL, -- 제목(*)
    content     CLOB          NOT NULL, -- 메모 내용
    rdate       DATE          NOT NULL,  -- 메모 등록 날짜
    userno      NUMBER(10)    NOT NULL , -- FK
    FOREIGN KEY (userno) REFERENCES bloguser (userno) -- 메모를 등록한 관리자
);

DROP SEQUENCE memo_seq;

CREATE SEQUENCE memo_seq
START WITH 1         -- 시작 번호
INCREMENT BY 1       -- 증가값
MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
CACHE 2              -- 2번은 메모리에서만 계산
NOCYCLE;             -- 다시 1부터 생성되는 것을 방지


-- 등록
INSERT INTO memo (memono, title, content, rdate ,userno)
VALUES (memo_seq.NEXTVAL, '오늘 회의 요약', '회의 내용 정리...', SYSDATE, 1);

INSERT INTO memo (memono, title, content, rdate ,userno)
VALUES (memo_seq.NEXTVAL, '오늘 회의 요약', '회의 내용 정리...', SYSDATE, 1);

commit;

-- 전체 조회
SELECT memono, title, content, rdate, userno
FROM memo;

-- 특정 메모 조회
SELECT memono, title, content, rdate, userno
FROM memo
WHERE memono = 1;

-- 수정
UPDATE memo 
SET title = '변경된 이름', content = '변경된 내용...'
WHERE memono = 1;

-- 삭제
DELETE FROM memo WHERE memono = 1;
