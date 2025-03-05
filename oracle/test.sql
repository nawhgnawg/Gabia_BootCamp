DROP TABLE test;

CREATE TABLE test(
    testno NUMBER(5)   NOT NULL, -- -99999 ~ +99999
    mname  VARCHAR(20) NOT NULL, -- 한글 3 byte, 영숫자 1 byte 
    funct  VARCHAR(50) NOT NULL, -- 주요 기술 분야  
    PRIMARY KEY (testno)         -- 중복 안됨, 고유한 값만 가능  
); 

INSERT INTO test(testno, mname, funct) 
VALUES(1, '왕눈이', 'JAVA/Thymeleaf/Spring Boot'); 

INSERT INTO test(testno, mname, funct) 
VALUES(2, '아로미', 'JAVA/Thymeleaf/Spring Boot/React'); 

INSERT INTO test(testno, mname, funct) 
VALUES(3, '투투', 'JAVA/Thymeleaf/Spring Boot/React/HTML5'); 

-- 오름차순
SELECT testno, mname, funct FROM test ORDER BY testno ASC; 

-- 내림차순
SELECT testno, mname, funct FROM test ORDER BY testno DESC; 

-- VARCHAR 타입의 저장 가능 글자수 확인  
INSERT INTO test(testno, mname, funct) 
VALUES(4, '오늘은 금요일', 'JAVA/Thymeleaf/Spring Boot/React/HTML5');

-- 한글: 3 byte, 그외의 문자는 1 byte (MySQL: VARCHAR(20) 이면 한글등 모든 문자 20자 저장가능, 오라클은 6자 가능)
-- INSERT INTO test(testno, mname, funct) 
-- VALUES(5, '오늘은 금요일 FRIDAY', 'JAVA/Thymeleaf/Spring Boot/React/HTML5');
-- ORA-12899: value too large for column "KD"."TEST"."MNAME" (actual: 26, maximum: 20)

SELECT testno, mname, funct 
FROM test 
ORDER BY testno ASC; 

DELETE FROM test 
WHERE testno=4;

SELECT * FROM test;

COMMIT;

