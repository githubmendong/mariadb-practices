--
-- select 기본 연습
--

-- 예제1: departments 테이블의 모든 데이터를 출력
SELECT 
    *
FROM
    departments;

-- 프로젝션 (projection)
-- 예제2: employees 테이블에서 직원 이름, 성별, 입사일을 출력

select first_name as '이름', gender as '성', hire_date as '입사일' from employees;

-- distinct
-- 예제1 : titles 테이블에서 모든 직급을 출력하세요
SELECT DISTINCT
    title
FROM
    titles;

-- 예제4: TITELS 테이블에서 직급은 어떤 것들이 있는지 직급이름을 한 번씩만 출력하세요.
SELECT DISTINCT
    title
FROM
    titles;


--
-- where 절
--

-- 예제 1: 비교 연산자 : employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일 출력
SELECT 
    *
FROM
    employees
WHERE
    hire_date < '1990-01-01'
    and gender = 'f'
ORDER BY hire_date DESC;

-- 예제2: 논리 연산자 : employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 성별,입사일을 출력하세요.
SELECT 
    *
FROM
    employees
WHERE
    hire_date < '1990-01-01'
    and gender = 'f'
ORDER BY hire_date DESC;

-- 예제3: in 연산자 : dept_emp 테이블에서ㅓ 부서 번호가 d005이거나 d009에 속한 사원의 사번, 부서 번호를 출력
SELECT 
    first_no, dept_no
FROM
    dept_emp
WHERE
    dept_no = 'd005' OR dept_no = 'd009';


SELECT 
    first_no, dept_no
FROM
    dept_emp
WHERE
    dept_no IN ('d005' , 'd009');
    
-- 예제4: like 검색 : employees 테이블에서 1989년에 입사한 직원들의 이름, 입사일을 출력
SELECT 
    first_name, hire_date
FROM
    employees
WHERE
    '1989-01-01' <= hire_date
        AND hire_date <= '1989-12-31';

SELECT 
    first_name, hire_date
FROM
    employees
WHERE
    hire_date BETWEEN '1989-01-01' AND '1989-12-31';
    
    
    
--
-- order by
--

-- 예제1: employees 테이블에서 직원 이름, 성별, 입사일을 입사일 빠른 순으로 출력
SELECT concat(first_name, ' ', last_name) as 'full name', gender, hire_date
from employees
order by hire_date asc;

-- dPwp 2: salaries 테이블에서 2201년 월급이 가장 높은 순으로 사번, 월급을 출력
SELECT 
    emp_no, salary, from_date, to_date
FROM
    salaries
WHERE
    to_dare LIKE '2001%'
        OR from_date LIKE '2001%'
ORDER BY salary DESC;

-- 예제3 : 남자 직원의 이름 성별 입사일을 선임순으로 출력
select first_name, gender, hire_date
from employees
where gender ='m'
order by hire_date desc;

-- 예제4: 직원들의 사번, 월급을 사번순으로 출력하하되 같은 직원의 월급이 높은 순도 반영
select emp_no, salary
from salaries
order by emp_no asc, salary desc;


    '