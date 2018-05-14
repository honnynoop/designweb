--2017 4  19 jyl_member,jyl_mboard,jyl_calendar »ý¼º
create table jyl_member(
id varchar2(50) primary key,
name varchar2(50)  not null,
email varchar2(50) unique ,
pwd varchar2(50)  not null,
delflag number(1) default 0,
auth number(1) default 3
);
INSERT INTO jyl_member (ID,NAME,EMAIL,PWD)
VALUES('sn001','È«±æµ¿','aa@naver.com','1234');

INSERT INTO jyl_member (ID,NAME,EMAIL,PWD,AUTH)
VALUES('admin','admin','admin@naver.com','1234',1);

select * from jyl_member;


create table jyl_mboard(
seq number primary key,
id varchar2(50) not null,
title varchar2(200) not null,
content varchar2(4000) not null,
wdate date not null,
ref number not null,
step number not null,
depth number not null,
parent number not null,
delflag number(1) default 0
);  
create sequence seq_jyl_mboard 
start with 1 increment by 1;

alter table jyl_mboard ADD CONSTRAINT 
fk_jyl_mboard_member
FOREIGN KEY (id)
		 REFERENCES jyl_member (id);

--0420
create table jyl_calendar(
seq number primary key,
id varchar2(50) not null,
title  varchar2(100) not null,
content varchar2(4000) not null,
wdate varchar2(12) not null,
regdate date not null
);
CREATE SEQUENCE SEQ_jyl_calendar
START WITH 1 INCREMENT BY 1;

ALTER TABLE jyl_calendar ADD CONSTRAINT 
FK_jyl_calendar_MEMBER FOREIGN KEY (ID)
REFERENCES jyl_member (ID);
--0424
create table jyl_pds	(
seq number(8) primary key,
id varchar2(50) not null,
title varchar2(200) not null,
content varchar2(4000) not null,
filename varchar2(50) not null,
readcount number(8) not null,
downcount number(8) not null,
regdate date not null
);	

create sequence seq_jyl_pds
start with 1 increment by 1;

alter table jyl_pds
add constraint fk_jyl_pds_member foreign key(id)
references jyl_member(id);

--0425
create table jyl_poll(
POLLID     NUMBER  primary key,
ID         VARCHAR2(50) NOT NULL,
QUESTION   VARCHAR2(1000) NOT NULL,
SDATE      DATE NOT NULL,
EDATE      DATE NOT NULL,
ITEMCOUNT  NUMBER NOT NULL,
POLLTOTAL  NUMBER NOT NULL,
REGDATE    DATE NOT NULL
);
CREATE sequence seq_jyl_poll START WITH 1 INCREMENT BY 1;

ALTER TABLE jyl_poll ADD CONSTRAINT fk_jyl_poll_member
FOREIGN KEY (ID)
REFERENCES jyl_member (ID);

CREATE table jyl_POLLSUB (
    POLLSUBID  NUMBER primary key,
    POLLID     NUMBER NOT NULL,
    ANSWER     VARCHAR2(1000) NOT NULL,
    ACOUNT     NUMBER NOT NULL
);

CREATE sequence seq_jyl_POLLSUB START WITH 1 INCREMENT BY 1;
  
ALTER TABLE jyl_POLLSUB ADD CONSTRAINT fk_jyl_POLLSUB_poll
FOREIGN KEY (POLLID)
REFERENCES jyl_poll (POLLID);

CREATE table jyl_VOTER (
    VOTERID    NUMBER primary key,
    POLLID     NUMBER,
    POLLSUBID  NUMBER NOT NULL,
    ID         VARCHAR2(50) NOT NULL,
    REGDATE    DATE NOT NULL
);

CREATE sequence seq_jyl_VOTER START WITH 1 INCREMENT BY 1;

ALTER TABLE jyl_VOTER ADD CONSTRAINT fk_jyl_VOTER_POLL
FOREIGN KEY (POLLID)
REFERENCES jyl_poll (POLLID);

ALTER TABLE jyl_VOTER ADD CONSTRAINT fk2_jyl_VOTER_POLLSUB
FOREIGN KEY (POLLSUBID)
REFERENCES jyl_POLLSUB (POLLSUBID);

ALTER TABLE jyl_VOTER ADD CONSTRAINT fk3_jyl_VOTER_member
FOREIGN KEY (ID)
REFERENCES jyl_member (ID);



--drop table hkyoutube cascade constraint;

create table jyl_youtube(
seq number primary key,
vname varchar2(100) not null,
id varchar2(50) not null,
title varchar2(200) not null,
category varchar2(200) not null,
wdate date not null
);

ALTER TABLE jyl_youtube ADD CONSTRAINT 
FK_jyl_youtube_MEMBER FOREIGN KEY (ID)
REFERENCES jyl_member (ID);

create sequence seq_jyl_youtube
start with 1 increment by 1;



SELECT seq,id, TITLE, CONTENT,wdate
		FROM HKM_CALENDAR
		WHERE (substr(wdate,1,4) BETWEEN '2017' AND '2017')
		 AND (substr(wdate,5,2) BETWEEN '03' AND '04');
		 
		 
create table spfp_diary(
seq		number			primary key,
id		varchar2(20)	not null,
wdate	date	default	sysdate,
content	varchar2(2000)	not null,
ref		varchar2(2000),
img		varchar2(100)
);

create sequence SEQ_SPFP_DIARY start with 1 increment by 1;

ALTER TABLE spfp_diary ADD CONSTRAINT fk_spfp_diary_member
FOREIGN KEY (ID)
REFERENCES jyl_member(ID);		 




alter table jyl_member add (TEAM number default 0);
select * from jyl_member;
select * from spfp_diary;

with data as (
SELECT 
			employee_id, 
			manager_id,
			first_name, 
			last_name, email, phone_number,hire_date,LEVEL l
		FROM (SELECT 
					employee_id, 
					manager_id,
					first_name, 
					last_name, email, phone_number,hire_date,
					LEVEL l, 
					SYS_CONNECT_BY_PATH(TO_CHAR(LEVEL,'FM000')||employee_id,'/') ORDER2
		FROM employees
		START WITH  manager_id  is null
		CONNECT BY PRIOR  employee_id = manager_id
		)
ORDER BY ORDER2)
select max(l) from data;

SELECT e.first_name AS employee_name,
       dc.dept_count AS emp_dept_count
FROM   employees e,
       (SELECT department_id, COUNT(*) AS dept_count
        FROM   employees
        GROUP BY department_id) dc
WHERE  e.department_id = dc.department_id;










