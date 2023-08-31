-- inner join

-- 예제1 : 현재, 근무하고 있는 이름과 직책을 모두 출력

select a.emp_no, a.first_name, b.title
from employees a, titles b
where  a.emp_no = b.emp_no      -- join 조건 (n-1)
and b.to_date = '9999-01-01'    -- row 선택 조건 1
and a.gender = 'f'              -- row 선택 조건 2
and b.title = 'Engineer';       -- row 선택 조건 3


--
-- ANSI / ISO SQL1999 JOIN 표준 문법
--

-- 1) join ~ on *
-- 예제: 현재, 직책별 평균 연봉을 큰 순서대로 출력
select a.title, avg(b.salary)
    from titles a, salaries
    where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.title
order by avg(b.salary) desc;

-- 2) Natural Join
-- 조인 대상이 되는 테이블들에 이름이 같은 공통 칼럼이 있는 경우
-- 조인 조건을 명시적으로 암묵적으로 조인이 된다.
-- 3) join ~ using

