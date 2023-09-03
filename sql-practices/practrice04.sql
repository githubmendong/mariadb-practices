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
-- 평균 급여가 가장 높은 부서는?
-- 부서이름, 평균급여
SELECT
    d.dept_name, ROUND(AVG(b.salary)) AS avg_salary
FROM
    employees a,
    salaries b,
    dept_emp c,
    departments d
WHERE
        a.emp_no = b.emp_no
  AND a.emp_no = c.emp_no
  AND c.dept_no = d.dept_no
  AND b.to_date = '9999-01-01'
  AND c.to_date = '9999-01-01'
GROUP BY d.dept_name  -- 부서 이름으로 그룹화해야 합니다.
HAVING avg_salary = (SELECT
                         MAX(avg_salary)
                     FROM
                         (SELECT
                              ROUND(AVG(b.salary)) AS avg_salary
                          FROM
                              employees a, salaries b, dept_emp c
                          WHERE
                                  a.emp_no = b.emp_no
                            AND a.emp_no = c.emp_no
                            AND b.to_date = '9999-01-01'
                            AND c.to_date = '9999-01-01'
                          GROUP BY c.dept_no) AS subquery);

-- 문제7.
-- 평균 급여가 가장 높은 직책?
-- 직책, 평균급여
SELECT
    c.title, ROUND(AVG(b.salary)) AS avg_salary
FROM
    employees a,
    salaries b,
    titles c
WHERE
        a.emp_no = b.emp_no
  AND a.emp_no = c.emp_no
  AND b.to_date = '9999-01-01'
  AND c.to_date = '9999-01-01'
GROUP BY c.title
HAVING avg_salary = (SELECT
                         MAX(avg_salary)
                     FROM
                         (SELECT
                              ROUND(AVG(b.salary)) AS avg_salary
                          FROM
                              employees a, salaries b, titles c
                          WHERE
                                  a.emp_no = b.emp_no
                            AND a.emp_no = c.emp_no
                            AND b.to_date = '9999-01-01'
                            AND c.to_date = '9999-01-01'
                          GROUP BY c.title) AS subquery);



-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.

SELECT
    d.dept_name AS '부서이름',
    CONCAT(e.first_name, ' ', e.last_name) AS '사원이름',
    s.salary AS '연봉',
    CONCAT(m.first_name, ' ', m.last_name) AS '매니저 이름',
    ms.salary AS '매니저 연봉'
FROM
    employees e
        JOIN dept_emp de ON e.emp_no = de.emp_no AND de.to_date = '9999-01-01'
        JOIN departments d ON de.dept_no = d.dept_no
        JOIN dept_manager dm ON de.dept_no = dm.dept_no AND dm.to_date = '9999-01-01'
        JOIN employees m ON dm.emp_no = m.emp_no
        JOIN salaries s ON e.emp_no = s.emp_no AND s.to_date = '9999-01-01'
        JOIN salaries ms ON m.emp_no = ms.emp_no AND ms.to_date = '9999-01-01'
WHERE
        s.salary > ms.salary;
