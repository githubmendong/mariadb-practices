--	upper
select upper(first_name) from employees;

-- lower
select lower('SEOUL'), lcase('sEoUi') from dual;
select lower(first_name) from employees;

--  substyring (문자열 , indexm, length)
SELECT SUBSTRING('Hello World', 3, 2) FROM DUAL;

-- 예제 )
-- 예제4: like 검색 : employees 테이블에서 1989년에 입사한 직원들의 이름, 입사일을 출력
SELECT 
    first_name, hire_date
FROM
    employees
WHERE
	substring(hire_date,1,4)='1989';
    

-- lpad, rpad : 정렬함수
select lpad('1234', 10,'-'), rpad('1234',10,'-')from dual;

-- 예제 직원 월급 오른쪽으로 정렬
select lpad(salary, 10, '*')
from salaries;

-- trim, ltrim, rtirm
SELECT LTRIM('     hello    ') FROM DUAL;
SELECT concat('---', ltrim('     hello    '), '---') from dual;
SELECT concat('---', trim(leading 'x' from ' hello  '),'----') from dual;
SELECT concat('---', trim(trailing 'x' from ' hello xx '),'----') from dual;
SELECT concat('---', trim(both 'x' from ' xxxxxhelloxxxx  '),'----') from dual;

-- length
select length('Hello World') from dual;