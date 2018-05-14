1) �μ� ��ȣ�� �޿��� Ŀ�̼��� �޴� ����� �μ� ��ȣ �� �޿��� ��ġ�ϴ� ��� ����� ��, �μ� ��ȣ �� �޿��� ǥ���ϴ� query�� �ۼ��մϴ�. 
    SELECT last_name, department_id, salary   
    FROM   employees    
    WHERE  (salary, department_id) IN      
    (SELECT  salary, department_id           
    FROM    employees           
    WHERE   commission_pct IS NOT NULL); 

2) �޿��� Ŀ�̼��� ��ġ ID 1700�� �ִ� ����� �޿� �� Ŀ�̼ǰ� ��ġ�ϴ� ����� ��, �μ� �̸� �� �޿��� ǥ���մϴ�. 
     SELECT e.last_name, d.department_name, e.salary   
     FROM   employees e, departments d     
     WHERE  e.department_id = d.department_id    
     AND   (salary, NVL(commission_pct,0)) IN    
     (SELECT salary, NVL(commission_pct,0)      
     FROM employees e, departments  d        
     WHERE e.department_id = d.department_id       
     AND d.location_id = 1700); 

3) Kochhar�� ������ �޿� �� Ŀ�̼��� �޴� ��� ����� ��, ä�� ��¥ �� �޿��� ǥ���ϴ� query�� �ۼ��մϴ�. ��: ��� ���տ� Kochhar�� ǥ������ ���ʽÿ�. 
SELECT last_name, hire_date, salary FROM   employees
WHERE  (salary, NVL(commission_pct,0)) IN   
(SELECT salary, NVL(commission_pct,0)        
FROM   employees               
WHERE  last_name = 'Kochhar') AND last_name != 'Kochhar'; 

4) ��� ���� ������(JOB_ID = 'SA_MAN')���� ���� �޿��� �޴� ����� ǥ���ϴ� query�� �ۼ��մϴ�. �޿� ����� ��������� �����մϴ�. 
    SELECT last_name, job_id, salary   
    FROM   employees     WHERE  salary > ALL   
    (SELECT salary                
    FROM   employees     WHERE  job_id = 'SA_MAN')  
    ORDER BY salary DESC; 

5)  �̸��� T�� �����ϴ� ���ÿ� �����ϴ� ����� ��� ID, ��, �μ� ID ���� ���� ������ ǥ���մϴ�. 
     SELECT employee_id, last_name, department_id  
     FROM   employees      WHERE  department_id IN 
     (SELECT department_id         
     FROM departments                     
         WHERE location_id IN       
         (SELECT  location_id        
         FROM locations                 
         WHERE city LIKE 'T%')); 
         

6) �ش� �μ��� ��� �޿����� �޿� ������ ���� ��� ����� ã�� query�� �ۼ��մϴ�. �ش� �μ��� ���� ����� ��, �޿�, �μ� ID �� ��� �޿��� ǥ���մϴ�. ��� �޿��� �������� �����մϴ�. ���� ��¿� ǥ�õ� ��� query�� ���� �˻��Ǵ� �࿡ alias�� ����մϴ�.  
  SELECT e.last_name ename, e.salary salary,   
  e.department_id deptno, AVG(a.salary) dept_avg  
  FROM   employees e, employees a       
  WHERE  e.department_id = a.department_id    
  AND    e.salary > (SELECT AVG(salary)     
  FROM   employees                   
  WHERE  department_id = e.department_id )     
  GROUP BY e.last_name, e.salary, e.department_id   
    ORDER BY AVG(a.salary); 

7) �����ڰ� �ƴ� ��� ����� ã���ϴ�. 
a) ���� NOT EXISTS �����ڸ� ����Ͽ� �� �۾��� �����մϴ�.  
   SELECT outer.last_name   
   FROM    employees outer    
   WHERE  NOT EXISTS (SELECT 'X'           
   FROM employees inner        
   WHERE inner.manager_id =             
   outer.employee_id); 
   b) 

   NOT IN �����ڸ� ����Ͽ� �� �۾��� ������ �� �ֽ��ϱ�? �����ϴٸ� �� ����� �����̸� �������� �ʴٸ� �� ������ �����Դϱ�? 
        SELECT outer.last_name     
        FROM   employees outer     
        WHERE  outer.employee_id     
        NOT IN (SELECT inner.manager_id    
        FROM   employees inner);  
        
�ι�° ����� �ٶ������� �ʽ��ϴ�. Subquery�� NULL ���� ���ϹǷ� ��ü query�� �ƹ� �൵ ��ȯ���� �ʽ��ϴ�. NULL ���� ���ϴ� ������ ��� NULL �� �Ǳ� �����Դϴ�. �� ���տ� NULL ���� ���Ե� ��쿡�� NOT EXISTS ��� NOT IN �� ������� ���ʽÿ� 