select version(), current_date(), now() from dual;

-- 수학 함수도 사용 할 수 있다. (사칙 연산도 가능)

SELECT SIN(PI() / 4), 1+2 * 3-4/5 FROM DUAL;

-- 대소문자 구분 안한다.
select version(), current_date(), now() from Dual;

-- tabel 생성 :DDL
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gerder char(1), -- 성별
    birth date, -- 생일
    death date -- 죽은날
);

-- schema 확인
show tables;
desc pet;

-- schema 삭제 DDL
drop table pet;
show tables;

-- insert : DML (C)
insert into pet value('성탄이', '안대혁', 'dog', 'm','2019-12-25',null);

-- select: DML (R)
select * from pet;

-- update: DML(U)
UPDATE pet 
SET 
    name = '성타니'
WHERE
    name = '성탄이2';

-- delete: DML (D)
delete from pet where name = '성타니';

-- load data
LOAD DATA LOCAL INFILE 'C:/Users/SDH/Documents/Poscodx/poscodx/mariadb-practices/pet.txt' into table pet;
update pet set death = null;

-- select data
-- 문) bowser의 주인의 이름은?
select owner from pet where name = 'bowser';

-- 문2) 1998 이 후에 태어난 애들은?
select * from pet where birth >= '1998-01-01';

-- 문3) 종이 뱀이거나 새인 애들은?
select * from pet where species = 'snake' or species = 'bird';

-- 예4) order by
select name, birth from pet order by birth asc;

-- 예5) order by ~ desc
select name, birth from pet order by birth desc;

-- 예6) where 절에 null 다루기
select name, birth death from pet where death is null;
select name, birth death from pet where death is not null;

-- 예7) like 검색 (패턴검색)
select name from pet where name like 'b%';
select name from pet where name like '%fy';
select name from pet where name like '%w%';
select name from pet where name like '____';
select name from pet where name like 'b_____';

-- 예8 ) 집계  count avg sum max min
select count(*) from pet;
select name, max(birth) from pet;
