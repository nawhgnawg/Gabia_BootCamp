-- /ws_java/oop/src/news.sql

show databases;
use javadb;

-- 테이블 생성
create table news(		-- news : 테이블명, Excel의 sheet
	newsno INT,			-- 컬럼 
    title VARCHAR(20),	-- 한글/영어/숫자 구분 없이 20자 저장, 사용하고 남은 매모리 재사용
    rdate DATETIME
);

-- 생성된 테이블 목록
show tables;

-- 테이블 삭제
drop table news;

-- 현재 날짜/시간 (yyyy-mm-dd hh:mm:ss)
select NOW();

-- 등록
insert into news(newsno, title, rdate) values(1, '케이뱅크 LLM 서비스 도입', now());
insert into news(newsno, title, rdate) values(2, 'OpenAI API 가격 낮춰', now());
insert into news(newsno, title, rdate) values(3, '알고리즘, 데이터분석 중요해져...', now());

select * from news;

-- 레코드를 고유하게 구분할 방법이 필요 : Primary Key
create table news(		-- news : 테이블명, Excel의 sheet
	newsno INT,			-- 컬럼 
    title VARCHAR(20),	-- 한글/영어/숫자 구분 없이 20자 저장, 사용하고 남은 매모리 재사용
    rdate DATETIME,
    primary Key(newsno)	-- 중복 불가능
);

insert into news(newsno, title, rdate) values(1, '케이뱅크 LLM 서비스 도입', now());
insert into news(newsno, title, rdate) values(2, 'OpenAI API 가격 낮춰', now());
insert into news(newsno, title, rdate) values(3, '알고리즘, 데이터분석 중요해져...', now());

select * from news;


-- 일련번호의 자동 생성
create table news(		-- news : 테이블명, Excel의 sheet
	newsno INT auto_increment,		-- 컬럼 
    title VARCHAR(20),	-- 한글/영어/숫자 구분 없이 20자 저장, 사용하고 남은 매모리 재사용
    rdate DATETIME,
    primary Key(newsno)	-- 중복 불가능
);

insert into news(title, rdate) values("케이뱅크 'LLM' 서비스 도입", now());
insert into news(title, rdate) values('OpenAI "API" 가격 낮춰', now());
insert into news(title, rdate) values('알고리즘, 데이터분석 중요해져...', now());

select * from news;

-- 목록
select newsno, title from news;
select newsno, title, rdate from news order by newsno desc;
select newsno, title, rdate from news order by newsno asc;
select newsno, title, rdate from news order by title;

-- 조회
select newsno, title, rdate from news where newsno = 1;

-- 수정
update news set title = 'SQLD 준비' where newsno = 1;
update news set title = '정보처리 산업기사 준비' where newsno = 2;

-- 삭제
delete from news where newsno = 1;

select * from news;

-- NULL, NOT NULL
insert into news(title) values('창의적 생각 중요해져.');

create table news(									-- news : 테이블명, Excel의 sheet
	newsno INT 			not null auto_increment,	-- 컬럼 
    title  VARCHAR(20) 	not null,					-- 한글/영어/숫자 구분 없이 20자 저장, 사용하고 남은 매모리 재사용
    rdate  DATETIME 	not null,
    primary Key(newsno)								-- 중복 불가능
);

-- null 값을 가질 수 없음 : insert into news(title) values('창의적 생각 중요해져.');	--
insert into news(title, rdate) values("Python 중요해져.", now());

select * from news;