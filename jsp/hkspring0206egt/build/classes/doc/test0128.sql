1) 부서 번호와 급여가 커미션을 받는 사원의 부서 번호 및 급여와 일치하는 모든 사원의 성, 부서 번호 및 급여를 표시하는 query를 작성합니다. 
    SELECT last_name, department_id, salary   
    FROM   employees    
    WHERE  (salary, department_id) IN      
    (SELECT  salary, department_id           
    FROM    employees           
    WHERE   commission_pct IS NOT NULL); 

2) 급여와 커미션이 위치 ID 1700에 있는 사원의 급여 및 커미션과 일치하는 사원의 성, 부서 이름 및 급여를 표시합니다. 
     SELECT e.last_name, d.department_name, e.salary   
     FROM   employees e, departments d     
     WHERE  e.department_id = d.department_id    
     AND   (salary, NVL(commission_pct,0)) IN    
     (SELECT salary, NVL(commission_pct,0)      
     FROM employees e, departments  d        
     WHERE e.department_id = d.department_id       
     AND d.location_id = 1700); 

3) Kochhar와 동일한 급여 및 커미션을 받는 모든 사원의 성, 채용 날짜 및 급여를 표시하는 query를 작성합니다. 주: 결과 집합에 Kochhar를 표시하지 마십시오. 
SELECT last_name, hire_date, salary FROM   employees
WHERE  (salary, NVL(commission_pct,0)) IN   
(SELECT salary, NVL(commission_pct,0)        
FROM   employees               
WHERE  last_name = 'Kochhar') AND last_name != 'Kochhar'; 

4) 모든 영업 관리자(JOB_ID = 'SA_MAN')보다 많은 급여를 받는 사원을 표시하는 query를 작성합니다. 급여 결과를 하향식으로 정렬합니다. 
    SELECT last_name, job_id, salary   
    FROM   employees     WHERE  salary > ALL   
    (SELECT salary                
    FROM   employees     WHERE  job_id = 'SA_MAN')  
    ORDER BY salary DESC; 

5)  이름이 T로 시작하는 도시에 거주하는 사원의 사원 ID, 성, 부서 ID 등의 세부 정보를 표시합니다. 
     SELECT employee_id, last_name, department_id  
     FROM   employees      WHERE  department_id IN 
     (SELECT department_id         
     FROM departments                     
         WHERE location_id IN       
         (SELECT  location_id        
         FROM locations                 
         WHERE city LIKE 'T%')); 
         

6) 해당 부서의 평균 급여보다 급여 수준이 높은 모든 사원을 찾는 query를 작성합니다. 해당 부서에 대해 사원의 성, 급여, 부서 ID 및 평균 급여를 표시합니다. 평균 급여를 기준으로 정렬합니다. 예제 출력에 표시된 대로 query에 의해 검색되는 행에 alias를 사용합니다.  
  SELECT e.last_name ename, e.salary salary,   
  e.department_id deptno, AVG(a.salary) dept_avg  
  FROM   employees e, employees a       
  WHERE  e.department_id = a.department_id    
  AND    e.salary > (SELECT AVG(salary)     
  FROM   employees                   
  WHERE  department_id = e.department_id )     
  GROUP BY e.last_name, e.salary, e.department_id   
    ORDER BY AVG(a.salary); 

7) 관리자가 아닌 모든 사원을 찾습니다. 
a) 먼저 NOT EXISTS 연산자를 사용하여 이 작업을 수행합니다.  
   SELECT outer.last_name   
   FROM    employees outer    
   WHERE  NOT EXISTS (SELECT 'X'           
   FROM employees inner        
   WHERE inner.manager_id =             
   outer.employee_id); 
   b) 

   NOT IN 연산자를 사용하여 이 작업을 수행할 수 있습니까? 가능하다면 그 방법은 무엇이며 가능하지 않다면 그 이유는 무엇입니까? 
        SELECT outer.last_name     
        FROM   employees outer     
        WHERE  outer.employee_id     
        NOT IN (SELECT inner.manager_id    
        FROM   employees inner);  
        
두번째 방법은 바람직하지 않습니다. Subquery가 NULL 값을 취하므로 전체 query는 아무 행도 반환하지 않습니다. NULL 값을 비교하는 조건은 모두 NULL 이 되기 때문입니다. 값 집합에 NULL 값이 포함될 경우에는 NOT EXISTS 대신 NOT IN 을 사용하지 마십시오 