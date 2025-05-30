/**********************************/
/* Table Name: 댓글 */
/**********************************/
DROP TABLE reply;

CREATE TABLE reply(
        replyno                              NUMBER(10)         NOT NULL     PRIMARY KEY,
        contentsno                           NUMBER(10)         NOT NULL,
        userno                               NUMBER(6)          NOT NULL,
        content                              VARCHAR2(1000)     NOT NULL,
        rdate                                DATE               NOT NULL,
  FOREIGN KEY (contentsno) REFERENCES contents (contentsno),
  FOREIGN KEY (userno) REFERENCES bloguser (userno)
);

DROP SEQUENCE reply_seq;

SELECT
    s.sid,
    s.serial#,
    s.username,
    s.program,
    o.object_name,
    o.object_type,
    l.locked_mode
FROM
    v$locked_object l
JOIN dba_objects o ON l.object_id = o.object_id
JOIN v$session s ON l.session_id = s.sid;


CREATE SEQUENCE reply_seq
  START WITH 1              -- 시작 번호
  INCREMENT BY 1          -- 증가값
  MAXVALUE 9999999999 -- 최대값: 9999999999
  CACHE 2                     -- 2번은 메모리에서만 계산
  NOCYCLE;                   -- 다시 1부터 생성되는 것을 방지


1) 등록
INSERT INTO reply(replyno, contentsno, userno, content, rdate)
VALUES(reply_seq.nextval, 14, 1, '댓글1', sysdate);
INSERT INTO reply(replyno, contentsno, userno, content, rdate)
VALUES(reply_seq.nextval, 14, 1, '댓글2', sysdate);
INSERT INTO reply(replyno, contentsno, userno, content, rdate)
VALUES(reply_seq.nextval, 14, 1, '댓글3', sysdate);

2) 전체 목록
SELECT replyno, contentsno, userno, content, rdate
FROM reply
ORDER BY replyno DESC;

3) reply + member join 목록
SELECT m.id,
       r.replyno, r.contentsno, r.memberno, r.content, r.rdate
FROM member m,  reply r
WHERE m.memberno = r.memberno
ORDER BY r.replyno DESC;

4) reply + member join + 특정 contentsno 별 목록
SELECT m.id,
       r.replyno, r.contentsno, r.memberno, r.content, r.rdate
FROM member m,  reply r
WHERE (m.memberno = r.memberno) AND r.contentsno=3
ORDER BY r.replyno DESC;

 ID    REPLYNO CONTENTSNO MEMBERNO CONTENT                                                                                                                                                                         PASSWD RDATE
 ----- ------- ---------- -------- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- ------ ---------------------
 user1       3          1        1 댓글 3                                                                                                                                                                            123    2019-12-18 16:46:43.0
 user1       2          1        1 댓글 2                                                                                                                                                                            123    2019-12-18 16:46:39.0
 user1       1          1        1 댓글 1

5) 조회
SELECT replyno, contentsno, memberno, content, rdate
FROM reply
WHERE replyno = 1;

6) 수정
UPDATE reply
SET content='댓글 수정'
WHERE replyno=1;

7) 삭제
-- 삭제
DELETE FROM reply
WHERE replyno=1;

--------------------------------------------------------------------------
FK contentsno, memberno 관련
--------------------------------------------------------------------------

1) FK contentsno에 해당하는 댓글 수 확인
SELECT COUNT(*) as cnt
FROM reply
WHERE contentsno=1;

 CNT
 ---
   1

2) FK contentsno에 해당하는 댓글 삭제
DELETE FROM reply
WHERE contentsno=1;

3) FK memberno에 해당하는 댓글 수 확인
SELECT COUNT(*) as cnt
FROM reply
WHERE memberno=1;

 CNT
 ---
   1

4) FK memberno에 해당하는 댓글 삭제
DELETE FROM reply
WHERE memberno=1;

ROLLBACK;

ALTER SYSTEM KILL SESSION '52,15210';
commit;