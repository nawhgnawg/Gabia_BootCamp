-- /ws_java/oop/src/jdbc/movie.sql

create table movie (		-- movie : 영화
	movieno INT 		not null auto_increment,	-- 영화 번호 
    title  VARCHAR(20) 	not null,					-- 영화 제목
    score  FLOAT		not null,					-- 평점
    actor  VARCHAR(50)	not null, 					-- 출연
    rdate  DATETIME 	not null,					-- 등록일
    primary Key(movieno)	-- 중복 불가능
);

-- 등록 (Create)
insert into movie(title, score, actor, rdate) values("업사이드(영화, 2017)", 7.5, "케빈 하트", now());
insert into movie(title, score, actor, rdate) values("사일런스(영화, 2019)", 8.5, "스탠리 투치", now());
insert into movie(title, score, actor, rdate) values("인터스텔라(영화, 2014)", 10.0, "매튜 맥커너히", now());

-- 목록 (Read)
select movieno, title, score, actor, rdate from movie;
select movieno, title, score, actor, rdate from movie order by movieno ASC;

-- 조회 (Read)
select movieno, title, grade, actor, rdate from movie where movieno = 1;
select * from movie;


-- 수정 (Update)
update movie set title = '그 여자 작사 그 남자 작곡', score = 10.0, actor = "휴 그랜트" 
where movieno = 1;

-- 삭제 (Delete)
delete from movie where movieno = 3;

-- Count
select count(*) as cnt from movie;
select count(movieno) as cnt from movie;

-- 컬럼 변경 Alter
alter table movie change column score grade float;
-- ALTER TABLE movie RENAME COLUMN actor TO abc1; -- X
-- ALTER TABLE movie CHANGE actor abc1; -- X
-- ALTER TABLE movie actor abc1; -- X
