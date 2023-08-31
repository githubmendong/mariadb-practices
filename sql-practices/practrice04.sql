-- 서브쿼리(SUBQUERY) SQL 문제입니다.

-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?

select count(*) as count_higher_salary
from salaries
where salary > (select avg(salary) from salaries where to_date = '9999-01-01');


-- 문제2.  (x)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요

select e.emp_no, concat(e.first_name, ' ', e.last_name) as employee_name, s.salary
from employees e
         join salaries s on e.emp_no = s.emp_no and s.to_date = '9999-01-01'
         join dept_emp de on e.emp_no = de.emp_no and de.to_date = '9999-01-01'
         join (select de.dept_no, avg(salary) as avg_salary
               from dept_emp de
                        join salaries s on de.emp_no = s.emp_no and s.to_date = '9999-01-01'
               where de.to_date = '9999-01-01'
               group by de.dept_no) avg_salaries on de.dept_no = avg_salaries.dept_no
where s.salary > avg_salaries.avg_salary;


-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.

select e1.emp_no,
       concat(e1.first_name, ' ', e1.last_name) as employee_name,
       concat(e2.first_name, ' ', e2.last_name) as manager_name,
       d.dept_name
from employees e1
         join dept_emp de on e1.emp_no = de.emp_no and de.to_date = '9999-01-01'
         join employees e2 on de.emp_no = e2.emp_no
         join departments d on de.dept_no = d.dept_no;


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

select e.emp_no,
       concat(e.first_name, ' ', e.last_name) as employee_name,
       t.title,
       s.salary
from employees e
         join titles t on e.emp_no = t.emp_no and t.to_date = '9999-01-01'
         join salaries s on e.emp_no = s.emp_no and s.to_date = '9999-01-01'
         join (select de.dept_no, avg(s.salary) as avg_salary
               from dept_emp de
                        join salaries s on de.emp_no = s.emp_no and s.to_date = '9999-01-01'
               where de.to_date = '9999-01-01'
               group by de.dept_no
               order by avg_salary desc limit 1) avg_salary_dept on e.emp_no = avg_salary_dept.emp_no
where s.salary > avg_salary_dept.avg_salary
order by s.salary desc;


-- 문제6.
-- 평균 연봉이 가장 높은 부서는?

select d.dept_name, avg(s.salary) as avg_salary
from departments d
         join dept_emp de on d.dept_no = de.dept_no and de.to_date = '9999-01-01'
         join salaries s on de.emp_no = s.emp_no and s.to_date = '9999-01-01'
group by d.dept_no
order by avg_salary desc limit 1;


-- 문제7.
-- 평균 연봉이 가장 높은 직책?

select t.title, avg(s.salary) as avg_salary
from titles t
         join salaries s on t.emp_no = s.emp_no and s.to_date = '9999-01-01'
group by t.title
order by avg_salary desc limit 1;


-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.


select e.emp_no,
       d.dept_name,
       concat(e.first_name, ' ', e.last_name) as employee_name,
       s.salary,
       concat(m.first_name, ' ', m.last_name) as manager_name,
       ms.salary                              as manager_salary
from employees e
         join dept_emp de on e.emp_no = de.emp_no and de.to_date = '9999-01-01'
         join departments d on de.dept_no = d.dept_no
         join employees m on de.emp_no = m.emp_no
         join salaries s on e.emp_no = s.emp_no and s.to_date = '9999-01-01'
         join salaries ms on m.emp_no = ms.emp_no and ms.to_date = '9999-01-01'
where s.salary > ms.salary;
