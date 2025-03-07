- PURGE 테이블 삭제시 휴지통에 버리지 말것, 즉시 삭제, 개발자 버전은 휴지통기능 지원 안함. 
DROP TABLE itpay2 PURGE;
 
CREATE TABLE itpay2(
    payno   NUMBER(7)   NOT NULL,  -- 1 ~ 9999999
    part    VARCHAR(20) NOT NULL,  -- 부서명
    sawon   VARCHAR(10) NOT NULL,  -- 사원명
    age     NUMBER(3)   NOT NULL,  -- 나이, 1 ~ 999
    address VARCHAR(50) NOT NULL,  -- 주소
    month   CHAR(6)     NOT NULL,  -- 급여달, 200805
    gdate   DATE        NOT NULL,  -- 수령일
    bonbong NUMBER(8)   DEFAULT 0, -- 본봉  
    tax     NUMBER(7, 2)   DEFAULT 0, -- 세금, 전체 자리, +-99999.99
    bonus   NUMBER(7)       NULL,  -- 보너스
    family  NUMBER(7)       NULL,  -- 가족 수당
    PRIMARY KEY(payno)
);

1) 기존 레코드 삭제
DELETE FROM itpay2;
 
2) sample용 레코드 추가
INSERT INTO itpay2(payno, part, sawon, age, address,month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '디자인팀', '개발팀', 27, '경기도 성남시', '200801', sysdate, 1530000, 12345.67, 0);

INSERT INTO itpay2(payno, part, sawon, age, address,month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '디자인팀', '개발팀', 27, '경기도', '200801', sysdate, 1530000, 12345.67, 0);
         
INSERT INTO itpay2(payno, part, sawon, age, address,month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '디자인팀', '나길동', 30, '인천시 계양구','200801', sysdate-5, 1940000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address,month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '디자인팀', '나길동', 30, '인천시','200801', sysdate-5, 1940000, 0, 0);
  
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길동', 36, '경기도 부천시','200802', sysdate-1, 4070000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길동', 36, '경기도','200802', sysdate-1, 4070000, 0, 0);
  
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '기획설계팀', '바길동', 40, '서울시 강서구', '200802', sysdate-0, 3840000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '기획설계팀', '바길동', 40, '서울시', '200802', sysdate-0, 3840000, 0, 0);
  
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus) 
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '신길동', 33, '서울시 관악구', '200804', sysdate, 3500000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus) 
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '신길동', 33, '강원도', '200804', sysdate, 3500000, 0, 0);
  
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '가길순', 23, '경기도 고양시', '200804', sysdate, 3200000, 0, 0);
       
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '나길순', 24, '경기도 파주시', '200804', sysdate, 3200000, 0, 0);
 
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '다길순', 25, '경기도 안양시', '200804', sysdate, 2500000, 0, 0);
 
INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '서울시 종로구', '200804', sysdate, 2300000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '충청북도', '200804', sysdate, 2300000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '충청남도', '200804', sysdate, 2300000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '전라북도', '200804', sysdate, 2300000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '전라남도', '200804', sysdate, 2300000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '경상북도', '200804', sysdate, 2300000, 0, 0);

INSERT INTO itpay2(payno, part, sawon, age, address, month, gdate, bonbong, tax, bonus)
VALUES((SELECT NVL(MAX(payno), 0)+1 as payno FROM itpay2), '개발팀', '라길순', 26, '경상남도', '200804', sysdate, 2300000, 0, 0);

SELECT COUNT(*) FROM itpay2;
  COUNT(*)
----------
      1031

-- SQL Developer의경우 F5로 실행
3) index가 없는 address 컬럼의 검색, 0.061, 0.051, 0.061 초
SELECT * FROM itpay2 WHERE address = '서울시 종로구';
  
4) index 생성: 테이블명_컬럼명_idx
-- CREATE INDEX index 이름 ON 테이블명(컬럼명);
CREATE INDEX itpay2_address_idx ON itpay2(address);
 
5) index 사용, 별다른 선언 필요 없음, 0.063, 0.046, 0.061, 0.048 초
SELECT * FROM itpay2 WHERE address = '서울시 종로구';     -- index를 사용하면 속도가 빠르다.

6) index를 사용하지 못하는 사례, 데이터 변형, 0.049, 0.053, 0.050초
-- index를 적용한 address를 함수에 인자로 사용하면 index를 사용하지 못한다.
SELECT * FROM itpay2 WHERE SUBSTR(address, 1, 7) = '서울시 종로구';

7) index 삭제
DROP INDEX itpay2_address_idx;
 
8) 영어로된 이름을 대문자로 변경하여 함수기반 인덱스를 생성한 경우
UPDATE itpay2 SET sawon = 'developer' WHERE sawon = '개발팀';
UPDATE itpay2 SET sawon = 'gagildong' WHERE sawon = '가길순';
UPDATE itpay2 SET sawon = 'nagildong' WHERE sawon = '나길동';
UPDATE itpay2 SET sawon = 'nagilsoon' WHERE sawon = '나길순';
UPDATE itpay2 SET sawon = 'bagildong' WHERE sawon = '바길동';
UPDATE itpay2 SET sawon = 'ragilsoon' WHERE sawon = '라길순';
UPDATE itpay2 SET sawon = 'ragildong' WHERE sawon = '라길동';

SELECT * FROM itpay2 WHERE sawon = 'developer';

CREATE INDEX emp_sawon_idx ON itpay2(UPPER(sawon));

SELECT * FROM itpay2 WHERE sawon = 'developer';