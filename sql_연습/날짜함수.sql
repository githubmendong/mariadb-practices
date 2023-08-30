--
-- 날자 함수
--

-- curdate(), current_date
select curdate(), current_date from dual;

-- now() vs sysdate()
select now(), sysdate() from dual;
select now(), sleep(2), now() from dual;
select now(), sleep(2), sysdate() from dual;

-- date_format
-- 2023년 8월30일 11시 37분 57초
select date_format(now(), '%y년 %m월 %d일 %h시 %i분 %s초') from dual;

-- period_diff
-- formatting : yymm, yyyyMM
-- 예 ) 근무 개월 2023년 기준
select first_name,
	hird_date,
    period_diff(date_format(curdate(),'%y%m'),date_format(hire_date, '%y%m'))
    from employees;
    
-- date_add(adddate), date_sub(subdate)
-- 날짜를 data 타입의 컬럼이나 값에 type(year, mmonth, day) 의 표현식으로 더하거나 뺄 수 있다
-- 예) 각 사원ㅇ의 근속 년 수가 5년 되는 날에 휴가 보내준다면 각 사원들의 근속 휴가 날짜는?

select first_name,
hire_date,
date_add(hire_date, interval 5 year)
from employees;

-- cast
-- select '12345' + 10, cast('12345' as int) + 10 from dual;
-- select date_format(cast('2023-08-30') as date), '%Y년%m월%d일') from dual;
select cast(cast(1-2 as unsigned) as signed) from dual;
-- select cast(cast(1-2 as unsigned) as int) from dual;
-- select cast(cast(1-2 as unsigned) as integer) from dual;

-- type
-- 문자 : varchar, char, text, CLOB
-- 정수 : tiny, medium, signed(int, integer), usigned, big int
-- 실수: float, double
-- 시간 : date, datetime
-- LOB : CLOB,
